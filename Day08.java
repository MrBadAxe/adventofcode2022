//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day08{

  private static ArrayList<String> lines;
  private static Day08TreeGrid grid;

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

    int width = lines.get(0).length();
    int height = lines.size();
    grid = new Day08TreeGrid(width,height);
    for(int cx=0; cx<grid.getWidth(); cx++){
      String[] cells = lines.get(cx).split("");
      for(int cy=0; cy<grid.getHeight(); cy++){
        grid.set(cx,cy,Integer.parseInt(cells[cy]));
      }
    }
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    int countVisible = 0;
    for(int cx=0; cx<grid.getWidth(); cx++){
      String[] cells = lines.get(cx).split("");
      for(int cy=0; cy<grid.getHeight(); cy++){
        if(grid.isVisible(cx,cy)){ countVisible++; }
      }
    }
    return countVisible;
  }

  public static int getPart02(String filepath){
    readInput(filepath);
    int bestView = 0;
    for(int cx=0; cx<grid.getWidth(); cx++){
      String[] cells = lines.get(cx).split("");
      for(int cy=0; cy<grid.getHeight(); cy++){
        bestView = Math.max(bestView, grid.getScenicScore(cx,cy));
      }
      //System.out.print("\n");
    }
    return bestView;
  }

}
