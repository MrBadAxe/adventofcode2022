//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;
import java.util.HashMap;

public class Day21{
  public static long getPart01(List<String> input){
    HashMap<String,Day21Monkey> monkeys = new HashMap<String,Day21Monkey>();

    for(String line : input){
      String[] tokens = line.split("\s");
      if(tokens.length == 4){
        monkeys.put(tokens[0].substring(0,4), new Day21ExprMonkey(tokens[1],tokens[2].charAt(0),tokens[3]));
      }else if(tokens.length == 2){
        monkeys.put(tokens[0].substring(0,4), new Day21ConstMonkey(Integer.parseInt(tokens[1])));
      }
    }

    return monkeys.get("root").yell(monkeys);
  }

  public static long getPart02(List<String> input){
    HashMap<String,Day21Monkey> monkeys = new HashMap<String,Day21Monkey>();

    for(String line : input){
      String[] tokens = line.split("\s");
      String label = tokens[0].substring(0,4);
      if(label.equals("root")){
        monkeys.put(label, new Day21ExprMonkey(tokens[1],'=',tokens[3]));
      }else if(tokens.length == 4){
        monkeys.put(label, new Day21ExprMonkey(tokens[1],tokens[2].charAt(0),tokens[3]));
      }else if(tokens.length == 2){
        monkeys.put(label, new Day21ConstMonkey(Integer.parseInt(tokens[1])));
      }
    }

    long total = 0L;
    long k = 1L;
    long diff = monkeys.get("root").yell(monkeys);
    while(diff != 0){
      if(diff > 0){
        k*=2;
      }else{
        total += (k/2);
        k = 1L;
      }
      monkeys.put("humn",new Day21ConstMonkey(total+k));
      diff = monkeys.get("root").yell(monkeys);
    }
    return (total+k);
  }

}
