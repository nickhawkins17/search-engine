import java.util.ArrayList;

public class Results 
{
	/**
	 * Constructor for Results
	 */
	public Results()
	{
		super();
	}
	
	/**
	 * Method that returns the formatted String of the 
	 * results to be displayed
	 * 
	 * @param list - list of Paragraphs to be displayed
	 * @return sb.toString() - the formatted string of all paragraphs
	 */
	public String displayResults(ArrayList<Paragraph> list)
	{
		StringBuilder sb = new StringBuilder();
		if (list.size() == 0)
		{
			sb.append("");
		}
		else
		{
			for (int ii = 0; ii < list.size(); ii++)
			{
				sb.append(list.get(ii).getOrdinalNumber());
				sb.append(" ");
				sb.append(list.get(ii).getTitle());
				sb.append(" - ");
				sb.append(list.get(ii).getAuthor());
				sb.append("\n");
				sb.append(getFirstSentence(list.get(ii).getText()));
				sb.append("\n\n");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Method that returns the formatted String of the 
	 * results to be displayed.
	 * 
	 * @param text - the text of the paragraph
	 * @return text - the first paragraph of the text
	 */
	public String getFirstSentence(String text)
	{
		for (int ii = 0; ii < text.length(); ii++)
		{
			if (text.charAt(ii) == '.' && ii > 79)
			{
				return text.substring(0, ii + 1);
			}
		}
		
		return text;
	}
}
