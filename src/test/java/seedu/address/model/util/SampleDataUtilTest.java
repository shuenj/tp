package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class SampleDataUtilTest {

    @Test
    public void retrieveData_compareAttributes_success() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        assertEquals(new Name("Alex Yeoh"), samplePersons[0].getName());
        assertEquals(new Name("Bernice Yu"), samplePersons[1].getName());
        assertEquals(new Email("homimay@example.com"), samplePersons[2].getEmail());
        assertEquals(new Phone("93210283"), samplePersons[3].getPhone());
        assertEquals(new Email("lidavid@example.com"), samplePersons[4].getEmail());
        assertEquals(new Name("Irfan Ibrahim"), samplePersons[5].getName());
        assertEquals(new Phone("92624417"), samplePersons[6].getPhone());

    }
}
