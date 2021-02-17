package com.morin.bowling.billmackenzie.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.model.AdultBowler;
import com.morin.bowling.billmackenzie.model.BMTeam;
import com.morin.bowling.billmackenzie.model.YouthBowler;

public class AddTeamDialog extends TitleAreaDialog implements VerifyListener{
	
	 private Text teamIDText;
	 private Text bowlingCentreText;
	 private Button okButton;
	
	 private Text adultNameText;
	 private Text adultAverageText; 
	 private Button adultSinglesPoolPOA;
	 private Button adultSinglesPoolScratch;
	 
	 private Text youthNameText;
	 private Text youthAverageText; 
	 private Button youthSinglesPoolPOA;
	 private Button youthSinglesPoolScratch;
	 
	 private BMTeam team;

  public AddTeamDialog(Shell shell) {
    super(shell);
  }
  
  public AddTeamDialog(Shell shell, BMTeam team) {
	    super(shell);
	    this.team = team;
	  }

  /**
   * @see org.eclipse.jface.window.Window#create() We complete the dialog with
   *      a title and a message
   */
  public void create() {
    super.create();
    setTitle("Add Team");
    setMessage("Add new Team description");
    getShell().setText("Add Team Title");
  }

  /**
   * @see org.eclipse.jface.dialogs.Dialog#
   *      createDialogArea(org.eclipse.swt.widgets.Composite) Here we fill the
   *      center area of the dialog
   */
  protected Control createDialogArea(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout containerLayout = new GridLayout();
    containerLayout.numColumns=2;
    container.setLayout(containerLayout);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    container.setLayoutData(data);
    
    GridData gd = new GridData();
    Label teamIDLabel = new Label(container,SWT.TRAIL);
    teamIDLabel.setText("ID:");
    teamIDLabel.setLayoutData(gd);
    
    gd = new GridData();
    gd.widthHint = 50;
    teamIDText = new Text(container,SWT.LEFT|SWT.BORDER);
    teamIDText.setLayoutData(gd);
    teamIDText.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			verifyComplete();
		}
	});
    
    gd = new GridData();
    Label adultCentreLabel = new Label(container,SWT.TRAIL);
    adultCentreLabel.setText("Bowling Centre:");
    adultCentreLabel.setLayoutData(gd);
    
    gd = new GridData();
    gd.widthHint = 200;
    bowlingCentreText = new Text(container,SWT.LEFT|SWT.BORDER);
    bowlingCentreText.setTextLimit(30);
    bowlingCentreText.setLayoutData(gd);
    bowlingCentreText.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			verifyComplete();
		}
	});
    
    gd = new GridData(GridData.FILL_BOTH);
    gd.horizontalSpan=3;
    gd.verticalIndent=15;
    Group youthGroup = new Group(container, SWT.NONE);
    youthGroup.setText("Youth Bowler");
    GridLayout youthLayout = new GridLayout();
    youthLayout.numColumns = 4;
    youthGroup.setLayoutData(gd);
    youthGroup.setLayout(youthLayout);
    
    gd = new GridData();
    Label youthNameLabel = new Label(youthGroup,SWT.TRAIL);
    youthNameLabel.setText("Name:");
    youthNameLabel.setLayoutData(gd);  
    
    gd = new GridData(GridData.FILL_HORIZONTAL);
    youthNameText = new Text(youthGroup,SWT.LEFT|SWT.BORDER);
    youthNameText.setTextLimit(25);
    youthNameText.setLayoutData(gd);
    youthNameText.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			verifyComplete();
		}
	});
    
    Label youthAverageLabel = new Label(youthGroup,SWT.LEFT);
    youthAverageLabel.setText("Average:");
    
    gd = new GridData();
    gd.widthHint = 40;
    youthAverageText = new Text(youthGroup,SWT.CENTER|SWT.BORDER);
    youthAverageText.setTextLimit(3);
    youthAverageText.addVerifyListener(this);
    youthAverageText.setLayoutData(gd);
    youthAverageText.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			verifyComplete();
		}
	});
    
    gd = new GridData(GridData.FILL_HORIZONTAL);
    youthSinglesPoolPOA = new Button(youthGroup, SWT.CHECK);
    youthSinglesPoolPOA.setText("Singles POA Pool");
    gd.horizontalSpan = 4;
    youthSinglesPoolPOA.setLayoutData(gd);
    
    gd = new GridData(GridData.FILL_HORIZONTAL);
    youthSinglesPoolScratch = new Button(youthGroup, SWT.CHECK);
    youthSinglesPoolScratch.setText("Singles Scratch Pool");
    gd.horizontalSpan = 4;
    youthSinglesPoolScratch.setLayoutData(gd);
    
    gd = new GridData(GridData.FILL_BOTH);
    gd.horizontalSpan=3;
    gd.verticalIndent=15;
    Group adultGroup = new Group(container, SWT.NONE);
    adultGroup.setText("Adult Bowler");
    GridLayout adultLayout = new GridLayout();
    adultLayout.numColumns = 4;
    adultGroup.setLayout(adultLayout);
    adultGroup.setLayoutData(gd);
    
    gd = new GridData();
    Label adultNameLabel = new Label(adultGroup,SWT.TRAIL);
    adultNameLabel.setText("Name:");
    adultNameLabel.setLayoutData(gd);
    
    gd = new GridData(GridData.FILL_HORIZONTAL);
    adultNameText = new Text(adultGroup,SWT.LEFT|SWT.BORDER);
    adultNameText.setTextLimit(25);
    adultNameText.setLayoutData(gd);
    adultNameText.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			verifyComplete();
		}
	});
    
    Label adultAverageLabel = new Label(adultGroup,SWT.LEFT);
    adultAverageLabel.setText("Average:");
    
    gd = new GridData();
    gd.widthHint=40;
    adultAverageText = new Text(adultGroup,SWT.CENTER|SWT.BORDER);
    adultAverageText.setTextLimit(3);
    adultAverageText.addVerifyListener(this);
    adultAverageText.setLayoutData(gd);
    adultAverageText.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			verifyComplete();
		}
	});
    
    gd = new GridData(GridData.FILL_HORIZONTAL);
    adultSinglesPoolPOA = new Button(adultGroup, SWT.CHECK);
    adultSinglesPoolPOA.setText("Singles POA Pool");
    gd.horizontalSpan = 4;
    adultSinglesPoolPOA.setLayoutData(gd);
    
    gd = new GridData(GridData.FILL_HORIZONTAL);
    adultSinglesPoolScratch = new Button(adultGroup, SWT.CHECK);
    adultSinglesPoolScratch.setText("Singles Scratch Pool");
    gd.horizontalSpan = 4;
    adultSinglesPoolScratch.setLayoutData(gd);
    
    if (team != null)
    	displayTeamInfo();
    else
    	teamIDText.setText(String.valueOf(Activator.getDefault().getTournament().getNextAvailableTeamID()));
    
    
    return container;
  }
  
  private void displayTeamInfo() {
	  if (team != null)
	  {
		  teamIDText.setText(String.valueOf(team.getId()));
		  bowlingCentreText.setText(team.getBowlingCentre());
		  adultNameText.setText(team.getAdult().getName());
		  adultAverageText.setText(String.valueOf(team.getAdult().getAverage()));
		  adultSinglesPoolPOA.setSelection(team.getAdult().isPoaPot());
		  adultSinglesPoolScratch.setSelection(team.getAdult().isScratchPot());
		  
		  youthNameText.setText(team.getYouth().getName());
		  youthAverageText.setText(String.valueOf(team.getYouth().getAverage()));
		  youthSinglesPoolPOA.setSelection(team.getYouth().isPoaPot());
		  youthSinglesPoolScratch.setSelection(team.getYouth().isScratchPot());
	  }
}

