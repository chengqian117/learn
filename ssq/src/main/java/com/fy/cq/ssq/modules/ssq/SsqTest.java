package com.fy.cq.ssq.modules.ssq;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SsqTest {

    //private int seed=37;

    private Integer[] hq;
    private Integer[] hqsx;
    private int lq;

    public static SsqTest getOneSsq(){
        SsqTest ssq=new SsqTest();
        ssq.hq=new Integer[6];
        ssq.hqsx=new Integer[6];
        ssq.lq=0;
        List<Integer> hqList2=Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36);
        List<Integer> lqList=Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        List<Integer> hqList=new ArrayList<>(hqList2);
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<6;i++){
            int integer=(int)(Math.random()*hqList.size());
            list.add(hqList.get(integer));
            hqList.remove(integer);
        }
        ssq.hqsx=list.toArray(ssq.hqsx);
        list.sort(Integer::compareTo);
        ssq.hq=list.toArray(ssq.hq);
        ssq.lq=lqList.get(((int)(Math.random()*lqList.size())));
        return ssq;
    }


    public void getCs(){
        int i=0;
        boolean a=false;
        Integer[] i1=new Integer[]{10,12,27,31,32,33};
        Integer[] i2=new Integer[]{31,10,32,33,12,27};
        int l=3;
        while (!a) {
            SsqTest oneSsq = SsqTest.getOneSsq();
            if(Arrays.equals(oneSsq.hq,i1)&&Arrays.equals(oneSsq.hqsx,i2)&&l==oneSsq.lq){
                a=true;
            }
            i++;
        }
        System.out.println(i);
    }
    public static void main(String[] args) {
        Map<Integer,Integer> map=new HashedMap();
        for (int i=0;i<1000000;i++){
            Integer integer=Integer.valueOf((int)(Math.random()*16));
            if(map.get(integer)==null){
                map.put(integer,1);
            }else{
                Integer a=map.get(integer);
                map.put(integer,a+1);
            }
        }
        map.forEach((integer, integer2) -> System.out.println("号码 "+integer+" 总共"+integer2+ "次"));
//        System.out.println(new Date());
//        int i=2018604033;
//        while (i!=0){
//            SsqTest oneSsq = SsqTest.getOneSsq();
//            if(i==1){
//                System.out.println(oneSsq);
//            }
//            i--;
//        }
//        SsqTest oneSsq = SsqTest.getOneSsq();
//        System.out.println("2"+oneSsq);
//        System.out.println(new Date());

        System.out.println(getOneSsq());
    }

    @Override
    public String toString() {
        return "SsqTest{" +
                "hq=" + Arrays.toString(hq) +
                ", hqsx=" + Arrays.toString(hqsx) +
                ", lq=" + lq +
                '}';
    }
}
