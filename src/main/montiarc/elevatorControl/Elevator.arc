package elevatorControl;

import java.util.List;

/*return the status of elevator: open, close, up or down.*/
component Elevator {

  port <<sync>> in List<Boolean> req,
       <<sync>> out Integer clear;
  port <<sync>> in List<Boolean> at,
       <<sync>> out Boolean open, close, up, down,
       <<sync>> in Boolean isOpen, isClosed, isObstacle;

  Controller ctrl;

  req -> ctrl.req;

  ctrl.clear -> clear;

  at -> ctrl.at;

  isOpen -> door.isOpen;
  isClosed -> door.isClosed;
  isObstacle -> door.isObstacle;

  Door door;

  door.open -> open;
  door.close -> close;
  door.closed -> ctrl.isClosed;

  Lift lift;

  lift.up -> up;
  lift.down -> down;

  ctrl.door -> door.cmd;
  ctrl.lift -> lift.cmd;

}