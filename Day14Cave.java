public class Day14Cave{
  private final int DEPTH;
  private final int WIDTH = 1000;
  private final boolean HAS_FLOOR;
  private final int SAND_SPAWN_X = 500;
  private final int SAND_SPAWN_Y = 0;

  private char[][] cave;

  public Day14Cave(int depth, boolean floor){
    HAS_FLOOR = floor;
    DEPTH = (HAS_FLOOR ? depth + 2 : depth);
    cave = new char[WIDTH][DEPTH];
    for(int y=0;y<DEPTH;y++){
      for(int x=0;x<WIDTH;x++){
        this.set(x,y,'.');
      }
    }
    if(HAS_FLOOR){
      this.placeCaveWall(0,DEPTH-1,WIDTH-1,DEPTH-1);
    }
  }

  public char get(int x, int y){
    return cave[x][y];
  }
  public void set(int x, int y, char val){
    cave[x][y] = val;
  }
  public void placeCaveWall(int x1, int y1, int x2, int y2){
    //System.out.println("(" + x1 + "," + y1 + ") -> (" + x2 + "," + y2 + ")");
    if(x1 == x2){
      if(y1 == y2){
        this.set(x1,y1,'#');
      }else{
        for(int y=Math.min(y1,y2);y<=Math.max(y1,y2);y++){
          this.set(x1,y,'#');
        }
      }
    }else{
      if(y1 == y2){
        for(int x=Math.min(x1,x2);x<=Math.max(x1,x2);x++){
          this.set(x,y1,'#');
        }
      }else{
        return;
      }
    }
  }
  public boolean placeSand(){
    int x = 500;
    int y = 0;
    if(cave[x][y] != '.'){return false;}

    boolean rest = false;
    while(!rest){
      //System.out.println("(" + x + "," + y + ")");
      if(y + 1 > cave[0].length-1){
        if(HAS_FLOOR){
          rest = true;
          cave[x][y] = 'O';
        }else{
          return false;
        }
      }else if(cave[x][y+1] == '.'){
        y++;
      }else if(x - 1 < 0){
        return false;
      }else if(cave[x-1][y+1] == '.'){
        y++;
        x--;
      }else if(x + 1 > cave.length-1){
        return false;
      }else if(cave[x+1][y+1] == '.'){
        y++;
        x++;
      }else{
        rest = true;
        cave[x][y] = 'O';
      }
    }
    //System.out.println("");
    return rest;
  }
  public String toString(int leftEdge, int rightEdge){
    String z = "";
    for(int y=0;y<DEPTH;y++){
      for(int x=leftEdge;x<=rightEdge;x++){
        z += this.get(x,y);
      }
      z += "\n";
    }
    return z;
  }
}
