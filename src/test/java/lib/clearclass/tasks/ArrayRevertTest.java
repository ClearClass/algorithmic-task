package lib.clearclass.tasks;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class ArrayRevertTest {
	@Test
	public void compare(){
		Random r = new Random();
		int N = r.nextInt(100)+1; // число в диапазоне [1..100] включительно
		int[] x1 = new int[N];
		for (int i = 0; i < N; i++)
			x1[i] = i;
		int[] x2 = x1.clone();
		int M = r.nextInt(N)+1;   // число в диапазоне [1..N] включительно
		ArrayRevert.revert(x2, M);
		for (int i = 0; i < N; i++)
			assertTrue("N="+ N +", M="+ M, i<M ? x1[i]==x2[i+N-M] : x1[i]==x2[i-M]);
	}
	
	@Test
	public void view(){
		int[] x1 = {1, 2, 3, 4, 5, 6, 7, 8};
		for (int x : x1)
			System.out.print(x + " ");
		System.out.println();
		ArrayRevert.revert(x1, 5);
		for (int x : x1)
			System.out.print(x + " ");
	}
}
