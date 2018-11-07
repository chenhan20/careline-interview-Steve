package com.careline.interview.test.mission2;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


@RestController
public class Mission2Controller {

	 public final ObjectMapper mapper = new ObjectMapper(); 

	
	@RequestMapping("/mission2/compute")
	@ResponseBody
	public Map<String,Object> compute(@RequestBody String data) {
		Map<String ,Object> map = new HashMap<>();

		ArrayList<Integer> numList = new ArrayList<>();
		BigDecimal sum = new BigDecimal(0);
		BigDecimal max = new BigDecimal(0);
		BigDecimal min = new BigDecimal(9999);
		BigDecimal avg = new BigDecimal(0);
		List<Integer> sorted_list = new ArrayList<>();
		try {
			Map<String ,Object> myObjects = mapper.readValue(data, new TypeReference<Map<String ,Object>>(){});
			numList = (ArrayList<Integer>) myObjects.get("numList");
			numList.sort(null);     //排序
			for(int i=0;i<numList.size();i++) {
				BigDecimal thisNumber = new BigDecimal(numList.get(i));
				sum = sum.add(thisNumber);  //加總
				max = max.max(thisNumber);	//最大
				min = min.min(thisNumber);	//最小
			}
			BigDecimal listSize = new BigDecimal(numList.size());
			avg = sum.divide(listSize,2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		map.put("sum",sum);
		map.put("max",max);
		map.put("min",min);
		map.put("avg",avg);
		map.put("sorted_list",numList);
        return map;
	}



	
}
