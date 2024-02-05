
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import java.io.*;

public class GutenSearchTest {
	@Test//(expected = IllegalArgumentException.class)
	public void constructor_Search()
	{
		new Search();
	}

	@Test//(expected = IllegalArgumentException.class)
	public void constructor_Results()
	{
		new Results();
	}

	@Test//(expected = IllegalArgumentException.class)
	public void constructor_Source()
	{
		new Source("ibm.txt");
		new Source("twitter_bootstrap.txt");
		new Source("");
	}

	@Test//(expected = IllegalArgumentException.class)
	public void constructor_Paragraph()
	{
		new Paragraph("Lots of text", "Author", "Title", 400);
		new Paragraph("", "", "", 0);
		new Paragraph("", "", "", 1);
	}

	@Test//(expected = IOException.class)
	public void driver_Methods()
	{
		ArrayList<Source> sources = new ArrayList<Source>();
		ArrayList<Source> manuallyAdded = new ArrayList<Source>();
		Driver d = new Driver();
		
		Driver.main(new String[2]);

		assertEquals(0, d.addSource("ibm.txt"));
		assertEquals(0, d.addSource("hacker.txt"));
		assertEquals(-1, d.addSource("iDontExist.txt"));

		assertEquals(0, d.removeSource("ibm.txt"));
		assertEquals(-1, d.removeSource("yup.txt"));			

		sources = Driver.getSources();
		manuallyAdded.add(new Source("hacker.txt"));
		
		assertEquals(sources.get(0).getFileName(), manuallyAdded.get(0).getFileName());
		assertEquals(sources.size(), manuallyAdded.size());
		Driver.refresh();

	}
	
	@Test
	public void source_Methods()
	{
		Source s = new Source("hacker.txt");
		Source t = new Source("ibm.txt");
		Source u = new Source("library.txt");
		try
		{
			s.listParagraphs();
			t.listParagraphs();
			u.listParagraphs();
			u.getParagraphList();
			
			throw new IOException();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		u.setAuthor("Nick");
		assertEquals(u.author, "Nick");
		
		u.setTitle("Library of Congress");
		assertEquals(u.title, "Library of Congress");
		
		assertEquals(u.getFileName(), "library.txt");
		
		assertEquals(80, t.getParagraphList().size());
		assertEquals(2958, s.getParagraphList().size());
	}
	
	@Test
	public void paragraph_Methods()
	{
		Paragraph p = new Paragraph("This not is not a very long paragraph", "Unknown Author", "Unknown Title", 140);
		HashMap <String, Integer> map = new HashMap<String, Integer>();
		
		map.put("not", 2);
		map.put("is", 1);
		map.put("paragraph", 1);
		map.put("a", 1);
		map.put("long", 1);
		map.put("This", 1);
		map.put("very", 1);
		
		assertEquals("Unknown Author", p.getAuthor());
		assertEquals("Unknown Title", p.getTitle());
		assertEquals("This not is not a very long paragraph", p.getText());
		assertEquals(140, p.getOrdinalNumber());

		assertEquals(map.get("not"), p.getHashMap().get("not"));
		assertEquals(map.get("is"), p.getHashMap().get("is"));
		assertEquals(map.get("a"), p.getHashMap().get("a"));
		assertEquals(map.get("very"), p.getHashMap().get("very"));
		assertEquals(map.get("long"), p.getHashMap().get("long"));
		assertEquals(map.get("paragraph"), p.getHashMap().get("paragraph"));
	}
	
	@Test
	public void search_Methods()
	{
		Source s = new Source("hacker.txt");
		Source t = new Source("ibm.txt");
		Search se = new Search();
		ArrayList<Source> list = new ArrayList<Source>();
		ArrayList<Paragraph> results;
		ArrayList<Paragraph> results2;
		
		list.add(s);
		list.add(t);
		
		/*results = se.getTermsAnd(list, "Program");
		results2 = se.getTermsOr(list, "system");
		
		System.out.println(results.size());
		System.out.println(results2.size());
		
		assertEquals(10, se.getTermsAnd(list, "Program").size());
		assertEquals(10, se.getTermsOr(list, "system").size());*/
	}
	
	@Test
	public void results_Methods()
	{
		Paragraph p1 = new Paragraph("This is a short paragraph.", "Some Author", "Some Title", 156);
		Paragraph p2 = new Paragraph("This is another short paragraph. With two sentences.", "Another Author", "Another Title", 342);
		String results;
		ArrayList<Paragraph> list = new ArrayList<Paragraph>();
		ArrayList<Paragraph> emptyList = new ArrayList<Paragraph>();
		Results r = new Results();
		
		list.add(p1);
		list.add(p2);
		
		results = r.displayResults(list);
		
		assertEquals("This is a short paragraph.", r.getFirstSentence(p1.getText()));
		assertEquals("No results available.", r.displayResults(emptyList));
		assertEquals("This is another short paragraph.", r.getFirstSentence(p2.getText()));
		
	}
	
	@Test
	public void administrator_Methods()
	{
		Administrator a = new Administrator();		
	}
}
