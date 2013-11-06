package daniel.imre.tdd.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * Provides localized data from property files.
 * @author Daniel_Imre
 *
 */
public class PropertyFileBasedLocalizedDataProvider implements LocalizedDataProvider {
    private String resourcePattern;

    @Override
    public String getLocalizedData(String pageType, LocalizedDataType dataType, Locale locale) {
        String localizedData;
        try {
            Properties properties = new Properties();
            InputStream in = getClass().getResourceAsStream(getPropertyPath(pageType, dataType));
            properties.load(in);
            localizedData = properties.getProperty(locale.toString());
        } catch (IOException ex) {
            //some logging should go here
            localizedData = null;
        }
        return localizedData;
    }

    private String getPropertyPath(String pageType, LocalizedDataType dataType) {
        return MessageFormat.format(resourcePattern, dataType.name().toLowerCase(), pageType);
    }

    public void setResourcePattern(String resourcePattern) {
        this.resourcePattern = resourcePattern;
    }
}
