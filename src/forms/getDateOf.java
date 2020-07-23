package forms;

import java.util.Calendar;
import java.util.Date;

public class getDateOf {
    public static int Month(int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(0,month,0);
    return cal.get(Calendar.DAY_OF_MONTH);
    }
}
