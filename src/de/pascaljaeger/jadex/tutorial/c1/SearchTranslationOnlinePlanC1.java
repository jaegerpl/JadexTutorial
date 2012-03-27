package de.pascaljaeger.jadex.tutorial.c1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IElement;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

public class SearchTranslationOnlinePlanC1 extends Plan {
	
	protected static Map dictionary;

	public static Map<String,String> getDictionary()
	{
	  if(dictionary==null)
	  {
	    dictionary = new HashMap<String,String>();
	    dictionary.put("milk", "Milch");
	    dictionary.put("cow", "Kuh");
	    dictionary.put("cat", "Katze");
	    dictionary.put("dog", "Hund");
	  }
	  return dictionary;
	}
	
	public SearchTranslationOnlinePlanC1(){
		System.out.println("Created: "+this);
	}
	
	@Override
	public void body() {
		System.out.println("Entering Body");
		IMessageEvent me = (IMessageEvent)getReason();
		String message = (String)me.getParameter(SFipa.CONTENT).getValue();
		String eword;
		StringTokenizer strtok = new StringTokenizer(message);
		if(message.startsWith("translate english_german")){
			if(strtok.countTokens() == 3){
				strtok.nextToken();
				strtok.nextToken();
				eword = strtok.nextToken();
				
				System.out.println("Eword = "+eword);
				if(dictionary.containsKey(eword)){
					String gword = (String) dictionary.get(eword);
					System.out.println("Translating from English to German: "+eword+" - "+gword);
				} else {
					System.out.println("Sorry word is not in database: "+eword);	
				}
			}
		}
	}
}