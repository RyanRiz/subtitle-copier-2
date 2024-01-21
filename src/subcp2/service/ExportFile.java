/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ryan Rizky
 */
public class ExportFile {
    public void exportText(File file, javax.swing.table.TableModel tableModel) {
        File selectedFile = file;

        // Ensure the file has a .txt extension
        if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
            selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
            DefaultTableModel model = (DefaultTableModel) tableModel;

            // Write all lines
            for (int i = 0; i < model.getRowCount(); i++) {
                // Assuming text is in the 4th column (adjust the column index based on your actual structure)
                String text = model.getValueAt(i, 4).toString();
                writer.write(text);
                
                // Add a newline character after each line of text
                writer.newLine();
                // Add an additional newline character for an empty line separation
                writer.newLine();
            }

            JOptionPane.showMessageDialog(null, "Text exported successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error exporting file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exportTime(File file, javax.swing.table.TableModel tableModel) {
        File selectedFile = file;

        // Ensure the file has a .txt extension
        if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
            selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
            DefaultTableModel model = (DefaultTableModel) tableModel;

            for (int i = 0; i < model.getRowCount(); i++) {
                // Assuming start time is in the 1st column and end time is in the 2nd column (adjust the column indices based on your actual structure)
                String startTime = model.getValueAt(i, 1).toString();
                String endTime = model.getValueAt(i, 2).toString();
                
                // Format the timestamp line
                String timestampLine = startTime + " --> " + endTime;
                writer.write(timestampLine);

                // Add a newline character after each line of timestamp
                writer.newLine();
                // Add an additional newline character for an empty line separation
                writer.newLine();
            }

            JOptionPane.showMessageDialog(null, "Timestamp exported successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error exporting file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
