package daniel.imre.tdd.demo.web.controller.command;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import daniel.imre.tdd.demo.web.data.PageContext;
import daniel.imre.tdd.demo.web.data.PageModel;

/**
 * Populates author information.
 *
 * @author Daniel_Imre
 *
 */
public class AuthorPropertiesPopulatorCommand implements PageCommand {
    private String resourcePattern;

    @Override
    public void execute(PageContext context, PageModel model) {
        try {
            Properties properties = new Properties();
            InputStream in = getClass().getResourceAsStream(getPropertyPath(context));
            properties.load(in);
            model.setAuthorName(properties.getProperty(context.getLocale().toString()));
        } catch (IOException ex) {
            model.setAuthorName(null);
        }
    }

    private String getPropertyPath(PageContext context) {
        return MessageFormat.format(resourcePattern, "author_name", context.getType());
    }

    public void setResourcePattern(String resourcePattern) {
        this.resourcePattern = resourcePattern;
    }

}
