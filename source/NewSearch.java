import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewSearch extends JPanel
{
	private JPanel upperPanel, lowerPanel, mainPanel;
	private JButton searchButton;
	private JTextField searchBar;
	private JTextArea searchBox = new JTextArea(20, 60);
	private JScrollPane scroll = new JScrollPane(searchBox);
    private JCheckBox orToggle;
    private PrintWriter writer;
	
	/**
	 * Constructor for the NewSearch GUI
	 */
	public NewSearch()
	{
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		searchButton = new JButton("Search");
		searchBar = new JTextField("", 40);
		mainPanel = new JPanel();
		orToggle = new JCheckBox("Use Logical OR");
	    orToggle.setSelected(false);
		
	    try 
		{
			writer = new PrintWriter("resultsHistory.txt", "UTF-8");
		} 
		catch (FileNotFoundException | UnsupportedEncodingException e2) 
		{
			e2.printStackTrace();
		}
		
		upperPanel.add(searchBar);
		
		NewButtonListener listener = new NewButtonListener();
		
		searchButton.addActionListener(listener);
		upperPanel.add(searchButton);
		upperPanel.add(orToggle);
		
		lowerPanel.add(scroll);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
		mainPanel.add(upperPanel);
		mainPanel.add(lowerPanel);
		
		add(mainPanel);
		
	}
	
	/**
	 * Takes in a printwriter, a key, and text and prints to a file. 
	 * Used to print the search results to a separate file.  
	 * 
	 * @param writer - PrinterWriter object
	 * @param key - the search term(s)
	 * @param text - the search results
	 * 
	 */
	private void printToFile(PrintWriter writer, String key, String resultsLabel, String text)
			throws FileNotFoundException, UnsupportedEncodingException
	{
		writer.println("Search for: " + key);
		writer.println(resultsLabel + "\n");
		writer.println("\n" + text + "\n\n");
		writer.close();
	}

	private class NewButtonListener implements ActionListener
	{
		/**
		 * ActionListener for the search button 
		 * 
		 * @param e - ActionEvent created when the button is pressed
		 */
		public void actionPerformed(ActionEvent e)
		{
			Results r = new Results();
			Search s = new Search();
			int size = 0;
			String resultsLabel;
			
			if (searchButton == e.getSource())
			{
				if (orToggle.isSelected())
		        {
					size = s.getTermsOr(Driver.getSources(), searchBar.getText()).size();
					resultsLabel = size + " Results for \"" + searchBar.getText() + "\":\n\n";
		        	searchBox.setText(resultsLabel + r.displayResults(s.getTermsOr(Driver.getSources(), searchBar.getText())));
		        }
		        else
		        {
		        	size = s.getTermsAnd(Driver.getSources(), searchBar.getText()).size();
		        	resultsLabel = size + " Results for \"" + searchBar.getText() + "\":\n\n";
		        	searchBox.setText(resultsLabel + r.displayResults(s.getTermsAnd(Driver.getSources(), searchBar.getText())));
		        }
				searchBox.setCaretPosition(0);
				
				try 
				{
					printToFile(writer, searchBar.getText(), resultsLabel, searchBox.getText());
				} 
				catch (FileNotFoundException | UnsupportedEncodingException e1) 
				{
					e1.printStackTrace();
				}
				
				Driver.refresh();
			}
		}
	}
}
