package slackMarkov;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
	private String id;
	private String displayName;
	private ArrayList<Word> wordList;
	private ArrayList<String> startingWords;
	
	User(String id, String displayName){
		this.id = id;
		this.displayName = displayName;
		this.wordList = new ArrayList<Word>();
		this.startingWords = new ArrayList<String>();
	}
	
	/* Adds message to things the user has said and distrubutes it to the according words in the wordlist
	 * Pre-Req: a message to give it
	 * Post-req: nothing 
	 */
	public void addMessage(String message){
		String splitMessage[] = message.split(" "); 
		
		String prevWord = ""; //stores previous word in order to reference it later
		
		for (int i = 0; i<splitMessage.length;i++){
			if (i==0){
				prevWord = splitMessage[i];
				
			}
			else{
				
			}
		}
		
		

	}
	
	/* Finds the word object in the word list based on the string provided. Creates new word object in the list if it cannot find the word object
	 * pre-req: word to search for
	 * post: Word object found in list or a newly created one
	 */
	private Word findWord(String word){
		for (Word w: wordList){
			
		}
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
