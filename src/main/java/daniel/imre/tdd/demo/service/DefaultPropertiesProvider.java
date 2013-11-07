package daniel.imre.tdd.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Provides map of key values from a properties file.
 *
 * @author Daniel_Imre
 *
 */
public class DefaultPropertiesProvider implements PropertiesProvider {
    private ResourceInputStreamProvider resourceInputStreamProvider = new DefaultResourceInputStreamProvider();

    @Override
    public Map<Object, Object> getProperties(String path) {
        Map<Object, Object> result = null;
        try {
            InputStream in = resourceInputStreamProvider.getInputStream(path);
            if (in != null) {
                Properties properties = new Properties();
                try {
                    properties.load(in);
                    result = copyProperties(properties);
                } finally {
                    in.close();
                }
            }
        } catch (IOException ex) {
            //some logging should go here
            result = null;
        }
        if (result == null) {
            result = Collections.emptyMap();
        }
        return result;
    }

    private Map<Object, Object> copyProperties(Properties properties) {
        Map<Object, Object> propertiesCopy = new HashMap<>(properties);
        return Collections.unmodifiableMap(propertiesCopy);
    }

    public void setResourceInputStreamProvider(ResourceInputStreamProvider resourceInputStreamProvider) {
        this.resourceInputStreamProvider = resourceInputStreamProvider;
    }
}
