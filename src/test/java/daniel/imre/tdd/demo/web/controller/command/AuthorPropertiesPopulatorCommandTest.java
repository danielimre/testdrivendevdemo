package daniel.imre.tdd.demo.web.controller.command;

import org.junit.Test;

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

    @Test
    public void shouldPopulateAuthorNameFromResourceBasedOnLocaleAndType() {

    }

    @Test
    public void shouldPopulateAuthorDescriptionFromResourceBasedOnLocaleAndType() {

    }

}
