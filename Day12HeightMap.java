public class Day12HeightMap{
  private final int WIDTH;
  private final int HEIGHT;
  private char[][] grid;
  private Point start;
  private Point end;

  public Day12HeightMap(int width, int height){
    WIDTH = width;
    HEIGHT = height;
    grid = new char[width][height];
    for(int x=0;x<width;x++){
      for(int y=0;y<height;y++){
        grid[x][y] = ' ';
      }
    }
  }

  public int getWidth(){
    return WIDTH;
  }
  public int getHeight(){
    return HEIGHT;
  }

  public char getCell(int x, int y){
    return grid[x][y];
  }
  public void setCell(int x, int y, char val){
    grid[x][y] = val;
    if(val == 'S'){
      start = new Point(x,y);
    }
    if(val == 'E'){
      end = new Point(x,y);
    }
  }
  public Point getStart(){
    return start;
  }
  public Point getEnd(){
    return end;
  }
}
