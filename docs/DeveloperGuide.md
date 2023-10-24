---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# MediSync Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Affiliation` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Affiliation` object per unique tag, instead of each `Person` needing their own `Affiliation` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


### Affiliation History feature
The `affiliationHistory` records the past and current affiliations that a person has with others. It is implemented similar to `affiliations`, but with a few differences:
* When adding a person, the user specified `affiliations` will be added to `affiliationHistory` of himself/herself and the affiliated person.
  * The `Person` constructor has been modified to use `affiliations` as the `affiliationHistory` during initialization.
* When editing a `Person`'s `Name`, the user specified affiliation will also be updated in `affiliationHistory` of others.
* When editing a `Person`'s `affiliations`, specifically,
  * adding `affiliations` will be add it to `affiliationHistory` as well
  * removing an `affiliations`, will **not** be removed from `affiliationHistory`
* When deleting a `Person`, the `affiliationHistory` of others will be updated accordingly.

Alternative implementations:
Another way to handle deletion is to not remove the `affiliations` from `affiliationHistory` of others. However, this would require either a flag to indicate that the `affiliations` is no longer valid, or a separate `affiliationHistory` to store the `affiliations` that are no longer valid. This would complicate the implementation of `affiliationHistory` and other functions like list so that deleted a `Person` is not shown. By removing the `affiliations` from `affiliationHistory`, it is also truly deleing the person.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* a head nurse of a hospital
* has a need to track significant number of contacts
* has a need to quickly identify relevant individuals from the vast pool of contacts
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* can type fast

**Value proposition**:

Provide categories for different healthcare roles, healthcare-specific information within each contact, capacity to track duty days and shifts for staff members, an emergency contacts section that updates based on who is on duty, robust search and filter capabilities to quickly find staff members based on criteria such as department, specialisation


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Type  | Priority | As a …​                                    | I want to …​                                                                                                         | So that I can…​                                                                                                      |
|-------|----------|--------------------------------------------|----------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| Story | `* * *`  | new user                                   | see usage instructions                                                                                               | refer to instructions when I forget how to use the App                                                               |
| Epic  | `* * *`  | new user                                   | set up the application                                                                                               |                                                                                                                      |
| Story | `* * *`  | new user                                   | see the features of the app                                                                                          |                                                                                                                      |
| Story | `* * *`  | past user                                  | import existing contact data from my previously saved contacts                                                       | kickstart my contact list                                                                                            |
| Epic  | `* * *`  | user                                       | add a new person                                                                                                     |                                                                                                                      |
| Story | `* * *`  | user                                       | specify contact details, including phone numbers and email addresses,                                                | maintain accurate and up-to-date contact information for each staff member                                           |
| Story | `* * *`  | user                                       | add emergency contact information for each staff member                                                              | quickly reach out to the appropriate contacts in critical situations                                                 |
| Story | `* * *`  | user                                       | include pager numbers for doctors and on-call schedules                                                              | have a method of contact during off-hours                                                                            |
| Epic  | `* * *`  | head nurse                                 | edit my contacts                                                                                                     |                                                                                                                      |
| Story | `* * *`  | head nurse                                 | update contact information (e.g., phone numbers, email addresses) as needed                                          | ensure that the contact details remain current and reliable                                                          |
| Story | `* * *`  | head nurse                                 | track and manage staff availability for upcoming shifts in the contact book including leave requests and shift swaps | manage the availability and shift schedule easily                                                                    |
| Story | `* * *`  | head nurse                                 | add comments to the contact book entries, such as private notes                                                      | make more informed decisions when assigning tasks or seeking assistance                                              |
| Story | `* * *`  | head nurse                                 | mark certain contacts as "critical contacts" in the contact book                                                     | they can be searched for and identified more easily in emergencies                                                   |
| Story | `* * *`  | head nurse                                 | adjust the shifts of staff members                                                                                   | staff members can have flexibility in changing shift timings                                                         |
| Story | `* * *`  | head nurse                                 | transfer a patient to a new doctor in charge                                                                         | patients can be flexibly transferred when different expertise is required, or when manpower needs reallocation       |
| Epic  | `* * *`  | head nurse                                 | view my contacts                                                                                                     |                                                                                                                      |
| Story | `* * *`  | head nurse                                 | view who is the doctor in charge of a patient currently                                                              |                                                                                                                      |
| Story | `* * *`  | head nurse                                 | view who are the patients who are currently under a doctor                                                           |                                                                                                                      |
| Story | `* * *`  | head nurse                                 | view the past doctors who have treated the patient                                                                   | obtain some medical history                                                                                          |
| Story | `* * *`  | head nurse                                 | view who are the past patients who have been treated by a doctor                                                     | obtain the patient history of the doctor                                                                             |
| Story | `* * *`  | head nurse                                 | view view the patients whom the nurses are in charge of                                                              | balance the workload of my nurses                                                                                    |
| Story | `* * *`  | head nurse                                 | view next-of-kin information of patients                                                                             | contact them when necessary                                                                                          |
| Story | `* * *`  | head nurse                                 | view the patients’ discharge dates                                                                                   | manage the discharge procedure                                                                                       |
| Story | `* * *`  | head nurse                                 | view the duty and shift dates of my staff                                                                            | manage and oversee when they are working                                                                             |
| Epic  | `* * *`  | head nurse                                 | delete a person                                                                                                      | remove entries that I no longer need                                                                                 |
| Story | `* * *`  | head nurse                                 | remove only the unwanted information about a staff or a patient if it is inaccurate                                  | ensure that the directory remains accurate                                                                           |
| Story | `* * *`  | head nurse                                 | delete contacts of staff who are not working in the hospital anymore                                                 |                                                                                                                      |
| Story | `* * *`  | head nurse                                 | delete contacts of patients who have been discharged                                                                 |                                                                                                                      |
| Story | `* * *`  | head nurse                                 | delete all contacts when I quit                                                                                      |                                                                                                                      |
| Story | `* * *`  | head nurse                                 | maintain a contact book of all medical staff within the hospital                                                     | quickly find and contact the right healthcare professionals in case of emergencies or when coordinating patient care |
| Epic  | `* * *`  | head nurse                                 | manage my contact book                                                                                               |                                                                                                                      |
| Story | `* * *`  | head nurse                                 | back up the contact book                                                                                             | prevent data loss and maintain the integrity of the contact information                                              |
| Story | `* * *`  | head nurse                                 | load the previous backup                                                                                             | restore my contacts when I make wrong edits                                                                          |
| Story | `* * *`  | user                                       | export contact information in a printable format for reference                                                       | have physical copies of essential contact details when needed                                                        |
| Epic  | `* * *`  | head nurse                                 | search for specific contacts                                                                                         | locate details of persons without having to go through the entire list                                               |
| Story | `* *`    | head nurse                                 | filter out "critical contacts" in the contact book                                                                   | reach them quickly during urgent situations                                                                          |
| Story | `* *`    | head nurse                                 | select language preferences and interpreter contact information                                                      | effectively communicate with patients who have limited English proficiency                                           |
| Story | `* *`    | head nurse                                 | track and display the availability status of staff members (e.g., on-duty, off-duty, on-call)                        | facilitate decision-making for patient care assignments                                                              |
| Story | `* *`    | head nurse                                 | filter staff members by their specialisation (e.g., cardiologist, pediatrician)                                      | identify and contact the right healthcare professionals based on their expertise                                     |
| Story | `* *`    | head nurse                                 | search for staff members by name, role, or department quickly                                                        | easily find and access their contact information when necessary                                                      |
| Story | `* *`    | user                                       | hide private contact details                                                                                         | minimize chance of someone else seeing them by accident                                                              |
| Story | `*`      | user with many persons in the address book | sort persons by name                                                                                                 | locate a person easily                                                                                               |
| Story | `*`      | include photos of staff members            | quickly identify the individual and better personalise our communication                                             | locate a person easily                                                                                               |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `MediSync`, the **Actor** is the `user`, and the `precondition` is that user is in the main interface unless specified otherwise)

