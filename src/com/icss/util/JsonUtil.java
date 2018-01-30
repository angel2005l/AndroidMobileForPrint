package com.icss.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JsonUtil {

	/**
	 * JSON格式 处理
	 */
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * json变为list
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> json2List(String json, Class<T> clazz) {
		try {
			JavaType javaType = getCollectionType(ArrayList.class, clazz);
			return (List<T>) mapper.readValue(json, javaType);
		} catch (JsonParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String json) {
		if ("" == json || json.length() == 0) {
			return null;
		}
		// JavaType javaType = getCollectionType(HashMap.class, clazz);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	// @SuppressWarnings("unchecked")
	// public static <T> T parseJson(String json, String className){
	// try {
	// return (T) parseJson(json, Class.forName(className).getClass());
	// } catch (ClassNotFoundException e) {
	// // TODO 自动生成的 catch 块
	// e.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * json变对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseJson(String json, Class<T> clazz) {
		try {
			return (T) mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	private static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}
}
