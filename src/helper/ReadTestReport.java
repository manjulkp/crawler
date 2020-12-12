package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadTestReport {

	String FeatureName;
	String eachTestCaseName;
	String eachTestCaseStatus;
	String eachTestCaseExecutionTime;
	List<String> filesToRead = new ArrayList<>();
	StringBuilder comment = new StringBuilder();
	List<TestCaseResult> listOfTestCaseExecutedStatus;
    String fileNameToAppend;
	int totalTestCase;
	int totalPassedTestCase;
	int totalFailedTestCase;
	int totalSkippedTestCase;

	
	/*
	 * List all the files in a folder
	 */
	public void listFilesForFolder(File folder) {
		
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				filesToRead.add(fileEntry.getAbsolutePath());
			}
		}
	}
    
	/*
	 * Read the files 
	 */
	public void readFile(String s) {
		InputStream ins = null; // raw byte-stream
		Reader r = null; // cooked reader
		BufferedReader br = null; // buffered for readLine()

		try {

			ins = new FileInputStream(s);
			r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
			br = new BufferedReader(r);
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				ExtractTestCaseResult(s);
				
			}
		} catch (Exception e) {
			System.err.println(e.getMessage()); // handle exception
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Throwable t) {
					/* ensure close happens */ }
			}
			if (r != null) {
				try {
					r.close();
				} catch (Throwable t) {
					/* ensure close happens */ }
			}
			if (ins != null) {
				try {
					ins.close();
				} catch (Throwable t) {
					/* ensure close happens */ }
			}
		}
	}

	/*
	 * Convert the extracted each line from file  to java object that is later used for displaying data in table format  
	 */
	private void ExtractTestCaseResult(String s) {

		if (s.contains("START")) {
			FeatureName = get_match(s, ".+?(//.*)");
			fileNameToAppend = get_match(s, ".+?:(.*)");

		} else if (s.contains("RUN")) {
			eachTestCaseName = get_match(s, ".+?RUN\\s+([A-Z].+)");
			totalTestCase++;
		} else if (!(s.contains("PASS") || s.contains("FAIL") || s.contains("SKIP:"))) {
			comment.append(s);
		}

		else if (s.contains("PASS:") || s.contains("FAIL:") || s.contains("SKIP:")) {
			eachTestCaseName = get_match(s, ".+?:\\s+([A-Z].+)\\s+\\(.+");
			eachTestCaseExecutionTime = get_match(s, ".+?\\((.+)\\)");
			eachTestCaseStatus = get_match(s, ".+?\\s+([A-Z].+?):.+");

			listOfTestCaseExecutedStatus.add(new TestCaseResult(fileNameToAppend + "." + eachTestCaseName,
					eachTestCaseStatus, eachTestCaseExecutionTime, comment.toString()));
			comment = new StringBuilder();

			//This is used for tracking the count 
			if (eachTestCaseStatus.contains("PASS")) {
				totalPassedTestCase++;

			} else if (eachTestCaseStatus.contains("FAIL")) {
				totalFailedTestCase++;

			} else if (eachTestCaseStatus.contains("SKIP")) {
				totalSkippedTestCase++;

			}

		} else {
			eachTestCaseStatus = s;
		}

	}

	/*
	 * Extract the regrex matching string 
	 */
	private String get_match(String s, String p) {
		// returns first match of p in s for first group in regular expression
		Matcher m = Pattern.compile(p).matcher(s);
		return m.find() ? m.group(1) : "";
	}

}
