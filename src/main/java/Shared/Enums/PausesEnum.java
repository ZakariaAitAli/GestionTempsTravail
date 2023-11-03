package Shared.Enums;

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

}
