package org.alopedraza;

import java.util.Arrays;
import java.util.TreeSet;

public class Solution {

	public static void main(String[] args) {
		//An example
		int[] A = {86, 1463, 85, 85, 5798, 2, 85, 2, 86};
		
		Solution sol= new Solution();
		int[] leaders = sol.solution(8, 6000, A);
		if (leaders.length != 0)
			Arrays.stream(leaders).boxed().forEach(System.out::println);
		else
			System.out.println("Values of array can't be leader");
	}
	
	public int[] solution(int K, int M, int[] A){
		int[] leaders = {};
		TreeSet<Integer> leadSet = new TreeSet<Integer>();
		
		int maxRankLeader = A.length / 2;
		
		if(K < A.length && lessThanMax(M,A)) {
			int n = 0;
			while(n + K <= A.length) {
				//modify the segment in an auxiliary array
				int[] auxA = A.clone();
				for(int i = n; i < K + n; i++) {
					auxA[i]++;
				}
				
				//check how many times each number is repeated
				for(int i = 0; i < auxA.length; i++) {
					int nRepeat = 0;
					for(int j = i; j < auxA.length; j++) {
						if(auxA[i] == auxA[j]) {
							nRepeat++;
						}
					}
					if(nRepeat > maxRankLeader) {
						leadSet.add(auxA[i]);
						break;
					}
				}
				n++;
			}
		}
		
		//convert set in array to return
		if(!leadSet.isEmpty())
			leaders = leadSet.stream().mapToInt(lead -> lead.intValue()).toArray();
		
		return leaders;
	}
	
    //function to know if any array value exceeds the max
	private boolean lessThanMax(int max, int[] A) {
		for(int a : A) {
			if(a > max)
				return false;
		}
		
		return true;
	}

}