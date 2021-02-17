package com.morin.bowling.billmackenzie.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.dialogs.AddTeamDialog;
import com.morin.bowling.billmackenzie.model.BMTeam;
import com.morin.bowling.billmackenzie.model.BMTournament;


public class EditTeamAction implements ISelectionListener, IWorkbenchWindowActionDelegate, IObjectActionDelegate {
	
	private IWorkbenchWindow window;
	private IWorkbenchPart targetPart;
	private BMTeam currentTeam;

	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}
	
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
		window.getSelectionService().addSelectionListener(this);
	}

	public void selectionChanged(IAction action, ISelection incoming) {
		if ( incoming instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection)incoming;
			action.setEnabled(selection.size() == 1 && selection.getFirstElement() instanceof BMTeam);
			
			if (selection.getFirstElement() instanceof BMTeam )
			{
				currentTeam = (BMTeam) selection.getFirstElement();
			}
			else
			{
				action.setEnabled(false);
			}
		}
	}

	public void run(IAction action) {
		if (currentTeam != null )
		{
			Shell shell = null;		
			if (targetPart != null)
				shell = targetPart.getSite().getShell();
			else if (window != null)
				shell = window.getShell();

			if (shell != null)
			{
				int beforeTeamId = currentTeam.getId();
				AddTeamDialog dialog = new AddTeamDialog(shell, currentTeam);
				dialog.create();
				if (dialog.open() == IDialogConstants.OK_ID) 
				{
					BMTournament tournament = Activator.getDefault().getTournament();
					BMTeam team = dialog.getTeam();
					if (team != null)
						if ( beforeTeamId != team.getId())
							tournament.updateTeam(team, true, beforeTeamId);
						else
							tournament.updateTeam(team, false, 0);
						team.teamChanged();
				}	
			}
		}
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		 this.targetPart = targetPart;
		
	}

}
