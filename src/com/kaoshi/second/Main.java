package com.kaoshi.second;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yhcui
 * A卷第二题
 * 
 */
public class Main {

	
	public static void main(String[] args) {
		Set<Long> set = new HashSet<Long>();
		for(long i=0;i<4294967296L;i++){
			set.add(i);
		}
	}
}
