package slackMarkov;

import java.util.ArrayList;

public class Word {
	private String name;
	private ArrayList<String> followWords;
	
	public Word(String name){
		this.name = name;
		followWords = new ArrayList<String>();
	}
	
	public void addWord(String word){
		followWords.add(word);
	}
	
	//TODO optimize this
	public String getNextWord(){
		String output = null;
		int totalWords = followWords.size();
		int chosenWordIndex = (int) Math.floor((Math.random()*totalWords));
		output = followWords.get(chosenWordIndex);
		return output;
	}
	
	public static void main(String args[]){
		Word test = new Word("test");
		test.addWord("hello_world");
		test.addWord("class");
		for (int i = 0; i<10; i++){
			System.out.println(test.getNextWord());
		}
	}
}
