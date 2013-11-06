package daniel.imre.tdd.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link PropertyFileBasedLocalizedDataProvider}.
 *
 * @author Daniel_Imre
 *
 */
public class PropertyFileBasedLocalizedDataProviderTest {
    private static final String AUTHOR_NAME = "Homepage author";
    private static final Locale LOCALE = Locale.UK;
    private static final String PAGE_TYPE = "homepage";
    private static final Locale MISSING_LOCALE = Locale.GERMAN;
    private static final Locale ANY_LOCALE = null;
    private static final String MISSING_PAGE_TYPE = "missingpage";
    private PropertyFileBasedLocalizedDataProvider provider = new PropertyFileBasedLocalizedDataProvider();

    @Before
    public void initContext() {
        provider.setResourcePattern("/author/data_{0}_{1}.properties");
    }

    @Test
    public void shouldReturnLocalizedDataBasedOnPageAndDataTypePerLocale() {
        String result = provider.getLocalizedData(PAGE_TYPE, LocalizedDataType.AUTHOR_NAME, LOCALE);
        assertEquals(AUTHOR_NAME, result);
    }

    @Test
    public void shouldReturnNullIfLocalizedDataIsNotFoundForLocale() {
        String result = provider.getLocalizedData(PAGE_TYPE, LocalizedDataType.AUTHOR_NAME, MISSING_LOCALE);
        assertNull(result);
    }

    @Test
    public void shouldReturnNullIfPropertyFileIsNotFound() {
        String result = provider.getLocalizedData(MISSING_PAGE_TYPE, LocalizedDataType.AUTHOR_NAME, ANY_LOCALE);
        assertNull(result);
    }

    @Test
    public void shouldClosePropertyFileAfterReadingIt() {

    }

    @Test
    public void shouldClosePropertyFileEvenIfErrorHappens() {

    }

    @Test
    public void shouldCachePropertyFileContent() {

    }
}

