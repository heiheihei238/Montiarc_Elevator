package elevatorControl;

import java.util.List;

/*
 * Elevator Control Center
 * The data from the sensor will be transmitted first to this component
 *
 */
component ElevatorControl {

  port <<sync>> in List<Boolean> btn,
       <<sync>> out List<Boolean> light;
  port <<sync>> in List<Boolean> at,
       <<sync>> out Boolean open, close, up, down,
       <<sync>> in Boolean isOpen, isClosed, isObstacle;

  ControlStation control;

  btn -> control.btn;

  control.light -> light;

  control.req -> elevator.req;

  Elevator elevator;

  at -> elevator.at;

  elevator.open -> open;
  elevator.close -> close;
  elevator.up -> up;
  elevator.down -> down;

  elevator.clear -> control.clear;

  isOpen -> elevator.isOpen;
  isClosed -> elevator.isClosed;
  isObstacle -> elevator.isObstacle;

}
