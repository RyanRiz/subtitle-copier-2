/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.service;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.ui.FlatListCellBorder.Default;

/**
 *
 * @author Ryan Rizky
 */
public class SaveFile {
    public void save(javax.swing.table.TableModel tableModel, String filePath) {
        DefaultTableModel model = (DefaultTableModel) tableModel;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                // Write entry number (# column)
                writer.write(model.getValueAt(i, 0).toString());
                writer.newLine();
    
                // Write time format (e.g., 00:00:19,507 --> 00:00:23,429)
                writer.write(model.getValueAt(i, 1) + " --> " + model.getValueAt(i, 2));
                writer.newLine();
    
                // Write text content
                writer.write(model.getValueAt(i, 4).toString());
                writer.newLine();
                writer.newLine(); // Add an empty line between entries
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
