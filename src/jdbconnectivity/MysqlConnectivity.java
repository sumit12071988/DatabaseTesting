package jdbconnectivity;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;

public class MysqlConnectivity {

	public static void main(String[] args) throws SQLException {
		
		String host = "localhost";
		String port = "3306";
		String dbSchemaName = "qadbt";
		String userName = "root";
		String password = "#Adm4n2020";
		
		// To ESTABLISH a connection b/w JAVA and MYSQL using JDBC driver
		Connection con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbSchemaName+"", userName, password);
		
		
		// Creating a Statement object for handling SQL QUERY EXECUTIONS STATEMENTS
		Statement s = con.createStatement();
		
		
		// EXECUTE THE QUERY and store the O/p of the query in a collection called "Result Set" 
		ResultSet rs = s.executeQuery("select * from qadbt.credentials where scenario ='outstbalancecard';");
		
		
		// By Default the focus of Resultset Object "rs" will be at 0th index or base index, which doesn't hold any data. 
		//		Thus we need to move its focus to next data.
		
		//	Traversing inside result set using While loop to check IF data is found AFTER moving focus to 1st Index, then perform actions
		while(rs.next()) {		
		
		System.out.println(rs.getString("password"));
		System.out.println(rs.getString("username"));
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Batto\\Desktop\\JARS\\ChromeDriver\\ChromeAboveV81\\chromedriver.exe");		
		WebDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(rs.getString("username"));
		driver.findElement(By.xpath("//input[@name='pw']")).sendKeys(rs.getString("password"));
		
		
		
		}
	}
}
