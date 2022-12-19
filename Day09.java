//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;

public class Day09{

  public static void moveRope(Day09Rope rope, List<String> input){
    for(String str : input){
      char direction = str.charAt(0);
      int distance = Integer.parseInt(str.substring(2));
      for(int k=0;k<distance;k++){
        rope.move(direction);
      }
    }
  }

  public static int getPart01(List<String> input){
    Day09Rope rope = new Day09Rope(2);
    moveRope(rope, input);
    return rope.getSeenCount();
  }
  public static int getPart02(List<String> input){
    Day09Rope rope = new Day09Rope(10);
    moveRope(rope, input);
    return rope.getSeenCount();
  }

}
