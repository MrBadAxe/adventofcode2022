import java.util.HashMap;

public class Day07Directory{
  private Day07Directory parent;
  private HashMap<String,Day07Directory> dirs;
  private HashMap<String,Integer> files;

  public Day07Directory(){
    dirs = new HashMap<String,Day07Directory>();
    files = new HashMap<String,Integer>();
    parent = null;
  }

  public Day07Directory(Day07Directory dir){
    this();
    parent = dir;
  }

  public void addFile(String name, int size){
    files.put(name,size);
  }

  public void mkDir(String name){
    Day07Directory subdir = new Day07Directory(this);
    dirs.put(name,subdir);
  }

  public Day07Directory getParent(){
    return parent;
  }

  public HashMap<String,Day07Directory> getDirList(){
    return dirs;
  }

  public Day07Directory getDir(String name){
    return dirs.get(name);
  }

  public int getDirSize(){
    int z = 0;
    for(String file : files.keySet()){
      z += files.get(file);
    }
    for(String dir : dirs.keySet()){
      z += dirs.get(dir).getDirSize();
    }
    return z;
  }
}
