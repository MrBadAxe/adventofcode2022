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

  public int getResourceCount(String resourceName){
    return resources.get(resourceName);
  }
  public void setRobotCost(String robotType, String resource, int q){
    if(robotCosts.get(robotType) == null){
      robotCosts.put(robotType,new HashMap<String,Integer>());
    }
    robotCosts.get(robotType).put(resource,q);
  }

  public void tick(){
    for(String str : resourceTypes){
      resources.put(str,resources.get(str)+robotCounts.get(str));
    }
  }
}
