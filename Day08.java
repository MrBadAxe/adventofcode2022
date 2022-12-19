//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;

public class Day08{

  public static Day08TreeGrid generateGrid(List<String> input){
    int width = input.get(0).length();
    int height = input.size();
    Day08TreeGrid grid = new Day08TreeGrid(width,height);
    for(int cx=0; cx<grid.getWidth(); cx++){
      String[] cells = input.get(cx).split("");
      for(int cy=0; cy<grid.getHeight(); cy++){
        grid.set(cx,cy,Integer.parseInt(cells[cy]));
      }
    }
    return grid;
  }

  public static int getPart01(List<String> input){
    Day08TreeGrid grid = generateGrid(input);
    int countVisible = 0;
    for(int cx=0; cx<grid.getWidth(); cx++){
      String[] cells = input.get(cx).split("");
      for(int cy=0; cy<grid.getHeight(); cy++){
        if(grid.isVisible(cx,cy)){ countVisible++; }
      }
    }
    return countVisible;
  }

  public static int getPart02(List<String> input){
    Day08TreeGrid grid = generateGrid(input);
    int bestView = 0;
    for(int cx=0; cx<grid.getWidth(); cx++){
      String[] cells = input.get(cx).split("");
      for(int cy=0; cy<grid.getHeight(); cy++){
        bestView = Math.max(bestView, grid.getScenicScore(cx,cy));
      }
    }
    return bestView;
  }

}
