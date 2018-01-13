package com.tobetek.review.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 请求URL地址，得到返回的字符串结果
     * @param url
     * @return
     */
    public static String sendGetString(String url) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        CloseableHttpClient client = null;
        try {
//        	log.info("------1-----"+url);
        	client = HttpClients.createDefault();
//        	log.info("------2-----createDefault");
        	InputStream in = client.execute(new HttpGet(url)).getEntity().getContent();
//        	log.info("------3-----client.execute");
            br = new BufferedReader(new InputStreamReader(in, Charset.forName("utf-8")));
            String tmp;
            while( (tmp = br.readLine()) != null ) {
                sb.append(tmp).append("\\r\\n");
            }
        } catch(IOException e) {
            log.error(e.getMessage());
        } finally {
            closeResources(br);
            closeResources(client);
        }
        return sb.toString();
    }
    /**
     * 关闭资源
     * @param closeable
     * @param client 
     * @return
     */
    public static boolean closeResources(Closeable closeable) {
//    	log.info("------4-closeResources-----"+closeable.getClass().getSimpleName());
        boolean flag = false;
        try {
            if(null != closeable) {
                closeable.close();
            }
            flag = true;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return flag;
    }
}
