---
layout: default.md
title: "Zhan Peng's Project Portfolio Page"
---

### Project: MediSync

MediSync is a desktop app specifically used for head nurses to manage staff and patients, optimised for use via a Command Line Interface (CLI). Instead of tracking data of staff and patients using existing applications such as Microsoft Excel, using a CLI-centric application like MediSync will help head nurses track and handle contact management more quickly.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=T16-2&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=leezhanpeng&tabRepo=AY2324S1-CS2103-T16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **New Feature**: Implemented the `info` command (PR: [#145](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/145))
    * It enables user to view the information of a particular person without having to click on its `PersonCard`.
    * This thereby retains MediSync's spirit of a CLI-optimised application.
    * The difficulty of this implementation lies in the inner workings of the UI. Having to set the correct elements on focus, and allowing the use of arrow keys for information traversal took a lot of understanding of the interaction between each component, specifically the UI and the Model.


* **New Feature**: Implemented the `onduty` command (PR: [#145](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/146))
    * It enables user to list the `Doctor` or `Nurse` that are on duty for the day.
    * This feature was comfortable to implement as the existing logic in the `find` command is suitable for this. Additions to the logic include the necessary predicate for the filter, as well as the use of datetime to retrieve the correct staff members.


* **Technical Additions**: Created the `Patient`, `Doctor`, and `Nurse` classes that are utilised in varying areas of the project (PRs: [#71](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/71), [#130](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/130))
    * The use of polymorphism is necessary as we intend to have varying attributes and commands for the different role types. The `Staff` class is also created for `Doctor` and `Nurse` to morph from.
    * It was arduous in setting this up. Having the varying classes be suitable for use for ALL commands took a burst of effort. It is a needed addition for the whole application to stay intact.


* **Technical Additions**: Introduced the `Role` class/attribute to suit the presence of the three `Person` types (PR: [#88](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/88))
    * To complement the above addition, the `Role` attribute is introduced for `Person` class.
    * It streamlines a lot of the processes for the commands that are available. It does so by allowing itself to generate `Person` of the different types without the need to check their instances.


* **Technical Additions**: Introduced the `Shiftdays` class/attribute for the `Doctor` class (PR: [#109](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/109))
    * This addition was to kickstart the development of the `shiftday` command to modify shift days of staff members.


* **Project management**:
    * Managed releases on GitHub (Releases: [`v1.2b`](https://github.com/AY2324S1-CS2103-T16-2/tp/releases/tag/v1.2b), [`v1.3.trial`](https://github.com/AY2324S1-CS2103-T16-2/tp/releases/tag/v1.3.trial), [`v1.3`](https://github.com/AY2324S1-CS2103-T16-2/tp/releases/tag/v1.3), [`v1.3.1`](https://github.com/AY2324S1-CS2103-T16-2/tp/releases/tag/v1.3.1)).
    * Opened majority of issues on GitHub for task tracking (Link: [Issues](https://github.com/AY2324S1-CS2103-T16-2/tp/issues?q=is%3Aissue+is%3Aclosed))).


* **Enhancements to existing features**:
    * Revamped the whole UI of MediSync (PRs: [#100](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/100), [#129](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/129), [#155](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/155)).
    * Updated the saving and reading of JSON file to include new features introduced (PR: [#113](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/113)).
    * Enhanced JSON file reading verification so that data is thoroughly checked and any sort of violation in the data is flagged as invalid data (PR: [#158](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/158)).


* **Documentation**:
    * User Guide:
        * Modified documentation for commands `add`, `help`.
        * Added documentation for commands `onduty`, `info` (PR: [#156](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/156)).
        * Added documentation for `Displaying the information of a person using mouse`, and `Traversing the information of persons in the list` (PR: [#156](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/156)).
        * Written the `Glossary` section (PR: [#166](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/166)).
        * Written the `Editing the data file` section (PR: [#156](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/156)).
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
    * PRs reviewed (with non-trivial review comments): [#68](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/68), [#96](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/96), [#98](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/98), [#147](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/147).
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/28#issuecomment-1694649929), [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/29#issuecomment-1694648517)).
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S1-CS2103T-T12-4/tp/issues/118), [2](https://github.com/AY2324S1-CS2103T-T12-4/tp/issues/138), [3](https://github.com/AY2324S1-CS2103T-T12-4/tp/issues/124), [4](https://github.com/AY2324S1-CS2103T-T12-4/tp/issues/140)).


* **Tools**:
    * Set up the team's project repo.
    * Set up CodeCov.
    * Set up MarkBind for tP website (PR: [#42](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/42)).
    * Enabled assertions in `build.gradle` (PR: [#114](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/114)).
