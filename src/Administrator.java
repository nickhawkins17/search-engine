import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Administrator extends JPanel implements ActionListener
{
	Container	contentPane;
	JPanel		center, south, editPanel, inputPanel, fileEditPanel;
	JLabel		file, author, title;
	JTextField	inputStringBox, authorStringBox, titleStringBox, fileEditStringBox;
	JButton		add, remove, edit, save, cancelSave, confirmRemove, cancelRemove;
	//JTextField messages;
	private JTextPane messages = new JTextPane();
	private int count;

	
	/**
	 * Constructor for Administrator
	 */
	public Administrator()
	{
		super();
		setupLayout();
	}
	
	/**
	 * Set up JPanels, JLabels, and the BorderLayout
	 */
	private void setupLayout()
	{
		inputPanel = new JPanel();
		JPanel messagePanel = new JPanel();
		JPanel editInfo = new JPanel();
		JPanel savePanel = new JPanel();
		fileEditPanel = new JPanel();
		authorStringBox = new JTextField("",10);
		titleStringBox = new JTextField("",10);
		setLayout(new BorderLayout());
		
		messages.setText("Add, Remove, or Edit a File");
		messages.setEditable(false);
		
		center = new JPanel();
		center.setLayout(new BorderLayout());
		JPanel centerTempPanel = new JPanel();
		
		// new
		editPanel = new JPanel();
		editPanel.setLayout(new BorderLayout());
		
		// new
		authorStringBox = new JTextField("",10);
		titleStringBox = new JTextField("",10);
    
    // new
		savePanel.setLayout(new FlowLayout());
		
		file = new JLabel("Filename:");
		author = new JLabel("Author:");
		title = new JLabel("Title:");
		JLabel fileEditLabel = new JLabel("Filename:");
		
		// new
		fileEditStringBox = new JTextField("",30);
		
		// new
		fileEditPanel.setLayout(new FlowLayout());
		fileEditPanel.add(fileEditLabel);
		fileEditPanel.add(fileEditStringBox);
		
		inputStringBox = new JTextField("",30);
		inputPanel.add(file);
		inputPanel.add(inputStringBox);
		centerTempPanel.add(inputPanel); // new
		
		messagePanel.add(messages);
		center.add(messagePanel, BorderLayout.NORTH);
		center.add(inputPanel, BorderLayout.CENTER);
		
		add(center);
		
		south = new JPanel();
		south.setLayout(new FlowLayout());
		
		add = new JButton();
		add.setText("Add");
		south.add(add);
        
		add.addActionListener(this);
		
		remove = new JButton();
		remove.setText("Remove");
		south.add(remove);
		remove.addActionListener(this);
		
		// new
		confirmRemove = new JButton();
		confirmRemove.setText("Confirm");
		inputPanel.add(confirmRemove);
		confirmRemove.addActionListener(this);
		
		// new
		cancelRemove = new JButton();
		cancelRemove.setText("Cancel");
		inputPanel.add(cancelRemove);
		cancelRemove.addActionListener(this);

		// new
		edit = new JButton();
		edit.setText("Edit");
		south.add(edit);
		edit.addActionListener(this);
		
		// new
		save = new JButton();
		save.setText("Save");
		savePanel.add(save);
		save.addActionListener(this);
		
		// new
		cancelSave = new JButton();
		cancelSave.setText("Cancel");
		savePanel.add(cancelSave);
		cancelSave.addActionListener(this);
		
		//all new
		editInfo.add(author, BorderLayout.NORTH);
		editInfo.add(authorStringBox, BorderLayout.NORTH);
		editInfo.add(title, BorderLayout.SOUTH);
		editInfo.add(titleStringBox, BorderLayout.SOUTH);
		editPanel.add(editInfo, BorderLayout.CENTER);
		editPanel.add(savePanel, BorderLayout.SOUTH);
		editPanel.add(fileEditPanel, BorderLayout.NORTH);
		
		inputPanel.setVisible(false);
		editPanel.setVisible(false);
		
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.NORTH);
		add(editPanel, BorderLayout.SOUTH);
	}
    
	/**
	 * ActionPerform for the Add/Remove buttons
	 * @param ae - ActionEvent of the button pressed
	 */
    public void actionPerformed(ActionEvent ae)
    {
    	Driver d = new Driver();
    	ArrayList<String> fileNames = new ArrayList<String>();
    	
    	if (ae.getSource() == add)
    	{
    		Driver.refresh();

	   		JFileChooser fileChooser = new JFileChooser();
	   		fileChooser.setMultiSelectionEnabled(true);
	   	    fileChooser.setCurrentDirectory(new java.io.File("."));
	   		int result = fileChooser.showOpenDialog(contentPane);
	   		
	   		if (result == JFileChooser.APPROVE_OPTION) {
	   			File[] selected = fileChooser.getSelectedFiles();
	   	    		for (int ii = 0; ii < selected.length; ii++)
	    		{
	    			fileNames.add(selected[ii].getName());
	    		}
    		}

    		for (int ii = 0; ii < fileNames.size(); ii++)
    		{
    			if (d.addSource(fileNames.get(ii)) == 0)
        			messages.setText(messages.getText() + "\nFile \"" + fileNames.get(ii) + "\" successfully added.");
        		else
        			messages.setText(messages.getText() + "\nFile \"" + fileNames.get(ii) + "\" could not be added.");
    		}
    		
    		fileNames.clear();
    	
    		inputStringBox.setText("");
    	}
    	
    	else if (ae.getSource() == remove)
    	{	
    	  inputPanel.setVisible(true);
    	  editPanel.setVisible(false);
    	  messages.setText("Remove a file from the database");
    	}
    	
    	else if (ae.getSource() == confirmRemove)
    	{
    	  inputPanel.setVisible(false);
    	  
    	  if (d.removeSource(inputStringBox.getText()) == 0)
        {
          messages.setText("File \"" + inputStringBox.getText() + "\" successfully removed.");
          count++;
        }
        else
        {
          messages.setText("File \"" + inputStringBox.getText() + "\" is not in the database."); 
        }
    	  
    	  inputStringBox.setText("");
    	}
    	else if (ae.getSource() == cancelRemove)
    	{
    	  inputPanel.setVisible(false);
    	  messages.setText("Add, Remove, or Edit a File");
    	}
    	
    	else if (ae.getSource() == edit)
    	{
    	  editPanel.setVisible(true);
    	  inputPanel.setVisible(false);
    	  messages.setText("Edit author and title information.");
    	}
    	
    	else if (ae.getSource() == save)
    	{
    	  if(authorStringBox.getText().length() > 0)
    	  {
    	    if(d.updateAuthor(authorStringBox.getText(), fileEditStringBox.getText()) == 0)
    	      messages.setText("Unable to edit. File \"" + fileEditStringBox.getText() + "\" is not in the database.");
    	    else
    	       messages.setText("Author and title information saved.");
    	  }
    	  if(titleStringBox.getText().length() > 0)
        {
          if(d.updateTitle(titleStringBox.getText(), fileEditStringBox.getText()) == 0)
            messages.setText("Unable to edit. File \"" + fileEditStringBox.getText() + "\" is not in the database.");
          else
            messages.setText("Author and title information saved.");
        }
    	  
    	  authorStringBox.setText("");
    	  titleStringBox.setText("");
    	  fileEditStringBox.setText("");
    	  
    	  editPanel.setVisible(false);
    	 
    	}
    	
    	else if (ae.getSource() == cancelSave)
    	{
    	  authorStringBox.setText("");
        titleStringBox.setText("");
    	  editPanel.setVisible(false);
    	  messages.setText("Add, Remove, or Edit a File");
    	}
    }
}
