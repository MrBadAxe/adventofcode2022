//package org.mrbadaxe.AdventOfCode2022;

import java.util.List;

public class Day22{

  public static TriPoint getStartingPosition(Day22Grid grid){
    int row = 0;
    int col = 0;
    while(grid.get(row,col) == Day22Grid.VOID){
      col++;
    }
    return new TriPoint(row,col,Day22Grid.FACING_RIGHT);
  }
  public static TriPoint getStartingPosition(Day22Cube cube){
    int row = 0;
    int col = 0;
    while(cube.get(0,col,0,0) == Day22Grid.VOID){
      col++;
    }
    return new TriPoint(row * cube.edgeLength(), col * cube.edgeLength(), Day22Grid.FACING_RIGHT);
  }


  public static Day22Grid generateGrid(List<String> input){
    int longest = 0;
    for(String line : input){
      longest = Math.max(longest,line.length());
    }
    Day22Grid grid = new Day22Grid(input.size(),longest);
    for(int row=0;row<input.size();row++){
      String line = input.get(row);
      for(int col=0;col<line.length();col++){
        grid.set(row,col,line.charAt(col));
      }
    }
    System.out.println(grid.toString());
    return grid;
  }

  public static Day22Cube generateCube(List<String> input){
    int longest = 0;
    for(String line : input){
      longest = Math.max(longest,line.length());
    }
    Day22Cube cube = new Day22Cube(input.size(),longest);
    for(int row=0;row<input.size();row++){
      String line = input.get(row);
      for(int col=0;col<line.length();col++){
        cube.set(row,col,line.charAt(col));
      }
    }
    //System.out.println(grid.toString());
    //cube.calculateCubeNet_Sample();
    cube.calculateCubeNet_Test();
    return cube;
  }

  public static TriPoint navigate(Day22Grid grid, TriPoint startPos, String dirList){
    TriPoint pos = startPos;
    String[] moves = dirList.split("[LR]");
    String[] turns = dirList.split("[0-9]+");

    for(int k=0; k<moves.length; k++){
      System.out.print(turns[k] + " ");
      System.out.print(moves[k] + " ");
      int newFacing;
      switch((turns[k].length() > 0 ? turns[k].charAt(0) : ' ')){
        case 'L': newFacing = (pos.getZ() + 3)%4; break;
        case 'R': newFacing = (pos.getZ() + 1)%4; break;
        default:  newFacing = pos.getZ();
      }
      pos = new TriPoint(pos.getX(),pos.getY(),newFacing);

      int movesRemaining = Integer.parseInt(moves[k]);
      while(movesRemaining > 0){
        TriPoint newPos = grid.neighbor(pos);
        if(grid.get(newPos.getX(),newPos.getY()) == Day22Grid.WALL){
          movesRemaining = 0;
        }else{
          pos = newPos;
          System.out.println(pos.toString());
          movesRemaining--;
        }
      }
    }
    return pos;
  }

  public static TriPoint navigate(Day22Cube cube, TriPoint startPos, String dirList){
    TriPoint pos = startPos;
    String[] moves = dirList.split("[LR]");
    String[] turns = dirList.split("[0-9]+");

    for(int k=0; k<moves.length; k++){
      System.out.print(turns[k] + " ");
      System.out.print(moves[k] + " ");
      int newFacing;
      switch((turns[k].length() > 0 ? turns[k].charAt(0) : ' ')){
        case 'L': newFacing = (pos.getZ() + 3)%4; break;
        case 'R': newFacing = (pos.getZ() + 1)%4; break;
        default:  newFacing = pos.getZ();
      }
      pos = new TriPoint(pos.getX(),pos.getY(),newFacing);

      int movesRemaining = Integer.parseInt(moves[k]);
      while(movesRemaining > 0){
        TriPoint newPos = cube.neighbor(pos);
        if(cube.get(newPos.getX(),newPos.getY()) == Day22Grid.WALL){
          movesRemaining = 0;
        }else{
          pos = newPos;
          System.out.println(pos.toString());
          movesRemaining--;
        }
      }
    }
    return pos;
  }

  public static long getPart01(List<String> input){
    String dirList = input.remove(input.size()-1);
    String separator = input.remove(input.size()-1);

    Day22Grid grid = generateGrid(input);
    TriPoint pos = getStartingPosition(grid);
    System.out.println(pos);
    pos = navigate(grid, pos, dirList);

    return (1000 * (pos.getX()+1)) + (4 * (pos.getY()+1)) + pos.getZ();
  }

  public static long getPart02(List<String> input){
    String dirList = input.remove(input.size()-1);
    String separator = input.remove(input.size()-1);

    Day22Cube cube = generateCube(input);
    TriPoint pos = getStartingPosition(cube);
    System.out.println(pos);
    pos = navigate(cube, pos, dirList);

    return (1000 * (pos.getX()+1)) + (4 * (pos.getY()+1)) + pos.getZ();
  }

}
