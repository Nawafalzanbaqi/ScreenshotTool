# ScreenshotTool 📸

A lightweight Java command-line utility that captures a full-screen screenshot, generates a grayscale version, produces an inverted-image version, and packages all outputs into a single ZIP file.  
Each run generates timestamp-based filenames to ensure uniqueness and clean organization.

---

## Features

- 🖼️ Capture full-screen screenshot  
- ⚪ Convert image to grayscale  
- 🔄 Invert grayscale image  
- 📦 Automatically ZIP all generated files  
- 🗂️ Saves all outputs to a user-specified folder

---

## Requirements

- **Java JDK 8 or later** (JDK 17+ recommended)
- Operating System with GUI support (Windows / Linux / macOS)

Verify Java installation:


`java -version`
`javac -version`

Installation
Clone the repository:


git clone https://github.com/your-username/ScreenshotTool.git

---

### Navigate into the project:


`cd ScreenshotTool`

Build
Compile the program:


`javac ScreenshotTool.java`

This will generate:

cpp
ScreenshotTool.class
Usage
Run the tool by specifying an output folder:


java ScreenshotTool <output-folder-path>

- Example (Windows)

java ScreenshotTool C:\output

- Example (Linux / macOS)


java ScreenshotTool /home/user/output

Output Files

---

### How It Works *(Brief)*

Screenshot: Uses java.awt.Robot to capture the full screen.

Grayscale Conversion: Applies weighted RGB transformation for accurate luminance.

Inversion: Flips grayscale pixel values (255 - value).

ZIP Packaging: Bundles all produced files using ZipOutputStream.

