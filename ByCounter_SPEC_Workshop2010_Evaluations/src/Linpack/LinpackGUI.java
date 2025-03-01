package Linpack;

/*
Modified 6/27/00 by Keith Seymour -- seymour@cs.utk.edu
-Increased problem size to 500x500
-Fixed matgen() random number generation.
-Updated event handling to 1.1 model.
-Modified deprecated method calls.
-Cleaned up code.
-Added PII/PIII/Win2000 options

Modified 3/3/97 by David M. Doolin (dmd) doolin@cs.utk.edu
Fixed error in matgen() method. Added some comments.

Modified 2/17/97 by Paul McMahan mcmahan@cs.utk.edu
and Shilpa Singhal singhal@cs.utk.edu
Added support for new cgi-script which handles
submissions

Modified 1/22/97 by Paul McMahan mcmahan@cs.utk.edu
Added more Mac processor options to form.

Optimized by Jonathan Hardwick (jch@cs.cmu.edu), 3/28/96
Compare to Linkpack.java.
Optimizations performed:
 - added "final" modifier to performance-critical methods.
 - changed lines of the form "a[i] = a[i] + x" to "a[i] += x".
 - minimized array references using common subexpression elimination.
 - eliminated unused variables.
 - undid an unrolled loop.
 - added temporary 1D arrays to hold frequently-used columns of 2D arrays.
 - wrote my own abs() method
See http://www.cs.cmu.edu/~jch/java/linpack.html for more details.


Ported to Java by Reed Wade  (wade@cs.utk.edu) 2/96
built using JDK 1.0 on solaris
using "javac -O Linpack.java"


Translated to C by Bonnie Toy 5/88
  (modified on 2/25/94  to fix a problem with daxpy  for
   unequal increments or equal increments not equal to 1.
     Jack Dongarra)

*/


import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Polygon;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;


class DataItem {
  double mflops;
  String label;
  int itemtype;
  String info;

  DataItem (double mflops, int itemtype, String info, String label) {
    this.mflops = mflops;
    this.label = label;
    this.itemtype = itemtype;
    this.info = info;
  }
}


@SuppressWarnings("serial")
public class LinpackGUI extends Applet implements ActionListener {

  String applet_version = "LinpackJavaV2.1";

  /* problem size = psize x psize */
  static final int psize = 500;

  Button doit_b;
  String doit_b_string = "Press To Run Benchmark (may take a few moments)";

  StatsGraph graph_c;

  Label mesg_l;
  Label os_l, cpu_l;
  Choice os_m, cpu_m;

  Label comments_l;
  TextField comments_e;
  Label name_l;
  TextField name_e;
  Label email_l;
  TextField email_e;

  Button submit_b;

  double mflops_result = 0.0;
  double residn_result = 0.0;
  double time_result = 0.0;
  double eps_result = 0.0;
  double total = 0.0;

  double second_orig = -1;

  final double abs (double d) {
    return (d >= 0) ? d : -d;
  }

