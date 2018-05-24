package genericCheckpointing.util;

public class SerializeTypes {
	public static String DPSerializationOpen()
	{
		String txt = "<DPSerialization> \n";
		return txt;
	}
	
	public static String DPSerializationClose(String txt)
	{
		txt += "</DPSerialization>  \n";
		return txt;
	}
	
	public static String ComplexTypeOpen(String txt, Class<?> c)
	{
		txt += "  <complexType xsi:type=\"" + c.getName() + "\">  \n";
		return txt;
	}
	
	public static String ComplexTypeClose(String txt)
	{
		txt += "  </complexType>  \n";
		return txt;
	}
	
	public static String SetVariables(String txt, String name, String class1, Object value)
	{
		if(class1.toString().contains("String"))
			class1 = "string";
		
		txt += "    <" + name + " xsi:type=\"xsd:" + class1 + "\">" + value + "</" + name + ">  \n";
		return txt;
	}
}
