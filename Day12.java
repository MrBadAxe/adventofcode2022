//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;

public class Day12{

  public static Day12HeightMap generateHeightMap(List<String> input){
    int width = input.get(0).length();
    int height = input.size();
    Day12HeightMap map = new Day12HeightMap(width, height);
    for(int y=0;y<height;y++){
      String line = input.get(y);
      String[] cells = line.split("");
      for(int x=0;x<width;x++){
          char val = cells[x].charAt(0);
          map.setCell(x,y,val);
      }
    }
    return map;
  }

  public static int getPart01(List<String> input){
    Day12HeightMap map = generateHeightMap(input);
    int[][] path = Day12PathFinder.findPath(map, false);
    return path[map.getEnd().getX()][map.getEnd().getY()];
  }

  public static int getPart02(List<String> input){
    Day12HeightMap map = generateHeightMap(input);
    int[][] path = Day12PathFinder.findPath(map, true);
    return path[map.getEnd().getX()][map.getEnd().getY()];
  }

}
