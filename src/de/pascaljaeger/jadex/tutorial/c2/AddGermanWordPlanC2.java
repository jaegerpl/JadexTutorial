package de.pascaljaeger.jadex.tutorial.c2;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IExpression;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

public class AddGermanWordPlanC2 extends Plan {
		
	private IExpression queryword;

	@Override
	public void body() {
		this.queryword = getExpression("query_egword"); 
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

				if((String)queryword.execute("$eword", eword) == null){
					getBeliefbase().getBeliefSet("egwords").addFact(new jadex.commons.Tuple(eword, gword));
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
