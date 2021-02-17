package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class YouthScratchReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestYouthScratchReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 259),
			new WrappedTeam(TeamTestReport.TEAM3, 216),
			new WrappedTeam(TeamTestReport.TEAM4, 215),
			new WrappedTeam(TeamTestReport.TEAM5, 203),		
			new WrappedTeam(TeamTestReport.TEAM2, 157)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(0, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 449),
			new WrappedTeam(TeamTestReport.TEAM5, 416),
			new WrappedTeam(TeamTestReport.TEAM3, 405),
			new WrappedTeam(TeamTestReport.TEAM4, 402),	
			new WrappedTeam(TeamTestReport.TEAM2, 344)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(1, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 680),
			new WrappedTeam(TeamTestReport.TEAM4, 600),	
			new WrappedTeam(TeamTestReport.TEAM5, 589),
			new WrappedTeam(TeamTestReport.TEAM3, 579),
			new WrappedTeam(TeamTestReport.TEAM2, 534)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(2, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 863),
			new WrappedTeam(TeamTestReport.TEAM4, 840),
			new WrappedTeam(TeamTestReport.TEAM3, 810),
			new WrappedTeam(TeamTestReport.TEAM5, 775),	
			new WrappedTeam(TeamTestReport.TEAM2, 709)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(3, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 1088),
			new WrappedTeam(TeamTestReport.TEAM4, 1045),
			new WrappedTeam(TeamTestReport.TEAM3, 1039),
			new WrappedTeam(TeamTestReport.TEAM5, 976),
			new WrappedTeam(TeamTestReport.TEAM2, 907)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(4, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
