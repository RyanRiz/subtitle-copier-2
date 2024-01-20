/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.service;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import subcp2.utilities.Calculate;
import subcp2.utilities.Decimal;

/**
 *
 * @author Ryan Rizky
 */
public class Copy {
    public void text(JTable sourceTable, JTable targetTable) {
        int[] selectedRowsSource = sourceTable.getSelectedRows();
        int[] selectedRowsTarget = targetTable.getSelectedRows();

        if (selectedRowsSource.length > 0 && selectedRowsTarget.length > 0) {
            DefaultTableModel sourceModel = (DefaultTableModel) sourceTable.getModel();
            DefaultTableModel targetModel = (DefaultTableModel) targetTable.getModel();

            for (int i = 0; i < Math.min(selectedRowsTarget.length, selectedRowsSource.length); i++) {
                String textToCopy = sourceModel.getValueAt(selectedRowsSource[i], 4).toString();

                // Set the text data in the corresponding row of targetTable
                targetModel.setValueAt(textToCopy, selectedRowsTarget[i], 4);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                                        "Select an equal number of rows in both tables.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void timestamp(JTable sourceTable, JTable targetTable) {
        int[] selectedRowsSource = sourceTable.getSelectedRows();
        int[] selectedRowsTarget = targetTable.getSelectedRows();

        if (selectedRowsSource.length > 0 && selectedRowsTarget.length > 0) {
            for (int i = 0; i < selectedRowsSource.length; i++) {
                int sourceRow = selectedRowsSource[i];
                int targetRow = selectedRowsTarget[i];

                DefaultTableModel sourceModel = (DefaultTableModel) sourceTable.getModel();
                DefaultTableModel targetModel = (DefaultTableModel) targetTable.getModel();

                // Get the start and end time from the source table
                String newStartTime = sourceModel.getValueAt(sourceRow, 1).toString();
                String newEndTime = sourceModel.getValueAt(sourceRow, 2).toString();
                targetModel.setValueAt(newStartTime, targetRow, 1);
                targetModel.setValueAt(newEndTime, targetRow, 2);

                // Calculate Duration and set it in the table
                Calculate calculate = new Calculate();
                long durationString = calculate.duration(newStartTime, newEndTime);

                // Format Duration
                Decimal decimal = new Decimal();
                String duration = decimal.format(durationString);

                // Set Duration
                targetModel.setValueAt(duration, targetRow, 3);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                                        "Select an equal number of rows in both tables.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void both(JTable sourceTable, JTable targetTable) {
        int[] selectedRowsSource = sourceTable.getSelectedRows();
        int[] selectedRowsTarget = targetTable.getSelectedRows();

        if (selectedRowsSource.length > 0 && selectedRowsTarget.length > 0) {
            for (int i = 0; i < selectedRowsSource.length; i++) {
                int sourceRow = selectedRowsSource[i];
                int targetRow = selectedRowsTarget[i];

                DefaultTableModel sourceModel = (DefaultTableModel) sourceTable.getModel();
                DefaultTableModel targetModel = (DefaultTableModel) targetTable.getModel();

                // Get the start and end time from the source table
                String newStartTime = sourceModel.getValueAt(sourceRow, 1).toString();
                String newEndTime = sourceModel.getValueAt(sourceRow, 2).toString();
                targetModel.setValueAt(newStartTime, targetRow, 1);
                targetModel.setValueAt(newEndTime, targetRow, 2);

                // Calculate Duration
                Calculate calculate = new Calculate();
                long durationString = calculate.duration(newStartTime, newEndTime);

                // Format Duration
                Decimal decimal = new Decimal();
                String duration = decimal.format(durationString);

                // Set Duration
                targetModel.setValueAt(duration, targetRow, 3);

                // Get the text from the source table
                String textToCopy = sourceModel.getValueAt(sourceRow, 4).toString();

                // Set the text data in the corresponding row of targetTable
                targetModel.setValueAt(textToCopy, targetRow, 4);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                                        "Select an equal number of rows in both tables.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
