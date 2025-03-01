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


package JavaGrande.jgfutil;



public class JGFTimer {

  public String name; 
  public String opname; 
  public double time; 
  public double opcount; 
  public long calls; 
  public int size = -1;
  
  private long start_time;
  private boolean on; 

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

  public JGFTimer(String name){
    this(name,""); 
  }



  public void start(){
    if (this.on) System.out.println("Warning timer " + this.name + " was already turned on");
    this.on = true; 
    this.start_time = System.currentTimeMillis();
  }


  public void stop(){
    this.time += (double) (System.currentTimeMillis()-this.start_time) / 1000.;
    if (!this.on) System.out.println("Warning timer " + this.name + " wasn't turned on");
    this.calls++;
    this.on = false;  
  }

  public void addops(double count){
    this.opcount += count;
  } 

  public void reset(){
    this.time = 0.0; 
    this.calls = 0; 
    this.opcount = 0; 
    this.on = false;
  }

  public double perf(){
    return this.opcount / this.time; 
  }

  public void longprint(){
      System.out.println("Timer            Calls         Time(s)       Performance("+this.opname+"/s)");   
     System.out.println(this.name + "           " + this.calls +    "           "  +  this.time + "        " + this.perf());
  }

  public void print(){
    if (this.opname.equals("")) {
      System.out.println(this.name + "   " + this.time + " (s)");
    }
    else {

      switch(this.size) {
      case 0:
      System.out.println(this.name + ":SizeA" + "\t" + this.time + " (s) \t " + (float)this.perf() + "\t"
                        + " ("+this.opname+"/s)");
      break;
      case 1:
      System.out.println(this.name + ":SizeB" + "\t" + this.time + " (s) \t " + (float)this.perf() + "\t"
                        + " ("+this.opname+"/s)");
      break;
      case 2:
      System.out.println(this.name + ":SizeC" + "\t" + this.time + " (s) \t " + (float)this.perf() + "\t"
                        + " ("+this.opname+"/s)");
      break;
      default:
      System.out.println(this.name + "\t" + this.time + " (s) \t " + (float)this.perf() + "\t"
                        + " ("+this.opname+"/s)");
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
			+ " ("+this.opname+"/s)");  
  }

}
