package com.morin.bowling.billmackenzie.views;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.model.BMTeam;

public class ScoresView extends ViewPart implements ISelectionListener, VerifyListener, IPropertyChangeListener {
	
	public static final String ID = "com.morin.bowling.billmackenzie.views.scores";
	
	private BMTeam currentTeam;
	private BMTeam previousTeam;
	
	private FormToolkit toolkit;
	private ScrolledForm form;
	
	private Color red;
	private Color green;
	private Color black;
	
	private Label adultBowlerName;
	private Label adultBowlerAverage;
	private Label youthBowlerName;
	private Label youthBowlerAverage;
	
	private Label adultScratchTotal;
	private Label adultPOATotal;
	private Label youthScratchTotal;
	private Label youthPOATotal;
	private Label teamTotalScratch;
	private Label teamTotalPOA;
	
	private Text[] adultScores = new Text[5];
	private Text[] youthScores = new Text[5];
	private Label[] adultPOAScores = new Label[5];
	private Label[] youthPOAScores = new Label[5];

	public ScoresView() {
		super();
	}

	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		
		red = parent.getDisplay().getSystemColor(SWT.COLOR_RED);
		green = parent.getDisplay().getSystemColor(SWT.COLOR_GREEN);
		black = parent.getDisplay().getSystemColor(SWT.COLOR_BLACK);
		
