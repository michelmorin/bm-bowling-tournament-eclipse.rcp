package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class AdultScratchPoolReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestAdultScratchPoolReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 256),
			new WrappedTeam(TeamTestReport.TEAM5, 231),
			new WrappedTeam(TeamTestReport.TEAM2, 198)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolScratch(0);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchPoolReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 235),
			new WrappedTeam(TeamTestReport.TEAM5, 219),
			new WrappedTeam(TeamTestReport.TEAM2, 173)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolScratch(1);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchPoolReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 307),
			new WrappedTeam(TeamTestReport.TEAM2, 205),
			new WrappedTeam(TeamTestReport.TEAM5, 190)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolScratch(2);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchPoolReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 215),
			new WrappedTeam(TeamTestReport.TEAM2, 198),
			new WrappedTeam(TeamTestReport.TEAM5, 176)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolScratch(3);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultScratchPoolReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 243),
			new WrappedTeam(TeamTestReport.TEAM2, 214),
			new WrappedTeam(TeamTestReport.TEAM5, 204)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolScratch(4);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
