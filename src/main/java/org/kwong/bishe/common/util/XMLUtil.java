package org.kwong.bishe.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml解析工具
 * @author
 */
public class XMLUtil {
/*
	public static void main1(String[] args) {
		String xml = "<a1>" +
				"<a2><name>小二</name><age>9999</age></a2>" +
				"<a2><name>小三</name><age>7894</age></a2>" +
				"<a21><a22>sssss</a22></a21></a1>";
		Map<String,String> map = toMap(xml, "/a1/a21");
		System.out.println(map);
		System.out.println(toList(xml, "/a1"));
	}
	*/
	public static void main2(String[] args) throws IOException {
		
//		String xml = FileUtils.readFileToString(new File("E:\\workspace\\cloudmeeting\\trunk\\01-SourceCode\\backstage\\yzt_v1.0\\src\\cn\\qtone\\yzt\\service\\ext\\gdxxt\\QRY_CLASS_TEACHER.xml"),"UTF-8");
//		List<Map<String,String>> list = toList(xml, "/Relations");
//		System.out.println(list);
	}

	public static Document toDocument(String xml) {
		try {
			SAXReader saxReader = new SAXReader();
			return saxReader.read(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Element selectNode(Element root,String path ){
		String p = path.startsWith("/")? path.substring(1) : path;
		p = p.endsWith("/")? p.substring(0,p.length()-1) : p;
		
		if(p.length() == 0)
			return null;
		String nodePah[] = p.split("/");
		Element ele = root;
		for (int i = 1, len = nodePah.length; i < len; i++)
			ele = ele.element(nodePah[i]);
		return ele;
	}

	/**
	 * 将选择节点的子节点转为map
	 * @param xml	将要解析的XML
	 * @param path   /root/student 选择节点的路径
	 * @return
	 */
	public static Map<String, String> toMap(String xml, String path) {
		Document doc = toDocument(xml);
		Element ele = selectNode(doc.getRootElement(),path);
		return ele == null ? null :toMap(ele);
	}
	
	/**
	 * 将子节点转成map,子节点作为key
	 * @param ele
	 * @return
	 */
	public static Map<String, String> toMap(Element ele){
		if(ele == null)
			return null;
		Iterator ite = ele.elementIterator();
		Map<String,String> map = new HashMap<String,String>();
		while (ite.hasNext()) {
			Element e  = (Element)ite.next();
			if(e.elements().size() > 0){
				map.put(e.getName(), StringUtils.trim(e.asXML()));
			}else{
				map.put(e.getName(), StringUtils.trim(e.getText()));
			}
		}
		return map;
	}

	/**
	 * 将选择节点的子节点转为List
	 * @param xml	将要解析的XML
	 * @param path   /root/student 选择节点的路径
	 * @return
	 */
	public static List<Map<String, String>> toList(String xml, String path){
		Document doc = toDocument(xml);
		Element ele = selectNode(doc.getRootElement(),path);
		if(ele == null)
			return null;
		Iterator ite = ele.elementIterator();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		while (ite.hasNext()) {
			list.add(toMap((Element)ite.next()));
		}
		return list;
	}
	
	
	public static String[] toArray(String xml,String path){
		Document doc = toDocument(xml);
		Element ele = selectNode(doc.getRootElement(),path);
		Iterator ite = ele.elementIterator();
		List<String> list = new ArrayList<String>();
		while (ite.hasNext()) {
			Element e  = (Element)ite.next();
			list.add(StringUtils.trim(e.elements().size() > 0 ? e.asXML() : e.getText()));
		}
		return list.size() > 0 ? list.toArray(new String[list.size()]) : null;
	}
	
	public static void main(String[] args) {
		String xml="<MSG_BODY>"
		+"<PageNo>2</PageNo>"
		+"<PageCount>9</PageCount>"
		+"<SchoolIds>"
		+"<SchoolId>49507</SchoolId>"
		+"<SchoolId>49511</SchoolId>"
		+"<SchoolId>49515</SchoolId>"
		+"<SchoolId>49516</SchoolId>"
		+"<SchoolId>49517</SchoolId>"
		+"<SchoolId>49519</SchoolId>"
		+"<SchoolId>49520</SchoolId>"
		+"<SchoolId>49523</SchoolId>"
		+"<SchoolId>49527</SchoolId>"
		+"<SchoolId>49530</SchoolId>"
		+"</SchoolIds>"
		+"</MSG_BODY>";
		
		System.out.println(Arrays.toString(XMLUtil.toArray(xml, "/MSG_BODY/SchoolIds")));
	}
}
