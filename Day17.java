//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;
import java.util.Stack;

public class Day17{

  public static int getPart01(List<String> input){
    Day17Well well = new Day17Well(input.get(0));
    well.addPiece();
    System.out.println(well);

    return 0;
  }

}
