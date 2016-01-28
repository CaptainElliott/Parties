package me.itselliott.partymanager.util;

/**
 * Created by Elliott2 on 27/01/2016.
 */
public class Time {

    public static int toTicks(int seconds) {
        return seconds*20;
    }

    public static int toSeconds(int ticks) {
        return ticks/20;
    }

    public static String formatTime(int s, int ms) {
        String string = "";
        if (s < 10) {
            string += "0" + s;
        } else {
            string += s;
        }
        string += ":";
        if (ms < 10) {
            string += "0" + ms;
        } else  {
            string += ms;
        }
        return string;
    }

}
