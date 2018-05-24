package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {
	private double myDoubleT;
	private float myFloatT;
	private short myShortT;
	private char myCharT;
	private double myOtherDoubleT;
	
	public MyAllTypesSecond()
	{
		myDoubleT = 0;
		myFloatT = 0;
		myShortT = 0;
		myCharT = '$';
		myOtherDoubleT = 0;
	}
	
	public MyAllTypesSecond(double myDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn, double myOtherDoubleTIn)
	{
		myDoubleT = myDoubleTIn;
		myFloatT = myFloatTIn;
		myShortT = myShortTIn;
		myCharT = myCharTIn;
		myOtherDoubleT = myOtherDoubleTIn;
	}
	
	public double getmyDoubleT() {
		return myDoubleT;
	}
	public void setmyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}
	
	public float getmyFloatT() {
		return myFloatT;
	}
	public void setmyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}
	
	public short getmyShortT() {
		return myShortT;
	}
	public void setmyShortT(short myShortT) {
		this.myShortT = myShortT;
	}
	
	public char getmyCharT() {
		return myCharT;
	}
	public void setmyCharT(char myCharT) {
		this.myCharT = myCharT;
	}
	
	public double getmyOtherDoubleT() {
		return myOtherDoubleT;
	}
	public void setmyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
	}
	
	public int hashCode()
	{
		return (int) (myDoubleT * myFloatT * myShortT * myCharT * myOtherDoubleT);
	}
	
	public boolean equals(Object obj)
	{
		return this.hashCode() == ((SerializableObject)obj).hashCode();
	}
}
