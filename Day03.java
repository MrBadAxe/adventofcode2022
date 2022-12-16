//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day03{
  private static ArrayList<String> rucksacks;

  public static void readInput(String filepath){
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      rucksacks = new ArrayList<String>();
      String nextLine = null;
      while((nextLine=console.readLine())!=null){
        rucksacks.add(nextLine);
      }
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
  }

  private static ArrayList<String> findCommonLetters(String a, String b){
    ArrayList<String> z = new ArrayList<String>();
    for(String s : a.split("")){
      if(b.contains(s) && !z.contains(s)){
        z.add(s);
      }
    }
    return z;
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    int priorityTotal = 0;
    for(String rucksack : rucksacks){
      ArrayList<String> duplicatedItems = findCommonLetters(rucksack.substring(0,(rucksack.length()/2)),rucksack.substring(rucksack.length()/2));
      for(String duplicatedItem : duplicatedItems){
        priorityTotal += " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(duplicatedItem);
      }
    }
    return priorityTotal;
  }

  public static int getPart02(String filepath){
    readInput(filepath);
    int priorityTotal = 0;

    for(int k=0;k<rucksacks.size();k+=3){
      ArrayList<String> duplicatedItems = findCommonLetters(String.join("",findCommonLetters(rucksacks.get(k), rucksacks.get(k+1))),rucksacks.get(k+2));
      for(String duplicatedItem : duplicatedItems){
        priorityTotal += " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(duplicatedItem);
      }
    }

    return priorityTotal;
  }

}
