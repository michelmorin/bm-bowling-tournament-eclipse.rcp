package com.morin.bowling.billmackenzie;

import org.eclipse.ui.plugin.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

import com.morin.bowling.billmackenzie.model.BMTournament;

/**
 * The main plugin class to be used in the desktop.
 */
public class Activator extends AbstractUIPlugin {
	
	private BMTournament tournament;
	private String tournamentLocation;

	//The shared instance.
	private static Activator plugin;
	
	/**
	 * The constructor.
	 */
	public Activator() {
		plugin = this;
		tournamentLocation = null;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		BMTournament tournament = new BMTournament("dummy");
		Activator.getDefault().setTournament(tournament);
		
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance.
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("com.morin.bowling.tournament", path);
	}
	
	public BMTournament getTournament()
	{
		return tournament;
	}
	public void setTournament(BMTournament tournament)
	{
		this.tournament = tournament;
	}

	public String getTournamentLocation() {
		return tournamentLocation;
	}

	public void setTournamentLocation(String tournamentLocation) {
		this.tournamentLocation = tournamentLocation;
	}
}
