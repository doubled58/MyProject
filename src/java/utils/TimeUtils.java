/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class TimeUtils {
     public static Time getSqlTimeFromHTMLTimeInput(String timeInput) {
        // Define the date format expected from HTML input
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        try {
            // Parse the string input to get a Date object
            Date parsedDate = dateFormat.parse(timeInput);

            // Convert Date to java.sql.Time
            Time sqlTime = new Time(parsedDate.getTime());

            return sqlTime;
        } catch (ParseException e) {
        }
        return null;
    }
}
