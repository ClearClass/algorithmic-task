package lib.clearclass.tasks;

import java.util.*;

public class WordChain {
	private int setSize;
	private Node tree;
	private boolean findChain = false;
	private LinkedList<String> result = new LinkedList<>();

	public WordChain(String word, List<String> list) {
		if(!list.contains(word)) 
			throw new RuntimeException(word + " is not in the set");
		setSize = list.size();
		tree = new Node(word, list);
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
		
		Node(String word, List<String> list){
			this.word = word;
			int wordIndex = list.indexOf(word);
			list.remove(word);
			char thisLastChar = word.charAt(word.length()-1);
			for(int i=0; i<list.size(); i++){
				String st = list.get(i);
				if(thisLastChar==st.charAt(0))
					setNodes.add(new Node(st, list));
			}
			list.add(wordIndex, word);
		}
	}
}