package com.morin.bowling.platform.close;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;


public class CloseTournamentProxy implements ICloseTournament {
	
	IConfigurationElement ce;
	ICloseTournament action;

	public CloseTournamentProxy(IConfigurationElement ce) {
		this.ce = ce;
	}
	
	public void run() {
		if (action == null)
			try {
				action = (ICloseTournament)ce.createExecutableExtension("close");
			} catch (CoreException e) {
				System.out.print("...failed! Reason: " + e.getLocalizedMessage());
			}
			
			action.run();
	}
}
