package elevatorControl;

import elevatorControl.ElevatorIO.LiftCMD;

/* Up and down control of the elevator */
component Lift {

  port in LiftCMD cmd;
  port out Boolean up;
  port out Boolean down;

  <<sync>> automaton {
    initial state Wait;

    Wait -> Wait [cmd == null || cmd == LiftCMD.STOP] / {
      up = false;
      down = false;
    };
    Wait -> Up [cmd != null && cmd == LiftCMD.UP] / {
      up = true;
      down = false;
    };
    Wait -> Down [cmd != null && cmd == LiftCMD.DOWN] / {
      up = false;
      down = true;
    };

    state Up;

    Up -> Wait [cmd != null && cmd == LiftCMD.STOP] / {
       up = false;
       down = false;
     };
    Up -> Up [cmd == null || cmd == LiftCMD.UP] / {
      up = true;
      down = false;
    };
    Up -> Down [cmd != null && cmd == LiftCMD.DOWN] / {
      up = false;
      down = true;
    };

    state Down;

    Down -> Wait [cmd != null && cmd == LiftCMD.STOP] / {
       up = false;
       down = false;
     };
    Down -> Up [cmd != null && cmd == LiftCMD.UP] / {
      up = true;
      down = false;
    };
    Down -> Down [cmd == null || cmd == LiftCMD.DOWN] / {
      up = false;
      down = true;
    };
  }
}