//package org.mrbadaxe.AdventOfCode2022;

import java.util.ArrayList;
import java.util.List;

public class Day14{

  public static Day14Cave generateCave(List<String> input, boolean floor){
    int maxDepth = 0;

    for(String str : input){
      String[] points = str.split("\s");
      for(String point : points){
        if(!point.equals("->")){
          String[] coords = point.split(",");
          maxDepth = Math.max(maxDepth,Integer.parseInt(coords[1]));
        }
      }
    }
    return new Day14Cave(maxDepth+1,floor);
  }

  public static void generateCaveWalls(Day14Cave cave, List<String> input){
    for(String str : input){
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

  public static int getPart01(List<String> input){
    Day14Cave cave = generateCave(input,false);
    generateCaveWalls(cave, input);

    boolean canPlaceMoreSand = true;
    int countSand = 0;
    while(canPlaceMoreSand){
      canPlaceMoreSand = cave.placeSand();
      if(canPlaceMoreSand){ countSand++; }
    }
    return countSand;
  }

  public static int getPart02(List<String> input){
    Day14Cave cave = generateCave(input,true);
    generateCaveWalls(cave, input);

    boolean canPlaceMoreSand = true;
    int countSand = 0;
    while(canPlaceMoreSand){
      canPlaceMoreSand = cave.placeSand();
      if(canPlaceMoreSand){ countSand++; }
    }
    return countSand;
  }

}
