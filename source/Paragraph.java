import java.util.*;

public class Paragraph {

	String text;
	String author;
	String title;
	int ordinalNumber;
	
	/**
	 * Constructor for a Paragraph object
	 * 
	 * @param text - text of the paragraph
	 * @param author - name of the author
	 * @param title - title of the book/file
	 * @param ordinalNumber - paragraph's order number in the source
	 */
	public Paragraph(String text, String author, String title, int ordinalNumber)
	{
		this.text = text;
		this.author = author;
		this.title = title;
		this.ordinalNumber = ordinalNumber;
	}
	
	/**
	 * Getter method for text
	 * 
	 * @return text - text of the paragraph
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Getter method for author
	 * 
	 * @return author - author of the source
	 */
	public String getAuthor()
	{
		return author;
	}
	
	/**
	 * Getter method for title
	 * 
	 * @return title - title of the source
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Getter method for ordinal number
	 * 
	 * @return ordinalNumber - paragraph number in the source
	 */
	public int getOrdinalNumber()
	{
		return ordinalNumber;
	}

	/**
	 * Setter method for author
	 * 
	 * @param text - name of the author
	 */
	public void setAuthor(String text)
	{
		author = text;
	}
	
	/**
	 * Setter method for title
	 * 
	 * @return text - title of the source
	 */
	public void setTitle(String text)
	{
		title = text;
	}
	
	/**
	 * Creates a HashMap of the paragraph's tokens to count
	 * each word's occurrences in the paragraph
	 * 
	 * @return words - HashMap of the paragraph's tokens
	 */
	public Map<String, Integer> getHashMap()
	{
		Map<String, Integer> words = new HashMap<String, Integer>();
		String[] arr = this.text.split(" ");
		
		for (int ii = 0; ii < arr.length; ii++)
		{
			String key = arr[ii];
			Integer frequency = (Integer)words.get(key);
			
			if (frequency == null)
			{
				frequency = 1;
			}
			else
			{
				int value = frequency.intValue();
				frequency = new Integer(value + 1);
			}
			words.put(key.toLowerCase(), frequency);
		}
		
		return words;
	}
}
