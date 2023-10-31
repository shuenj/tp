package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NextOfKinCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;

public class NextOfKinCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE);

    private static final NextOfKinCommandParser parser = new NextOfKinCommandParser();

    private static final String VALID_NAME = "n/" + VALID_NAME_AMY;
    private static final String INVALID_NAME = INVALID_NAME_DESC;
    private static final String VALID_PHONE = "p/" + VALID_PHONE_AMY;

    private static final String INVALID_PHONE = INVALID_PHONE_DESC;
    private static final String VALID_RELATIONSHIP = "rs/Spouse";
    private static final String INVALID_RELATIONSHIP = "rs/Spouse*";

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        String userInput = VALID_NAME + " " + VALID_PHONE + " " + VALID_RELATIONSHIP;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        String userInput = "-5 " + VALID_NAME + " " + VALID_PHONE + " " + VALID_RELATIONSHIP;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // zero index
        userInput = "0 " + VALID_NAME + " " + VALID_PHONE + " " + VALID_RELATIONSHIP;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidParameters_failure() {

        // invalid name
        String userInput = "1 " + INVALID_NAME + " " + VALID_PHONE + " " + VALID_RELATIONSHIP;
        assertParseFailure(parser, userInput, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        userInput = "1 " + VALID_NAME + " " + INVALID_PHONE + " " + VALID_RELATIONSHIP;
        assertParseFailure(parser, userInput, Phone.MESSAGE_CONSTRAINTS);

        // invalid relationship
        userInput = "1 " + VALID_NAME + " " + VALID_PHONE + " " + INVALID_RELATIONSHIP;
        assertParseFailure(parser, userInput, Relationship.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_noParametersSpecified_success() {
        String userInput = "1";

        NextOfKinCommand expectedCommand = new NextOfKinCommand(Index.fromOneBased(1), null);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleAffiliationSpecified_success() {
        String userInput = "1 " + VALID_NAME + " " + VALID_PHONE + " " + VALID_RELATIONSHIP;

        NextOfKin nextOfKin = new NextOfKin(new Name(VALID_NAME_AMY), new Phone(VALID_PHONE_AMY),
                new Relationship("Spouse"));
        NextOfKinCommand expectedCommand = new NextOfKinCommand(Index.fromOneBased(1), nextOfKin);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
