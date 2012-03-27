package de.pascaljaeger.jadex.tutorial.c3;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IExpression;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class EnglishGermanTranslationPlanC3 extends Plan {
	
	protected static Map<String, String> dictionary;

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

	private IExpression queryword;
	
	public EnglishGermanTranslationPlanC3(){
		System.out.println("Created: "+this);
	}
	
	@Override
	public void body() {
		
		this.queryword = getExpression("query_egword");
		
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
				
				// count translations
				
				int cnt = ((Integer)getBeliefbase().getBelief("transcnt").getFact()).intValue(); getBeliefbase().getBelief("transcnt").setFact(new Integer(cnt+1));
				
				System.out.println("Eword = "+eword);
				String gword = (String)queryword.execute("$eword", eword);
				if(gword != null){
					System.out.println("Translating from English to German: "+eword+" - "+gword);
				} else {
					System.out.println("Sorry word is not in database: "+eword);	
				}
			}
		}
	}
}