package elevatorControl;

import java.util.List;

/* On the outside of the elevator, there is a button for each floor. When btn=true,
 * it means that the user needs to take the elevator on this floor.
 */
component FloorControl {

  port  in List<Boolean> btn,
        out List<Boolean> light;
  port  in Integer clear,
        out List<Boolean> req;

  int current = 0;

  <<sync>> automaton {
    initial state LightOff;

    LightOff -> LightOff [!btn.get(current) || clear == current] / {
      if(current < 4) {
        current = current + 1;
      }
      else {
        current = 0;
      }
    };
    LightOff -> LightOn [btn.get(current) && clear != current] / {
      light.set(current, true);
      req.set(current, true);
    };

    state LightOn;

    LightOn -> LightOn [clear != current] / {
      if(current < 4) {
        current = current + 1;
      }
      else {
        current = 0;
      }
    };
    LightOn -> LightOff [clear == current] / {
      light.set(current, false);
      req.set(current, false);
    };
  }

}