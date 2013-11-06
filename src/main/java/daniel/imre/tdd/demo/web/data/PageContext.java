package daniel.imre.tdd.demo.web.data;

import java.util.Locale;

/**
 * Holds context information for the page.
 * @author Daniel_Imre
 *
 */
public class PageContext {
    private Locale locale;
    private String type;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
