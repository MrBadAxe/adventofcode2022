import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Day16ValveGraph{
  private HashMap<String,Day16Valve> valveGraph;
  //private Day16Valve currentPos;
  //private int minutesRemaining;
  private int totalPressureReleased;
  private List<String> opened;
  private HashMap<String,Day16Agent> agents;
  private HashMap<String,HashMap<String,Integer>> precomputedDistances;

  public Day16ValveGraph(HashMap<String,Day16Valve> vg){
    this.valveGraph = vg;
    //this.currentPos = this.valveGraph.get(start);
    //this.minutesRemaining = timer;
    totalPressureReleased = 0;
    opened = new ArrayList<String>();
    agents = new HashMap<String,Day16Agent>();
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
  public List<String> getOpened(){
    return this.opened;
  }
  public String toString(){
    String z = this.getTotalPressureReleased() + " | " + String.join(" ",opened) + " | ";
    for(String a : agents.keySet()){
      z += a + "@" + agents.get(a).getCurrentPos().getName() + ":" + agents.get(a).getMinutesRemaining() + " ";
    }
    return z;
  }
  public void addAgent(String name, Day16Agent agent){
    this.agents.put(name,agent);
  }
  public Day16Agent getAgent(String name){
    return this.agents.get(name);
  }
  public HashMap<String,Day16Agent> getAllAgents(){
    return this.agents;
  }
  public boolean equals(Day16ValveGraph other){
    boolean z = true;
    /*
    for(Day16Agent a : this.getAllAgents().values()){
      if(!other.getAllAgents().values().contains(a)){
        z &= false;
      }
    }
    */
    return z && (this.getOpened().containsAll(other.getOpened())) && (this.getTotalPressureReleased() == other.getTotalPressureReleased());
  }

  public int getDistance(String from, String to){
    if(this.precomputedDistances == null){
      precomputedDistances = new HashMap<String,HashMap<String,Integer>>();
    }
    if(precomputedDistances.containsKey(from) && precomputedDistances.get(from).containsKey(to)){
      return precomputedDistances.get(from).get(to);
    }else{
      if(!precomputedDistances.containsKey(from)){
        precomputedDistances.put(from,new HashMap<String,Integer>());
      }
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
      precomputedDistances.get(from).put(to,distances.get(to));
      return distances.get(to);
    }
  }
  public Day16ValveGraph copy(){
    Day16ValveGraph z = new Day16ValveGraph(this.valveGraph);
    z.releasePressure(this.getTotalPressureReleased());
    z.getOpened().addAll(this.getOpened());
    for(String a : agents.keySet()){
      z.addAgent(a,new Day16Agent(agents.get(a).getCurrentPos(), agents.get(a).getMinutesRemaining()));
    }
    return z;
  }

  public Day16ValveGraph move(String agentName, String move){
    Day16ValveGraph z = this.copy();

    Day16Agent agent = z.getAgent(agentName);
    int dist = getDistance(agent.getCurrentPos().getName(),move);
    agent.setCurrentPos(valveGraph.get(move));
    agent.setMinutesRemaining(agent.getMinutesRemaining() - (dist+1));
    //System.out.println(z.getCurrentPos().toString());
    if(!z.isOpen(agent.getCurrentPos().getName())){
      z.open(agent.getCurrentPos().getName());
      z.releasePressure(agent.getCurrentPos().getFlowRate() * agent.getMinutesRemaining());
    }

    return z;
  }

}
