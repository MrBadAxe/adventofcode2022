//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

public class Day05{
  private static ArrayList<Stack<String>> crateStacks;
  private static ArrayList<String> crateMoves;
  private static final int CRATE_SPACING = 4;

  public static void readInput(String filepath){
    crateMoves = new ArrayList<String>();
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      String nextLine = null;
      while((nextLine=console.readLine())!=null){
        crateMoves.add(nextLine);
      }
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }

    int separator = crateMoves.indexOf("");
    //System.out.println(separator);
    ArrayList<String> pictureStacks = new ArrayList<String>();
    while(separator > 0){
      pictureStacks.add(0,crateMoves.get(0));
      crateMoves.remove(0);
      separator--;
    }
    crateMoves.remove(0);
    pictureStacks.remove(0);

    crateStacks = new ArrayList<Stack<String>>();
    int stackCount = (pictureStacks.get(0).length()+1)/CRATE_SPACING;

    for(int k=0;k<stackCount;k++){
      Stack<String> stack = new Stack<String>();
      for(int j=0;j<pictureStacks.size();j++){
        int crateOffset = (CRATE_SPACING*k)+1;
        if(pictureStacks.get(j).length() >= crateOffset){
          String crate = pictureStacks.get(j).substring(crateOffset,crateOffset+1);
          if(!crate.equals(" ")){ stack.push(crate); }
        }
      }
      crateStacks.add(stack);
    }
  }

  public static String getStackString(){
    String z = "";
    for(Stack<String> stack : crateStacks){
      for(String str : stack){
        z += str;
      }
      z += "\n";
    }
    return z;
  }

  public static String getPart01(String filepath){
    readInput(filepath);

    for(String crateMove : crateMoves){
      String[] split = crateMove.split(" ");
      int moveFrom = Integer.parseInt(split[3]);
      int moveTo = Integer.parseInt(split[5]);
      int moveCount = Integer.parseInt(split[1]);
      for(int k=0;k<moveCount;k++){
        crateStacks.get(moveTo-1).push(crateStacks.get(moveFrom-1).pop());
      }
    }

    String z = "";
    for(Stack<String> stack : crateStacks){
      z += stack.peek();
    }
    return z;
  }

  public static String getPart02(String filepath){
    readInput(filepath);

    for(String crateMove : crateMoves){
      String[] split = crateMove.split(" ");
      int moveFrom = Integer.parseInt(split[3]);
      int moveTo = Integer.parseInt(split[5]);
      int moveCount = Integer.parseInt(split[1]);

      Stack<String> crane = new Stack<String>();
      for(int k=0;k<moveCount;k++){
        crane.push(crateStacks.get(moveFrom-1).pop());
      }
      for(int k=0;k<moveCount;k++){
        crateStacks.get(moveTo-1).push(crane.pop());
      }
    }

    String z = "";
    for(Stack<String> stack : crateStacks){
      z += stack.peek();
    }
    return z;
  }

}
