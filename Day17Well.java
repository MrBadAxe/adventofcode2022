import java.util.Stack;

public class Day17Well{
  static final int WIDTH = 7;

  Stack<String> well;
  String jetPattern;
  int jetIndex;
  int pieceIndex;
  int activePieceRow;
  int[] pieceHeights = {1,3,3,4,2};

  public Day17Well(String str){
    jetPattern = str;
    jetIndex = 0;
    activePieceRow = -1;

    well = new Stack<String>();
  }

  public String toString(){
    return String.join("\n",well);
  }

  public void addPiece(){
    well.push(".......");
    well.push(".......");
    well.push(".......");

    activePieceRow = well.size();
    switch(pieceIndex%5){
      case 0:
        well.push("..@@@@.");
        break;
      case 1:
        well.push("...@...");
        well.push("..@@@..");
        well.push("...@...");
        break;
      case 2:
        well.push("..@@@..");
        well.push("....@..");
        well.push("....@..");
        break;
      case 3:
        well.push("..@....");
        well.push("..@....");
        well.push("..@....");
        well.push("..@....");
        break;
      case 4:
        well.push("..@@...");
        well.push("..@@...");
        break;
    }
    pieceIndex++;
  }

  private void settle(){
    char jetDir = jetPattern.charAt(jetIndex);
    char[][] window = new String[pieceHeights[(pieceIndex-1)%5]][WIDTH];
    switch(jetDir){
      case '<':
        break;
      case '>':
        break;
    }
    jetIndex++;
  }
}
