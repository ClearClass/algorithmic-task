package lib.clearclass.tasks;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberPermutationTest {
	@Test
	public void resultSetSizeTest(){
		List<Integer> nums = Stream.of(1, 1, 6, 3, 2, 1).collect(Collectors.toList());
		Set<List<Integer>> res = NumberPermutation.solve(nums);
		assertEquals(combNum(nums), res.size());
	}
	
	static long combNum(List<Integer> list){
		return fact(list.size())/list.stream().collect(Collectors.groupingBy(i->i)).values().stream().map(li->fact(li.size())).reduce(1L, (x, y)->x*y);
	}
	
	static long fact(int n) {
	    return LongStream.rangeClosed(1, n).reduce(1, (long x, long y)->x*y);
	}
}
