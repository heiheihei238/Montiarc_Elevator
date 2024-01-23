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

public class ControlStation implements montiarc.rte.timesync.IComponent {

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

  protected montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> req;

  public montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> getReq() {
    return this.req;
  }

  public void setReq(montiarc.rte.timesync.IOutPort<java.util.List<java.lang.Boolean>> req) {
    this.req = req;
  }

  /* generated by template ma2java.component.Port.ftl*/

  protected montiarc.rte.timesync.IInPort<java.lang.Integer> clear;

  public montiarc.rte.timesync.IInPort<java.lang.Integer> getClear() {
    return this.clear;
  }

  public void setClear(montiarc.rte.timesync.IInPort<java.lang.Integer> clear) {
    this.clear = clear;
  }

  // parameters

  // variables

  public ControlStation() {}

  /* generated by template ma2java.component.Composed.ftl*/

  // subcomponents
  protected elevatorControl.FloorControl floor;

  public elevatorControl.FloorControl getComponentFloor() {
    return this.floor;
  }

  protected java.util.List<montiarc.rte.timesync.IComponent> getAllSubcomponents() {
    return java.util.Arrays.asList(new montiarc.rte.timesync.IComponent[] {floor});
  }

  @Override
  public void compute() {
    java.util.List<montiarc.rte.timesync.IComponent> notYetComputed =
        new java.util.ArrayList<>(getAllSubcomponents());
    while (notYetComputed.size() > 0) {
      java.util.Set<montiarc.rte.timesync.IComponent> computedThisIteration =
          new java.util.HashSet<>();
      for (montiarc.rte.timesync.IComponent subcomponent : notYetComputed) {
        if (subcomponent.isSynced()) {
          subcomponent.compute();
          computedThisIteration.add(subcomponent);
        }
      }
      if (computedThisIteration.isEmpty()) {
        throw new RuntimeException(
            "Could not complete compute cycle due to not all ports being synced. Likely reasons: Forgot to call init() or cyclic connector loop.");
      } else {
        notYetComputed.removeAll(computedThisIteration);
      }
    }
  }

  public void setUp() {
    this.btn =
        new montiarc.rte.timesync.InPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "btn" : "btn");
    this.clear =
        new montiarc.rte.timesync.InPort<>(
            !this.getInstanceName().isBlank() ? this.getInstanceName() + "." + "clear" : "clear");
    this.floor = new elevatorControl.FloorControl();
    this.floor.setInstanceName(
        !this.getInstanceName().isBlank() ? this.getInstanceName() + ".floor" : "floor");
    this.floor.setUp();
    this.btn.connect(floor.getBtn());
    this.light = floor.getLight();
    this.req = floor.getReq();
    this.clear.connect(floor.getClear());
  }

  @Override
  public void init() {
    this.floor.init();
  }

  @Override
  public void tick() {
    // update subcomponents
    this.floor.tick();
  }

  @Override
  public boolean isSynced() {
    return this.getBtn().isSynced() && this.getClear().isSynced();
  }
}