package com.jd.pop.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsfUtil {
    public static final String DEFAULT_CHARSET = "UTF-8";// 默认的编码格式
    public static void main(String[] args) throws ParserException {
        String cookie = "shshshfpa=a24a3975-8aa5-d45f-58e8-d7aea442e3be-1528730247; shshshfpb=0f667d742756a3a341d21b59c84924998895ac8754b06e5535b1e92851; ipLocation=%u5317%u4eac; cn=0; __jdu=15287302442161873631619; pinId=pVvgI0wl9Ki-Klm7d8w4zrV9-x-f3wj7; _tp=fCYv2MPeQdABLlqjCMCd7aRKMLUKk5GHjsTIYTUImWM%3D; _pst=test_pop_weidian; PCSYCityID=258; areaId=5; ipLoc-djd=5-148-3187-0; user-key=7c68c539-4009-4c71-a4ee-bac693605772; cookie.jcloud.com=\"userId=378171,pin=duanyimeng,nick=   ,created=1539746604905\"; pin=test_pop_weidian; unick=%E7%9B%B4%E6%92%AD%E9%97%B4%E6%88%BF%E7%AE%A1; ceshi3.com=000; unpl=V2_ZzNtbUtRQEclCxNRfBpaAWIAElQRXhQdcV1DXCgaWVAyUEVcclRCFXwURlVnGl8UZwAZXkRcQBxFCEJkexhdBGYKEV5EX3MlfQAoVDYZMgYJAF9tQVdzFUUIQldyH14EZAYXXkJQQxxzCkFWex5cDVczEltyVEsXcQ5EUX8aVA1XBhVaSlBEHHcLdlVLGGwEV0J8W0pfRxJzRUZQeBBaB2YAF1hBV0QVfA5EU3kZWwVvMxNtQQ%3d%3d; __jdv=225342465|kong|t_1000082279_|jingfen|872ca2d462754218b8f95d49b24ddbf0|1540182440701; retina=1; cid=9; webp=1; mba_muid=15287302442161873631619; wq_addr=766564764%7C1_2810_51081_0%7C%u5317%u4EAC_%u5927%u5174%u533A_%u4EA6%u5E84%u7ECF%u6D4E%u5F00%u53D1%u533A_%7C%u5317%u4EAC%u5927%u5174%u533A%u4EA6%u5E84%u7ECF%u6D4E%u5F00%u53D1%u533A%u6D4B%u8BD53%7C116.503%2C39.7739; jdAddrId=1_2810_51081_0; jdAddrName=%u5317%u4EAC_%u5927%u5174%u533A_%u4EA6%u5E84%u7ECF%u6D4E%u5F00%u53D1%u533A_; mitemAddrId=1_2810_51081_0; mitemAddrName=%u5317%u4EAC%u5927%u5174%u533A%u4EA6%u5E84%u7ECF%u6D4E%u5F00%u53D1%u533A%u6D4B%u8BD53; PPRD_P=UUID.15287302442161873631619; sc_width=1680; wq_logid=1540208812.2065102694; __wga=1540208805067.1540208630240.1540208630240.1540208630240.2.1; TrackID=1w8R_lRbr7NsgV2nS7npsL387AZYQPnnJ5WVUw2BnYx071Ifid9JGhSchDjzw6nBMi8fkcFw818E3lxPXxcdN6Q14IzPBN3JyqqZZIh20tAEJ2aEDLLwP7NSOrUJFPtT9; jd.erp.lang=zh_CN; erp1.jd.com=5227077FD4568815892E6A27232797C6801A4928A3E45D892E6C9B2C70C81DD723B2D6B3CC85A28A0A447A3501614600D2F699B7D75FC306265C7CACA58273EEC02B55BF20840CCBDAEC9F4BAA892507; 3AB9D23F7A4B3C9B=DGEZ6YEZWSOY3ZC4CYDSNQMMKHQCSXUXVYBKH4JZPGCSW3ZFYDZLOYN74HOEZVFACQYHNCROS4OEPUK4OCMRQZ5IVI; shshshfp=9a37db760a40f2bab4892b0088efb34c; _gcl_au=1.1.1206936759.1540271630; wlfstk_smdl=x4fy1rmajmj6r7bbx9jg7h9qyacfj3gg; sso.jd.com=91bf819400574f609ab91aa52753f800; __jda=225342465.15287302442161873631619.1528730244.1540284494.1540346381.198; __jdc=225342465; _cloudberry_login=B7M7RZHCOBUKXLQJVKQ5FLJRGLFUON2SVWBPROFDS3QRUCHFZH7RJLW5LVMSIVU6POFNA54D4BR4MW7SD7LRPEZ663657N4HXQOAQ2FGG5DYD6VNRMNLXQ67TAW7UHFY";

//        List<String> myAppNames = getMyAppNames(cookie);
//        System.out.println(myAppNames);
        String[] apis =  new String[]{
                "com.jd.pop.crm.client.service.ShopGiftActivityJsfService"
//                "com.jd.gms.crs.service.ProductService",
//                "com.jd.gms.crs.service.MapService",
//                "com.jd.gms.crs.service.BookVideoBasicFieldService",
//                "com.jd.gms.crs.service.TradeService",
//                "com.jd.gms.crs.service.customize.ProductCustomService",
//                "com.jd.gms.crs.service.ProductBigFieldService",
//                "com.jd.gms.crs.service.ImageService",
//                "com.jd.gms.crs.service.BookVideoBigFieldService",
//                "com.jd.gms.crs.service.ProductNewAttributeService",
//                "com.jd.gms.crs.service.ProductAssemblyService"
        };
        for (String api:apis){
            findApps(cookie, api);
        }


    }

    private static void findApps(String cookie, String api) throws ParserException {
        /**
         * 查询api的ID
         */
        String searchApiUrl = "http://old.jsf.jd.com/iface/manage/search/new?initdata=false";
        String r1 = postJson(searchApiUrl, "interfaceName=" + api,cookie);
        System.out.println("##"+searchApiUrl);

        JSONObject parse = (JSONObject) JSONObject.parse(r1);
        JSONObject content = (JSONObject) parse.get("content");
        JSONObject data = (JSONObject) content.get("data");
        JSONArray list = (JSONArray) data.get("data");
        if(list.size()<=0){
            System.out.println("接口不存在");
        }

        JSONObject o = (JSONObject) list.get(0);
        Integer id = (Integer)o.get("id");
        String interfaceName = (String) o.get("interfaceName");
        String url = "http://old.jsf.jd.com/iface/manage/detail/"+id+"?name="+interfaceName+"&queryIface="+interfaceName+"&queryShowType=1&pageIndex=0&pageSize=10";
//        System.out.println(url);
        /**
         * 查询制定接口调用的应用
         */
//            String url = "http://old.jsf.jd.com/iface/manage/detail/106584?name=com.jd.pop.z.center.api.ZActivityWriteService&queryIface=com.jd.pop.z.center.api.ZActivityWriteService&queryShowType=1&pageIndex=0&pageSize=10";
        String s = doGet(url, cookie);
        String html = parseHtml(s);
        Set<String> appNames = parseAppNames(html);
        //我所有的appnames
        List<String> myAppNames = getMyAppNames(cookie);

        List<String> tempAppNames = new ArrayList<String>(myAppNames);
//        Collections.copy(tempAppNames,myAppNames);
        tempAppNames.removeAll(appNames);
//        System.out.println(tempAppNames);
        myAppNames.removeAll(tempAppNames);
        System.out.println("api:"+api);
        System.out.println("受影响app:"+myAppNames);
    }

    private static List<String> getMyAppNames(String cookie) {
        /**
         * 查询我的应用
         */
        String url1 = "http://old.jsf.jd.com/app/app/search";
        //查询1000条，如果应用大于1000个时，可能有丢失
        String content1 = postJson(url1, "start=0&length=1000",cookie);
        return parseMyAppNames(content1);
    }

    private static List<String> parseMyAppNames(String s1) {
        JSONObject parse = (JSONObject) JSONObject.parse(s1);
        JSONObject content = (JSONObject) parse.get("content");
        JSONObject data = (JSONObject) content.get("data");
        JSONArray list = (JSONArray) data.get("data");
        List<String> myAppNames = new ArrayList<>();
        for (int i = 0; i < list.size() ; i++) {
            JSONObject o = (JSONObject) list.get(i);
//            System.out.println(o.get("id")+":"+o.get("appName"));
            myAppNames.add((String) o.get("appName"));
        }
        return myAppNames;
    }

    private static String parseHtml(String s) {
        int i = s.indexOf("id=\"appName\"");
        String substring = s.substring(0,i);
        int i1 = substring.lastIndexOf("<");
        String substring1 = s.substring(i1);
        int i2 = substring1.indexOf("</select>")+"</select>".length();
        String substring2 = substring1.substring(0, i2);
//        System.out.println(substring2);
        return substring2;
    }

    private static Set<String> parseAppNames(String substring2) throws ParserException {
        Parser parser = Parser.createParser(substring2, DEFAULT_CHARSET);
        NodeIterator elements = parser.elements();
        Set appNames = new HashSet();
        //遍历select
        while (elements.hasMoreNodes()){
            Node node = elements.nextNode();
            NodeList children = node.getChildren();
            SimpleNodeIterator elements1 = children.elements();
            //遍历option
            while (elements1.hasMoreNodes()){
                Node node1 = elements1.nextNode();
                NodeList children1 = node1.getChildren();
                if (children1!=null){
//                    System.out.println(children1.elementAt(0).getText());
                    appNames.add(children1.elementAt(0).getText());
                }
            }
        }
        return appNames;
    }

    public static String doGet(String url, String cookie) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Cookie",cookie);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String postJson(String requestUrl, String param, String cookie) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestUrl);
        try {
            CloseableHttpResponse response = null;
            try {
                httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString());
                httpPost.setEntity(new StringEntity(param));
                httpPost.setHeader("Cookie",cookie);
                response = httpclient.execute(httpPost);
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toString(httpEntity, DEFAULT_CHARSET);
            } finally {
                if(response!=null){
                    response.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
