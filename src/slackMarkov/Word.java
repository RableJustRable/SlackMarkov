package slackMarkov;

import java.util.ArrayList;

public class Word {
	private String name;
	private ArrayList<String> followWords;
	private int startFreq;
	
	public Word(String name){
		this.name = name;
		followWords = new ArrayList<String>();
		startFreq = 0;
	}
	
	/*	Adds given word (as a string) to the follow word list
	 * Pre-Req: Word to add
	 * Post-req: word is added to followWords list
	 */
	public void addWord(String word){
		followWords.add(word);
	}
	
	
	/* Randomly chooses the next word to print based on the weighted distrubution of all words within its followWords list
	 * Pre-Req: followWords has words in it. 
	 * Post-req: one word from followWords
	 */
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
