//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Day16{

  public static HashMap<String,Day16Valve> generateValveGraph(List<String> input){
    HashMap<String,Day16Valve> valveGraph = new HashMap<String,Day16Valve>();
    for(String str: input){
      String[] tokens = str.split("\s");
      String name = tokens[1];
      int flowRate = Integer.parseInt(tokens[4].substring(5,tokens[4].length()-1));
      Day16Valve valve = new Day16Valve(name, flowRate);
      valveGraph.put(valve.getName(),valve);
    }
    for(String str: input){
      String[] tokens = str.split("\s");
      Day16Valve from = valveGraph.get(tokens[1]);
      for(int k=9;k<tokens.length;k++){
        Day16Valve to = valveGraph.get(tokens[k].substring(0,2));
        from.linkTo(to);
      }
    }
    return valveGraph;
  }

  public static int getPart01(List<String> input){
    Day16ValveGraph valveGraph = new Day16ValveGraph(generateValveGraph(input));
    valveGraph.addAgent("h",new Day16Agent(valveGraph.getValveGraph().get("AA"),30));

    List<Day16ValveGraph> states = new ArrayList<Day16ValveGraph>();
    states.add(valveGraph);
    int best = 0;
    long start = System.currentTimeMillis();
    while(states.size() > 0){
      Day16ValveGraph current = states.remove(0);
      for(String str : current.getValveGraph().keySet()){
        Day16ValveGraph potentialMove = current.move("h",str);
        //System.out.println(current.toString() + "->" + potentialMove.toString());
        best = Math.max(best,potentialMove.getTotalPressureReleased());
        if(potentialMove.getTotalPressureReleased() > current.getTotalPressureReleased()){
          states.add(potentialMove);
        }
      }
    }
    System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");

    return best;
  }

  public static int getPart02(List<String> input){
    Day16ValveGraph valveGraph = new Day16ValveGraph(generateValveGraph(input));
    valveGraph.addAgent("h",new Day16Agent(valveGraph.getValveGraph().get("AA"),26));
    valveGraph.addAgent("e",new Day16Agent(valveGraph.getValveGraph().get("AA"),26));

    List<Day16ValveGraph> states = new ArrayList<Day16ValveGraph>();
    states.add(valveGraph);
    int best = 0;
    long start = System.currentTimeMillis();
    while(states.size() > 0){
      Day16ValveGraph current = states.remove(0);
      for(String str : current.getValveGraph().keySet()){
        if(current.getValveGraph().get(str).getFlowRate() > 0 && !current.isOpen(str)){
          Day16ValveGraph potentialMove = current.move(((current.getAgent("e").getMinutesRemaining() > current.getAgent("h").getMinutesRemaining()) ? "e" : "h"),str);
          System.out.print(states.size() + "\t");
          System.out.println(current.toString() + "\t->\t" + potentialMove.toString());
          best = Math.max(best,potentialMove.getTotalPressureReleased());
          if(potentialMove.getTotalPressureReleased() > current.getTotalPressureReleased()){
            states.add(0,potentialMove);
          }

        }
      }
    }
    System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");

    return best;
  }
}
