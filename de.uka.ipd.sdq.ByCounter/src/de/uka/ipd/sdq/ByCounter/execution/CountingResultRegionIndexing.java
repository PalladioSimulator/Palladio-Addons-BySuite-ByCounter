package de.uka.ipd.sdq.ByCounter.execution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationRegion;

/**
 * Indexing for counting regions.
 * @author Martin Krogmann
 */
public class CountingResultRegionIndexing implements ICollectionStrategy {
	
	Map<InstrumentationRegion,List<CountingResult>> results;
	
	public CountingResultRegionIndexing() {
		this.results = new HashMap<InstrumentationRegion, List<CountingResult>>();
	}

	/**
	 * @param res Partial counting result.
	 * @param currentRegion Region to which the result belongs.
	 */
	public void add(CountingResult res, InstrumentationRegion currentRegion) {
		List<CountingResult> rs = this.results.get(currentRegion);
		if(rs == null) {
			// no entry for this region id yet
			rs = new LinkedList<CountingResult>();
			this.results.put(currentRegion, rs);
		}
		rs.add(res);
	}

	@Override
	public void clearResults() {
		this.results.clear();
	}

	@Override
	public boolean protocolCount(ProtocolCountStructure result) {
		throw new IllegalArgumentException("Don't use this protocol method.");
	}

	@Override
	public final SortedSet<CountingResult> retrieveAllCountingResults() {
		SortedSet<CountingResult> addedResults = new TreeSet<CountingResult>();
		// for each region
		for(Entry<InstrumentationRegion,List<CountingResult>> e : this.results.entrySet()) {
			// add up the counting results
			InstrumentationRegion ir = e.getKey();
			Iterator<CountingResult> iter = e.getValue().iterator();
			CountingResult irResult = iter.next().clone();
			while(iter.hasNext()) {
				irResult.add(iter.next());
			}
//			irResult.setQualifyingMethodName(ir.toString());
			addedResults.add(irResult);
		}
		return addedResults;
	}

}
