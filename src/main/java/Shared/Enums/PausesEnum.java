package Shared.Enums;

import java.sql.Time;

public class PausesEnum {

    public static final int PAUSE_1 = 1;
    public static final int PAUSE_2 = 2;
    public static final int PAUSE_3 = 3;

   public static int getHours (int number) {
      int pause = 0 ;
       switch (number) {
           case PAUSE_1 :
               pause =20 ;
               break;
           case PAUSE_2:
               pause = 60 ;
               break ;
           case PAUSE_3:
               pause = 15 ;
               break;
        }
        return pause ;
   }
    public static int calculateValue(Time time) {

        Time startTime1 = Time.valueOf("09:00:00");
        Time endTime1 = Time.valueOf("12:00:00");
        Time startTime2 = Time.valueOf("12:00:00");
        Time endTime2 = Time.valueOf("14:00:00");


        if (time.after(startTime1) && time.before(endTime1)) {
            return 1;
        } else if (time.after(startTime2) && time.before(endTime2)) {
            return 2;
        } else {
            return 3;
        }
    }

}
