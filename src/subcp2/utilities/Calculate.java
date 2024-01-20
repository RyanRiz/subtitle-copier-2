/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.utilities;

/**
 *
 * @author Ryan Rizky
 */
public class Calculate {
    public long duration(String startTime, String endTime) {
        long startMillis = convertTimeToMillis(startTime);
        long endMillis = convertTimeToMillis(endTime);

        return endMillis - startMillis;
    }

    private long convertTimeToMillis(String time) {
        String[] tokens = time.split(":|,");
        int hours = Integer.parseInt(tokens[0]);
        int minutes = Integer.parseInt(tokens[1]);
        int seconds = Integer.parseInt(tokens[2]);
        int milliseconds = Integer.parseInt(tokens[3]);

        return hours * 3600000 + minutes * 60000 + seconds * 1000 + milliseconds;
    }
}
