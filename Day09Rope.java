import java.util.ArrayList;
import java.util.HashSet;

public class Day09Rope{
  private int[] linksX;
  private int[] linksY;
  private ArrayList<Point> tailSeen;

  public Day09Rope(int linkCount){
    linksX = new int[linkCount];
    linksY = new int[linkCount];
    for(int k=0;k<linkCount;k++){
      linksX[k] = 0;
      linksY[k] = 0;
    }
    tailSeen = new ArrayList<Point>();
    tailSeen.add(new Point(0,0));
  }

  public void move(char direction){
    switch(direction){
      case 'U': linksY[0]++;  break;
      case 'D': linksY[0]--;  break;
      case 'L': linksX[0]--;  break;
      case 'R': linksX[0]++;  break;
    }
    for(int k=1;k<linksX.length;k++){
      int distX = linksX[k] - linksX[k-1];
      int distY = linksY[k] - linksY[k-1];

      if(distX == 2){
        linksX[k] = linksX[k-1] + 1;
        if(distY == 1){
          linksY[k]--;
        }else if(distY == -1){
          linksY[k]++;
        }
      }else if(distX == -2){
        linksX[k] = linksX[k-1] - 1;
        if(distY == 1){
          linksY[k]--;
        }else if(distY == -1){
          linksY[k]++;
        }
      }
      if(distY == 2){
        linksY[k] = linksY[k-1] + 1;
        if(distX == 1){
          linksX[k]--;
        }else if(distX == -1){
          linksX[k]++;
        }
      }else if(distY == -2){
        linksY[k] = linksY[k-1] - 1;
        if(distX == 1){
          linksX[k]--;
        }else if(distX == -1){
          linksX[k]++;
        }
      }
    }
    Point tail = new Point(linksX[linksX.length-1],linksY[linksY.length-1]);
    if(!tailSeen.contains(tail)){
      tailSeen.add(tail);
    }
  }

  public int getSeenCount(){
    /*for(Point p : tailSeen){
      System.out.println("(" + p.getX() + "," + p.getY() + ")");
    }*/
    return tailSeen.size();
  }
}
