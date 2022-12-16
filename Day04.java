//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day04{
  private static ArrayList<String> assignmentPairs;

  public static void readInput(String filepath){
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      assignmentPairs = new ArrayList<String>();
      String nextLine = null;
      while((nextLine=console.readLine())!=null){
        assignmentPairs.add(nextLine);
      }
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
  }

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

  public static int getPart01(String filepath){
    readInput(filepath);
    int countFullyContained = 0;
    for(String assignmentPair : assignmentPairs){
      String[] ranges = assignmentPair.split("[,-]");
      if(isFullyContained(Integer.parseInt(ranges[0]),Integer.parseInt(ranges[1]),Integer.parseInt(ranges[2]),Integer.parseInt(ranges[3]))){ countFullyContained++; }
    }
    return countFullyContained;
  }

  public static int getPart02(String filepath){
    readInput(filepath);
    int countOverlaps = 0;
    for(String assignmentPair : assignmentPairs){
      String[] ranges = assignmentPair.split("[,-]");
      if(overlap(Integer.parseInt(ranges[0]),Integer.parseInt(ranges[1]),Integer.parseInt(ranges[2]),Integer.parseInt(ranges[3]))){ countOverlaps++; }
    }
    return countOverlaps;
  }

}
