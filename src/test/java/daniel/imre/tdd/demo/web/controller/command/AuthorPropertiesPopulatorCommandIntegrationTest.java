package daniel.imre.tdd.demo.web.controller.command;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

import daniel.imre.tdd.demo.service.LocalizedDataProvider;
import daniel.imre.tdd.demo.service.PropertyFileBasedLocalizedDataProvider;
import daniel.imre.tdd.demo.web.data.PageContext;
import daniel.imre.tdd.demo.web.data.PageModel;

/**
 * Integration test for {@link AuthorPropertiesPopulatorCommand}.
 *
 * @author Daniel_Imre
 *
 */
public class AuthorPropertiesPopulatorCommandIntegrationTest {
    private static final String AUTHOR_NAME = "Homepage author";
    private static final Locale LOCALE = Locale.UK;
    private static final String PAGE_TYPE = "homepage";
    private static final String AUTHOR_DESCRIPTION = "English text for homepage goes here";

    @Test
    public void shouldPopulateAuthorFieldsFromPropertyFiles() {
        AuthorPropertiesPopulatorCommand command = createCommand();
        PageContext context = aContextWith(LOCALE, PAGE_TYPE);
        PageModel model = new PageModel();
        command.execute(context, model);
        assertEquals(AUTHOR_NAME, model.getAuthorName());
        assertEquals(AUTHOR_DESCRIPTION, model.getAuthorDescription());
    }

    private AuthorPropertiesPopulatorCommand createCommand() {
        AuthorPropertiesPopulatorCommand command = new AuthorPropertiesPopulatorCommand();
        command.setLocalizedDataProvider(createLocalizedDataProvider());
        return command;
    }

    private LocalizedDataProvider createLocalizedDataProvider() {
        PropertyFileBasedLocalizedDataProvider provider = new PropertyFileBasedLocalizedDataProvider();
        provider.setResourcePattern("/author/data_{0}_{1}.properties");
        return provider;
    }

    private PageContext aContextWith(Locale locale, String pageType) {
        PageContext context = new PageContext();
        context.setLocale(locale);
        context.setType(pageType);
        return context;
    }
}
