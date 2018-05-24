package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Driver --- FileProcessor to read and write file.
 * @author    Vinit Kadam
 */
public class FileProcessor {
	private BufferedReader reader;
	BufferedWriter writer;
	public FileProcessor(String fileName) throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		
		FileReader fr = null;
		fr = new FileReader(fileName);
		
		reader = new BufferedReader(fr);
	}
	
	public FileProcessor(){
		
	}
	
	/**
	 * returning next line
	 */
	public String GetNextLine() throws IOException
	{		
		return reader.readLine();
	}
	
	public void openFile(String fileName)
	{
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			System.exit(0);
		}
	}
	
	public void closeFile()
	{
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			System.exit(0);
		}
	}
	
	public void writeToFile(String content, String fileName)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(content);
		    writer.close();
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		finally
		{
			
		}
	}
	
	public void GetNextLine(String fileName)
	{
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			System.exit(0);
		}
		
		reader = new BufferedReader(fr);
	}

	public void OpenFileToRead(String fileName) {
		// TODO Auto-generated method stub
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			System.exit(0);
		}
		
		reader = new BufferedReader(fr);
	}
	
	public void closeFileReading()
	{
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
