package de.pascaljaeger.jadex.tutorial.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IElement;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

public class EnglishGermanTranslationPlanB4 extends Plan {
	
	static HashMap<String, String> wordtable;
	static {
		  wordtable = new HashMap();
		  wordtable.put("coffee", "Kaffee");
		  wordtable.put("milk", "Milch");
		  wordtable.put("cow", "Kuh");
		  wordtable.put("cat", "Katze");
		  wordtable.put("dog", "Hund");
	}
	
	public static boolean containsWord(String name) {
		System.out.println("Static lookup");
		  return wordtable.containsKey(name);
	}
	
	public EnglishGermanTranslationPlanB4(){
		System.out.println("Created: "+this);
	}
	
	@Override
	public void body() {
		System.out.println("Entering Body");
		IMessageEvent me = (IMessageEvent)getReason();
		String eword = me.getMessage().toString();
		URL dict;
		try {
			dict = new URL("http://wolfram.schneider.org/dict/dict.cgi?query="+eword);
			BufferedReader in = new BufferedReader(new InputStreamReader(dict.openStream()));
			String inline;
			while((inline = in.readLine())!=null) 
			{
			  if(inline.indexOf("<td>")!=-1 && inline.indexOf(eword)!=-1) 
			  {
			    try 
			    {
			     int start = inline.indexOf("<td>")+4;
			     int end = inline.indexOf("</td", start);
			     String worda = inline.substring(start, end);
			     start = inline.indexOf("<td", start);
			     start = inline.indexOf(">", start);
			     end = inline.indexOf("</td", start);
			     String wordb = inline.substring(start, end==-1? inline.length()-1: end);
			     wordb = wordb.replaceAll("<b>", "");
			     wordb = wordb.replaceAll("</b>", "");
			     System.out.println(worda+" - "+wordb);
			    }
			    catch(Exception e) 
			    {
			      System.out.println(inline);
			    }
			  }
			}
			in.close();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}