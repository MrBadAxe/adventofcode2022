public class TriPoint{
  private final int x;
  private final int y;
  private final int z;

  public TriPoint(int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public TriPoint(){
    this(0,0,0);
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  public int getZ(){
    return this.z;
  }

  @Override
  public boolean equals(Object o){
    if(o == this){ return true; }
    if(!(o instanceof TriPoint)){ return false; }
    TriPoint other = (TriPoint)o;
    return (this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ());
  }

  public String toString(){
    return "(" + this.getX() + "," + this.getY() + "," + this.getZ() + ")";
  }
}
