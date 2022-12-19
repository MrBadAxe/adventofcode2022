import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2022{
  public static List<String> readInput(String filepath){
    List<String> lines = new ArrayList<String>();
    try{
      lines = Files.readAllLines(Path.of(filepath));
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
    return lines;
  }

  public static void main(String[] args){
    /*
    System.out.println("Day 01 Part 01: " + Day01.getPart01(readInput("./input_day01.txt")));
    System.out.println("Day 01 Part 02: " + Day01.getPart02(readInput("./input_day01.txt")));

    System.out.println("Day 02 Part 01: " + Day02.getPart01(readInput("./input_day02.txt")));
    System.out.println("Day 02 Part 02: " + Day02.getPart02(readInput("./input_day02.txt")));

    System.out.println("Day 03 Part 01: " + Day03.getPart01(readInput("./input_day03.txt")));
    System.out.println("Day 03 Part 02: " + Day03.getPart02(readInput("./input_day03.txt")));

    System.out.println("Day 04 Part 01: " + Day04.getPart01(readInput("./input_day04.txt")));
    System.out.println("Day 04 Part 02: " + Day04.getPart02(readInput("./input_day04.txt")));

    System.out.println("Day 05 Part 01: " + Day05.getPart01(readInput("./input_day05.txt")));
    System.out.println("Day 05 Part 02: " + Day05.getPart02(readInput("./input_day05.txt")));

    System.out.println("Day 06 Part 01: " + Day06.getPart01(readInput("./input_day06.txt")));
    System.out.println("Day 06 Part 02: " + Day06.getPart02(readInput("./input_day06.txt")));

    System.out.println("Day 07 Part 01: " + Day07.getPart01(readInput("./input_day07.txt")));
    System.out.println("Day 07 Part 02: " + Day07.getPart02(readInput("./input_day07.txt")));

    System.out.println("Day 08 Part 01: " + Day08.getPart01(readInput("./input_day08.txt")));
    System.out.println("Day 08 Part 02: " + Day08.getPart02(readInput("./input_day08.txt")));

    System.out.println("Day 09 Part 01: " + Day09.getPart01(readInput("./input_day09.txt")));
    System.out.println("Day 09 Part 02: " + Day09.getPart02(readInput("./input_day09.txt")));

    System.out.println("Day 10 Part 02: " + Day10.getPart01(readInput("./input_day10.txt")));
    System.out.println("Day 10 Part 01: " + Day10.getPart02(readInput("./input_day10.txt")));

    System.out.println("Day 11 Part 01: " + Day11.getPart01(readInput("./input_day11.txt")));
    System.out.println("Day 11 Part 02: " + Day11.getPart02(readInput("./input_day11.txt")));

    System.out.println("Day 12 Part 01: " + Day12.getPart01(readInput("./input_day12.txt")));
    System.out.println("Day 12 Part 02: " + Day12.getPart02(readInput("./input_day12.txt")));

    System.out.println("Day 13 Part 01: " + Day13.getPart01(readInput("./input_day13.txt")));
    System.out.println("Day 13 Part 02: " + Day13.getPart02(readInput("./input_day13.txt")));

    System.out.println("Day 14 Part 01: " + Day14.getPart01(readInput("./input_day14.txt")));
    System.out.println("Day 14 Part 02: " + Day14.getPart02(readInput("./input_day14.txt")));

    System.out.println("Day 15 Part 01: " + Day15.getPart01(readInput("./input_day15.txt"),2000000));
    System.out.println("Day 15 Part 02: " + Day15.getPart02(readInput("./input_day15.txt"),4000000));
    */
    System.out.println("Day 16 Part 01: " + Day16.getPart01(readInput("./input_day16_short.txt")));
    //System.out.println("Day 16 Part 02: " + Day14.getPart02(readInput("./input_day16.txt")));
  }
}
