package com.morin.bowling.platform.actions;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;

import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.morin.bowling.platform.open.OpenTournamentProxy;

public class OpenTournamentAction extends Action implements IWorkbenchAction {
	
	private IWorkbenchWindow window;
	
	private final static String ID = "com.morin.bowling.platform.open";
	
	public OpenTournamentAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Open...");
		setToolTipText("Open a Tournament");
	}
	
	public void run() {
		
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extPoint = extRegistry.getExtensionPoint("com.morin.bowling.platform.tournament");
		IExtension[] extensions = extPoint.getExtensions();
		Hashtable<String, IConfigurationElement> hashTable = new Hashtable<String, IConfigurationElement>();
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement[] confElement = extensions[i].getConfigurationElements();
			for (int j = 0; j < confElement.length; j++) {
				hashTable.put(confElement[j].getAttribute("id"), confElement[j]);
			}
		}		
		
		FileDialog dialog = new FileDialog(window.getShell());
    	dialog.setText("Open Tournament");
    	dialog.setFilterExtensions(new String[]{"*.xml"});
    	String tournamentFile = dialog.open();
    	
    	if ( tournamentFile != null )
    	{
    		File file = new File(tournamentFile);
    		if (file.exists())
    		{
    			String id = openTournament(file);
    			if (hashTable.containsKey(id))
    			{
    				OpenTournamentProxy op = new OpenTournamentProxy((IConfigurationElement)hashTable.get(id));
    				op.run(tournamentFile);
    			}
    		}
    	}	
	}
	
	private String openTournament(File file) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = null;
		try {
			document = documentBuilder.parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element rootElement = document.getDocumentElement();
		
		return (rootElement.getAttributes().getNamedItem("id")).getNodeValue();
	}
	
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
