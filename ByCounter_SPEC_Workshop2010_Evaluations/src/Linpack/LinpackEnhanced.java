package Linpack;

import java.util.Arrays;

/**
 * TODO use java.util.logging
 * TODO measurement infrastructure added by Michael Kuperberg
 * Modified 3/3/97 by David M. Doolin (dmd) doolin@cs.utk.edu
 * Fixed error in matgen() method. Added some comments.
 *
 * Modified 1/22/97 by Paul McMahan mcmahan@cs.utk.edu
 * Added more MacOS options to form.
 *
 * Optimized by Jonathan Hardwick (jch@cs.cmu.edu), 3/28/96
 * Compare to Linkpack.java.
 * Optimizations performed:
 * - added "final" modifier to performance-critical methods.
 * - changed lines of the form "a[i] = a[i] + x" to "a[i] += x".
 * - minimized array references using common subexpression elimination.
 * - eliminated unused variables.
 * - undid an unrolled loop.
 * - added temporary 1D arrays to hold frequently-used columns of 2D arrays.
 * - wrote my own abs() method
 * See http://www.cs.cmu.edu/~jch/java/linpack.html for more details.
 *
 * Ported to Java by Reed Wade  (wade@cs.utk.edu) 2/96
 *
 * Translated to C by Bonnie Toy 5/88
 * (modified on 2/25/94  to fix a problem with daxpy  for
 * unequal increments or equal increments not equal to 1.
 * Jack Dongarra)
 */
public class LinpackEnhanced {

	/**
	 * Self-describing
	 */
	private static int _MK__nrOfExecutions = 1;//more than JIT compilation threshold - unless overwritten
	
	/**
	 * Self-describing
	 */
	private static int _MK__nrOfWarmups = 0;

	/**
	 * Using nanoTime...
	 */
	private static boolean _MK__selfMeasure = true;
	
	/**
	 * There are some issues with the implementation...
	 */
	private static boolean _MK_compareToPrevious = false;
	
	/**
	 * Incremented and printed to prevent unwanted dead code elimination
	 */
	public static double _MK_deadCodeEliminationPreventionVariable = 0.0D;
	
	/**
	 * Used to check whether each run of the algorithm returns the same result
	 */
	private static double[] _MK_last_a_firstPart = null;
	
	/**
	 * Used to check whether each run of the algorithm returns the same result
	 */
	private static double[] _MK_last_b = null;
	
	static long _MK_mainStartCurrentTimerMs;
	
	/**
	 * In the original single-run writeup, the results were printed. 
	 * Now, however, this can be disabled to prevent System.out use 
	 * from distorting the results.
	 */
	private static boolean _MK_origLinpackPrintingPerformMeasurement=false;
	
	/**
	 * In the original single-run writeup, the results were printed. 
	 * Now, however, this can be disabled to prevent System.out use 
	 * from distorting the results.
	 */
	private static boolean _MK_origPrintingDoPrintLinpackResult=false;
	
	/**
	 * In the original single-run writeup, the results were printed. 
	 * Now, however, this can be disabled to prevent System.out use 
	 * from distorting the results. This field is for measuring 
	 * the duration of printing. 
	 */
	private static long _MK_origPrintingStartTime=0L;
	
	/**
	 * In the original single-run writeup, the results were printed. 
	 * Now, however, this can be disabled to prevent System.out use 
	 * from distorting the results. This field is for measuring 
	 * the duration of printing. 
	 */
	private static long _MK_origPrintingStopTime=0L;
	static Long _MK_realAvg;
	static Long _MK_realFirst;
	static Long _MK_realMax;
static java.util.List<Long> _MK_realMeasurements = new java.util.ArrayList<Long>();
	//	java.util.SortedMap<Long, Integer> _MK_warmupFrequencies = new java.util.TreeMap<Long, Integer>();
//	java.util.SortedMap<Long, Integer> _MK_realFrequencies = new java.util.TreeMap<Long, Integer>();
	static Long _MK_realMed;
	static Long _MK_realMin;
	
	static double _MK_realSum = 0.0D;
	
	static long _MK_start;
	static long _MK_stop;
	static Long _MK_warmupAvg;
	static Long _MK_warmupFirst = -1L;
	static Long _MK_warmupMax;
	static java.util.List<Long> _MK_warmupMeasurements = new java.util.ArrayList<Long>();
	static Long _MK_warmupMed;
	static Long _MK_warmupMin;
	static double _MK_warmupSum = 0.0D;

