/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.utilities;

/**
 *
 * @author Ryan Rizky
 */
public class Formatting {
    public String italic(String text) {
        if (text.startsWith("<i>") && text.endsWith("</i>")) {
            // If <i> tags are present, remove them
            return text.substring(3, text.length() - 4);
        } else {
            // If <i> tags are not present, add them
            return "<i>" + text + "</i>";
        }
    }

    public String bold(String text) {
        if (text.startsWith("<b>") && text.endsWith("</b>")) {
            // If <b> tags are present, remove them
            return text.substring(3, text.length() - 4);
        } else {
            // If <b> tags are not present, add them
            return "<b>" + text + "</b>";
        }
    }

    public String underline(String text) {
        if (text.startsWith("<u>") && text.endsWith("</u>")) {
            // If <u> tags are present, remove them
            return text.substring(3, text.length() - 4);
        } else {
            // If <u> tags are not present, add them
            return "<u>" + text + "</u>";
        }
    }
}
