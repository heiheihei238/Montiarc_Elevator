package elevatorControl;

import elevatorControl.ElevatorIO.DoorCMD;
import elevatorControl.ElevatorIO.LiftCMD;
import elevatorControl.ElevatorIO.Direction;
import java.util.List;

/* according to the request floor number out of the elevator and the pushed button in the elevator,
 * different command of door and lift will be generated.
 */
component Controller {

  port  in List<Boolean> req,
        in List<Boolean> at,
        in Boolean isClosed,
        out DoorCMD door,
        out LiftCMD lift,
        <<delayed>> out Integer clear;

  Direction direction = Direction.NA;
  int current = 0;
  int searchPosition = 0;
  int target = 0;
  int timer = 5;
  boolean stopNext = false;

  <<sync>> automaton {
    initial { clear = 0; } state Init {

      initial state WaitTimer;

      WaitTimer -> WaitTimer [timer > 0] / {
        timer = timer - 1;
        clear = 0;
      };
      WaitTimer -> CloseDoor [timer == 0] / {
        clear = 0;
      };

      state CloseDoor;

      CloseDoor -> CloseDoor [!isClosed] / {
        clear = 0;
      };

      CloseDoor -> OK [at.get(0) && isClosed] / {
        current = 1;
        direction = Direction.UP;
        clear = 0;
      };
      CloseDoor -> DriveDown [!at.get(0) && isClosed] / {
        lift = LiftCMD.DOWN;
        clear = 0;
      };

      state DriveDown;

      DriveDown -> DriveDown [!at.get(0)] / {
        clear = 0;
      };

      DriveDown -> OK [at.get(0)] / {
        searchPosition = 1;
        current = 1;
        direction = Direction.UP;
        clear = 0;
      };

      state OK;

      OK -> WaitReq / {
        stopNext = true;
        clear = 0;
      };
    };

    state WaitReq {
      initial state SearchFloor;

      /* new searchfloor function */
      SearchFloor -> SearchFloorUp[!req.get(searchPosition) && direction == Direction.UP] / {
        if(searchPosition < 4) {
            searchPosition = searchPosition + 1;
        }
        else {
            searchPosition = 1;
        }
        clear = 0;
      };
      SearchFloor -> SearchFloorDown[!req.get(searchPosition) && direction == Direction.DOWN] / {
        if(searchPosition > 1) {
            searchPosition = searchPosition - 1;
        }
        else {
            searchPosition = 4;
        }
        clear = 0;
      };
      SearchFloor -> Found[req.get(searchPosition)] / {
        target = searchPosition;
        clear = 0;
      };

      state SearchFloorUp;

      SearchFloorUp -> SearchFloorUp[!req.get(searchPosition)] / {
        if(searchPosition < 4) {
            searchPosition = searchPosition + 1;
        }
        else {
            searchPosition = 1;
        }
        clear = 0;
      };
      SearchFloorUp -> Found[req.get(searchPosition)] / {
        target = searchPosition;
        clear = 0;
      };

      state SearchFloorDown;

      SearchFloorDown -> SearchFloorDown[!req.get(searchPosition)] / {
        if(searchPosition > 1) {
            searchPosition = searchPosition - 1;
        }
        else {
            searchPosition = 4;
        }
        clear = 0;
      };
      SearchFloorDown -> Found[req.get(searchPosition)] / {
        target = searchPosition;
        clear = 0;
      };

      state Found;

      Found -> Continue [current < target] / {
        direction = Direction.UP;
        stopNext = false;
        clear = 0;
      };
      Found -> Continue [current > target] / {
        direction = Direction.DOWN;
        stopNext = false;
        clear = 0;
      };
      Found -> Continue [current == target] / {
        stopNext = true;
        clear = 0;
      };
    };

    state Continue;

    // go up and stop at next floor
    Continue -> GoUp[direction == Direction.UP && isClosed && at.get(current) && req.get(current + 1) && !stopNext] / {
      current = current + 1;
      stopNext = true;
      lift = LiftCMD.UP;
    };
    // go up and not stop
    Continue -> GoDown[direction == Direction.UP && isClosed && at.get(current) && !req.get(current + 1) && !stopNext] / {
      current = current + 1;
      stopNext = false;
      lift = LiftCMD.UP;
      clear = 0;
    };
    // go down and stop at next floor
    Continue -> GoDown[direction == Direction.DOWN && isClosed && at.get(current) && req.get(current - 1) && !stopNext] / {
      current = current - 1;
      stopNext = true;
      lift = LiftCMD.UP;
    };
    // go down and not stop
    Continue -> GoDown[direction == Direction.DOWN && isClosed && at.get(current) && !req.get(current - 1) && !stopNext] / {
      current = current - 1;
      stopNext = false;
      lift = LiftCMD.UP;
      clear = 0;
    };
    // stop at current floor
    Continue -> Door[at.get(current) && req.get(current) && stopNext] / {
        lift = LiftCMD.STOP;
        door = DoorCMD.OPEN;
        clear = current;
    };

    state GoUp;
    // go up and stop at next floor
    GoUp -> GoUp[direction == Direction.UP && isClosed && at.get(current) && req.get(current + 1) && !stopNext] / {
        current = current + 1;
        stopNext = true;
        lift = LiftCMD.UP;
    };
    // go up and not stop
    GoUp -> GoUp[direction == Direction.UP && isClosed && at.get(current) && !req.get(current + 1) && !stopNext] / {
        current = current + 1;
        stopNext = false;
        lift = LiftCMD.UP;
        clear = 0;
    };
    // stop at current floor
    GoUp -> Door[at.get(current) && req.get(current) && stopNext] / {
        lift = LiftCMD.STOP;
        door = DoorCMD.OPEN;
        clear = current;
    };

    state GoDown;
    // go down and stop at next floor
    GoDown -> GoDown[direction == Direction.DOWN && isClosed && at.get(current) && req.get(current - 1) && !stopNext] / {
        current = current - 1;
        stopNext = true;
        lift = LiftCMD.DOWN;
    };
    // go down and not stop
    GoDown -> GoDown[direction == Direction.DOWN && isClosed && at.get(current) && !req.get(current - 1) && !stopNext] / {
        current = current - 1;
        stopNext = false;
        lift = LiftCMD.DOWN;
        clear = 0;
    };
    // stop at current floor
    GoDown -> Door[at.get(current) && req.get(current) && stopNext] / {
        lift = LiftCMD.STOP;
        door = DoorCMD.OPEN;
        clear = current;
    };

    state Door;

    Door -> Door [req.get(current) && at.get(current)] / {
        door = DoorCMD.OPEN;
        clear = current;
    };
    Door -> SearchFloor [!req.get(current) && isClosed];
  }
}