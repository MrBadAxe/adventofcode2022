import java.util.LinkedList;

public class Day12PathFinder{
  public static final int NORTH = 0;
  public static final int SOUTH = 1;
  public static final int WEST = 2;
  public static final int EAST = 3;

  public static boolean isValidMove(Day12HeightMap map, int x, int y, int dir){
    char from = map.getCell(x,y);
    from = (from == 'S' ? 'a' : (from == 'E' ? 'z' : from));
    char to;
    switch(dir){
      case NORTH:
        if(y < 1){return false;}
        to = map.getCell(x,y-1);
        break;
      case SOUTH:
        if(y > map.getHeight()-2){return false;}
        to = map.getCell(x,y+1);
        break;
      case WEST:
        if(x < 1){return false;}
        to = map.getCell(x-1,y);
        break;
      case EAST:
        if(x > map.getWidth()-2){return false;}
        to = map.getCell(x+1,y);
        break;
      default:
        return false;
    }
    to = (to == 'S' ? 'a' : (to == 'E' ? 'z' : to));
    return ((int)to - (int)from) <= 1;
  }

  public static int[][] findPath(Day12HeightMap map, boolean allLowPointsEqual){

    int[][] z = new int[map.getWidth()][map.getHeight()];
    for(int x=0;x<map.getWidth();x++){
      for(int y=0;y<map.getHeight();y++){
        z[x][y] = Integer.MAX_VALUE;
      }
    }
    LinkedList<Point> exploreList = new LinkedList<Point>();
    if(allLowPointsEqual){
      for(int x=0;x<map.getWidth();x++){
        for(int y=0;y<map.getHeight();y++){
          if(map.getCell(x,y) == 'a' || map.getCell(x,y) == 'S'){
            z[x][y] = 0;
            exploreList.add(new Point(x,y));
          }
        }
      }
    }else{
      Point start = map.getStart();
      z[start.getX()][start.getY()] = 0;
      exploreList.add(start);
    }

    while(exploreList.size() > 0){
      Point next = exploreList.removeFirst();
      int x = next.getX();
      int y = next.getY();
      //System.out.println("(" + x + "," + y + ")=" + map.getCell(x,y));
      if(isValidMove(map,x,y,NORTH)){
        if(z[x][y] + 1 < z[x][y-1]){
          z[x][y-1] = z[x][y] + 1;
          exploreList.add(new Point(x,y-1));
        }
      }
      if(isValidMove(map,x,y,SOUTH)){
        if(z[x][y] + 1 < z[x][y+1]){
          z[x][y+1] = z[x][y] + 1;
          exploreList.add(new Point(x,y+1));
        }
      }
      if(isValidMove(map,x,y,WEST)){
        if(z[x][y] + 1 < z[x-1][y]){
          z[x-1][y] = z[x][y] + 1;
          exploreList.add(new Point(x-1,y));
        }
      }
      if(isValidMove(map,x,y,EAST)){
        if(z[x][y] + 1 < z[x+1][y]){
          z[x+1][y] = z[x][y] + 1;
          exploreList.add(new Point(x+1,y));
        }
      }

    }
    return z;
  }
}
