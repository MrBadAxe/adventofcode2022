public class Point{
  private final int x;
  private final int y;

  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }

  public Point(){
    this(0,0);
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  @Override
  public boolean equals(Object o){
    if(o == this){ return true; }
    if(!(o instanceof Point)){ return false; }
    Point other = (Point)o;
    return (this.getX() == other.getX() && this.getY() == other.getY());
  }

  public String toString(){
    return "(" + this.getX() + "," + this.getY() + ")";
  }
}
