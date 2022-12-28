public class Day22Grid{
  public static final char VOID = ' ';
  public static final char SPACE = '.';
  public static final char WALL = '#';

  public static final int FACING_RIGHT = 0;
  public static final int FACING_DOWN = 1;
  public static final int FACING_LEFT = 2;
  public static final int FACING_UP = 3;

  private final int WIDTH;
  private final int HEIGHT;
  private final int REGION_EDGE_LENGTH;
  private char[][] grid;
  private boolean isCube;

  public Day22Grid(int h, int w, boolean wrap){
    this.WIDTH = w;
    this.HEIGHT = h;
    this.REGION_EDGE_LENGTH = calculateRegionEdgeLength(h,w);
    this.isCube = wrap;
    grid = new char[HEIGHT][WIDTH];
    for(int col=0;col<WIDTH;col++){
      for(int row=0;row<HEIGHT;row++){
        this.set(row,col,VOID);
      }
    }
  }

  private int calculateRegionEdgeLength(int h, int w){
    return (w == 0 ? h : calculateRegionEdgeLength(w, h % w));
  }

  public Day22Grid(int h, int w){
    this(h,w,false);
  }

  public int height(){
    return this.HEIGHT;
  }
  public int width(){
    return this.WIDTH;
  }
  public int get(int row, int col){
    return this.grid[row][col];
  }
  public void set(int row, int col, char c){
    this.grid[row][col] = c;
  }
  public String toString(){
    String z = "";
    for(int row=0;row<HEIGHT;row++){
      z += new String(this.grid[row]) + "\n";
    }
    return z;
  }

  public TriPoint neighbor(TriPoint t){
    return this.isCube ? flatNeighbor(t) : flatNeighbor(t);
  }

  public TriPoint flatNeighbor(TriPoint t){
    int row = t.getX();
    int col = t.getY();
    int facing = t.getZ();

    int z = -1;
    switch(facing){
      case FACING_RIGHT:
        z = (col+1)%WIDTH;
        while(this.get(row,z) == VOID){
          z = (z+1)%WIDTH;
        }
        return new TriPoint(row,z,facing);
      case FACING_DOWN:
        z = (row+1)%HEIGHT;
        while(this.get(z,col) == VOID){
          z = (z+1)%HEIGHT;
        }
        return new TriPoint(z,col,facing);
      case FACING_LEFT:
        z = (col+WIDTH-1)%WIDTH;
        while(this.get(row,z) == VOID){
          z = (z+WIDTH-1)%WIDTH;
        }
        return new TriPoint(row,z,facing);
      case FACING_UP:
        z = (row+HEIGHT-1)%HEIGHT;
        while(this.get(z,col) == VOID){
          z = (z+HEIGHT-1)%HEIGHT;
        }
        return new TriPoint(z,col,facing);
      default:
        return t;
    }
  }

}
