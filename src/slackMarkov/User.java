package slackMarkov;

import java.util.ArrayList;

public class User {
	private String id;
	private String displayName;
	private ArrayList<Word> wordList;
	
	User(String id, String displayName){
		this.id = id;
		this.displayName = displayName;
	}
	
	public void addMessage(String message){
		
	}
	
}
