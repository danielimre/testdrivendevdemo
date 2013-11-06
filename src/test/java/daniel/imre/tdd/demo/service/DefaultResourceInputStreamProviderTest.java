package daniel.imre.tdd.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Unit test for {@link DefaultResourceInputStreamProvider}.
 *
 * @author Daniel_Imre
 *
 */
public class DefaultResourceInputStreamProviderTest {
    private DefaultResourceInputStreamProvider provider = new DefaultResourceInputStreamProvider();

    @Test
    public void shouldReturnReaderIfResourceIsAvailable() {
        assertNotNull(provider.getInputStream("/author/data_author_name_homepage.properties"));
    }

    @Test
    public void shouldReturnNullIfResourceIsNotAvailable() {
        assertNull(provider.getInputStream("/author/missing.properties"));
    }
}
