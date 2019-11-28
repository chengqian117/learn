package com.fy.cq.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapJsonUtil {
	private final static  Logger logger=LoggerFactory.getLogger("MapJsonUtil");
	/**
	public static MapJson formatMapJsonByMap(Map<String,Object> map){
		List<RailwayLine> railwayLines;
		List<Station> stations;
		MapJson mapjson=new MapJson();
		try{
			 railwayLines=(List<RailwayLine>) map.get("railwayLines");
			 stations=(List<Station>) map.get("stations");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("将Map数据转换成MapJson对象时，获取数据转换格式失败");
			return  null;
		}
		
		if(null!=railwayLines&&railwayLines.size()>0){
			for(RailwayLine rl:railwayLines){
				Line line=new Line();
				Primary primary=new Primary();
				line.setCode(rl.getTrackLineCd());
				line.setName(rl.getTrackLineName());
				primary.setColor(rl.getDrawColor());
				line.setPrimary(primary);
				//获取站点数量，绘制地铁上下行线
				int sectionsNumber=railwayLines.size()*2;
				if(sectionsNumber==0){
					logger.info("对应地铁线，未获取到站台信息");
					break;
				}else{
					List<Section> sections=new ArrayList<Section>();
					for(int i=0;i<sectionsNumber;i++){
						
					}
				}
			}
		}else{
			return  null;
		}
		
		return  mapjson;
	}
	 **/
}
