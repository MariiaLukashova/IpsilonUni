package config;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class ScreenListener implements ITestListener{

    private void screenMake(ITestResult result) {
        Object instance = result.getInstance();
        if (instance == null || !(instance instanceof BaseTest)) return;
        WebDriver driver = ((BaseTest) instance).getDriver();
        if (driver == null) return;
        Screenshot.makeScreen(driver, "AutoScreen");
    }

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onFinish(ITestContext context) {
    }

    public void onStart(ITestContext context) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        screenMake(result);
    }

    public void onTestFailure(ITestResult result) {
        screenMake(result);
    }

    public void onTestSkipped(ITestResult result) {
        screenMake(result);
    }
}
