package com.morin.bowling.billmackenzie.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.model.AdultBowler;
import com.morin.bowling.billmackenzie.model.BMTeam;
import com.morin.bowling.billmackenzie.model.BMTournament;
import com.morin.bowling.billmackenzie.model.YouthBowler;

public class OpenSaveTournamentOperation {

	
	public final static void saveTournamentToFile() {
		try {
			PlatformUI.getWorkbench().getProgressService().busyCursorWhile(
				new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor) {
						monitor.beginTask("Saving Tournament", 100);
						
						BMTournament tournament = Activator.getDefault().getTournament();
						
						String fileLocation = Activator.getDefault().getTournamentLocation();	
													
						try {
							BufferedWriter out = new BufferedWriter(new FileWriter(fileLocation));
							out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
							out.write("<tournament name=\"" + tournament.getName() + "\" id=\"billmackenzie\">\n");

							BMTeam[] teams = tournament.getAllTeams();
							for (int i = 0; i < teams.length; i++) {
								if (monitor.isCanceled())
									return;
								
								monitor.subTask("Saving Entry # " + (i+1));
								
								out.write("\t<team id=\"" + String.valueOf(teams[i].getId()) + 
										"\" bowling=\"" + teams[i].getBowlingCentre() + "\">\n");
								out.write("\t\t<adultbowler name=\"" + teams[i].getAdult().getName() + 
										"\" average=\"" + teams[i].getAdult().getAverage() + 
										"\" scratchpool=\"" + String.valueOf(teams[i].getAdult().isScratchPot()) + 
										"\" poapool=\"" + String.valueOf(teams[i].getAdult().isPoaPot()) + 
										"\" games=\"" + teams[i].getAdult().getGame(0) + "," +
										teams[i].getAdult().getGame(1) + "," +
										teams[i].getAdult().getGame(2) + "," +
										teams[i].getAdult().getGame(3) + "," +
										teams[i].getAdult().getGame(4) + "\"/>\n");
								out.write("\t\t<youthbowler name=\"" + teams[i].getYouth().getName() + 
										"\" average=\"" + teams[i].getYouth().getAverage() + 
										"\" scratchpool=\"" + String.valueOf(teams[i].getYouth().isScratchPot()) + 
										"\" poapool=\"" + String.valueOf(teams[i].getYouth().isPoaPot()) + 
										"\" games=\"" + teams[i].getYouth().getGame(0) + "," +
										teams[i].getYouth().getGame(1) + "," +
										teams[i].getYouth().getGame(2) + "," +
										teams[i].getYouth().getGame(3) + "," +
										teams[i].getYouth().getGame(4) + "\"/>\n");
								out.write("\t</team>\n");
								
								monitor.worked(100/teams.length);
							}
							monitor.done();
							out.write("</tournament>");
							out.close();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
		} catch (InvocationTargetException e1) {
		} catch (InterruptedException e1) {
		}		
		
	}
	
	public final static void openTournamentFromFile(File file) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = null;
		try {
			document = documentBuilder.parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element rootElement = document.getDocumentElement();
		NodeList childNodesOfRootElement = rootElement.getChildNodes();
		
		Node tournamentName = rootElement.getAttributes().getNamedItem("name");
		
		BMTournament tournament = Activator.getDefault().getTournament();
		tournament.clear();
		tournament.setName(tournamentName.getNodeValue());
		
		for (int i = 0; i < childNodesOfRootElement.getLength(); i++) 
		{
			Node childTeamNode = childNodesOfRootElement.item(i);
			
			if ( (childTeamNode.getNodeName() != null) && (childTeamNode.getNodeName().equals("team")))
			{
				String teamID = childTeamNode.getAttributes().getNamedItem("id").getNodeValue();
				//String teamName = childTeamNode.getAttributes().getNamedItem("name").getNodeValue();
				String teamBowling = childTeamNode.getAttributes().getNamedItem("bowling").getNodeValue();
				
				String adultName = null;
				String adultAverage = null;
				String adultScratchPool = null;
				String adultPOAPool = null;
				String adultGamesArray[] = null;
				
				String youthName = null;
				String youthAverage = null;
				String youthScratchPool = null;
				String youthPOAPool = null;
				String youthGamesArray[] = null;
				
							
				NodeList childNodesOfTeamElement = childTeamNode.getChildNodes();
				
				for (int j = 0; j < childNodesOfTeamElement.getLength(); j++) 
				{	
					Node childBowlerNode = childNodesOfTeamElement.item(j);
					if ( childBowlerNode.getNodeName() != null )
					{
						if ( childBowlerNode.getNodeName().equals("adultbowler") )
						{
							adultName = childBowlerNode.getAttributes().getNamedItem("name").getNodeValue();
							adultAverage = childBowlerNode.getAttributes().getNamedItem("average").getNodeValue();
							adultScratchPool = childBowlerNode.getAttributes().getNamedItem("scratchpool").getNodeValue();
							adultPOAPool = childBowlerNode.getAttributes().getNamedItem("poapool").getNodeValue();
							String adultGames = childBowlerNode.getAttributes().getNamedItem("games").getNodeValue();
							adultGamesArray =  adultGames.split(",");
							
							
						}
						if ( childBowlerNode.getNodeName().equals("youthbowler") )
						{
							youthName = childBowlerNode.getAttributes().getNamedItem("name").getNodeValue();
							youthAverage = childBowlerNode.getAttributes().getNamedItem("average").getNodeValue();
							youthScratchPool = childBowlerNode.getAttributes().getNamedItem("scratchpool").getNodeValue();
							youthPOAPool = childBowlerNode.getAttributes().getNamedItem("poapool").getNodeValue();
							String youthGames = childBowlerNode.getAttributes().getNamedItem("games").getNodeValue();
							youthGamesArray =  youthGames.split(",");
						}
					}
				}
				
				AdultBowler adultBowler = new AdultBowler(adultName, Integer.valueOf(adultAverage).intValue(), Boolean.valueOf(adultScratchPool).booleanValue(), Boolean.valueOf(adultPOAPool).booleanValue());
				for (int j = 0; j < adultGamesArray.length; j++) {
					adultBowler.setGame(j, Integer.valueOf(adultGamesArray[j]).intValue());
				}
				YouthBowler youthBowler = new YouthBowler(youthName, Integer.valueOf(youthAverage).intValue(), Boolean.valueOf(youthScratchPool).booleanValue(), Boolean.valueOf(youthPOAPool).booleanValue());
				for (int k = 0; k < youthGamesArray.length; k++) {
					youthBowler.setGame(k, Integer.valueOf(youthGamesArray[k]).intValue());
				}
				
				BMTeam team = new BMTeam(Integer.valueOf(teamID).intValue(), teamBowling, adultBowler, youthBowler);
				tournament.addTeam(team);	
			}	
		}	
		
	}
}
