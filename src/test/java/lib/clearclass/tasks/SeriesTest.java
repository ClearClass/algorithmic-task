package lib.clearclass.tasks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class SeriesTest {
	@Test
	@SuppressWarnings("unchecked")
	public void verify(){
		// формируем ряд ...		
		ArrayList<Integer> series = new ArrayList<>(Arrays.asList(0));
		int M = 18; // длина ряда: 2^M
		for (int i = 0; i < M; i++)
			((ArrayList<Integer>) series.clone()).stream().map(n -> ++n%3).forEach(series::add);
		assertEquals((int)Math.pow(2, M), series.size());
		// проверяем элементы ...
		for (int i = 0; i < series.size(); i++)
			assertEquals((int)series.get(i), Series.get(i));
	}
}