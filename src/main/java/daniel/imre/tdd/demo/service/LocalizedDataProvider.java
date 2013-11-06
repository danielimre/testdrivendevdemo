package daniel.imre.tdd.demo.service;

import java.util.Locale;

/**
 * Provides localized data.
 *
 * @author Daniel_Imre
 *
 */
public interface LocalizedDataProvider {

    /**
     * Gets localized data for a given page and data type in a specific locale.
     * @param pageType the type of page
     * @param dataType the type of data
     * @param locale the locale
     * @return the localized data or null if not found
     */
    String getLocalizedData(String pageType, LocalizedDataType dataType, Locale locale);
}
