package testCases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestCase implements IRetryAnalyzer
{

  private int cnt = 0;
  //You could mentioned maxRetryCnt (Maximiun Retry Count) as per your requirement. Here I took 2, If any failed testcases then it will run two times
  private int maxRetryCnt = 2;

  //retry function retry execution of failed test case 
	@Override
	public boolean retry(ITestResult result) {

		if (cnt < maxRetryCnt)
        {
              System.out.println("Retrying failed " + result.getName() + " again and the count is " + (cnt+1));
              cnt++;
              return true;
        }
		return false;
	}

}
