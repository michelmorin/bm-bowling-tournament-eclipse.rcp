package com.morin.bowling.billmackenzie.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import com.morin.bowling.billmackenzie.model.Tournament;

public class BMTournament extends Tournament {
	
	private String name;
	private transient ListenerList propertyChangeListeners = null;
	
	private Hashtable<Integer, BMTeam> teams;

	public BMTournament(String name) {
		this.name = name;
		teams = new Hashtable<Integer, BMTeam>();
	}

	public void addPropertyChangeListener(IPropertyChangeListener listener) {
	    getPropertyChangeListeners().add(listener);
	  }
	
	public void addTeam(BMTeam team) {
		Integer id = new Integer(team.getId());
		teams.put(id, team);
		firePropertyChange("tournamentChange", null, this);
	}

	public void clear() {
		teams.clear();
		name = "";
		firePropertyChange("tournamentChange", null, this);
	}
	
	void firePropertyChange(String changeId, Object oldValue, Object newValue) 
	{
	    final PropertyChangeEvent event = new PropertyChangeEvent(this,
	        changeId, oldValue, newValue);

	    Object[] listeners = getPropertyChangeListeners().getListeners();
	    for (int i = 0; i < listeners.length; i++) 
	    {
	      ((IPropertyChangeListener) listeners[i]).propertyChange(event);
	    }
	}
	
	public BMTeam[] getAllTeams() {
		ArrayList<BMTeam> teamsArray = new ArrayList<BMTeam>();
		Collection<BMTeam> collection = teams.values();
		for (Iterator<BMTeam> iter = collection.iterator(); iter.hasNext();) {
			BMTeam element = (BMTeam) iter.next();
			teamsArray.add(element);
		}
		
		return (BMTeam[]) teamsArray.toArray(new BMTeam[teamsArray.size()]);
	}
	
	public String getName() {
		return name;
	}
	
	public int getNextAvailableTeamID()
	{
		int id = 0;
		boolean found = true;
		
		BMTeam[] allTeams = getAllTeams();
		while( found == true ) 
		{
			found = false;
			id++;
			for (int i = 0; i < allTeams.length; i++) {
				if ( allTeams[i].getId() == id )
				{
					found = true;
				}
			}
		}
		
		return id;
	}
	
	private ListenerList getPropertyChangeListeners() 
	{
	    if (propertyChangeListeners == null)
	      propertyChangeListeners = new ListenerList();
	    return propertyChangeListeners;
	}
	
	public BMTeam getTeam(int id) {
		//if index does not exist return null
		return (BMTeam) teams.get(Integer.valueOf(id));
	}
	
	public void removePropertyChangeListener(
		      IPropertyChangeListener listener) {
		    getPropertyChangeListeners().remove(listener);
		  }
	
	public void removeTeam(BMTeam team) {
		Integer id = new Integer(team.getId());
		teams.remove(id);
		firePropertyChange("tournamentChange", null, this);
	}
	public void removeTeam(int id) {
		teams.remove(id);
		firePropertyChange("tournamentChange", null, this);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void updateTeam(BMTeam team, boolean idChanged, int id) {
			if ( idChanged )
			{
				removeTeam(id);
				addTeam(team);
			}
			else
			{
				addTeam(team);
			}
			firePropertyChange("tournamentChange", null, this);
	}
	public void updateTeam(BMTeam team) {
		addTeam(team);
		firePropertyChange("tournamentChange", null, this);
}
}
