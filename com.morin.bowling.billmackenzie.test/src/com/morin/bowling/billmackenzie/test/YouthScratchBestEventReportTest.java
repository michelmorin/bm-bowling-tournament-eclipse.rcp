package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class YouthScratchBestEventReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestYouthScratchBestEventReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 259),
			new WrappedTeam(TeamTestReport.TEAM3, 216),
			new WrappedTeam(TeamTestReport.TEAM2, 157)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(0, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchBestEventReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 449),
			new WrappedTeam(TeamTestReport.TEAM5, 416),
			new WrappedTeam(TeamTestReport.TEAM2, 344)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(1, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchBestEventReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 680),
			new WrappedTeam(TeamTestReport.TEAM5, 589),
			new WrappedTeam(TeamTestReport.TEAM3, 579)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(2, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchBestEventReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 863),
			new WrappedTeam(TeamTestReport.TEAM3, 810),
			new WrappedTeam(TeamTestReport.TEAM5, 775)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(3, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchBestEventReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 1088),
			new WrappedTeam(TeamTestReport.TEAM3, 1039),
			new WrappedTeam(TeamTestReport.TEAM5, 976)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthScratch(4, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
