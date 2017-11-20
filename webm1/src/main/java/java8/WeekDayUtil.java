package java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by mqc on 2017/7/5.
 */
public class WeekDayUtil {

    /**
     * 获得当前日期与本周日相差的天数
     * @param gmtCreate 当前日期
     * @return int
     */
    public static int getMondayPlus(LocalDate gmtCreate) {
        int value = 7 - gmtCreate.getDayOfWeek().getValue();
        return value;
    }

    // 获得下周星期一的日期(计划建课时用)
    public static LocalDate getNextMonday(LocalDate gmtCreate) {
        LocalDate localDate = gmtCreate.plusDays(getMondayPlus(gmtCreate)+1);
        return localDate;
    }

    // 获得未来第7天的日期(JOb生成具体年月日时间)
    public static LocalDate getSevenMonday(LocalDate gmtCreate) {
        LocalDate localDate = gmtCreate.plusDays(6);
        return localDate;
    }

    /**
     * 获得下周星期一的日期（排班时用）
     * @param gmtCreate 当前日期
     * @param num 第几周（1代表下一周 2代表下下周 以此类推）
     * @return Date
     */
    public static LocalDate getNextMonday(LocalDate gmtCreate,Integer num) {
        LocalDate localDate = gmtCreate.plusDays(7*num + getMondayPlus(gmtCreate)+1);
        return localDate;
    }

    /**
     * 当前日期是周几
     * @param date 当前日期
     * @return 周几
     */
    public static int getTodayWeekDay(LocalDate date){
        int day = date.getDayOfWeek().getValue();
        return day;
    }

