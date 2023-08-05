# DevelopersLife

`DevelopersLife` is a platform and user-friendly application built with Java and Gradle, making it platform-independent
and accessible to users across different operating systems. The application serves as a task manager, enabling users to
efficiently keep records of their tasks and manage their daily activities. Whether the user prefers a command-line
interface (CUI) or a graphical user interface (GUI), "DevelopersLife" caters to their needs and provides a seamless
experience.
> [!NOTE]
> This application is completely free and opensource. I have made this project for learning purpose which helps you to
> be consistent at work. This is so simple. No complex code is written. I am using `File System` instead of a database.
> You can also design it according to you by doing small changes in it.

> [!WARNING]
> Still, some exceptions are not caught properly considering that the user will develop it, according to his
> requirement.

```shell
# If you are cloning git repository and want to run the project directly

# for linux and macos
./gradlew run

# for windows
./gradlew.bat run

```
### Key Features

1. **Task Tracking**: The application empowers users to record and organize their tasks, providing a centralized hub for
   all their activities.
2. **Export Records as CSV**: This provides feature to export users records in `CSV` format which further helps in
   analyzing your work progress, self-checking, development in personal goals etc.
3. **Platform Independence**: "DevelopersLife" can be installed and used on various operating systems, ensuring its
   availability and convenience for a broad range of users.
4. **CUI and GUI**: The application offers both a command-line interface (CUI) and a graphical user interface (GUI),
   providing users the flexibility to choose the interface that suits them best.
5. **Editing Capabilities**: Users can easily edit and update their tasks, allowing for real-time adjustments and
   improvements to their task list.
6. **Task Deletion**: Tasks that are completed or no longer relevant can be effortlessly deleted from the list,
   maintaining
   a clean and organized workspace.
7. **Productivity Improvement**: By tracking tasks and their progress, "DevelopersLife" aids users in monitoring
   their productivity and identifying areas for improvement.
8. **User-Friendly Design**: The application's intuitive design and easy-to-navigate interface ensure a smooth user
   experience.

### How is it working?

* Upon launching "DevelopersLife," users are presented with the option to interact with the application through either
  the CUI or GUI.
* In the CUI, users can use text commands to add, edit, and delete tasks. The application provides clear instructions
  and prompts to guide users through the process.
* In the GUI, users can interact with a visually appealing and intuitive interface. The GUI allows for more direct
  interactions, such as clicking buttons to add or remove tasks.
* Users can create tasks, set deadlines, and assign statuses (e.g., pending, completed, unchecked) to each task. They
  can easily review their tasks and check their progress.
* Tasks can be edited to reflect any changes, ensuring the most up-to-date information is captured.
* Users can delete tasks that are no longer relevant, maintaining a concise and focused task list.
* The application's storage system utilizes the file system, ensuring that users' data is securely saved and easily
  accessible across sessions.