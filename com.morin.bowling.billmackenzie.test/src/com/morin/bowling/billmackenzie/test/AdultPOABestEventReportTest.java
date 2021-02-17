package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class AdultPOABestEventReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestAdultPOABestEventReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 40),
			new WrappedTeam(TeamTestReport.TEAM3, 22)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(0, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOABestEventReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 74),
			new WrappedTeam(TeamTestReport.TEAM1, 13)};
			
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(1, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOABestEventReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 73),
			new WrappedTeam(TeamTestReport.TEAM1, 20)};
			
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(2, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOABestEventReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 102),
			new WrappedTeam(TeamTestReport.TEAM1, 20)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(3, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOABestEventReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 124),
			new WrappedTeam(TeamTestReport.TEAM1, 31)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(4, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
