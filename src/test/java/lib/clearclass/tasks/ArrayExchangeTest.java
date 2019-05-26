package lib.clearclass.tasks;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class ArrayExchangeTest {
	int N = 20;
	int[] a = new int[N];
	int b; 
	
	public ArrayExchangeTest(){
		Random r = new Random();
		int M = 10; // диапазон распределения случайных чисел
		for(int i = 0; i < N; i++) 
			a[i] = r.nextInt(M); // число в диапазоне [0..M-1] включительно
		System.out.println("Before: " + Arrays.toString(a));
		b = r.nextInt(M);
		System.out.println("b = " + b);
	}
	
	@Test
	public void testExchange1(){
		// выполняем перестановку
		ArrayExchange.exchange1(a, b);
		System.out.println("After:  " + Arrays.toString(a) + "\n\n\n");
		// осуществляем проверку
		int i = 0;
		for( ; i<N && a[i]<=b; i++){}
		for( ; i<N; i++) assertTrue(a[i]>=b);
	}
	
	@Test
	public void testExchange2(){
		// выполняем перестановку
		ArrayExchange.exchange2(a, b);
		System.out.println("After:  " + Arrays.toString(a) + "\n\n\n");
		// осуществляем проверку
		int i = 0;
		for( ; i<N && a[i]<b; i++){}
		for( ; i<N && a[i]==b; i++){}
		for( ; i<N; i++) assertTrue(a[i]>b);
	}
}
