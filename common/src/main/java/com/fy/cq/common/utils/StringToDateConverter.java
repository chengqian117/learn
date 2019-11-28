package com.fy.cq.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String value) {
		SimpleDateFormat sdf;
		Date date=new Date();
		try {
			if(value.matches("\\d{4}-\\d{2}-\\d{2}")){
				sdf=new SimpleDateFormat("yyyy-MM-dd");
				date=sdf.parse(value);
			}else if(value.matches("\\d{10,12}")){
				long l1=Long.parseLong(value+"000");
				date=new Date(l1);
			}else if(value.matches("\\d{13,}")){
				long l1=Long.parseLong(value);
				date=new Date(l1);
			}else{
				return date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
