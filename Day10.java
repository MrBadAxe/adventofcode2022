//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Day10{
  private static LinkedList<String> lines;

  public static void readInput(String filepath){
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      lines = new LinkedList<String>();
      String nextLine = null;
      while((nextLine=console.readLine())!=null){
        lines.add(nextLine);
      }
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    int runningReadingTotal = 0;

    LinkedList<Integer> readClocks = new LinkedList<Integer>();
    readClocks.add(20);
    readClocks.add(60);
    readClocks.add(100);
    readClocks.add(140);
    readClocks.add(180);
    readClocks.add(220);

    int registerX = 1;
    int clock = 1;

    int currentInstrClocks = 0;
    String instruction = "";
    String opcode = "";
    int instrParam = 0;
    while(lines.size() > 0){
      if(currentInstrClocks <= 0){
        registerX += instrParam;
        instruction = lines.removeFirst();
        opcode = instruction.substring(0,4);
        if(opcode.equals("noop")){
          currentInstrClocks = 1;
          instrParam = 0;
        }else if(opcode.equals("addx")){
          currentInstrClocks = 2;
          instrParam = Integer.parseInt(instruction.substring(5));
        }
      }
      //System.out.println(clock + ":" + registerX + ":" + runningReadingTotal + " -- " + instruction + "(" + currentInstrClocks + ")");
      if(readClocks.size() > 0 && clock == readClocks.getFirst()){
        runningReadingTotal += (clock * registerX);
        readClocks.removeFirst();
      }
      currentInstrClocks--;
      clock++;
    }
    return runningReadingTotal;
  }

  public static String getPart02(String filepath){
    readInput(filepath);

    LinkedList<Integer> readClocks = new LinkedList<Integer>();
    int registerX = 1;
    int clock = 1;
    String raster = "";

    int currentInstrClocks = 0;
    String instruction = "";
    String opcode = "";
    int instrParam = 0;
    while(lines.size() > 0){
      if(currentInstrClocks <= 0){
        registerX += instrParam;
        instruction = lines.removeFirst();
        opcode = instruction.substring(0,4);
        if(opcode.equals("noop")){
          currentInstrClocks = 1;
          instrParam = 0;
        }else if(opcode.equals("addx")){
          currentInstrClocks = 2;
          instrParam = Integer.parseInt(instruction.substring(5));
        }
      }
      //System.out.println(clock + ":" + registerX + ":" + " -- " + instruction + "(" + currentInstrClocks + ")");
      raster = raster + ((clock-1)%40 == registerX || (clock-1)%40 == registerX-1 || (clock-1)%40 == registerX+1 ? "#" : ".");
      currentInstrClocks--;
      clock++;
    }
    String z = "\n";
    for(int k=0;k<raster.length();k+=40){
      z += raster.substring(k,k+40) + "\n";
    }
    return z;
  }

}
