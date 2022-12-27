public class LongPoint{
  private final long x;
  private final long y;

  public LongPoint(long x, long y){
    this.x = x;
    this.y = y;
  }

  public LongPoint(){
    this(0,0);
  }

  public long getX(){
    return this.x;
  }

  public long getY(){
    return this.y;
  }

  @Override
  public boolean equals(Object o){
    if(o == this){ return true; }
    if(!(o instanceof LongPoint)){ return false; }
    LongPoint other = (LongPoint)o;
    return (this.getX() == other.getX() && this.getY() == other.getY());
  }

  public String toString(){
    return "(" + this.getX() + "," + this.getY() + ")";
  }
}
