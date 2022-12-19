//package org.mrbadaxe.AdventOfCode2022;

import java.util.LinkedList;
import java.util.List;

public class Day07{

  public static Day07Directory buildFileTree(List<String> input){
    Day07Directory root = new Day07Directory();
    Day07Directory workingDir = root;
    for(String str : input){
      String[] tokens = str.split("\s");
      if(tokens[0].equals("$")){
        if(tokens[1].equals("ls")){
        }else if(tokens[1].equals("cd")){
          if(tokens[2].equals("..")){
            workingDir = workingDir.getParent();
          }else if(tokens[2].equals("/")){
            workingDir = root;
          }else{
            workingDir = workingDir.getDir(tokens[2]);
          }
        }
      }else if(tokens[0].equals("dir")){
        workingDir.mkDir(tokens[1]);
      }else{
        workingDir.addFile(tokens[1],Integer.parseInt(tokens[0]));
      }
    }
    return root;
  }
  public static int getPart01(List<String> input){
    Day07Directory root = buildFileTree(input);

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

  public static int getPart02(List<String> input){
    Day07Directory root = buildFileTree(input);

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
