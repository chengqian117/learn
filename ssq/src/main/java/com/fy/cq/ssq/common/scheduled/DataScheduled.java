package com.fy.cq.ssq.common.scheduled;

import com.fy.cq.ssq.modules.data.entity.SecondData;
import com.fy.cq.ssq.modules.data.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Profile({"default","test","dev"})
@Slf4j
public class DataScheduled {
    @Autowired
    SecondDataService secondDataService;
    private static ExecutorService executorService;

    public static synchronized ExecutorService getExecutor(){

        if(executorService==null){
            executorService = Executors.newCachedThreadPool();
        }
        return executorService;
    }

    @Scheduled(cron="0/1 * * * * ? ")
    //每秒生成数据
    public void generationSecondData(){
        ExecutorService executor = this.getExecutor();
        String [] ss=new String[]{"1","2","3","4"};
        for (String s:ss ) {
            executor.submit(()->{
                try {
                    SecondData da=new SecondData(s,new Date(),new BigDecimal(Math.random()*(100)+0).setScale(2, BigDecimal.ROUND_DOWN),
                            "1","");
                    boolean t=secondDataService.save(da);
                    if(t){
                        log.info("insert"+s+" :data"+da.toString());
                    }else{
                        log.error("error--------second   :"+"insert"+s+" :data"+da.toString());
                    }
                }catch (Exception e){
                    log.error("异常信息 e={} "+"秒生成数据",e.getMessage(),e);
                }

            });
        }

    }

    @Autowired
    MinuteDataService minuteDataService;
    //每分钟的第3秒汇总 上一分钟的数据
    @Scheduled(cron="3 * * * * ?")
    public void sumSecondData(){
        ExecutorService executor = this.getExecutor();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        //当前时间倒退一分钟获取上一分的样式
        long l=date.getTime()-60*1000;
        String date2=simpleDateFormat.format(new Date(l));
        String [] ss=new String[]{"1","2","3","4"};
        for (String s:ss ) {
            executor.submit(()->{
                try {
                    int i= minuteDataService.sumSecondDataByUserAndDay(s,date2);
                    if(i==1){
                        log.info("分汇总 userId:"+s+" date:+"+date2);
                    }else{
                        log.error("error--------minute   :"+"userId"+s+" :data"+date2);
                    }
                }catch (Exception e){
                    log.error("异常信息 e={} "+"分汇总",e.getMessage(),e);
                }

            });
        }

    }

    @Autowired
    HourDataService hourDataService;
    //每小时的第3分钟汇总 上一小时的数据
    @Scheduled(cron="0 3 * * * ?")
    public void sumMinuteData(){
        ExecutorService executor = this.getExecutor();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Date date=new Date();
        //当前时间倒退一小时获取上一小时的样式
        long l=date.getTime()-60*60*1000;
        String date2=simpleDateFormat.format(new Date(l));
        String [] ss=new String[]{"1","2","3","4"};
        for (String s:ss ) {
            executor.submit(()->{
                try{
                    int i= hourDataService.sumMinuteDataByUserAndDay(s,date2);
                    if(i==1){
                        log.info("小時汇总 userId:"+s+" date:+"+date2);
                    }else{
                        log.error("error--------hour   :"+"userId"+s+" :data"+date2);
                    }
                }catch (Exception e){
                    log.error("异常信息 e={} "+"小时汇总",e.getMessage(),e);
                }

            });
        }

    }

    @Autowired
    DayDataService dayDataService;
    //每天的0点3分汇总 上一天的数据
    @Scheduled(cron="0 3 0 * * ?")
    public void sumHourData(){
        ExecutorService executor = this.getExecutor();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        //当前时间倒退一天获取上一天的样式
        long l=date.getTime()-24*60*60*1000;
        String date2=simpleDateFormat.format(new Date(l));
        String [] ss=new String[]{"1","2","3","4"};
        for (String s:ss ) {
            executor.submit(()->{
                try{
                    int i= dayDataService.sumHourDataByUserAndDay(s,date2);
                    if(i==1){
                        log.info("天汇总 userId:"+s+" date:+"+date2);
                    }else{
                        log.error("error--------day   :"+"userId"+s+" :data"+date2);
                    }
                }catch (Exception e){
                    log.error("异常信息 e={} "+"天汇总",e.getMessage(),e);
                }

            });
        }

    }

    @Autowired
    MonthDataService monthDataService;
    //每月的1号0点3分汇总 上一月的数据
    @Scheduled(cron="0 3 0 1 * ?")
    public void sumDayData(){
        ExecutorService executor = this.getExecutor();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date date=new Date();
        //当前时间倒退一天获取上个月最会一天的月份样式
        long l=date.getTime()-24*60*60*1000;
        String date2=simpleDateFormat.format(new Date(l));
        String [] ss=new String[]{"1","2","3","4"};
        for (String s:ss ) {
            executor.submit(()->{
                try{
                    int i= monthDataService.sumDayDataByUserAndDay(s,date2);
                    if(i==1){
                        log.info("月汇总 userId:"+s+" date:+"+date2);
                    }else{
                        log.error("error--------day   :"+"userId"+s+" :data"+date2);
                    }
                }catch (Exception e){
                    log.error("异常信息 e={} "+"月汇总",e.getMessage(),e);
                }

            });
        }

    }

    @Autowired
    YearDataService yearDataService;
    //每年1月1号0点3分汇总 上一年的数据
    @Scheduled(cron="0 3 0 1 1 ?")
    public void sumMonthData(){
        ExecutorService executor = this.getExecutor();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date date=new Date();
        //当前时间倒退一天获取上个年的最后一天的年份样式
        long l=date.getTime()-24*60*60*1000;
        String date2=simpleDateFormat.format(new Date(l));
        String [] ss=new String[]{"1","2","3","4"};
        for (String s:ss ) {
            executor.submit(()->{
                try{
                    int i= yearDataService.sumMonthDataByUserAndDay(s,date2);
                    if(i==1){
                        log.info("年汇总 userId:"+s+" date:+"+date2);
                    }else{
                        log.error("error--------day   :"+"userId"+s+" :data"+date2);
                    }
                }catch (Exception e){
                    log.error("异常信息 e={} "+"年汇总",e.getMessage(),e);
                }

            });
        }

    }

}
