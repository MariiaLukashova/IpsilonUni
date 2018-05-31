package config;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.kohsuke.args4j.Option;


/**
 * Settings
 */
public class Settings {

    @Option(name = "--testngXML", usage = "path to testng xml", required = true, aliases = {"-tngx"})
    public String testngXML;

    public static Settings testSetting = new Settings();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
