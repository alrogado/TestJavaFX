package org.testjavafx.conf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alrogado on 6/20/17.
 */
public class ApplicationSettings extends Settings{

    private static Logger logger = LoggerFactory.getLogger(ApplicationSettings.class);

    // Version and copyright information
    static final String APPLICATION_NAME = "Test JavaFX LS";
    static final String COPYRIGHT = "@Test JavaFX LS 2017";
    static final String VERSION = "V1.0";

    public static Locale LOCALE = new Locale("es");
    public static ResourceBundle APPBUNDLE = ResourceBundle.getBundle("testjfx", LOCALE);

    public static int WIDTH = 1100;
    public static int HEIGHT = 800;

    public String fileName = "applicationSettings.conf";

    static ApplicationSettings instance = new ApplicationSettings().loadConf(ApplicationSettings.class);

    @JsonProperty private long startupDelay;

    public static String getBundleString(String rscBundle) {
        String res = rscBundle;
        try {
            res = APPBUNDLE.getString(rscBundle);
        } catch (Exception e) {
            logger.warn("Error loading resources ", e);
        }
        return res;
    }

    @JsonIgnore private LongProperty startupDelayProperty = new SimpleLongProperty(startupDelay);

    {
        startupDelayProperty.addListener(changeListener);

    }

    public static ApplicationSettings getInstance() {
        return instance;
    }



    public long getStartupDelay() {
        return startupDelayProperty.get();
    }

    public void setStartupDelay(long startupDelay) {
        this.startupDelayProperty.set(startupDelay);
    }


    @Override
    public String getFileName() {
        return fileName;
    }
    
    
    
    public static void main(String args[]) throws IOException {
        System.out.println("setStartupDelay:" + ApplicationSettings.getInstance().getStartupDelayProperty());
        ApplicationSettings.getInstance().setStartupDelay(58);
    }

    public long getStartupDelayProperty() {
        return startupDelayProperty.get();
    }

    public LongProperty startupDelayProperty() {
        return startupDelayProperty;
    }


}
