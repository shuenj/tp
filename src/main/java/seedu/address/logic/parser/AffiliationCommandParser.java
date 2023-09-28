package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AffiliationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AffiliationCommand object
 */
public class AffiliationCommandParser implements Parser<AffiliationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AffiliationCommand
     * and returns a AffiliationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AffiliationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AffiliationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AffiliationCommand.MESSAGE_USAGE), pe);
        }
    }

}
