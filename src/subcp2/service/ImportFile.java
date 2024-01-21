/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ryan Rizky
 */
public class ImportFile {
    public void importText(File file, javax.swing.table.TableModel tableModel) {
        File selectedFile = file;

        DefaultTableModel model = (DefaultTableModel) tableModel;
        int rowCount = model.getRowCount();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), StandardCharsets.UTF_8))) {
            List<String> newTextLines = new ArrayList<>();
    
                String line;
                int currentRow = 0;
    
                // Check for BOM and skip it
                if (reader.markSupported()) {
                    reader.mark(1);
                    if (reader.read() != 0xFEFF) {
                        reader.reset();
                    }
                }

                // Read all lines and process them
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
    
                    // Skip lines that don't contain text
                    if (!line.isEmpty() && !line.matches("\\d+") && !line.matches("\\d{2}:\\d{2}:\\d{2},\\d+ --> \\d{2}:\\d{2}:\\d{2},\\d+")) {
                        // Handle multiline subtitles
                        StringBuilder subtitleText = new StringBuilder(line);
    
                        // Read subsequent lines until a new subtitle starts
                        while ((line = reader.readLine()) != null && !line.trim().isEmpty() && !line.matches("\\d{2}:\\d{2}:\\d{2},\\d+ --> \\d{2}:\\d{2}:\\d{2},\\d+")) {
                            subtitleText.append("\n").append(line.trim());
                        }
    
                        newTextLines.add(subtitleText.toString());
    
                        // Break if the number of lines (excluding line numbers) exceeds the left table row count
                        if (++currentRow >= rowCount) {
                            break;
                        }
                    }
                }

                if (newTextLines.size() == rowCount) {
                    for (int i = 0; i < rowCount; i++) {
                        model.setValueAt(newTextLines.get(i), i, 4);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The number of lines in the subtitle file does not match the number of lines in the table.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importTime(File file, javax.swing.table.TableModel tableModel) {
        File selectedFile = file;

        DefaultTableModel model = (DefaultTableModel) tableModel;
        int rowCount = model.getRowCount();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), StandardCharsets.UTF_8))) {
            List<String> newTimestampLines = new ArrayList<>();
    
                String line;
                int currentRow = 0;
    
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
    
                    // Skip lines that don't contain timestamp information
                    if (line.matches("\\d{2}:\\d{2}:\\d{2},\\d+ --> \\d{2}:\\d{2}:\\d{2},\\d+")) {
                        newTimestampLines.add(line);
    
                        // Break if the number of lines (timestamp lines) exceeds the left table row count
                        if (++currentRow >= rowCount) {
                            break;
                        }
                    }
                }
    
                if (newTimestampLines.size() == rowCount) {
                    // Update the timestamp in leftTable
                    for (int i = 0; i < rowCount; i++) {
                        String timestamp = newTimestampLines.get(i);
                        String[] timestampParts = timestamp.split(" --> ");
    
                        // Assuming start time is in the 1st column and end time is in the 2nd column (adjust the column indices based on your actual structure)
                        model.setValueAt(timestampParts[0], i, 1); // Start Time
                        model.setValueAt(timestampParts[1], i, 2); // End Time
                    }
                } else {
                    // Show an error message if the subtitle lengths don't match
                    JOptionPane.showMessageDialog(null, "Subtitle length is not the same.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
