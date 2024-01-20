# Subtitle Copier

Subtitle Copier is a Java Swing-based application that allows users to manage and manipulate subtitle files. The application provides a user-friendly interface for opening, saving, and editing subtitle files.

## Features

### 1. Opening Subtitle Files (SRT only)

- **Open Left Subtitle**: Open a subtitle file and load its content into the left table.
- **Open Right Subtitle**: Open another subtitle file and load its content into the right table.

### 2. Viewing and Editing Subtitles

- **Subtitle Table Interaction**: Click on a row in either the left or right table to view and edit subtitle details.
- **Text Editing**: Edit the subtitle text directly in the text field.

### 3. Import and Export

- **Import Text**: Import text content from an external file into the subtitle table.
- **Import Time**: Import timestamp information from an external file into the subtitle table.
- **Export Text**: Export subtitle text content to an external file.
- **Export Time**: Export timestamp information to an external file.

### 4. Saving and Closing

- **Save**: Save the subtitles in the table to a new subtitle file.
- **Close**: Clear the content of the left or right table.

### 5. Copying Subtitles

- **Copy Text to Left/Right**: Copy subtitle text from one table to another.
- **Copy Time to Left/Right**: Copy timestamp information from one table to another.
- **Copy Both to Left/Right**: Copy both text and timestamp information from one table to another.

### 6. Formating Toolbar

- **Bold, Italic, and Underline**: Easily format subtitle text using the Formatting Toolbar.

## Upcoming Features

### 1. Search Feature (Coming Soon 🔍)
### 2. English Text Detection (Coming Soon 🌐)
### 3. Undo Manager (Coming Soon ↩️)

## Usage

1. Open the application.
2. Load subtitle files into the left and right tables.
3. Interact with subtitles, edit text, and perform various operations.
4. Save or export the edited subtitles as needed.
5. Close the application when done.

## Dependencies

This project uses the following Java libraries:

- Swing (for GUI)
- Java File I/O (for handling subtitle files)

## How to Run

To run the application, compile and execute the `Main.java` file.

```bash
javac Main.java
java Main
