package de.pascaljaeger.jadex.tutorial.d1;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IExpression;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class EnglishGermanTranslationPlanD1 extends Plan {
	
	String reply;  // The message event type of the reply.
	String content; // The content of the reply message event.
	
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
	
	public EnglishGermanTranslationPlanD1(){
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
				
				System.out.println("Eword = "+eword);
				String gword = (String)queryword.execute("$eword", eword);
				if(gword != null){
					System.out.println("Translating from English to German: "+eword+" - "+gword);
					reply = "inform";
					content = gword;
				} else {
					System.out.println("Sorry word is not in database: "+eword);	
					reply = "failure";
					content = "Sorry, word could not be translated: "+eword;
				}
			}
		}
		IMessageEvent replymsg = getEventbase().createReply((IMessageEvent)getReason(), reply);
		replymsg.getParameter(SFipa.CONTENT).setValue(content);
		sendMessage(replymsg);
	}
}