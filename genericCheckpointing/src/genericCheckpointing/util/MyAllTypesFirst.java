package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject {
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;
	private int myOtherInt;
	
	public MyAllTypesFirst()
	{
		myInt= 0;
		myLong = 0;
		myString = "Kadam";
		myBool = true;
		myOtherInt = 0;
	}
	
	public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn, int myOtherIntIn)
	{
		myInt= myIntIn;
		myLong = myLongIn;
		myString = myStringIn;
		myBool = myBoolIn;
		myOtherInt = myOtherIntIn;
	}
	
	public int getmyInt() {
		return myInt;
	}
	public void setmyInt(int myInt) {
		this.myInt = myInt;
	}
	
	public long getmyLong() {
		return myLong;
	}
	public void setmyLong(long myLong) {
		this.myLong = myLong;
	}
	
	public String getmyString() {
		return myString;
	}
	public void setmyString(String myString) {
		this.myString = myString;
	}
	
	public boolean getmyBool() {
		return myBool;
	}
	public void setmyBool(boolean myBool) {
		this.myBool = myBool;
	}
	
	public int getmyOtherInt() {
		return myOtherInt;
	}
	public void setmyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}
	
	public int hashCode()
	{
		return (int) (myInt * myLong * myString.hashCode() * (myBool ? 1 : 2) * myOtherInt);
	}
	
	public boolean equals(Object obj)
	{
		return this.hashCode() == ((SerializableObject)obj).hashCode();
	}
}
