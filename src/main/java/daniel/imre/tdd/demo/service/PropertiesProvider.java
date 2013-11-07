package daniel.imre.tdd.demo.service;

import java.util.Map;

/**
 * Provides map of key values.
 *
 * @author Daniel_Imre
 *
 */
public interface PropertiesProvider {

    /**
     * Gets map from a path.
     * @param path the path
     * @return the map, never null
     */
    Map<Object, Object> getProperties(String path);
}
