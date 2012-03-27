package de.pascaljaeger.jadex.tutorial.b3;

import jadex.bdi.runtime.Plan;

import java.util.HashMap;

public class EnglishGermanTranslationPlanB3 extends Plan {
	
	private HashMap<String, String> hm;
	
	public EnglishGermanTranslationPlanB3(){
		System.out.println("Created: "+this);
		hm = new HashMap<String, String>();
		hm.put("Hello", "Hallo");
		hm.put("Bye", "Auf Wiedersehen");
	}
	
	@Override
	public void body() {
		System.out.println("Entering Body");
		String eword = (String)getParameter("eword").getValue();
		System.out.println("Eword = "+eword);
		if(hm.containsKey(eword)){
			String gword = hm.get(eword);
			System.out.println("Translating from English to German: "+eword+" - "+gword);
		} else {
			System.out.println("Sorry word is not in database: "+eword);
		}	
	}
}