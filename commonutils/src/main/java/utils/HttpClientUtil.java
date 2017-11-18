package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
public class HttpClientUtil {

    /**
     * get请求
     *
     * @param url    请求地址
     * @param params map参数
     * @return json
     */
    public static JSONObject getJsonData(String url, Map<String, String> params) {
        JSONObject jsonObject = null;
        // 获取当前客户端对象
//        HttpClient httpClient = HttpClientUtil.createDefault();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(pairs);
            // 根据地址发送get请求
            HttpGet request = new HttpGet(builder.build());
            // 通过请求对象获取响应对象
            response = httpClient.execute(request);
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                // 返回json格式：
                jsonObject = JSON.parseObject(result);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     * post请求
     * @param url url
     * @param params map
     * @return json
     */
    public static JSONObject postJsonData(String url, Map<String, String> params) {
        JSONObject jsonObject = null;
        //写了两种获取httpClient的方式
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpClient httpClient = HttpClientUtil.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //拼接参数
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        CloseableHttpResponse response = null;

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
            response = httpClient.execute(httpPost);
            /**请求发送成功，并得到响应**/
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity);
                // 返回json格式：
                jsonObject = JSON.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonObject;
    }

    /**
     *      * post请求，参数为json字符串
     *       * @param url 请求地址
     *       * @param jsonString json字符串
     *       * @return 响应
     *
     */
    public static String postJson(String url, String jsonString) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            post.setEntity(new ByteArrayEntity(jsonString.getBytes("UTF-8")));
            response = httpClient.execute(post);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static CloseableHttpClient createDefault() {
        return HttpClientBuilder.create().build();
    }

    //"http://abc.gensee.com/integration/site/webcast/record/info?
    // loginName=admin@abc.com&password=20141006abctina&webcastId=62385";
    public static void main(String[] args) throws Exception{
        JSONObject object = new JSONObject();
        object.put("site_id",0);
        object.put("ad_id",0);
        JSONArray array = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("label","姓名");
        jsonObject.put("id","61900222000");
        jsonObject.put("value","");
        array.add(jsonObject);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("label","电话");
        jsonObject2.put("id","61900222001");
        jsonObject2.put("value","");
        array.add(jsonObject2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("label","提交");
        jsonObject3.put("id","61900222008");
        jsonObject3.put("value","");
        array.add(jsonObject3);
        object.put("data",array);
        String url = "http://talk.abc.com.cn/ajax/toutiaoPost.ashx";
        //talk.abc.com.cn/ajax/toutiaoPost.ashx
        HttpClientUtil.postJson(url, object.toJSONString());

    }
}