public BMTeam getTeam() {
      return team;
  }
  

  protected void buttonPressed(int buttonId) 
  {
      if (buttonId == IDialogConstants.OK_ID) 
      {
    	  if (team == null)
    	  {
    		  team = new BMTeam(Integer.parseInt(teamIDText.getText()), bowlingCentreText.getText(), new AdultBowler(adultNameText.getText(), Integer.valueOf(adultAverageText.getText()).intValue(), adultSinglesPoolScratch.getSelection(), adultSinglesPoolPOA.getSelection()), new YouthBowler(youthNameText.getText(), Integer.valueOf(youthAverageText.getText()).intValue(), youthSinglesPoolScratch.getSelection(), youthSinglesPoolPOA.getSelection()));
     	  }
    	  else
    	  {
    		  team.setId(Integer.parseInt(teamIDText.getText()));
	    	  team.setBowlingCentre(bowlingCentreText.getText());
	    	  
	    	  team.getAdult().setName(adultNameText.getText());
	    	  team.getAdult().setAverage(Integer.valueOf(adultAverageText.getText()).intValue());
	    	  team.getAdult().setPoaPot(adultSinglesPoolPOA.getSelection());
	    	  team.getAdult().setScratchPot(adultSinglesPoolScratch.getSelection());
	    	  
	    	  team.getYouth().setName(youthNameText.getText());
	    	  team.getYouth().setAverage(Integer.valueOf(youthAverageText.getText()).intValue());
	    	  team.getYouth().setPoaPot(youthSinglesPoolPOA.getSelection());
	    	  team.getYouth().setScratchPot(youthSinglesPoolScratch.getSelection());
    	  }
      }
      super.buttonPressed(buttonId);
  }
  
  protected void createButtonsForButtonBar(Composite parent) {
		
		//super.createButtonBar(parent);
		okButton = createButton(
				parent,
				IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL,
				true);
		createButton(
			parent,
			IDialogConstants.CANCEL_ID,
			IDialogConstants.CANCEL_LABEL,
			false);
		
		if (team != null)
	       	okButton.setEnabled(true);
		else
			okButton.setEnabled(false);
	}
  private void verifyComplete() {
		
		if (okButton == null) {
			return;		
		}
		
		if ( teamIDText.getText().trim().length() == 0 ) {
			okButton.setEnabled(false);
			setErrorMessage("Some required fields are empty.");
			return;
		}
		
		if ( bowlingCentreText.getText().trim().length() == 0 ) {
			okButton.setEnabled(false);
			setErrorMessage("Some required fields are empty.");
			return;
		}
		
		if ( adultNameText.getText().trim().length() == 0 ) {
			okButton.setEnabled(false);
			setErrorMessage("Some required fields are empty.");
			return;
		}
		
		if ( adultAverageText.getText().trim().length() == 0 ) {
			okButton.setEnabled(false);
			setErrorMessage("Some required fields are empty.");
			return;
		}
		
		if ( youthNameText.getText().trim().length() == 0 ) {
			okButton.setEnabled(false);
			setErrorMessage("Some required fields are empty.");
			return;
		}
		
		if ( youthAverageText.getText().trim().length() == 0 ) {
			okButton.setEnabled(false);
			setErrorMessage("Some required fields are empty.");
			return;
		}
		
		if (team != null)
		{
			if ( team.getId() != Integer.parseInt(teamIDText.getText())) {
				if ( Activator.getDefault().getTournament().getTeam( Integer.parseInt(teamIDText.getText()) ) != null ) {
					setErrorMessage("The id given already exist.");
					okButton.setEnabled(false);
					return;
				}
			}	
		}
		else
		{
			if ( Activator.getDefault().getTournament().getTeam( Integer.parseInt(teamIDText.getText()) ) != null ) {
				setErrorMessage("The id given already exist.");
				okButton.setEnabled(false);
				return;
			}
		}
						
		okButton.setEnabled(true);	
		setErrorMessage(null);
	}


public void verifyText(VerifyEvent event) {
	//Assume we don't allow it
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
  
}
