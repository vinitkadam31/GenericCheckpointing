package genericCheckpointing.util;

public class Results implements FileDisplayInterfaceI, StdoutDisplayInterfaceI {
	private FileProcessor fileProcessor;
	public Results()
	{
		fileProcessor = new FileProcessor();
	}
	
	@Override
	public void write(String content) {
		// TODO Auto-generated method stub
		System.out.println(content);
		System.out.println();
	}

	@Override
	public void write(String content, String outputFile) {
		// TODO Auto-generated method stub
		fileProcessor.writeToFile(content, outputFile);
	}

}
