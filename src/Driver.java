import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.io.*;


public class Driver extends JPanel
{
	static ArrayList<Source> sources = new ArrayList<Source>();

	private static JTabbedPane tabs;
	private ImageIcon icon = new ImageIcon("");
	
	private static NewSearch window;
	private static Administrator window2;
	private static JFrame frame = new JFrame();
	
	/**
	 * Constructor for the Driver 
	 */
	public Driver()
	{
		window = new NewSearch();
		window2 = new Administrator();
		
		tabs = new JTabbedPane();
		tabs.addTab("Search", icon, window, "Search");
		tabs.addTab("Admin", icon, window2, "Admin");
		add(tabs);
	}
	
	/**
	 * Main method
	 * 
	 * @param args - String array of arguments
	 */
	public static void main (String[] args)
	{

		Driver d = new Driver();
		Driver.build();
		
		/*
		d.addSource("theambassador.txt");
		d.addSource("ibm.txt");
		d.addSource("programmingBio.txt");
		d.addSource("hacker.txt");
		*/

	}
	
	/**
	 * Builds the Program's Search GUI
	 * 
	 */
	private static void build()
	{
		Driver search = new Driver();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(search, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Adds a source to the Source ArrayList in the main
	 * 
	 * @param fileName - String of the file name to be added
	 * @return -1 if the file was not added, 0 if the file
	 * 			was successfully added
	 */
	public int addSource(String fileName)
	{
		File f = new File(fileName);
		try
		{
			if(f.exists())
			{
				Source toAdd = new Source(fileName);
				sources.add(toAdd);
				sources.get(sources.size() - 1).listParagraphs();
				return 0;
			}
			else
				return -1;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return -1;
		}

	}
	
	/**
	 * Removes a source from the source ArrayList
	 * 
	 * @param fileName - name of the file to be removed
	 * @return -1 when the fileName doesn't exist, 0 if 
	 * 			the file was removed successfully
	 */
	public int removeSource(String fileName)
	{		
		for (int ii = 0; ii < sources.size(); ii++)
		{
			if (sources.get(ii).getFileName().equals(fileName))
			{
				sources.remove(ii);
				return 0;
			}
		}
			return -1;
	}
	
	/**
	 * Returns the ArrayList of the sources
	 * 
	 * @return sources - the ArrayList of source objects
	 */
	public static ArrayList<Source> getSources()
	{
		return sources;
	}
	
	/**
	 * Refreshes sizes of the changing labels 
	 * 
	 */
	public static void refresh()
	{
		frame.repaint();
		frame.revalidate();
	}

	/**
	 * Changes all the authors for a specific book
	 * @param text - name of the updated Author
	 * @param fileName - the filename to be updated
	 * 
	 * @return count - number of paragraphs updated
	 */
  public int updateAuthor(String text, String fileName)
  {
    int count = 0;
    
    for (int ii = 0; ii < sources.size(); ii++)
    {
      if (sources.get(ii).getFileName().equals(fileName))
      {
        sources.get(ii).setAuthor(text);
        count++;
      }
    }
    
    return count;
    
  }

	/**
	 * Changes all the titles for a specific book
	 * @param text - name of the updated title
	 * @param fileName - the filename to be updated
	 * 
	 * @return count - number of paragraphs updated
	 */
  public int updateTitle(String text, String fileName)
  {
    int count = 0;
    
    for (int ii = 0; ii < sources.size(); ii++)
    {
      if (sources.get(ii).getFileName().equals(fileName))
      {
        sources.get(ii).setTitle(text);
        count++;
      }
    }
    
    return count;
  }
}
