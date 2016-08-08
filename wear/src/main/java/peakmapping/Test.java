package peakmapping;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test{
	
	public static void main(String[] args){
	
		//List<Double> myList1 = new ArrayList<Double>(Arrays.asList(1.7675244,-0.12813355,-11.815829,-8.459209,-6.228248,-9.562116,-2.311194,-9.190887,-10.339299,-7.35271,-25.084236,28.06364,-12.177477,-7.7610607,-11.56076,-5.494175,1.5399976));
		//List<Double> myList2 = new ArrayList<Double>(Arrays.asList(-3.9983168,-4.357447,-4.5681367,-3.7253778,-6.5505357,-6.1530986,-9.586384,-9.859323,-7.9726915,-26.03455,-38.048653,-10.6637745,-5.8226986,-3.7014358,2.2744915,1.8770541,1.5705963));

		List<Double> myList1 = new ArrayList<Double>(Arrays.asList(1.7675244,-0.12813355,-11.815829,-8.459209,-6.228248,-9.562116,-2.311194,-9.190887,-10.339299,-7.35271,-25.084236,28.06364,-12.177477,-7.7610607,-11.56076,-5.494175,1.5399976));
		List<Double> myList2 = new ArrayList<Double>(Arrays.asList(-0.5680065,2.7264152,18.215086,0.72486305,-12.749704,5.4300494,16.112978,43.914448,-30.543411,8.999224,-0.7260237,2.9993544,1.7160625,-0.49139214,2.6593776,1.3425665));
		
		Double delta = 0.15;
		PeakMapping maps = new PeakMapping(myList1, myList2, delta);
		
		System.out.println();
		System.out.println(maps.peak_detection(myList1, delta).get(0));
		System.out.println(maps.peak_detection(myList2, delta).get(0));
		System.out.println((maps.peaks1.get(0).get(0)!=null) && (maps.peaks2.get(0).get(0)!=null));
		
		if(maps.peaks1.get(0).size()>=(maps.peaks2.get(0).size())){
			
			for(int i=1; i<14; i++){
				System.out.println(
				(maps.peaks1.get(0).get(i)!=null) && (maps.peaks2.get(0).get(i)!=null)
				||
				(maps.peaks1.get(0).get(i+1)!=null) && (maps.peaks2.get(0).get(i)!=null)
				||
				(maps.peaks1.get(0).get(i-1)!=null) && (maps.peaks2.get(0).get(i)!=null)
				||
				(maps.peaks1.get(0).get(i)!=null) && (maps.peaks2.get(0).get(i+1)!=null)
				||
				(maps.peaks1.get(0).get(i)!=null) && (maps.peaks2.get(0).get(i-1)!=null)
				||
				(maps.peaks1.get(0).get(i)==null) && (maps.peaks2.get(0).get(i)==null)
/*  				||
				(maps.peaks1.get(0).get(i+1)==null) && (maps.peaks2.get(0).get(i)==null)
				||
				(maps.peaks1.get(0).get(i-1)==null) && (maps.peaks2.get(0).get(i)==null)
				||
				(maps.peaks1.get(0).get(i)==null) && (maps.peaks2.get(0).get(i+1)==null)
				||
				(maps.peaks1.get(0).get(i)==null) && (maps.peaks2.get(0).get(i-1)==null)  */
				);
			}
			
		} else if(!(maps.peaks1.get(0).size()>=(maps.peaks2.get(0).size()))){
			
			for(int i=0; i<maps.peaks2.get(0).size()-1; i++){
				System.out.println(
				(maps.peaks1.get(0).get(i)!=null) && (maps.peaks2.get(0).get(i)!=null)
				||
				(maps.peaks1.get(0).get(i+1)!=null) && (maps.peaks2.get(0).get(i)!=null)
				||
				(maps.peaks1.get(0).get(i-1)!=null) && (maps.peaks2.get(0).get(i)!=null)
				||
				(maps.peaks1.get(0).get(i)!=null) && (maps.peaks2.get(0).get(i+1)!=null)
				||
				(maps.peaks1.get(0).get(i)!=null) && (maps.peaks2.get(0).get(i-1)!=null)
				||
				(maps.peaks1.get(0).get(i)==null) && (maps.peaks2.get(0).get(i)==null)
				||
				(maps.peaks1.get(0).get(i+1)==null) && (maps.peaks2.get(0).get(i)==null)
				||
				(maps.peaks1.get(0).get(i-1)==null) && (maps.peaks2.get(0).get(i)==null)
				||
				(maps.peaks1.get(0).get(i)==null) && (maps.peaks2.get(0).get(i+1)==null)
				||
				(maps.peaks1.get(0).get(i)==null) && (maps.peaks2.get(0).get(i-1)==null)
				);
			}
		} 
		
	}
	
}