package com.ikpil.hello.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeExample implements Example {
    private static final Logger logger = LoggerFactory.getLogger(CollectionExample.class);
    private static final TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");

    public void run() {
        checkIntegerDate();
        checkIntegerYearWeek();
    }

    private void checkIntegerDate() {
        // 365 일 계산하여, 체크
        int prevDate = -1;
        for (int i = 1; i < 365 * 10; ++i) {
            Calendar calendar = Calendar.getInstance(timeZone);
            calendar.add(Calendar.DAY_OF_MONTH, i);

            int dateByCalendar = getIntegerDate(calendar);
            if (prevDate >= dateByCalendar) {
                logger.error("invalid date - prevDate({}) dateByCalendar({})", prevDate, dateByCalendar);
            }

            prevDate = dateByCalendar;
        }
    }

    private void checkIntegerYearWeek() {
        // 365 일 계산하여, 체크

        int prevDate = -1;
        for (int i = 1; i < 365 * 10; ++i) {
            Calendar calendar = Calendar.getInstance(timeZone);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setMinimalDaysInFirstWeek(4);
            calendar.add(Calendar.DAY_OF_MONTH, i);

            int dateByCalendar = getIntegerYearWeek(calendar);
            if (prevDate > dateByCalendar) {
                logger.error("invalid year week - date({}) dateByCalendar({})", calendar.getTime(), dateByCalendar);
            }

            prevDate = dateByCalendar;
        }
    }

    private int getIntegerDate(Calendar cal) {
        // Calendar.YEAR 의 필드는 1
        // Calendar.MONTH 의 필드는 0 ~ 11 까지의 값 *주의*
        // Calendar.DAY_OF_MONTH 의 필드는 1 ~ 31 까지의 값
        return cal.get(Calendar.YEAR) * 10000   // 20190000
                + (cal.get(Calendar.MONTH) + 1) * 100 // 20190700
                + cal.get(Calendar.DAY_OF_MONTH); // 20190723
    }

    private int getIntegerYearWeek(Calendar cal) {
        // 필독 - 주차는 정확 하므로, 년도는 주차 기준으로 평가 해야 합니다.

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // *주의* 월은 0 ~ 11 까지이다.
        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

        if (1 == weekOfYear && 12 == month) {
            // 주차가 1인데, 12월일 경우, 다음년도 기준
            // 예) 2019년 12월 29일은 년도는 2019 이지만, 주차는 2020년 1주차 이다
            year += 1;
        } else if (52 <= weekOfYear && month == 1) {
            // 주차가 53인데, 1월일 경우, 이전년도 셋팅
            // 예) 2021년 1월 1일은 년도는 2021년이지만, 주차는 2020년 53주차 이다.
            year -= 1;
        }

        return year * 100 + weekOfYear;
    }
}
