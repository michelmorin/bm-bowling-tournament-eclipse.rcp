package com.morin.bowling.platform.open;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;


public class OpenTournamentProxy implements IOpenTournament {
	
	IConfigurationElement ce;
	IOpenTournament action;

	public OpenTournamentProxy(IConfigurationElement ce) {
		this.ce = ce;
	}
	
	public void run(String file) {
		if (action == null)
			try {
				action = (IOpenTournament)ce.createExecutableExtension("open");
			} catch (CoreException e) {
				System.out.print("...failed! Reason: " + e.getLocalizedMessage());
			}
			
			if (file != null)
				action.run(file);
	}
}
