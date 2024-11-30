/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutioncetre.Timetable;

import java.util.HashMap;
import java.util.Map;

public class Timetable {
    private Map<String, String[]> schedule;

    public Timetable() {
        schedule = new HashMap<>();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (String day : days) {
            schedule.put(day, new String[8]); // 8 periods per day
        }
    }

    // Get the timetable schedule
    public Map<String, String[]> getSchedule() {
        return schedule;
    }

    // Set subject for a specific day and period
    public void setSlot(String day, int period, String subject) {
        if (schedule.containsKey(day) && period >= 1 && period <= 8) {
            schedule.get(day)[period - 1] = subject;
        } else {
            System.out.println("Invalid day or period!");
        }
    }

    // Print the timetable
    public void printTimetable() {
        schedule.forEach((day, periods) -> {
            System.out.println(day + ": ");
            for (int i = 0; i < periods.length; i++) {
                System.out.println("  Period " + (i + 1) + ": " + (periods[i] == null ? "Free" : periods[i]));
            }
        });
    }
}