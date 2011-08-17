/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - Version 2.0             *
*                                                                         *
*                            produced by                                  *
*                                                                         *
*                  Java Grande Benchmarking Project                       *
*                                                                         *
*                                at                                       *
*                                                                         *
*                Edinburgh Parallel Computing Centre                      *
*                                                                         * 
*                email: epcc-javagrande@epcc.ed.ac.uk                     *
*                                                                         *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/


package jgfutil;

public class JGFTimer {

  public long calls; 
  public String name; 
  private boolean on; 
  public double opcount; 
  public String opname; 
  public int size = -1;
  
  private long start_time;
  public double time; 

  public JGFTimer(String name){
    this(name,""); 
  }

  public JGFTimer(String name, String opname){
    this.name = name;
    this.opname = opname;
    reset(); 
  }

  public JGFTimer(String name, String opname, int size){
    this.name = name;
    this.opname = opname;
    this.size = size;
    reset();
  }



  public void addops(double count){
    opcount += count;
  }


  public void longprint(){
      System.out.println("Timer            Calls         Time(s)       Performance("+opname+"/s)");   
     System.out.println(name + "           " + calls +    "           "  +  time + "        " + this.perf());
  }

  public double perf(){
    return opcount / time; 
  } 

  public void print(){
    if (opname.equals("")) {
      System.out.println(name + "   " + time + " (s)");
    }
    else {

      switch(size) {
      case 0:
      System.out.println(name + ":SizeA" + "\t" + time + " (s) \t " + (float)this.perf() + "\t"
                        + " ("+opname+"/s)");
      break;
      case 1:
      System.out.println(name + ":SizeB" + "\t" + time + " (s) \t " + (float)this.perf() + "\t"
                        + " ("+opname+"/s)");
      break;
      case 2:
      System.out.println(name + ":SizeC" + "\t" + time + " (s) \t " + (float)this.perf() + "\t"
                        + " ("+opname+"/s)");
      break;
      default:
      System.out.println(name + "\t" + time + " (s) \t " + (float)this.perf() + "\t"
                        + " ("+opname+"/s)");
      break;
      }

    }
  }

  public void printperf(){

     String name;
     name = this.name; 

     // pad name to 40 characters
     while ( name.length() < 40 ) name = name + " "; 
     
     System.out.println(name + "\t" + (float)this.perf() + "\t"
			+ " ("+opname+"/s)");  
  }

  public void reset(){
    time = 0.0; 
    calls = 0; 
    opcount = 0; 
    on = false;
  }

  public void start(){
    if (on) System.out.println("Warning timer " + name + " was already turned on");
    on = true; 
    start_time = System.currentTimeMillis();
  }


  public void stop(){
    time += (double) (System.currentTimeMillis()-start_time) / 1000.;
    if (!on) System.out.println("Warning timer " + name + " wasn't turned on");
    calls++;
    on = false;  
  }

}
