//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day02{
  private static ArrayList<String> rounds;

  public static void readInput(String filepath){
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      rounds = new ArrayList<String>();
      String nextLine = null;
      while((nextLine=console.readLine())!=null){
        rounds.add(nextLine);
      }
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    int totalScore = 0;
    for(String round : rounds){
      String[] moves = round.split(" ");
      //System.out.print(moves[0] + ":" + moves[1] + ":");
      int roundScore = 0;
      if(moves[1].equals("X")){  //you pick rock
        roundScore += 1;
        if(moves[0].equals("C")){ roundScore += 6; }else if(moves[0].equals("A")){ roundScore += 3; }
      }
      if(moves[1].equals("Y")){  //you pick paper
        roundScore += 2;
        if(moves[0].equals("A")){ roundScore += 6; }else if(moves[0].equals("B")){ roundScore += 3; }
      }
      if(moves[1].equals("Z")){  //you pick scissors
        roundScore += 3;
        if(moves[0].equals("B")){ roundScore += 6; }else if(moves[0].equals("C")){ roundScore += 3; }
      }
      //System.out.println(roundScore);
      totalScore += roundScore;
    }
    return totalScore;
  }

  public static int getPart02(String filepath){
    readInput(filepath);
    int totalScore = 0;
    for(String round : rounds){
      String[] moves = round.split(" ");
      //System.out.print(moves[0] + ":" + moves[1] + ":");
      int roundScore = 0;
      if(moves[1].equals("X")){  //you must lose
        roundScore += 0;
        if(moves[0].equals("A")){ roundScore += 3; }
        else if(moves[0].equals("B")){ roundScore += 1; }
        else if(moves[0].equals("C")){ roundScore += 2; }
      }
      if(moves[1].equals("Y")){  //you must draw
        roundScore += 3;
        if(moves[0].equals("A")){ roundScore += 1; }
        else if(moves[0].equals("B")){ roundScore += 2; }
        else if(moves[0].equals("C")){ roundScore += 3; }
      }
      if(moves[1].equals("Z")){  //you must win
        roundScore += 6;
        if(moves[0].equals("A")){ roundScore += 2; }
        else if(moves[0].equals("B")){ roundScore += 3; }
        else if(moves[0].equals("C")){ roundScore += 1; }
      }
      //System.out.println(roundScore);
      totalScore += roundScore;
    }
    return totalScore;
  }

}
