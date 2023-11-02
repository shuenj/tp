package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AFFILIATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.AffiliationContainsKeywordsPredicate;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.Role;
import seedu.address.model.person.RoleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean onlyOnePrefix(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getPrefixNum() == 1;
    }

    /**
     * Converts the prefix mapped value from an ArgumentMultimap into a list of String keywords.
     */
    private static String[] getKeywords(ArgumentMultimap argumentMultimap, Prefix prefix) throws ParseException {
        String trimmedArgs = argumentMultimap.getValue(prefix).get();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");
        return keywords;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE, PREFIX_AFFILIATION);

        // check that preamble is empty
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!onlyOnePrefix(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Predicate<Person> predicate;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] keywords = getKeywords(argMultimap, PREFIX_NAME);
            if (!Name.isValidName(keywords)) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            predicate = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] keywords = getKeywords(argMultimap, PREFIX_PHONE);
            if (!Phone.isValidPhone(keywords)) {
                throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
            }
            predicate = new PhoneContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] keywords = getKeywords(argMultimap, PREFIX_EMAIL);
            if (!Email.isValidEmail(keywords)) {
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            }
            predicate = new EmailContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            String[] keywords = getKeywords(argMultimap, PREFIX_ROLE);
            if (!Role.isValidRole(keywords)) {
                throw new ParseException(Role.MESSAGE_CONSTRAINTS);
            }
            predicate = new RoleContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_AFFILIATION).isPresent()) {
            String[] keywords = getKeywords(argMultimap, PREFIX_AFFILIATION);
            if (!Affiliation.isValidAffiliationName(keywords)) {
                throw new ParseException(Affiliation.MESSAGE_CONSTRAINTS);
            }
            predicate = new AffiliationContainsKeywordsPredicate(Arrays.asList(keywords));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(predicate);
    }

}
