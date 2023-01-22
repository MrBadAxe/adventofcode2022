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

  public static int lcm(int a, int b){
    return a*b / gcd(a,b);
  }
  public static int gcd(int a, int b){
    return b==0 ? a : gcd(b, a%b);
  }

  public static int getPart01(List<String> input){
    int maxSteps = lcm((input.size()-2),(input.get(0).length()-2));
    Day24BlizzardField[] fields = new Day24BlizzardField[maxSteps];
    fields[0] = parseBlizzardField(input);
    System.out.println(fields[0].toString());
    for(int k=1;k<maxSteps;k++){
      fields[k] = fields[k-1].step();
      System.out.println(fields[k].toString());
    }
    return 0;
  }
}
