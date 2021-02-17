package com.morin.bowling.billmackenzie.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.Perspective;
import com.morin.bowling.billmackenzie.dialogs.NewTournamentDialog;
import com.morin.bowling.billmackenzie.model.BMTournament;


public class NewTournamentAction extends Action implements IWorkbenchAction {
	
	private IWorkbenchWindow window;
	
	private final static String ID = "com.morin.bowling.billmackenzie.addteam";
	
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
			if (dialog.getTournamentName() != null)
			{
				try {
					PlatformUI.getWorkbench().showPerspective(Perspective.ID, window);
				} catch (WorkbenchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				BMTournament tournament = Activator.getDefault().getTournament();
				tournament.clear();
				tournament.setName(dialog.getTournamentName());
				Activator.getDefault().setTournamentLocation(null);
			}
		}	
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
