public class Day16Agent{
  private Day16Valve currentPos;
  private int minutesRemaining;

  public Day16Agent(Day16Valve start, int timer){
    this.currentPos = start;
    this.minutesRemaining = timer;
  }

  public Day16Valve getCurrentPos(){
    return this.currentPos;
  }
  public void setCurrentPos(Day16Valve valve){
    this.currentPos = valve;
  }
  public int getMinutesRemaining(){
    return this.minutesRemaining;
  }
  public void setMinutesRemaining(int remaining){
    this.minutesRemaining = remaining;
  }

  public boolean equals(Day16Agent other){
    return (this.getMinutesRemaining() == other.getMinutesRemaining()) && (this.getCurrentPos().getName().equals(other.getCurrentPos().getName()));
  }
}
