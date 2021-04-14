package json;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDrivenTestingUsingJson {
	WebDriver driver;
	@BeforeClass
	void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		
	}
	@AfterClass
	void tearDown(){
		driver.close();
		
	}
	//we are mentioning from where we are getting the data
	@Test(dataProvider="dp")
	//here wea are receving the data from dataprovider and storing it in a variable data whose return type is string
	void login(String data) throws InterruptedException{
		/*
		 * here we are receiving the data as array
		 * which contains 2 data in every object
		 * so we have to split the data
		 */
		//whenever comma occurs split the data
		String users[]=data.split(",");//data which we are getting after split save as string
		
		driver.get("https://demo.actitime.com/login.do");
			driver.findElement(By.id("username")).sendKeys(users[0]);
			Thread.sleep(3000);
			driver.findElement(By.name("pwd")).sendKeys(users[1]);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[.='Login ']")).click();
			Thread.sleep(4000);
		
		
	}
	@DataProvider(name="dp")
	String[] readData() throws IOException, ParseException {
		JSONParser js=new JSONParser();
		FileReader f=new FileReader("./data/user.json");
		//parsing the data to java object
		Object obj = js.parse(f);
		//typecasting the obj to jsonobject
		JSONObject jsonobj = (JSONObject) obj;
		//again typcasting to get the array present in jsonobject
		JSONArray userloginarray=(JSONArray) jsonobj.get("userlogin");
		//we have to return data in java array ,so the size of the array should be equla to size off json array
		String arr[]=new String[userloginarray.size()];
		for(int i=0;i<userloginarray.size();i++) {
			JSONObject userlogin = (JSONObject)userloginarray.get(i);
			String userid = (String)userlogin.get("userid");
			String password = (String)userlogin.get("password");
			//here we are getting the size of arrayy and separating the data with comma
			arr[i]=userid+","+password;
			
		}
		//returning the data after foperation in for loop
		return arr;
		
	}

}
