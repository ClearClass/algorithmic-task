package lib.clearclass.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

public class PeriodicStringTest {
	@Test
	public void test(){
		String st = "fgtkfgtkfgtkfgtkfgtkfgtkfgtkfgtkfgtkfgtkfgtk";
		assertEquals("fgtk", PeriodicString.getPeriod(st));
		
		st = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		assertEquals("x", PeriodicString.getPeriod(st));
		
		st = "abcdabcd";
		assertEquals("abcd", PeriodicString.getPeriod(st));
		
		st = "xx_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		assertNull(PeriodicString.getPeriod(st));
		
		st = "fgtk_fgtk_fg";
		assertNull(PeriodicString.getPeriod(st));
	}
}