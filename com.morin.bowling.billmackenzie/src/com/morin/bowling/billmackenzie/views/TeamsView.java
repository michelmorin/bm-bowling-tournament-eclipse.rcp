package com.morin.bowling.billmackenzie.views;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.model.BMTeam;
import com.morin.bowling.billmackenzie.model.BMTournament;

public class TeamsView extends ViewPart implements ITabbedPropertySheetPageContributor {
	
	public static final String ID = "com.morin.bowling.billmackenzie.views.teams";
	
	private TableViewer viewer;
	
	TeamFilter teamFilter = null;
		
	Text findTeamText;
	
	class TeamSorter extends ViewerSorter
	{
		public int category (Object element) {
			return ((BMTeam)element).getId();			
		}
	}
	
	class TeamFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			String searchText = findTeamText.getText().trim();
			
			// TODO Auto-generated method stub
			if ( searchText.equals("") )
				return true;
				
			
			//make case sensitivity be ignored here			
			if ( ((BMTeam)element).getAdult().getName().toLowerCase().contains(searchText.toLowerCase()) 
					|| ((BMTeam)element).getYouth().getName().toLowerCase().contains(searchText.toLowerCase()) 
					|| String.valueOf(((BMTeam)element).getId()).toLowerCase().contains(searchText.toLowerCase()) 
					|| ((BMTeam)element).getBowlingCentre().toLowerCase().contains(searchText.toLowerCase()))
				return true;
				
				
			return false;
		}
		}
		
	public TeamsView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createPartControl(Composite parent) {
	
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		parent.setLayout(gridLayout);
				
		Label findTeamLabel= new Label(parent, SWT.BEGINNING);
		findTeamLabel.setText("Find:  ");
		
	    
		
		findTeamText = new Text(parent, SWT.BORDER);
		//findTeamText.setText("Chris");
		GridData gridData1 = new GridData();
	    gridData1.grabExcessHorizontalSpace = true;
	    gridData1.horizontalAlignment = GridData.FILL;
	    findTeamText.setLayoutData(gridData1);
	    findTeamText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				
				if (teamFilter != null )
					viewer.removeFilter(teamFilter);
					
				teamFilter = new TeamFilter();
				TeamFilter[] teamFilters= new TeamFilter[] { teamFilter };
				viewer.setFilters(teamFilters);				
			}
    	});
	    
		
		viewer = new TableViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION );
		
		viewer.setLabelProvider(new TeamsLabelProvider());
		viewer.setContentProvider(new TeamsContentProvider());
		viewer.setSorter(new TeamSorter());
		TeamFilter[] teamFilters= new TeamFilter[] { new TeamFilter() };
		viewer.setFilters(teamFilters);
		
		createColumns(viewer);
		viewer.getTable().setHeaderVisible(true);
			
		viewer.setInput(getInput());
		
		GridData gridData2 = new GridData();
		gridData2.horizontalSpan = 2;
		gridData2.grabExcessHorizontalSpace = true;
	    gridData2.grabExcessVerticalSpace = true;
	    gridData2.horizontalAlignment = GridData.FILL;
	    gridData2.verticalAlignment = GridData.FILL;
		viewer.getTable().setLayoutData(gridData2);
		
		getSite().setSelectionProvider(viewer);	
		
		parent.pack();
		
		makeActions();
	}
	
	private void createColumns(TableViewer viewer) {
		TableColumn column = new TableColumn(viewer.getTable(), SWT.FLAT );
		column.setText("ID");
		column.setResizable(true);
		column.setWidth(40);
		
		column = new TableColumn(viewer.getTable(), SWT.FLAT);
		column.setText("Names");
		column.setResizable(true);
		column.setWidth(200);
	}
	
	private BMTournament getInput() {
		return Activator.getDefault().getTournament();
	}
	
	private void makeActions() {
		MenuManager menuMgr = new MenuManager("teamsPopup");
		menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	public void setFocus() {
		viewer.getControl().setFocus();

	}
	
	public void dispose() {
		super.dispose();
	}
	
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
        if (adapter == IPropertySheetPage.class)
            return new TabbedPropertySheetPage(this);
        return super.getAdapter(adapter);
    }

	public String getContributorId() {
		return getSite().getId();
	}
}
