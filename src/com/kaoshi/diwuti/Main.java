package com.kaoshi.diwuti;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author yhcui
 * A卷第五题 
 * 
 */
public class Main {

	
	public static  int zuo = 1;
	
	public static double getJuLi(Person p1,Person p2){
//		 double juli =  Math.floor((Math.pow(4-5,2)+Math.pow(8-5,2)+Math.pow(8-5,2)));
		 double juli =  Math.pow(p1.getX()-p2.getX(),2)+Math.pow(p1.getY()-p2.getY(),2)+Math.pow(p1.getZ()-p2.getZ(),2);
		 return juli;
	}
	public static int getZuoBiao(){
//		int number = new Random().nextInt(1000000000) + 1;
//		return number;
		return zuo+ new Random().nextInt(10) + 1;
	}
	
	public static void main(String[] args) {
		
		List<Person> personList = new ArrayList<Person>();
		for(int i=0;i<1002;i++){
			int x = getZuoBiao();
			int y = getZuoBiao();
			int z =getZuoBiao();
			
			Person person = new Person(String.valueOf(i),x,y,z);
			personList.add(person);
		}
		long start = System.currentTimeMillis();
		
		double[][] juli = new double[personList.size()][personList.size()];
		for(int i=0;i<personList.size();i++){
			for(int j=0;j<personList.size();j++){
				double juliya = getJuLi(personList.get(i),personList.get(j));
				juli[i][j] = juliya;
			}
		}
		/*for(int i = 0;i<juli.length;i++){
			double[] temp = juli[i];
			for(int j =0;j<temp.length;j++){
				System.out.print(temp[j]+",");
			}
			System.out.println("-----");
		}*/
		Set<Integer> set =new HashSet<Integer>();
		
		Set<Integer> setY =new HashSet<Integer>();
		
		for(int i = 0;i<juli.length;i++){
			if(set.contains(i)){
				continue;
			}
			double[] temp = juli[i];
			double minJuli = 0;
			int minX =0 ,minY=0;
			int flag = 0;
			boolean has = false;
			for(int j =0;j<temp.length;j++){
				if(set.contains(j)){
					continue;
				}
				if(i == j){
					continue;
				}
//				if(temp[j] >=0){
				if(flag == 0){
					minJuli = temp[j];
					flag = 1;
					has = true;
					minX = i;
					minY = j;
				}else{
					if(minJuli > temp[j]){
						minJuli =temp[j];
						minX = i;
						minY = j;
						has = true;
					}
				}
					
//				}
			}
			
			if(set.contains(minX) || set.contains(minY)){
				
			}else if(has){
				System.out.println(personList.get(minX).getId()+","+personList.get(minY).getId()+"距离："+minJuli);
				set.add(minX);
				set.add(minY);
				juli[minX][minY] =-1;
			}
			
			
			
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
	
}
