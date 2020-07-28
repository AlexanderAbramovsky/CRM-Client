package sahan.abr.lib;

import org.w3c.dom.ls.LSOutput;
import sahan.abr.entities.Client;
import sahan.abr.entities.Employee;
import sahan.abr.entities.Parent;

import java.util.Calendar;

public class LibSRM {

    public static int[] splitDay(String day) {
        String[] time = day.split("\\.");

        int date[] = new int[3];

        date[0] = Integer.parseInt(time[0]);
        date[1] = Integer.parseInt(time[1]);
        date[2] = Integer.parseInt(time[2]);

        return date;
    }

    public static String getDateStringFormat(Calendar calendar) {
        String day = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0" : "") + calendar.get(Calendar.DAY_OF_MONTH);
        String month = (((calendar.get(Calendar.MONTH) + 1) < 10) ? "0" : "") + (calendar.get(Calendar.MONTH) + 1);
        String year = "" + calendar.get(Calendar.YEAR);
        String[] masYear = year.split("");
        year = masYear[masYear.length - 2] + masYear[masYear.length - 1];
        return day + "." + month + "." + year;
    }

    public static String getFIO(Employee employee) {
        return employee.getSurname() + " " + employee.getName().split("")[0]
                + "." + employee.getMiddleName().split("")[0];
    }

    public static String getFIO(Client client) {
        return client.getSurname() + " " + client.getName().split("")[0]
                + "." + client.getMiddleName().split("")[0] + ".";
    }

//    public void dateOutput(){
//        String day = "";
//        String month = "";
//        String year = "";
//
//        for (int i = 1; i <= 6; i++) {
//
//            day = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0" : "") + calendar.get(Calendar.DAY_OF_MONTH);
//            month = (((calendar.get(Calendar.MONTH) + 1) < 10) ? "0" : "") + (calendar.get(Calendar.MONTH) + 1);
//            year = "" + calendar.get(Calendar.YEAR);
//            String[] masYear = year.split("");
//            year = masYear[masYear.length - 2] + masYear[masYear.length - 1];
//
//            if (i == 1) labelMonday.setText(day + "." + month + "." + year);
//            if (i == 2) labelTuesday.setText(day + "." + month + "." + year);
//            if (i == 3) labelWednesday.setText(day + "." + month + "." + year);
//            if (i == 4) labelThursday.setText(day + "." + month + "." + year);
//            if (i == 5) labelFriday.setText(day + "." + month + "." + year);
//            if (i == 6) labelSaturday.setText(day + "." + month + "." + year);
//
//            date.setDate(date.getDate() + 1);
//            calendar.setTime(date);
//        }
//
//        day = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0" : "") + calendar.get(Calendar.DAY_OF_MONTH);
//        month = (((calendar.get(Calendar.MONTH) + 1) < 10) ? "0" : "") + (calendar.get(Calendar.MONTH) + 1);
//        year = "" + calendar.get(Calendar.YEAR);
//        String[] masYear = year.split("");
//        year = masYear[masYear.length - 2] + masYear[masYear.length - 1];
//
//        labelSunday.setText(day + "." + month + "." + year);
//
//        labelWeek.setText(labelMonday.getText() + " - " + labelSunday.getText());
//
//        strWeek[7] = labelSunday.getText();
//        strWeek[1] = labelMonday.getText();
//        strWeek[2] = labelTuesday.getText();
//        strWeek[3] = labelWednesday.getText();
//        strWeek[4] = labelThursday.getText();
//        strWeek[5] = labelFriday.getText();
//        strWeek[6] = labelSaturday.getText();
//    }
}
