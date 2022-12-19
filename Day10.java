//package org.mrbadaxe.AdventOfCode2022;

import java.util.LinkedList;
import java.util.List;

public class Day10{
  private static LinkedList<String> lines;

  public static int getPart01(List<String> input){
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
    int instructionPointer = 0;

    int currentInstrClocks = 0;
    String instruction = "";
    String opcode = "";
    int instrParam = 0;
    while(instructionPointer < input.size()){
      if(currentInstrClocks <= 0){
        registerX += instrParam;
        instruction = input.get(instructionPointer);
        instructionPointer++;
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

  public static String getPart02(List<String> input){
    LinkedList<Integer> readClocks = new LinkedList<Integer>();
    int registerX = 1;
    int clock = 1;
    int instructionPointer = 0;
    String raster = "";

    int currentInstrClocks = 0;
    String instruction = "";
    String opcode = "";
    int instrParam = 0;
    while(instructionPointer < input.size()){
      if(currentInstrClocks <= 0){
        registerX += instrParam;
        instruction = input.get(instructionPointer);
        instructionPointer++;
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
