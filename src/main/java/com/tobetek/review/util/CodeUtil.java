package com.tobetek.review.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by 17090056 on 2018/1/2.
 */
public class CodeUtil {
	public static String basePath = "G:\\Test\\ssm\\src\\main\\";
	public static String sqlpath = "sql";

	public static List<String> list = new ArrayList<String>();
	
    public static void main(String[] args) {
    	
        String url = "http://review.suning.com/ajax/review_lists/general-000000000154722868-0000000000-total-2-default-20-----reviewList.htm?callback=reviewList";
        String result = HttpUtil.sendGetString(url);
        int from = result.indexOf("(") + 1;
        int to = result.lastIndexOf(")");
        JSONObject json = JSONObject.fromObject(result.substring(from, to) );
        JSONArray arr = json.getJSONArray("commodityReviews");
        String path = basePath + "java\\com\\tobetek\\review\\entity\\";
        CodeUtil.createEntity(arr.getJSONObject(0), path, "Demo", "Review", null);

        String path2 = basePath + sqlpath + "\\";
        sumFiles(path2);
        
        String daoPath = basePath + "java\\com\\tobetek\\review\\dao\\";
        createDao(daoPath, "Demo");
        String servicePath = basePath + "java\\com\\tobetek\\review\\service\\";
        createService(servicePath, "Demo");
        
        String mapperPath = basePath + "resources\\mapper\\";
        createMapper(mapperPath, "Demo", "com.tobetek.review.entity.");
    }

