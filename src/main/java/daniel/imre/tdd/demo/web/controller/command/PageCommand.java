package daniel.imre.tdd.demo.web.controller.command;

import daniel.imre.tdd.demo.web.data.PageContext;
import daniel.imre.tdd.demo.web.data.PageModel;

/**
 * A unit of work to be executed based on a page context.
 * The result of
 *
 * @author Daniel_Imre
 *
 */
public interface PageCommand {

    /**
     * Executes the command.
     * @param context the page context
     * @param model the page model
     */
    void execute(PageContext context, PageModel model);
}
