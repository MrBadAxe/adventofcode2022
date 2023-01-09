//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day19{
  public static List<Day19Blueprint> generateBlueprintsList(List<String> input){
    List<Day19Blueprint> blueprints = new ArrayList<Day19Blueprint>();
    String blueprintDesc = "Blueprint (\\d+): Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. Each obsidian robot costs (\\d+) ore and (\\d+) clay. Each geode robot costs (\\d+) ore and (\\d+) obsidian.";
    Pattern p = Pattern.compile(blueprintDesc);
    for(String str : input){
      Matcher m = p.matcher(str);
      if(m.find()){
        Day19Blueprint bp = new Day19Blueprint();
        bp.setRobotCost("ore","ore",Integer.parseInt(m.group(2)));
        bp.setRobotCost("clay","ore",Integer.parseInt(m.group(3)));
        bp.setRobotCost("obsidian","ore",Integer.parseInt(m.group(4)));
        bp.setRobotCost("obsidian","clay",Integer.parseInt(m.group(5)));
        bp.setRobotCost("geodes","ore",Integer.parseInt(m.group(6)));
        bp.setRobotCost("geodes","obsidian",Integer.parseInt(m.group(7)));
        blueprints.add(bp);
      }
    }
    return blueprints;
  }

  public static List<String> cullActions(Day19MiningOperation op, List<String> actions, int maxTime){
    //don't build an ore robot if we generate enough in a turn to build any robot
    if(op.robots("ore") >= op.blueprint().maxRobotsNeeded("ore")){
      actions.remove("ore");
    }

    //  don't build:
    //  * anything, on (lastTurn - 1)
    //  * anything but a geode robot, on (lastTurn - 2)
    //  * a clay robot, on (lastTurn - 3)
    if(op.timeElapsed() == maxTime - 3){
      actions.remove("clay");
    }else if(op.timeElapsed() == maxTime - 2){
      actions.remove("clay");
      actions.remove("ore");
      actions.remove("obsidian");
    }else if(op.timeElapsed() == maxTime - 1){
      actions.remove("clay");
      actions.remove("ore");
      actions.remove("obsidian");
      actions.remove("geodes");
    }
    // always build a geode robot if you can
    if(actions.contains("geodes")){
      actions.clear();
      actions.add("geodes");
    }
    return actions;
  }
  public static long getPart01(List<String> input){
    List<Day19Blueprint> blueprints = generateBlueprintsList(input);
    for(int k=0;k<blueprints.size();k++){
      Day19Blueprint bp = blueprints.get(k);
      //System.out.println(bp.toString());
      List<Day19Blueprint> processing = new ArrayList<Day19Blueprint>();
      List<Day19Blueprint> completed = new ArrayList<Day19Blueprint>();
      processing.add(bp);

      while(processing.size() > 0){
        Day19Blueprint current = processing.remove(0);
        //System.out.println(current.timeElapsed() + " " + processing.size());
        List<String> actions = current.availableBuildActions();
        for(String str : actions){
          Day19Blueprint newbp = current.copy();
          newbp.tick(str);
          //System.out.println(newbp.toString());
          if(newbp.timeElapsed() >= 24){
            //System.out.println("needs more");
            completed.add(newbp);
          }else{
            processing.add(newbp);
          }
        }
      }

      int best = 0;
      for(Day19Blueprint result : completed){
        best = Math.max(best,result.getResourceCount("geodes"));
      }
      System.out.println(best * (k+1));
    }
    return 0;
  }

}
