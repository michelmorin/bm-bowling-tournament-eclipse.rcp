package com.morin.bowling.platform.actions;

import java.util.Hashtable;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.morin.bowling.platform.PerspectiveBlank;
import com.morin.bowling.platform.close.CloseTournamentProxy;

public class CloseTournamentAction extends Action implements IWorkbenchAction, IPerspectiveListener {
	
	private final static String ID = "com.morin.bowling.platform.close";
	
	private IWorkbenchWindow window;
	private String currentPerspective;
	
	public CloseTournamentAction(IWorkbenchWindow window) {
		setId(ID);
		setText("&Close");
		setToolTipText("Close Tournament");
		
		this.window = window;	
		window.addPerspectiveListener(this);	
	}
	
	public void run() {  	
		
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extPoint = extRegistry.getExtensionPoint("com.morin.bowling.platform.tournament");
		IExtension[] extensions = extPoint.getExtensions();
		Hashtable<String, IConfigurationElement> hashTable = new Hashtable<String, IConfigurationElement>();
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement[] confElement = extensions[i].getConfigurationElements();
			for (int j = 0; j < confElement.length; j++) {
				hashTable.put(confElement[j].getAttribute("perspective"), confElement[j]);
			}
		}	
		
		if (hashTable.containsKey(currentPerspective))
		{
			CloseTournamentProxy closeTournament = new CloseTournamentProxy((IConfigurationElement)hashTable.get(currentPerspective));
			closeTournament.run();
		}
		
    	try {
			PlatformUI.getWorkbench().showPerspective(PerspectiveBlank.ID, window);
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dispose() {
		window.removePerspectiveListener(this);
		
	}

	public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
		if ( perspective.getId().equals("com.morin.bowling.platform.blankPerspective") )
		{
			setEnabled(false);
		}
		else
		{
			setEnabled(true);
		}
		currentPerspective = perspective.getId();
	}

	public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
		
	}

}
