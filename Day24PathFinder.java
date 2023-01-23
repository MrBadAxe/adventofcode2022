import java.util.List;
import java.util.ArrayList;

public class Day24PathFinder{

  private static int lcm(int a, int b){
    return a*b / gcd(a,b);
  }
  private static int gcd(int a, int b){
    return b==0 ? a : gcd(b, a%b);
  }

  private static Day24BlizzardField[] precomputeFields(Day24BlizzardField initState){
    int maxSteps = lcm((initState.rows()-2),(initState.cols()-2));
    Day24BlizzardField[] fields = new Day24BlizzardField[maxSteps];
    fields[0] = initState;
    System.out.println(fields[0].toString());
    for(int k=1;k<maxSteps;k++){
      fields[k] = fields[k-1].step();
      System.out.println(fields[k].toString());
    }
    return fields;
  }

  private static List<Point> getNeighbors(Point p){
    List<Point> neighbors = new ArrayList<Point>();
    neighbors.add(new Point(p.getX()-1,p.getY()));
    neighbors.add(new Point(p.getX()+1,p.getY()));
    neighbors.add(new Point(p.getX(),p.getY()-1));
    neighbors.add(new Point(p.getX(),p.getY()+1));
    return neighbors;
  }

  public static int[][] solve(Day24BlizzardField initState, Point start, Point end){

    int[][] distances = new int[initState.rows()][initState.cols()];

    for(int row=0;row<distances.length;row++){
      for(int col=0;col<distances[0].length;col++){
        if(row == 0 || row == distances.length || col == 0 || col == distances[0].length){
          distances[row][col] = -1;
        }else{
          distances[row][col] = Integer.MAX_VALUE;
        }
      }
    }
    distances[start.getX()][start.getY()] = 0;
    distances[end.getX()][end.getY()] = Integer.MAX_VALUE;

    Day24BlizzardField[] fields = precomputeFields(initState);
    }

    return distances;
  }
}
