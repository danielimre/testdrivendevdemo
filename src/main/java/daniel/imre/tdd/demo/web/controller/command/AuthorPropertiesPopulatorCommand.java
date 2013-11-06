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
        model.setAuthorName(getDataForType(context, "author_name"));
        model.setAuthorDescription(getDataForType(context, "author_description"));
    }

    private String getDataForType(PageContext context, String dataType) {
        String localizedData;
        try {
            Properties properties = new Properties();
            InputStream in = getClass().getResourceAsStream(getPropertyPath(context, dataType));
            properties.load(in);
            localizedData = properties.getProperty(context.getLocale().toString());
        } catch (IOException ex) {
            //some logging should go here
            localizedData = null;
        }
        return localizedData;
    }

    private String getPropertyPath(PageContext context, String dataType) {
        return MessageFormat.format(resourcePattern, dataType, context.getType());
    }

    public void setResourcePattern(String resourcePattern) {
        this.resourcePattern = resourcePattern;
    }

}
