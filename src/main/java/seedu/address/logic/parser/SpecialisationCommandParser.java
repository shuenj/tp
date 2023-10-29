package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SpecialisationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Specialisation;

public class SpecialisationCommandParser implements Parser<SpecialisationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SpecialisationCommand
     * and returns a SpecialisationCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SpecialisationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            String[] splitArgs = args.trim().split(" ");
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            // After parsing the index, the remaining string should be the specialisations
            int specialisationStart = args.indexOf(splitArgs[1]);
            String specialisationString = args.substring(specialisationStart);
            Set<Specialisation> specialisations = ParserUtil.parseSpecialisations(specialisationString);
            return new SpecialisationCommand(index, specialisations);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SpecialisationCommand.MESSAGE_USAGE), pe);
        }
    }
}
