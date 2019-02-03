package lib.clearclass.tasks;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

public class WordChainTest {
	String[] words = {"магия", "ясновидение", "енот", "тоник", "кошка", "амулет", 
			"текстолит", "телескоп", "погадка", "анклав", "водомерка", "агроном"};
	
	HashSet<String> set = new HashSet<>(Arrays.asList(words));
	WordChain chain = new WordChain("погадка", set);
	LinkedList<String> result = chain.getResult();
	
	@Test
	public void verify(){
		assertTrue(result.size()==set.size());
		
		String lastWord = result.getLast();
		char previous = lastWord.charAt(lastWord.length()-1);
		char next;
		for(String st : result) {
			next = st.charAt(0);
			assertTrue(next==previous);
			previous = st.charAt(st.length()-1);
		}
	}
	
	@Test
	public void view(){
		 System.out.println(set);
		 System.out.println(result);
	}
}
