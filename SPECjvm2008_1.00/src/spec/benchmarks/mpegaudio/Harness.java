/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * Copyright (c) 1997,1998 Sun Microsystems, Inc. All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */
package spec.benchmarks.mpegaudio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.CRC32;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.DecoderException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.SampleBuffer;
import spec.harness.Context;

public class Harness {
	private static Logger log = Logger.getLogger(Harness.class.getCanonicalName());
    
//	/**
//     * introduced by Michael Kuperberg
//     * @param args
//     */
//    public static void main(String[] args) {
//    	log.info("MK ");
//		Harness h = new Harness();
//		int id;
//		for(int i=0; i<Main.DEFAULT_NUMBER_OF_BENCHMARK_RUNS; i++){
//			id = i*Main.MK_DEFAULT_INCREASE_STEP;
//			h.inst_main(id);//this is where it is passed!
//		}
//	}
    
    /**
     * introduced by Michael Kuperberg
     * @param args
     */
    public static void runOnce(String[] args) {
    	if(Main._MK_verbose) log.info("MK ");
		Harness h = new Harness();
		h.inst_main(0);
	}
    
    private List<Long> MK_nanoTimeInnerResults = new ArrayList<Long>();
    
    private List<Long> MK_nanoTimeOuterResults = new ArrayList<Long>();
    
    private long MK_startInner = 0L;
    
    private long MK_startOuter = 0L;
    
    private long MK_stopInner = 0L;
    
    private long MK_stopOuter = 0L;
    
    long[] result = new long[Main.TRACKS_NUMBER];//duration in ms!

	private int MK_decodedFrames = 0;

	private List<Long> MK_nanoTimePerFrameFromDecode = new ArrayList<Long>();

	private boolean MK_printFrameProgress=false;
    
//    public long MK_decode(final String name, Long MK_numberOfFrames) throws BitstreamException,
//    DecoderException, FileNotFoundException {
//    	long ret = decode(name);
//    	return ;
//    }
	
    public long decode(final String name) throws BitstreamException,
            DecoderException, FileNotFoundException {
    	if(Main._MK_verbose) log.info("MK Filename: "+name+", "+(new File(name)).getAbsolutePath());
        Bitstream stream = new Bitstream(new FileInputStream(name));
        Decoder decoder = new Decoder();
        Header h;
        CRC32 crc = new CRC32();//MK no HDD backup?
        int decodedFrames = 0;
        short[] buffer;
        SampleBuffer sb;
        if(Main._MK_useInnerInsertedMeasurements){
            MK_nanoTimeInnerResults.clear();
        }
        int framesPerDot=20;
        if(MK_printFrameProgress) System.out.println("MK9: one dot = "+framesPerDot+" frames");
        while (decodedFrames < Main.FRAMES_LIMIT && (h = stream.readFrame()) != null) {
            decodedFrames++;
            if(MK_printFrameProgress) {
            	if(decodedFrames%framesPerDot==0) System.out.print('.');
            }
            if(Main._MK_useInnerInsertedMeasurements){
            	sb = ((SampleBuffer) decoder.decodeFrame(h, stream));
            	buffer = sb.getBuffer();
            	MK_startInner = System.nanoTime();
            	updateCRC32(crc, buffer);
            	MK_stopInner = System.nanoTime();
            	MK_nanoTimeInnerResults.add(MK_stopInner-MK_startInner);
            }else{
            	updateCRC32(crc, ((SampleBuffer) decoder.decodeFrame(h, stream)).getBuffer());
            }
            stream.closeFrame();
        }
        System.out.println("");
        if(decodedFrames<Main.FRAMES_LIMIT && (Main._MK_useInnerInsertedMeasurements || Main._MK_useOuterInsertedMeasurements)){
	        	System.out.println("MK: Stelle0: Only "+decodedFrames+" frames decoded " +
	        			"for file "+name);
	        MK_decodedFrames  = decodedFrames;
        }
        if(Main._MK_useInnerInsertedMeasurements){
        	System.out.println("MK: Stelle1: updateCRC32 duration results for frames in name "+name+": ");
            for(Long innerResult : MK_nanoTimeInnerResults){
            	System.out.print(innerResult+" ");
        	}
            System.out.println("");//for line break
        }
        stream.close();
        return crc.getValue();
    }
    
