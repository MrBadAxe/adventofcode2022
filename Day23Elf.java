import java.util.List;

public class Day23Elf{

  private Point currentPos;
  private Point proposedMove;
  private int checkDir;

  public Day23Elf(Point startPos){
    this.currentPos = startPos;
    this.proposedMove = null;
    this.checkDir = 0;
  }

  public Point currentPos(){
    return this.currentPos;
  }
  public Point getProposedMove(){
    return this.proposedMove;
  }

  @Override
  public boolean equals(Object o){
    if(o == this){ return true; }
    if(!(o instanceof Day23Elf)){ return false; }
    Day23Elf other = (Day23Elf)o;
    return (this.currentPos().equals(other.currentPos()));
  }

  public String toString(){
    return this.currentPos().toString() + (this.getProposedMove() == null ? "" : (" prop " + this.getProposedMove().toString()));
  }

  public void propose(List<Day23Elf> elves){
    boolean canMoveNorthWest = !(elves.contains(new Day23Elf(new Point(this.currentPos().getX()-1, this.currentPos().getY()-1))));
    boolean canMoveNorth = !(elves.contains(new Day23Elf(new Point(this.currentPos().getX()-1, this.currentPos().getY()))));
    boolean canMoveNorthEast = !(elves.contains(new Day23Elf(new Point(this.currentPos().getX()-1, this.currentPos().getY()+1))));
    boolean canMoveWest = !(elves.contains(new Day23Elf(new Point(this.currentPos().getX(), this.currentPos().getY()-1))));
    boolean canMoveEast = !(elves.contains(new Day23Elf(new Point(this.currentPos().getX(), this.currentPos().getY()+1))));
    boolean canMoveSouthWest = !(elves.contains(new Day23Elf(new Point(this.currentPos().getX()+1, this.currentPos().getY()-1))));
    boolean canMoveSouth = !(elves.contains(new Day23Elf(new Point(this.currentPos().getX()+1, this.currentPos().getY()))));
    boolean canMoveSouthEast = !(elves.contains(new Day23Elf(new Point(this.currentPos().getX()+1, this.currentPos().getY()+1))));

    if(canMoveNorth && canMoveNorthEast && canMoveEast && canMoveSouthEast && canMoveSouth && canMoveSouthWest && canMoveWest && canMoveNorthWest){
      proposedMove = currentPos;
      return;
    }

    for(int k=0;k<4;k++){
      switch((checkDir+k)%4){
        case 0:
          if(canMoveNorthEast && canMoveNorth && canMoveNorthWest){
            proposedMove = new Point(this.currentPos().getX()-1, this.currentPos().getY());
            return;
          }
          break;
        case 1:
          if(canMoveSouthEast && canMoveSouth && canMoveSouthWest){
            proposedMove = new Point(this.currentPos().getX()+1, this.currentPos().getY());
            return;
          }
          break;
        case 2:
          if(canMoveNorthWest && canMoveWest && canMoveSouthWest){
            proposedMove = new Point(this.currentPos().getX(), this.currentPos().getY()-1);
            return;
          }
          break;
        case 3:
          if(canMoveNorthEast && canMoveEast && canMoveSouthEast){
            proposedMove = new Point(this.currentPos().getX(), this.currentPos().getY()+1);
            return;
          }
          break;
      }
    }
    proposedMove = currentPos;
    return;
  }

  private void clear(){
    this.proposedMove = null;
    this.checkDir = (this.checkDir+1)%4;
  }
  public void reject(){
    clear();
  }
  public void accept(){
    this.currentPos = this.proposedMove;
    clear();
  }
}
