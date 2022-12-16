//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day01{
  private static ArrayList<Integer> elfCalorieCounts;

  public static void readInput(String filepath){
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      elfCalorieCounts = new ArrayList<Integer>();
      String nextLine = null;
      int nextCalorieCount = 0;
      while((nextLine=console.readLine())!=null){
        if(nextLine.equals("")){
          elfCalorieCounts.add(nextCalorieCount);
          nextCalorieCount = 0;
        }else{
          nextCalorieCount += Integer.parseInt(nextLine);
        }
      }
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    int maxCalorieCount = 0;
    for(Integer i : elfCalorieCounts){
      maxCalorieCount = (i > maxCalorieCount) ? i : maxCalorieCount;
    }
    return maxCalorieCount;
  }

  public static int getPart02(String filepath){
    readInput(filepath);

    int[] top3 = {0,0,0};

    for(Integer i : elfCalorieCounts){
      //System.out.println(i);
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
    //System.out.println(top3[0]+" "+top3[1]+" "+top3[2]);
    return top3[0]+top3[1]+top3[2];
  }
}
