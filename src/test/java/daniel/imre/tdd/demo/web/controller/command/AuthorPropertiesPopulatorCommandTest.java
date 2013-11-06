package daniel.imre.tdd.demo.web.controller.command;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import daniel.imre.tdd.demo.web.data.PageContext;
import daniel.imre.tdd.demo.web.data.PageModel;

/**
 * Unit test for {@link AuthorPropertiesPopulatorCommand}.
 * <p>
 * The author name and description should come from separate files per page type and field (for sake of complexity):
 * <br>
 * {@code data_[field name]_[page type].properties}.
 * <br>
 * e.g.: {@code data_author_name_homepage.properties}
 * <p>
 * Each file contains multiple lines, one per locale.
 * <br>
 * e.g.: {@code en_GB=John}
 *
 * @author Daniel_Imre
 *
 */
public class AuthorPropertiesPopulatorCommandTest {
    private static final String AUTHOR_NAME = "Homepage author";
    private static final Locale LOCALE = Locale.UK;
    private static final String PAGE_TYPE = "homepage";
    private AuthorPropertiesPopulatorCommand command = new AuthorPropertiesPopulatorCommand();

    @Before
    public void initContext() {
        command.setResourcePattern("/author/data_{0}_{1}.properties");
    }

    @Test
    public void shouldPopulateAuthorNameFromResourceBasedOnLocaleAndType() {
        PageContext context = aContextWith(LOCALE, PAGE_TYPE);
        PageModel model = new PageModel();
        command.execute(context, model);
        assertEquals(AUTHOR_NAME, model.getAuthorName());
    }

    @Test
    public void shouldPopulateAuthorDescriptionFromResourceBasedOnLocaleAndType() {

    }

    private PageContext aContextWith(Locale locale, String pageType) {
        PageContext context = new PageContext();
        context.setLocale(locale);
        context.setType(pageType);
        return context;
    }

}
