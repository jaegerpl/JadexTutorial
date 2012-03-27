package de.pascaljaeger.jadex.tutorial.c1;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

public class AddGermanWordPlanC1 extends Plan {
		
	@Override
	public void body() {
		String eword;
		String gword;
		IMessageEvent me = (IMessageEvent)getReason();
		String message = (String)me.getParameter(SFipa.CONTENT).getValue();
		StringTokenizer strtok = new StringTokenizer(message);
		System.out.println("Tokencount = "+strtok.countTokens());
		if(strtok.countTokens() == 4){
			if(strtok.nextToken().equals("add") &&
			   strtok.nextToken().equals("english_german")){
				eword = strtok.nextToken();
				gword = strtok.nextToken();
				
				Map words = (Map)getBeliefbase().getBelief("egwords").getFact(); 
				if(!words.containsKey(eword)){
					words.put(eword, gword);
					System.out.println(eword + ":" + gword +" hinzugefügt");
				} else {
					System.out.println(eword +" bereits vorhanden");
				}
			} else {
				System.out.println("Wrong Request!");
			}
		} else {
			System.out.println("Wrong Request Format.");
		}


	}
	



}
