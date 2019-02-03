package lib.clearclass.tasks;

import java.util.*;

public class WordChain {
	private int setSize;
	private Node tree;
	private boolean findChain = false;
	private LinkedList<String> result = new LinkedList<>();

	public WordChain(String word, HashSet<String> set) {
		if(!set.contains(word)) 
			throw new RuntimeException(word + " is not in the set");
		setSize = set.size();
		tree = new Node(word, set);
		traverse(tree);
	}
	
	private int currentDepth = 0;
	private void traverse(Node node){
		currentDepth++;
		for(Node nd : node.setNodes){
			if(findChain) break;
			traverse(nd);
		}
		if(currentDepth==setSize && node.word.charAt(node.word.length()-1) == tree.word.charAt(0))
			findChain = true;
		if(findChain) result.addFirst(node.word);
		currentDepth--;
	}
	
	public LinkedList<String> getResult() {
		if(findChain) return result;
		return null;
	}
	
	private class Node {
		String word;
		Set<Node> setNodes = new HashSet<>(); // слова, начинающиеся на word[last]
		
		Node(String word, HashSet<String> set){
			this.word = word;
			HashSet<String> reduceSet = (HashSet<String>) set.clone();
			// запрещается изменять коллекцию, для которой был получен итератор
			reduceSet.remove(word); 
			Iterator<String> it = reduceSet.iterator();
			char thisLastChar = word.charAt(word.length()-1);
			while(it.hasNext()) {
				String st = it.next();
				if(thisLastChar==st.charAt(0))
					setNodes.add(new Node(st, reduceSet));
			}
		}
	}
}