    /**
     * 根据计划启动日期生成具体的课程日期
     * @param date 日期
     * @param weekday 周几
     * @param timeNo 哪个时间点
     * @param type 1代表开始日期 2代表结束日期
     * @return 具体年月日时分的日期
     * @throws ParseException 异常
     */
    public static LocalDate getWeekDay(LocalDate date,int weekday,Integer timeNo,int type) throws ParseException {
        if(date == null){
            date = LocalDate.now();
        }
        date = date.plusDays(weekday-1);
        if(timeNo!=null){
            if(type==1){
                String imptimeMi = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +" "+getStartTime(timeNo);
                return LocalDate.parse(imptimeMi,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }else{
                String imptimeMi = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +" "+getEndTime(timeNo);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return LocalDate.parse(imptimeMi,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
        }else{
            String imptimeMi = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return LocalDate.parse(imptimeMi,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    /**
     * 根据日期生成未来7天内具体的课程日期
     * @param gmtCreate 日期
     * @param weekday 周几
     * @param timeNo 哪个时间点
     * @param type 1代表开始日期 2代表结束日期
     * @return 具体年月日时分的日期
     * @throws ParseException 异常
     */
    public static LocalDate getNextWeekDay(LocalDate gmtCreate,int weekday,Integer timeNo,int type) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        List<String> dates = new ArrayList<String>();
        final Calendar calendar = Calendar.getInstance();
        if(gmtCreate == null){
            gmtCreate = LocalDate.now();
        }
        int day = gmtCreate.getDayOfWeek().getValue();
        LocalDate localDate;
        if (weekday>=day){
            localDate = gmtCreate.plusDays(weekday-day);
        } else {
            localDate = gmtCreate.plusDays(7-day+weekday);
        }
        if(timeNo!=null){
            if(type==1){
                String imptimeMi = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +" "+ getStartTime(timeNo);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return LocalDate.parse(imptimeMi,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }else{
                String imptimeMi = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +" "+ getEndTime(timeNo);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return LocalDate.parse(imptimeMi,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
        }else{
            return LocalDate.parse(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    /**
     * 根据计划启动日期生成具体的课程日期
     * @param weekday 周几
     * @param timeNo 哪个时间点
     * @param type 1代表开始日期 2代表结束日期
     * @return 具体年月日时分的日期
     * @throws ParseException 异常
     */
    /*public static LocalDate getSevenWeekDay(LocalDate gmtCreate,int weekday,Integer timeNo,int type) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        List<String> dates = new ArrayList<String>();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(gmtCreate);
        String date = simpleDateFormat.format(calendar.getTime());

        try {
            if (weekday == WeekDayUtil.getTodayWeekDay(simpleDateFormat.parse(date))){
                if(timeNo!=null){
                    if(type==1){
                        String imptimeMi = date +" "+ WeekDayUtil.getStartTime(timeNo);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        return sdf2.parse(imptimeMi);
                    }else{
                        String imptimeMi = date +" "+ WeekDayUtil.getEndTime(timeNo);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        return sdf2.parse(imptimeMi);
                    }
                }else{
                    return simpleDateFormat.parse(date);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }*/
    /**
     * 开始时间
     * @param timeNo timeNo
     * @return timeNoStr
     */
    public static String getStartTime(int timeNo){
        String timeNoStr="";
        if(timeNo==1){
            timeNoStr="0:00";
        }else if(timeNo==2){
            timeNoStr="0:30";
        }else if(timeNo==3){
            timeNoStr="1:00";
        }else if(timeNo==4){
            timeNoStr="1:30";
        }else if(timeNo==5){
            timeNoStr="2:00";
        }else if(timeNo==6){
            timeNoStr="2:30";
        }else if(timeNo==7){
            timeNoStr="3:00";
        }else if(timeNo==8){
            timeNoStr="3:30";
        }else if(timeNo==9){
            timeNoStr="4:00";
        }else if(timeNo==10){
            timeNoStr="4:30";
        }else if(timeNo==11){
            timeNoStr="5:00";
        }else if(timeNo==12){
            timeNoStr="5:30";
        }else if(timeNo==13){
            timeNoStr="6:00";
        }else if(timeNo==14){
            timeNoStr="6:30";
        }else if(timeNo==15){
            timeNoStr="7:00";
        }else if(timeNo==16){
            timeNoStr="7:30";
        }else if(timeNo==17){
            timeNoStr="8:00";
        }else if(timeNo==18){
            timeNoStr="8:30";
        }else if(timeNo==19){
            timeNoStr="9:00";
        }else if(timeNo==20){
            timeNoStr="9:30";
        }else if(timeNo==21){
            timeNoStr="10:00";
        }else if(timeNo==22){
            timeNoStr="10:30";
        }else if(timeNo==23){
            timeNoStr="11:00";
        }else if(timeNo==24){
            timeNoStr="11:30";
        }else if(timeNo==25){
            timeNoStr="12:00";
        }else if(timeNo==26){
            timeNoStr="12:30";
        }else if(timeNo==27){
            timeNoStr="13:00";
        }else if(timeNo==28){
            timeNoStr="13:30";
        }else if(timeNo==29){
            timeNoStr="14:00";
        }else if(timeNo==30){
            timeNoStr="14:30";
        }else if(timeNo==31){
            timeNoStr="15:00";
        }else if(timeNo==32){
            timeNoStr="15:30";
        }else if(timeNo==33){
            timeNoStr="16:00";
        }else if(timeNo==34){
            timeNoStr="16:30";
        }else if(timeNo==35){
            timeNoStr="17:00";
        }else if(timeNo==36){
            timeNoStr="17:30";
        }else if(timeNo==37){
            timeNoStr="18:00";
        }else if(timeNo==38){
            timeNoStr="18:30";
        }else if(timeNo==39){
            timeNoStr="19:00";
        }else if(timeNo==40){
            timeNoStr="19:30";
        }else if(timeNo==41){
            timeNoStr="20:00";
        }else if(timeNo==42){
            timeNoStr="20:30";
        }else if(timeNo==43){
            timeNoStr="21:00";
        }else if(timeNo==44){
            timeNoStr="21:30";
        }else if(timeNo==45){
            timeNoStr="22:00";
        }else if(timeNo==46){
            timeNoStr="22:30";
        }else if(timeNo==47){
            timeNoStr="23:00";
        }else if(timeNo==48){
            timeNoStr="23:30";
        }
        return timeNoStr;
    }

    /**
     * 结束时间
     * @param timeNo timeNo
     * @return  timeNoStr
     */
    public static String getEndTime(int timeNo){
        String timeNoStr="";
        if(timeNo==1){
            timeNoStr="0:30";
        }else if(timeNo==2){
            timeNoStr="1:00";
        }else if(timeNo==3){
            timeNoStr="1:30";
        }else if(timeNo==4){
            timeNoStr="2:00";
        }else if(timeNo==5){
            timeNoStr="2:30";
        }else if(timeNo==6){
            timeNoStr="3:00";
        }else if(timeNo==7){
            timeNoStr="3:30";
        }else if(timeNo==8){
            timeNoStr="4:00";
        }else if(timeNo==9){
            timeNoStr="4:30";
        }else if(timeNo==10){
            timeNoStr="5:00";
        }else if(timeNo==11){
            timeNoStr="5:30";
        }else if(timeNo==12){
            timeNoStr="6:00";
        }else if(timeNo==13){
            timeNoStr="6:30";
        }else if(timeNo==14){
            timeNoStr="7:00";
        }else if(timeNo==15){
            timeNoStr="7:30";
        }else if(timeNo==16){
            timeNoStr="8:00";
        }else if(timeNo==17){
            timeNoStr="8:30";
        }else if(timeNo==18){
            timeNoStr="9:00";
        }else if(timeNo==19){
            timeNoStr="9:30";
        }else if(timeNo==20){
            timeNoStr="10:00";
        }else if(timeNo==21){
            timeNoStr="10:30";
        }else if(timeNo==22){
            timeNoStr="11:00";
        }else if(timeNo==23){
            timeNoStr="11:30";
        }else if(timeNo==24){
            timeNoStr="12:00";
        }else if(timeNo==25){
            timeNoStr="12:30";
        }else if(timeNo==26){
            timeNoStr="13:00";
        }else if(timeNo==27){
            timeNoStr="13:30";
        }else if(timeNo==28){
            timeNoStr="14:00";
        }else if(timeNo==29){
            timeNoStr="14:30";
        }else if(timeNo==30){
            timeNoStr="15:00";
        }else if(timeNo==31){
            timeNoStr="15:30";
        }else if(timeNo==32){
            timeNoStr="16:00";
        }else if(timeNo==33){
            timeNoStr="16:30";
        }else if(timeNo==34){
            timeNoStr="17:00";
        }else if(timeNo==35){
            timeNoStr="17:30";
        }else if(timeNo==36){
            timeNoStr="18:00";
        }else if(timeNo==37){
            timeNoStr="18:30";
        }else if(timeNo==38){
            timeNoStr="19:00";
        }else if(timeNo==39){
            timeNoStr="19:30";
        }else if(timeNo==40){
            timeNoStr="20:00";
        }else if(timeNo==41){
            timeNoStr="20:30";
        }else if(timeNo==42){
            timeNoStr="21:00";
        }else if(timeNo==43){
            timeNoStr="21:30";
        }else if(timeNo==44){
            timeNoStr="22:00";
        }else if(timeNo==45){
            timeNoStr="22:30";
        }else if(timeNo==46){
            timeNoStr="23:00";
        }else if(timeNo==47){
            timeNoStr="23:30";
        }else if(timeNo==48){
            timeNoStr="0:00";
        }
        return timeNoStr;
    }
    static final long nd = 1000 * 24 * 60 * 60;//天
    static final long nh = 1000 * 60 * 60;//小时
    static final long nm = 1000 * 60;//分钟
    /**
     *  获取两时间小时差
     * @param startDate 
     * @param endDate
     * @return
     */
    public static long getHour(Date startDate,Date endDate){
    	long diff = endDate.getTime() - startDate.getTime();
    	return diff % nd / nh;
    }
    /**
     * 获取两时间分钟差
     * @param startDate 
     * @param endDate
     * @return
     */
    public static long getMin(Date startDate,Date endDate){
    	long diff = endDate.getTime() - startDate.getTime();
    	return diff % nd % nh / nm;
    }
}
