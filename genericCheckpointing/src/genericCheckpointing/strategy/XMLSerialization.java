package genericCheckpointing.strategy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import genericCheckpointing.util.Helper;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.SerializeTypes;

/**
 * Driver --- XML serialization strategy
 * @author    Vinit Kadam
 */
public class XMLSerialization implements StrategyI {

	String content = "";
	public XMLSerialization()
	{
		
	}
	
	/**
	   * processInput: to serialize object using reflection
	   * @param sObject,wireFormat
	   * @return No return value.
	   */
	@Override
	public void processInput(SerializableObject sObject, String wireFormat) {
		
		try
		{
			content = SerializeTypes.DPSerializationOpen();
			Object someObject = sObject;
			Class<? extends SerializableObject> c = sObject.getClass();
			content = SerializeTypes.ComplexTypeOpen(content, c);
			for (Field field : sObject.getClass().getDeclaredFields()) 
			{
			    field.setAccessible(true); 
			    
			    String methodName = "get" + field.getName();
		        Method getterMethod = c.getMethod(methodName);
		        Object invokeRet = getterMethod.invoke(someObject);
			    
			    if(Helper.CheckIfFieldCanInclude(field.getType().toString(), invokeRet))
			    	content = SerializeTypes.SetVariables(content, field.getName(), field.getType().toString(), invokeRet);
			}
			content = SerializeTypes.ComplexTypeClose(content);
			content = SerializeTypes.DPSerializationClose(content);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		finally
		{}
	}

	/**
	   * getWireFormat: to return serialized content
	   * @param no parameter
	   * @return content.
	   */
	@Override
	public String getWireFormat() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public SerializableObject getDeserializedObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
