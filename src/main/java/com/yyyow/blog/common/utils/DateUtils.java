package com.yyyow.blog.common.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 */
public class DateUtils {

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static int getIntNowTimestamp(){
        Instant now = Instant.now();
        Long epochSecond = now.getEpochSecond();
        return epochSecond.intValue();
    }

    public static long getLongNowTimestamp(){
        Instant now = Instant.now();
        return now.getEpochSecond();
    }


    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static long getLongSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Long.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static int getSecondTimestampTwo(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }



}
