package de.pascaljaeger.jadex.tutorial.b2;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

import java.util.HashMap;

public class EnglishGermanTranslationPlanB2 extends Plan {
	
	private HashMap<String, String> hm;
	
	public EnglishGermanTranslationPlanB2(){
		System.out.println("Created: "+this);
		hm = new HashMap<String, String>();
		hm.put("Hello", "Hallo");
		hm.put("Bye", "Auf Wiedersehen");
	}
	
	@Override
	public void body() {
		System.out.println("Entering Body");
		IMessageEvent me = (IMessageEvent)getReason();
		String eword = (String)me.getParameter(SFipa.CONTENT).getValue();
		System.out.println("Eword = "+eword);
		if(hm.containsKey(eword)){
			String gword = hm.get(eword);
			System.out.println("Translating from English to German: "+eword+" - "+gword);
		} else {
			System.out.println("Sorry word is not in database: "+eword);
		}	
	}
}