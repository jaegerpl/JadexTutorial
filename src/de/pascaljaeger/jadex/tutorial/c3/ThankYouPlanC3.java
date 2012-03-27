package de.pascaljaeger.jadex.tutorial.c3;

import jadex.bdi.runtime.Plan;

public class ThankYouPlanC3 extends Plan {

	@Override
	public void body() {
		
		int cnt = ((Integer)getBeliefbase().getBelief("transcnt").getFact()).intValue();
		System.out.println("Thank you - this was the "+cnt+" translation.");
		
	}

}
