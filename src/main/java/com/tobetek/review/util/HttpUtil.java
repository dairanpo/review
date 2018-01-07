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

    private static CloseableHttpClient httpClient = null;
    
    public static synchronized void init() {
    	if(null == httpClient) {
    		httpClient = HttpClients.createDefault();
    	}
    }
    /**
     * 请求URL地址，得到返回的结果内容对象
     * @param url
     * @return
     * @throws IOException
     */
    public static CloseableHttpResponse sendGetResponse(String url) throws IOException {
    	init();
        return httpClient.execute(new HttpGet(url));
    }

    /**
     * 请求URL地址，得到返回的结果内容对象
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpEntity sendGetEntity(String url) throws IOException {
        return sendGetResponse(url).getEntity();
    }

    /**
     * 请求URL地址，得到返回的字符串输出流
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream sendGetInputStream(String url) throws IOException {
        return sendGetEntity(url).getContent();
    }
    /**
     * 请求URL地址，得到返回的字符串结果
     * @param url
     * @return
     */
    public static String sendGetString(String url) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(sendGetInputStream(url), Charset.forName("utf-8")));
            String tmp;
            while( (tmp = br.readLine()) != null ) {
                sb.append(tmp).append("\\r\\n");
            }
        } catch(IOException e) {
            log.error(e.getMessage());
        } finally {
            closeResources(br);
        }
        return sb.toString();
    }
    /**
     * 关闭资源
     * @param closeable
     * @return
     */
    public static boolean closeResources(Closeable closeable) {
        boolean flag = false;
        if(null == closeable) {
            return flag;
        }
        try {
            closeable.close();
            flag = true;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return flag;
    }
}
