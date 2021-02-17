package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class AdultScratchBestEventReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestAdultScratchBestEventReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 256),
			new WrappedTeam(TeamTestReport.TEAM5, 231),
			new WrappedTeam(TeamTestReport.TEAM2, 198)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(0, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchBestEventReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 491),
			new WrappedTeam(TeamTestReport.TEAM5, 450),
			new WrappedTeam(TeamTestReport.TEAM2, 371)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(1, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchBestEventReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 798),
			new WrappedTeam(TeamTestReport.TEAM5, 640),
			new WrappedTeam(TeamTestReport.TEAM2, 576)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(2, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchBestEventReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 1013),
			new WrappedTeam(TeamTestReport.TEAM5, 816),
			new WrappedTeam(TeamTestReport.TEAM2, 774)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(3, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchBestEventReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 1256),
			new WrappedTeam(TeamTestReport.TEAM5, 1020),
			new WrappedTeam(TeamTestReport.TEAM2, 988)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultScratch(4, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
