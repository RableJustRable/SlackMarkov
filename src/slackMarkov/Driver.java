package slackMarkov;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class Driver {
	private static String UserName = System.getProperty("user.name");
	private static String testExportLocation = "C:\\Users\\"+UserName+"\\Dropbox\\SlackExport\\data\\ACM Flint Slack export Dec 8 2016";
	private static String giphyBotId = "B2U1PEUPP";
	private String exportLocation = testExportLocation;
	private ArrayList<String> channelNames;
	private ArrayList<User> userList;
	
	public void run() throws IOException{
		this.userList = importUsers(testExportLocation);
		this.channelNames = getChannelNames(testExportLocation);
		
		//add slackbot in manually since it isnt in the users.json file
		this.userList.add(new User("USLACKBOT","SlackBot"));
		
		System.out.println("Users: "+userList.size());
		System.out.println("Channels: "+channelNames.size());
		
		for (String s:channelNames){
			importChannel(s);
		}
		
		//importChannelSubFile(exportLocation + "\\dankmemes\\2016-11-29.json");
		
		for(User dipshit:userList){
			if (dipshit.getTotalMessages()>0){
				System.out.println(dipshit.dump());
			}
		}
		
		for (int i = 0; i < 10;i++){
			User temp = findUserByName("johncollins");
			System.out.println(temp.generateMessage());
		}
		
		//displayIt(new File(testExportLocation));
	}
	
	/* Takes in a channel name as string, imports all the messages to the respective users that made them.
	 * Pre-req: channel in the folder that is specified at run time
	 * post: fills userlist with words to correct users accordingly
	 */
	private void importChannel(String channelName) throws IOException{
		//TODO this
		String channelFilePath = exportLocation + File.separator + channelName;
		File node = new File(channelFilePath);
		int filesImported = 0;
		if (node.isDirectory()){
			String[] fileList = node.list();
			for (String s:fileList){
				if(s.length()>5){
					String jsonTest = s.substring(s.length()-5);
					if (jsonTest.equals(".json")){
						importChannelSubFile(node.getAbsolutePath()+File.separator+s);
						filesImported++;
					}
				}
			}
		}
		System.out.println("Channel "+channelName+" imported with "+filesImported+" files imported");
		
	}
	
	/*	takes in the full path to a one of a channels json file and sorts the messages out to the users.
	 *  pre-req: full path to the .json file to be importaed
	 *  post: channels messages sorted to users
	 */
	
	private void importChannelSubFile(String fullFilePath) throws IOException{
		//TODO this
		//System.out.println("Reading file: "+fullFilePath);
		String jsonContents = FileUtils.readFileToString(new File(fullFilePath));
		
		JSONArray messageArray = new JSONArray(jsonContents);
		for (int i = 0; i<messageArray.length();i++){
			JSONObject item = messageArray.getJSONObject(i);
			
			//check to see if its a giphy link
			if (item.has("bot_id")){
				try{
				if (item.getString("bot_id").equals(giphyBotId)){
					if (item.has("attachments")){
						JSONArray attachmentArray = item.getJSONArray("attachments");
						if (attachmentArray.length()>0){
							User temp = findUser(item.getString("user"));
							JSONObject jsontemp = attachmentArray.getJSONObject(0);
							temp.addMessage("/giphy "+jsontemp.getString("title"));							
						}
					}
				}
				}
				catch(JSONException e){
					//TODO maybe log something here? this is only here because sometimes a bot ID can be null which is annoying
				}
			}
			else if (!item.has("subtype")){
				User temp = findUser(item.getString("user"));
				temp.addMessage(item.getString("text"));
			}
			else if (item.has("subtype")){
				if (item.getString("user").equals("USLACKBOT")){
					User temp = findUser(item.getString("user"));
					temp.addMessage(item.getString("text"));
				}
			}
			
		}
	}
	
	/*Imports the users from the user.json folder its given. Iports as the user class, which contains both the unique slack ID as well as their username
	* Pre-req: A folder which has a valid user.json file
	* post: ArrayList full of user objects created using the users.json file
	*
	*/
	
	private static ArrayList<User> importUsers(String folder) throws IOException{
		ArrayList<User> output = new ArrayList<User>();
		
		String fullFilePath = folder+java.io.File.separator+"users.json";
		String jsonContents = FileUtils.readFileToString(new File(fullFilePath));
		
		//JSONObject userFileJson = new JSONObject(jsonContents);
		//JSONArray userArray = userFileJson.getJSONArray(null);
		
		JSONArray userArray = new JSONArray(jsonContents);
		for (int i=0; i<userArray.length();i++){
			JSONObject item = userArray.getJSONObject(i);
			output.add(new User(item.getString("id"),item.getString("name")));
		}
		
		return output;		
	}
	
/*	Finds the userobject within userlist that corresponds to the given id.
	 * 	pre: filled userlist
	 * 	post: userObject with given id
	 */
	private User findUser(String id){
		for (User u:userList){
			if (u.getID().equals(id)){
				return u;
			}
		}
		return null;
	}
	
	private User findUserByName(String name){
		for (User u:userList){
			if (u.getName().equals(name)){
				return u;
			}
		}
		return null;
	}

	private static ArrayList<String> getChannelNames(String folder) throws IOException{
		ArrayList<String> output = new ArrayList<String>();
		
		String fullFilePath = folder+java.io.File.separator+"channels.json";
		String jsonContents = FileUtils.readFileToString(new File(fullFilePath));
		
		//JSONObject userFileJson = new JSONObject(jsonContents);
		//JSONArray userArray = userFileJson.getJSONArray(null);
		
		JSONArray userArray = new JSONArray(jsonContents);
		for (int i=0; i<userArray.length();i++){
			JSONObject item = userArray.getJSONObject(i);
			//System.out.println(item.get("name"));
			output.add(item.getString("name"));
		}
		
		
		return output;
	}
	
	//Displays the file structure for a folder. here for reference. 
	public static void displayIt(File node){

		System.out.println(node.getAbsoluteFile());

		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				displayIt(new File(node, filename));
			}
		}

	}
	
	public static void main(String args[]){
		Driver program = new Driver();
		try {
			program.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
