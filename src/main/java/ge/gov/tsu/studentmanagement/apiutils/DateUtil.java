package ge.gov.tsu.studentmanagement.apiutils;

import java.text.SimpleDateFormat;
import java.util.Date;

public  class DateUtil {

    //private static String dateString;

    public static String getDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getOnlyDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
