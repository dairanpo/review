package com.tobetek.review.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

public class JsonUtil {

    /**
     * 功能描述: <br>
     * 将json字符串转换成对象
     *
     * @param json
     * @param requiredType
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String json, Class<T> requiredType) {
//        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { YYYY_MM_DD }));
        return (T) JSONObject.toBean(JSONObject.fromObject(json), requiredType);
    }
    /**
     * 将JSON转换成List
     * @param <T>
     * @param json
     * @param valueClz Collection中存放的对象的Class
     * @return
     */
    /*public static <T> List<?> json2List(String json, Class<T> valueClz) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        return (List<?>) JSONArray.toCollection(jsonArray, valueClz);
    }*/

    public static Map<String, String> json2Map(String json) {
        Map<String, String> map = new HashMap<String, String>();
//        TODO
        return map;
    }

    /**
     * get json str to bean
     * unable to recognize list and map
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
	public static <T> T json2Entity(String json, Class<T> clazz) {
        T t = null;
        try {
            JSONObject jo = JSONObject.fromObject(json);
            t = clazz.newInstance();
            for(Field f : clazz.getDeclaredFields()) {
                if(null == f.getName() || "".equals(f.getName()) || "serialVersionUID".equals(f.getName())
                        || Modifier.toString(f.getModifiers()).contains("static") || f.getType().getSimpleName().equals("long")
                        || Modifier.toString(f.getModifiers()).contains("final") || !jo.containsKey(f.getName())) {
                    continue;
                }
                String content = jo.getString(f.getName());
                if(null == content || "".equals(content)) {
                    continue;
                }
                Object obj;
                if(f.getType() == String.class) {
                    obj = content;
                } else if(f.getType() == List.class){
                    Class<?> tmpClazz = Class.forName("com.tobetek.review.entity."+StringUtil.firstCodeUpper(f.getName()));
                    obj = json2List(content, tmpClazz);
                } else if(f.getType() == Map.class){
                    //TODO
                    obj = null;
                } else {
                    obj = json2Entity(content, f.getType());
                }
//                f.setAccessible(true);
//                f.set(t, obj);
                // 需要set方法
                BeanUtils.setProperty( t, f.getName(), obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    /**
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<?> json2List(String json, Class<T> clazz) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<T> list = new ArrayList<T>();
        for(int i=0; i < jsonArray.size(); i++) {
        	T t = json2Entity(jsonArray.getString(i), clazz);
        	list.add(t);
        }
        return list;
    }
}
