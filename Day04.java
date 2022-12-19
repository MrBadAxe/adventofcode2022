//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;

public class Day04{

  private static boolean isFullyContained(int al, int au, int bl, int bu){
    if(al > bl){ return isFullyContained(bl,bu,al,au); }
    if(al == bl && bu > au){return isFullyContained(bl,bu,al,au);}

    if(au == bl && (al == au || bl == bu)){ return true; }
    if(bl >= al && bu <= au){ return true; }

    return false;
  }

  private static boolean overlap(int al, int au, int bl, int bu){
    if(al > bl){ return overlap(bl,bu,al,au); }
    if(al == bl && bu > au){return overlap(bl,bu,al,au);}

    if(bl <= au){ return true; }
    return false;
  }

  public static int getPart01(List<String> input){
    List<String> assignmentPairs = input;
    int countFullyContained = 0;
    for(String assignmentPair : assignmentPairs){
      String[] ranges = assignmentPair.split("[,-]");
      if(isFullyContained(Integer.parseInt(ranges[0]),Integer.parseInt(ranges[1]),Integer.parseInt(ranges[2]),Integer.parseInt(ranges[3]))){ countFullyContained++; }
    }
    return countFullyContained;
  }

  public static int getPart02(List<String> input){
    List<String> assignmentPairs = input;
    int countOverlaps = 0;
    for(String assignmentPair : assignmentPairs){
      String[] ranges = assignmentPair.split("[,-]");
      if(overlap(Integer.parseInt(ranges[0]),Integer.parseInt(ranges[1]),Integer.parseInt(ranges[2]),Integer.parseInt(ranges[3]))){ countOverlaps++; }
    }
    return countOverlaps;
  }

}
