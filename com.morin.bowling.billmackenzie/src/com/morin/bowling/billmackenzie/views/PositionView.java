package com.morin.bowling.billmackenzie.views;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import com.morin.bowling.billmackenzie.model.BMTeam;
import com.morin.bowling.billmackenzie.utilities.BMStandings;

public class PositionView extends ViewPart implements ISelectionListener, IPropertyChangeListener {
	
	public static final String ID = "com.morin.bowling.billmackenzie.views.position";
	
	private FormToolkit toolkit;
	private ScrolledForm form;
	private Combo gameCombo;
	private Button bestEventFilter;
	
	private BMTeam currentTeam;
	private BMTeam previousTeam;
	private Label youthPOA;
	private Label youthScratch;
	private Label adultPOA;
	private Label adultScratch;
	private Label teamPOA;
	private Label teamScratch;

	public PositionView() {
		// TODO Auto-generated constructor stub
	}

	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		
		GridLayout layout = new GridLayout();
		form.getBody().setLayout(layout);
		
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
		gameCombo.addSelectionListener(new SelectionAdapter() {
	          public void widgetSelected(SelectionEvent e) {
	               displayTeam(currentTeam);
	          }
	     });
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		bestEventFilter = toolkit.createButton(gameOptionsContainer, "Show in best event only", SWT.CHECK);
		bestEventFilter.setLayoutData(gd);
		bestEventFilter.addSelectionListener(new SelectionAdapter() {
	          public void widgetSelected(SelectionEvent e) {
	               displayTeam(currentTeam);
	          }
	     });
		gameOptionsSection.setClient(gameOptionsContainer);
		
		//Youth Section
		Section youthSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR|Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		youthSection.setLayoutData(gd);
		youthSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		youthSection.setText("Youth Position");	
		
		Composite youthContainer = toolkit.createComposite(youthSection);
		GridLayout youthLayout = new GridLayout();
		youthLayout.numColumns = 2;
		youthContainer.setLayout(youthLayout);
		youthContainer.setLayoutData(gd);
		
		toolkit.createLabel(youthContainer, "POA: ", SWT.RIGHT);	
		youthPOA = toolkit.createLabel(youthContainer, "", SWT.RIGHT);
		gd = new GridData();
		gd.widthHint = 25;
		youthPOA.setLayoutData(gd);
		
		toolkit.createLabel(youthContainer, "Scratch: ", SWT.RIGHT);
		youthScratch = toolkit.createLabel(youthContainer, "", SWT.RIGHT);	
		gd = new GridData();
		gd.widthHint = 25;
		youthScratch.setLayoutData(gd);
		
		youthSection.setClient(youthContainer);
		
		//Adult Section
		Section adultSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR|Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		adultSection.setLayoutData(gd);
		adultSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		adultSection.setText("Adult Position");	
		
		Composite adultContainer = toolkit.createComposite(adultSection);
		GridLayout adultLayout = new GridLayout();
		adultLayout.numColumns = 2;
		adultContainer.setLayout(adultLayout);
		
		toolkit.createLabel(adultContainer, "POA: ", SWT.RIGHT);	
		adultPOA = toolkit.createLabel(adultContainer, "", SWT.RIGHT);	
		gd = new GridData();
		gd.widthHint = 25;
		adultPOA.setLayoutData(gd);
		
		toolkit.createLabel(adultContainer, "Scratch: ", SWT.RIGHT);
		adultScratch = toolkit.createLabel(adultContainer, "", SWT.RIGHT);	
		gd = new GridData();
		gd.widthHint = 25;
		adultScratch.setLayoutData(gd);
		
		adultSection.setClient(adultContainer);
		
		//Team Section
		Section teamSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR|Section.TWISTIE|Section.EXPANDED);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		teamSection.setLayoutData(gd);
		teamSection.addExpansionListener(new ExpansionAdapter() {
	    	public void expansionStateChanged(ExpansionEvent e) {
	    		form.reflow(true);
	    	}
	    });
		teamSection.setText("Team Position");	
		
		Composite teamContainer = toolkit.createComposite(teamSection);
		GridLayout teamLayout = new GridLayout();
		teamLayout.numColumns = 2;
		teamContainer.setLayout(teamLayout);
		
		toolkit.createLabel(teamContainer, "POA: ", SWT.RIGHT);	
		teamPOA = toolkit.createLabel(teamContainer, "", SWT.RIGHT);	
		gd = new GridData();
		gd.widthHint = 25;
		teamPOA.setLayoutData(gd);
		
		toolkit.createLabel(teamContainer, "Scratch: ", SWT.RIGHT);
		teamScratch = toolkit.createLabel(teamContainer, "", SWT.RIGHT);
		gd = new GridData();
		gd.widthHint = 25;
		teamScratch.setLayoutData(gd);
		
		teamSection.setClient(teamContainer);
		
		getSite().getPage().addSelectionListener(this);
	}

	public void setFocus() {
		form.setFocus();
	}
	
	public void displayTeam(BMTeam team) {	
		//Resets Labels
		teamScratch.setText("");	
		teamPOA.setText("");	
		adultScratch.setText("");	
		adultPOA.setText("");	
		youthScratch.setText("");	
		youthPOA.setText("");	
		
		String teamScratchString = String.valueOf(BMStandings.getTeamScratchPosition(gameCombo.getSelectionIndex(),bestEventFilter.getSelection(), team.getId()));
		if (!teamScratchString.equals("-1"))
				teamScratch.setText(teamScratchString);
		
		String teamPOAString = String.valueOf(BMStandings.getTeamPOAPosition(gameCombo.getSelectionIndex(),bestEventFilter.getSelection(), team.getId()));
		if (!teamPOAString.equals("-1"))
			teamPOA.setText(teamPOAString);
		
		String adultScratchString = String.valueOf(BMStandings.getAdultScratchPosition(gameCombo.getSelectionIndex(),bestEventFilter.getSelection(), team.getId()));
		if (!adultScratchString.equals("-1"))
			adultScratch.setText(adultScratchString);
		
		String adultPOAString = String.valueOf(BMStandings.getAdultPOAPosition(gameCombo.getSelectionIndex(),bestEventFilter.getSelection(), team.getId()));
		if (!adultPOAString.equals("-1"))
			adultPOA.setText(adultPOAString);
		
		String youthScratchString = String.valueOf(BMStandings.getYouthScratchPosition(gameCombo.getSelectionIndex(),bestEventFilter.getSelection(), team.getId()));
		if (!youthScratchString.equals("-1"))
			youthScratch.setText(youthScratchString);
		
		String youthPOAString = String.valueOf(BMStandings.getYouthPOAPosition(gameCombo.getSelectionIndex(),bestEventFilter.getSelection(), team.getId()));
		if (!youthPOAString.equals("-1"))
			youthPOA.setText(youthPOAString);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if ( incoming instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection)incoming;
			if (selection.getFirstElement() instanceof BMTeam )
			{
				BMTeam team = (BMTeam) selection.getFirstElement();
				setInput(team);
				displayTeam(team);
			}
		}
	}
	
	private void setInput(BMTeam team)
	{
		previousTeam = currentTeam;
		currentTeam = team;
		inputChanged(previousTeam, currentTeam);
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
	          if (event.getProperty() == "scoreChanged" || event.getProperty() == "teamChanged")
	          {
	        	displayTeam(currentTeam);
	          }   
	        }
	      });
	    }	
	}

}
