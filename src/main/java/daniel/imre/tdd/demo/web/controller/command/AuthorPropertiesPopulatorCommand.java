package daniel.imre.tdd.demo.web.controller.command;

import daniel.imre.tdd.demo.service.LocalizedDataProvider;
import daniel.imre.tdd.demo.service.LocalizedDataType;
import daniel.imre.tdd.demo.web.data.PageContext;
import daniel.imre.tdd.demo.web.data.PageModel;

/**
 * Populates author information.
 *
 * @author Daniel_Imre
 *
 */
public class AuthorPropertiesPopulatorCommand implements PageCommand {
    private LocalizedDataProvider localizedDataProvider;

    @Override
    public void execute(PageContext context, PageModel model) {
        model.setAuthorName(getLocalizedData(context, LocalizedDataType.AUTHOR_NAME));
        model.setAuthorDescription(getLocalizedData(context, LocalizedDataType.AUTHOR_DESCRIPTION));
    }

    private String getLocalizedData(PageContext context, LocalizedDataType dataType) {
        return localizedDataProvider.getLocalizedData(context.getType(), dataType, context.getLocale());
    }

    public void setLocalizedDataProvider(LocalizedDataProvider localizedDataProvider) {
        this.localizedDataProvider = localizedDataProvider;
    }

}
