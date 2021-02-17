package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class TeamScratchBestEvenReportTest {

	TeamTestReport teamTestReport = new TeamTestReport();
	
	@Test
	public void TestTeamScratchBestEventReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 493),
			new WrappedTeam(TeamTestReport.TEAM5, 434),
			new WrappedTeam(TeamTestReport.TEAM2, 355)};
			
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(0, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamScratchBestEventReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 893),
			new WrappedTeam(TeamTestReport.TEAM1, 850),
			new WrappedTeam(TeamTestReport.TEAM2, 715)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(1, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamScratchBestEventReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 1398),
			new WrappedTeam(TeamTestReport.TEAM1, 1282),
			new WrappedTeam(TeamTestReport.TEAM2, 1110)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(2, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamScratchBestEventReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 1853),
			new WrappedTeam(TeamTestReport.TEAM1, 1659),
			new WrappedTeam(TeamTestReport.TEAM2, 1483)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(3, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamScratchBestEventReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 2301),
			new WrappedTeam(TeamTestReport.TEAM1, 2089),
			new WrappedTeam(TeamTestReport.TEAM2, 1895)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamScratch(4, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
