package daniel.imre.tdd.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
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
    private static final String AUTHOR_NAME = "Homepage author";
    private static final Locale LOCALE = Locale.UK;
    private static final String PAGE_TYPE = "homepage";
    private static final Locale MISSING_LOCALE = Locale.GERMAN;
    private static final Locale ANY_LOCALE = null;
    private static final String MISSING_PAGE_TYPE = "missingpage";
    private PropertyFileBasedLocalizedDataProvider provider = new PropertyFileBasedLocalizedDataProvider();
    @Mock
    private ResourceInputStreamProvider inputStreamProvider;

    @Before
    public void initContext() {
        provider.setResourcePattern("/author/data_{0}_{1}.properties");
    }

    @Test
    public void shouldReturnLocalizedDataBasedOnPageAndDataTypePerLocale() {
        String result = provider.getLocalizedData(PAGE_TYPE, DATA_TYPE, LOCALE);
        assertEquals(AUTHOR_NAME, result);
    }

    @Test
    public void shouldReturnNullIfLocalizedDataIsNotFoundForLocale() {
        String result = provider.getLocalizedData(PAGE_TYPE, DATA_TYPE, MISSING_LOCALE);
        assertNull(result);
    }

    @Test
    public void shouldReturnNullIfPropertyFileIsNotFound() {
        String result = provider.getLocalizedData(MISSING_PAGE_TYPE, DATA_TYPE, ANY_LOCALE);
        assertNull(result);
    }

    @Test
    public void shouldClosePropertyFileAfterReadingIt() throws IOException {
        provider.setResourceInputStreamProvider(inputStreamProvider);
        InputStream inputStream = mock(InputStream.class);
        when(inputStreamProvider.getInputStream("/author/data_author_name_homepage.properties")).thenReturn(inputStream);
        provider.getLocalizedData(PAGE_TYPE, DATA_TYPE, LOCALE);
        verify(inputStream).close();
    }

    @Test
    public void shouldClosePropertyFileEvenIfErrorHappens() throws IOException {
        provider.setResourceInputStreamProvider(inputStreamProvider);
        InputStream inputStream = mock(InputStream.class);
        when(inputStreamProvider.getInputStream("/author/data_author_name_homepage.properties")).thenReturn(inputStream);
        when(inputStream.read((byte[]) any())).thenThrow(new IOException());
        provider.getLocalizedData(PAGE_TYPE, DATA_TYPE, LOCALE);
        verify(inputStream).close();
    }

    @Test
    public void shouldCachePropertyFileContent() {

    }
}
