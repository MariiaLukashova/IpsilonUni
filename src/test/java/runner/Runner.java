package runner;

import config.ScreenListener;
import config.Settings;
import exceptions.TestNgRunException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    TestNG testng = new TestNG();
    public String testngXML;

    public static void main(String[] args) {
        new Runner(args).run();
    }

    private Runner(String[] args) {
        Settings setting = new Settings();
        CmdLineParser cmdLineParser = new CmdLineParser(setting);

        try {
            cmdLineParser.parseArgument(args);
            testngXML = setting.testngXML;

        } catch (CmdLineException ex) {
            System.err.println("error: " + ex.toString());
            cmdLineParser.printUsage(System.out);
        }
    }

    private void run() {
        try {
            XmlSuite xmlSuite = new Parser(testngXML).parseToList().get(0);
            this.testng.setCommandLineSuite(xmlSuite);

            List<Class> listeners = new ArrayList<Class>();
            listeners.add(HTMLReporter.class);
            listeners.add(ScreenListener.class);
            testng.setListenerClasses(listeners);

            this.testng.run();
        } catch (Exception e) {
            throw new TestNgRunException("Error running testNG suite" + e.getMessage());
        }
    }
}