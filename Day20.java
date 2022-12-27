//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;
import java.util.ArrayList;

public class Day20{

  public static ArrayList<LongPoint> rotateToIndex(ArrayList<LongPoint> input, int target){
    while(input.get(0).getX() != target){
      input = rotateLeft(input);
    }
    return input;
  }

  public static ArrayList<LongPoint> rotateLeft(ArrayList<LongPoint> input){
    input.add(input.remove(0));
    return input;
  }

  public static ArrayList<LongPoint> rotateRight(ArrayList<LongPoint> input){
    input.add(0,input.remove(input.size() - 1));
    return input;
  }

  public static ArrayList<LongPoint> mix(ArrayList<LongPoint> input){
    for(int k=0;k<input.size();k++){
      if(k%100==0){System.out.print(k+" ");}
      input = rotateToIndex(input,k);
      //System.out.println(input.toString());
      LongPoint p = input.remove(0);
      //System.out.println(input.toString());
      for(long j = 0; j < Math.abs(p.getY())%input.size(); j++){
        input = (p.getY() > 0 ? rotateLeft(input) : rotateRight(input));
        //System.out.println(input.toString());
      }
      input.add(0,p);
      //System.out.println(input.toString());
      //System.out.println("");
    }
    return input;
  }

  public static long getPart01(List<String> input){
    ArrayList<LongPoint> ciphertext = new ArrayList<LongPoint>();
    for(int k=0;k<input.size();k++){
      ciphertext.add(new LongPoint((long)k,Long.parseLong(input.get(k))));
    }
    ciphertext = mix(ciphertext);
    while(ciphertext.get(0).getY() != 0){
      ciphertext = rotateLeft(ciphertext);
    }
    //System.out.println(ciphertext.toString());

    long n1 = ciphertext.get(1000%ciphertext.size()).getY();
    long n2 = ciphertext.get(2000%ciphertext.size()).getY();
    long n3 = ciphertext.get(3000%ciphertext.size()).getY();
    System.out.println(n1 + " " + n2 + " " + n3);
    return n1+n2+n3;
  }

  public static long getPart02(List<String> input){
    long ENCRYPTION_KEY = 811_589_153L;
    ArrayList<LongPoint> ciphertext = new ArrayList<LongPoint>();
    for(int k=0;k<input.size();k++){
      ciphertext.add(new LongPoint((long)k, Long.parseLong(input.get(k)) * ENCRYPTION_KEY));
    }

    for(int mixCycles = 0; mixCycles < 10; mixCycles++){
      System.out.println("=== PASS " + (mixCycles+1) + " ===");
      ciphertext = mix(ciphertext);
    }

    while(ciphertext.get(0).getY() != 0){
      ciphertext = rotateLeft(ciphertext);
    }
    //System.out.println(ciphertext.toString());

    long n1 = ciphertext.get(1000%ciphertext.size()).getY();
    long n2 = ciphertext.get(2000%ciphertext.size()).getY();
    long n3 = ciphertext.get(3000%ciphertext.size()).getY();
    System.out.println(n1 + " " + n2 + " " + n3);
    return n1+n2+n3;
  }

}
