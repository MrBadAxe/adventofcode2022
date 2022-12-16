//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day06{
  private static String signal;

  public static void readInput(String filepath){
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      ArrayList<String> lines = new ArrayList<String>();
      String nextLine = null;
      while((nextLine=console.readLine())!=null){
        lines.add(nextLine);
      }
      signal = lines.get(0);
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
  }

  public static boolean containsNoDuplicateLetters(String s){
    //System.out.println(s);
    if(s.length() == 1){
      return true;
    }else{
      //System.out.println(s.substring(1) + ":" + s.substring(0,1));
      if(s.substring(1).contains(s.substring(0,1))){
        return false;
      }else{
        return containsNoDuplicateLetters(s.substring(1));
      }
    }
  }

  public static int getPart01(String filepath){
    int markerLength = 4;
    readInput(filepath);
    int markerPos = 0;
    boolean markerFound = false;
    while(!markerFound){
      String potentialMarker = signal.substring(markerPos,markerPos+markerLength);
      //System.out.println(potentialMarker);
      markerFound = containsNoDuplicateLetters(potentialMarker);
      markerPos++;
    }
    return markerPos + markerLength - 1;
  }

  public static int getPart02(String filepath){
    int markerLength = 14;
    readInput(filepath);
    int markerPos = 0;
    boolean markerFound = false;
    while(!markerFound){
      String potentialMarker = signal.substring(markerPos,markerPos+markerLength);
      //System.out.println(potentialMarker);
      markerFound = containsNoDuplicateLetters(potentialMarker);
      markerPos++;
    }
    return markerPos + markerLength - 1;
  }

}
