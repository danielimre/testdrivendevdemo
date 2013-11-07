package daniel.imre.tdd.demo.service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Provides localized data from property files.
 * @author Daniel_Imre
 *
 */
public class PropertyFileBasedLocalizedDataProvider implements LocalizedDataProvider {
    private String resourcePattern;
    private PropertiesProvider propertiesProvider;

    @Override
    public String getLocalizedData(String pageType, LocalizedDataType dataType, Locale locale) {
        return (String) getProperties(pageType, dataType).get(locale.toString());
    }

    private Map<Object, Object> getProperties(String pageType, LocalizedDataType dataType) {
        return propertiesProvider.getProperties(getPropertyPath(pageType, dataType));
    }

    private String getPropertyPath(String pageType, LocalizedDataType dataType) {
        return MessageFormat.format(resourcePattern, dataType.name().toLowerCase(), pageType);
    }

    public void setResourcePattern(String resourcePattern) {
        this.resourcePattern = resourcePattern;
    }

    public void setPropertiesProvider(PropertiesProvider propertiesProvider) {
        this.propertiesProvider = propertiesProvider;
    }
}
