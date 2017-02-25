package com.snake;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.*;
public class Test {

   public static void main(String[] args) {
	   
	   LinkedList<Integer> linkedList = new LinkedList<Integer>();
	   for(int i = 0; i < 10; i++) {
		   linkedList.add(i);
	   }
	   System.out.println(linkedList);
	   ArrayList<Integer> arrayList = new ArrayList<Integer>(linkedList);
	   System.out.println(arrayList);
   }
}
