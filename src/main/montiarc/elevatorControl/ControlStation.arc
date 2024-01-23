package elevatorControl;

import java.util.List;

/* the control station out of the elevator on each floor */
component ControlStation {

  port <<sync>> in List<Boolean> btn,
       <<sync>> out List<Boolean> light,
       <<sync>> out List<Boolean> req;
  port <<sync>> in Integer clear;

  FloorControl floor;

  btn -> floor.btn;

  floor.light -> light;

  floor.req -> req;

  clear -> floor.clear;
}