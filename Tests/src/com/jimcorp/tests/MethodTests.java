package com.jimcorp.tests;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class MethodTests {

	public static void main(String[] args) {
//		String path = "C:\\Users\\Jimmy\\Pictures\\Resource Pics\\giraffe6.png";
//		readImage(path);
		
		List<String> list = new ArrayList<String>();
		list.add("Banana");
		list.add("Penelope");
		System.out.println("List before change:");
		printList(list);
		
		changeMe(list);
		System.out.println("List after change:");
		printList(list);
	}
	
	
	public static void changeMe(List<String> list) {
		
		list.add("new item1");
		list.add("new item2");
		list.remove(0);
		System.out.println("List inside function:");
		printList(list);
	}
	
	public static <T> void printList(List<T> list) {
		for(T t : list) {
			System.out.println(" - " + t);
		}
		System.out.println();
	}
	
	public static void readImage(String path) {
		Image image = null;
		try {
			image = ImageIO.read(new File(path));
			System.out.println(image.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
