//package org.mrbadaxe.AdventOfCode2022;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Day15{

  public static HashMap<Point,Point> buildSensorMap(List<String> input){
    HashMap<Point,Point> sensorMap = new HashMap<Point,Point>();
    for(String str : input){
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
    return sensorMap;
  }

  public static int getTaxicabDistance(Point a, Point b){
    return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
  }

  public static ArrayList<Day15Interval> generateNoBeaconIntervalList(HashMap<Point,Point> map, int row){
    ArrayList<Day15Interval> z = new ArrayList<Day15Interval>();
    for(Point p : map.keySet()){
      int taxicabDistance = getTaxicabDistance(p,map.get(p));
      int distToRow = Math.abs(p.getY() - row);
      if(distToRow < taxicabDistance){
        int remain = taxicabDistance - distToRow;
        Day15Interval interval = new Day15Interval(p.getX()-remain,p.getX()+remain);
        z.add(interval);
      }
    }

    int oldSize = Integer.MAX_VALUE;
    while(z.size() != oldSize){
      oldSize = z.size();
      for(int k=0;k<z.size()-1;k++){
        for(int j=k+1;j<z.size();j++){
          Day15Interval i1 = z.get(k);
          Day15Interval i2 = z.get(j);
          if(i1.merge(i2) != null){
            z.set(k,i1.merge(i2));
            z.remove(j);
            j--;
          }
        }
      }
    }
    return z;
  }

  public static int getPart01(List<String> input, int row){
    HashMap<Point,Point> sensorMap = buildSensorMap(input);
    ArrayList<Day15Interval> yNoBeaconIntervals = generateNoBeaconIntervalList(sensorMap,row);

    int yNoBeaconSpaces = 0;
    for(Day15Interval interval : yNoBeaconIntervals){
      yNoBeaconSpaces += interval.size();
    }

    ArrayList<Integer> xKnownSpaces = new ArrayList<Integer>();
    for(Point p : sensorMap.values()){
      if(p.getY() == row){
        int xKnown = p.getX();
        if(!xKnownSpaces.contains(xKnown)){
          xKnownSpaces.add(xKnown);
        }
      }
    }

    return yNoBeaconSpaces - xKnownSpaces.size();
  }

  public static long getPart02(List<String> input, int xMax){
    HashMap<Point,Point> sensorMap = buildSensorMap(input);

    int beaconX = 0;
    int beaconY = 0;

    for(int k=0;k<xMax;k++){
      ArrayList<Day15Interval> yNoBeaconIntervals = generateNoBeaconIntervalList(sensorMap,k);
      if(yNoBeaconIntervals.size() == 1 && (yNoBeaconIntervals.get(0).getLower() <= 0) && (yNoBeaconIntervals.get(0).getUpper() >= xMax)){
        //covers entire row, do nothing
      }else{
        Day15Interval i1 = yNoBeaconIntervals.get(0);
        Day15Interval i2 = yNoBeaconIntervals.get(1);
        if(i1.getUpper() + 2 == i2.getLower()){
          beaconX = i2.getLower()-1;
        }else{
          beaconX = i1.getLower()-1;
        }
        beaconY = k;
      }
    }
    return (long)beaconX*4000000 + (long)beaconY;
  }

}
