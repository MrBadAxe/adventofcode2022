import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Day16ValveGraph{
  private HashMap<String,Day16Valve> valveGraph;
  private Day16Valve currentPos;
  private int minutesRemaining;
  private int totalPressureReleased;
  private Set<String> opened;

  public Day16ValveGraph(HashMap<String,Day16Valve> vg, String start, int timer){
    this.valveGraph = vg;
    this.currentPos = this.valveGraph.get(start);
    this.minutesRemaining = timer;
    totalPressureReleased = 0;
    opened = new HashSet<String>();
  }

  public int getMinutesRemaining(){
    return this.minutesRemaining;
  }
  public Day16Valve getCurrentPos(){
    return this.currentPos;
  }
  public void setCurrentPos(Day16Valve valve){
    this.currentPos = valve;
  }
  public int getTotalPressureReleased(){
    return this.totalPressureReleased;
  }
  public void releasePressure(int pressure){
    this.totalPressureReleased += pressure;
  }
  public HashMap<String,Day16Valve> getValveGraph(){
    return this.valveGraph;
  }
  public boolean isOpen(String str){
    return this.opened.contains(str);
  }
  public void open(String str){
    this.opened.add(str);
  }
  public Set<String> getOpened(){
    return this.opened;
  }
  public String toString(){
    return this.getCurrentPos().getName() + " " + this.getMinutesRemaining() + "s " + this.getTotalPressureReleased() + "pr";
  }

  public int getDistance(String from, String to){
    HashMap<String,Integer> distances = new HashMap<String,Integer>();
    int currentDistance = 0;
    distances.put(from,currentDistance);
    List<String> nextValves = new ArrayList<String>();

    while(!distances.containsKey(to)){
      currentDistance++;
      for(String str : distances.keySet()){
        Day16Valve valveFrom = this.valveGraph.get(str);
        for(Day16Valve valveTo : valveFrom.getLinks()){
          if(!distances.containsKey(valveTo.getName())){
            nextValves.add(valveTo.getName());
          }
        }
      }
      while(nextValves.size() > 0){
        distances.put(nextValves.remove(0),currentDistance);
      }
    }
    return distances.get(to);
  }

  public Day16ValveGraph move(String move){
    Day16ValveGraph z = new Day16ValveGraph(this.valveGraph, this.getCurrentPos().getName(), this.getMinutesRemaining());
    z.releasePressure(this.getTotalPressureReleased());
    z.getOpened().addAll(this.getOpened());

    z.minutesRemaining -= (getDistance(currentPos.getName(),move) + 1);
    z.setCurrentPos(valveGraph.get(move));
    //System.out.println(z.getCurrentPos().toString());
    if(!z.isOpen(z.getCurrentPos().getName())){
      z.open(z.getCurrentPos().getName());
      z.releasePressure(z.getCurrentPos().getFlowRate() * z.getMinutesRemaining());
    }

    return z;
  }

}
