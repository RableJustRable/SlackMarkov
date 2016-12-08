package slackMarkov;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
	private String id;
	private String displayName;
	private ArrayList<Word> wordList;
	private ArrayList<String> startingWords;
	private int totalMessages;
	
	User(String id, String displayName){
		this.id = id;
		this.displayName = displayName;
		this.wordList = new ArrayList<Word>();
		this.startingWords = new ArrayList<String>();
		this.totalMessages = 0;
	}
	
	/* Adds message to things the user has said and distrubutes it to the according words in the wordlist
	 * Pre-Req: a message to give it
	 * Post-req: nothing 
	 */
	public void addMessage(String message){
		String splitMessage[] = message.split(" "); 
		
		Word prevWord = null; //stores previous word in order to reference it later
		
		for (int i = 0; i<splitMessage.length;i++){
			if (i==0){
				prevWord = this.findWord(splitMessage[i]);
				startingWords.add(splitMessage[i]);
			}
			else{
				prevWord.addWordToFollowList(splitMessage[i]);
				prevWord = this.findWord(splitMessage[i]);
			}
			if (i==splitMessage.length-1){
				this.findWord(splitMessage[i]).incEndFreq();
			}
		}
		
		
		this.totalMessages++;
	}
	
	/* Finds the word object in the word list based on the string provided. Creates new word object in the list if it cannot find the word object
	 * pre-req: word to search for
	 * post: Word object found in list or a newly created one
	 */
	private Word findWord(String word){
		for (Word w: wordList){
			if (w.getName().equals(word)){
				return w;
			}
		}
		//we did not find the word given, create and object for it and return it
		Word temp = new Word(word);
		wordList.add(temp);
		return findWord(word);
	}
	
	/*Generates a message using the wordList and a markov chain type algoritm
	 * Pre-req: wordList with initialized words in it.
	 * Post-req: a generated message
	 */
	public String generateMessage(){
		StringBuilder sb = new StringBuilder();
		String nextWord = this.getStartingWord();
		while(nextWord!=null){
			sb.append(nextWord);
			sb.append(" ");
			Word temp = findWord(nextWord);
			nextWord = temp.getNextWord();
		}
		
		String output = sb.toString();
		
		return output;
	}
	
	public String getName(){
		return displayName;
	}
	
	public int getTotalMessages(){
		return this.totalMessages;
	}
	
	/* Chooses a random word from the starting wordlist, weighted according to frequency.
	 * pre-req: starting list with 1 or more word in it
	 * post: random word from the starting list
	 */
	private String getStartingWord(){
		String output = null;
		int totalWords = startingWords.size();
		int chosenWordIndex = (int) Math.floor((Math.random()*(totalWords)));
		if (chosenWordIndex<totalWords){
			output = startingWords.get(chosenWordIndex);
		}
		return output;
	}
	
	public String getID(){
		return id;
	}
	//dumps all data on the user. Pretty much useless outside testing
	public String dump(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName());
		sb.append("\n");
		sb.append(this.getID());
		sb.append("\n");
		sb.append("Starting Words:\n");
		//write out starting wordlist
		for (String s:startingWords){
			sb.append(s);
			sb.append(" ");
		}
		
		sb.append("\n");
		
		sb.append("Full Wordlist:\n");
		for (Word w:wordList){
			sb.append(w.getName());
			sb.append(" ");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	public static void main(String args[]){
		User temp = new User("4","Test User");
		temp.addMessage("/giphy stroke");
		temp.addMessage("stroke me");
		System.out.println(temp.dump());
		System.out.println(temp.generateMessage());
		
	}
	
}
