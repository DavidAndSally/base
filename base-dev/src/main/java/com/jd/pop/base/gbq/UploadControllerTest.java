package com.jd.pop.base.gbq;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 上传服务测试
 * Created by lixx-f on 2018/8/21.
 */
public class UploadControllerTest {

    public static void main(String[] args) throws IOException, URISyntaxException {
//        uploadProjectTest();
        uploadSingleProjectTest();
    }

    public static void uploadProjectTest() throws URISyntaxException, IOException {
        String md5 = "";
//        File file = new File("E:\\dev\\审计\\文件对比样例文件\\综合楼土建预算书.GBQ4");
//        File file = new File("E:\\dev\\审计\\文件对比样例文件\\03示例工程-清单计价-投标文件.GBQ5");
        InputStream inputStream = new FileInputStream("E:\\dev" +
                "\\审计\\文件对比样例文件\\03示例工程-清单计价-投标文件\\GSPFiles\\GBQ_2.GSP");


        HttpPost httpPost = new HttpPost("http://gbq5test.glodon.com/gbqfileparser/upload/project");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addBinaryBody("file", inputStream, ContentType.create(
                "multipart/form-data"), "GBQ_2.GSP");
//        builder.addBinaryBody("file", file);
        HttpEntity httpEntity = builder.build();
        httpPost.setEntity(httpEntity);

        URI uri = new URIBuilder(httpPost.getURI()).
                addParameter("md5", md5).build();
        httpPost.setURI(uri);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                System.out.println(result);
//                ResultModel resultModel = gson.fromJson(result, ResultModel.class);
//                assertTrue(resultModel.isResult());
            }
        }

    }

    public static void uploadSingleProjectTest() throws URISyntaxException, IOException {
        String md5 = "";
//        File file = new File("C:\\Users\\lixx-f\\Desktop\\test.GBQ5");
        InputStream inputStream = new FileInputStream("E:\\dev" +
                "\\审计\\文件对比样例文件\\03示例工程-清单计价-投标文件\\GSPFiles\\GBQ_2.GSP");


        HttpPost httpPost = new HttpPost("http://gbq5test.glodon.com/gbqfileparser/upload/project");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addBinaryBody("file", inputStream, ContentType.create("multipart/form-data"), "GBQ_1.GSP");
        //builder.addBinaryBody("file", file);
        HttpEntity httpEntity = builder.build();
        httpPost.setEntity(httpEntity);

        URI uri = new URIBuilder(httpPost.getURI()).
                addParameter("md5", md5).
                addParameter("fileType", "Constant.SINGLE_FILE").build();
        httpPost.setURI(uri);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                System.out.println(result);
//                ResultModel resultModel = gson.fromJson(result, ResultModel.class);
//                assertTrue(resultModel.isResult());
            }
        }

    }


}