    /**
     * Returns track name...
     * @param index
     * @return
     */
    private String getName(int index) {
        return Main.prefix + "track" + index + ".mp3";//prefix not displayed on hover, even though it's final...
    }
    
    
    public void inst_main(int id) {
    	if(Main._MK_verbose) log.info("MK : id: "+id+", startingSongIndex: "+Main._MK_songIndexStarting+"; endingSongIndexIncl: "+Main._MK_songIndexEndingIncl+
    			" (id passed to a single Harness.run(id) in original implementation)");
//        run(id);//writes to "result" field (which is an array)

        for (int i = Main._MK_songIndexStarting; i <= Main._MK_songIndexEndingIncl; i++) {//TODO this loop: performance impact?
            run(i);
        }
        for (int i = Main._MK_songIndexStarting; i <= Main._MK_songIndexEndingIncl; i++) {//TODO this loop: performance impact?
            Context.getOut().println("MK: stelle 2: id "+id+", track " + i + ": returned by decode/crc: " + result[i]);//MK changes
        }
    }
    
    /**
     * MK still always runs just 6 files?
     * @param id
     */
    public void run(int id) {//TODO check for positiveness...
    	if(Main._MK_verbose) log.info("MK_run_single_file_as_if_instrumented: "+
    			Main._MK_run_single_file_as_if_instrumented);
    	if(Main._MK_run_single_file_as_if_instrumented){
    		try {
				result[id] = decode(getName(id));
			} catch (BitstreamException e) {
				e.printStackTrace();
			} catch (DecoderException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    		return;
    	}else{
	    	if(Main._MK_useOuterInsertedMeasurements){
	            MK_nanoTimeOuterResults.clear();
	            MK_nanoTimePerFrameFromDecode.clear();
	    	}
	    	try {
	            for (int i = id; i < id + Main.TRACKS_NUMBER; i++) {
	                int ind = i % Main.TRACKS_NUMBER;
	                if(Main._MK_useOuterInsertedMeasurements){
	                	MK_startOuter = System.nanoTime();
	                }
	                result[ind] = decode(getName(ind));
	                if(Main._MK_useOuterInsertedMeasurements){
	                	MK_stopOuter = System.nanoTime();
	                	MK_nanoTimeOuterResults.add((MK_stopOuter-MK_startOuter));
	                    MK_nanoTimePerFrameFromDecode.add((MK_stopOuter-MK_startOuter)/MK_decodedFrames);
	                }
	                
	            }
	            if(Main._MK_useOuterInsertedMeasurements){
	            	Thread.sleep(500); //MK to ensure that command line output does not get mixed up...
	            	System.err.println("MK3: decode() duration results for all files, passed run ID is "+id+": ");
	                for(Long result: MK_nanoTimeOuterResults){
	                	System.err.print(result+" ");
	                }
	                System.err.println("");//line break
	                System.err.println("MK3: decode() duration results *per frame* for all files, passed run ID is "+id+": ");
	                for(Long result: MK_nanoTimePerFrameFromDecode){
	                	System.err.print(result+" ");
	                }
	                System.err.println("");//line break
	            }
	        } catch (Exception e) {
	            e.printStackTrace(Context.getOut());
	        }
    	}
    }
    
    /**
     * MK quite bytecode-intensive, but not bytecode-invariant...
     * @param crc32
     * @param buffer
     */
    private void updateCRC32(CRC32 crc32, short[] buffer) {
        int length = buffer.length;
        byte[] b = new byte[length * 2];
        for (int i = 0; i < length; i++) {
            short value = buffer[i];
            b[i] = (byte) buffer[i];
            b[i + length] = (byte) ((value & 0xff00) >> 8);
        }
        
        crc32.update(b, 0, b.length);
    }
}