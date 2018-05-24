package genericCheckpointing.util;

public class DeserializeTypes {
	public static String GetVariableFormat(String fieldName, String fieldType)
	{
		if(fieldType.contains("String"))
			fieldType = "string";
		
		return "<" + fieldName + " xsi:type=\"xsd:" + fieldType + "\">(.+?)</" + fieldName + ">";
	}
	
	public static String GetClassMatchRegex()
	{
		return "<complexType xsi:type=\"(.+?)\">";
	}
}
