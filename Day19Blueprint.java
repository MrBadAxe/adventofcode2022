import java.util.HashMap;
import java.util.List;

public class Day19Blueprint{

  private int timeElapsed;

  public static String[] resourceTypes = {"ore","clay","obsidian","geodes"};

  private HashMap<String,Integer> resources;
  private HashMap<String,Integer> robotCounts;
  private HashMap<String,HashMap<String,Integer>> robotCosts;

  public Day19Blueprint(){
    timeElapsed = 0;
    resources = new HashMap<String,Integer>();
    robotCounts = new HashMap<String,Integer>();
    robotCosts = new HashMap<String,HashMap<String,Integer>>();

    for(String str : resourceTypes){
      resources.put(str,0);
      robotCounts.put(str,(str.equals("ore") ? 1 : 0));
      robotCosts.put(str,new HashMap<String,Integer>());
    }
  }

  public String toString(){
    String z = "";
    z += "TIME: " + timeElapsed + " | ";
    z += "RESOURCES: ";
    for(String str : resourceTypes){
      z += (str + " " + resources.get(str) + " ");
    }
    z += "| ROBOTS: ";
    for(String str : resourceTypes){
      z += (str + " " + robotCounts.get(str) + " ");
    }
    z += "| ROBOT COSTS: \n";
    for(String str : resourceTypes){
      z += "" + str + ": ";
      for(String str2 : robotCosts.get(str).keySet()){
        z += robotCosts.get(str).get(str2) + " " + str2 + " ";
      }
      z += "; ";
    }
    return z;
  }

  public int timeElapsed(){
    return this.timeElapsed;
  }
  public int getResourceCount(String resourceName){
    return resources.get(resourceName);
  }
  private void setResourceCount(String resourceName, int count){
    resources.put(resourceName,count);
  }
  public int getRobotCount(String resourceName){
    return robotCounts.get(resourceName);
  }
  private void setRobotCount(String resourceName, int count){
    robotCounts.put(resourceName,count);
  }
  private void setTimeElapsed(int elapsed){
    this.timeElapsed = elapsed;
  }

  public void setRobotCost(String robotType, String resource, int q){
    if(robotCosts.get(robotType) == null){
      robotCosts.put(robotType,new HashMap<String,Integer>());
    }
    robotCosts.get(robotType).put(resource,q);
  }
  public boolean canBuildRobot(String resourceName){
    boolean z = true;
    HashMap<String,Integer> cost = robotCosts.get(resourceName);
    for(String str : cost.keySet()){
      z &= (resources.get(str) >= cost.get(str));
    }
    return z;
  }

  public List<String> availableBuildActions(){
    List<String> z = new ArrayList<String>();
    z.add("gather");
    String bestRobot = "none";
    for(String str : resourceTypes){
      if(canBuildRobot(str)){
        bestRobot = str;
      }
    }
    if(!bestRobot.equals("none")){ z.add(bestRobot); }
    return z;
  }

  public void tick(){
  public Day19Blueprint copy(){
    Day19Blueprint z = new Day19Blueprint();
    for(String str : resourceTypes){
      z.setResourceCount(str,this.getResourceCount(str));
      z.setRobotCount(str,this.getRobotCount(str));
      z.setTimeElapsed(this.timeElapsed);
      for(String cost : this.robotCosts.get(str).keySet()){
        z.setRobotCost(str,cost,this.robotCosts.get(str).get(cost));
      }
    }
    //System.out.println(z.toString());
    //System.out.println(z.timeElapsed() == this.timeElapsed());
    return z;
  }
    for(String str : resourceTypes){
      resources.put(str,resources.get(str)+robotCounts.get(str));
    }
  }
}
