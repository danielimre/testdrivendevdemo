package daniel.imre.tdd.demo.service;

import java.io.InputStream;


/**
 * Provides input streams on resources.
 *
 * @author Daniel_Imre
 *
 */
public interface ResourceInputStreamProvider {

    /**
     * Gets input stream for resource by its path.
     * @param resourcePath the path of the resource
     * @return the input stream or null if not available
     */
    InputStream getInputStream(String resourcePath);
}
