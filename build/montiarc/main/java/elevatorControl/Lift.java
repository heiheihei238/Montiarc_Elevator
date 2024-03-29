/* generated by template ma2java.component.CompilationUnit.ftl*/

/* (c) https://github.com/MontiCore/monticore */
/* generated by template ma2java.Package.ftl*/
package elevatorControl;

/* generated by template ma2java.Import.ftl*/
import elevatorControl.ElevatorIO.LiftCMD;
/* generated by template ma2java.Import.ftl*/
import java.lang.*;

/* generated by template ma2java.component.Component.ftl*/

/* generated by template ma2java.component.Header.ftl*/

public class Lift implements montiarc.rte.timesync.IComponent {

  private String instanceName = "";

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }

  public String getInstanceName() {
    return this.instanceName;
  }

  // ports
  /* generated by template ma2java.component.Port.ftl*/

  protected montiarc.rte.timesync.IInPort<elevatorControl.ElevatorIO.LiftCMD> cmd;

  public montiarc.rte.timesync.IInPort<elevatorControl.ElevatorIO.LiftCMD> getCmd() {
    return this.cmd;
  }

  public void setCmd(montiarc.rte.timesync.IInPort<elevatorControl.ElevatorIO.LiftCMD> cmd) {
    this.cmd = cmd;
  }

  /* generated by template ma2java.component.Port.ftl*/

  protected montiarc.rte.timesync.IOutPort<java.lang.Boolean> up;

  public montiarc.rte.timesync.IOutPort<java.lang.Boolean> getUp() {
    return this.up;
  }

  public void setUp(montiarc.rte.timesync.IOutPort<java.lang.Boolean> up) {
    this.up = up;
  }

  /* generated by template ma2java.component.Port.ftl*/

  protected montiarc.rte.timesync.IOutPort<java.lang.Boolean> down;

  public montiarc.rte.timesync.IOutPort<java.lang.Boolean> getDown() {
    return this.down;
  }

  public void setDown(montiarc.rte.timesync.IOutPort<java.lang.Boolean> down) {
    this.down = down;
  }

  // parameters

  // variables

  public Lift() {}

  /* generated by template ma2java.component.Atomic.ftl*/

  /* generated by template ma2java.component.Automaton.ftl*/

  protected States currentState;

  protected States getCurrentState() {
    return this.currentState;
  }

  public void compute() {
    montiarc.rte.log.Log.comment("Computing component " + this.getInstanceName() + "");
    // log state @ pre
    montiarc.rte.log.Log.trace("State@pre = " + this.getCurrentState());
    // log input values
    montiarc.rte.log.Log.trace("Value of input port cmd = " + this.getCmd().getValue());
    // transition from the current state
    switch (currentState) {
      case Wait:
        transitionFromWait();
        break;
      case Up:
        transitionFromUp();
        break;
      case Down:
        transitionFromDown();
        break;
    }
    // log output values
    montiarc.rte.log.Log.trace("Value of output port up = " + this.getUp().getValue());
    montiarc.rte.log.Log.trace("Value of output port down = " + this.getDown().getValue());
    // log state @ post
    montiarc.rte.log.Log.trace("State@post = " + this.getCurrentState());
  }

  protected enum States {
    Wait(),
    Up(),
    Down();

    final States superState;

    java.util.Optional<States> getSuperState() {
      return java.util.Optional.ofNullable(this.superState);
    }

    States() {
      this.superState = null;
    }

    States(States superState) {
      this.superState = superState;
    }
  }

  protected void exit(States from, States to) {
    switch (from) {
      case Wait:
        exitWait();
        break;
      case Up:
        exitUp();
        break;
      case Down:
        exitDown();
        break;
    }
    if (from != to && from.getSuperState().isPresent()) {
      exit(from.getSuperState().get(), to);
    }
  }

  protected void transitionFromWait() {
    // input
    final elevatorControl.ElevatorIO.LiftCMD cmd = this.getCmd().getValue();

    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd == null || cmd == LiftCMD.STOP) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Wait);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = false;
        down = false;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToWait();
    } else
    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd != null && cmd == LiftCMD.UP) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Wait);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = true;
        down = false;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToUp();
    } else
    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd != null && cmd == LiftCMD.DOWN) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Wait);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = false;
        down = true;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToDown();
    } else {
    }

    this.getUp().sync();
    this.getDown().sync();
  }

  protected void transitionToWait() {
    // transition to state
    this.currentState = States.Wait;
    this.entryWait();
  }

  protected void entryWait() {}

  protected void exitWait() {}

  protected void initWait() {}

  protected void transitionFromUp() {
    // input
    final elevatorControl.ElevatorIO.LiftCMD cmd = this.getCmd().getValue();

    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd != null && cmd == LiftCMD.STOP) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Up);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = false;
        down = false;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToWait();
    } else
    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd == null || cmd == LiftCMD.UP) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Up);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = true;
        down = false;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToUp();
    } else
    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd != null && cmd == LiftCMD.DOWN) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Up);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = false;
        down = true;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToDown();
    } else {
    }

    this.getUp().sync();
    this.getDown().sync();
  }

  protected void transitionToUp() {
    // transition to state
    this.currentState = States.Up;
    this.entryUp();
  }

  protected void entryUp() {}

  protected void exitUp() {}

  protected void initUp() {}

  protected void transitionFromDown() {
    // input
    final elevatorControl.ElevatorIO.LiftCMD cmd = this.getCmd().getValue();

    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd != null && cmd == LiftCMD.STOP) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Down);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = false;
        down = false;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToWait();
    } else
    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd != null && cmd == LiftCMD.UP) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Down);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = true;
        down = false;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToUp();
    } else
    /* generated by template ma2java.component.Transition.ftl*/

    if (cmd == null || cmd == LiftCMD.DOWN) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.Down);
      // output
      java.lang.Boolean up = null;
      java.lang.Boolean down = null;

      // reaction
      {
        up = false;
        down = true;
      }
      // result
      if (up != null) this.getUp().setValue(up);
      if (down != null) this.getDown().setValue(down);

      // entry state(s)
      this.transitionToDown();
    } else {
    }

    this.getUp().sync();
    this.getDown().sync();
  }

  protected void transitionToDown() {
    // transition to state
    this.currentState = States.Down;
    this.entryDown();
  }

  protected void entryDown() {}

  protected void exitDown() {}

  protected void initDown() {}

  @Override
  public void init() {
    // execute the initial action
    this.initWait();
    // transition to the initial state
    this.transitionToWait();
    // provide initial value for delay ports

  }

  public void setUp() {
    this.cmd =
        new montiarc.rte.timesync.InPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "cmd" : "cmd");
    this.up =
        new montiarc.rte.timesync.OutPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "up" : "up");
    this.down =
        new montiarc.rte.timesync.OutPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "down" : "down");
  }

  @Override
  public void tick() {
    // update outgoing ports
    this.up.tick();
    this.down.tick();
  }

  @Override
  public boolean isSynced() {
    return this.getCmd().isSynced();
  }
}
