package org.kwong.bishe.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * json转 hashmap
 * @author
 *
 */
public class JsonUtil {
	
	public static void JsonToHashMap(JSONObject jsonData, Map<String, Object> rstList,
			String... params) {
		try {
			for (Iterator<String> keyStr = jsonData.keys(); keyStr.hasNext();) {

				String key1 = keyStr.next().trim();
				if (jsonData.get(key1) instanceof JSONObject) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					JsonToHashMap((JSONObject) jsonData.get(key1), mapObj, params);
					rstList.put(key1, mapObj);
					continue;
				}
				if (jsonData.get(key1) instanceof JSONArray) {
					ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();

					JSONArrayToHashMap((JSONArray) jsonData.get(key1), arrayList, params);
					rstList.put(key1, arrayList);
					continue;
				}
				JsonToHashMap(key1, jsonData.get(key1), rstList);
			}
			// 追加字段
			if (params != null && params.length == 2) {
				rstList.put(params[0], params[1]);
			}
			if (params != null && params.length == 4) {
				rstList.put(params[0], params[1]);
				rstList.put(params[2], params[3]);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public static void JSONArrayToHashMap(JSONArray jsonarray, List<Map<String, Object>> rstList,
			String... params) {
		try {
			for (int i = 0; i < jsonarray.length(); i++) {
				if (jsonarray.get(i) instanceof JSONObject) {

					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					JsonToHashMap((JSONObject) jsonarray.get(i), mapObj, params);
					rstList.add(mapObj);
					continue;
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

    public static void JsonToHashMap(String key, Object value, Map<String, Object> rstList) {  
       if (value instanceof String) {  
            rstList.put(key, (String) value);  
            return;  
        }  
        rstList.put(key, value);  
    }  
}
