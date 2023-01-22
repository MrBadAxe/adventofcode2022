public class Day24BlizzardField{

  public static final int FACING_RIGHT = 0;
  public static final int FACING_DOWN = 1;
  public static final int FACING_LEFT = 2;
  public static final int FACING_UP = 3;

  public static final int CELL_WALL = 255;

  private char[] DISPLAY = {'.','>','v','2','<','2','2','3','^','2','2','3','2','3','3','4'};

  private int HEIGHT;
  private int WIDTH;
  private int[][] field;

  public Day24BlizzardField(int rows, int cols){
    HEIGHT = rows;
    WIDTH = cols;
    field = new int[HEIGHT][WIDTH];
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        field[row][col] = CELL_WALL;
      }
    }
  }

  public int get(int row, int col){
    return field[row][col];
  }
  public void set(int row, int col, int val){
    field[row][col] = val;
  }
  public int rows(){
    return HEIGHT;
  }
  public int cols(){
    return WIDTH;
  }

  public String toString(){
    String z = "";
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        z += (field[row][col] == Day24BlizzardField.CELL_WALL ? '#' : DISPLAY[field[row][col]]);
      }
      z += "\n";
    }
    return z;
  }

  public Day24BlizzardField step(){
    Day24BlizzardField z = new Day24BlizzardField(this.HEIGHT,this.WIDTH);

    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        if(row == 0 || row == (HEIGHT-1) || col == 0 || col == (WIDTH-1)){
          z.set(row,col,this.get(row,col));
        }else{
          int north = this.get(row-1,col) == Day24BlizzardField.CELL_WALL ? this.get(HEIGHT-2,col) : this.get(row-1,col);
          int south = this.get(row+1,col) == Day24BlizzardField.CELL_WALL ? this.get(1,col) : this.get(row+1,col);
          int west = this.get(row,col-1) == Day24BlizzardField.CELL_WALL ? this.get(row,WIDTH-2) : this.get(row,col-1);
          int east = this.get(row,col+1) == Day24BlizzardField.CELL_WALL ? this.get(row,1) : this.get(row,col+1);

          int moves = (west & (1 << Day24BlizzardField.FACING_RIGHT)) +
                      (north & (1 << Day24BlizzardField.FACING_DOWN)) +
                      (east & (1 << Day24BlizzardField.FACING_LEFT)) +
                      (south & (1 << Day24BlizzardField.FACING_UP));

          z.set(row,col,moves);
        }
      }
    }
    return z;
  }
}
