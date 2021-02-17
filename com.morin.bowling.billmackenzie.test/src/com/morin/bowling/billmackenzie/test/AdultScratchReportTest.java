package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class AdultScratchReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestAdultScratchReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 256),
			new WrappedTeam(TeamTestReport.TEAM1, 234),
			new WrappedTeam(TeamTestReport.TEAM5, 231),
			new WrappedTeam(TeamTestReport.TEAM2, 198),
			new WrappedTeam(TeamTestReport.TEAM3, 168)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(0, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 491),
			new WrappedTeam(TeamTestReport.TEAM5, 450),
			new WrappedTeam(TeamTestReport.TEAM1, 401),
			new WrappedTeam(TeamTestReport.TEAM2, 371),
			new WrappedTeam(TeamTestReport.TEAM3, 366)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(1, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 798),
			new WrappedTeam(TeamTestReport.TEAM5, 640),
			new WrappedTeam(TeamTestReport.TEAM1, 602),
			new WrappedTeam(TeamTestReport.TEAM2, 576),
			new WrappedTeam(TeamTestReport.TEAM3, 511)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(2, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 1013),
			new WrappedTeam(TeamTestReport.TEAM5, 816),
			new WrappedTeam(TeamTestReport.TEAM1, 796),
			new WrappedTeam(TeamTestReport.TEAM2, 774),
			new WrappedTeam(TeamTestReport.TEAM3, 686)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(3, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 1256),
			new WrappedTeam(TeamTestReport.TEAM5, 1020),
			new WrappedTeam(TeamTestReport.TEAM1, 1001),
			new WrappedTeam(TeamTestReport.TEAM2, 988),
			new WrappedTeam(TeamTestReport.TEAM3, 854)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(4, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
