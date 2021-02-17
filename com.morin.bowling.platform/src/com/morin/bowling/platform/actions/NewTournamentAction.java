package com.morin.bowling.platform.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.morin.bowling.platform.Tournament;
import com.morin.bowling.platform.dialogs.NewTournamentDialog;

public class NewTournamentAction extends Action implements IWorkbenchAction {
	
	private IWorkbenchWindow window;
	
	private final static String ID = "com.morin.bowling.platform.newTournament";
	
	public NewTournamentAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&New...");
		setToolTipText("Create new tournament");
	}
	
	public void run() {
		NewTournamentDialog dialog = new NewTournamentDialog(window.getShell());
		dialog.create();
		if (dialog.open() == IDialogConstants.OK_ID) 
		{
			if (dialog.getSelectedTournament() != null)
			{
				Tournament selectedTournament = dialog.getSelectedTournament();
				try {
					PlatformUI.getWorkbench().showPerspective(selectedTournament.getPerspectiveID(), window);
				} catch (WorkbenchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