	/**
	 * Heavily adapted to enable measurements
	 * @param args
	 */
	public static void main(String[] args) {
		_MK_mainStartCurrentTimerMs = System.currentTimeMillis();
		
		LinpackEnhanced l = new LinpackEnhanced();
		
		if (_MK__nrOfWarmups < 0) {
			System.err.println("Warmup nr negative: " + _MK__nrOfWarmups
					+ ", returning");
			return;
		}
		if (_MK__nrOfExecutions < 1) {
			System.err.println("Number of executions <1: " + _MK__nrOfExecutions
					+ ", returning");
			return;
		}
		if (!_MK__selfMeasure) {
			System.out.println("Running " + _MK__nrOfExecutions
					+ " times without self-measuring, no warmup (ignored even if specified)");
			for (int i = 0; i < _MK__nrOfExecutions; i++) {
				_MK_deadCodeEliminationPreventionVariable += l.run_benchmark(); 
			}
		} else {
			System.out.println("Running " + _MK__nrOfExecutions
					+ " times (after "+_MK__nrOfWarmups+" warmups) with self-measuring");
			if (_MK__nrOfExecutions == 1 && _MK__nrOfWarmups == 0) {
				_MK_start = System.nanoTime();
				_MK_deadCodeEliminationPreventionVariable += l.run_benchmark();
				_MK_stop = System.nanoTime();
				System.out.println("Linpack single measurement: " + (_MK_stop - _MK_start) + " ns");

			} else {
				if (_MK__nrOfWarmups > 1) {
					_MK_start = System.nanoTime();
					_MK_deadCodeEliminationPreventionVariable += l.run_benchmark();
					_MK_stop = System.nanoTime();
					_MK_warmupMeasurements.add(_MK_stop - _MK_start);
					_MK_warmupSum += (_MK_stop - _MK_start);
					_MK_warmupFirst = (_MK_stop - _MK_start);
					for (int i = 1; i < _MK__nrOfWarmups; i++) {
						_MK_start = System.nanoTime();
						_MK_deadCodeEliminationPreventionVariable += l.run_benchmark();
						_MK_stop = System.nanoTime();
						_MK_warmupMeasurements.add(_MK_stop - _MK_start);
						_MK_warmupSum += (_MK_stop - _MK_start);
					}
				}
				//first measurement
				_MK_start = System.nanoTime();
				_MK_deadCodeEliminationPreventionVariable += l.run_benchmark();
				_MK_stop = System.nanoTime();
				_MK_realMeasurements.add(_MK_stop - _MK_start);
				_MK_realSum += (_MK_stop - _MK_start);
				_MK_realFirst = (_MK_stop - _MK_start);//this is important!
				//remaining measurements, if any
				for (int i = 1; i < _MK__nrOfExecutions; i++) {
					_MK_start = System.nanoTime();
					_MK_deadCodeEliminationPreventionVariable += l.run_benchmark();
					_MK_stop = System.nanoTime();
					_MK_realMeasurements.add(_MK_stop - _MK_start);
					_MK_realSum += (_MK_stop - _MK_start);
				}
				if (_MK_warmupMeasurements.size() > 0) {
					java.util.Collections.sort(_MK_warmupMeasurements);
					_MK_warmupAvg = new Double(_MK_warmupSum / _MK__nrOfWarmups).longValue();
					_MK_warmupMax = _MK_warmupMeasurements.get(_MK_warmupMeasurements
							.size() - 1);
					_MK_warmupMed = _MK_warmupMeasurements.get(_MK_warmupMeasurements
							.size() / 2);
					_MK_warmupMin = _MK_warmupMeasurements.get(0);
					System.out.println("Warmup: first=" + _MK_warmupFirst
							+ ", min=" + _MK_warmupMin + ", med=" + _MK_warmupMed
							+ ", avg=" + _MK_warmupAvg + ", max=" + _MK_warmupMax);
				}
				if (_MK_realMeasurements.size() > 0) {
					java.util.Collections.sort(_MK_realMeasurements);
					_MK_realAvg = new Double(_MK_realSum / _MK__nrOfExecutions)
							.longValue();
					_MK_realMax = _MK_realMeasurements.get(_MK_realMeasurements.size() - 1);
					_MK_realMed = _MK_realMeasurements.get(_MK_realMeasurements.size() / 2);
					_MK_realMin = _MK_realMeasurements.get(0);
					System.out.println("Real: first=" + _MK_realFirst + ", min="
							+ _MK_realMin + ", med=" + _MK_realMed + ", avg=" + _MK_realAvg
							+ ", max=" + _MK_realMax);
				}
			}
		}
		System.out.println("Main duration: "+(System.currentTimeMillis()-_MK_mainStartCurrentTimerMs)+
				"ms, deadCodeEliminationPreventionVariable: "+_MK_deadCodeEliminationPreventionVariable);
	}
	
