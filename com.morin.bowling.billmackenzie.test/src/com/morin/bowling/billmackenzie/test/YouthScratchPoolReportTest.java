package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class YouthScratchPoolReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestYouthScratchPoolReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 259),
			new WrappedTeam(TeamTestReport.TEAM3, 216),
			new WrappedTeam(TeamTestReport.TEAM5, 203)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolScratch(0);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchPoolReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM5, 213),
			new WrappedTeam(TeamTestReport.TEAM1, 190),
			new WrappedTeam(TeamTestReport.TEAM3, 189)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolScratch(1);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchPoolReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 231),
			new WrappedTeam(TeamTestReport.TEAM3, 174),
			new WrappedTeam(TeamTestReport.TEAM5, 173)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolScratch(2);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchPoolReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 231),
			new WrappedTeam(TeamTestReport.TEAM5, 186),
			new WrappedTeam(TeamTestReport.TEAM1, 183)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolScratch(3);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthScratchPoolReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 229),
			new WrappedTeam(TeamTestReport.TEAM1, 225),
			new WrappedTeam(TeamTestReport.TEAM5, 201)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolScratch(4);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
