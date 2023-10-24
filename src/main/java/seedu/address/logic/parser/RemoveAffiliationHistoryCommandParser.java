package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveAffiliationHistoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearAffiliationHistoryCommand object
 */
public class RemoveAffiliationHistoryCommandParser implements Parser<RemoveAffiliationHistoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearAffiliationHistoryCommand
     * and returns a ClearAffiliationHistoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveAffiliationHistoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RemoveAffiliationHistoryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveAffiliationHistoryCommand.MESSAGE_USAGE), pe);
        }
    }
}
