import java.util.HashMap;

public class Day22Cube{
  public static final char VOID = ' ';
  public static final char SPACE = '.';
  public static final char WALL = '#';

  public static final int FACING_RIGHT = 0;
  public static final int FACING_DOWN = 1;
  public static final int FACING_LEFT = 2;
  public static final int FACING_UP = 3;

  private final int EDGE_LENGTH;
  private char[][][][] cubeNet;
  private HashMap<TriPoint,TriPoint> edges;

  public Day22Cube(int h, int w){
    EDGE_LENGTH = calculateEdgeLength(h,w);
    cubeNet = new char[h/EDGE_LENGTH][w/EDGE_LENGTH][EDGE_LENGTH][EDGE_LENGTH];
    for(int row=0;row<h;row++){
      for(int col=0;col<w;col++){
        this.set(row,col,Day22Cube.VOID);
      }
    }
    edges = new HashMap<TriPoint,TriPoint>();
  }
  private int calculateEdgeLength(int h, int w){
    return (w == 0 ? h : calculateEdgeLength(w, h % w));
  }
  public int edgeLength(){
    return this.EDGE_LENGTH;
  }
  public char get(int faceRow, int faceCol, int cellRow, int cellCol){
    return this.cubeNet[faceRow][faceCol][cellRow][cellCol];
  }
  public void set(int faceRow, int faceCol, int cellRow, int cellCol, char c){
    this.cubeNet[faceRow][faceCol][cellRow][cellCol] = c;
  }
  public char get(int row, int col){
    System.out.println(">" + row + " " + col);
    return this.cubeNet[row/EDGE_LENGTH][col/EDGE_LENGTH][row%EDGE_LENGTH][col%EDGE_LENGTH];
  }
  public void set(int row, int col, char c){
    this.cubeNet[row/EDGE_LENGTH][col/EDGE_LENGTH][row%EDGE_LENGTH][col%EDGE_LENGTH] = c;
  }

