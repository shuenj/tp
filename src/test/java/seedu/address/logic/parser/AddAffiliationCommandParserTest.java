package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AFFILIATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.AFFILIATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AFFILIATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AFFILIATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAffiliationCommand;
import seedu.address.model.affiliation.Affiliation;

public class AddAffiliationCommandParserTest {

    private static final String AFFILIATION_EMPTY = " " + PREFIX_AFFILIATION;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAffiliationCommand.MESSAGE_USAGE);

    private AddAffiliationCommandParser parser = new AddAffiliationCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_AFFILIATION_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddAffiliationCommand.MESSAGE_NO_AFFILIATION_INPUT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + AFFILIATION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + AFFILIATION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidAffiliation_failure() {
        assertParseFailure(parser, "1" + INVALID_AFFILIATION_DESC,
                Affiliation.MESSAGE_CONSTRAINTS); // invalid affiliation

        // invalid affiliation followed by valid affiliation
        assertParseFailure(parser, "1" + INVALID_AFFILIATION_DESC + VALID_AFFILIATION_AMY,
                Affiliation.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_AFFILIATION} alone will reset the affiliations of
        // the {@code Person} being edited,
        // parsing it together with a valid affiliation results in error
        assertParseFailure(parser, "1" + AFFILIATION_DESC_BOB + AFFILIATION_DESC_AMY
                + AFFILIATION_EMPTY, Affiliation.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + AFFILIATION_DESC_BOB + AFFILIATION_EMPTY
                + AFFILIATION_DESC_AMY, Affiliation.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + AFFILIATION_EMPTY + AFFILIATION_DESC_BOB
                + AFFILIATION_DESC_AMY, Affiliation.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_oneAffiliationSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + AFFILIATION_DESC_AMY;

        Set<Affiliation> expectedAffiliation = new HashSet<>();
        expectedAffiliation.add(new Affiliation(VALID_NAME_AMY));
        AddAffiliationCommand expectedCommand = new AddAffiliationCommand(targetIndex, expectedAffiliation);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleAffiliationSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + AFFILIATION_DESC_AMY + AFFILIATION_DESC_BOB;

        Set<Affiliation> expectedAffiliation = new HashSet<>();
        expectedAffiliation.add(new Affiliation(VALID_NAME_AMY));
        expectedAffiliation.add(new Affiliation(VALID_NAME_BOB));
        AddAffiliationCommand expectedCommand = new AddAffiliationCommand(targetIndex, expectedAffiliation);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
