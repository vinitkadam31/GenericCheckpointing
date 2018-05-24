package genericCheckpointing.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

public class Helper {
	private static Random rand = new Random();
	/*public static boolean compareObjects(SerializableObject obj1, SerializableObject obj2)
	{
		try
		{
			 Class<? extends SerializableObject> objClass = obj1.getClass();
			 //System.out.println(objClass);
			 if(obj1.getClass() == obj2.getClass())
			 {
				 for (Field field : obj1.getClass().getDeclaredFields()) 
					{
					    field.setAccessible(true); // You might want to set modifier to public first.
					    //Object value = field.get(someObject);
					    
					    String methodName = "get" + field.getName();
				          Method getterMethod = objClass.getMethod(methodName);
				          Object invokeRet1 = getterMethod.invoke(obj1);
				          Object invokeRet2 = getterMethod.invoke(obj2);
				          
				          if(!CompareVariables(field.getType().toString(), invokeRet1, invokeRet2))
				        	  return false;   
					}
			 }
			 return true;
		}
		catch(Exception e)
		{
			 System.out.println(e);
			 return false;
		}
	 
	}
 
	private static boolean CompareVariables(String fieldType, Object obj1, Object obj2)
	{
		 switch(fieldType.toLowerCase())
			{
				case "int":
					if((int)obj1 != (int)obj2)
						return false;
					break;
				case "long":
					if((long)(obj1) != (long)(obj2))
						return false;
					break;
				case "short":
					if((short)(obj1) != (short)(obj2))
						return false;
					break;
				case "float":
					if((float)(obj1) != (float)(obj2))
						return false;
					break;
				case "double":
					//Double temp = (Double)(obj1);
					if(Double.compare((Double)(obj1), (Double)(obj2))!=0)
						return false;
					break;
				case "char":
					//Character temp1 = (Character)obj1;
					if(Character.compare((Character)obj1, (Character)obj2) != 0)
						return false;
					break;
				case "String":
					if(!obj1.equals(obj2))
						return false;
					break;
				case "boolean":
					if((boolean)(obj1) != (boolean)(obj2))
						return false;
					break;
				default:
					if(!obj1.equals(obj2))
						return false;
					break;
			}
		 return true;
	}*/
 
	public static void printObject(SerializableObject obj)
	{
		try
		{
			Class<? extends SerializableObject> objClass = obj.getClass();
			//content += objClass.getName() + "\n";
			System.out.println(objClass.getName());
			for (Field field : obj.getClass().getDeclaredFields()) 
			{
				field.setAccessible(true); // You might want to set modifier to public first.
				//Object value = field.get(someObject);
				    
				String methodName = "get" + field.getName();
			    Method getterMethod = objClass.getMethod(methodName);
			    Object invokeRet = getterMethod.invoke(obj);
			    
			    String fieldType = field.getType().toString();
			    if(fieldType.contains("String"))
			    	fieldType = "String";
			    
			    if (invokeRet != null) {
			    	//content += fieldType + "-" + field.getName() + "=" + invokeRet + "\n";
			    	System.out.println(fieldType + "-" + field.getName() + "=" + invokeRet);
			    }
			             
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		//content +="\n";
		System.out.println();
		//return content;
	}
	
	public static int getRandomInt()
	{
		return rand.nextInt(100);
	}
	public static double getRandomDouble()
	{
		return 0 + Math.random( ) * 600;
	}
	
	public static float getRandomFloat()
	{
		return (float) (0 + Math.random( ) * 100);
	}
	
	public static long getRandomLong()
	{
		return (long) (0 + Math.random( ) * 400);
	}
	
	public static boolean getRandomBoolean()
	{
		return rand.nextBoolean();
	}
	
	public static char getRandomCharacter()
	{
		return (char)(rand.nextInt(26) + 'a');
	}
	
	public static boolean CheckIfFieldCanInclude(String fieldType, Object value)
	{
		if(fieldType.equalsIgnoreCase("int") || fieldType.equalsIgnoreCase("long") || fieldType.equalsIgnoreCase("double"))// || fieldType.equalsIgnoreCase("short")  || fieldType.equalsIgnoreCase("float")
		{
			switch(fieldType.toLowerCase())
			{
				case "int":
					if((int)value < 10)
						return false;
					break;
				case "long":
					if((long)(value) <10)
						return false;
					break;
				case "short":
					if((short)(value) <10)
						return false;
					break;
				case "float":
					if((float)(value) <10)
						return false;
					break;
				case "double":
					if(Double.compare((Double)(value), new Double(10))< 0)
						return false;
					break;
			}
		}
		return true;
	}
}