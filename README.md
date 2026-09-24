# GreenFlag

GreenFlag is a lightweight Java desktop utility that detects mouse inactivity and performs a small automatic cursor movement after a configurable period.

The application is intended for personal testing, automation experiments, and learning how Java interacts with desktop input devices through the `Robot` API.

> Use this application only on computers and environments where you have authorization. Do not use it to bypass organizational policies, monitoring systems, security controls, or attendance requirements.

## Features

- Detects real mouse movement.
- Measures how long the mouse has remained inactive.
- Moves the cursor automatically after a configurable inactivity period.
- Returns the cursor to its original position.
- Prevents multiple instances from running simultaneously.
- Provides console messages with execution timestamps.
- Supports stopping the application by creating a `stop.txt` file.
- Uses only standard Java libraries and requires no external dependencies.

## Requirements

- Java Development Kit (JDK) 21 or newer.
- A desktop environment with graphical interface access.
- Permission to control the mouse through the operating system.
- Windows, Linux, or macOS with Java AWT support.

The application will not work in a headless environment, such as a server without a graphical desktop.

## Project Structure

```text
GreenFlag/
├── src/
│   ├── Main.java
│   ├── MouseMover.java
│   └── META-INF/
├── .gitignore
├── mouse.iml
└── README.md
```

### Main classes

- `MouseMover.java`: Contains the inactivity detection and automatic mouse movement functionality.
- `Main.java`: Basic Java entry-point example. The functional application entry point is currently `MouseMover`.

## Configuration

The behavior can be modified through constants located at the beginning of `MouseMover.java`.

```java
private static final int INACTIVITY_SECONDS = 5;
private static final int CHECK_EVERY_SECONDS = 1;
private static final int MOVE_PIXELS = 25;
private static final int RETURN_DELAY_MS = 300;
private static final String STOP_FILE_NAME = "stop.txt";
```

| Property | Description | Default |
|---|---|---:|
| `INACTIVITY_SECONDS` | Inactivity time before moving the cursor | 5 seconds |
| `CHECK_EVERY_SECONDS` | Frequency used to check the cursor position | 1 second |
| `MOVE_PIXELS` | Horizontal distance of the automatic movement | 25 pixels |
| `RETURN_DELAY_MS` | Delay before returning to the original position | 300 ms |
| `STOP_FILE_NAME` | Name of the file used to stop the program | `stop.txt` |

## Running the Application

### Using IntelliJ IDEA

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Configure the project SDK to use Java 21.
4. Open `src/MouseMover.java`.
5. Run the `MouseMover.main()` method.

### Using PowerShell

Clone the repository:

```powershell
git clone https://github.com/JuanCarlosHernandezLomas/GreenFlag.git
cd GreenFlag
```

Compile the application:

```powershell
javac -d out src\MouseMover.java
```

Run it:

```powershell
java -cp out MouseMover
```

## Stopping the Application

To stop GreenFlag safely, create a file named `stop.txt` on the Windows desktop:

```powershell
New-Item "$env:USERPROFILE\Desktop\stop.txt" -ItemType File
```

The application detects the file, deletes it, and finishes its execution.

You can also stop it from the terminal by pressing:

```text
Ctrl + C
```

If Windows redirects the desktop to OneDrive, the actual path may be:

```text
C:\Users\<user>\OneDrive\Desktop
```

The current implementation uses the path returned by `user.home` followed by `Desktop`, so this may require adjustment on systems that redirect that folder.

## How It Works

1. The application creates a `running.lock` file.
2. If that file already exists, the application assumes another instance is running.
3. The current mouse position is obtained through `MouseInfo`.
4. The position is checked at a configurable interval.
5. Real user movement resets the inactivity timer.
6. When the inactivity threshold is reached, `Robot` moves the cursor.
7. After a short delay, the cursor returns to its original position.
8. The application continues until `stop.txt` is detected or the process is terminated.

## Important Limitations

- The project currently does not use Maven or Gradle.
- Automated tests have not been implemented.
- The stop-file path assumes that the desktop folder is located directly under the user directory.
- Unexpected termination may leave `running.lock` in the project directory.
- Some operating systems may require accessibility permissions to control the cursor.
- The program requires a graphical environment and cannot run in headless mode.

## Recommended Improvements

- Add Maven or Gradle for compilation and packaging.
- Move configuration values to command-line arguments or a properties file.
- Resolve the desktop directory using an operating-system-aware mechanism.
- Improve management of the `running.lock` file.
- Add automated tests for configuration and inactivity calculations.
- Add a graphical interface for starting and stopping the utility.
- Create a packaged executable or runnable JAR.
- Add structured logging instead of console messages.
- Handle screen boundaries when moving the cursor.

## Security and Responsible Use

GreenFlag controls the system cursor programmatically. Run it only in personal or explicitly authorized environments.

The application should not be used to:

- Circumvent employer policies.
- Falsify user activity or attendance.
- Interfere with monitoring or security controls.
- Operate on another person's computer without permission.

## Contributing

Contributions are welcome.

1. Create a new branch:

```powershell
git checkout -b feature/improvement-name
```

2. Make and test your changes.
3. Commit them:

```powershell
git commit -m "Improve GreenFlag functionality"
```

4. Push the branch:

```powershell
git push origin feature/improvement-name
```

5. Open a Pull Request describing the purpose and behavior of the change.

## License

No license has been added yet. Until a license is defined, all rights remain with the repository owner.