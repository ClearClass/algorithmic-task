package lib.clearclass.tasks;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class WellTest {
	@Test
	public void testDictionary() {
		int size = 7;
		Well well = new Well(size);
		Well.Dictionary dic = well.getDictionary();
		
		// проверка метода getWord()
		assertEquals(size, dic.getWord().length());
		
		// проверка метода getWords(char char2)
		Set<String> words = dic.getWords('н');
		for (String word : words){
			assertEquals(size, word.length());
			assertEquals('н', word.charAt(1));
		}
		
		// проверка метода getWord(char char2, char char_)
		String word = dic.getWord('п','м');
		assertEquals(size, word.length());
		assertEquals('п', word.charAt(1));
		assertEquals('м', word.charAt(size-2));
	}
	
	@Test
	public void testWell() {
		int size = 9;
		Well well = new Well(size);
		String[] side = well.getSide();
		
		// проверка длины сторон 
		for (int i = 0; i < 4; i++)
			assertEquals(size, side[i].length());
		
		// проверка совпадения символов на пересечении
		assertTrue(side[0].charAt(1)==side[1].charAt(1));
		assertTrue(side[1].charAt(size-2)==side[2].charAt(1));
		assertTrue(side[2].charAt(size-2)==side[3].charAt(size-2));
		assertTrue(side[3].charAt(1)==side[0].charAt(size-2));
		
		// проверка уникальности сторон
		assertFalse(side[0].equals(side[1]) || side[0].equals(side[2]) || side[0].equals(side[3]));
		assertFalse(side[1].equals(side[2]) || side[1].equals(side[3]));
		assertFalse(side[2].equals(side[3]));
		
		well.draw();
	}
}
