package com.morin.bowling.billmackenzie.views;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;

import com.morin.bowling.billmackenzie.model.BMTournament;

public class TeamsContentProvider implements IStructuredContentProvider, IPropertyChangeListener {
	
	private StructuredViewer viewer;

	public Object[] getElements(Object inputElement) {
		return ((BMTournament)inputElement).getAllTeams();
	}

	public void dispose() {
		viewer = null;

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (this.viewer == null)
		      this.viewer = (StructuredViewer) viewer;

		    if (oldInput != newInput) { // if not the same

		      if (newInput != null) { // add listener to new - fires even if old is null
		        ((BMTournament) newInput).addPropertyChangeListener(this);
		      }

		      if (oldInput != null) { // remove from old - fires even if new is null
		        ((BMTournament) oldInput).removePropertyChangeListener(this);
		      }
		    }
	}

	public void propertyChange(final PropertyChangeEvent event) {
		Control ctrl = viewer.getControl();
	    if (ctrl != null && !ctrl.isDisposed()) {

	      ctrl.getDisplay().asyncExec(new Runnable() {

	        public void run() {
	          if (event.getProperty() == "tournamentChange")
	          {
	            viewer.refresh();
	          }         
	          
	          //else {
	          //  String[] propChange = new String[] {event.getProperty()};
	          //  viewer.update(event.getNewValue(), propChange);
	          //}
	        }
	      });
	    }
	}

}
