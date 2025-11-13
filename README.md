# Mobile Testing Framework

## Overview

This is a framework for automating mobile application tests using Appium and TestNG. It provides a basic structure for writing and organizing your mobile tests for both Android and iOS platforms.

## Features

*   **Cross-platform:** Write tests for both Android and iOS.
*   **Java-based:** Utilizes the robust and widely-used Java programming language.
*   **Maven:** Manages project dependencies and build lifecycle.
*   **TestNG:** A powerful testing framework for organizing and running tests.
*   **Page Object Model (POM):** A design pattern to create a more maintainable and reusable test codebase.
*   **Configurable:** Easily configure test parameters through a `config.json` file.

## Prerequisites

*   [Node.js and npm](https://nodejs.org/)
*   [Appium Server](https://appium.io/) (`npm install -g appium`)
*   [Appium Doctor](https://github.com/appium/appium-doctor) (`npm install -g appium-doctor`)
*   [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) (version 11 or higher)
*   [Apache Maven](https://maven.apache.org/download.cgi)
*   [Android Studio](https://developer.android.com/studio) (for Android testing) and/or [Xcode](https://developer.apple.com/xcode/) (for iOS testing)

## Setup

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Install dependencies:**
    ```bash
    mvn clean install
    ```
3.  **Configure your test environment:**
    -   Update the `config/config.json` file with the correct paths to your mobile application (.apk or .app) and device details.
    -   Make sure your Appium server is running. You can start it from the command line:
        ```bash
        appium
        ```

## How to Run Tests

You can run the tests using Maven from the command line:

```bash
mvn test
```

This will execute the test suite defined in the `testng.xml` file.

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
│   │       └── com
│   │           └── example
│   │               └── utils
│   │                   └── ConfigReader.java # Utility for reading config
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── ExampleTest.java  # Sample test case
├── .gitignore              # Files and directories to be ignored by Git
└── README.md               # This file
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
