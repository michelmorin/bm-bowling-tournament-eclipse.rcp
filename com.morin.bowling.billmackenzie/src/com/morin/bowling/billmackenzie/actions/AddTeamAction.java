package com.morin.bowling.billmackenzie.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.dialogs.AddTeamDialog;
import com.morin.bowling.billmackenzie.model.BMTeam;
import com.morin.bowling.billmackenzie.model.BMTournament;

public class AddTeamAction implements IWorkbenchWindowActionDelegate, IObjectActionDelegate {
	
	private IWorkbenchWindow window;
	private IWorkbenchPart targetPart;

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		Shell shell = null;		
		if (targetPart != null)
			shell = targetPart.getSite().getShell();
		else if (window != null)
			shell = window.getShell();
		
		if (shell != null)
		{
			AddTeamDialog dialog = new AddTeamDialog(shell);
			dialog.create();
			if (dialog.open() == IDialogConstants.OK_ID) 
			{
				BMTournament tournament = Activator.getDefault().getTournament();
				BMTeam team = dialog.getTeam();
				if (team != null)
					tournament.addTeam(team);
			}	
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
		
	}

}
