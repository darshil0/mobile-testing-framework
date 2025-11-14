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
- **Base Test Class:** `BaseTest` handles driver setup and teardown, reducing boilerplate code in test classes.

## Prerequisites

- [Node.js & npm](https://nodejs.org/)
- [Appium](https://appium.io/) (`npm install -g appium`)
- [Appium Doctor](https://github.com/appium/appium-doctor) (`npm install -g appium-doctor`)
- [Java JDK](https://www.oracle.com/java/technologies/downloads/) (11+)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Android Studio](https://developer.android.com/studio) (Android) and/or [Xcode](https://developer.apple.com/xcode/) (iOS)

## Getting Started

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Configure test environment:**
    - Update `config/config.json` with paths to your mobile app (`.apk` or `.app`) and device details.
    - Optional: Override values with environment variables, e.g., `ANDROID_VERSION`.
3.  **Start Appium server:**
    ```bash
    appium
    ```
4.  **Run tests:**
    ```bash
    mvn clean test -Dplatform=android
    ```
    For iOS:
    ```bash
    mvn clean test -Dplatform=ios
    ```

## Project Structure

```
.
├── config
│   └── config.json          # Environment-specific test configurations
├── pom.xml                  # Maven project configuration
├── reports                  # Generated test reports
├── src
│   ├── main/java/utils      # Utility classes (DriverManager, GestureHelper, etc.)
│   └── test/java/com/example
│       ├── BaseTest.java    # Base class for tests
│       └── ExampleTest.java # Sample test case
├── .gitignore               # Git ignore rules
└── README.md                # This file
```

## Changelog

### Version 1.2.0 (2025-11-14)

- Refactored `ConfigReader` to use constants and a generic capability getter.
- Refactored `DriverManager` to streamline driver initialization and improve capability management.
- Refactored `WaitHelper` to load the default timeout dynamically.
- Introduced a `BaseTest` class to handle driver setup and teardown.
- Updated `ExampleTest` to extend `BaseTest`.
- Updated documentation to reflect the latest changes.

### Version 1.1.0 (2025-11-13)

- Refactored `GestureHelper` to use W3C Actions API (replacing deprecated `TouchAction`).
- Enhanced `TestListener` with completed screenshot functionality and error handling.
- Centralized driver management in `DriverManager` for cleaner test structure.
- Added Javadoc comments for utilities and example tests.
- Updated documentation to reflect improvements and best practices.

### Version 1.0.0 (2025-11-12)

- Initial framework release.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
