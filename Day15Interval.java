public class Day15Interval{
  private final int lower;
  private final int upper;

  public Day15Interval(int lb, int ub){
    this.lower = lb;
    this.upper = ub;
  }

  public int getLower(){
    return this.lower;
  }
  public int getUpper(){
    return this.upper;
  }

  public boolean overlaps(Day15Interval other){
    return !(other.getLower() > this.getUpper() || other.getUpper() < this.getLower());
  }

  public boolean isAdjacentTo(Day15Interval other){
    if(this.overlaps(other)){
      return false;
    }else if(this.getLower() > other.getLower()){
      return other.getUpper()+1==this.getLower();
    }else{
      return this.getUpper()+1==other.getLower();
    }
  }

  public Day15Interval merge(Day15Interval other){
    if(!(this.overlaps(other) || this.isAdjacentTo(other))){
      return null;
    }else{
      return new Day15Interval(Math.min(this.getLower(),other.getLower()),Math.max(this.getUpper(),other.getUpper()));
    }
  }

  public String toString(){
    return "[" + this.getLower() + "," + this.getUpper() + "]";
  }

  public int size(){
    return (this.getUpper() - this.getLower()) + 1;
  }
}
