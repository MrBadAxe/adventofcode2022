//package org.mrbadaxe.AdventOfCode2022;

import java.util.ArrayList;
import java.util.List;

public class Day02{

  public static int getPart01(List<String> input){
    List<String> rounds = input;
    int totalScore = 0;
    for(String round : rounds){
      String[] moves = round.split(" ");
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
      totalScore += roundScore;
    }
    return totalScore;
  }

  public static int getPart02(List<String> input){
    List<String> rounds = input;
    int totalScore = 0;
    for(String round : rounds){
      String[] moves = round.split(" ");
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
      totalScore += roundScore;
    }
    return totalScore;
  }

}
