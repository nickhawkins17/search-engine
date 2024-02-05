import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Search 
{
	/**
	 * Search Constructor
	 * 
	 */
	public Search()
	{
		super();
	}
	
	/**
	 * Finds the Paragraph objects where the search term(s) are found
	 * in an OR logical search
	 * 
	 * @param list - ArrayList of source objects
	 * @param text - text from the search bar
	 */
	public ArrayList<Paragraph> getTermsOr (ArrayList<Source> list, String text)
	{
		String[] words = tokenize(text);
		Map<Paragraph, Integer> results = new HashMap<Paragraph, Integer>();
		
		for (int jj = 0; jj < list.size(); jj++)
		{
			ArrayList<Paragraph> allParagraphs = new ArrayList<Paragraph>();
			allParagraphs = list.get(jj).getParagraphList();
			
			for (int kk = 0; kk < allParagraphs.size(); kk++)
			{
				int occurances = 0;
				Map<String, Integer> hash = allParagraphs.get(kk).getHashMap();
				
				for (int ll = 0; ll < words.length; ll++)
				{
					if (hash.containsKey(words[ll].toLowerCase()))
					{
						occurances += hash.get(words[ll]).intValue();
					}
				}
				
				if (occurances > 0)
				{
					results.put(allParagraphs.get(kk), occurances);
				}
				
			}
		}
		
		return sortMap(results);

	}
	
	/**
	 * Finds the Paragraph objects where the search term(s) are found
	 * in an AND logical search
	 * 
	 * @param list - ArrayList of source objects
	 * @param text - text from the search bar
	 */
	public ArrayList<Paragraph> getTermsAnd (ArrayList<Source> list, String text)
	{
		String[] words = tokenize(text);
		Map<Paragraph, Integer> results = new HashMap<Paragraph, Integer>();
		ArrayList<Paragraph> sorted = new ArrayList<Paragraph>();
		
		// Iterate through sources
		for (int jj = 0; jj < list.size(); jj++)
		{
			ArrayList<Paragraph> allParagraphs = new ArrayList<Paragraph>();
			allParagraphs = list.get(jj).getParagraphList();
			
			// Iterate through paragraphs of source
			for (int kk = 0; kk < allParagraphs.size(); kk++)
			{
				int occurances = 0;
				Map<String, Integer> hash = allParagraphs.get(kk).getHashMap();
				
				// Iterate through number of search terms
				for (int ll = 0; ll < words.length; ll++)
				{
					// Check if the term is found in the hashmap
					if (hash.containsKey(words[ll]))
					{
						occurances += hash.get(words[ll]).intValue();
					}
					else
					{
						occurances = 0;
						ll = words.length;
					}
				}
				
				if (occurances > 0)
				{
					results.put(allParagraphs.get(kk), occurances);
				}
			}
		}		
		
		return sortMap(results);

	}
	
	/**
	 * Takes in the String of user input from the search
	 * bar and tokenizes it into multiple search terms 
	 * if the string contains more than one word
	 * 
	 * @param text - user input from the search bar
	 * @return words - String array of all search terms
	 */
	public String[] tokenize(String text)
	{
		String[] words = text.split("\\s+");
		for (int ii = 0; ii < words.length; ii++) 
		{
		    words[ii] = words[ii].replaceAll("[^\\w]", "");
		}
		
		return words;
	}
	
	/**
	 * Sorts the Paragraphs based on the number of times 
	 * the search term(s) are found in the paragraph.
	 * 
	 * @param passedMap - HashMap of Paragraphs attached with value of 
	 * 					the number of times search terms were found
	 * @return list - ArrayList of paragraph objects sorted by key hits
	 */
	public ArrayList<Paragraph> sortMap (Map<Paragraph, Integer> passedMap)
	{
		ArrayList<Paragraph> list = new ArrayList<Paragraph>();
		
		while (passedMap.size() > 0)
		{
			Map.Entry<Paragraph, Integer> maxEntry = null;
			for (Map.Entry<Paragraph, Integer> entry : passedMap.entrySet())
			{
			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			    {
			        maxEntry = entry;
			    }
			}
			list.add(maxEntry.getKey());
			passedMap.remove(maxEntry.getKey());
		}
		
		return list;

	}
}
