package com.morin.bowling.platform.actions;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.update.search.BackLevelFilter;
import org.eclipse.update.search.EnvironmentFilter;
import org.eclipse.update.search.UpdateSearchRequest;
import org.eclipse.update.search.UpdateSearchScope;
import org.eclipse.update.ui.UpdateJob;
import org.eclipse.update.ui.UpdateManagerUI;

public class NewFeaturesAction extends Action implements IAction {
	
	private IWorkbenchWindow window;
	
	public NewFeaturesAction(IWorkbenchWindow window) {
		this.window = window;
		setId("com.morin.bowling.platform.newfeatures");
		setText("Search for new Tournaments...");
		setToolTipText("Search for new Tournaments available");
		//setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, "icons/usearch_obj.gif"));
		window.getWorkbench().getHelpSystem().setHelp(this, "com.morin.bowling.platform.updates");		
	}
	
	public void run() {
		BusyIndicator.showWhile(window.getShell().getDisplay(), new Runnable() {
			public void run() {
				UpdateJob job = new UpdateJob("Searching for new tournaments", getSearchRequest());
				UpdateManagerUI.openInstaller(window.getShell(), job);
			}

			private UpdateSearchRequest getSearchRequest() {
				UpdateSearchRequest result = new UpdateSearchRequest(
						UpdateSearchRequest.createDefaultSiteSearchCategory(), 
						new UpdateSearchScope());
				result.addFilter(new BackLevelFilter());
				result.addFilter(new EnvironmentFilter());
				UpdateSearchScope scope = new UpdateSearchScope();
				try {
					String homeBase = System.getProperty("bowling.homebase",
							"http://www.foreveramazed.com/bowling/updates");
					URL url = new URL(homeBase);
					scope.addSearchSite("Bowling Site", url, null);
				} catch (MalformedURLException e) {
					// skip bad URLs
				}
				result.setScope(scope);
				return result;
			}
		});
	}
}
