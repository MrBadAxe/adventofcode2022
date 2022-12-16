//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day14{
  private static ArrayList<String> lines;
  private static Day14Cave cave;

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

  public static void generateCave(boolean floor){
    int maxDepth = 0;

    for(String str : lines){
      String[] points = str.split("\s");
      for(String point : points){
        if(!point.equals("->")){
          String[] coords = point.split(",");
          maxDepth = Math.max(maxDepth,Integer.parseInt(coords[1]));
        }
      }
    }
    //System.out.println(maxDepth);
    cave = new Day14Cave(maxDepth+1,floor);
  }

  public static void generateCaveWalls(){
    for(String str : lines){
      String[] points = str.split("\s");
      Point prevPoint = null;
      for(String point : points){
        if(!point.equals("->")){
          String[] coords = point.split(",");
          Point nextPoint = new Point(Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
          if(prevPoint != null){
            cave.placeCaveWall(prevPoint.getX(),prevPoint.getY(),nextPoint.getX(),nextPoint.getY());
          }
          prevPoint = nextPoint;
        }
      }
    }
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    generateCave(false);
    generateCaveWalls();

    boolean canPlaceMoreSand = true;
    int countSand = 0;
    while(canPlaceMoreSand){
      canPlaceMoreSand = cave.placeSand();
      if(canPlaceMoreSand){ countSand++; }
    }
    //System.out.println(cave.toString(450,550));
    return countSand;
  }

  public static int getPart02(String filepath){
    readInput(filepath);
    generateCave(true);
    generateCaveWalls();

    boolean canPlaceMoreSand = true;
    int countSand = 0;
    while(canPlaceMoreSand){
      canPlaceMoreSand = cave.placeSand();
      if(canPlaceMoreSand){ countSand++; }
    }
    //System.out.println(cave.toString(390,610));
    return countSand;
  }

}
