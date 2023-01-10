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

    return actions;
  }

  public static int calculateQualityLevel(Day19Blueprint bp, int maxTime){
    List<Day19MiningOperation> processing = new ArrayList<Day19MiningOperation>();

    Day19MiningOperation startop = new Day19MiningOperation(bp,maxTime);
    processing.add(startop);

    Day19MiningOperation bestop = null;

    while(processing.size() > 0){
      Day19MiningOperation cur = processing.remove(0);

      //List<String> actions = cur.availableBuildActions();

      //System.out.println(cur.toString());

      List<String> actions = cur.unlockedRobots();
      actions = cullActions(cur, actions, maxTime);


      List<Day19MiningOperation> results = new ArrayList<Day19MiningOperation>();
      for(String str : actions){
        Day19MiningOperation newop = cur.copy();
        while(!newop.canBuildRobotNow(str)){
          newop.tick("gather");
        }
        newop.tick(str);
        if(newop.timeRemaining() >= 0){
          results.add(newop);
        }
      }

      if(results.size() == 0){
        while(cur.timeRemaining() > 0){
          cur.tick("gather");
        }
        results.add(cur);
      }

      for(Day19MiningOperation op : results){
        if(bestop == null || op.resources("geodes") > bestop.resources("geodes")){
          bestop = op;
        }
        if(op.timeRemaining() > 0 && op.maxRemainingGeodes() > bestop.resources("geodes")){
          processing.add(op);
        }
      }
    }

    return bestop.resources("geodes");
  }

  public static long getPart01(List<String> input){
    int MAX_TIME = 24;

    List<Day19Blueprint> blueprints = generateBlueprintsList(input);

    int total = 0;
    for(int k=0;k<blueprints.size();k++){
      Day19Blueprint bp = blueprints.get(k);
      int blueprintBestResult = calculateQualityLevel(bp, MAX_TIME);
      //System.out.println(blueprintBestResult);
      total += blueprintBestResult * (k+1);
    }

    return total;
  }

}
