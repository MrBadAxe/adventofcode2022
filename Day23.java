//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;
import java.util.ArrayList;

public class Day23{

  public static List<Day23Elf> parseElvesPositions(List<String> input){
    List<Day23Elf> elves = new ArrayList<Day23Elf>();

    int height = input.size();
    int width = input.get(0).length();
    for(int row=0;row<height;row++){
      for(int col=0;col<width;col++){
        if(input.get(row).charAt(col) == '#'){
          Day23Elf elf = new Day23Elf(new Point(row,col));
          //System.out.println(elf.toString());
          elves.add(elf);
        }
      }
    }
    return elves;
  }

  public static List<Point> getOtherEntryPoints(Day23Elf e){
    Point proposed = e.getProposedMove();
    List<Point> z = new ArrayList<Point>();
    z.add(new Point(proposed.getX()-1,proposed.getY()));
    z.add(new Point(proposed.getX()+1,proposed.getY()));
    z.add(new Point(proposed.getX(),proposed.getY()-1));
    z.add(new Point(proposed.getX(),proposed.getY()+1));
    z.remove(e.currentPos());
    return z;
  }

  public static int getPart01(List<String> input){
    List<Day23Elf> elves = parseElvesPositions(input);
    return 0;
  }
}
