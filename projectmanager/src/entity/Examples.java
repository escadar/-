package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Examples {
	public static void main(String[] args) {
		Examples e = new Examples();
		int res = e.func(3, 5);
		System.out.println(res);
		 
	/*int x = 1;
		for (int i = 1; i < 5; i++) {
			System.out.println(x+i);
			
		}*/
		
		/*Random rand = new Random();
		int x = rand.nextInt(5) % 10;
		System.out.println(x);*/
		
		/*List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(3);
		list.add(2);
		list.remove(2);
	System.out.println(list.size()*10);
		 System.out.println(list.get(3) );
		*/
	}
	public  static int func(int y,int x){
		
		return y*x;
	}
	
	 
	
	

}
