/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.utilities;

import java.text.DecimalFormat;
/**
 *
 * @author Ryan Rizky
 */
public class Decimal {
    public String format(Long time) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String output = decimalFormat.format(time);

        return output;
    }
}
