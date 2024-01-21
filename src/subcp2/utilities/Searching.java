/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Ryan Rizky
 */
public class Searching {
    private int lastFoundRow = -1;
    private int lastFoundLine = -1;

    public int text(String text, boolean RadiocaseSensitive, JTable table) {
        String searchText = text;
    
        boolean caseSensitive = RadiocaseSensitive;
    
        int flags = 0;
        if (!caseSensitive) {
            flags = flags | Pattern.CASE_INSENSITIVE;
        }
    
        Pattern pattern = Pattern.compile(searchText, flags);
    
        int startRow = (lastFoundRow == -1) ? 0 : lastFoundRow + 1;
    
        boolean found = false;
        int maxIterations = table.getRowCount(); // Limit the number of iterations
    
        for (int row = startRow, iteration = 0; iteration < maxIterations; row++, iteration++) {
            if (row == table.getRowCount()) {
                row = 0; // Loop back to the beginning of the table
            }
    
            for (int col = 0; col < table.getColumnCount(); col++) {
                Object cellValue = table.getValueAt(row, col);
                if (cellValue != null) {
                    String cellText = cellValue.toString();
                    Matcher matcher = pattern.matcher(cellText);
    
                    if (matcher.find()) {
                        // Scroll to the found row
                        table.setRowSelectionInterval(row, row);
                        table.scrollRectToVisible(table.getCellRect(row, col, true));
                        lastFoundRow = row;
                        found = true;
                        break; // Stop searching after the first match
                    }
                }
            }
    
            if (found) {
                break; // Stop searching if a match is found
            }
        }
    
        if (!found) {
            // If no match is found, reset lastFoundRow to search from the beginning again
            lastFoundRow = -1;
    
            // If no match is found, you can display a message or take appropriate action
            JOptionPane.showMessageDialog(null, "No matching result found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }

        return lastFoundRow;
    }

    public int getLastFoundRow() {
        return lastFoundRow;
    }

    public int line(int lineNumber, JTable table) {
        String searchText = "\\b" + lineNumber + "\\b";
        Pattern pattern = Pattern.compile(searchText);

        int startLine = (lastFoundLine == -1) ? 0 : lastFoundLine + 1;
        int maxIterations = table.getRowCount();

        boolean found = false;

        for (int row = startLine, iteration = 0; iteration < maxIterations; row++, iteration++) {
            if (row == table.getRowCount()) {
                row = 0; // Loop back to the beginning of the table
            }

            Object cellValue = table.getValueAt(row, 0);
            if (cellValue != null) {
                String cellText = cellValue.toString();
                Matcher matcher = pattern.matcher(cellText);

                if (matcher.find()) {
                    // Scroll to the found row
                    table.setRowSelectionInterval(row, row);
                    table.scrollRectToVisible(table.getCellRect(row, 0, true));
                    lastFoundLine = row;
                    found = true;
                    break; // Stop searching after the first match
                }
            }
        }

        if (!found) {
            // If no match is found, reset lastFoundLine to search from the beginning again
            lastFoundLine = -1;

            // If no match is found, you can display a message or take appropriate action
            JOptionPane.showMessageDialog(null, "No matching line found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }

        return lastFoundLine;
    }

    public int getLastFoundLine() {
        return lastFoundLine;
    }
}

