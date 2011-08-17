/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * Copyright (c) 2006 Sun Microsystems, Inc. All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks.compiler.compiler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kuperberg.Helper;

import spec.benchmarks.compiler.Compiler;
import spec.benchmarks.compiler.MainBase;
import spec.benchmarks.compiler.Util;
import spec.harness.StopBenchmarkException;
import spec.harness.results.BenchmarkResult;

public class Main extends spec.benchmarks.compiler.MainBase {  
    
    public Main(BenchmarkResult bmResult, int threadId) {
        super(bmResult, threadId, 1);        
        String[] args = new String[] {  
                "-nowarn",        
                "-proc:none",                
                "@" + srcsFile.getPath()
                }; 

        compiler = new Compiler(args);       
    }   
    
    public static void setupBenchmark() {    	
    	MainBase.preSetupBenchmark(Main.class);
        srcsFile = getSrcFile(resDir, tmpDir);  
        new Main(new BenchmarkResult(), 1).harnessMain(true);       
    }
   
    public static File getSrcFile(File resDir, File tmpDir) {
        File srcsFile = Util.getSrcFile(tmpDir);        
        File srcDir = Util.getSrcDir(resDir, "compiler.compiler");      
        File compilerZip = Util.getZipFile(srcDir);        
        try {        	
            ArrayList<String> srcs = new ArrayList<String>();
            FileWriter fw = new FileWriter(srcsFile);
            Util.unzip(compilerZip.getPath(), tmpDir.getPath(), srcs);            
            
            for (String src: srcs) {
                if (src.endsWith(".java") && src.contains("/src/share/classes/")) {                	
                    // JCTree.java has an error at 1615 that we shall avoid for now
                    if (src.endsWith("version-template.java")) {
                        String version = src.replace("-template", "");
                         File templateFile = new File(src);
                         File versionFile = new File(version);
                         templateFile.renameTo(versionFile);
                         src = version;
                    }
                    fw.write(src);
                    fw.write(Util.linesep());
                }
           }
           fw.close();
        } catch (IOException ioe) {
        	ioe.printStackTrace();
            throw new StopBenchmarkException("ERROR: could not create: " + srcsFile.getPath());          
        }
      
      return srcsFile;
    }        
    
	private static int _MK_numberOfRepetitions = 20;
    
    public static long _MK_val = 0;
    
    public static void main(String[] args) throws Exception {//TODO clarify: how has added this?
    	List<Long> _MK_meas = new ArrayList<Long>();
    	double sum = 0D;
        for(int i=0; i<_MK_numberOfRepetitions ; i++){
        	_MK_val= System.nanoTime(); //MK
        	runSimple( Main.class, args );
        	_MK_val = System.nanoTime()-_MK_val;
        	_MK_meas.add(_MK_val);
        	sum += _MK_val;
            System.err.println("MK "+i+": compiler.compiler.Main.main: "+_MK_val+" ns");
        }
        Helper.plotDurationsHistory(_MK_meas, "measurement number", 
        		"duration [ns]", "Compiler.compiler", "compiler.compiler");
        System.err.println("MK: compiler.compiler.Main.main 1st from "+
        		_MK_numberOfRepetitions+" meas: "+_MK_meas.get(0));
        Collections.sort(_MK_meas);
        System.err.println("MK: compiler.compiler.Main.main min from "+
        		_MK_numberOfRepetitions+" meas: "+_MK_meas.get(0));
        System.err.println("MK: compiler.compiler.Main.main med from "+
        		_MK_numberOfRepetitions+" meas: "+_MK_meas.get(_MK_meas.size()/2));
        System.err.println("MK: compiler.compiler.Main.main avg from "+
        		_MK_numberOfRepetitions+" meas: "+sum/_MK_numberOfRepetitions);
        System.err.println("MK: compiler.compiler.Main.main max from "+
        		_MK_numberOfRepetitions+" meas: "+_MK_meas.get(_MK_meas.size()-1));
    }
}

