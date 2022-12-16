//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day12{
  private static Day12HeightMap map;
  private static int startX;
  private static int startY;
  private static int endX;
  private static int endY;

  public static void readInput(String filepath){
    ArrayList<String> lines = new ArrayList<String>();
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

    int width = lines.get(0).length();
    int height = lines.size();
    map = new Day12HeightMap(width, height);
    for(int y=0;y<height;y++){
      String line = lines.get(y);
      String[] cells = line.split("");
      for(int x=0;x<width;x++){
          char val = cells[x].charAt(0);
          map.setCell(x,y,val);
          if(val == 'S'){
            startX = x;
            startY = y;
          }
          if(val == 'E'){
            endX = x;
            endY = y;
          }
      }
    }
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    int[][] path = Day12PathFinder.findPath(map, false);
    return path[map.getEnd().getX()][map.getEnd().getY()];
  }

  public static int getPart02(String filepath){
    readInput(filepath);
    int[][] path = Day12PathFinder.findPath(map, true);
    return path[map.getEnd().getX()][map.getEnd().getY()];
  }

}
