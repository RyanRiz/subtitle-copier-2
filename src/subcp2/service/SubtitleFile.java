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

import javax.swing.table.DefaultTableModel;

import subcp2.utilities.Calculate;
import subcp2.utilities.Decimal;

/**
 *
 * @author Ryan Rizky
 */
public class SubtitleFile {
    public void loadSubtitleFile(File file, javax.swing.table.TableModel tableModel) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            DefaultTableModel model = (DefaultTableModel) tableModel;
            model.setRowCount(0); // Clear existing rows
    
            String line;
            int currentRow = -1;
    
            while ((line = reader.readLine()) != null) {
                line = line.trim();
    
                if (line.isEmpty()) {
                    // Skip empty lines
                    continue;
                }
    
                if (line.matches("\\d+")) {
                    // Line with a number: Entry number (# column)
                    currentRow = model.getRowCount();
                    model.addRow(new Object[]{currentRow + 1, "", "", "", ""});
                    model.setValueAt(line, currentRow, 0);
                } else if (line.matches("\\d{2}:\\d{2}:\\d{2},\\d+ --> \\d{2}:\\d{2}:\\d{2},\\d+")) {
                    // Line with time format (e.g., 00:00:19,507 --> 00:00:23,429)
                    if (currentRow == -1) {
                        // If there is no current row, add a new one
                        currentRow = model.getRowCount();
                        model.addRow(new Object[]{currentRow + 1, "", "", "", ""});
                    }
    
                    String[] timeTokens = line.split(" --> ");
                    model.setValueAt(timeTokens[0], currentRow, 1);
                    model.setValueAt(timeTokens[1], currentRow, 2);
    
                    // Calculate Duration
                    Calculate calculate = new Calculate();
                    long durationString = calculate.duration(timeTokens[0], timeTokens[1]);

                    // Format Duration
                    Decimal decimal = new Decimal();
                    String duration = decimal.format(durationString);

                    // Set Duration
                    model.setValueAt(duration, currentRow, 3);
                } else {
                    // Line with text content
                    if (currentRow >= 0) { // Ensure there is at least one row
                        String existingText = (String) model.getValueAt(currentRow, 4);
                        String newText = existingText.isEmpty() ? line : existingText + "\n" + line;
                        model.setValueAt(newText, currentRow, 4);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
