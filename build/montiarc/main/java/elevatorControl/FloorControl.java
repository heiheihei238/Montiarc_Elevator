/* generated by template ma2java.component.CompilationUnit.ftl*/

/* (c) https://github.com/MontiCore/monticore */
/* generated by template ma2java.Package.ftl*/
package elevatorControl;

/* generated by template ma2java.Import.ftl*/
import java.util.List;
/* generated by template ma2java.Import.ftl*/
import java.lang.*;

/* generated by template ma2java.component.Component.ftl*/

/* generated by template ma2java.component.Header.ftl*/

public class FloorControl implements montiarc.rte.timesync.IComponent {

  private String instanceName = "";

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }

  public String getInstanceName() {
    return this.instanceName;
  }

  // ports
  /* generated by template ma2java.component.Port.ftl*/

  protected montiarc.rte.timesync.IInPort<java.util.List<java.lang.Boolean>> btn;

  public montiarc.rte.timesync.IInPort<java.util.List<java.lang.Boolean>> getBtn() {
    return this.btn;
  }

  public void setBtn(montiarc.rte.timesync.IInPort<java.util.List<java.lang.Boolean>> btn) {
    this.btn = btn;
  }

  /* generated by template ma2java.component.Port.ftl*/

  protected montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> light;

  public montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> getLight() {
    return this.light;
  }

  public void setLight(montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> light) {
    this.light = light;
  }

  /* generated by template ma2java.component.Port.ftl*/

  protected montiarc.rte.timesync.IInPort<java.lang.Integer> clear;

  public montiarc.rte.timesync.IInPort<java.lang.Integer> getClear() {
    return this.clear;
  }

  public void setClear(montiarc.rte.timesync.IInPort<java.lang.Integer> clear) {
    this.clear = clear;
  }

  /* generated by template ma2java.component.Port.ftl*/

  protected montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> req;

  public montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> getReq() {
    return this.req;
  }

  public void setReq(montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> req) {
    this.req = req;
  }

  // parameters

  // variables
  protected int current;

  public FloorControl() {

    this.current = 0;
  }

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
    montiarc.rte.log.Log.trace("Value of input port btn = " + this.getBtn().getValue());
    montiarc.rte.log.Log.trace("Value of input port clear = " + this.getClear().getValue());
    // transition from the current state
    switch (currentState) {
      case LightOff:
        transitionFromLightOff();
        break;
      case LightOn:
        transitionFromLightOn();
        break;
    }
    // log output values
    montiarc.rte.log.Log.trace("Value of output port light = " + this.getLight().getValue());
    montiarc.rte.log.Log.trace("Value of output port req = " + this.getReq().getValue());
    // log state @ post
    montiarc.rte.log.Log.trace("State@post = " + this.getCurrentState());
  }

  protected enum States {
    LightOff(),
    LightOn();

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
      case LightOff:
        exitLightOff();
        break;
      case LightOn:
        exitLightOn();
        break;
    }
    if (from != to && from.getSuperState().isPresent()) {
      exit(from.getSuperState().get(), to);
    }
  }

  protected void transitionFromLightOff() {
    // input
    final java.util.List<java.lang.Boolean> btn = this.getBtn().getValue();
    final java.lang.Integer clear = this.getClear().getValue();

    /* generated by template ma2java.component.Transition.ftl*/

    if (!btn.get(current) || clear == current) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.LightOff);
      // output
      java.util.List<java.lang.Boolean> light = null;
      java.util.List<java.lang.Boolean> req = null;

      // reaction
      {
        if (current < 4) {
          current = current + 1;

        } else {
          current = 0;
        }
      }
      // result
      if (light != null) this.getLight().setValue(light);
      if (req != null) this.getReq().setValue(req);

      // entry state(s)
      this.transitionToLightOff();
    } else
    /* generated by template ma2java.component.Transition.ftl*/

    if (btn.get(current) && clear != current) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.LightOff);
      // output
      java.util.List<java.lang.Boolean> light = null;
      java.util.List<java.lang.Boolean> req = null;

      // reaction
      {
        light.set(current, true);
        req.set(current, true);
      }
      // result
      if (light != null) this.getLight().setValue(light);
      if (req != null) this.getReq().setValue(req);

      // entry state(s)
      this.transitionToLightOn();
    } else {
    }

    this.getLight().sync();
    this.getReq().sync();
  }

  protected void transitionToLightOff() {
    // transition to state
    this.currentState = States.LightOff;
    this.entryLightOff();
  }

  protected void entryLightOff() {}

  protected void exitLightOff() {}

  protected void initLightOff() {}

  protected void transitionFromLightOn() {
    // input
    final java.util.List<java.lang.Boolean> btn = this.getBtn().getValue();
    final java.lang.Integer clear = this.getClear().getValue();

    /* generated by template ma2java.component.Transition.ftl*/

    if (clear != current) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.LightOn);
      // output
      java.util.List<java.lang.Boolean> light = null;
      java.util.List<java.lang.Boolean> req = null;

      // reaction
      {
        if (current < 4) {
          current = current + 1;

        } else {
          current = 0;
        }
      }
      // result
      if (light != null) this.getLight().setValue(light);
      if (req != null) this.getReq().setValue(req);

      // entry state(s)
      this.transitionToLightOn();
    } else
    /* generated by template ma2java.component.Transition.ftl*/

    if (clear == current) {
      // exit state(s)
      this.exit(this.getCurrentState(), States.LightOn);
      // output
      java.util.List<java.lang.Boolean> light = null;
      java.util.List<java.lang.Boolean> req = null;

      // reaction
      {
        light.set(current, false);
        req.set(current, false);
      }
      // result
      if (light != null) this.getLight().setValue(light);
      if (req != null) this.getReq().setValue(req);

      // entry state(s)
      this.transitionToLightOff();
    } else {
    }

    this.getLight().sync();
    this.getReq().sync();
  }

  protected void transitionToLightOn() {
    // transition to state
    this.currentState = States.LightOn;
    this.entryLightOn();
  }

  protected void entryLightOn() {}

  protected void exitLightOn() {}

  protected void initLightOn() {}

  @Override
  public void init() {
    // execute the initial action
    this.initLightOff();
    // transition to the initial state
    this.transitionToLightOff();
    // provide initial value for delay ports

  }

  public void setUp() {
    this.btn =
        new montiarc.rte.timesync.InPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "btn" : "btn");
    this.light =
        new montiarc.rte.timesync.OutPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "light" : "light");
    this.clear =
        new montiarc.rte.timesync.InPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "clear" : "clear");
    this.req =
        new montiarc.rte.timesync.OutPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "req" : "req");
  }

  @Override
  public void tick() {
    // update outgoing ports
    this.light.tick();
    this.req.tick();
  }

  @Override
  public boolean isSynced() {
    return this.getBtn().isSynced() && this.getClear().isSynced();
  }
}
