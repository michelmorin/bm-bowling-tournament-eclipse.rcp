package com.morin.bowling.billmackenzie.actions;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;

import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.Perspective;
import com.morin.bowling.platform.open.IOpenTournament;

public class OpenTournamentAction implements IOpenTournament {
	
	public void run(String tournamentFile) {
		
		try {
			PlatformUI.getWorkbench().showPerspective(Perspective.ID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File(tournamentFile);
		OpenSaveTournamentOperation.openTournamentFromFile(file);
		Activator.getDefault().setTournamentLocation(tournamentFile);
	}
    	
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		
	}
}
