package slackMarkov;

import java.util.ArrayList;

public class Word {
	private String name;
	private ArrayList<String> followWords;
	private int endFreq;
	
	public Word(String name){
		this.name = name;
		followWords = new ArrayList<String>();
		endFreq = 0;
	}
	
	/*	Adds given word (as a string) to the follow word list
	 * Pre-Req: Word to add
	 * Post-req: word is added to followWords list
	 */
	public void addWordToFollowList(String word){
		followWords.add(word);
	}
	
	public String getName(){
		return name;
	}
	
	
	/* Randomly chooses the next word to print based on the weighted distrubution of all words within its followWords list
	 * Sends back null if the word indicated that it should end the statement there (ie if its a common end word)
	 * Pre-Req: followWords has words in it. 
	 * Post-req: one word from followWords
	 */
	//TODO optimize this
	public String getNextWord(){
		String output = null;
		int totalWords = followWords.size();
		int chosenWordIndex = (int) Math.floor((Math.random()*(totalWords+endFreq)));
		if (chosenWordIndex<totalWords){
			output = followWords.get(chosenWordIndex);
		}
		return output;
	}
	
	public static void main(String args[]){
		Word test = new Word("test");
		test.addWordToFollowList("hello_world");
		test.addWordToFollowList("class");
		test.incEndFreq();
		double endMessageFreq = 0;
		double testTotalMessages = 100000000000.0;
		for (int i = 0; i<testTotalMessages; i++){
			String nextWord = test.getNextWord();
			if (nextWord!=null){
				//System.out.println(nextWord);
			}
			else{
				//System.out.println("EndMessage");
				endMessageFreq++;
			}
		}
		double percent = endMessageFreq/testTotalMessages;
		System.out.println("Total: "+ testTotalMessages+" EndSig: "+endMessageFreq+" Percent: "+percent);
	}

	public void incEndFreq() {
		endFreq++;
	}
}
