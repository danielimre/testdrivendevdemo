package daniel.imre.tdd.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link CachingPropertiesProvider}.
 *
 * @author Daniel_Imre
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CachingPropertiesProviderTest {
    private static final Map<Object, Object> DELEGATED_RESULT = Collections.<Object, Object>singletonMap("some", "value");
    private static final String A_PATH = "a/path";
    private CachingPropertiesProvider cachingProvider = new CachingPropertiesProvider();
    @Mock
    private PropertiesProvider provider;

    @Before
    public void initContext() {
        cachingProvider.setProvider(provider);
    }

    @Test
    public void shouldDelegateResolutionToProvider() {
        when(provider.getProperties(A_PATH)).thenReturn(DELEGATED_RESULT);
        Map<Object, Object> result = cachingProvider.getProperties(A_PATH);
        assertEquals(DELEGATED_RESULT, result);
    }

    @Test
    public void shouldCachePropertyFileContent() {
        when(provider.getProperties(A_PATH)).thenReturn(DELEGATED_RESULT);
        cachingProvider.getProperties(A_PATH);
        cachingProvider.getProperties(A_PATH);
        verify(provider, times(1)).getProperties(A_PATH);
    }
}
