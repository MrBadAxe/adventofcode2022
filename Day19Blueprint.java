import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Day19Blueprint{

  public static String[] resourceTypes = {"ore","clay","obsidian","geodes"};

  private HashMap<String,HashMap<String,Integer>> robotCosts;

  public Day19Blueprint(){
    robotCosts = new HashMap<String,HashMap<String,Integer>>();
    for(String str : resourceTypes){
      robotCosts.put(str,new HashMap<String,Integer>());
    }
  }

  public String toString(){
    String z = "";
    for(String robot : resourceTypes){
      z += (robot + ": ");
      for(String cost : robotCosts.get(robot).keySet()){
        z += (robotCosts.get(robot).get(cost) + " " + cost + " ");
      }
      z += "; ";
    }
    return z;
  }

  public int getRobotCost(String robotType, String resource){
    return (robotCosts.get(robotType).get(resource) == null ? 0 : robotCosts.get(robotType).get(resource));
  }
  public void setRobotCost(String robotType, String resource, int q){
    if(robotCosts.get(robotType) == null){
      robotCosts.put(robotType,new HashMap<String,Integer>());
    }
    robotCosts.get(robotType).put(resource,q);
  }

  public int maxRobotsNeeded(String resourceName){
    int z = 0;
    for(String str : resourceTypes){
      int cost = (robotCosts.get(str).get(resourceName) == null ? 0 : robotCosts.get(str).get(resourceName));
      z = Math.max(z,cost);
    }
    if(resourceName.equals("geodes")){
      z = Integer.MAX_VALUE;
    }
    return z;
  }

}
