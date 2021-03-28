package lib.clearclass.tasks;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

import static lib.clearclass.tasks.OverlayLines.*;

public class OverlayLinesTest {
	
	@Test
	public void test(){
		int N = 100; // количество отрезков
		int[] ai = new int[N]; // координаты начальных точек
		int[] bi = new int[N]; // координаты конечных точек
		
		for (int i = 0; i < 500; i++) {
			fill(ai, bi);
			assertArrayEquals(find1(ai, bi), find2(ai, bi));
		}
	}
}