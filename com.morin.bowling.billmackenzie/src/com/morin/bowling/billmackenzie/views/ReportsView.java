package com.morin.bowling.billmackenzie.views;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.HyperlinkGroup;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import com.morin.bowling.billmackenzie.utilities.BMStandings;
import com.morin.bowling.billmackenzie.utilities.WrappedTeam;

public class ReportsView extends ViewPart {
	
	public static final String ID = "com.morin.bowling.billmackenzie.views.reports";
	
	private FormToolkit toolkit;
	private ScrolledForm form;
	private HyperlinkGroup linksGroup;
	private Combo gameCombo;
	private Button bestEventFilter;

	public ReportsView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		
		GridLayout layout = new GridLayout();
		form.getBody().setLayout(layout);
		
		linksGroup = toolkit.getHyperlinkGroup();
        linksGroup.setHyperlinkUnderlineMode(HyperlinkSettings.UNDERLINE_HOVER);
        linksGroup.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
		
        //Game Options Section
        Section gameOptionsSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR | Section.TWISTIE|Section.EXPANDED);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gameOptionsSection.setLayoutData(gd);
		gameOptionsSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		gameOptionsSection.setText("Game Options");
		
		Composite gameOptionsContainer = toolkit.createComposite(gameOptionsSection);
		GridLayout gameOptionsLayout = new GridLayout();
		gameOptionsLayout.numColumns = 2;
		gameOptionsContainer.setLayout(gameOptionsLayout);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gameOptionsContainer.setLayoutData(gd);
		
		toolkit.createLabel(gameOptionsContainer, "Game: ", SWT.RIGHT);	
		gameCombo = new Combo(gameOptionsContainer, SWT.LEFT|SWT.READ_ONLY);
		gameCombo.setItems( new String [] {"Game 1", "Game 2", "Game 3", "Game 4", "Game 5"} );
		gameCombo.select(0);
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		bestEventFilter = toolkit.createButton(gameOptionsContainer, "Show in best event only", SWT.CHECK);
		bestEventFilter.setLayoutData(gd);
		gameOptionsSection.setClient(gameOptionsContainer);
		
		//Singles Pool Section
		Section singlesPotSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR|Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		singlesPotSection.setLayoutData(gd);
		singlesPotSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		singlesPotSection.setText("Singles Pool Reports");	
		
		Composite singlesPoolContainer = toolkit.createComposite(singlesPotSection);
		singlesPoolContainer.setLayout(new GridLayout());

		Hyperlink adultPOAPool = toolkit.createHyperlink(singlesPoolContainer, "Adult POA Pool", SWT.LEFT);
	    linksGroup.add(adultPOAPool);
	    adultPOAPool.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleAdultPoolPOA(gameCombo.getSelectionIndex());
				printReport(teams, 1, "Adult POA Singles Pool - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		adultPOAPool.setLayoutData(gd);		
		
		Hyperlink adultScratchPool = toolkit.createHyperlink(singlesPoolContainer, "Adult Scratch Pool", SWT.LEFT);
	    linksGroup.add(adultScratchPool);
	    adultScratchPool.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleAdultPoolScratch(gameCombo.getSelectionIndex());
				printReport(teams, 1, "Adult Scratch Singles Pool - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));	
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		adultScratchPool.setLayoutData(gd);
		
		Hyperlink youthPOAPool = toolkit.createHyperlink(singlesPoolContainer, "Youth POA Pool", SWT.LEFT);
	    linksGroup.add(youthPOAPool);
	    youthPOAPool.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleYouthPoolPOA(gameCombo.getSelectionIndex());
				printReport(teams, 2, "Youth POA Singles Pool - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));	
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		youthPOAPool.setLayoutData(gd);
			
		Hyperlink youthScratchPool = toolkit.createHyperlink(singlesPoolContainer, "Youth Scratch Pool", SWT.LEFT);
	    linksGroup.add(youthScratchPool);
	    youthScratchPool.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleYouthPoolScratch(gameCombo.getSelectionIndex());
				printReport(teams, 2, "Youth Scratch Singles Pool - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));	
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		youthScratchPool.setLayoutData(gd);
		singlesPotSection.setClient(singlesPoolContainer);
		
		//Team Section
		Section teamSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR|Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		teamSection.setLayoutData(gd);
		teamSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		teamSection.setText("Team Reports");
		
		Composite teamContainer = toolkit.createComposite(teamSection);
		teamContainer.setLayout(new GridLayout());
		
		Hyperlink teamPOA = toolkit.createHyperlink(teamContainer, "Team POA", SWT.LEFT);
	    linksGroup.add(teamPOA);
	    teamPOA.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleTeamPOA(gameCombo.getSelectionIndex(),bestEventFilter.getSelection());
				printReport(teams, 0, "Team POA - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));	
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		teamPOA.setLayoutData(gd);
		
		Hyperlink teamScratch = toolkit.createHyperlink(teamContainer, "Team Scratch", SWT.LEFT);
	    linksGroup.add(teamScratch);
	    teamScratch.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {	
				WrappedTeam[] teams = BMStandings.handleTeamScratch(gameCombo.getSelectionIndex(),bestEventFilter.getSelection());
				printReport(teams, 0, "Team Scratch - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		teamScratch.setLayoutData(gd);
		
		teamSection.setClient(teamContainer);
		
		//Individual Section		
		Section individualSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR|Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		individualSection.setLayoutData(gd);
		individualSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		individualSection.setText("Individual Reports");
		
		Composite individualContainer = toolkit.createComposite(individualSection);
		individualContainer.setLayout(new GridLayout());
			
		Hyperlink adultPOA = toolkit.createHyperlink(individualContainer, "Adult POA", SWT.LEFT);
	    linksGroup.add(adultPOA);
	    adultPOA.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleAdultPOA(gameCombo.getSelectionIndex(),bestEventFilter.getSelection());
				printReport(teams, 1, "Adult POA - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		adultPOA.setLayoutData(gd);
		
		
		Hyperlink adultScratch = toolkit.createHyperlink(individualContainer, "Adult Scratch", SWT.LEFT);
	    linksGroup.add(adultScratch);
	    adultScratch.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleAdultScratch(gameCombo.getSelectionIndex(),bestEventFilter.getSelection());
				printReport(teams, 1, "Adult Scratch - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		adultScratch.setLayoutData(gd);
		
		Hyperlink youthPOA = toolkit.createHyperlink(individualContainer, "Youth POA", SWT.LEFT);
	    linksGroup.add(youthPOA);
	    youthPOA.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleYouthPOA(gameCombo.getSelectionIndex(),bestEventFilter.getSelection());
				printReport(teams, 2, "Youth POA - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));	
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		youthPOA.setLayoutData(gd);
		
		Hyperlink youthScratch = toolkit.createHyperlink(individualContainer, "Youth Scratch", SWT.LEFT);
	    linksGroup.add(youthScratch);
	    youthScratch.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleYouthScratch(gameCombo.getSelectionIndex(),bestEventFilter.getSelection());
				printReport(teams, 2, "Youth Scratch - " + "Game " + String.valueOf(gameCombo.getSelectionIndex() + 1));
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		youthScratch.setLayoutData(gd);

		individualSection.setClient(individualContainer);
		
		//Administration Section		
		Section administrationSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR|Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		administrationSection.setLayoutData(gd);
		administrationSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		administrationSection.setText("Administration Reports");
		
		Composite administrationContainer = toolkit.createComposite(administrationSection);
		administrationContainer.setLayout(new GridLayout());
			
		Hyperlink teamAverage = toolkit.createHyperlink(administrationContainer, "Team Average", SWT.LEFT);
	    linksGroup.add(teamAverage);
	    teamAverage.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				WrappedTeam[] teams = BMStandings.handleTeamAverage();
				printTeamAverage(teams, "Team Average");
			}
		});
	    gd = new GridData(GridData.FILL_HORIZONTAL);
		teamAverage.setLayoutData(gd);
		
		administrationSection.setClient(administrationContainer);
	}

	public void setFocus() {
		form.setFocus();
	}
	
	private void openURL(String urlName) { 
		URL url;
		try {
			url = new URL("file:" + urlName);
		} catch (MalformedURLException e) {
			
			return;
		}
		
		try {
			PlatformUI.getWorkbench().getBrowserSupport()
					.getExternalBrowser().openURL(url);
		} catch (PartInitException e) {
			
		}
		
	 }
	
	
	private void printTeamAverage(WrappedTeam[] teams, String title)
	{
		String userLocation = System.getProperty("user.dir");
		String reportLocation = userLocation + File.separator + "bowlingreport.html";
		File reportFile = new File(reportLocation);
		if (reportFile.exists() )
			reportFile.delete();

		try {
			reportFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(reportLocation));
			out.write("<html>\n<head><title>" + title + "</title></head>\n<body>\n");
			  out.write("<h1 align=\"center\">" + title + "</h1>\n");
		
		    for (int k = 0; k < teams.length; k++) 
			{
		    	out.write("<p>\n");
		    	out.write("<hr width=\"75%\" align=\"center\">\n");
		    	out.write("<p>\n");
		    	out.write("<table border=\"0\" width=\"75%\" align=\"center\">\n");
		    	out.write("<tr>\n");
		    	out.write("<td width=\"30%\">ID: " + String.valueOf(teams[k].getTeam().getId()) + " - " + teams[k].getTeam().getBowlingCentre() + "</td>\n");
		    	out.write("<td width=\"40%\"></td>\n");
		    	out.write("<td width=\"30%\"></td>\n");
		    	out.write("</tr>\n");
		    	out.write("<tr>\n");
		    	out.write("<td width=\"30%\"></td>\n");
		    	out.write("<td width=\"40%\">" + teams[k].getTeam().getYouth().getName() + "</td>\n");
		    	out.write("<td width=\"30%\">Average: " + String.valueOf(teams[k].getTeam().getYouth().getAverage()) + "</td>\n");
		    	out.write("</tr>\n");
		    	out.write("<tr>\n");
		    	out.write("<td width=\"30%\"></td>\n");
		    	out.write("<td width=\"40%\">" + teams[k].getTeam().getAdult().getName() + "</td>\n");
		    	out.write("<td width=\"30%\">Average: " + String.valueOf(teams[k].getTeam().getAdult().getAverage()) + "</td>\n");
		    	out.write("</tr>\n");
		    	out.write("</table>\n");
			}
		    out.write("</body>\n</html>");
		    
		    out.close();
		} catch (IOException e) 
		{
		}
		openURL(reportLocation);	
	}
	
	private void printReport(WrappedTeam[] teams, int type, String title)
	{
		String userLocation = System.getProperty("user.dir");
		String reportLocation = userLocation + File.separator + "bowlingreport.html";
		File reportFile = new File(reportLocation);
		if (reportFile.exists() )
			reportFile.delete();

		try {
			reportFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(reportLocation));
			out.write("<html>\n<head><title>" + title + "</title></head>\n<body>\n");
			  out.write("<h1 align=\"center\">" + title + "</h1>\n");
		    out.write("<table border=\"0\" width=\"100%\">\n");
		
		    int position = 1;
		
		    for (int k = 0; k < teams.length; k++) 
			{
		    	out.write("<tr>\n");
		    	out.write("<td width=\"5%\">" + position + ".</td>\n");
		    	
		    	if (type == 0)
		    		out.write("<td width=\"40%\">" + teams[k].getTeam().getYouth().getName() + " / " + teams[k].getTeam().getAdult().getName() + "</td>\n");
		    	else if (type == 1)
		    		out.write("<td width=\"40%\">" + teams[k].getTeam().getAdult().getName() + "</td>\n");
		    	else if (type == 2)
		    		out.write("<td width=\"40%\">" + teams[k].getTeam().getYouth().getName() + "</td>\n");
		    	
		    	out.write("<td width=\"35%\">" + teams[k].getTeam().getBowlingCentre() + "</td>\n");
		    	out.write("<td align=\"right\" width=\"20%\">" + teams[k].getValue() + "</td>\n");    	
		    	
		    	position++;
			}
		    out.write("</tr>\n</table>\n</body>\n</html>");
		    
		    out.close();
		} catch (IOException e) 
		{
		}
		openURL(reportLocation);	
		
	}
}
