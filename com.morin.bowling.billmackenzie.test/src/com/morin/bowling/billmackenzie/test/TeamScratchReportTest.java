package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class TeamScratchReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestTeamScratchReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 493),
			new WrappedTeam(TeamTestReport.TEAM4, 471),
			new WrappedTeam(TeamTestReport.TEAM5, 434),
			new WrappedTeam(TeamTestReport.TEAM3, 384),
			new WrappedTeam(TeamTestReport.TEAM2, 355)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(0, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamScratchReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 893),
			new WrappedTeam(TeamTestReport.TEAM5, 866),
			new WrappedTeam(TeamTestReport.TEAM1, 850),
			new WrappedTeam(TeamTestReport.TEAM3, 771),
			new WrappedTeam(TeamTestReport.TEAM2, 715)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(1, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamScratchReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 1398),
			new WrappedTeam(TeamTestReport.TEAM1, 1282),
			new WrappedTeam(TeamTestReport.TEAM5, 1229),
			new WrappedTeam(TeamTestReport.TEAM2, 1110),
			new WrappedTeam(TeamTestReport.TEAM3, 1090)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(2, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamScratchReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 1853),
			new WrappedTeam(TeamTestReport.TEAM1, 1659),
			new WrappedTeam(TeamTestReport.TEAM5, 1591),
			new WrappedTeam(TeamTestReport.TEAM3, 1496),
			new WrappedTeam(TeamTestReport.TEAM2, 1483)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(3, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamScratchReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 2301),
			new WrappedTeam(TeamTestReport.TEAM1, 2089),
			new WrappedTeam(TeamTestReport.TEAM5, 1996),
			new WrappedTeam(TeamTestReport.TEAM2, 1895),
			new WrappedTeam(TeamTestReport.TEAM3, 1893)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(4, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
