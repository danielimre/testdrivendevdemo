package daniel.imre.tdd.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link PropertyFileBasedLocalizedDataProvider}.
 *
 * @author Daniel_Imre
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PropertyFileBasedLocalizedDataProviderTest {
    private static final LocalizedDataType DATA_TYPE = LocalizedDataType.AUTHOR_NAME;
    private static final String LOCALIZED_DATA = "Homepage author";
    private static final Locale LOCALE = Locale.UK;
    private static final String PAGE_TYPE = "homepage";
    private static final Locale MISSING_LOCALE = Locale.GERMAN;
    private PropertyFileBasedLocalizedDataProvider provider = new PropertyFileBasedLocalizedDataProvider();
    @Mock
    private PropertiesProvider propertiesProvider;

    @Before
    public void initContext() {
        provider.setResourcePattern("/author/data_{0}_{1}.properties");
        provider.setPropertiesProvider(propertiesProvider);
    }

    @Test
    public void shouldReturnLocalizedDataBasedOnPageAndDataTypePerLocale() {
        expectPropertiesForPageAndDataType();
        String result = provider.getLocalizedData(PAGE_TYPE, DATA_TYPE, LOCALE);
        assertEquals(LOCALIZED_DATA, result);
    }

    @Test
    public void shouldReturnNullIfLocalizedDataIsNotFoundForLocale() {
        expectPropertiesForPageAndDataType();
        String result = provider.getLocalizedData(PAGE_TYPE, DATA_TYPE, MISSING_LOCALE);
        assertNull(result);
    }

    private void expectPropertiesForPageAndDataType() {
        when(propertiesProvider.getProperties("/author/data_author_name_homepage.properties")).thenReturn(
                Collections.<Object, Object>singletonMap(LOCALE.toString(), LOCALIZED_DATA));
    }
}
