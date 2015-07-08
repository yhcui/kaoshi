package com.kaoshi.fourth;

public class Main {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ZiroomSpider a = new ZiroomSpider();
		a.start("http://www.ziroom.com/z/nl/z2.html");
		System.out.println(System.currentTimeMillis() - start);
	}
	
}
