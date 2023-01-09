import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Day19MiningOperation{

  private int timeRemaining;

  private HashMap<String,Integer> resources;
  private HashMap<String,Integer> robots;
  private Day19Blueprint blueprint;

  public Day19MiningOperation(Day19Blueprint bp, int timer){
    timeRemaining = timer;
    resources = new HashMap<String,Integer>();
    robots = new HashMap<String,Integer>();
    blueprint = bp;

    for(String str : blueprint.resourceTypes){
      resources.put(str,0);
      robots.put(str,0);
    }
    robots.put("ore",1);
  }

  public Day19MiningOperation copy(){
    Day19MiningOperation z = new Day19MiningOperation(this.blueprint, this.timeRemaining);
    for(String str : this.blueprint.resourceTypes){
      z.setResources(str,this.resources(str));
      z.setRobots(str,this.robots(str));
    }
    return z;
  }

  public String toString(){
    String z = "";
    z += "TIME: " + this.timeElapsed + " | ";
    z += "RESOURCES: ";
    for(String str : this.blueprint.resourceTypes){
      z += (str + " " + resources.get(str) + " ");
    }
    z += "| ROBOTS: ";
    for(String str : this.blueprint.resourceTypes){
      z += (str + " " + robots.get(str) + " ");
    }
    z += "| ACTIONS: ";
    for(String str : this.availableBuildActions()){
      z += str + " ";
    }
    return z;
  }

  public int timeRemaining(){
    return this.timeRemaining;
  }
  private void setTimeElapsed(int elapsed){
    this.timeRemaining = elapsed;
  }

  public int resources(String resourceName){
    return resources.get(resourceName);
  }
  private void setResources(String resourceName, int count){
    resources.put(resourceName,count);
  }

  public int robots(String resourceName){
    return robots.get(resourceName);
  }
  private void setRobots(String resourceName, int count){
    robots.put(resourceName,count);
  }

  public boolean canEventuallyBuildRobot(String resourceName){
    boolean z = true;
    for(String str : blueprint.resourceTypes){
      z &= (blueprint.getRobotCost(resourceName,str) == 0 || robots.get(str) > 0);
    }
    return z;
  }
  public List<String> unlockedRobots(){
    List<String> z = new ArrayList<String>();
    for(int k=0;k<blueprint.resourceTypes.length;k++){
      String str = blueprint.resourceTypes[blueprint.resourceTypes.length-(k+1)];
      if(canEventuallyBuildRobot(str)){
        z.add(str);
      }
    }
    return z;
  }

  public boolean canBuildRobotNow(String resourceName){
    boolean z = true;
    //HashMap<String,Integer> cost = robotCosts.get(resourceName);
    for(String str : blueprint.resourceTypes){
      z &= (resources.get(str) >= blueprint.getRobotCost(resourceName,str));
    }
    return z;
  }
  public List<String> availableBuildActions(){
    List<String> z = new ArrayList<String>();
    for(int k=0;k<blueprint.resourceTypes.length;k++){
      String str = blueprint.resourceTypes[blueprint.resourceTypes.length-(k+1)];
      if(canBuildRobotNow(str)){
        z.add(str);
      }
    }
    z.add("gather");
    return z;
  }

  public void tick(String action){
    //spend to build
    if(!action.equals("gather")){
      for(String str : blueprint.resourceTypes){
        resources.put(str,resources.get(str)-(blueprint.getRobotCost(action,str)));
      }
    }

    //gather
    for(String str : blueprint.resourceTypes){
      resources.put(str,resources.get(str)+robots.get(str));
    }

    //add robot
    if(!action.equals("gather")){
      robots.put(action,robots.get(action)+1);
    }

    //advance timer
    timeRemaining--;
  }

  public int maxRemainingGeodes(){
    return (this.timeRemaining() * (this.timeRemaining() + 1) / 2) + (this.timeRemaining() * this.robots("geodes")) + this.resources("geodes");
  }

}
