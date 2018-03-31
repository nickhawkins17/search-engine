import java.util.*;
import java.io.*;

public class Source 
{
	String fileName;
	String title;
	String author; 
	String text;
	ArrayList<Paragraph> list;
	
	/**
	 * Source constructor
	 * @param fileName - fileName of the source
	 */
	public Source(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * Sets the list global variable of all paragraphs in the source
	 * by reading them in
	 * 
	 * throws IOException - when the file does not exist
	 */
	public void listParagraphs() throws IOException
	{
		list = new ArrayList<Paragraph>();
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));

	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	        	if (line.length() > 6)
	        	{
		        	if (line.substring(0, 6).equals("Title:"))
		        	{
		        		this.title = line.substring(6, line.length());
		        	}
		        	else if (line.substring(0, 7).equals("Author:"))
		        	{
		        		this.author = line.substring(7, line.length());
		        	}
	        	}
	        	
	            sb.append(line);
	            sb.append("\n");
	   
	            if (line.length() >= 2)
	            {
		            if (line.charAt(line.length() - 1) == '.')
		        	{
		        		list.add(new Paragraph(format(sb.toString()), author, title, list.size() + 1));
		        		sb.setLength(0);
		        	}
	            }
	            line = br.readLine();
	            
	        }
	    } finally {
	        br.close();
	    }

	    if (this.title != null && this.title.length() > 1)
	    	setTitle(this.title);
	    else
	    	setTitle("Unknown Title");

	    if (this.author != null && this.author.length() > 1)
	    	setAuthor(this.author);
	    else
	    	setAuthor("Unknown Author");
	}
	
	/**
	 * Getter method for list
	 * 
	 * @return list - global variable ArrayList of Paragraph objects
	 */
	public ArrayList<Paragraph> getParagraphList()
	{
		return list;
	}
	
	/**
	 * Helper method for listParagraphs() that sets the Title of the
	 * source when it is found
	 * 
	 * @param text - the name of the source
	 */
	public void setTitle(String text)
	{
		this.title = text;
		for (int ii = 0; ii < list.size(); ii++)
		{
			list.get(ii).setTitle(title);
		}
	}
	
	/**
	 * Helper method for listParagraphs() that sets the Author of the
	 * source when it is found
	 * 
	 * @param text - the name of the author
	 */
	public void setAuthor(String text)
	{
		this.author = text;
		for (int ii = 0; ii < list.size(); ii++)
		{
			list.get(ii).setAuthor(author);
		}
	}
	
	/**
	 * Getter method for the fileName
	 * 
	 * @return fileName - the name of the file
	 */
	public String getFileName()
	{
		return fileName;
	}
	
	/**
	 * Helps format the paragraph objects, removes extra
	 * spaces, newlines, periods, and other characters
	 * 
	 * @param text - the text of the paragraph
	 * @return sb.toString() - the formatted version of text
	 */
	public String format(String text)
	{
		String ret;
		ret = text.replaceAll("\\s+", " ");
		ret.replaceAll("_",  "");
		ret.replaceAll("--",  "");
		
		if (ret.charAt(0) == ' ')
		{
			ret = ret.substring(1, ret.length() - 1);
		}
		
		StringBuilder sb = new StringBuilder(ret);

		int ii = 0;
		while ((ii = sb.indexOf(" ", ii + 45)) != -1) {
		    sb.replace(ii, ii + 1, "\n");
		}
		
		return sb.toString();
	}
}
