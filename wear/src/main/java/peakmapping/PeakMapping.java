package peakmapping;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class PeakMapping {
	
	public List<Double> myList1;
	public List<Double> myList2;
	List<Map<Integer, Double>> peaks1;
	List<Map<Integer, Double>>peaks2;
	public Double delta; 
	
	public PeakMapping(List<Double> myList1, List<Double> myList2, Double delta){
		
		this.myList1 = myList1;
		this.myList2 = myList2;
		this.delta = delta;
		
		peaks1 = peak_detection(myList1, delta);
		peaks2 = peak_detection(myList2, delta);
		
	}
	
	/**
	 * Detects peaks (calculates local minima and maxima) in the 
	 * vector <code>values</code>. The resulting list contains
	 * maxima at the first position and minima at the last one.
	 * 
	 * Maxima and minima maps contain the indice value for a
	 * given position and the value from a corresponding vector.
	 * 
	 * A point is considered a maximum peak if it has the maximal
	 * value, and was preceded (to the left) by a value lower by
	 * <code>delta</code>.
	 * 
	 * @param values Vector of values for whom the peaks should be detected
	 * @param delta The precedor of a maximum peak
	 * @param indices Vector of indices that replace positions in resulting maps
	 * @return List of maps (maxima and minima pairs) of detected peaks
	 */
	public static <U> List<Map<U, Double>> peak_detection(List<Double> values, Double delta, List<U> indices)
	{
		assert(indices != null);
		assert(values.size() != indices.size());
		
		Map<U, Double> maxima = new HashMap<U, Double>();
		Map<U, Double> minima = new HashMap<U, Double>();
		
		List<Map<U, Double>> peaks = new ArrayList<Map<U, Double>>();
		
		peaks.add(maxima);
		peaks.add(minima);
		
		Double maximum = null;
		Double minimum = null;
		U maximumPos = null;
		U minimumPos = null;
		
		boolean lookForMax = true;
		
		Integer pos = 0;
		
		//System.out.println(values);
		
		for (Double value : values) {
			
			try{
				if ((value > maximum) || (maximum == null)) {
					maximum = value;
					maximumPos = indices.get(pos);
				}
			}catch(NullPointerException e){
					maximum = value;
					maximumPos = indices.get(pos);
			}
			
			
			try{
				if (value < minimum || minimum == null) {
					minimum = value;
					minimumPos = indices.get(pos);
				}
			}catch(NullPointerException e){
					minimum = value;
					minimumPos = indices.get(pos);
			}
			
			if (lookForMax) {
				if (value < maximum - delta) {
					maxima.put(maximumPos, value);
					minimum = value;
					minimumPos = indices.get(pos);
					lookForMax = false;
				}
			} else {
				if (value > minimum + delta) {
					minima.put(minimumPos, value);
					maximum = value;
					maximumPos = indices.get(pos);
					lookForMax = true;
				}
			}
			
			pos++;
		}
		
		
		
		return peaks;
	}
	
	/**
	 * Detects peaks (calculates local minima and maxima) in the 
	 * vector <code>values</code>. The resulting list contains
	 * maxima at the first position and minima at the last one.
	 * 
	 * Maxima and minima maps contain the position for a
	 * given value and the value itself from a corresponding vector.
	 * 
	 * A point is considered a maximum peak if it has the maximal
	 * value, and was preceded (to the left) by a value lower by
	 * <code>delta</code>.
	 * 
	 * @param values Vector of values for whom the peaks should be detected
	 * @param delta The precedor of a maximum peak
	 * @return List of maps (maxima and minima pairs) of detected peaks
	 */
	public static List<Map<Integer, Double>> peak_detection(List<Double> values, Double delta)
	{
		List<Integer> indices = new ArrayList<Integer>();
		for (int i=0; i<values.size(); i++) {
			indices.add(i);
		}
		
		return peak_detection(values, delta, indices);
	}
	
	
	
}