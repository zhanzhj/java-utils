package org.liuxinyi.utils.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/30 0030.
 */
@Slf4j
public class DateUtils {

    public static final String FULL_PATTERN = "yyyyMMdd HH:mm:ss";
    public static final String CN_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String US_PATTERN = "yyyy/MM/dd HH:mm:ss";

    @Test
    public void test() {
        log.info(isBeforeTomorrow(new Date()) + "");
    }


    public boolean isBeforeTomorrow(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return date.before(calendar.getTime());
    }
}
