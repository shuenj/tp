---
layout: default.md
title: "Zheng Chenglong's Project Portfolio Page"
---

### Project: MediSync

MediSync is a desktop app specifically used for head nurses to manage staff and patients, optimised for use via a Command Line Interface (CLI). Instead of tracking data of staff and patients using existing applications such as Microsoft Excel, using a CLI-centric application like MediSync will help head nurses track and handle contact management more quickly.

Given below are my contributions to the project.

* **New Feature**: Implemented the `addaffn` command (PR: [#107](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/107))
  * It enables user to add affiliation on top of a person current affiliation rather than to overwrite it.

* **New Feature**: Implemented the `nok` command (PR: [#157](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/157))
  * It enables user to add next-of-kin details of `Patient`.

* **New Feature**: Implemented the `nok` command (PR: [#137](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/137))
  * It enables user to remove affiliation history of `Person`.

* **Technical Additions**: Created the `AffiliationAuthtication` and `AffiliationModifier` classes that are utilised in varying areas of the project (PRs: [#96](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/96))
  * 

* **Technical Additions**: Introduced the `NextOfKin` class/attribute to suit the presence of the three `Person` types (PR: [#148](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/148))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=seonlo99&breakdown=false)

* **Project management**:
  * To be added

* **Enhancements to existing features**:
  * To be added

* **Documentation**:
  * User Guide:
    * Modified documentation for commands `edit`.
    * Added documentation for commands `addaffn`, `removeah`, `nok` (PR: [#161](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/161)).
    * Adjusted the `Command Summary` table.
  
  * Developer Guide:
    * Updated UI component (PR: [#129](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/129)).
      * Adjusted the overview Class diagram of the UI component.
      * Added sequence diagram for the interaction between `PersonListPanel` and `InformationWindow` in the UI component.
    * Updated Storage component (PR: [#129](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/129)).
      * Adjusted the overview Class diagram of the Storage component.
      * Added the activity diagram for the jsonification process of the saving of data in MediSync.
    * Updated the Non-Functional Requirements.

* **Community**:
  * PRs reviewed (with non-trivial review comments):
  * Contributed to forum discussions (examples: )
  * Reported bugs and suggestions for other teams in the class (examples: To be added)

* **Tools**:
  * Set up the team's project repo
