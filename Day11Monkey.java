import java.util.LinkedList;

public class Day11Monkey{
  private LinkedList<Long> items;
  private int operationAX2;
  private int operationBX;
  private int operationC;
  private int testDivisor;
  private int recipientPass;
  private int recipientFail;
  private boolean extraWorry;

  private long inspectCount;

  public Day11Monkey(int ax2, int bx, int c, int test, int pass, int fail, boolean worry){
    items = new LinkedList<Long>();
    this.operationAX2 = ax2;
    this.operationBX = bx;
    this.operationC = c;
    this.testDivisor = test;
    this.recipientPass = pass;
    this.recipientFail = fail;
    this.extraWorry = worry;

    this.inspectCount = 0;
  }

  public int getTestDivisor(){
    return this.testDivisor;
  }

  public void give(long i){
    items.add(i);
  }

  public void inspectAll(Day11Monkey[] monkeys){
    while(items.size() > 0){
      inspect(monkeys);
    }
  }

  public void inspect(Day11Monkey[] monkeys){
    long lcm = 1;
    for(int k=0;k<monkeys.length;k++){
      if(monkeys[k] != null){ lcm *= monkeys[k].getTestDivisor(); }
    }
    long item = items.removeFirst();
    long newItem = ((operationAX2 * item * item) + (operationBX * item) + (operationC));
    newItem = (extraWorry ? (Long.remainderUnsigned(newItem,lcm)) : newItem / 3);
    if(newItem % (long)testDivisor == 0){
      monkeys[this.recipientPass].give(newItem);
    }else{
      monkeys[this.recipientFail].give(newItem);
    }
    inspectCount++;
  }

  public String describeBehavior(){
    String z = "op: " + this.operationAX2 + "x^2 + " + this.operationBX + "x + " + this.operationC + "; ";
    z += "test: " + this.testDivisor + "; ";
    z += "pass: " + this.recipientPass + "; ";
    z += "fail: " + this.recipientFail + "; ";
    return z;
  }

  public String describeInventory(){
    String z = "has: ";
    for(long item : items){
      z += item + " ";
    }
    return z;
  }

  public long getInspectCount(){
    return this.inspectCount;
  }

}
