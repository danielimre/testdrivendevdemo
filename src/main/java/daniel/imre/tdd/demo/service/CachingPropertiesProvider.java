package daniel.imre.tdd.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Delegates to other {@link PropertiesProvider} and caches results.
 *
 * @author Daniel_Imre
 *
 */
public class CachingPropertiesProvider implements PropertiesProvider {
    private PropertiesProvider provider;
    private Map<String, Map<Object, Object>> cache = new ConcurrentHashMap<String, Map<Object, Object>>();

    @Override
    public Map<Object, Object> getProperties(String path) {
        String cacheKey = createCacheKey(path);
        Map<Object, Object> result = cache.get(cacheKey);
        if (result == null) {
            result = provider.getProperties(path);
            cache.put(cacheKey, result);
        }
        return result;
    }

    private String createCacheKey(String path) {
        return path;
    }

    public void setProvider(PropertiesProvider provider) {
        this.provider = provider;
    }

}
