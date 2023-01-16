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

  public static List<Day23Elf> nextRound(List<Day23Elf> elves){
    for(Day23Elf elf : elves){
      elf.propose(elves);
      System.out.println(elf.toString());
    }
    for(Day23Elf elf : elves){
        System.out.println("\t" + elf.toString() + "->" + getOtherEntryPoints(elf).toString());
      if(elf.getProposedMove() != null && elf.getProposedMove() != elf.currentPos()){
        List<Day23Elf> conflicts = new ArrayList<Day23Elf>();
        conflicts.add(elf);
        for(Point p : getOtherEntryPoints(elf)){
          System.out.println(p.toString());
          Day23Elf conflict = elves.indexOf(new Day23Elf(p)) != -1 ? elves.get(elves.indexOf(new Day23Elf(p))) : null;
          if(conflict != null){
            System.out.println("\t\t" + conflict.toString());
            if(conflict.getProposedMove() != null && conflict.getProposedMove().equals(elf.getProposedMove())){
              conflicts.add(conflict);
            }
          }
        }
        if(conflicts.size() > 1){
          System.out.println("conflict!");
          for(Day23Elf conf : conflicts){
            System.out.println(conf.toString());
            conf.reject();
          }
        }
      }
    }
    for(Day23Elf elf : elves){
      if(elf.getProposedMove() != null){
        elf.accept();
      }
    }
    return elves;
  }

  public static int getPart01(List<String> input){
    List<Day23Elf> elves = parseElvesPositions(input);

    System.out.println(elves.toString());
    for(int k=0;k<10;k++){
      elves = nextRound(elves);
      System.out.println(elves.toString());
    }
    return elfListFootprint(elves) - elves.size();
  }
    }
    return 0;
  }
}
