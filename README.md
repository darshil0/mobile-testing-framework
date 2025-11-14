# Mobile Testing Framework

## Overview

This is a framework for automating mobile application tests using Appium and TestNG. It provides a robust and scalable structure for writing and organizing your mobile tests for both Android and iOS platforms.

## Features

*   **Cross-platform:** Write tests for both Android and iOS.
*   **Java-based:** Utilizes the robust and widely-used Java programming language.
*   **Maven:** Manages project dependencies and build lifecycle.
*   **TestNG:** A powerful testing framework for organizing and running tests.
*   **Centralized Configuration:** Easily manage test parameters for different environments through a `config.json` file.
*   **Driver Management:** A dedicated `DriverManager` to handle the driver lifecycle.
*   **Gesture Helpers:** A `GestureHelper` class to simplify touch actions like swipes, taps, and long presses.
*   **Explicit Waits:** A `WaitHelper` class to handle dynamic waits for elements.
*   **Test Listeners:** A `TestListener` for logging and taking screenshots on test failure.

## Prerequisites

*   [Node.js and npm](https://nodejs.org/)
*   [Appium Server](https://appium.io/) (`npm install -g appium`)
*   [Appium Doctor](https://github.com/appium/appium-doctor) (`npm install -g appium-doctor`)
*   [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) (version 11 or higher)
*   [Apache Maven](https://maven.apache.org/download.cgi)
*   [Android Studio](https://developer.android.com/studio) (for Android testing) and/or [Xcode](https://developer.apple.com/xcode/) (for iOS testing)

## Getting Started

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Configure your test environment:**
    -   Update the `config/config.json` file with the correct paths to your mobile application (.apk or .app) and device details.
    -   You can also use environment variables to override the default values in the config file. For example, you can set the `ANDROID_VERSION` environment variable to run the tests on a specific Android version.
3.  **Start the Appium server:**
    ```bash
    appium
    ```
4.  **Run the tests:**
    ```bash
    mvn clean test -Dplatform=android
    ```
    You can also run the tests for iOS by changing the `platform` parameter:
    ```bash
    mvn clean test -Dplatform=ios
    ```

## Project Structure

```
.
├── config
│   └── config.json         # Test configurations for different platforms
├── pom.xml                 # Maven project configuration
├── reports                 # Test reports will be generated here
├── src
│   ├── main
│   │   └── java
│   │       └── utils       # Utility classes (DriverManager, GestureHelper, etc.)
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── ExampleTest.java  # Sample test case
├── .gitignore              # Files and directories to be ignored by Git
└── README.md               # This file
```

## Changelog

### Version 1.1.0 (2024-07-26)

*   **Refactored `GestureHelper`:** Replaced the deprecated `TouchAction` with the W3C Actions API for improved compatibility and stability.
*   **Improved Test Listener:** Completed the `takeScreenshot` method and added error handling.
*   **Centralized Driver Management:** Refactored the `ExampleTest` to use the `DriverManager` for a cleaner and more maintainable test structure.
*   **Added Javadoc Comments:** Added detailed comments to all utility classes and the example test to improve code readability and understanding.
*   **Updated `README.md`:** Updated the documentation to reflect the latest changes and provide a more comprehensive guide.

### Version 1.0.0 (2024-07-25)

*   Initial release of the framework.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
