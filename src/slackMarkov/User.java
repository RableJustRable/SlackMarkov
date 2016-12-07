package slackMarkov;

import java.util.ArrayList;

public class User {
	private String id;
	private String displayName;
	private ArrayList<Word> wordList;
	
	User(String id, String displayName){
		this.id = id;
		this.displayName = displayName;
		this.wordList = new ArrayList<Word>();
	}
	
	/* Adds message to things the user has said and distrubutes it to the according words in the wordlist
	 * Pre-Req: a message to give it
	 * Post-req: nothing 
	 */
	public void addMessage(String message){
		
	}
	
	/*Generates a message using the wordList and a markov chain type algoritm
	 * Pre-req: wordList with initialized words in it.
	 * Post-req: a generated message
	 */
	
	public String generateMessage(){
		StringBuilder sb = new StringBuilder();
		
		
		
		String output = sb.toString();
		
		return output;
	}
	
}
