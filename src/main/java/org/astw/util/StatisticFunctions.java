package org.astw.util;

import java.util.Arrays;

import gnu.trove.map.hash.TIntIntHashMap;

public class StatisticFunctions {

	
	public static double mean(int[] input) {
		
	    int sum = 0;

	    for (int ii = 0; ii < input.length; ii++) 
	        sum += input[ii];

	    return (double)sum / input.length;
	}
	
	public static double median(int[] input) {
		
		int[] inp = Arrays.copyOf(input, input.length);
		
	    int middle = inp.length/2;
	    
	    if (inp.length%2 == 1) { //odd size array
	        return inp[middle];
	        
	    } else { //even size array
	        return (inp[middle-1] + inp[middle]) / 2.0;
	    }
	}
	
	public static int mode(int input[]) {
		
		TIntIntHashMap freq = new TIntIntHashMap();
		
		//compute the frequency of each data
		for(int ii = 0; ii< input.length; ii++){

			int curVal = input[ii];
			
			if(freq.containsKey(curVal)){
				
				int newLength = freq.get(curVal) + 1;
				freq.put(curVal, newLength);
			}else{
				
				freq.put(curVal, 1);
			}
		}
		
		int modeCandidate = -1;
		int candidateFreq = 0;
		
		int[] keys = freq.keys();
		
		//find the data with the maximum frequency
		for(int ii = 0; ii < keys.length; ii++){
			
			int f = freq.get(keys[ii]);
			
			if(f > candidateFreq){
				
				modeCandidate = keys[ii];
				candidateFreq = f;
			}
		}
	    return modeCandidate;
	}

	public static int max(int input[]){
		
		int maxCandidate = Integer.MIN_VALUE;
		
		for(int ii = 0; ii < input.length; ii++){
			
			int curVal = input[ii];
			
			if(curVal > maxCandidate)
				maxCandidate = curVal;
		}
		
		return maxCandidate;
	}
	
	public static int min(int input[]){
		
		int maxCandidate = Integer.MAX_VALUE;
		
		for(int ii = 0; ii < input.length; ii++){
			
			int curVal = input[ii];
			
			if(curVal < maxCandidate)
				maxCandidate = curVal;
		}
		
		return maxCandidate;
	}
}
