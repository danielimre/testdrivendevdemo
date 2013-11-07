package daniel.imre.tdd.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link DefaultPropertiesProvider}.
 *
 * @author Daniel_Imre
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultPropertiesProviderTest {
    private static final String ANY_PATH = "any/path";
    private DefaultPropertiesProvider provider = new DefaultPropertiesProvider();
    @Mock
    private ResourceInputStreamProvider inputStreamProvider;

    @Before
    public void initContext() {
        provider.setResourceInputStreamProvider(inputStreamProvider);
    }

    @Test
    public void shouldLoadPropertiesFromPath() {
        when(inputStreamProvider.getInputStream(ANY_PATH)).thenReturn(createInputStreamFor("some=value"));
        Map<Object, Object> result = provider.getProperties(ANY_PATH);
        assertEquals(Collections.singletonMap("some", "value"), result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnImmutableResult() {
        when(inputStreamProvider.getInputStream(ANY_PATH)).thenReturn(createInputStreamFor("some=value"));
        Map<Object, Object> result = provider.getProperties(ANY_PATH);
        result.clear();
    }

    @Test
    public void shouldReturnEmptyMapIfResourceNotFound() {
        when(inputStreamProvider.getInputStream(ANY_PATH)).thenReturn(null);
        Map<Object, Object> result = provider.getProperties(ANY_PATH);
        assertEquals(Collections.emptyMap(), result);
    }

    @Test
    public void shouldReturnEmptyMapIfReadingStreamThrowsException() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStreamProvider.getInputStream(ANY_PATH)).thenReturn(inputStream);
        when(inputStream.read((byte[]) any())).thenThrow(new IOException());
        Map<Object, Object> result = provider.getProperties(ANY_PATH);
        assertEquals(Collections.emptyMap(), result);
    }

    @Test
    public void shouldCloseInputStreamAfterReadingIt() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStreamProvider.getInputStream(ANY_PATH)).thenReturn(inputStream);
        provider.getProperties(ANY_PATH);
        verify(inputStream).close();
    }

    @Test
    public void shouldCloseInputStreamEvenIfErrorHappens() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStreamProvider.getInputStream(ANY_PATH)).thenReturn(inputStream);
        when(inputStream.read((byte[]) any())).thenThrow(new IOException());
        provider.getProperties(ANY_PATH);
        verify(inputStream).close();
    }

    private InputStream createInputStreamFor(String string) {
        return new ByteArrayInputStream(string.getBytes());
    }
}