  @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent evt) {
    Object source = evt.getSource();

    if (source==doit_b) {
      doit_b.setLabel("...running benchmark (" + psize +"x"+psize +")");
      run_benchmark(psize,psize*2);
      doit_b.setLabel(doit_b_string);
      submit_b.setEnabled(true);
      graph_c.setCurrentRun(mflops_result, residn_result, time_result, eps_result);
      graph_c.repaint();
    }
    else if (source==submit_b) {
      try {
        String OS = os_m.getSelectedItem();
        String CPU = cpu_m.getSelectedItem();

        if(OS.equals("Other"))
          OS = "";
        if (CPU.equals("Other"))
          CPU = "";

        String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

        Calendar c = Calendar.getInstance();
        String DATE =   c.get(Calendar.DAY_OF_MONTH) +" "+ 
                       months[c.get(Calendar.MONTH)] +" "+ 
                        c.get(Calendar.YEAR);

        String s = ("http://www.netlib.org/cgi-bin/linpackjava/linpackjava.pl?"
//          String s = ("http://netlib3.cs.utk.edu/cgi-bin/linpackjava/linpackjava.pl?"  
          + "email="
          + URLEncoder.encode(email_e.getText())
          + "&mflops=" + mflops_result
          + "&time=" + time_result
          + "&cpu="
          + URLEncoder.encode(cpu_m.getSelectedItem())
          + "&os="
          + URLEncoder.encode(os_m.getSelectedItem())
          + "&comments="
          + URLEncoder.encode(comments_e.getText())
          + "&name="
          + URLEncoder.encode(name_e.getText())
          + "&currtime=" + System.currentTimeMillis()
          + "&residn=" + residn_result
          + "&epsilon=" + eps_result
          + "&version=" + applet_version );
                                
        URL url = new URL(s);
        getAppletContext().showDocument(url);

      } catch (Exception e) {
        mesg_l.setText("!!submission failed for some reason");
      }
    }
    else {
      System.out.println("Unknown source.");
    }
  }
  /**
   * constant times a vector plus a vector.
   * jack dongarra, linpack, 3/11/78.
   **/

  final void daxpy( int n, double da, double dx[], int dx_off, int incx,
	      double dy[], int dy_off, int incy)
  {
    int i,ix,iy;

    if ((n > 0) && (da != 0)) {
      if (incx != 1 || incy != 1) {

	// code for unequal increments or equal increments not equal to 1

	ix = 0;
	iy = 0;
	if (incx < 0) ix = (-n+1)*incx;
	if (incy < 0) iy = (-n+1)*incy;
	for (i = 0;i < n; i++) {
	  dy[iy +dy_off] += da*dx[ix +dx_off];
	  ix += incx;
	  iy += incy;
	}
	return;
      } else {

	// code for both increments equal to 1

	for (i=0; i < n; i++)
	  dy[i +dy_off] += da*dx[i +dx_off];
      }
    }
  }
  
  /**
   * forms the dot product of two vectors.
   * jack dongarra, linpack, 3/11/78.
   **/

  final double ddot( int n, double dx[], int dx_off, int incx, double dy[],
	       int dy_off, int incy)
  {
    double dtemp;
    int i,ix,iy;

    dtemp = 0;

    if (n > 0) {
      
      if (incx != 1 || incy != 1) {

	// code for unequal increments or equal increments not equal to 1

	ix = 0;
	iy = 0;
	if (incx < 0) ix = (-n+1)*incx;
	if (incy < 0) iy = (-n+1)*incy;
	for (i = 0;i < n; i++) {
	  dtemp += dx[ix +dx_off]*dy[iy +dy_off];
	  ix += incx;
	  iy += incy;
	}
      } else {

	// code for both increments equal to 1
	
	for (i=0;i < n; i++)
	  dtemp += dx[i +dx_off]*dy[i +dy_off];
      }
    }
    return(dtemp);
  }
  

  
  final int dgefa( double a[][], int lda, int n, int ipvt[])
  {
    double[] col_k, col_j;
    double t;
    int j,k,kp1,l,nm1;
    int info;
    
    // gaussian elimination with partial pivoting
    
    info = 0;
    nm1 = n - 1;
    if (nm1 >=  0) {
      for (k = 0; k < nm1; k++) {
	col_k = a[k];
	kp1 = k + 1;
	
	// find l = pivot index
	
	l = idamax(n-k,col_k,k,1) + k;
	ipvt[k] = l;
	
	// zero pivot implies this column already triangularized
	
	if (col_k[l] != 0) {
	  
	  // interchange if necessary
	  
	  if (l != k) {
	    t = col_k[l];
	    col_k[l] = col_k[k];
	    col_k[k] = t;
	  }
	  
	  // compute multipliers
	  
	  t = -1.0/col_k[k];
	  dscal(n-(kp1),t,col_k,kp1,1);
	  
	  // row elimination with column indexing
	  
	  for (j = kp1; j < n; j++) {
	    col_j = a[j];
	    t = col_j[l];
	    if (l != k) {
	      col_j[l] = col_j[k];
	      col_j[k] = t;
	    }
	    daxpy(n-(kp1),t,col_k,kp1,1,
		  col_j,kp1,1);
	  }
	}
	else {
	  info = k;
	}
      }
    }

    ipvt[n-1] = n-1;
    if (a[(n-1)][(n-1)] == 0) info = n-1;
    
    return info;
  }
  
  /*
    dgefa factors a double precision matrix by gaussian elimination.
    
    dgefa is usually called by dgeco, but it can be called
    directly with a saving in time if  rcond  is not needed.
    (time for dgeco) = (1 + 9/n)*(time for dgefa) .
    
    on entry
    
    a       double precision[n][lda]
    the matrix to be factored.
    
    lda     integer
    the leading dimension of the array  a .
    
    n       integer
    the order of the matrix  a .
    
    on return
    
    a       an upper triangular matrix and the multipliers
    which were used to obtain it.
    the factorization can be written  a = l*u  where
    l  is a product of permutation and unit lower
    triangular matrices and  u  is upper triangular.
    
    ipvt    integer[n]
    an integer vector of pivot indices.
    
    info    integer
    = 0  normal value.
    = k  if  u[k][k] .eq. 0.0 .  this is not an error
    condition for this subroutine, but it does
    indicate that dgesl or dgedi will divide by zero
    if called.  use  rcond  in dgeco for a reliable
    indication of singularity.
    
    linpack. this version dated 08/14/78.
    cleve moler, university of new mexico, argonne national lab.
    
    functions
    
    blas daxpy,dscal,idamax
  */

  /**
   * dgesl solves the double precision system
   * a * x = b  or  trans(a) * x = b
   * using the factors computed by dgeco or dgefa.
   *
   * on entry
   *
   * a       double precision[n][lda]
   * the output from dgeco or dgefa.
   *
   * lda     integer
   * the leading dimension of the array  a .
   *
   * n       integer
   * the order of the matrix  a .
   *
   * ipvt    integer[n]
   * the pivot vector from dgeco or dgefa.
   *
   * b       double precision[n]
   * the right hand side vector.
   *
   * job     integer
   * = 0         to solve  a*x = b ,
   * = nonzero   to solve  trans(a)*x = b  where
   * trans(a)  is the transpose.
   *
   * on return
   *
   * b       the solution vector  x .
   *
   * error condition
   *
   * a division by zero will occur if the input factor contains a
   * zero on the diagonal.  technically this indicates singularity
   * but it is often caused by improper arguments or improper
   * setting of lda .  it will not occur if the subroutines are
   * called correctly and if dgeco has set rcond .gt. 0.0
   * or dgefa has set info .eq. 0 .
   *
   * to compute  inverse(a) * c  where  c  is a matrix
   * with  p  columns
   * dgeco(a,lda,n,ipvt,rcond,z)
   * if (!rcond is too small){
   * for (j=0,j<p,j++)
   * dgesl(a,lda,n,ipvt,c[j][0],0);
   * }
   * 
   * linpack. this version dated 08/14/78 .
   * cleve moler, university of new mexico, argonne national lab.
   * 
   * functions
   * 
   * blas daxpy,ddot
   **/

  final void dgesl( double a[][], int lda, int n, int ipvt[], double b[], int job)
  {
    double t;
    int k,kb,l,nm1,kp1;

    nm1 = n - 1;
    if (job == 0) {

      // job = 0 , solve  a * x = b.  first solve  l*y = b

      if (nm1 >= 1) {
	for (k = 0; k < nm1; k++) {
	  l = ipvt[k];
	  t = b[l];
	  if (l != k){
	    b[l] = b[k];
	    b[k] = t;
	  }
	  kp1 = k + 1;
	  daxpy(n-(kp1),t,a[k],kp1,1,b,kp1,1);
	}
      }

      // now solve  u*x = y

      for (kb = 0; kb < n; kb++) {
	k = n - (kb + 1);
	b[k] /= a[k][k];
	t = -b[k];
	daxpy(k,t,a[k],0,1,b,0,1);
      }
    }
    else {

      // job = nonzero, solve  trans(a) * x = b.  first solve  trans(u)*y = b

      for (k = 0; k < n; k++) {
	t = ddot(k,a[k],0,1,b,0,1);
	b[k] = (b[k] - t)/a[k][k];
      }

      // now solve trans(l)*x = y 

      if (nm1 >= 1) {
	//for (kb = 1; kb < nm1; kb++) {
	for (kb = 0; kb < nm1; kb++) {
	  k = n - (kb+1);
	  kp1 = k + 1;
	  b[k] += ddot(n-(kp1),a[k],kp1,1,b,kp1,1);
	  l = ipvt[k];
	  if (l != k) {
	    t = b[l];
	    b[l] = b[k];
	    b[k] = t;
	  }
	}
      }
    }
  }

  
  
  /**
   * purpose:
   * multiply matrix m times vector x and add the result to vector y.
   * 
   * parameters:
   * 
   * n1 integer, number of elements in vector y, and number of rows in
   * matrix m
   * 
   * y double [n1], vector of length n1 to which is added
   * the product m*x
   * 
   * n2 integer, number of elements in vector x, and number of columns
   * in matrix m
   * 
   * ldm integer, leading dimension of array m
   * 
   * x double [n2], vector of length n2
   * 
   * m double [ldm][n2], matrix of n1 rows and n2 columns
   **/

  final void dmxpy ( int n1, double y[], int n2, int ldm, double x[], double m[][])
  {
    int j,i;

    // cleanup odd vector
    for (j = 0; j < n2; j++) {
      for (i = 0; i < n1; i++) {
	y[i] += x[j]*m[j][i];
      }
    }
  }



  /**
   * scales a vector by a constant.
   * jack dongarra, linpack, 3/11/78.
   **/

  final void dscal( int n, double da, double dx[], int dx_off, int incx)
  {
    int i,nincx;

    if (n > 0) {
      if (incx != 1) {

	// code for increment not equal to 1

	nincx = n*incx;
	for (i = 0; i < nincx; i += incx)
	  dx[i +dx_off] *= da;
      } else {

	// code for increment equal to 1

	for (i = 0; i < n; i++)
	  dx[i +dx_off] *= da;
      }
    }
  }



  /**
   * estimate unit roundoff in quantities of size x.
   * 
   * this program should function properly on all systems
   * satisfying the following two assumptions,
   * 1.  the base used in representing dfloating point
   * numbers is not a power of three.
   * 2.  the quantity  a  in statement 10 is represented to
   * the accuracy used in dfloating point variables
   * that are stored in memory.
   * the statement number 10 and the go to 10 are intended to
   * force optimizing compilers to generate code satisfying
   * assumption 2.
   * under these assumptions, it should be true that,
   * a  is not exactly equal to four-thirds,
   * b  has a zero for its last bit or digit,
   * c  is not exactly equal to one,
   * eps  measures the separation of 1.0 from
   * the next larger dfloating point number.
   * the developers of eispack would appreciate being informed
   * about any systems where these assumptions do not hold.
   * 
   * *****************************************************************
   * this routine is one of the auxiliary routines used by eispack iii
   * to avoid machine dependencies.
   * *****************************************************************
   * 
   *   this version dated 4/6/83.
   **/

  final double epslon (double x)
  {
    double a,b,c,eps;

    a = 4.0e0/3.0e0;
    eps = 0;
    while (eps == 0) {
      b = a - 1.0;
      c = b + b + b;
      eps = abs(c-1.0);
    }
    return(eps*abs(x));
  }

  /**
   * finds the index of element having max. absolute value.
   * jack dongarra, linpack, 3/11/78.
   **/

  final int idamax( int n, double dx[], int dx_off, int incx)
  {
    double dmax, dtemp;
    int i, ix, itemp=0;

    if (n < 1) {
      itemp = -1;
    } else if (n ==1) {
      itemp = 0;
    } else if (incx != 1) {

      // code for increment not equal to 1

      dmax = (dx[dx_off] < 0.0) ? -dx[dx_off]: dx[dx_off];
      ix = 1 + incx;
      for (i = 0; i < n; i++) {
	dtemp = (dx[ix + dx_off] < 0.0) ? -dx[ix + dx_off]: dx[ix + dx_off];
	if (dtemp > dmax)  {
	  itemp = i;
	  dmax = dtemp;
	}
	ix += incx;
      }
    } else {

      // code for increment equal to 1

      itemp = 0;
      dmax = (dx[dx_off] < 0.0)? -dx[dx_off] : dx[dx_off];
      for (i = 0; i < n; i++) {
	dtemp = (dx[i + dx_off] < 0.0) ? -dx[i+dx_off]: dx[i+dx_off];
	if (dtemp > dmax) {
	  itemp = i;
	  dmax = dtemp;
	}
      }
    }
    return (itemp);
  }
  
  public void init () {
    setLayout(new BorderLayout());

    Panel c1 = new Panel();
    c1.setLayout(new BorderLayout());

    this.doit_b = new Button(this.doit_b_string);
    this.doit_b.addActionListener(this);

    c1.add("North", this.doit_b);
    this.doit_b.setForeground(Color.blue);


    this.graph_c = new StatsGraph();
    c1.add("Center", this.graph_c);
    this.graph_c.setSize(getSize().width, getSize().height);

    add("Center", c1);

    Panel c2 = new Panel();
    c2.setLayout(new GridLayout(6,1));

    this.mesg_l = new Label("If you would like to submit this timing fill in the boxes and hit Submit", Label.CENTER);
    c2.add(this.mesg_l);

    Panel c2_2 = new Panel();
    this.os_l = new Label("  Operating System:");
    c2_2.add(this.os_l);
    this.os_m = new Choice();
    c2_2.add(this.os_m);

    this.os_m.addItem("Other");
    this.os_m.addItem("Digital Unix (OSF)");
    this.os_m.addItem("FreeBSD");
    this.os_m.addItem("IRIX");
    this.os_m.addItem("SunOS 4x");
    this.os_m.addItem("Solaris 2x");
    this.os_m.addItem("Linux");
    this.os_m.addItem("Windows95/98");
    this.os_m.addItem("WindowsNT");
    this.os_m.addItem("Windows2000");
    this.os_m.addItem("Mac OS");

    this.cpu_l = new Label("  CPU:");
    c2_2.add(this.cpu_l);
    this.cpu_m = new Choice();
    c2_2.add(this.cpu_m);

    this.cpu_m.addItem("Other");
    this.cpu_m.addItem("Alpha");
    this.cpu_m.addItem("Intel 386");
    this.cpu_m.addItem("Intel 486");
    this.cpu_m.addItem("Intel Pentium");
    this.cpu_m.addItem("Intel P6");
    this.cpu_m.addItem("Intel PII");
    this.cpu_m.addItem("Intel PIII");
    this.cpu_m.addItem("Mac");
    this.cpu_m.addItem("MIPS");
    this.cpu_m.addItem("Motorola 68K");
    this.cpu_m.addItem("PowerPC 601");
    this.cpu_m.addItem("PowerPC 603e");
    this.cpu_m.addItem("PowerPC 604");
    this.cpu_m.addItem("PowerPC 604e");
    this.cpu_m.addItem("Sparc1");
    this.cpu_m.addItem("Sparc2");
    this.cpu_m.addItem("SparcIPX");
    this.cpu_m.addItem("Sparc5");
    this.cpu_m.addItem("Sparc10");
    this.cpu_m.addItem("Sparc20");
    this.cpu_m.addItem("UltraSparc");
    c2.add(c2_2);

    Panel c2_3 = new Panel();
    this.comments_l = new Label("Details:");
    c2_3.add(this.comments_l);
    this.comments_e = new TextField(40);
    c2_3.add(this.comments_e);
    c2.add(c2_3);

    Panel c2_4 = new Panel();
    this.name_l = new Label("Your Name:");
    c2_4.add(name_l);
    name_e = new TextField(35);
    c2_4.add(name_e);
    c2.add(c2_4);

    Panel c2_5 = new Panel();
    email_l = new Label("Email (required for submissions):");
    c2_5.add(email_l);
    email_e = new TextField(25);
    c2_5.add(email_e);
    c2.add(c2_5);

    submit_b = new Button("Submit This Run");
    submit_b.addActionListener(this);
    submit_b.setEnabled(false);
    c2.add(submit_b);

    add("South", c2);

    graph_c.load_graph();
  }
  
  final double matgen (double a[][], int lda, int n, double b[])
  {
    Random gen;
    double norma;
    int init, i, j;
    
    init = 1325;
    norma = 0.0;

    gen = new Random();
    gen.setSeed(init);

    /*  Next two for() statements switched.  Solver wants
     *  matrix in column order. --dmd 3/3/97
     */
    for (i = 0; i < n; i++) {
      for (j = 0; j < n; j++) {
	a[j][i] = gen.nextDouble() - .5;
	norma = (a[j][i] > norma) ? a[j][i] : norma;
      }
    }

    for (i = 0; i < n; i++) {
      b[i] = 0.0;
    }

    for (j = 0; j < n; j++) {
      for (i = 0; i < n; i++) {
	b[i] += a[j][i];
      }
    }
    
    return norma;
  }

  public void run_benchmark (int n, int ldaa)
  {
    int lda = ldaa+1;
    double a[][] = new double[ldaa][lda];
    double b[] = new double[ldaa];
    double x[] = new double[ldaa];
    double cray,ops,norma,normx;
    double resid,time;
    double kf;
    int i,ntimes,info,kflops;
    int ipvt[] = new int[ldaa];
    
    cray = .056;
    
    ops = (2.0e0*(n*n*n))/3.0 + 2.0*(n*n);
    
    /* Norm a == max element. */
    norma = matgen(a,lda,n,b);

    time = second();

    /* Factor a.  */
    info = dgefa(a,lda,n,ipvt);

    /* Solve ax=b. */
    dgesl(a,lda,n,ipvt,b,0);

    total = second() - time;
    
    for (i = 0; i < n; i++) {
      x[i] = b[i];
    }

    norma = matgen(a,lda,n,b);

    for (i = 0; i < n; i++) {
      b[i] = -b[i];
    }

    dmxpy(n,b,n,lda,x,a);

    resid = 0.0;
    normx = 0.0;

    for (i = 0; i < n; i++) {
      resid = (resid > abs(b[i])) ? resid : abs(b[i]);
      normx = (normx > abs(x[i])) ? normx : abs(x[i]);
    }
    
    eps_result = epslon((double)1.0);

    residn_result = resid/( n*norma*normx*eps_result );
    residn_result += 0.005; // for rounding
    residn_result = (int)(residn_result*100);
    residn_result /= 100;

    time_result = total;
    time_result += 0.005; // for rounding
    time_result = (int)(time_result*100);
    time_result /= 100;

    mflops_result = ops/(1.0e6*total);
    mflops_result += 0.0005; // for rounding
    mflops_result = (int)(mflops_result*1000);
    mflops_result /= 1000;
  }


  double second()
  {
    if (second_orig==-1) {
      second_orig = System.currentTimeMillis();
    }
    return (System.currentTimeMillis() - second_orig)/1000;
  }
}