	/**
	 * Part of the original implementation
	 */
	double second_orig = -1;

	/**
	 * MK performance-variant, but insignificantly (?)
	 * @param d
	 * @return
	 */
	final double abs(double d) {
		return (d >= 0) ? d : -d;
	}

	/*
	 * constant times a vector plus a vector. jack dongarra, linpack, 3/11/78.
	 */
	final void daxpy(int n, double da, double dx[], int dx_off, int incx,
			double dy[], int dy_off, int incy) {
		int i, ix, iy;

		if ((n > 0) && (da != 0)) {
			if (incx != 1 || incy != 1) {

				// code for unequal increments or equal increments not equal to
				// 1

				ix = 0;
				iy = 0;
				if (incx < 0)
					ix = (-n + 1) * incx;
				if (incy < 0)
					iy = (-n + 1) * incy;
				for (i = 0; i < n; i++) {
					dy[iy + dy_off] += da * dx[ix + dx_off];
					ix += incx;
					iy += incy;
				}
				return;
			} else {

				// code for both increments equal to 1

				for (i = 0; i < n; i++)
					dy[i + dy_off] += da * dx[i + dx_off];
			}
		}
	}

	/*
	 * forms the dot product of two vectors. jack dongarra, linpack, 3/11/78.
	 */
	final double ddot(int n, double dx[], int dx_off, int incx, double dy[],
			int dy_off, int incy) {
		double dtemp;
		int i, ix, iy;

		dtemp = 0;

		if (n > 0) {

			if (incx != 1 || incy != 1) {

				// code for unequal increments or equal increments not equal to
				// 1

				ix = 0;
				iy = 0;
				if (incx < 0)
					ix = (-n + 1) * incx;
				if (incy < 0)
					iy = (-n + 1) * incy;
				for (i = 0; i < n; i++) {
					dtemp += dx[ix + dx_off] * dy[iy + dy_off];
					ix += incx;
					iy += incy;
				}
			} else {

				// code for both increments equal to 1

				for (i = 0; i < n; i++)
					dtemp += dx[i + dx_off] * dy[i + dy_off];
			}
		}
		return (dtemp);
	}

	/*
	 * dgefa factors a double precision matrix by gaussian elimination.
	 * 
	 * dgefa is usually called by dgeco, but it can be called directly with a
	 * saving in time if rcond is not needed. (time for dgeco) = (1 + 9/n)*(time
	 * for dgefa) .
	 * 
	 * on entry
	 * 
	 * a double precision[n][lda] the matrix to be factored.
	 * 
	 * lda integer the leading dimension of the array a .
	 * 
	 * n integer the order of the matrix a .
	 * 
	 * on return
	 * 
	 * a an upper triangular matrix and the multipliers which were used to
	 * obtain it. the factorization can be written a = l*u where l is a product
	 * of permutation and unit lower triangular matrices and u is upper
	 * triangular.
	 * 
	 * ipvt integer[n] an integer vector of pivot indices.
	 * 
	 * info integer = 0 normal value. = k if u[k][k] .eq. 0.0 . this is not an
	 * error condition for this subroutine, but it does indicate that dgesl or
	 * dgedi will divide by zero if called. use rcond in dgeco for a reliable
	 * indication of singularity.
	 * 
	 * linpack. this version dated 08/14/78. cleve moler, university of new
	 * mexico, argonne national lab.
	 * 
	 * functions
	 * 
	 * blas daxpy,dscal,idamax
	 */
	final int dgefa(double a[][], int lda, int n, int ipvt[]) {
		double[] col_k, col_j;
		double t;
		int j, k, kp1, l, nm1;
		int info;

		// gaussian elimination with partial pivoting

		info = 0;
		nm1 = n - 1;
		if (nm1 >= 0) {
			for (k = 0; k < nm1; k++) {
				col_k = a[k];
				kp1 = k + 1;

				// find l = pivot index

				l = idamax(n - k, col_k, k, 1) + k;
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

					t = -1.0 / col_k[k];
					dscal(n - (kp1), t, col_k, kp1, 1);

					// row elimination with column indexing

					for (j = kp1; j < n; j++) {
						col_j = a[j];
						t = col_j[l];
						if (l != k) {
							col_j[l] = col_j[k];
							col_j[k] = t;
						}
						daxpy(n - (kp1), t, col_k, kp1, 1, col_j, kp1, 1);
					}
				} else {
					info = k;
				}
			}
		}
		ipvt[n - 1] = n - 1;
		if (a[(n - 1)][(n - 1)] == 0)
			info = n - 1;

