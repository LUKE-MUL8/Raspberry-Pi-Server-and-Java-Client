package greater;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class ClientArray {
	
	public static String start(int value) throws Throwable {
		// Change the IP address to that of your computer. 
		InetAddress inet = InetAddress.getByName("192.168.43.3");
		Socket s = new Socket(inet, 8011);
		
		// Send the value to the server so server can decide what type of data to send back
		// 1 ->Temperature  2 ->Humidity
	    DataOutputStream writeToServer= new DataOutputStream(s.getOutputStream());
	    writeToServer.writeBytes(Integer.toString(value));
		
		//wait patiently....
		Thread.sleep(200);
		//input from the socket (s)
		InputStream in = s.getInputStream();
		
		//scanner object to access the input
		Scanner scanner = new Scanner(in);
		
		//inputLine is the next (only) line in our "document"
		String inputLine = scanner.nextLine();
		
		//System.out.println(inputLine);//print entire line
		scanner.close();
		return inputLine;
	}
	
	public static void main(String[] args)throws Throwable
	{
		// Creates instance of DataProcessor to get Data values.
		//DataProcessor doc = new DataProcessor();
		
     	//Un comment this 2 lines out to live test it
	    String recivedString = start(1);
		System.out.println("The data I've got: ");
	    System.out.print(recivedString);

	}
	
}
