package de.pascaljaeger.jadex.tutorial.b4;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class EnglishGermanTranslationPlanB4 extends Plan {
	
	private static HashMap<String, String> wordtable;
	
	static {
		  wordtable = new HashMap<String, String>();
		  wordtable.put("coffee", "Kaffee");
		  wordtable.put("milk", "Milch");
		  wordtable.put("cow", "Kuh");
		  wordtable.put("cat", "Katze");
		  wordtable.put("dog", "Hund");
	}
	
	public EnglishGermanTranslationPlanB4(){
		System.out.println("Created: "+this);
	}
	
	@Override
	public void body() {
		System.out.println("Entering Body");
		IMessageEvent me = (IMessageEvent)getReason();
		//String eword = me.getMessage().toString();
		String eword = (String)me.getParameter(SFipa.CONTENT).getValue();
		System.out.println("Eword = "+eword);
		if(wordtable.containsKey(eword)){
			String gword = wordtable.get(eword);
			System.out.println("Translating from English to German: "+eword+" - "+gword);
		} else {
			System.out.println("Sorry word is not in database: "+eword);
		}	
	}
	
	public static boolean containsWord(String name) {
		  return wordtable.containsKey(name);
	}
}