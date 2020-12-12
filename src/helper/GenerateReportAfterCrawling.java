package helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateReportAfterCrawling {

	static ReadTestReport report = new ReadTestReport();

	public static void main(String[] args) {

		// create a scanner so we can read the command-line input
		Scanner scanner = new Scanner(System.in);

		// prompt for the user's name
		System.out.print("Enter your folder where files are present - exampleformat as  /Users/purushi1.macbook/readfiles : ");

		// get their input as a String
		String path = scanner.next();
		
//		 String path =
//		"/Users/purushi1.macbook/eclipse-api/DataStructureProblem/crawler_test_report.com/src/main/resources";

		/*
		 * This is called to crawler each report file and display the output
		 */
		File folder = new File(path);		
		report.listFilesForFolder(folder);

		for (String eachFile : report.filesToRead) {
			report.listOfTestCaseExecutedStatus = new ArrayList<>();
			System.out.println(eachFile);
			// report.readFile(eachFile);
			report.readFile(eachFile);
			FinalReportForEachFileRead(report);
		}

		/*
		 * This is called to crawler each report file and display the output in aggregated format 
		 */
		report = new ReadTestReport();
		report.listFilesForFolder(folder);

		report.listOfTestCaseExecutedStatus = new ArrayList<>();
		for (String eachFile : report.filesToRead) {
			report.readFile(eachFile);
		}

		FinalReportForEachFileReadAggregated(report);

	}

	/*
	 * Display the output in console in table after the program reads each report
	 */
	private static void FinalReportForEachFileRead(ReadTestReport report) {
		TableGenerator tableGenerator = new TableGenerator();

		List<String> headersList = new ArrayList<>();
		headersList.add("TestCaseName");
		headersList.add("Status");
		headersList.add("Time");
		headersList.add("Comments");

		List<List<String>> rowsList = new ArrayList<>();

		for (int i = 0; i < report.listOfTestCaseExecutedStatus.size(); i++) {
			List<String> row = new ArrayList<>();

			row.add(report.listOfTestCaseExecutedStatus.get(i).getRunTestCaseName().toString());
			row.add(report.listOfTestCaseExecutedStatus.get(i).getStatusOfTestCaseExecuted().toString());
			row.add(report.listOfTestCaseExecutedStatus.get(i).getTestCaseExecutedTime().toString());
			row.add(report.listOfTestCaseExecutedStatus.get(i).getComments().toString());

			rowsList.add(row);
		}
		System.out.println("\n");

		System.out.println(report.FeatureName + " " + ":" + " " + report.eachTestCaseStatus);

		System.out.println(tableGenerator.generateTable(headersList, rowsList));
	}

	/*
	 * Display the output in console in table as consolidated report after the program reads each report 
	 */
	private static void FinalReportForEachFileReadAggregated(ReadTestReport report) {
		TableGenerator tableGenerator = new TableGenerator();

		List<String> headersList = new ArrayList<>();
		headersList.add("TestCaseName");
		headersList.add("Status");
		headersList.add("Time");
		headersList.add("Comments");

		List<List<String>> rowsList = new ArrayList<>();

		for (int i = 0; i < report.listOfTestCaseExecutedStatus.size(); i++) {
			List<String> row = new ArrayList<>();

			row.add(report.listOfTestCaseExecutedStatus.get(i).getRunTestCaseName().toString());
			row.add(report.listOfTestCaseExecutedStatus.get(i).getStatusOfTestCaseExecuted().toString());
			row.add(report.listOfTestCaseExecutedStatus.get(i).getTestCaseExecutedTime().toString());
			row.add(report.listOfTestCaseExecutedStatus.get(i).getComments().toString());

			rowsList.add(row);
		}
		System.out.println("\n");
		System.out.println("*** The Complete Test Summary Report ***");
		System.out.println("Total Test case Executed" + " " + ":" + " " + report.totalTestCase);
		System.out.println("Total Passed Test case" + " " + ":" + " " + report.totalPassedTestCase);
		System.out.println("Total Failed Test case" + " " + ":" + " " + report.totalFailedTestCase);
		System.out.println("Total Sipped Test case" + " " + ":" + " " + report.totalSkippedTestCase);

		System.out.println(tableGenerator.generateTable(headersList, rowsList));
	}

}
