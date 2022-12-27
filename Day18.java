//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;
import java.util.ArrayList;

public class Day18{

  private static int CUBE_EDGE = 20;

  public static int getPart01(List<String> input){
    List<TriPoint> droplets = new ArrayList<TriPoint>();
    for(String line : input){
      String[] split = line.split(",");
      droplets.add(new TriPoint(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2])));
    }
    int maxSurfaceArea = 6 * droplets.size();
    for(TriPoint droplet : droplets){
      if(droplets.contains(new TriPoint(droplet.getX()-1,droplet.getY(),droplet.getZ()))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX()+1,droplet.getY(),droplet.getZ()))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX(),droplet.getY()-1,droplet.getZ()))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX(),droplet.getY()+1,droplet.getZ()))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX(),droplet.getY(),droplet.getZ()-1))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX(),droplet.getY(),droplet.getZ()+1))){ maxSurfaceArea--; }
    }
    return maxSurfaceArea;
  }

  public static int getPart02(List<String> input){
    List<TriPoint> droplets = new ArrayList<TriPoint>();
    for(String line : input){
      String[] split = line.split(",");
      droplets.add(new TriPoint(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2])));
    }


    List<TriPoint> steam = new ArrayList<TriPoint>();
    steam.add(new TriPoint(0,0,0));

    int newSteamCount = 1;
    int steamCount = steam.size();
    while(newSteamCount > 0){
      //System.out.println(steam.size() + ":" + steam.toString());
      List<TriPoint> newSteam = new ArrayList<TriPoint>();
      for(TriPoint t : steam){
        int x = t.getX();
        int y = t.getY();
        int z = t.getZ();
        List<TriPoint> neighbors = new ArrayList<TriPoint>();
        if(x > 0)           { neighbors.add(new TriPoint(x-1,y,z)); }
        if(x < CUBE_EDGE-1) { neighbors.add(new TriPoint(x+1,y,z)); }
        if(y > 0)           { neighbors.add(new TriPoint(x,y-1,z)); }
        if(y < CUBE_EDGE-1) { neighbors.add(new TriPoint(x,y+1,z)); }
        if(z > 0)           { neighbors.add(new TriPoint(x,y,z-1)); }
        if(z < CUBE_EDGE-1) { neighbors.add(new TriPoint(x,y,z+1)); }
        for(TriPoint n : neighbors){
          if(!droplets.contains(n) && !steam.contains(n)){
            newSteam.add(n);
          }
        }
      }
      newSteamCount = newSteam.size();
      //System.out.println(newSteamCount + ":" + newSteam.toString());
      for(TriPoint t : newSteam){
        if(!steam.contains(t)){
          steam.add(t);
        }
      }
    }

    System.out.println(droplets.size());
    System.out.println(steam.size());
    for(int x=0;x<CUBE_EDGE;x++){
      for(int y=0;y<CUBE_EDGE;y++){
        for(int z=0;z<CUBE_EDGE;z++){
          TriPoint t = new TriPoint(x,y,z);
          if(!steam.contains(t)){
            if(!droplets.contains(t)){
              droplets.add(t);
            }
          }
        }
      }
    }

    int maxSurfaceArea = 6 * droplets.size();
    for(TriPoint droplet : droplets){
      if(droplets.contains(new TriPoint(droplet.getX()-1,droplet.getY(),droplet.getZ()))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX()+1,droplet.getY(),droplet.getZ()))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX(),droplet.getY()-1,droplet.getZ()))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX(),droplet.getY()+1,droplet.getZ()))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX(),droplet.getY(),droplet.getZ()-1))){ maxSurfaceArea--; }
      if(droplets.contains(new TriPoint(droplet.getX(),droplet.getY(),droplet.getZ()+1))){ maxSurfaceArea--; }
    }
    return maxSurfaceArea;

  }

}
