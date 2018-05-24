package genericCheckpointing.util;

import java.util.Vector;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

/**
 * Driver --- Helper class for driver code
 * @author    Vinit Kadam
 */
public class GenericCheckPointingHelper 
{
	private Mode mode;
	private int NUM_OF_OBJECTS;
	private String fileName;
	private String xmlContents;
	
	/**
	   * Constructor: to accept required argument from driver
	   * @param mode,num_of_objects, fileName
	   * @return No return value.
	   */
	public GenericCheckPointingHelper(Mode modeIn,int num_of_objectsIn, String fileNameIn)
	{
		mode= modeIn;
		NUM_OF_OBJECTS = num_of_objectsIn;
		fileName = fileNameIn;
	}
	
	/**
	   * CreateProxy: to create proxy
	   * @param handler
	   * @return StoreRestoreI - proxy which is created.
	   */
	public StoreRestoreI CreateProxy(StoreRestoreHandler handler)
	{
		ProxyCreator pc = new ProxyCreator();
		
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
				 new Class[] {
				     StoreI.class, RestoreI.class
				 }, 
				 handler
				 );
		return cpointRef;
	}
	
	/**
	   * SerialiseObject: serialize object from random values
	   * @param handler, cpointRef
	   * @return Vector<SerializableObject> - required for comparison.
	   */
	public Vector<SerializableObject> SerialiseObject(StoreRestoreHandler handler, StoreRestoreI cpointRef)
	{
		Vector<SerializableObject> myRecordOldList = new Vector<SerializableObject>();
		String xmlContent = "";
		if(mode == Mode.serdeser)
		{
			//handler.OpenFile(fileName);
			MyAllTypesFirst myFirst;
			MyAllTypesSecond  mySecond;
	
			// Use an if/switch to proceed according to the command line argument
			// For deser, just deserliaze the input file into the data structure and then print the objects
			// The code below is for "serdeser" mode
			// For "serdeser" mode, both the serialize and deserialize functionality should be called.
	
			// create a data structure to store the objects being serialized
		        // NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
			// passed as "N" from the command line. 
			
			for (int i=0; i<NUM_OF_OBJECTS; i++) {
	
			    // use the index variable of this loop to change the values of the arguments to these constructors
			    myFirst = new MyAllTypesFirst(Helper.getRandomInt(),Helper.getRandomLong(), "vinit kadam " + Helper.getRandomInt(), Helper.getRandomBoolean(), Helper.getRandomInt());//...
			    mySecond = new MyAllTypesSecond(Helper.getRandomDouble(), Helper.getRandomFloat(), (short)Helper.getRandomInt(), Helper.getRandomCharacter(), Helper.getRandomDouble());//..
	
			    xmlContent += ((StoreI) cpointRef).writeObj(myFirst, 0, "XML");
			    xmlContent += ((StoreI) cpointRef).writeObj(mySecond, 0, "XML");
			    myRecordOldList.add(myFirst);
			    myRecordOldList.add(mySecond);
	
			}
			//handler.CloseFile();
		}
		xmlContents = xmlContent;
		return myRecordOldList;
	}
	
	public String GetXmlContent()
	{
		return xmlContents;
	}
	
	/**
	   * DeserializeObject: deserialize object from file
	   * @param handler, cpointRef
	   * @return Vector<SerializableObject> - required for comparison.
	   */
	public Vector<SerializableObject> DeserializeObject(StoreRestoreHandler handler, StoreRestoreI cpointRef)
	{
		Vector<SerializableObject> myRecordList = new Vector<SerializableObject>();
		SerializableObject myRecordRet;
		handler.OpenFileToRead(fileName);
		// create a data structure to store the returned ojects
		while(true) //for (int j=0; j<2*NUM_OF_OBJECTS; j++)
		{
		    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
		    
		    if(myRecordRet == null)
		    	break;
		    myRecordList.add(myRecordRet);
		}
		handler.CloseFileReading();
		return myRecordList;
	}
	
	/**
	   * CompareObjects: comparing 2 objects
	   * @param myRecordOldList, myRecordList - list to be compared
	   * @return no return value
	   */
	public void CompareObjects(Vector<SerializableObject> myRecordOldList, Vector<SerializableObject> myRecordList)
	{
		if(mode == Mode.serdeser)
		{
			int counter = 0;
			for(int i=0;i<2*NUM_OF_OBJECTS; i++)
			{
				if(myRecordOldList.get(i) != null && myRecordList.get(i) != null)
				{
					boolean flag = myRecordOldList.get(i).equals(myRecordList.get(i));  
					if(!flag)
						counter++;
				}
			}
			System.out.println(counter + " mismatched objects");
		}
	}
	
	/**
	   * PrintDeserializedObject: to print deserialized object
	   * @param myRecordList - to print values
	   * @return no return value
	   */
	public void PrintDeserializedObject(Vector<SerializableObject> myRecordList)
	{
		for (int j=0; j<myRecordList.size(); j++) {

		    Helper.printObject(myRecordList.get(j));
		}
	}
}
