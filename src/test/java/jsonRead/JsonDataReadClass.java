package jsonRead;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JsonDataReadClass {
	public static By b;
    public static WebDriver  driver;
    public static Properties prop;
	public static By getBy(String type, String locator) throws IOException {
		try {

			switch (type) {
	        case "xpath" :
	                     b= By.xpath(locator);break;
	        
	        case "css" :
	         	       b=By.cssSelector(locator);break;
	        
	        case "id" :
	                    	b= By.id(locator);break;
	           
	        case "tagname" : 
	                	  b=By.tagName(locator); break;
	           
	        case "classname" : 
	                  	b=By.className(locator); break;
	           
	        case "linktext" :
	                   	b=By.linkText(locator); break;
	           
	        case "partiallinktext" :
	        	           b=  By.partialLinkText(locator); break;
	           
	        default :
	            return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return b;
}
	public static void main(String[] args) throws IOException {
		
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		prop=new Properties();
		prop.load(file);
		
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	         
	    

		String jsonFile="src/test/resources/dataFiles/Elements.json";
		
		FileReader fileRead=new FileReader(jsonFile);
		
		JSONObject obj=(JSONObject) JSONValue.parse(fileRead);
		
		//System.out.println(obj);
		//this contains all json data
		JSONObject demoQ=(JSONObject) obj.get("DemoQA");
		
		//launch driver
		JSONObject url=(JSONObject) demoQ.get("URL");
		String urlplace=url.get("orangeHRM").toString();
		driver.get(urlplace);
		
		JSONObject fullname=(JSONObject) demoQ.get("FullName");
		
		String type=fullname.get("type").toString();
		String locator=fullname.get("locator").toString();
		
		By username=getBy(type, locator);
		System.out.println("user name enter...");
		driver.findElement(username).sendKeys(prop.getProperty("userName"));//sending username
		
        JSONObject password=(JSONObject) demoQ.get("password");
		
		String type1=password.get("type").toString();
		String locator1=password.get("locator").toString();
		
		By pass=getBy(type1, locator1);
		
		driver.findElement(pass).sendKeys(prop.getProperty("password"));//sending password
		System.out.println("password given...");
		
        JSONObject button=(JSONObject) demoQ.get("button");
		
		String btnType=button.get("type").toString();
		String btnLoc=button.get("locator").toString();
		
		By btn=getBy(btnType, btnLoc);
		
		driver.findElement(btn).click();//click button
		System.out.println("click button performed.");
		
		
	}
		
	
	
}
