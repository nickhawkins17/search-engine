# GutenSearch

GutenSearch is a search engine written in Java.

## Table of Contents
* [Setup](#setup)
* [Usage](#usage)
* [Search Functionality](#search-functionality)
* [Admin Functionality](#admin-functionality)
* [Authors](#authors)

## Setup

Install the latest version of OpenJDK (MacOS):
```bash
brew install openjdk
echo 'export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

## Usage

Run the GutenSearch.jar executable:

```bash
java -jar GutenSearch.jar
```

Follow the below instructions on Search and Admin functionality. 

## Search Functionality
### Displaying Search Results:
We have added six files to the database and are now ready to search. If we search for the word “program” and hit the 
“Search” button, it immediately returns 96 results. The first line lists the number of results in which the word 
“program” was found in a paragraph. The paragraphs are listed in the results by the number of occurrences of the key 
word. The top paragraph listed has the most occurrences of the word “program”, etc. The top of each paragraph listed 
displays the paragraph’s ordinal number, the number of the paragraph in its respective source, as well as the source’s 
title and author. Instead of displaying the entire paragraph, GutenSearch only displays the first 1-2 sentences of the 
paragraph, depending on the length of the first sentence.

### Time to Display Results:
The search results are displayed in a very insignificant amount of time. Generating a search for “program” results in 
no noticeable delay before 82 results are displayed. Generating a search for a more widely used term, such as “the”, 
results in a delay of approximately half a second before 9326 results are displayed.

### Searching for a Single Word:
A user can search for a single key term in GutenSearch. Any paragraph in which the term exists will be displayed in 
the results. All searches are case-insensitive, meaning the case of letters entered in the search bar or case letters 
in the database are irrelevant. The results are listed based on the number of occurrences in the paragraph, starting 
with the most occurrences at the top.

### Searching for Multiple Words (AND logic):
When the user types in more than one word in the search bar and hits “Search”, any results that are displayed are 
paragraphs that contain every word searched for. As with single term searching, the results are listed based on the 
number of occurrences in the paragraph. If we search for “program method building”, the top result is the paragraph 
with the most combined occurrences of the words “program” and “methods”.

### Searching for Quotes
A user should be able to search for direct quotes in the database. For example, when we search “She dropped her smoke”, 
GutenSearch displays one result from “The Ambassador” in Ordinal Paragraph #200. In our class presentation, it 
appeared that GutenSearch was not able to search quotes, but after further testing it appears to search quotes 
accurately. This discrepancy may have occurred due to a misspelling during our demonstration.
     
### Searching for Multiple Words (OR logic):
Entering two or more words in the search field and pressing “Search” while the “Use Logical OR” checkbox is checked 
generates a list of results in which at least one of the specified words occur for each paragraph in the database. 
Like with single and multiple word (AND logic) testing, the results are displayed based on the combined number of 
occurrences of the terms listed in the search box.

### Searching for Nonexistent Words, White Space, or Punctuation:
Entering a word that does not occur in any paragraph of any document in the database, such as “greebling” generate a 
results page that only contains the text, “0 results for ‘greebling’:”.
Entering nothing or simply pressing the spacebar yields similar results, with a message that reads
“0 results available for ‘’:” if nothing is entered, and “0 results available for “ ”:” if a space is entered.
Any punctuation entered, such as “?”, “,”, or “!” also yields similar results, with a results page that contains, “0 
results for “?”:”

### Searching for Words From a File that has Been Removed:
When we search for the term “ibm” with six files in the system, we generate 68 results. However, once we remove a file 
named “ibm.txt”, the search for “ibm” returns 54 results. The other 14 results are no longer found in the search 
results, as they were part of the source that has since been removed.

### Printing Search History and Results to a Separate File: 
When GutenSearch conducts a search, a new file is created in the directory of the program called “resultsHistory.txt”. 
The string that is input in the search box and the resulting paragraphs in which they are found are printed to this 
file. At the end of the session, a user can access this file for their search history and search results history. When 
a new session begins, the file is wiped and replaced with search results from the new session.

## Admin Functionality
### Adding an Existing File:
When the “Add” button is clicked, a JFileChecker opens a new window that browses available files on the computer. The 
user may click one or more files to upload. For example, when we add “hacker.txt” and “ibm.txt”, the label in the Admin 
window will print out “File ‘hacker.txt’ successfully added.” and “File ‘ibm.txt’ successfully added.”

### Automatic Setting of the Author and Title Variables:
When the files are successfully indexed and added, the “Author” and “Title” variables for each source are found within 
the text and set by the program. If the Author or Title cannot be found in the text, the variable will be appropriately 
set to “Unknown Author” or “Unknown Title”.

### Adding a File with an Unaccepted Format:
When the admin attempts to open a file with an unaccepted format, such as .java, the label in the Admin GUI will print 
out accordingly. For example, when we attempted to add Results.java, the label in the Admin GUI printed the message, 
“File ‘Results.java’ could not be added.”

### Removing an Existing File:
The Admin GUI label will prompt the user to type in the filename and click “Confirm” to delete a file from the 
database. For example, when the files “hacker.txt” and “ibm.txt” are in the database and we type “ibm.txt” in the text 
bar and click “Confirm”, the file is successfully removed from the database and the GUI label prints the message, 
“File ‘ibm.txt’ successfully removed.”

### Removing a Nonexistent File:
If only “hacker.txt” is remaining in the database and we choose to remove “ibm.txt” again, the label will print, “File 
‘ibm.txt’ is not in the database.”

### Editing Files in the Database:
An Admin can edit the author and title variables of any source already found in the database. By clicking the “Edit” 
button, an Admin can replace the Author or Title variable, or both simultaneously. When results are displayed from the 
edited source, the Author and/or Title will now display the specified edits from the admin rather than the original 
variables that were automatically set. If a filename that is input does not exist in the database, the Admin GUI 
label will tell the user, “Unable to edit. File ‘this.txt’ is not in the database.” Otherwise, it will tell the user, 
“Author and Title information saved.”

## Authors

* Nick Hawkins - James Madison University