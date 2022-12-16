//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day09{
  private static ArrayList<String> lines;

  public static void readInput(String filepath){
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      lines = new ArrayList<String>();
      String nextLine = null;
      while((nextLine=console.readLine())!=null){
        lines.add(nextLine);
      }
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    Day09Rope rope = new Day09Rope(2);
    for(String str : lines){
      char direction = str.charAt(0);
      int distance = Integer.parseInt(str.substring(2));
      for(int k=0;k<distance;k++){
        rope.move(direction);
      }
    }
    return rope.getSeenCount();
  }
  public static int getPart02(String filepath){
    readInput(filepath);
    Day09Rope rope = new Day09Rope(10);
    for(String str : lines){
      char direction = str.charAt(0);
      int distance = Integer.parseInt(str.substring(2));
      for(int k=0;k<distance;k++){
        rope.move(direction);
      }
    }
    return rope.getSeenCount();
  }

}
