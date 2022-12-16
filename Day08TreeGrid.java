public class Day08TreeGrid{
  private final int WIDTH;
  private final int HEIGHT;
  private int[][] treeHeights;

  public Day08TreeGrid(int width, int height){
    WIDTH = width;
    HEIGHT = height;
    treeHeights = new int[WIDTH][HEIGHT];
    for(int x=0;x<WIDTH;x++){
      for(int y=0;y<HEIGHT;y++){
        treeHeights[x][y] = 0;
      }
    }
  }

  public int get(int x, int y){
    return treeHeights[x][y];
  }
  public void set(int x, int y, int h){
    treeHeights[x][y] = h;
  }
  public int getWidth(){
    return WIDTH;
  }
  public int getHeight(){
    return HEIGHT;
  }

  public boolean isVisible(int x, int y){
    boolean west = true;
    for(int cx=x-1;cx>=0;cx--){
      west &= (get(cx,y) < get(x,y));
    }
    boolean east = true;
    for(int cx=x+1;cx<WIDTH;cx++){
      east &= (get(cx,y) < get(x,y));
    }

    boolean north = true;
    for(int cy=y-1;cy>=0;cy--){
      north &= (get(x,cy) < get(x,y));
    }
    boolean south = true;
    for(int cy=y+1;cy<HEIGHT;cy++){
      south &= (get(x,cy) < get(x,y));
    }
    return (west || east || north || south);
  }

  public int getScenicScore(int x, int y){
    int west = (x == 0 ? 0 : 1);
    while(x-west > 0 && get(x-west,y) < get(x,y)){
      west++;
    }
    int east = (x == getWidth()-1 ? 0 : 1);
    while(x+east < getWidth()-1 && get(x+east,y) < get(x,y)){
      east++;
    }
    int north = (y == 0 ? 0 : 1);
    while(y-north > 0 && get(x,y-north) < get(x,y)){
      north++;
    }
    int south = (y == getHeight()-1 ? 0 : 1);
    while(y+south < getHeight()-1 && get(x,y+south) < get(x,y)){
      south++;
    }
    return (west * east * north * south);
  }

}
