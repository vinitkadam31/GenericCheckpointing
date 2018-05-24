package genericCheckpointing.driver;

import java.util.Vector;

import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileDisplayInterfaceI;
import genericCheckpointing.util.GenericCheckPointingHelper;
import genericCheckpointing.util.Mode;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

/**
 * Driver --- program to implement proxy and strategy pattern.
 * @author    Vinit Kadam
 */
public class Driver {

	private FileDisplayInterfaceI displayInterfaceI;
	
	public Driver()
	{
		displayInterfaceI = new Results();
	}
	
	/**
	   * Entry point
	   * @param args string array containing the command line arguments.
	   * @return No return value.
	   */
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.validateArguments(args);
		Mode newMode = driver.validateMode(args[0]);
		int n = driver.validateN(args[1]);
			
		driver.PerformOperation(newMode, n, args[2]);//args[2]
	}
	
	/**
	   * Validating required command line arguments
	   * @param args string array containing the command line arguments.
	   * @return No return value.
	   */
	private void validateArguments(String[] args)
	{
		if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 argumnets.");
			System.exit(0);
		}
	}
	
	/**
	   * Validating mode
	   * @param mode to validate mode
	   * @return return enum mode.
	   */
	private Mode validateMode(String mode)
	{
		Mode newMode = null;
		if(mode.equalsIgnoreCase("serdeser"))
			newMode = Mode.serdeser;
		else if(mode.equalsIgnoreCase("deser"))
			newMode = Mode.deser;
		else
		{
			System.err.println("Incorrect mode");
			System.exit(0);
		}
		return newMode;
	}
	
	/**
	   * Validating number of object - whether its integer or not
	   * @param number to validate
	   * @return return int number
	   */
	public int validateN(String number)
	{
		try
		{
			return  Integer.parseInt(number);
		}
		catch(Exception e)
		{
			System.err.println("N - Not a integer, Number Format Exception");
			System.exit(0);
		}
		return 0;
	}
	
	/**
	   * driver code for proxy pattern
	   * @param mode, NUM_OF_OBJECTS fileName
	   * @return no return type
	   */
	public void PerformOperation(Mode mode, int NUM_OF_OBJECTS, String fileName)
	{
		//String fileName = "";
		
		/*if(mode == Mode.serdeser)
			fileName = "F:\\DP\\Project5\\checkpoint.txt";
		else
			fileName = "F:\\DP\\Project5\\input.txt";//inputFile*/
		GenericCheckPointingHelper pointingHelper = new GenericCheckPointingHelper(mode, NUM_OF_OBJECTS, fileName);
		
		
		
		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		StoreRestoreHandler handler = new StoreRestoreHandler();
		
		// create a proxy
		StoreRestoreI cpointRef = pointingHelper.CreateProxy(handler);
			
		// FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file
		//handler.invoke(cpointRef, m, args);
		Vector<SerializableObject> myRecordOldList = pointingHelper.SerialiseObject(handler, cpointRef);
		if(mode == Mode.serdeser)
		{
			String xmlContent = pointingHelper.GetXmlContent();
			displayInterfaceI.write(xmlContent, fileName);
		}
		
		Vector<SerializableObject> myRecordList = pointingHelper.DeserializeObject(handler, cpointRef);
		
		//if(mode == 2)
		{
			pointingHelper.PrintDeserializedObject(myRecordList);
		}
		
		// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)

		// FIXME: compare and confirm that the serialized and deserialzed objects are equal. 
		// The comparison should use the equals and hashCode methods. Note that hashCode 
		// is used for key-value based data structures
		
		pointingHelper.CompareObjects(myRecordOldList, myRecordList);
	}
}
