package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    private InformationWindow informationWindow;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, InformationWindow informationWindow) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        this.informationWindow = informationWindow;
    }

    /**
     * Clears any selection of {@code PersonCard}.
     */
    public void unselectPersonCard() {
        personListView.getSelectionModel().clearSelection();
    }

    /**
     * Displays the information of {@code Person} with the given index referred from the displayed contact list
     * potentially.
     *
     * @param showInfoIndex An optional index indicating the position of the {@code Person} to display information for.
     *                      If present, the {@code Person} at the specified index will be displayed in the information
     *                      window.
     */
    public void displayPotentialInformation(Optional<Integer> showInfoIndex) {
        if (showInfoIndex.isPresent()) {
            personListView.requestFocus();

            // Selects the indicated Person
            personListView.getSelectionModel().select(showInfoIndex.get());

            // Displays the information
            informationWindow.displayInformation(personListView.getSelectionModel().getSelectedItem());

            // Scrolls the Person list to the selected Person
            personListView.scrollTo(showInfoIndex.get());
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {

        public PersonListViewCell() {

            // Handles an on-click of a PersonCard
            setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) {
                    Person selectedPerson = getItem();
                    if (selectedPerson != null) {
                        informationWindow.displayInformation(selectedPerson);
                    }
                }
            });

            // Handles up and down arrow key selection of PersonCard
            personListView.setOnKeyPressed(event -> {
                Person focusedPerson = personListView.getSelectionModel().getSelectedItem();
                if (focusedPerson != null) {
                    informationWindow.displayInformation(focusedPerson);
                }
            });

            // Handles the unfocus of list view when it loses focus due to user clicking elsewhere
            personListView.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        personListView.getSelectionModel().clearSelection();
                    }
                }
            });
        }

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
