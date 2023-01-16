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

  public static String elfListToString(List<Day23Elf> elves){
    int rowMin = 0;
    int rowMax = 0;
    int colMin = 0;
    int colMax = 0;
    for(Day23Elf elf : elves){
      rowMin = Math.min(rowMin, elf.currentPos().getX());
      rowMax = Math.max(rowMax, elf.currentPos().getX());
      colMin = Math.min(colMin, elf.currentPos().getY());
      colMax = Math.max(colMax, elf.currentPos().getY());
    }
    String z = "";
    for(int row=rowMin;row<=rowMax;row++){
      for(int col=colMin;col<=colMax;col++){
        z += (elves.contains(new Day23Elf(new Point(row,col))) ? "#" : ".");
      }
      z += "|";
    }
    return z;
  }

  public static int elfListFootprint(List<Day23Elf> elves){
    int rowMin = 0;
    int rowMax = 0;
    int colMin = 0;
    int colMax = 0;
    for(Day23Elf elf : elves){
      rowMin = Math.min(rowMin, elf.currentPos().getX());
      rowMax = Math.max(rowMax, elf.currentPos().getX());
      colMin = Math.min(colMin, elf.currentPos().getY());
      colMax = Math.max(colMax, elf.currentPos().getY());
    }
    return ((rowMax - rowMin) + 1) * ((colMax - colMin) + 1);
  }

  public static List<Day23Elf> nextRound(List<Day23Elf> elves){
    for(Day23Elf elf : elves){
      elf.propose(elves);
    }
    for(Day23Elf elf : elves){
      if(elf.getProposedMove() != null && elf.getProposedMove() != elf.currentPos()){
        List<Day23Elf> conflicts = new ArrayList<Day23Elf>();
        conflicts.add(elf);
        for(Point p : getOtherEntryPoints(elf)){
          Day23Elf conflict = elves.indexOf(new Day23Elf(p)) != -1 ? elves.get(elves.indexOf(new Day23Elf(p))) : null;
          if(conflict != null){
            if(conflict.getProposedMove() != null && conflict.getProposedMove().equals(elf.getProposedMove())){
              conflicts.add(conflict);
            }
          }
        }
        if(conflicts.size() > 1){
          for(Day23Elf conf : conflicts){
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

    for(int k=0;k<10;k++){
      elves = nextRound(elves);
    }
    return elfListFootprint(elves) - elves.size();
  }

  public static int getPart02(List<String> input){
    List<Day23Elf> elves = parseElvesPositions(input);
    List<Day23Elf> elvesNext = null;
    int rounds = 0;
    int moved = Integer.MIN_VALUE;
    String oldList = elves.toString();

    while(moved != 0){
      elvesNext = nextRound(elves);
      rounds++;
      String newList = elvesNext.toString();
      moved = oldList.compareTo(newList);
      elves = elvesNext;
      oldList = newList;
    }
    return rounds;
  }

}
