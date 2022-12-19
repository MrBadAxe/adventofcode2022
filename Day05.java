//package org.mrbadaxe.AdventOfCode2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day05{
  private static final int CRATE_SPACING = 4;

  public static ArrayList<Stack<String>> initializeCrates(List<String> input){
    int separator = input.indexOf("");
    ArrayList<String> pictureStacks = new ArrayList<String>();
    for(int k=0;k<separator;k++){
      pictureStacks.add(0,input.get(k));
    }
    pictureStacks.remove(0);

    ArrayList<Stack<String>> crateStacks = new ArrayList<Stack<String>>();
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
    return crateStacks;
  }
  public static ArrayList<String> initializeMoveList(List<String> input){
    int separator = input.indexOf("");
    ArrayList<String> moveList = new ArrayList<String>();
    for(int k=separator+1;k<input.size();k++){
      moveList.add(input.get(k));
    }
    return moveList;
  }

  public static String getStackString(ArrayList<Stack<String>> crateStacks){
    String z = "";
    for(Stack<String> stack : crateStacks){
      for(String str : stack){
        z += str;
      }
      z += "\n";
    }
    return z;
  }

  public static String getPart01(List<String> input){
    ArrayList<Stack<String>> crateStacks = initializeCrates(input);
    ArrayList<String> crateMoves = initializeMoveList(input);

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

  public static String getPart02(List<String> input){
    ArrayList<Stack<String>> crateStacks = initializeCrates(input);
    ArrayList<String> crateMoves = initializeMoveList(input);

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
