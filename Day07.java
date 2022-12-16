//package org.mrbadaxe.AdventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day07{
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

  public static Day07Directory buildFileTree(){
    Day07Directory root = new Day07Directory();
    Day07Directory workingDir = root;
    for(String str : lines){
      //System.out.println(str);
      String[] tokens = str.split("\s");
      if(tokens[0].equals("$")){
        if(tokens[1].equals("ls")){
          //System.err.println("list");
        }else if(tokens[1].equals("cd")){
          if(tokens[2].equals("..")){
            //System.err.println("up one");
            workingDir = workingDir.getParent();
          }else if(tokens[2].equals("/")){
            //System.err.println("to root");
            workingDir = root;
          }else{
            //System.err.println("to dir " + tokens[2]);
            workingDir = workingDir.getDir(tokens[2]);
          }
        }
      }else if(tokens[0].equals("dir")){
        //System.err.println("new dir: " + tokens[1]);
        workingDir.mkDir(tokens[1]);
      }else{
        workingDir.addFile(tokens[1],Integer.parseInt(tokens[0]));
      }
    }
    return root;
  }
  public static int getPart01(String filepath){
    readInput(filepath);
    Day07Directory root = buildFileTree();

    int allTheSmallDirs = 0;
    LinkedList<Day07Directory> queue = new LinkedList<Day07Directory>();
    queue.add(root);
    while(queue.size() > 0){
      Day07Directory nextDir = queue.removeFirst();
      if(nextDir.getDirSize() < 100000){
        allTheSmallDirs += nextDir.getDirSize();
      }
      for(String dir : nextDir.getDirList().keySet()){
        queue.add(nextDir.getDir(dir));
      }
    }
    return allTheSmallDirs;
  }

  public static int getPart02(String filepath){
    readInput(filepath);
    Day07Directory root = buildFileTree();

    int diskSize = 70_000_000;
    int freeSpaceRequired = 30_000_000;
    int usedSpace = root.getDirSize();
    int minimumDeleteSize = freeSpaceRequired - (diskSize - usedSpace);
    int smallestDirSize = usedSpace;

    LinkedList<Day07Directory> queue = new LinkedList<Day07Directory>();
    queue.add(root);
    while(queue.size() > 0){
      Day07Directory nextDir = queue.removeFirst();
      if(nextDir.getDirSize() > minimumDeleteSize){
        smallestDirSize = Math.min(smallestDirSize,nextDir.getDirSize());
      }
      for(String dir : nextDir.getDirList().keySet()){
        queue.add(nextDir.getDir(dir));
      }
    }

    return smallestDirSize;
  }

}
