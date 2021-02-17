package com.morin.bowling.billmackenzie.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.morin.bowling.billmackenzie.Activator;

public class SaveAsTournamentAction implements IWorkbenchWindowActionDelegate {
	
	private IWorkbenchWindow window;
	
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		FileDialog dialog = new FileDialog(window.getShell(), SWT.SAVE);
    	dialog.setText("Save Tournament");
    	dialog.setFilterExtensions(new String[]{"*.xml"});
    	String tournamentFile = dialog.open();
    	
    	if ( tournamentFile != null )
    	{
    		Activator.getDefault().setTournamentLocation(tournamentFile);	
    	}
    	else
    	{
    		return;
    	}
				
    	OpenSaveTournamentOperation.saveTournamentToFile();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

}
