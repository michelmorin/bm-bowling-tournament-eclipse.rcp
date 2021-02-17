package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class AdultPOAReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestAdultPOAReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 40),
			new WrappedTeam(TeamTestReport.TEAM5, 39),
			new WrappedTeam(TeamTestReport.TEAM4, 25),
			new WrappedTeam(TeamTestReport.TEAM3, 22),
			new WrappedTeam(TeamTestReport.TEAM2, -6)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(0, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOAReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 74),
			new WrappedTeam(TeamTestReport.TEAM5, 66),
			new WrappedTeam(TeamTestReport.TEAM4, 29),
			new WrappedTeam(TeamTestReport.TEAM1, 13),							
			new WrappedTeam(TeamTestReport.TEAM2, -37)};
			
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(1, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOAReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 105),
			new WrappedTeam(TeamTestReport.TEAM3, 73),
			new WrappedTeam(TeamTestReport.TEAM5, 64),
			new WrappedTeam(TeamTestReport.TEAM1, 20),
			new WrappedTeam(TeamTestReport.TEAM2, -36)};
			
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(2, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOAReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 102),
			new WrappedTeam(TeamTestReport.TEAM4, 89),
			new WrappedTeam(TeamTestReport.TEAM5, 48),
			new WrappedTeam(TeamTestReport.TEAM1, 20),
			new WrappedTeam(TeamTestReport.TEAM2, -42)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(3, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOAReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 124),
			new WrappedTeam(TeamTestReport.TEAM4, 101),
			new WrappedTeam(TeamTestReport.TEAM5, 60),
			new WrappedTeam(TeamTestReport.TEAM1, 31),
			new WrappedTeam(TeamTestReport.TEAM2, -32)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPOA(4, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
