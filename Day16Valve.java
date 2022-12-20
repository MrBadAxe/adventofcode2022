import java.util.List;
import java.util.ArrayList;

public class Day16Valve{
  private boolean openState;
  private String name;
  private int flowRate;
  private List<Day16Valve> links;

  public Day16Valve(String name, int rate){
    this.openState = false;
    this.name = name;
    this.flowRate = rate;
    this.links = new ArrayList<Day16Valve>();
  }

  public String getName(){
    return this.name;
  }
  public int getFlowRate(){
    return this.flowRate;
  }
  public List<Day16Valve> getLinks(){
    return this.links;
  }

  public String toString(){
    String z = this.getName() + "(" + this.getFlowRate();
    z += ")->";
    for(Day16Valve link : links){
      z += link.getName() + ",";
    }
    return z;
  }

  public void linkTo(Day16Valve other){
    this.getLinks().add(other);
  }
}