@SuppressWarnings({"static-access", "serial",
  "unchecked"})
class StatsGraph extends Canvas {

  double max_mflops = 0.0;

Vector data_list = new Vector(10);

  boolean couldnt_load = false;

  double mflops_result = 0.0;
  double residn_result = 0.0;
  double time_result = 0.0;
  double eps_result = 0.0;

  String dataURL = "http://www.netlib.org/benchmark/linpackjava/linpackData";
void drawStringRising(Graphics g, int rise, String s, int x, int y) {
    // for each char : draw, rise

    char chars[] = s.toCharArray();

    String _s;

    FontMetrics fm = g.getFontMetrics();
    int l=s.length();

    for (int i=0; i<l; i++) {
      _s = String.valueOf(chars[i]);
      g.drawString(_s, x,y);
      x += fm.stringWidth(_s) +1;
      y -= rise;
    }
  }

  //    String dataURL = "http://netlib3.cs.utk.edu/benchmark/linpackjava/linpackData";
void load_graph() {
    /* open the linpackData file */
    InputStream is;

    try {
      is = new URL(dataURL).openStream();
    } catch (Exception e) {
      couldnt_load = true;
      System.out.println(e.getMessage());
      return;
    }

    StreamTokenizer st = new StreamTokenizer(new InputStreamReader(is));

    /* allow various commenting methods */
    st.commentChar('#');
    st.slashStarComments(true);
    st.slashSlashComments(true);

    st.quoteChar('"');

    st.parseNumbers(); // TT_NUMBER , nval

    try {
      while (st.TT_EOF != st.nextToken()) {
        double mflops = 0.0;
        String label = "";
        String info = "";
        int itemtype = 0;
        DataItem item;

        // first item label
        while ('"'!=st.ttype && st.TT_EOF != st.nextToken())
          /*spin*/;

        if ('"'==st.ttype)
          label = st.sval;

        // second item itemtype
        while (st.TT_NUMBER!=st.ttype && st.TT_EOF != st.nextToken())
          /*spin*/;

        if (st.TT_NUMBER==st.ttype)
          itemtype = (int)st.nval;

        // third item info
        while ('"'!=st.ttype && st.TT_EOF != st.nextToken())
          /*spin*/;

        if ('"'==st.ttype)
          info = st.sval;

        // second fourth mflops
        while (st.TT_NUMBER!=st.ttype && st.TT_EOF != st.nextToken())
          /*spin*/;

        if (st.TT_NUMBER==st.ttype) {
          mflops = st.nval;
          if (mflops > max_mflops)
            max_mflops = mflops;
          item = new DataItem(mflops, itemtype, info, label);
          data_list.addElement(item);
        }
      }
    } catch (Exception e) {
      couldnt_load = true;
      return;
    }

    try { 
      is.close();
    } catch (Exception e) {
      couldnt_load = true;
      return;
    }
  }

