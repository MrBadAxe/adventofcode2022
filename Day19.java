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

  public static long getPart01(List<String> input){
    List<Day19Blueprint> blueprints = generateBlueprintsList(input);
    for(Day19Blueprint bp : blueprints){
      System.out.println(bp.toString());
    }
    return 0;
  }

}
