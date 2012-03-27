package de.pascaljaeger.jadex.tutorial.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

public class SearchTranslationOnlineB4 extends Plan {

	@Override
	public void body() {
		IMessageEvent me = (IMessageEvent)getReason();
		String eword = (String)me.getParameter(SFipa.CONTENT).getValue();
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