	private static void createMapper(String path, String demo, String classSub) {
		String exampleCo1 = "<if test=\"id != null\" >\r\n\t\t\t\t";
		String exampleCo2 = "id,\r\n\t\t\t</if>";
		
		String exampleVa = "<if test=\"id != null\" >\r\n\t\t\t\t#{id,jdbcType=INTEGER},\r\n\t\t\t</if>";
		String fix = "Dao.xml";
		String daoStr = FileUtil.read2Str(path + demo + fix);
        for(String tmp : list) {
        	String str = daoStr.replace(demo, tmp).replace(getSplitName(demo, "_"), getSplitName(tmp, "_"));
        	Class<?> clazz = null;
        	try {
				clazz = Class.forName(classSub + tmp);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	StringBuilder comlumnStr = new StringBuilder();
        	StringBuilder valueStr = new StringBuilder();
        	for( Field f : clazz.getDeclaredFields() ){
        		
        		String modStr = Modifier.toString(f.getModifiers());
        		if(f.getName().equals("id") || modStr.contains("final") || modStr.contains("static")) {
        			continue;
        		}
        		Column anno = f.getAnnotation(Column.class);
        		if(null == anno) {
        			continue;
        		}
    			String tmpCo1 = exampleCo1;
    			String tmpCo2 = exampleCo2;
    			String tmpVo = exampleVa;
        		switch(f.getType().getSimpleName()){
        		case "String":
        			tmpVo = tmpVo.replace("INTEGER", "VARCHAR");
        		case "long" :
        			tmpCo1 = tmpCo1.replace("id", f.getName());
        			tmpCo2 = tmpCo2.replace("id", anno.name());
        			tmpVo = tmpVo.replace("id", f.getName());
        			comlumnStr.append(tmpCo1+tmpCo2);
        			valueStr.append(tmpVo);
        			break;
    			default:
        		}
        	}
			str = str.replace("<!-- columns -->", comlumnStr.toString())
					.replace("<!-- values -->", valueStr.toString());
        	FileUtil.write2File(str, path + tmp + fix);
        }
	}

	private static void createDao(String path, String demo) {
		String fix = "Dao.java";
		String daoStr = FileUtil.read2Str(path + demo + fix);
        for(String tmp : list) {
        	String str = daoStr.replace(demo, tmp);
        	FileUtil.write2File(str, path + tmp + fix);
        }
	}

    private static void createService(String path, String demo) {
		String fix = "Service.java";
		String fixImpl = "ServiceImpl.java";
		String serviceStr = FileUtil.read2Str(path + demo + fix);
		String implStr = FileUtil.read2Str(path + "impl\\" + demo + fixImpl);
        for(String tmp : list) {
        	String str = serviceStr.replace(demo, tmp).replace(demo.toLowerCase(), tmp.toLowerCase());
        	FileUtil.write2File(str, path + tmp + fix);
        	String str2 = implStr.replace(demo, tmp).replace(demo.toLowerCase(), tmp.toLowerCase());
        	FileUtil.write2File(str2, path + "impl\\" + tmp + fixImpl);
        }
	}

	/**
     * 获取对象字段并生成相应的column
     * 要求文件目录java与sql在同一个目录下
     * 运行之后，需要加上entity的getter及setter方法
     * @param jobj
     * @param path
     * @param oldName
     * @param newName
     */
    public static void createEntity(JSONObject jobj, String path, String oldName, String newName, String subName) {
    	
    	list.add(newName);
        StringBuilder toColumns = new StringBuilder();
        StringBuilder toStrings = new StringBuilder();
        StringBuilder sqlStr = new StringBuilder();
        for(Object key : jobj.keySet()) {
            Object obj = jobj.get(key);
            String tmpName = firstCodeUpper(key.toString());
            if( obj instanceof JSONArray ) {
                JSONArray tmpArr = (JSONArray) obj;
                if(tmpArr.size() == 0) {
                    continue;
                }
                createEntity(tmpArr.getJSONObject(0), path, oldName, tmpName, newName);
//                key += "List";
                toColumns.append("\tprivate List<").append(tmpName).append("> ").append(key).append(";\r\n");
            } else if( obj instanceof JSONObject ) {
                toColumns.append("\tprivate ").append(tmpName).append(" ").append(key).append(";\r\n");
            	toColumns.append("\t@Column(name=\"").append(getSplitName(key.toString(), "_")).append("_id").append("\")\r\n");//增加字段注解
                toColumns.append("\tprivate long ").append(key).append("Id").append(";\r\n");
                sqlStr.append("\r\n\t`").append(getSplitName(key.toString(), "_")).append("_id").append("` bigint(20) DEFAULT 0,");
                createEntity( (JSONObject) obj, path, oldName, tmpName, null);
//                sqlStr.append("\r\n\t`").append(getSplitName(key.toString(), "_")).append("` bigint(20) DEFAULT 0,");
            } else {//@Column(name="book_id")
            	toColumns.append("\t@Column(name=\"").append(getSplitName(key.toString(), "_")).append("\")\r\n");//增加字段注解
                toColumns.append("\tprivate String ").append(key).append(";\r\n\r\n");//增加字段
                sqlStr.append("\r\n\t`").append(getSplitName(key.toString(), "_"));
                if(!key.toString().equals("content")) {
                	sqlStr.append("` varchar(255) DEFAULT NULL,");
                } else {
                	sqlStr.append("` varchar(1023) DEFAULT NULL,");
                }
            }
            toStrings.append("\" +\r\n\t\t\t\", ").append(key).append("=\'\" + ").append(key).append(" + \"\'");
        }
        if(subName != null) {
        	toColumns.append("\t@Column(name=\"").append(getSplitName(subName, "_")).append("\")\r\n");//增加字段注解
        	toColumns.append("\tprivate long ").append( firstCodeLower(subName) ).append(";\r\n\r\n");//增加字段
        	sqlStr.append("\r\n\t`").append(getSplitName(subName, "_")).append("` bigint(20) DEFAULT 0,");
            toStrings.append("\" +\r\n\t\t\t\", ").append(subName.toLowerCase()).append("=\'\" + ").append(subName.toLowerCase()).append(" + \"\'");
        }
        toStrings.append("\" +\r\n\t\t\t\"").deleteCharAt(toStrings.indexOf(","));
        String fix = ".java";
        String demo = path + oldName + fix;
        String target = path + newName + fix;
        String content = FileUtil.read2Str(demo)
        		.replace(getSplitName(oldName,"_"), getSplitName(newName,"_"))
        		.replace("//columns",toColumns)
        		.replace(oldName, newName)
        		.replace("//toString",toStrings.toString());
        FileUtil.write2File(content, target);//write 2 java file

        path = target.substring(0,target.indexOf("\\java\\")) + "\\" + sqlpath + "\\";
        fix = "_mysql.sql";
        demo = path + oldName + fix;
        target = path + newName + fix;
        content = FileUtil.read2Str(demo)
        		.replace(getSplitName(oldName,"_"), getSplitName(newName,"_"))
        		.replace("-- here",sqlStr);
        FileUtil.write2File(content,target);
    }

    /**
     * 汇总sql语句
     * @param path
     * @return
     */
    public static boolean sumFiles(String path) {
        boolean flag = false;
        File f = new File(path);
        if(!f.isDirectory()) {
            return flag;
        }
        StringBuilder sb = new StringBuilder();
        for(File tmp : f.listFiles()) {
            if( !tmp.getName().endsWith(".sql") ||
                    tmp.getName().equals("sum_mysql.sql")) {
                continue;
            }
            sb.append(FileUtil.read2Str(tmp)).append("\r\n");
        }
        String targetPath = path;
        if(!path.endsWith("\\")) {
            targetPath += "\\";
        }
        targetPath += "sum_mysql.sql";
        FileUtil.write2File(sb.toString(), targetPath);
        flag = true;
        return flag;
    }

    /**
     * 根据大写字符，用splitStr分割
     * @param str
     * @param splitStr
     * @return
     */
    public static String getSplitName(String str, String splitStr) {
        return StringUtil.getSplitName(str, splitStr);
    }

    /**
     * 首写字符大写
     * @param str
     * @return
     */
    public static String firstCodeUpper(String str) {
    	return StringUtil.firstCodeUpper(str);
    }

    /**
     * 首写字符小写
     * @param str
     * @return
     */
    public static String firstCodeLower(String str) {
    	return StringUtil.firstCodeLower(str);
    }
}
