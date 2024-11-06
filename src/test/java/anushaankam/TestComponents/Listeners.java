package anushaankam.TestComponents;
  
  import java.io.IOException;
  
  import org.openqa.selenium.WebDriver; 
  import org.testng.ITestContext; 
  import org.testng.ITestListener; 
  import org.testng.ITestResult;
  
  import com.aventstack.extentreports.ExtentReports; 
  import com.aventstack.extentreports.ExtentTest; 
  import com.aventstack.extentreports.Status;
  
  import anushaankam.resources.ExtentReportsNG;
  
  public class Listeners extends BaseTest implements ITestListener 
  {
  
  ExtentReports extent = ExtentReportsNG.getReportObject(); 
  ExtentTest test;
  ThreadLocal<ExtentTest> extentTest =  new ThreadLocal<ExtentTest>(); //Thread Safe
  
  @Override 
  public void onTestStart(ITestResult result) 
  { 
	  test = extent.createTest(result.getMethod().getMethodName());
	  extentTest.set(test); //unique ID
  }
  
  @Override 
  public void onTestSuccess(ITestResult result) 
  {
  extentTest.get().log(Status.PASS, "Test PASSED"); 
  }
  
  @Override 
  public void onTestFailure(ITestResult result) 
  {
  extentTest.get().fail(result.getThrowable()); 
  //Screenshot, Attach to report
  
  try 
  {
	driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
			  .get(result.getInstance());
  } 
  catch (Exception e1) 
  {
	// TODO Auto-generated catch block
	e1.printStackTrace();
  }
  String filePath=null;
  try 
  {
	filePath = getScreenshot(result.getMethod().getMethodName(),driver);
  } 
  catch (IOException e)
  {
	// TODO Auto-generated catch block
	e.printStackTrace();
  }
  extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
  }
	 
  
  @Override 
  public void onFinish(ITestContext context) 
  { 
	  extent.flush(); 
  }
  }
 