  Polygon make_diamond(int x, int y) {
    Polygon p = new Polygon();
    p.addPoint(x,y+6);
    p.addPoint(x+6,y);
    p.addPoint(x,y-6);
    p.addPoint(x-6,y);

    return p;
  }

  public void paint(Graphics g) {

    Font font = g.getFont();	// save the default font
    Font minifont = new Font(font.getName(), font.getStyle(), 8);	// smaller

    int pad=20;

    if (mflops_result > max_mflops)
      max_mflops = mflops_result;

    // clear background
    g.setColor(getBackground());
    g.fillRect(1, 1, getSize().width - 2, getSize().height - 2);

    g.setColor(getForeground());
    if (couldnt_load) {
      g.drawString("Error: couldn't load Linpack data file", 10,50);
      return;
    }

    DataItem item;
    int i;

    // pad is size of border around bars area, same on all 3 sides
    // 0 element is not displayed, it is the scale (max value)

    int x, y2;
    int w,h;	// width and height excluding pad

    w = getSize().width - pad*2;
    h = getSize().height- pad*2;

    for (i=1; i<data_list.size(); i++) {
      item = (DataItem)data_list.elementAt(i);
      x = ( w * (i-1)/(data_list.size()-1) ) +pad;
      y2 = (int)(h - (h * item.mflops / max_mflops) +pad);

      g.setColor(getForeground());
      g.drawLine(x, h+pad, x, y2);
      g.drawString(item.label, x-5, h+pad+15);
      g.setFont(minifont);
      drawStringRising(g, 1, ""+item.mflops+" / "+item.info, x-10, y2-10);
      g.setFont(font);

      if (item.itemtype==1)
        g.setColor(Color.red);
      else if (item.itemtype==2)
        g.setColor(Color.green);
      else if (item.itemtype==3)
        g.setColor(Color.orange);
      else if (item.itemtype==4)
        g.setColor(Color.blue);
      else if (item.itemtype==5)
        g.setColor(Color.pink);

      g.fillPolygon(make_diamond(x,y2));
    }

    if (mflops_result!=0.0) {
      g.setColor(Color.red);
      x = w+pad;
      y2 = (int)(h - (h * mflops_result / max_mflops) +pad);
      g.drawLine(x, h+pad, x, y2);
      g.drawString("this", w+pad-5, h+pad+15);
      g.fillPolygon(make_diamond(x,y2));
      g.drawString(""+mflops_result, x-5, y2-10);
      g.setColor(Color.blue);

      String s = "Mflop/s: "+mflops_result
         +"  Time: "+time_result+" secs"
         +"  Norm Res: "+residn_result
         +"  Precision: "+eps_result;

      g.drawString(s, (getSize().width - g.getFontMetrics().stringWidth(s)) /2, 15);

      if (residn_result>100) {
        g.setColor(Color.yellow);
        g.drawString("Warning: there appears to be a problem with", 30, 30);
        g.drawString("the floating point arithmetic on this machine!!", 30, 50);
      }
    }
  }

  void setCurrentRun( double mflops_result, double residn_result, 
    double time_result, double eps_result)
  {
    this.mflops_result = mflops_result;
    this.residn_result = residn_result;
    this.time_result = time_result;
    this.eps_result = eps_result;
  }
}
