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
    private ResourceInputStreamProvider resourceInputStreamProvider = new DefaultResourceInputStreamProvider();
    private String resourcePattern;

    @Override
    public String getLocalizedData(String pageType, LocalizedDataType dataType, Locale locale) {
        String localizedData;
        try {
            InputStream in = resourceInputStreamProvider.getInputStream(getPropertyPath(pageType, dataType));
            if (in != null) {
                Properties properties = new Properties();
                try {
                    properties.load(in);
                } finally {
                    in.close();
                }
                localizedData = properties.getProperty(locale.toString());
            } else {
                localizedData = null;
            }
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

    public void setResourceInputStreamProvider(ResourceInputStreamProvider resourceInputStreamProvider) {
        this.resourceInputStreamProvider = resourceInputStreamProvider;
    }
}
