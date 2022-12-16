//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day11{
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

  public static int parseLineIndex(String line){
    return Integer.parseInt(line.substring(7,line.length()-1));
  }

  public static int[] parseLineOperation(String line){
    String[] tokens = line.split("\s");
    int[] z = {0,0,0};
    if(tokens[tokens.length-2].equals("+")){
      z[1] = 1;
      z[2] = Integer.parseInt(tokens[tokens.length-1]);
    }else{
      if(tokens[tokens.length-3].equals("old") && tokens[tokens.length-1].equals("old")){
        z[0] = 1;
      }else{
        z[1] = Integer.parseInt(tokens[tokens.length-1]);
      }
    }
    return z;
  }

  public static int parseLineTest(String line){
    return Integer.parseInt(line.split("\s")[5]);
  }

  public static int parseLinePass(String line){
    return Integer.parseInt(line.split("\s")[9]);
  }

  public static int parseLineFail(String line){
    return Integer.parseInt(line.split("\s")[9]);
  }

  public static int[] parseLineStartItems(String line){
    String[] tokens = line.strip().substring(16).split(",\s");
    int[] z = new int[tokens.length];
    for(int k=0;k<tokens.length;k++){
      z[k] = Integer.parseInt(tokens[k]);
    }
    return z;
  }

  public static Day11Monkey[] gatherMonkeys(boolean worry){
    Day11Monkey[] z = new Day11Monkey[8];
    int nextMonkeyDesc = 0;
    int monkeyDescLength = 6;
    while(nextMonkeyDesc + monkeyDescLength <= lines.size()){
      int index = parseLineIndex(lines.get(nextMonkeyDesc));

      int[] polynomial = parseLineOperation(lines.get(nextMonkeyDesc + 2));
      int ax2 = polynomial[0];
      int bx = polynomial[1];
      int c = polynomial[2];

      int test = parseLineTest(lines.get(nextMonkeyDesc + 3));
      int pass = parseLinePass(lines.get(nextMonkeyDesc + 4));
      int fail = parseLineFail(lines.get(nextMonkeyDesc + 5));

      z[index] = new Day11Monkey(ax2,bx,c,test,pass,fail,worry);
      int[] startItems = parseLineStartItems(lines.get(nextMonkeyDesc + 1));
      for(int k=0;k<startItems.length;k++){
        z[index].give(startItems[k]);
      }

      nextMonkeyDesc+=(monkeyDescLength+1);
    }
    return z;

  }

  public static long getPart01(String filepath){
    readInput(filepath);

    Day11Monkey[] monkeys = gatherMonkeys(false);
    for(int passes=0;passes<20;passes++){
      for(int k=0;k<monkeys.length;k++){
        if(monkeys[k] != null){
          monkeys[k].inspectAll(monkeys);
        }
      }
    }

    ArrayList<Long> inspectCounts = new ArrayList<Long>();
    for(int k=0;k<monkeys.length;k++){
      if(monkeys[k] != null){
        inspectCounts.add(monkeys[k].getInspectCount());
      }
    }
    inspectCounts.sort(null);
    return inspectCounts.get(inspectCounts.size()-1) * inspectCounts.get(inspectCounts.size()-2);
  }

  public static long getPart02(String filepath){
    readInput(filepath);

    Day11Monkey[] monkeys = gatherMonkeys(true);
    for(int passes=0;passes<10000;passes++){
      for(int k=0;k<monkeys.length;k++){
        if(monkeys[k] != null){
          monkeys[k].inspectAll(monkeys);
        }
      }
      if(false){
        for(int k=0;k<monkeys.length;k++){
          if(monkeys[k] != null){
            System.out.print(monkeys[k].describeInventory());
            System.out.println(" (" + monkeys[k].getInspectCount() + ")");
          }
        }
        System.out.println("");
      }
    }

    ArrayList<Long> inspectCounts = new ArrayList<Long>();
    for(int k=0;k<monkeys.length;k++){
      if(monkeys[k] != null){
        inspectCounts.add(monkeys[k].getInspectCount());
      }
    }
    inspectCounts.sort(null);
    return inspectCounts.get(inspectCounts.size()-1) * inspectCounts.get(inspectCounts.size()-2);
  }

}
