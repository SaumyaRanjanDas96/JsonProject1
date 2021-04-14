package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJsonFile {
	private static final int i = 0;

	public static void main(String[] args) throws IOException, ParseException {
		JSONParser js=new JSONParser();
		FileReader f=new FileReader("./jsonfile/employee.json");
		Object obj = js.parse(f);
		JSONObject jsobj=(JSONObject) obj;
		String fname = (String) jsobj.get("firstname");
		String lname=(String) jsobj.get("lname");
		System.out.println("first name :"+ fname);
		System.out.println("last name"+ lname);
		JSONArray array = (JSONArray) jsobj.get("adress");
		
		for(int i=0;i<array.size();i++);
		{
			
			JSONObject adress=(JSONObject) array.get(i);
			String street=(String) adress.get("street");
			System.out.println(street);
		}
	}

}