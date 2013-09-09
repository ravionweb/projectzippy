package com.schooltrix.TestTemp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class Test1 {

	public void setTest() {
		Set set = new HashSet();
		set.add("ee");
		set.add("ee");
		set.add("ffee");
		set.add("ffuuee");
		set.add("ffffee");
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object);
		}
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String object = (String) iterator.next();
			System.out.println("p"+object);
		}
		
		System.out.println(set);
		
	}
	
	public static void main(String[] args) {
		Test1 test1 = new Test1();
		//test1.setTest();
		//test1.SysOUT();
		//test1.testingTemp();
		test1.nullEmptyCheck();
	}

	private void nullEmptyCheck() {
		// TODO Auto-generated method stub
		String g = "   f";
		if (StringUtils.isBlank(g)) {
			System.out.println("jkjkjk");
		}else{
			System.out.println("jjjjjjjjjjjjjjjjj");
		}
		
		String hhh = "                   v        ";
		if (StringUtils.isNotBlank(hhh)) {
			System.out.println("UUUU");
		}else{
			System.out.println("YYYY");
		}
		
		
		
	}

	private void testingTemp() {
		int g = 2;
System.out.println(g+++g+++"**"+g++);
System.out.println(g);
		
	}

	private void SysOUT() {
String p="kk";
		System.out.println(p.equalsIgnoreCase("k")?"Yes":"No");
	}

}
