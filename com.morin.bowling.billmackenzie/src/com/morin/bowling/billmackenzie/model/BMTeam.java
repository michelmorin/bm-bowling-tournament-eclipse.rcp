package com.morin.bowling.billmackenzie.model;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import com.morin.bowling.billmackenzie.model.Team;

public class BMTeam extends Team {
	
	private AdultBowler adult;
	private YouthBowler youth;
	private String bowlingCentre;
	private int id;
	
	private transient ListenerList propertyChangeListeners = null;

	public BMTeam(int id, String bowlingCentre, AdultBowler adult, YouthBowler youth) {
		this.id = id;
		this.bowlingCentre = bowlingCentre;
		this.adult = adult;
		this.youth = youth;
	}

	public AdultBowler getAdult() {
		return adult;
	}
	
	public void setAdult(AdultBowler adult) {
		this.adult = adult;
	}

	public YouthBowler getYouth() {
		return youth;
	}

	public void setYouth(YouthBowler youth) {
		this.youth = youth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void teamChanged() {
		firePropertyChange("teamChanged", null, null);
	}
	
	public void scoreChanged() {
		firePropertyChange("scoreChanged", null, null);
	}

	public String getBowlingCentre() {
		return bowlingCentre;
	}

	public void setBowlingCentre(String bowlingCentre) {
		this.bowlingCentre = bowlingCentre;
		firePropertyChange("teamChanged", null, null);
	}	
	
	public void addPropertyChangeListener(IPropertyChangeListener listener) {
	    getPropertyChangeListeners().add(listener);
	  }
	
	public void removePropertyChangeListener(
		      IPropertyChangeListener listener) {
		    getPropertyChangeListeners().remove(listener);
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
	
	private ListenerList getPropertyChangeListeners() 
	{
	    if (propertyChangeListeners == null)
	      propertyChangeListeners = new ListenerList();
	    return propertyChangeListeners;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