  public TriPoint neighbor(TriPoint t){
    int absRow = t.getX();
    int absCol = t.getY();
    int facing = t.getZ();
    int faceRow = absRow / EDGE_LENGTH;
    int faceCol = absCol / EDGE_LENGTH;
    int cellRow = absRow % EDGE_LENGTH;
    int cellCol = absCol % EDGE_LENGTH;
    System.out.println("(" + faceRow + "," + faceCol + "):(" + cellRow + "," + cellCol + ")");
    int netHeight = cubeNet.length * EDGE_LENGTH;
    int netWidth = cubeNet[0].length * EDGE_LENGTH;

    //TriPoint link;
    int newFacing;
    int newRow = -1;
    int newCol = -1;

    switch(facing){
      case FACING_RIGHT:
        if(cellCol + 1 < EDGE_LENGTH){
          System.out.println("right: doesn't leave the face");
          return new TriPoint(absRow,((absCol+1)%netWidth),facing);
        }/*else if(this.get(absRow,((absCol+1)%netWidth)) != Day22Cube.VOID){
          System.out.println("right: doesn't cross a void (" + this.get(absRow,((absCol+1)%netWidth)) + ")");
          return new TriPoint(absRow,((absCol+1)%netWidth),facing);
        }*/else{
          System.out.println("right: crosses a void");
          TriPoint link = edges.get(new TriPoint(faceRow,faceCol,facing));
          System.out.println(link.toString());
          newFacing = link.getZ();
          switch(newFacing){
            case FACING_RIGHT:
              newRow = (link.getX() * EDGE_LENGTH) + cellRow;
              newCol = (link.getY() * EDGE_LENGTH);
              break;
            case FACING_DOWN:
              newRow = (link.getX() * EDGE_LENGTH);
              newCol = (link.getY() * EDGE_LENGTH) + (EDGE_LENGTH - cellRow) - 1;
              break;
            case FACING_LEFT:
              newRow = (link.getX() * EDGE_LENGTH) + (EDGE_LENGTH - cellRow) - 1;
              newCol = (link.getY() * EDGE_LENGTH) + (EDGE_LENGTH - 1);
              break;
            case FACING_UP:
              newRow = (link.getX() * EDGE_LENGTH) + (EDGE_LENGTH - 1);
              newCol = (link.getY() * EDGE_LENGTH) + cellRow;
              break;
          }
          return new TriPoint(newRow,newCol,newFacing);
        }
      case FACING_DOWN:
        if(cellRow + 1 < EDGE_LENGTH){
          System.out.println("down: doesn't leave the face");
          return new TriPoint(((absRow+1)%netHeight),absCol,facing);
        }/*else if(this.get(((absRow+1)%netHeight),absCol) != Day22Cube.VOID){
          System.out.println("down: doesn't cross a void");
          return new TriPoint(((absRow+1)%netHeight),absCol,facing);
        }*/else{
          TriPoint link = edges.get(new TriPoint(faceRow,faceCol,facing));
          newFacing = link.getZ();
          switch(newFacing){
            case FACING_DOWN:
              newRow = (link.getX() * EDGE_LENGTH);
              newCol = (link.getY() * EDGE_LENGTH) + cellCol;
              break;
            case FACING_LEFT:
              newRow = (link.getX() * EDGE_LENGTH) + cellCol;
              newCol = (link.getY() * EDGE_LENGTH) + (EDGE_LENGTH - 1);
              break;
            case FACING_UP:
              newRow = (link.getX() * EDGE_LENGTH) + (EDGE_LENGTH - 1);
              newCol = (link.getY() * EDGE_LENGTH) + (EDGE_LENGTH - cellCol) - 1;
              break;
            case FACING_RIGHT:
              newRow = (link.getX() * EDGE_LENGTH) + (EDGE_LENGTH - cellCol) - 1;
              newCol = (link.getY() * EDGE_LENGTH);
              break;
          }
          return new TriPoint(newRow,newCol,newFacing);
        }
      case FACING_LEFT:
        if(cellCol - 1 >= 0){
          System.out.println("left: doesn't leave the face");
          return new TriPoint(absRow,((absCol+netWidth-1)%netWidth),facing);
        }/*else if(this.get(absRow,((absCol+netWidth-1)%netWidth)) != Day22Cube.VOID){
          System.out.println("left: doesn't cross a void");
          return new TriPoint(absRow,((absCol+netWidth-1)%netWidth),facing);
        }*/else{
          TriPoint link = edges.get(new TriPoint(faceRow,faceCol,facing));
          newFacing = link.getZ();
          switch(newFacing){
            case FACING_LEFT:
              newRow = (link.getX() * EDGE_LENGTH) + cellRow;
              newCol = (link.getY() * EDGE_LENGTH) + (EDGE_LENGTH - 1);
              break;
            case FACING_UP:
              newRow = (link.getX() * EDGE_LENGTH) + (EDGE_LENGTH - 1);
              newCol = (link.getY() * EDGE_LENGTH) + (EDGE_LENGTH - cellRow) - 1;
              break;
            case FACING_RIGHT:
              newRow = (link.getX() * EDGE_LENGTH) + (EDGE_LENGTH - cellRow) - 1;
              newCol = (link.getY() * EDGE_LENGTH);
              break;
            case FACING_DOWN:
              newRow = (link.getX() * EDGE_LENGTH);
              newCol = (link.getY() * EDGE_LENGTH) + cellRow;
              break;
          }
          return new TriPoint(newRow,newCol,newFacing);
        }
      case FACING_UP:
        if(cellRow - 1 >= 0){
          System.out.println("up: doesn't leave the face");
          return new TriPoint(((absRow+netHeight-1)%netHeight),absCol,facing);
        }/*else if(this.get(((absRow+netHeight-1)%netHeight),absCol) != Day22Cube.VOID){
          System.out.println("up: doesn't cross a void");
          return new TriPoint(((absRow+netHeight-1)%netHeight),absCol,facing);
        }*/else{
          TriPoint link = edges.get(new TriPoint(faceRow,faceCol,facing));
          newFacing = link.getZ();
          switch(newFacing){
            case FACING_UP:
              newRow = (link.getX() * EDGE_LENGTH) + (EDGE_LENGTH - 1);
              newCol = (link.getY() * EDGE_LENGTH) + cellCol;
              break;
            case FACING_RIGHT:
              newRow = (link.getX() * EDGE_LENGTH) + cellCol;
              newCol = (link.getY() * EDGE_LENGTH);
              break;
            case FACING_DOWN:
              newRow = (link.getX() * EDGE_LENGTH);
              newCol = (link.getY() * EDGE_LENGTH) + (EDGE_LENGTH - cellCol) - 1;
              break;
            case FACING_LEFT:
              newRow = (link.getX() * EDGE_LENGTH) + (EDGE_LENGTH - cellCol) - 1;
              newCol = (link.getY() * EDGE_LENGTH) + (EDGE_LENGTH - 1);
              break;
          }
          return new TriPoint(newRow,newCol,newFacing);
        }
    }
    return null;
  }

