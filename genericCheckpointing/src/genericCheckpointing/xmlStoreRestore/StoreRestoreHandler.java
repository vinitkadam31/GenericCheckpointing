package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.strategy.StrategyI;
import genericCheckpointing.strategy.XMLDeserialization;
import genericCheckpointing.strategy.XMLSerialization;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StoreRestoreHandler implements InvocationHandler {
	private FileProcessor fileProcessor;
	
	private XMLSerialization xmlSerialization;
	public StoreRestoreHandler()
	{
		xmlSerialization = new XMLSerialization();
		fileProcessor = new FileProcessor();
	}
	
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
	{
		if(m.getName().equals("writeObj"))
		{
			if((args[2] != null) && String.valueOf(args[2]).equals("XML"))
				return serializeData((SerializableObject)args[0], xmlSerialization);
		}
		if(m.getName().equals("readObj"))
		{
			return deserializeData(null, new XMLDeserialization(), (String)args[0]);
		}
	    // if the method is write
	       // if the wireFormat is XML
	           //  call serializeData(args[0], new XMLSerializationStrategy());
	   
	    // if statements to check if it is the read method so that
	    // deserialization can be done ... 
		return null;
	}
	
	public String serializeData(SerializableObject sObject, StrategyI sStrategy) {
        sStrategy.processInput(sObject, "");
        return sStrategy.getWireFormat();
        //fileProcessor.writeToFile(content);
	}

	public void OpenFile(String fileName)
	{
		fileProcessor.openFile(fileName);
	}
	
	public void CloseFile()
	{
		fileProcessor.closeFile();
	}
	
	public void OpenFileToRead(String fileName)
	{
		fileProcessor.OpenFileToRead(fileName);
	}
	
	public SerializableObject deserializeData(SerializableObject sObject, StrategyI sStrategy, String wireFormat) {
		String content = "";
		try {
			String currenLine = "";
			while((currenLine = fileProcessor.GetNextLine()) != null)
			{
				content += currenLine + "\n";
				if(currenLine.contains("</DPSerialization>"))
					break;
			}
		} catch (Exception e) {
			System.err.println("Deserialization - Error occured while reading file");
			System.exit(0);
		}
		finally
		{
			if(content == "")
				return null;
		}
		
        sStrategy.processInput(sObject, content);
        //fileProcessor.writeToFile(txt);
        return sStrategy.getDeserializedObject();
	}
	
	public void CloseFileReading()
	{
		fileProcessor.closeFileReading();
	}
}
