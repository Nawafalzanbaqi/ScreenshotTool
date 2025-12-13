# 📸 Screenshot Utility (Java)

## 🌟 Project Overview

This repository contains a simple, command-line utility written in **Java** designed for advanced screen capturing and image processing. Beyond taking a standard screenshot, the tool automatically applies two common image filters—**Grayscale** and **Inversion**—and packages all three resulting images into a single ZIP archive for immediate use.

This project is ideal for developers, testers, or users who require quick, filtered versions of their screen captures for documentation, debugging, or artistic purposes.

## ✨ Key Features

- **Full Screen Capture:** Captures the entire screen using Java's `java.awt.Robot` class.
- **Automatic Filtering:**
    - **Grayscale Conversion:** Converts the original screenshot to a Grayscale image.
    - **Image Inversion:** Inverts the colors of the Grayscale image.
- **Batch Output:** Saves the original, Grayscale, and Inverted images (as PNG files) to a specified output directory.
- **Automatic Archiving:** Compresses all three output images into a single ZIP file, named with a timestamp for easy organization.
- **Command-Line Interface:** Simple execution via the command line, requiring only the output path as an argument.

## 🛠️ Technology Stack

| Component | Technology | Purpose |
| :--- | :--- | :--- |
| **Core Language** | Java | The entire utility is built using standard Java (AWT and ImageIO). |
| **Image Processing** | `java.awt.image.BufferedImage` | Used for pixel-level manipulation to apply Grayscale and Inversion filters. |
| **Screen Capture** | `java.awt.Robot` | Handles the low-level screen capture functionality. |
| **Archiving** | `java.util.zip` | Used to compress the final output files into a single ZIP archive. |

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK):** The project requires a Java Runtime Environment (JRE) to run, and a JDK to compile.

### Compilation

If you only have the source file (`ScreenshotTool.java`), you can compile it using the Java compiler:

```bash
javac ScreenshotTool.java
```

---

### Usage

The tool is executed from the command line and requires one argument: the absolute path to the desired output folder.
```Bash
# Example for Windows (using the provided run.bat)
run.bat C:\Users\YourUser\Desktop\Screenshots
```
```bash
# Example for Linux/macOS (assuming you have the compiled .class file)
java ScreenshotTool /home/user/screenshots/output
```

- Output Files:
The tool will create the following files in your specified output directory, all named with a unique timestamp

### (e.g., screenshot_1702483200000):
- screenshot_[timestamp].png (Original Capture)
- screenshot_[timestamp]_gray.png (Grayscale Filter)
- screenshot_[timestamp]_inverted.png (Inverted Filter)
- screenshot_[timestamp].zip (Archive containing all three images)

  ---
  
### 🤝 Contributing
This project is a personal utility, but suggestions and improvements are always welcome. Feel free to fork the repository and submit a Pull Request.

---

### 📄 License
[Specify License, e.g., This project is open-source and available under the MIT License.]