  /*
    hardcoded edge mapping for sample input
    ..#.
    ###.
    ..##
  */

  public void calculateCubeNet_Sample(){
    this.edges.put(new TriPoint(0,2,0),new TriPoint(2,3,2));
    this.edges.put(new TriPoint(0,2,2),new TriPoint(1,1,1));
    this.edges.put(new TriPoint(0,2,3),new TriPoint(1,0,1));
    this.edges.put(new TriPoint(1,0,1),new TriPoint(2,2,3));
    this.edges.put(new TriPoint(1,0,2),new TriPoint(2,3,3));
    this.edges.put(new TriPoint(1,0,3),new TriPoint(0,2,1));
    this.edges.put(new TriPoint(1,1,1),new TriPoint(2,2,0));
    this.edges.put(new TriPoint(1,1,3),new TriPoint(0,2,0));
    this.edges.put(new TriPoint(1,2,0),new TriPoint(2,3,1));
    this.edges.put(new TriPoint(2,2,1),new TriPoint(1,0,3));
    this.edges.put(new TriPoint(2,2,2),new TriPoint(1,1,3));
    this.edges.put(new TriPoint(2,3,0),new TriPoint(0,2,2));
    this.edges.put(new TriPoint(2,3,1),new TriPoint(1,0,0));
    this.edges.put(new TriPoint(2,3,3),new TriPoint(1,2,2));

    this.edges.put(new TriPoint(0,2,1),new TriPoint(1,2,1));
    this.edges.put(new TriPoint(1,2,3),new TriPoint(0,2,3));
    this.edges.put(new TriPoint(1,0,0),new TriPoint(1,1,0));
    this.edges.put(new TriPoint(1,1,2),new TriPoint(1,1,2));
    this.edges.put(new TriPoint(1,1,0),new TriPoint(1,2,0));
    this.edges.put(new TriPoint(1,2,2),new TriPoint(1,1,2));
    this.edges.put(new TriPoint(1,2,1),new TriPoint(2,2,1));
    this.edges.put(new TriPoint(2,2,3),new TriPoint(1,2,3));
    this.edges.put(new TriPoint(2,2,0),new TriPoint(2,3,0));
    this.edges.put(new TriPoint(2,3,2),new TriPoint(2,2,2));
  }

  /*
    hardcoded edge mapping for test input
    .##
    .#.
    ##.
    #..
  */

  public void calculateCubeNet_Test(){
    this.edges.put(new TriPoint(0,1,2),new TriPoint(2,0,0));
    this.edges.put(new TriPoint(0,1,3),new TriPoint(3,0,0));
    this.edges.put(new TriPoint(0,2,0),new TriPoint(2,1,2));
    this.edges.put(new TriPoint(0,2,1),new TriPoint(1,1,2));
    this.edges.put(new TriPoint(0,2,3),new TriPoint(3,0,3));
    this.edges.put(new TriPoint(1,1,0),new TriPoint(0,2,3));
    this.edges.put(new TriPoint(1,1,2),new TriPoint(2,0,1));
    this.edges.put(new TriPoint(2,0,2),new TriPoint(0,1,0));
    this.edges.put(new TriPoint(2,0,3),new TriPoint(1,1,0));
    this.edges.put(new TriPoint(2,1,0),new TriPoint(0,2,2));
    this.edges.put(new TriPoint(2,1,1),new TriPoint(3,0,2));
    this.edges.put(new TriPoint(3,0,0),new TriPoint(2,1,3));
    this.edges.put(new TriPoint(3,0,1),new TriPoint(0,2,1));
    this.edges.put(new TriPoint(3,0,2),new TriPoint(0,1,1));

    this.edges.put(new TriPoint(0,1,0),new TriPoint(0,2,0));
    this.edges.put(new TriPoint(0,2,2),new TriPoint(0,1,2));
    this.edges.put(new TriPoint(0,1,1),new TriPoint(1,1,1));
    this.edges.put(new TriPoint(1,1,3),new TriPoint(0,1,3));
    this.edges.put(new TriPoint(1,1,1),new TriPoint(2,1,1));
    this.edges.put(new TriPoint(2,1,3),new TriPoint(1,1,3));
    this.edges.put(new TriPoint(2,0,0),new TriPoint(2,1,0));
    this.edges.put(new TriPoint(2,1,2),new TriPoint(2,0,2));
    this.edges.put(new TriPoint(2,0,1),new TriPoint(3,0,1));
    this.edges.put(new TriPoint(3,0,3),new TriPoint(2,0,3));
  }

}
