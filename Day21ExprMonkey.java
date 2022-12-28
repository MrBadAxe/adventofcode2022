import java.util.HashMap;

public class Day21ExprMonkey implements Day21Monkey{
  public String operand1;
  public char operator;
  public String operand2;

  public Day21ExprMonkey(String m1, char c, String m2){
    this.operand1 = m1;
    this.operator = c;
    this.operand2 = m2;
  }

  public long yell(HashMap<String,Day21Monkey> monkeys){
    Day21Monkey m1 = monkeys.get(operand1);
    long i1 = m1.yell(monkeys);

    Day21Monkey m2 = monkeys.get(operand2);
    long i2 = m2.yell(monkeys);

    switch(this.operator){
      case '+': return (i1 + i2);
      case '-': return (i1 - i2);
      case '*': return (i1 * i2);
      case '/': return (i1 / i2);
      case '=':
        return (i1 - i2);
      default:  return -1;
    }
  }

  public String toString(){
    return this.operand1 + " " + this.operator + " " + this.operand2;
  }
}