		GridLayout layout = new GridLayout();
		form.getBody().setLayout(layout);
			
//		Adult Section
		Section adultBowlerSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR | Section.TWISTIE|Section.EXPANDED);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		adultBowlerSection.setLayoutData(gd);
		adultBowlerSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		adultBowlerSection.setText("Adult Bowler");
		
		//Adult Container
		Composite adultContainer = toolkit.createComposite(adultBowlerSection);
		GridLayout adultLayout = new GridLayout();
		gd = new GridData(GridData.FILL_HORIZONTAL);
		adultLayout.numColumns = 6;
		adultContainer.setLayout(adultLayout);
		adultContainer.setLayoutData(gd);
		
		//Adult Bowler Name
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		adultBowlerName = toolkit.createLabel(adultContainer, "", SWT.WRAP);
		adultBowlerName.setFont(JFaceResources.getHeaderFont());
		adultBowlerName.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		adultBowlerName.setLayoutData(gd);
		
		//Adult Bowler Average Label
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		Label label = toolkit.createLabel(adultContainer, "AVG: ", SWT.RIGHT);
		label.setFont(JFaceResources.getHeaderFont());
		label.setLayoutData(gd);
		
		//Adult Bowler Average
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		adultBowlerAverage = toolkit.createLabel(adultContainer, "", SWT.LEFT);
		adultBowlerAverage.setFont(JFaceResources.getHeaderFont());
		adultBowlerAverage.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		adultBowlerAverage.setLayoutData(gd);
		
		//Adult Game Label Header
		for (int i = 0; i < 5; i++) {
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 50;
			gd.verticalIndent = 15;
			label = toolkit.createLabel(adultContainer, "Game " + (i + 1), SWT.CENTER);
			label.setFont(JFaceResources.getBannerFont());
			label.setLayoutData(gd);
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 80;
		gd.verticalIndent = 15;
		label = toolkit.createLabel(adultContainer, "Total Scratch", SWT.CENTER);
		label.setFont(JFaceResources.getBannerFont());
		label.setLayoutData(gd);
		
		//Adult Scores
		for (int i = 0; i < 5; i++) {
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 50;
			adultScores[i] = toolkit.createText(adultContainer, "", SWT.BORDER | SWT.CENTER);
			adultScores[i].setLayoutData(gd);
			adultScores[i].setEnabled(false);
			adultScores[i].setTextLimit(3);
			adultScores[i].addVerifyListener(this);
			adultScores[i].addFocusListener(new FocusAdapter() {
		    	public void focusLost(FocusEvent e)
		    	{
		    		calculateNumbers();
		    		saveScoresToTournament();
		    	}
		    });	   
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 80;
		adultScratchTotal = toolkit.createLabel(adultContainer, "", SWT.CENTER);
		adultScratchTotal.setLayoutData(gd);
		
		//Adult plus/minus label headers
		for (int i = 0; i < 5; i++) {
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 50;
			label = toolkit.createLabel(adultContainer, "+/-", SWT.CENTER);
			label.setFont(JFaceResources.getBannerFont());
			label.setLayoutData(gd);
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 80;
		label = toolkit.createLabel(adultContainer, "Total POA", SWT.CENTER);
		label.setFont(JFaceResources.getBannerFont());
		label.setLayoutData(gd);
		
		//Adult plus/minus label, this is calculated based on average
		for (int i = 0; i < 5; i++) {
			gd = new GridData(GridData.FILL_HORIZONTAL);
			adultPOAScores[i] = toolkit.createLabel(adultContainer, "", SWT.CENTER);
			adultPOAScores[i].setLayoutData(gd);
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		adultPOATotal = toolkit.createLabel(adultContainer, "", SWT.CENTER);
		adultPOATotal.setLayoutData(gd);
		
		adultBowlerSection.setClient(adultContainer);
		
		//Youth Bowler Section	
		Section youthBowlerSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR | Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		youthBowlerSection.setLayoutData(gd);
		youthBowlerSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		youthBowlerSection.setText("Youth Bowler");
		
		//Youth Container
		Composite youthContainer = toolkit.createComposite(youthBowlerSection);
		GridLayout youthLayout = new GridLayout();
		gd = new GridData(GridData.FILL_HORIZONTAL);
		youthLayout.numColumns = 6;
		youthContainer.setLayout(youthLayout);
		youthContainer.setLayoutData(gd);
		
		//Youth Bowler Name
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		youthBowlerName = toolkit.createLabel(youthContainer, "", SWT.WRAP);
		youthBowlerName.setFont(JFaceResources.getHeaderFont());
		youthBowlerName.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		youthBowlerName.setLayoutData(gd);
		
		//Youth Bowler Average Label
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		label = toolkit.createLabel(youthContainer, "AVG: ", SWT.RIGHT);
		label.setFont(JFaceResources.getHeaderFont());
		label.setLayoutData(gd);
		
		//Youth Bowler Average
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		youthBowlerAverage = toolkit.createLabel(youthContainer, "", SWT.WRAP);
		youthBowlerAverage.setFont(JFaceResources.getHeaderFont());
		youthBowlerAverage.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		youthBowlerAverage.setLayoutData(gd);
		
		//YouthGame Label Headers
		for (int i = 0; i < 5; i++) {
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 50;
			gd.verticalIndent = 15;
			label = toolkit.createLabel(youthContainer, "Game " + (i + 1), SWT.CENTER);
			label.setFont(JFaceResources.getBannerFont());
			label.setLayoutData(gd);
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 80;
		gd.verticalIndent = 15;
		label = toolkit.createLabel(youthContainer, "Total Scratch", SWT.CENTER);
		label.setFont(JFaceResources.getBannerFont());
		label.setLayoutData(gd);
		
		//Youth Scores
		for (int i = 0; i < 5; i++) {
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 50;
			youthScores[i] = toolkit.createText(youthContainer, "", SWT.BORDER | SWT.CENTER);
			youthScores[i].setLayoutData(gd);
			youthScores[i].setEnabled(false);
			youthScores[i].setTextLimit(3);
			youthScores[i].addVerifyListener(this);
			youthScores[i].addFocusListener(new FocusAdapter() {
		    	public void focusLost(FocusEvent e)
		    	{
		    		calculateNumbers();
		    		saveScoresToTournament();
		    	}
		    });	   
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 80;
		youthScratchTotal = toolkit.createLabel(youthContainer, "", SWT.CENTER);
		youthScratchTotal.setLayoutData(gd);
		
		//Youth plus/minus label headers
		for (int i = 0; i < 5; i++) {
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 50;
			label = toolkit.createLabel(youthContainer, "+/-", SWT.CENTER);
			label.setFont(JFaceResources.getBannerFont());
			label.setLayoutData(gd);
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		label = toolkit.createLabel(youthContainer, "Total POA", SWT.CENTER);
		label.setFont(JFaceResources.getBannerFont());
		gd.widthHint = 80;
		label.setLayoutData(gd);
		
		//Youth plus/minus label, this is calculated based on average
		for (int i = 0; i < 5; i++) {
			gd = new GridData(GridData.FILL_HORIZONTAL);
			youthPOAScores[i] = toolkit.createLabel(youthContainer, "", SWT.CENTER);
			youthPOAScores[i].setLayoutData(gd);
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		youthPOATotal = toolkit.createLabel(youthContainer, "", SWT.CENTER);
		youthPOATotal.setLayoutData(gd);
		
		youthBowlerSection.setClient(youthContainer);	
			
		//Team Section
		Section teamSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR | Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		teamSection.setLayoutData(gd);
		teamSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		teamSection.setText("Team");
		
		//Team Containter
		Composite teamContainer = toolkit.createComposite(teamSection);
		GridLayout teamLayout = new GridLayout();
		gd = new GridData(GridData.FILL_HORIZONTAL);
		teamLayout.numColumns = 2;
		teamContainer.setLayout(teamLayout);
		teamContainer.setLayoutData(gd);
		
		//Team Total Scratch Header
		gd = new GridData();
		gd.widthHint = 100;
		label = toolkit.createLabel(teamContainer, "Total Scratch:  ", SWT.RIGHT);
		label.setFont(JFaceResources.getBannerFont());
		label.setLayoutData(gd);
	
		//Team Total Scratch
		gd = new GridData(GridData.FILL_HORIZONTAL);
		teamTotalScratch = toolkit.createLabel(teamContainer, "", SWT.LEFT);
		teamTotalScratch.setLayoutData(gd);
		
		//Team Total POA Header
		gd = new GridData();
		label = toolkit.createLabel(teamContainer, "Total POA:  ", SWT.RIGHT);
		label.setFont(JFaceResources.getBannerFont());
		gd.widthHint = 100;
		label.setLayoutData(gd);
	
		//Team Total POA
		gd = new GridData(GridData.FILL_HORIZONTAL);
		teamTotalPOA = toolkit.createLabel(teamContainer, "", SWT.LEFT);
		teamTotalPOA.setLayoutData(gd);
		
		teamSection.setClient(teamContainer);
		
		getSite().getPage().addSelectionListener(this);
	}
	
	private void calculateNumbers() {
		if (currentTeam == null)
			return;
		
		//Adult Total Scratch Calculation
		int tempAdultScratchTotal = 0;
		for (int i = 0; i < adultScores.length; i++) {
			if ( !adultScores[i].getText().equals("") )
			{
				tempAdultScratchTotal = tempAdultScratchTotal + Integer.valueOf(adultScores[i].getText()).intValue();
				
				int tempAdultPOAGame = (Integer.valueOf(adultScores[i].getText()).intValue()) - currentTeam.getAdult().getAverage();
				adultPOAScores[i].setText(String.valueOf(tempAdultPOAGame));
				if ( tempAdultPOAGame < 0 )
					adultPOAScores[i].setForeground(red);
				else if ( tempAdultPOAGame > 0 )
					adultPOAScores[i].setForeground(green);
				else
					adultPOAScores[i].setForeground(black);
			}
			else
			{
				adultPOAScores[i].setText("");
			}
				
		}
		adultScratchTotal.setText(String.valueOf(tempAdultScratchTotal));
		
		//Adult Total POA Calculation
		int tempAdultPOATotal = 0;
		for (int i = 0; i < adultPOAScores.length; i++) {
			if ( !adultPOAScores[i].getText().equals("") )
				tempAdultPOATotal = tempAdultPOATotal + Integer.valueOf(adultPOAScores[i].getText()).intValue();
		}
		if ( tempAdultPOATotal < 0 )
			adultPOATotal.setForeground(red);
		else if ( tempAdultPOATotal > 0 )
			adultPOATotal.setForeground(green);
		else
			adultPOATotal.setForeground(black);
		
		adultPOATotal.setText(String.valueOf(tempAdultPOATotal));
		
		//Youth Total Scratch Calculation
		int tempYouthScratchTotal = 0;
		for (int j = 0; j < youthScores.length; j++) {
			if ( !youthScores[j].getText().equals("") )
			{
				tempYouthScratchTotal = tempYouthScratchTotal + Integer.valueOf(youthScores[j].getText()).intValue();
			
				int tempYouthPOAGame = (Integer.valueOf(youthScores[j].getText()).intValue()) - currentTeam.getYouth().getAverage();
				youthPOAScores[j].setText(String.valueOf(tempYouthPOAGame));
				if ( tempYouthPOAGame < 0 )
					youthPOAScores[j].setForeground(red);
				else if ( tempYouthPOAGame > 0 )
					youthPOAScores[j].setForeground(green);
				else
					youthPOAScores[j].setForeground(black);
			}
			else
			{
				youthPOAScores[j].setText("");
			}
				
		}
		youthScratchTotal.setText(String.valueOf(tempYouthScratchTotal));
		
		//Youth Total POA Calculation
		int tempYouthPOATotal = 0;
		for (int i = 0; i < youthPOAScores.length; i++) {
			if ( !youthPOAScores[i].getText().equals("") )
				tempYouthPOATotal = tempYouthPOATotal + Integer.valueOf(youthPOAScores[i].getText()).intValue();
		}
		if ( tempYouthPOATotal < 0 )
			youthPOATotal.setForeground(red);
		else if ( tempYouthPOATotal > 0 )
			youthPOATotal.setForeground(green);
		else
			youthPOATotal.setForeground(black);
		
		youthPOATotal.setText(String.valueOf(tempYouthPOATotal));
		
		//Team Total Scratch
		int tempTeamTotalScratch = Integer.valueOf(adultScratchTotal.getText()).intValue() + Integer.valueOf(youthScratchTotal.getText()).intValue();
		teamTotalScratch.setText(String.valueOf(tempTeamTotalScratch));
		
		//Team Total POA
		int tempTeamTotalPOA = Integer.valueOf(adultPOATotal.getText()).intValue() + Integer.valueOf(youthPOATotal.getText()).intValue();
		if ( tempTeamTotalPOA < 0 )
			teamTotalPOA.setForeground(red);
		else if ( tempTeamTotalPOA > 0 )
			teamTotalPOA.setForeground(green);
		else
			teamTotalPOA.setForeground(black);
		teamTotalPOA.setText(String.valueOf(tempTeamTotalPOA));
	}
	
	public void displayTeam(BMTeam team) {	
		//Clear all text
		adultBowlerName.setText("");
		adultBowlerAverage.setText("");
		adultScratchTotal.setText("");
		adultPOATotal.setText("");
		
		youthBowlerName.setText("");
		youthBowlerAverage.setText( "" );
		youthScratchTotal.setText("");
		youthPOATotal.setText("");
		
		teamTotalScratch.setText("");
		teamTotalPOA.setText("");
		
		for (int i = 0; i < 5; i++) {
			adultScores[i].setEnabled(false);
			youthScores[i].setEnabled(false);
			
			adultScores[i].setText("");
			adultPOAScores[i].setText("");
			youthScores[i].setText("");
			youthPOAScores[i].setText("");
		}
		
		if (team != null)
		{
			adultBowlerName.setText(team.getAdult().getName());
			adultBowlerAverage.setText( String.valueOf(team.getAdult().getAverage()) );
			youthBowlerName.setText(team.getYouth().getName());
			youthBowlerAverage.setText( String.valueOf(team.getYouth().getAverage()) );
			
			for (int i = 0; i < 5; i++) {
				adultScores[i].setEnabled(true);
				youthScores[i].setEnabled(true);
				
				String adultGame = String.valueOf(currentTeam.getAdult().getGame(i));
				if ( !adultGame.equals("-1") && adultGame != null )
					adultScores[i].setText(adultGame);
				
				String youthGame = String.valueOf(currentTeam.getYouth().getGame(i));	
				if ( !youthGame.equals("-1") && youthGame != null )
					youthScores[i].setText(youthGame);
			}
			
			//Recalculate numbers
			calculateNumbers();
		}
		
	}

	public void setFocus() {
		form.setFocus();
	}
	
	private void setInput(BMTeam team)
	{
		previousTeam = currentTeam;
		currentTeam = team;
		inputChanged(previousTeam, currentTeam);
	}
	
	private void saveScoresToTournament( )
	{
		if ( currentTeam != null)
		{
			//Save Adult Scores
			for (int i = 0; i < adultScores.length; i++) {
				if ( !adultScores[i].getText().equals("") )
					currentTeam.getAdult().setGame(i, Integer.valueOf(adultScores[i].getText()).intValue());
				else
					currentTeam.getAdult().setGame(i, -1);				
			}
			//Save Youth Scores
			for (int i = 0; i < youthScores.length; i++) {
				if ( !youthScores[i].getText().equals("") )
					currentTeam.getYouth().setGame(i, Integer.valueOf(youthScores[i].getText()).intValue());
				else
					currentTeam.getYouth().setGame(i, -1);
			}						
			Activator.getDefault().getTournament().updateTeam(currentTeam);
			currentTeam.scoreChanged();
		}
	}
	
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if ( incoming instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection)incoming;
			if (selection.getFirstElement() instanceof BMTeam )
			{
				if ( currentTeam != null)
				{
					//Resets Adult Scores
					for (int i = 0; i < adultScores.length; i++) {
							adultScores[i].setText("");					
					}
					//Resets Youth Scores
					for (int i = 0; i < youthScores.length; i++) {
							youthScores[i].setText("");
					}						
				}
				BMTeam team = (BMTeam) selection.getFirstElement();
				setInput(team);
				displayTeam(team);
				
			}
		}
	}
	
	public void verifyText(VerifyEvent event) {
	    // Assume we don't allow it
	    event.doit = true;

	    // Get the new Text to be inserted
	    String newText = ((Text) event.widget).getText() + event.text;
	    if ( newText == "")
	    	return;
	    try {
	    	 int newValue = Integer.valueOf(newText).intValue();
	    	 if ( newValue > 450 || newValue < -1 )
	    	 {
	    		 event.doit = false;
	    	 }
		} catch (Exception e) {
			event.doit = false;
		}
	  }
	
	public void dispose() {
		super.dispose();
		red.dispose();
		green.dispose();
		black.dispose();
	}
	
	public void inputChanged(Object oldInput, Object newInput) {
		
		    if (oldInput != newInput) { // if not the same

		      if (newInput != null) { // add listener to new - fires even if old is null
		        ((BMTeam) newInput).addPropertyChangeListener(this);
		      }

		      if (oldInput != null) { // remove from old - fires even if new is null
		        ((BMTeam) oldInput).removePropertyChangeListener(this);
		      }
		    }
	}

	public void propertyChange(final PropertyChangeEvent event) {
		
		Control ctrl = form.getContent();
	    if (ctrl != null && !ctrl.isDisposed()) {

	      ctrl.getDisplay().asyncExec(new Runnable() {

	        public void run() {
	          if (event.getProperty() == "teamChanged")
	          {
	        	displayTeam(currentTeam);
	          }    
	        }
	      });
	    }	
		
	}

}
