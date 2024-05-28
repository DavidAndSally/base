

import com.alibaba.fastjson.JSONObject;
import com.glodon.cloudt.rest.client.RestServiceClient;
import com.glodon.cloudt.rest.client.data.HmacRestAuthInfo;
import com.glodon.cloudt.rest.client.data.RestResponseInfo;
import com.glodon.cloudt.rest.client.exception.AuthenticateException;
import com.glodon.cloudt.rest.client.exception.InvalidUriException;
import com.glodon.cloudt.rest.client.exception.NoAuthenticateException;
import com.glodon.cloudt.rest.client.impl.HmacRestServiceClient;
import org.apache.http.entity.ContentType;

/**
 * @author liuzk
 * @create 2018-05-31 15:10.
 */
public class TciInspectFiledFilterTest {
    public static final String PRODUCT_SAFE = "safe";
    public static final String PRODUCT_QUALITY = "quality";
    public static void main(String[] args ){
        //创建客户端实例
        RestServiceClient serviceClient =  HmacRestServiceClient.getInstance();
        //构建认证信息
        HmacRestAuthInfo restAuthInfo = new HmacRestAuthInfo();
//        String authPath = TciInspectFiledFilterTest.class.getClassLoader().getResource("auth3.lic").getPath();
//        String authPath = TciInspectFiledFilterTest.class.getClassLoader().getResource("test-gd.lic").getPath();
        //设置授权文件路径
        restAuthInfo.setLicPath("C:/test510-auth.lic");
        //设置认证信息
        try {
            serviceClient.authenticate(restAuthInfo);
        } catch (AuthenticateException e) {
            e.printStackTrace();
        }
        //查询隐患列表数据
        RestResponseInfo postRequest = getInspectLists(serviceClient);
        //处理请求结果
        dealRequest(postRequest);
        //
        RestResponseInfo getRequest = getInspectById(serviceClient);
        dealRequest(getRequest);

        RestResponseInfo imagePostRequest = getImageUrls(serviceClient);
        System.out.println("获取图片的url地址");
        dealRequest(imagePostRequest);
    }

    private static RestResponseInfo getInspectLists(RestServiceClient serviceClient) {
//        String requestUrl = serviceClient.getRestRootAddress() + "/api/" + PRODUCT_SAFE + "/v1.0/tciInspect/fetchInspectsWithPermission";
        String requestUrl = "http://127.0.0.1:8989"+ "/services/tciInspect/fetchInspectRecords";
        //请求参数
        JSONObject param = new JSONObject();
        param.put("sourceCode","GZMetroLine12");
        //分页起始下标，从0开始,第一页为0，第二页为1
        param.put("pageIndex", 0);
        //一页请求的数量
        param.put("pageSize",100);
        param.put("updateTime","2018-05-12 00:00:00");
        //发送请求
        try {
            RestResponseInfo restResponseInfo = serviceClient.post(requestUrl, param.toJSONString(), ContentType.APPLICATION_JSON.getMimeType());
            return restResponseInfo;
        } catch (InvalidUriException e) {
            e.printStackTrace();
        } catch (NoAuthenticateException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static RestResponseInfo getImageUrls(RestServiceClient serviceClient) {
        String requestUrl = serviceClient.getRestRootAddress() + "/api/" + PRODUCT_SAFE + "/v1.0/OCRInspect/queryImgUrlByKeys";
        try {
            //请求参数
            String[] imageKyes = new String[]{"Inspection/72f1344b-1d9e-4ba7-9a0b-794881c85590.mp4", "Inspection/776fbf93-998b-4c6f-92ce-1a618c0b7965.jpg"};
            RestResponseInfo restResponseInfo = serviceClient.post(requestUrl, JSONObject.toJSONString(imageKyes), ContentType.APPLICATION_JSON.getMimeType());
            return restResponseInfo;
        } catch (InvalidUriException e) {
            e.printStackTrace();
        } catch (NoAuthenticateException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static RestResponseInfo getInspectById(RestServiceClient serviceClient) {
//        String detailUrl = serviceClient.getRestRootAddress() + "/api/" + PRODUCT_SAFE + "/v1.0/tciInspect/fetchInspectDetailWithPermission";
        String detailUrl = serviceClient.getRestRootAddress() + "/api/" + PRODUCT_SAFE + "/v1.0/tciInspect/fetchInspectRecordDetail";
        try {
            //请求参数
            JSONObject param = new JSONObject();
            param.put("sourceCode","GZMetroLine12");
            //分页起始下标，从0开始,第一页为0，第二页为1
            param.put("pageIndex", 0);
            //一页请求的数量
            param.put("pageSize",10);
            param.put("updateTime","2018-11-29 00:00:00");
            RestResponseInfo restResponseInfo = serviceClient.post(detailUrl, param.toJSONString(), ContentType.APPLICATION_JSON.getMimeType());
            return restResponseInfo;
        } catch (InvalidUriException e) {
            e.printStackTrace();
        } catch (NoAuthenticateException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void dealRequest(RestResponseInfo restResponseInfo) {
        if (restResponseInfo.isSuccess()) {
            String content = restResponseInfo.getStringContent();
            System.out.println(content);
        } else {
            System.out.println(restResponseInfo.getErrorCode());
            System.out.println(restResponseInfo.getErrorMessage());
        }
    }
}
