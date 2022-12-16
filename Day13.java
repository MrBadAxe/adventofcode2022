//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day13{
  private static ArrayList<String> lines;

  public static void readInput(String filepath){
    try{
      BufferedReader console = new BufferedReader(new FileReader(filepath));
      lines = new ArrayList<String>();
      String nextLine = null;
      while((nextLine=console.readLine())!=null){
        lines.add(nextLine);
      }
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
  }

  public static boolean isList(String packet){
    return (packet.length() > 0 && packet.charAt(0) == '[' && packet.charAt(packet.length()-1) == ']');
  }

  public static boolean bracketsBalanced(String str){
    int lefts = 0;
    int rights = 0;
    for(int k=0;k<str.length();k++){
      if(str.charAt(k) == '['){
        lefts++;
      }else if(str.charAt(k) == ']'){
        rights++;
      }
    }
    return (lefts == rights);
  }

  public static String[] splitToList(String packet){
    String[] commaSeparated = (packet.substring(1,packet.length()-1)).split(",");
    ArrayList<String> rejoined = new ArrayList<String>();
    for(int k=0;k<commaSeparated.length;k++){
      String candidate = commaSeparated[k];
      int run = 0;
      while(!bracketsBalanced(candidate)){
        run++;
        candidate = candidate + "," + commaSeparated[k+run];
      }
      rejoined.add(candidate);
      k+=run;
    }
    return rejoined.toArray(new String[rejoined.size()]);
  }

  public static int packetsOrdered(String left, String right){
    if(!isList(left) && !isList(right)){
      if(left.equals("")){
        return (right.equals("") ? 0 : 1);
      }else{
        return (right.equals("") ? -1 : Integer.signum(Integer.parseInt(right) - Integer.parseInt(left)));
      }
    }else if(isList(left) && isList(right)){
      String[] leftList = splitToList(left);
      String[] rightList = splitToList(right);
      int index=0;
      int itemResult = 0;
      while(itemResult == 0 && index < Math.min(leftList.length,rightList.length)){
        itemResult = packetsOrdered(leftList[index],rightList[index]);
        index++;
      }
      if(itemResult != 0){ return itemResult; }
      return Integer.signum(rightList.length - leftList.length);
    }else if(isList(left) && !isList(right)){
      return right.equals("") ? -1 : packetsOrdered(left, "[" + right + "]");
    }else if(!isList(left) && isList(right)){
      return left.equals("") ? 1 : packetsOrdered("[" + left + "]", right);
    }
    return 0;
  }

  public static int getPart01(String filepath){
    readInput(filepath);
    int correctPairs = 0;
    int pairsIndex = 0;
    while(lines.size() > 0){
      pairsIndex++;
      String left = lines.remove(0);
      String right = lines.remove(0);
      int isOrdered = packetsOrdered(left,right);
      if(isOrdered >= 0){
        correctPairs+=pairsIndex;
      }
      String blank = (lines.size() > 0 ? lines.remove(0) : "");
    }
    return correctPairs;
  }

  public static int getPart02(String filepath){
    readInput(filepath);
    lines.add("[[2]]");
    lines.add("[[6]]");
    ArrayList<String> sortedPackets = new ArrayList<String>();
    for(String str : lines){
      if(!str.equals("")){
        int insertIndex = 0;
        while(insertIndex < sortedPackets.size()){
          int compare = packetsOrdered(sortedPackets.get(insertIndex),str);
          if(compare > -1){
            insertIndex++;
          }else{
            sortedPackets.add(insertIndex,str);
            insertIndex = Integer.MAX_VALUE;
          }
        }
        if(insertIndex != Integer.MAX_VALUE){
          sortedPackets.add(str);
        }
      }
    }
    return ((sortedPackets.indexOf("[[2]]")+1) * (sortedPackets.indexOf("[[6]]")+1));
  }

}
