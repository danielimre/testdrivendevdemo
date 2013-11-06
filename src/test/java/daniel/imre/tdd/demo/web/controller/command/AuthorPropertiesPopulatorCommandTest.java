package daniel.imre.tdd.demo.web.controller.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import daniel.imre.tdd.demo.service.LocalizedDataProvider;
import daniel.imre.tdd.demo.service.LocalizedDataType;
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
@RunWith(MockitoJUnitRunner.class)
public class AuthorPropertiesPopulatorCommandTest {
    private static final String AUTHOR_NAME = "Homepage author";
    private static final Locale LOCALE = Locale.UK;
    private static final String PAGE_TYPE = "homepage";
    private static final String AUTHOR_DESCRIPTION = "English text for homepage goes here";
    private static final Locale MISSING_LOCALE = Locale.GERMAN;
    private AuthorPropertiesPopulatorCommand command = new AuthorPropertiesPopulatorCommand();
    @Mock
    private LocalizedDataProvider localizedDataProvider;

    @Before
    public void initContext() {
        command.setLocalizedDataProvider(localizedDataProvider);
    }

    @Test
    public void shouldPopulateAuthorNameBasedOnLocaleAndType() {
        when(localizedDataProvider.getLocalizedData(PAGE_TYPE, LocalizedDataType.AUTHOR_NAME, LOCALE)).thenReturn(AUTHOR_NAME);
        PageContext context = aContextWith(LOCALE, PAGE_TYPE);
        PageModel model = new PageModel();
        command.execute(context, model);
        assertEquals(AUTHOR_NAME, model.getAuthorName());
    }

    @Test
    public void shouldPopulateAuthorDescriptionBasedOnLocaleAndType() {
        when(localizedDataProvider.getLocalizedData(PAGE_TYPE, LocalizedDataType.AUTHOR_DESCRIPTION, LOCALE)).thenReturn(AUTHOR_DESCRIPTION);
        PageContext context = aContextWith(LOCALE, PAGE_TYPE);
        PageModel model = new PageModel();
        command.execute(context, model);
        assertEquals(AUTHOR_DESCRIPTION, model.getAuthorDescription());
    }

    @Test
    public void shouldSkipFieldIfLocalizedDataNotFound() {
        PageContext context = aContextWith(MISSING_LOCALE, PAGE_TYPE);
        PageModel model = new PageModel();
        command.execute(context, model);
        assertNull(model.getAuthorName());
        assertNull(model.getAuthorDescription());
    }

    private PageContext aContextWith(Locale locale, String pageType) {
        PageContext context = new PageContext();
        context.setLocale(locale);
        context.setType(pageType);
        return context;
    }

}
