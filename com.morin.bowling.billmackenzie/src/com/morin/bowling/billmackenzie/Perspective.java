package com.morin.bowling.billmackenzie;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.morin.bowling.billmackenzie.views.PositionView;
import com.morin.bowling.billmackenzie.views.ReportsView;
import com.morin.bowling.billmackenzie.views.ScoresView;
import com.morin.bowling.billmackenzie.views.TeamsView;

public class Perspective implements IPerspectiveFactory {
	
	public static final String ID = "com.morin.bowling.billmackenzie.perspective";

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		
		layout.addView(TeamsView.ID, IPageLayout.LEFT, 0.25f, layout.getEditorArea());
		layout.addView(ScoresView.ID, IPageLayout.RIGHT, 0.26f, TeamsView.ID);
		layout.addView("org.eclipse.ui.views.PropertySheet", IPageLayout.BOTTOM, 0.75f, ScoresView.ID);
		IFolderLayout topRight = layout.createFolder("topRight", IPageLayout.RIGHT, 0.7f, ScoresView.ID);				
		topRight.addView(ReportsView.ID);
		topRight.addView(PositionView.ID);
		
		layout.getViewLayout(TeamsView.ID).setCloseable(false);
		layout.getViewLayout(ScoresView.ID).setCloseable(false);
		layout.getViewLayout(ReportsView.ID).setCloseable(false);
		layout.getViewLayout(PositionView.ID).setCloseable(false);
		layout.getViewLayout("org.eclipse.ui.views.PropertySheet").setCloseable(false);
		
		layout.addActionSet("com.morin.bowling.billmackenzie.actionSet1");
	}
}
