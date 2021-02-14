package utilities;

public class TestConfig{

	//send mail details, values to be passed in MonitoringMail.java class
	
	public static String server="smtp.gmail.com";
	public static String from = "keshava.raheja@gmail.com";
	public static String password = "Keshava@123";
	public static String[] to ={"gauravraheja4u@gmail.com","ruchi.dogra.cdac@gmail.com"};
	public static String subject = "Test Report";
	
	public static String messageBody ="TestMessage from selenium";
	public static String attachmentPath= System.getProperty("user.dir")+ "//test-output//emailable-report.html";
	public static String attachmentName="emailable-testreport";
	
}
