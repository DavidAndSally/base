package com.jd.pop.base.gbq;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixx-f on 2018/8/29.
 */
public class RecordControllerTest {

    public static void main(String[] args) throws IOException,
            URISyntaxException {
        testGetTableData();
    }

    public static void testGetTableData() throws URISyntaxException,
            IOException {
        //md5  dfda7a2f92894aa0b5e95ec2be0f3be2
        String uuid = "7f1702f0-c5d1-4bef-8055-71ac0c467bf0";
        String fullPath = "?ۺ?¥????Ԥ????.GSP";


        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("dataBaseName", "BusinessDB");


        List<Map> tableList = new ArrayList<>();
        Map<String, Object> tableMap = new HashMap<>();
        tableMap.put("tableName", "ResClassifyTemplate");
        List<String> fieldList = new ArrayList<>();
        fieldList.add("ResClassifyTemplateID");
        fieldList.add("Description");
        fieldList.add("CostTypeSelection");
        tableMap.put("fields", fieldList);
        tableList.add(tableMap);
        tableMap = new HashMap<>();
        tableMap.put("tableName", "ResClassifyTemplate.CostTypeSelection");
        fieldList = new ArrayList<>();
        fieldList.add("CostTypeID");
        tableMap.put("fields", fieldList);
        tableList.add(tableMap);

        map.put("tables", tableList);
        list.add(map);
        Gson gson = new Gson();
        String jsonParams = gson.toJson(list);

        HttpPost oHttpPost = new HttpPost("http://gbq5test.glodon.com/gbqfileparser/record/getTableData");
        URI uri = new URIBuilder(oHttpPost.getURI()).
                addParameter("uuid", uuid).
                addParameter("fullPath", fullPath).build();
        HttpEntity bodyEntity = new ByteArrayEntity(jsonParams.getBytes("UTF-8"));
        oHttpPost.setEntity(bodyEntity);
        oHttpPost.setURI(uri);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpClient.execute(oHttpPost)) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                System.out.println(result);
//                ResultModel resultModel = gson.fromJson(result, ResultModel.class);
//                assertTrue(resultModel.isResult());
            }
        }

    }
}
