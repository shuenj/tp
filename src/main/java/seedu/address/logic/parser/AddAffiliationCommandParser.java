package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AFFILIATION;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAffiliationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.affiliation.Affiliation;

/**
 * Parses input arguments and creates a new AddAffiliationCommand object
 */
public class AddAffiliationCommandParser implements Parser<AddAffiliationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAffiliationCommand
     * and returns an AddAffiliationCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAffiliationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_AFFILIATION);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAffiliationCommand.MESSAGE_USAGE), pe);
        }

        Set<Affiliation> affiliationListToAdd = ParserUtil
                .parseAffiliations(argMultimap.getAllValues(PREFIX_AFFILIATION));
        if (affiliationListToAdd.isEmpty()) {
            throw new ParseException(AddAffiliationCommand.MESSAGE_NO_AFFILIATION_INPUT);
        }

        return new AddAffiliationCommand(index, affiliationListToAdd);
    }
}
