package com.morin.bowling.platform;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.morin.bowling.platform.actions.CloseTournamentAction;
import com.morin.bowling.platform.actions.NewFeaturesAction;
import com.morin.bowling.platform.actions.NewTournamentAction;
import com.morin.bowling.platform.actions.OpenTournamentAction;
import com.morin.bowling.platform.actions.UpdateAction;


/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private NewTournamentAction newTournamentAction;
	private OpenTournamentAction openTournamentAction;
	private CloseTournamentAction closeTournamentAction;
	private IWorkbenchAction exitAction;
	private UpdateAction updateAction;
	private IAction newFeaturesAction;
	private IWorkbenchAction aboutAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(final IWorkbenchWindow window) {
		
		newTournamentAction = new NewTournamentAction(window);
		openTournamentAction = new OpenTournamentAction(window);
		closeTournamentAction = new CloseTournamentAction(window);
		exitAction = ActionFactory.QUIT.create(window);
		updateAction = new UpdateAction(window);	
		newFeaturesAction = new NewFeaturesAction(window);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(newTournamentAction);
    	register(openTournamentAction);
    	register(closeTournamentAction);
		register(exitAction);
		register(updateAction);
		register(newFeaturesAction);
		register(aboutAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager("&File",	IWorkbenchActionConstants.M_FILE);
		MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
		fileMenu.add(newTournamentAction);
		fileMenu.add(openTournamentAction);
    	fileMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    	fileMenu.add(new Separator());
    	fileMenu.add(closeTournamentAction);
		fileMenu.add(exitAction);
		menuBar.add(fileMenu);
		helpMenu.add(updateAction);
		helpMenu.add(newFeaturesAction);
		helpMenu.add(aboutAction);
		menuBar.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    	menuBar.add(helpMenu);
	}

}
