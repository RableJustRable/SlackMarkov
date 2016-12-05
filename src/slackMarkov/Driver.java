package slackMarkov;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class Driver {
	private static String testExportLocation = "C:\\Users\\Shadow4\\Dropbox\\SlackExport\\data\\ACM Flint Slack export Nov 30 2016";
	
	public static void main(String args[]) throws IOException{
		JSONObject obj = new JSONObject();
		importUsers(testExportLocation);
	}
	
	private static ArrayList<User> importUsers(String folder) throws IOException{
		ArrayList<User> output = new ArrayList<User>();
		
		String fullFilePath = folder+java.io.File.separator+"users.json";
		String jsonContents = FileUtils.readFileToString(new File(fullFilePath));
		
		JSONObject userFileJson = new JSONObject();
		
		
		
		return output;		
	}
	

}
