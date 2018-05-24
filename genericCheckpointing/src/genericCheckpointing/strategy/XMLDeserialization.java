package genericCheckpointing.strategy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import genericCheckpointing.util.DeserializeTypes;
import genericCheckpointing.util.SerializableObject;

/**
 * Driver --- XML deserialization strategy
 * @author    Vinit Kadam
 */
public class XMLDeserialization implements StrategyI {

	private SerializableObject serializableObject;
	
	/**
	   * processInput: to deserialize object using reflection
	   * @param sObject,wireFormat
	   * @return No return value.
	   */
	@Override
	public void processInput(SerializableObject sObject, String wireFormat) {
		
		Pattern pattern = Pattern.compile(DeserializeTypes.GetClassMatchRegex());
		Matcher matcher = pattern.matcher(wireFormat);
		matcher.find();
		
		Class<?> cls = null;
		try {
			cls = Class.forName(matcher.group(1));
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		SerializableObject clsInstance = null;
		try {
			clsInstance = (SerializableObject) cls.newInstance();
		} catch (InstantiationException e) {
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.out.println(e);
		}
		finally{}
		try
		{
			for (Field field : clsInstance.getClass().getDeclaredFields()) 
			{
			    String methodName = "set" + field.getName();
		        Method setter = cls.getMethod(methodName, field.getType());
		        pattern = Pattern.compile(DeserializeTypes.GetVariableFormat(field.getName(), field.getType().toString()));// "<" + field.getName() + " xsi:type=\"xsd:" + field.getType() + "\">(.+?)</myInt>");
		  		matcher = pattern.matcher(wireFormat);
		  		
		  		if(!matcher.find())
		  			continue;
		  		switch(field.getType().toString().toLowerCase())
		  		{
		  			case "int":
		  				setter.invoke(clsInstance, Integer.parseInt(matcher.group(1)));
		  				break;
		  			case "long":
		  				setter.invoke(clsInstance, Long.parseLong(matcher.group(1)));
		  				break;
		  			case "short":
		  				setter.invoke(clsInstance, Short.parseShort(matcher.group(1)));
		  				break;
		  			case "float":
		  				setter.invoke(clsInstance, Float.parseFloat(matcher.group(1)));
		  				break;
		  			case "double":
		  				setter.invoke(clsInstance, Double.parseDouble(matcher.group(1)));
		  				break;
		  			case "char":
		  				setter.invoke(clsInstance, ((String)matcher.group(1)).charAt(0));
		  				break;
		  			case "String":
		  				setter.invoke(clsInstance, (String)matcher.group(1));
		  				break;
		  			case "boolean":
		  				setter.invoke(clsInstance, Boolean.parseBoolean(matcher.group(1)));
		  				break;
		  			default:
		  				setter.invoke(clsInstance, (String)matcher.group(1));
		  				break;
		  		}
		  		
		  		
		          //if(field.getName().equals("myInt") )
		        //setter.invoke(clsInstance, temp);
		          
			    
			    	//field.set(someObject, "34");
			    /*if (value != null) {
			        System.out.println(field.getType() + "-" + field.getName() + "=" + value);
			    }*/
			    
			    //content = SerializeTypes.SetVariables(content, field.getName(), field.getType(), invokeRet);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		serializableObject = clsInstance;
	}

	@Override
	public String getWireFormat() {
		return null;
	}

	/**
	   * SerializableObject: to deserialized object
	   * @param no parameter
	   * @return object.
	   */
	@Override
	public SerializableObject getDeserializedObject() {
		return serializableObject;
	}

}