		return info;
	}

	/*
	 * dgesl solves the double precision system a * x = b or trans(a) * x = b
	 * using the factors computed by dgeco or dgefa.
	 * 
	 * on entry
	 * 
	 * a double precision[n][lda] the output from dgeco or dgefa.
	 * 
	 * lda integer the leading dimension of the array a .
	 * 
	 * n integer the order of the matrix a .
	 * 
	 * ipvt integer[n] the pivot vector from dgeco or dgefa.
	 * 
	 * b double precision[n] the right hand side vector.
	 * 
	 * job integer = 0 to solve a*x = b , = nonzero to solve trans(a)*x = b
	 * where trans(a) is the transpose.
	 * 
	 * on return
	 * 
	 * b the solution vector x .
	 * 
	 * error condition
	 * 
	 * a division by zero will occur if the input factor contains a zero on the
	 * diagonal. technically this indicates singularity but it is often caused
	 * by improper arguments or improper setting of lda . it will not occur if
	 * the subroutines are called correctly and if dgeco has set rcond .gt. 0.0
	 * or dgefa has set info .eq. 0 .
	 * 
	 * to compute inverse(a) * c where c is a matrix with p columns
	 * dgeco(a,lda,n,ipvt,rcond,z) if (!rcond is too small){ for (j=0,j<p,j++)
	 * dgesl(a,lda,n,ipvt,c[j][0],0); }
	 * 
	 * linpack. this version dated 08/14/78 . cleve moler, university of new
	 * mexico, argonne national lab.
	 * 
	 * functions
	 * 
	 * blas daxpy,ddot
	 */
	final void dgesl(double a[][], int lda, int n, int ipvt[], double b[],
			int job) {
		double t;
		int k, kb, l, nm1, kp1;

		nm1 = n - 1;
		if (job == 0) {

			// job = 0 , solve a * x = b. first solve l*y = b

			if (nm1 >= 1) {
				for (k = 0; k < nm1; k++) {
					l = ipvt[k];
					t = b[l];
					if (l != k) {
						b[l] = b[k];
						b[k] = t;
					}
					kp1 = k + 1;
					daxpy(n - (kp1), t, a[k], kp1, 1, b, kp1, 1);
				}
			}

			// now solve u*x = y

			for (kb = 0; kb < n; kb++) {
				k = n - (kb + 1);
				b[k] /= a[k][k];
				t = -b[k];
				daxpy(k, t, a[k], 0, 1, b, 0, 1);
			}
		} else {

			// job = nonzero, solve trans(a) * x = b. first solve trans(u)*y = b

			for (k = 0; k < n; k++) {
				t = ddot(k, a[k], 0, 1, b, 0, 1);
				b[k] = (b[k] - t) / a[k][k];
			}

			// now solve trans(l)*x = y

			if (nm1 >= 1) {
				for (kb = 1; kb < nm1; kb++) {
					k = n - (kb + 1);
					kp1 = k + 1;
					b[k] += ddot(n - (kp1), a[k], kp1, 1, b, kp1, 1);
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

	/*
	 * purpose: multiply matrix m times vector x and add the result to vector y.
	 * 
	 * parameters:
	 * 
	 * n1 integer, number of elements in vector y, and number of rows in matrix
	 * m
	 * 
	 * y double [n1], vector of length n1 to which is added the product m*x
	 * 
	 * n2 integer, number of elements in vector x, and number of columns in
	 * matrix m
	 * 
	 * ldm integer, leading dimension of array m
	 * 
	 * x double [n2], vector of length n2
	 * 
	 * m double [ldm][n2], matrix of n1 rows and n2 columns
	 */
	final void dmxpy(int n1, double y[], int n2, int ldm, double x[],
			double m[][]) {
		int j, i;

		// cleanup odd vector
		for (j = 0; j < n2; j++) {
			for (i = 0; i < n1; i++) {
				y[i] += x[j] * m[j][i];
			}
		}
	}

	/*
	 * scales a vector by a constant. jack dongarra, linpack, 3/11/78.
	 */
	final void dscal(int n, double da, double dx[], int dx_off, int incx) {
		int i, nincx;

		if (n > 0) {
			if (incx != 1) {

				// code for increment not equal to 1

				nincx = n * incx;
				for (i = 0; i < nincx; i += incx)
					dx[i + dx_off] *= da;
			} else {

				// code for increment equal to 1

				for (i = 0; i < n; i++)
					dx[i + dx_off] *= da;
			}
		}
	}

	/*
	 * estimate unit roundoff in quantities of size x.
	 * 
	 * this program should function properly on all systems satisfying the
	 * following two assumptions, 1. the base used in representing dfloating
	 * point numbers is not a power of three. 2. the quantity a in statement 10
	 * is represented to the accuracy used in dfloating point variables that are
	 * stored in memory. the statement number 10 and the go to 10 are intended
	 * to force optimizing compilers to generate code satisfying assumption 2.
	 * under these assumptions, it should be true that, a is not exactly equal
	 * to four-thirds, b has a zero for its last bit or digit, c is not exactly
	 * equal to one, eps measures the separation of 1.0 from the next larger
	 * dfloating point number. the developers of eispack would appreciate being
	 * informed about any systems where these assumptions do not hold.
	 * 
	 * **************************************************************** this
	 * routine is one of the auxiliary routines used by eispack iii to avoid
	 * machine dependencies.
	 * ****************************************************************
	 * 
	 * this version dated 4/6/83.
	 */
	final double epslon(double x) {
		double a, b, c, eps;

		a = 4.0e0 / 3.0e0;
		eps = 0;
		while (eps == 0) {
			b = a - 1.0;
			c = b + b + b;
			eps = abs(c - 1.0);
		}
		return (eps * abs(x));
	}

	/*
	 * finds the index of element having max. absolute value. jack dongarra,
	 * linpack, 3/11/78.
	 */
	final int idamax(int n, double dx[], int dx_off, int incx) {
		double dmax, dtemp;
		int i, ix, itemp = 0;

		if (n < 1) {
			itemp = -1;
		} else if (n == 1) {
			itemp = 0;
		} else if (incx != 1) {

			// code for increment not equal to 1

			dmax = abs(dx[0 + dx_off]);
			ix = 1 + incx;
			for (i = 1; i < n; i++) {
				dtemp = abs(dx[ix + dx_off]);
				if (dtemp > dmax) {
					itemp = i;
					dmax = dtemp;
				}
				ix += incx;
			}
		} else {

			// code for increment equal to 1

			itemp = 0;
			dmax = abs(dx[0 + dx_off]);
			for (i = 1; i < n; i++) {
				dtemp = abs(dx[i + dx_off]);
				if (dtemp > dmax) {
					itemp = i;
					dmax = dtemp;
				}
			}
		}
		return (itemp);
	}

	final double matgen(double a[][], int lda, int n, double b[]) {
		double norma;
		int init, i, j;

		init = 1325;
		norma = 0.0;
		/*
		 * Next two for() statements switched. Solver wants matrix in column
		 * order. --dmd 3/3/97
		 */
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				init = 3125 * init % 65536;
				a[j][i] = (init - 32768.0) / 16384.0;
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

	// MK benchmarkable
	/**
	 * MK Modified to return double - dead code prevention (instead of result printing, which can now be deactivated)
	 * @return
	 */
	@SuppressWarnings("unused")
	public double run_benchmark() {
		// long start = 0L;//MK TODO
		// long stop = 0L;//MK TODO
		// start = System.nanoTime();//MK TODO
		double mflops_result = 0.0;
		double residn_result = 0.0;
		double time_result = 0.0;
		double eps_result = 0.0;

		//solving a*b=x...
		double a[][] = new double[200][201];//MK why is the matrix not quadratic?
		double b[] = new double[200];
		double x[] = new double[200];
		double cray, ops, total, norma, normx;
		double resid, time;
		double kf;
		int n, i, ntimes, info, lda, ldaa, kflops;
		int ipvt[] = new int[200];

		// double mflops_result;
		// double residn_result;
		// double time_result;
		// double eps_result;

		lda = 201;
		ldaa = 200;
		cray = .056;
		n = 100;

		ops = (2.0e0 * (n * n * n)) / 3.0 + 2.0 * (n * n);

		norma = matgen(a, lda, n, b);//initialises a and b
		if(_MK_compareToPrevious){
			if(_MK_last_a_firstPart!=null){
				if(Arrays.equals(a[0], _MK_last_a_firstPart)){
					System.out.println("first line of a remains the same");
				}else{
					System.out.println("first line of a changes");
				}
			}
			_MK_last_a_firstPart = a[0];
			
			if(_MK_last_b != null){
				if(Arrays.equals(b, _MK_last_b)){
					System.out.println("b remains the same");
				}else{
//					System.out.println("b changed from "+Arrays.toString(_MK_last_b)+" \n to "+Arrays.toString(b));
					System.out.println("b changed, e.g. first element from "+_MK_last_b[0]+" \n to "+b[0]);
//					
				}
			}
			_MK_last_b = b;
			System.out.println("_MK_last_b[0] changed to "+_MK_last_b[0]+" (not: Double.MIN_VALUE is "+Double.MIN_VALUE+")");//TODO this does not work... :-(
		}
		time = second();
		info = dgefa(a, lda, n, ipvt);
		dgesl(a, lda, n, ipvt, b, 0);
		total = second() - time;

		for (i = 0; i < n; i++) {
			x[i] = b[i];
		}
		norma = matgen(a, lda, n, b);
		for (i = 0; i < n; i++) {
			b[i] = -b[i];
		}
		dmxpy(n, b, n, lda, x, a);
		resid = 0.0;
		normx = 0.0;
		for (i = 0; i < n; i++) {
			resid = (resid > abs(b[i])) ? resid : abs(b[i]);
			normx = (normx > abs(x[i])) ? normx : abs(x[i]);
		}

		eps_result = epslon((double) 1.0);
		/*
		 * 
		 * residn_result = resid/( n*norma*normx*eps_result ); time_result =
		 * total; mflops_result = ops/(1.0e6*total);
		 * 
		 * return ("Mflops/s: " + mflops_result + "  Time: " + time_result +
		 * " secs" + "  Norm Res: " + residn_result + "  Precision: " +
		 * eps_result);
		 */
		residn_result = resid / (n * norma * normx * eps_result);
		residn_result += 0.005; // for rounding
		residn_result = (int) (residn_result * 100);
		residn_result /= 100;

		time_result = total;
		time_result += 0.005; // for rounding
		time_result = (int) (time_result * 100);
		time_result /= 100;

		mflops_result = ops / (1.0e6 * total);
		mflops_result += 0.0005; // for rounding
		mflops_result = (int) (mflops_result * 1000);
		mflops_result /= 1000;

		if(_MK_origPrintingDoPrintLinpackResult){
			if(_MK_origLinpackPrintingPerformMeasurement){
				_MK_origPrintingStartTime = System.nanoTime();
			}
			System.out.println("Mflops/s: " + mflops_result + "  Time: " +
					time_result + " secs" + "  Norm Res: " + residn_result +
					"  Precision: " + eps_result);
			if(_MK_origLinpackPrintingPerformMeasurement){
				_MK_origPrintingStopTime = System.nanoTime();
				System.out.println("Original printing of results: "+(_MK_origPrintingStopTime-_MK_origPrintingStartTime)
						+" ns");
			}
		}
		 
		// stop = System.nanoTime(); //MK TODO
		// long ns = stop-start;
		// long mus = Math.round((double)ns/1000);
		// long ms = Math.round((double)mus/1000);
		// long s = Math.round((double)ms/1000);
		// System.out.println("Linpack.run_benchmark executed in "
		// +ns+" ns = "+mus+" mus = "+ms+" ms = "+s+" s.");//MK TODO
		
		return mflops_result;
	}

	/**
	 * Original code
	 * @return
	 */
	double second() {
		if (this.second_orig == -1) {
			this.second_orig = System.currentTimeMillis();
		}
		return (System.currentTimeMillis() - this.second_orig) / 1000;
	}

}
