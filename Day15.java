//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day15{
  private static ArrayList<String> lines;
  private static HashMap<Point,Point> sensorMap;

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

  public static void buildSensorMap(){
    sensorMap = new HashMap<Point,Point>();
    for(String str : lines){
      String[] tokens = str.split(":");
      String sensor = tokens[0];
      String beacon = tokens[1];
      String[] sensorTokens = sensor.split("\s");
      int sensorX = Integer.parseInt(sensorTokens[2].substring(2,sensorTokens[2].length()-1));
      int sensorY = Integer.parseInt(sensorTokens[3].substring(2));
      Point sensorKey = new Point(sensorX, sensorY);

      String[] beaconTokens = beacon.split("\s");
      int beaconX = Integer.parseInt(beaconTokens[5].substring(2,beaconTokens[5].length()-1));
      int beaconY = Integer.parseInt(beaconTokens[6].substring(2));
      Point beaconValue = new Point(beaconX, beaconY);
      sensorMap.put(sensorKey,beaconValue);
    }

  }

  public static int getTaxicabDistance(Point a, Point b){
    return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
  }

  public static int getPart01(String filepath, int row){
    readInput(filepath);
    buildSensorMap();
    ArrayList<Day15Interval> yNoBeaconIntervals = new ArrayList<Day15Interval>();
    for(Point p : sensorMap.keySet()){
      System.out.print("closest beacon to (" + p.getX() + "," + p.getY() + ") is (" + sensorMap.get(p).getX() + "," + sensorMap.get(p).getY() + ") ");
      //int taxicabDistance = Math.abs(p.getX() - sensorMap.get(p).getX()) + Math.abs(p.getY() - sensorMap.get(p).getY());
      int taxicabDistance = getTaxicabDistance(p,sensorMap.get(p));
      System.out.println("which is " + taxicabDistance + " units away");
      int distToRow = Math.abs(p.getY() - row);
      System.out.println("row " + row + " is distance " + distToRow + " from sensor");
      if(distToRow < taxicabDistance){
        int remain = taxicabDistance - distToRow;
        Day15Interval interval = new Day15Interval(p.getX()-remain,p.getX()+remain);
        System.out.println("leaving " + remain + " from point of intersection");
        System.out.println("which rules out beacons between " + interval.toString());
        yNoBeaconIntervals.add(interval);
      }
      System.out.println("");
    }

    for(int k=0;k<yNoBeaconIntervals.size();k++){
      System.out.println(yNoBeaconIntervals.get(k).toString());
    }
    System.out.println("");

    for(int passes=0;passes<3;passes++){
      for(int k=0;k<yNoBeaconIntervals.size()-1;k++){
        for(int j=k+1;j<yNoBeaconIntervals.size();j++){
          Day15Interval i1 = yNoBeaconIntervals.get(k);
          Day15Interval i2 = yNoBeaconIntervals.get(j);
          if(i1.overlaps(i2)){
            System.out.println(i1.toString()+"+"+i2.toString());
            yNoBeaconIntervals.set(k,i1.merge(i2));
            yNoBeaconIntervals.remove(j);
            j--;
          }else{
            System.out.println(i1.toString()+"/"+i2.toString());
          }
        }
        System.out.println("");
      }
    }

    System.out.println("");
    int yNoBeaconSpaces = 0;
    for(Day15Interval interval : yNoBeaconIntervals){
      System.out.println(interval.toString());
      yNoBeaconSpaces += interval.size();
    }

    ArrayList<Integer> xKnownSpaces = new ArrayList<Integer>();
    for(Point p : sensorMap.values()){
      if(p.getY() == row){
        System.out.println(p.getX()+","+p.getY());
        int xKnown = p.getX();
        if(!xKnownSpaces.contains(xKnown)){
          xKnownSpaces.add(xKnown);
        }
      }
    }

    return yNoBeaconSpaces;
  }

}
