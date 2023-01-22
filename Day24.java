//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;
import java.util.ArrayList;

public class Day24{
  public static Day24BlizzardField parseBlizzardField(List<String> input){
    Day24BlizzardField field = new Day24BlizzardField(input.size(),input.get(0).length());
    for(int row=0;row<input.size();row++){
      String line = input.get(row);
      for(int col=0;col<line.length();col++){
        char s = line.charAt(col);
        switch(s){
          case '.': field.set(row,col,0); break;
          case '>': field.set(row,col,1 << Day24BlizzardField.FACING_RIGHT); break;
          case 'v': field.set(row,col,1 << Day24BlizzardField.FACING_DOWN); break;
          case '<': field.set(row,col,1 << Day24BlizzardField.FACING_LEFT); break;
          case '^': field.set(row,col,1 << Day24BlizzardField.FACING_UP); break;
        }
      }
    }
    return field;
  }

  public static int getPart01(List<String> input){
    Day24BlizzardField initState = parseBlizzardField(input);
    int[][] distances = Day24PathFinder.solve(initState,new Point(0,1),new Point(initState.rows()-1,initState.cols()-2));
    return 0;
  }
}
