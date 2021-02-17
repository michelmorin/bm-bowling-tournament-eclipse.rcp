package com.morin.bowling.billmackenzie.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewTournamentDialog extends TitleAreaDialog {
	
	private Text tournamentNameText;
	private String tournamentName;

  public NewTournamentDialog(Shell shell) {
    super(shell);
  }

  /**
   * @see org.eclipse.jface.window.Window#create() We complete the dialog with
   *      a title and a message
   */
  public void create() {
    super.create();
    setTitle("New Tournament");
    setMessage("Create a new Tournament");
    getShell().setText("New Tournament");
    tournamentName = null;
  }

  /**
   * @see org.eclipse.jface.dialogs.Dialog#
   *      createDialogArea(org.eclipse.swt.widgets.Composite) Here we fill the
   *      center area of the dialog
   */
  protected Control createDialogArea(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    layout.numColumns=2;
    container.setLayout(layout);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    container.setLayoutData(data);
    
    Label label = new Label(container, SWT.RIGHT);
    label.setText("Name:");
    
    tournamentNameText = new Text(container,SWT.SINGLE|SWT.BORDER);
    data = new GridData(GridData.FILL_HORIZONTAL);
    tournamentNameText.setLayoutData(data);
    
    return container;
  }
  
  public String getTournamentName() {
      return tournamentName;
  }
  
  protected void buttonPressed(int buttonId) {
      if (buttonId == IDialogConstants.OK_ID) 
      {
         tournamentName = tournamentNameText.getText();
      }
      super.buttonPressed(buttonId);
  }
}
