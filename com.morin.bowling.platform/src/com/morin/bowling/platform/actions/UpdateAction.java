package com.morin.bowling.platform.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.update.ui.UpdateJob;
import org.eclipse.update.ui.UpdateManagerUI;

public class UpdateAction extends Action implements IAction {
	
	private IWorkbenchWindow window;
	
	public UpdateAction(IWorkbenchWindow window) {
		this.window = window;
		setId("com.morin.bowling.platform.newupdates");
		setText("Check for Updates...");
		setToolTipText("Search for updates");
		//setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, "icons/usearch_obj.gif"));
		window.getWorkbench().getHelpSystem().setHelp(this, "com.morin.bowling.platform.updates");		
	}
	
	public void run() {
		BusyIndicator.showWhile(window.getShell().getDisplay(), new Runnable() {
			public void run() {
				UpdateJob job = new UpdateJob("Searching for updates", false, false);
				UpdateManagerUI.openInstaller(window.getShell(), job);
			}
		});
	}
	
	

}
