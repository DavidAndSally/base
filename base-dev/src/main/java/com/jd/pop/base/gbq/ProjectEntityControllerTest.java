package com.jd.pop.base.gbq;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lixx-f on 2018/8/22.
 */
@SuppressWarnings("Duplicates")
public class ProjectEntityControllerTest {

    public static void main(String[] args) throws IOException, URISyntaxException {
        getProjectEntity();
    }

    private static void getProjectEntity() throws URISyntaxException, IOException {
//        String uuid = "4eec03ea-292b-4b56-9c26-3ba627163203";
        String uuid = "7f1702f0-c5d1-4bef-8055-71ac0c467bf0";
//        String uuid = "17f4a75a-0a60-4a30-a081-b56d48af8be8";

        HttpGet httpGet = new HttpGet("http://gbq5test.glodon.com/gbqfileparser/project/get");
        URI uri = new URIBuilder(httpGet.getURI()).
                addParameter("uuid", uuid).build();
        httpGet.setURI(uri);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                System.out.println(result);
//                ResultModel resultModel = gson.fromJson(result, ResultModel.class);
//                assertTrue(resultModel.isResult());
                //得到结果
                //{"result":true,"errCode":"0","errMsg":"success","data":{"uuid":"68545d82-3b09-4642-9ffd-3a8a86f5abb3","md5":"036e1e54c4b3f8219e23d938ad72427b","fileEntities":[{"fullPath":"HistorySpecData.GSP"},{"fullPath":"GSPFiles/Bid.GSP"},{"fullPath":"GSPFiles/Comment.json"},{"fullPath":"GSPFiles/Config.GSP"},{"fullPath":"GSPFiles/Config.ini"},{"fullPath":"GSPFiles/FuncData.GSP"},{"fullPath":"GSPFiles/GBQ_2.GSP"},{"fullPath":"GSPFiles/GBQ_3.GSP"},{"fullPath":"Index/IndexTpl.GSP"},{"fullPath":"Index/Q5IndexModel.GSP"},{"fullPath":"Overview.ini"}]}

                //GBQ4得到结果：
                //{"result":true,"errCode":"0","errMsg":"success","data":{"uuid":"16c29f1b-c569-4e84-936f-e36cedd94c63","md5":"dfda7a2f92894aa0b5e95ec2be0f3be2","fileEntities":[{"fullPath":"?ۺ?¥????Ԥ????.GSP"},{"fullPath":"Overview.ini"}]}}
            }
        }

    }


}