**Use case: UC01- Add a person**

**MSS**

1. User requests to add new person and provide details
2. MediSync adds person to system and confirms addition to user

    Use case ends.

**Extensions**

* 1a. Provided details are incomplete or incorrect format

  * 1a1. MediSync shows an error message.

    Use case ends.

* 2a. Person of the same name already exists

    * 2a1. MediSync shows an error message.

      Use case ends.

**Use case: UC02- Edit a person**

**MSS**

1. User requests to edit a particular person's contact and provide details
2. MediSync edits person on system and confirms changes to user

   Use case ends.

**Extensions**

* 1a. Provided details are incomplete or incorrect format

    * 1a1. MediSync shows an error message.

      Use case ends.

* 1a. The given index is invalid.

    * 1a1. MediSync shows an error message.

      Use case ends.

**Use case: UC03- Delete a person**

**MSS**

1.  User requests to list persons
2.  MediSync shows a list of persons
3.  User requests to delete a specific person in the list
4.  MediSync deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. MediSync shows an error message.

      Use case resumes at step 2.

**Use case: UC04- Find a person**

**MSS**

1.  User requests to find person by name
2.  MediSync shows details of the all relevant persons
3.  User issues command to access help
4.  MediSync displays help instructions

    Use case ends.

**Extensions**

* 2a. No relevant names found.

  Use case resumes at step 3.

* 2a. List is empty.

  Use case resumes at step 3.

**Use case: UC05- Find affiliation of a person**

**MSS**

1.  User requests to find affiliations of a person
2.  MediSync shows all persons affiliated with the specified person
3.  User requests to exit MediSync
4.  MediSync closes the application

    Use case ends.

**Extensions**

* 1a. The given index is invalid.

    * 1a1. MediSync shows an error message.

      Use case resumes at step 3.

* 2a. Person has no affiliations.

  Use case resumes at step 3.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should respond to all commands within 3 seconds.
5.  Commands should be easy to remember and succinct.
6.  Product is not required to handle the printing of reports.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
