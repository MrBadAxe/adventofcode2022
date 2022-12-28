import java.util.HashMap;

public class Day21ConstMonkey implements Day21Monkey{
  private long value;
  public Day21ConstMonkey(long v){
    this.value = v;
  }

  public long yell(HashMap<String,Day21Monkey> monkeys){
    //System.out.println(this.toString());
    return this.value;
  }

  public String toString(){
    return Long.toString(this.value);
  }
}
