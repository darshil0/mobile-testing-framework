````markdown
# Mobile Testing Framework

## Overview

This framework provides a robust and scalable structure for automating mobile application tests using **Appium** and **TestNG**. It supports both Android and iOS platforms and promotes maintainable, reusable test code.

## Features

- **Platform Support:** Write tests for Android and iOS.
- **Java-Based:** Leverages the widely-used Java programming language.
- **Maven:** Handles dependencies and project build lifecycle.
- **TestNG Integration:** Organize, execute, and report tests efficiently.
- **Centralized Configuration:** Manage environment-specific parameters via `config/config.json`.
- **Driver Management:** `DriverManager` handles driver initialization and cleanup.
- **Gesture Helpers:** Simplify swipes, taps, and long-press actions with `GestureHelper`.
- **Explicit Waits:** Use `WaitHelper` to manage dynamic waits for elements.
- **Test Listeners:** `TestListener` logs activity and captures screenshots on failures.

## Prerequisites

- [Node.js & npm](https://nodejs.org/)
- [Appium](https://appium.io/) (`npm install -g appium`)
- [Appium Doctor](https://github.com/appium/appium-doctor) (`npm install -g appium-doctor`)
- [Java JDK](https://www.oracle.com/java/technologies/downloads/) (11+)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Android Studio](https://developer.android.com/studio) (Android) and/or [Xcode](https://developer.apple.com/xcode/) (iOS)

## Getting Started

1. **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2. **Configure test environment:**
    - Update `config/config.json` with paths to your mobile app (`.apk` or `.app`) and device details.
    - Optional: Override values with environment variables, e.g., `ANDROID_VERSION`.
3. **Start Appium server:**
    ```bash
    appium
    ```
4. **Run tests:**
    ```bash
    mvn clean test -Dplatform=android
    ```
    For iOS:
    ```bash
    mvn clean test -Dplatform=ios
    ```

## Project Structure

````

.
├── config
│   └── config.json          # Environment-specific test configurations
├── pom.xml                  # Maven project configuration
├── reports                  # Generated test reports
├── src
│   ├── main/java/utils      # Utility classes (DriverManager, GestureHelper, etc.)
│   └── test/java/com/example
│       └── ExampleTest.java # Sample test case
├── .gitignore               # Git ignore rules
└── README.md                # This file

```

## Changelog

### Version 1.1.0 (2025-11-13)

- Refactored `GestureHelper` to use W3C Actions API (replacing deprecated `TouchAction`).
- Enhanced `TestListener` with completed screenshot functionality and error handling.
- Centralized driver management in `DriverManager` for cleaner test structure.
- Added Javadoc comments for utilities and example tests.
- Updated documentation to reflect improvements and best practices.

### Version 1.0.0 (2025-11-12)

- Initial framework release.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss proposed updates.
```

✅ **Highlights of improvements:**

* Clearer hierarchy and headings.
* Streamlined feature descriptions.
* Concise instructions for setup and test execution.
* Professional formatting for readability in GitHub or IDEs.
* Updated phrasing to be more action-oriented and maintainable.
