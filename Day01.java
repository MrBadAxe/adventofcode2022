//package org.mrbadaxe.AdventOfCode2022;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day01{

  public static List<String> readInput(String filepath){
    List<String> lines = new ArrayList<String>();
    try{
      lines = Files.readAllLines(Path.of(filepath));
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
    return lines;
  }

  public static ArrayList<Integer> generateElfCalorieCountsList(List<String> lines){
    ArrayList<Integer> elfCalorieCounts = new ArrayList<Integer>();
    int nextCalorieCount = 0;
    for(String line : lines){
      if(line.equals("")){
        elfCalorieCounts.add(nextCalorieCount);
        nextCalorieCount = 0;
      }else{
        nextCalorieCount += Integer.parseInt(line);
      }
    }
    return elfCalorieCounts;
  }

  public static int getPart01(String filepath){
    ArrayList<Integer> elfCalorieCounts = generateElfCalorieCountsList(readInput(filepath));
    int maxCalorieCount = 0;
    for(Integer i : elfCalorieCounts){
      maxCalorieCount = (i > maxCalorieCount) ? i : maxCalorieCount;
    }
    return maxCalorieCount;
  }

  public static int getPart02(String filepath){
    ArrayList<Integer> elfCalorieCounts = generateElfCalorieCountsList(readInput(filepath));

    int[] top3 = {0,0,0};

    for(Integer i : elfCalorieCounts){
      top3[2] = (i > top3[2]) ? i : top3[2];
      if(top3[2] > top3[1]){
        int x = top3[1];
        top3[1] = top3[2];
        top3[2] = x;
      }
      if(top3[1] > top3[0]){
        int x = top3[0];
        top3[0] = top3[1];
        top3[1] = x;
      }
    }
    return top3[0]+top3[1]+top3[2];
  }
}
