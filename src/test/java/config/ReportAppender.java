package config;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class ReportAppender extends AppenderSkeleton{
    protected void append(LoggingEvent loggingEvent) {
        String msg = this.layout.format(loggingEvent).replaceAll("\n", "\n");
        Reporter.log(msg);
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return true;
    }
}
