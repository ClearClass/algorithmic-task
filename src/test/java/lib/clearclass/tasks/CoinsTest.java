package lib.clearclass.tasks;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class CoinsTest {
	int sum = new Random().nextInt(100);
	Coins coins = new Coins(sum);
	
	@Test // вывод результата при sum = 6
	public void view(){
		Coins coins = new Coins(6);
		for (ArrayList<Integer> series : coins.getResult())
			System.out.println(series);
	}
	
	@Test // проверяем уникальность каждого набора
	public void unique(){
		Set<ArrayList<Integer>> set = new HashSet<>();
		for (ArrayList<Integer> series : coins.getResult())
			assertTrue(set.add(series));
	}
	
	@Test // проверяем сумму каждого набора
	public void sum(){
		for (ArrayList<Integer> series : coins.getResult()){
			int rsum = 0;
			for (int n : series) 
				rsum+=n;
			assertEquals(sum, rsum);
		}
	}
}