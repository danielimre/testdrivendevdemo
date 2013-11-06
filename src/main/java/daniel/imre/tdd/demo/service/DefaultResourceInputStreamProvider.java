package daniel.imre.tdd.demo.service;

import java.io.InputStream;


/**
 * Resource input stream provider using class loader to get resources.
 *
 * @author Daniel_Imre
 *
 */
public class DefaultResourceInputStreamProvider implements ResourceInputStreamProvider {

    @Override
    public InputStream getInputStream(String resourcePath) {
        return getClass().getResourceAsStream(resourcePath);
    }

}
