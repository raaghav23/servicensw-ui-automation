package stepDefinition;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
@author : Raaghav
 */
public class SuiteLevelDesign extends AbstractTestNGCucumberTests{

	@BeforeSuite(alwaysRun = true)	
	public void beforesuiteExecution() throws Throwable {
		String directoryLocation = System.getProperty("user.dir") +"/target/JsonReports";				
		File directory = new File(directoryLocation);
		if (directory.exists()) {
			directory.delete();		
		} 
		if(!directory.exists()) {	
			directory.mkdir();		
		}   	    	
	}

	@AfterSuite(alwaysRun = true)	
	public void aftersuiteExecution() throws Throwable {		
		File reportOutputDirectory = new File("target");
		List<String> jsonFiles = new ArrayList<String>();
		String browserName = "Chrome";
		jsonFiles.add(System.getProperty("user.dir") +"/target/JsonReports/cucumber-report.json");		
		String jenkinsBasePath = "";
		String buildNumber = "1";
		String projectName = "FOXWEB Project "+"("+browserName+")";
		boolean skippedFails = true;
		boolean pendingFails = false;
		boolean undefinedFails = true;
		boolean runWithJenkins = false;
		boolean parallelTesting = false;
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		configuration.setStatusFlags(skippedFails, pendingFails, undefinedFails);
		configuration.setParallelTesting(parallelTesting);
		configuration.setJenkinsBasePath(jenkinsBasePath);
		configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber(buildNumber);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();

	}
}