//package org.mrbadaxe.AdventOfCode2022;

import java.util.ArrayList;
import java.util.List;

public class Day06{

  public static boolean containsNoDuplicateLetters(String s){
    if(s.length() == 1){
      return true;
    }else{
      if(s.substring(1).contains(s.substring(0,1))){
        return false;
      }else{
        return containsNoDuplicateLetters(s.substring(1));
      }
    }
  }

  public static int findMarker(String signal, int markerLength){
    int markerPos = 0;
    boolean markerFound = false;
    while(!markerFound){
      String potentialMarker = signal.substring(markerPos,markerPos+markerLength);
      markerFound = containsNoDuplicateLetters(potentialMarker);
      markerPos++;
    }
    return markerPos + markerLength - 1;
  }

  public static int getPart01(List<String> input){
    return findMarker(input.get(0),4);
  }

  public static int getPart02(List<String> input){
    return findMarker(input.get(0),14);
  }

}
