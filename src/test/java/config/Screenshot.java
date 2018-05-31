package config;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;


public class Screenshot {

    private static final String REPORTPATH = "test-output/html/";
    private static final String SCREENFOLDER = "screenshots";
    private static final String DEFUUSLT_MSG = "screenshot";

    private final static Logger LOG = Logger.getLogger(Screenshot.class);

    public static void deleteScreens(){
        File directory = new File(REPORTPATH + SCREENFOLDER);
        LOG.info("delete files from: " + directory.getAbsolutePath());
        File[] files = directory.listFiles();
        if (files != null && files.length > 0){
            for (File file : files){
                if (!file.delete()){
                    LOG.info("connot delete file: " + file);
                }
            }
        }
    }

    public static void makeScreen(WebDriver driver){
        makeScreen(driver, DEFUUSLT_MSG);
    }

    public static void makeScreen(WebDriver driver, String message){
        if (driver == null) return;
        try{
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFileToDirectory(screenshot, new File(REPORTPATH + SCREENFOLDER));
            String logMsg = "<a href='" + SCREENFOLDER + "/" + screenshot.getName() + "'>" + message + "</a>";
            LOG.info(logMsg);
        }
        catch (Exception ex){
            LOG.error("makeScreen error: " + ex);
            throw new RuntimeException(ex);
        }
    }
}
