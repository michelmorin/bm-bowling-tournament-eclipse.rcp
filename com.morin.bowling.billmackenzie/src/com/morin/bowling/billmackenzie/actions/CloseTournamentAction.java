package com.morin.bowling.billmackenzie.actions;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.platform.close.ICloseTournament;

public class CloseTournamentAction implements ICloseTournament {
	
	public void run() {
	
    	Activator.getDefault().getTournament().clear();
    	Activator.getDefault().getTournament().setName("dummy");
    	Activator.getDefault().setTournamentLocation(null);
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
