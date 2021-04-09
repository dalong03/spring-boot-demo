package test;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastJsonTest {

	@Test
	public void arraySer() {
		String arrayStr = "[[\"600\",\"57\"],[\"660\",\"196\"],[\"720\",\"426\"],[\"840\",\"263\"],[\"900\",\"22\"],[\"1020\",\"93\"],[\"1080\",\"1730\"],[\"1140\",\"1645\"],[\"1200\",\"1561\"],[\"1260\",\"119\"]]";
		List<Object> datas = JSONArray.parseArray(arrayStr, Object.class);
		System.out.println(datas);
	}
}
