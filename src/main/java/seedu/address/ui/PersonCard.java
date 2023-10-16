package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.MainApp;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label role;
    @FXML
    private ImageView displayPicture;

    private Image doctorImage = new Image(MainApp.class.getResourceAsStream("/images/doctor.png"));
    private Image patientImage = new Image(MainApp.class.getResourceAsStream("/images/patient.png"));


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        role.setText(person.getRole().value);

        if (person.getRole().toString().toUpperCase().equals(Role.Type.DOCTOR.name())) {
            displayPicture.setImage(doctorImage);
            role.setStyle("-fx-background-color: #89CFF0; -fx-font-weight: bold; -fx-text-fill: #0047AB");
        } else if (person.getRole().toString().toUpperCase().equals(Role.Type.PATIENT.name())) {
            displayPicture.setImage(patientImage);
            role.setStyle("-fx-background-color: #E97451; -fx-font-weight: bold; -fx-text-fill: #8B4000");
        }
    }
}
