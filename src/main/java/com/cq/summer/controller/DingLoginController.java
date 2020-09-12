//package com.cq.summer.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.cq.summer.entity.JSONResult;
//import com.dingtalk.api.DefaultDingTalkClient;
//import com.dingtalk.api.DingTalkClient;
//import com.dingtalk.api.request.OapiSnsGetPersistentCodeRequest;
//import com.dingtalk.api.request.OapiSnsGetSnsTokenRequest;
//import com.dingtalk.api.request.OapiSnsGettokenRequest;
//import com.dingtalk.api.request.OapiSnsGetuserinfoRequest;
//import com.dingtalk.api.response.OapiSnsGetPersistentCodeResponse;
//import com.dingtalk.api.response.OapiSnsGetSnsTokenResponse;
//import com.dingtalk.api.response.OapiSnsGettokenResponse;
//import com.dingtalk.api.response.OapiSnsGetuserinfoResponse;
//import com.taobao.api.ApiException;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("")
//public class DingLoginController {
//
//    private static final String URL = "http://127.0.0.1:8080/loginInfo";
//    private static final String DINGDING_URL = "https://oapi.dingtalk.com";
//    private static final String METHOD_GET = "GET";
//    private static final String APP_ID = "dingoajglmh8leatqjdkyi";
//    private static final String APP_SECRET = "dUjRcUtxLBocPYR28Lcwc6AJcrRB8criDh8NIrV7b7QMteyuJBWTmpcQNcV2O5wh";
//
//    @RequestMapping("/login")
//    public void login(HttpServletResponse response) throws IOException {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(DINGDING_URL).append("/connect/qrconnect?appid=" + APP_ID + "&")
//                .append("response_type=code&scope=snsapi_login&state=")
//                .append(System.currentTimeMillis()).append("&redirect_uri=").append(URL);
//        response.sendRedirect(stringBuilder.toString());
//    }
//
//    @RequestMapping("/loginInfo")
//    public JSONResult login(String code) throws ApiException {
//        String accessToken = getAccessToken(APP_ID, APP_SECRET);
//        String persistentCode = getPersistentCode(accessToken, code);
//        JSONObject infos= JSONObject.parseObject(persistentCode);
//        String openid = infos.getString("openid");
//        String persistent_code = infos.getString("persistent_code");
//        String unionid = infos.getString("unionid");
//        String snsToken = getSnsToken(accessToken,openid,persistent_code);
//        String userInfo = getUserInfo(snsToken);
//        return JSONResult.ok(userInfo);
//
//
//    }
//
//
//    public String getAccessToken(String appId, String appSecret) throws ApiException {
//        OapiSnsGettokenResponse response = null;
//
//        DingTalkClient client = new DefaultDingTalkClient(DINGDING_URL + "/sns/gettoken");
//        OapiSnsGettokenRequest request = new OapiSnsGettokenRequest();
//        request.setAppid(appId);
//        request.setAppsecret(appSecret);
//        request.setHttpMethod(METHOD_GET);
//        response = client.execute(request);
//        String body = response.getBody();
//        JSONObject jo = JSON.parseObject(body);
//        String errcode = jo.getString("errcode");
//        String access_token = null;
//        if ("0".equals(errcode)) {
//            access_token = (String) jo.get("access_token");
//        }
//        return access_token;
//    }
//
//
//    public String getPersistentCode(String accessToken, String code) throws ApiException {
//        OapiSnsGetPersistentCodeResponse response = null;
//
//        DingTalkClient client = new DefaultDingTalkClient(DINGDING_URL + "/sns/get_persistent_code");
//        OapiSnsGetPersistentCodeRequest request = new OapiSnsGetPersistentCodeRequest();
//        request.setTmpAuthCode(code);
//        response = client.execute(request, accessToken);
//
//        return response.getBody();
//    }
//
//
//    public String getSnsToken(String accessToken,String openId,String persistentCode) throws ApiException {
//        OapiSnsGetSnsTokenResponse response = null;
//        DingTalkClient client = new DefaultDingTalkClient(DINGDING_URL + "/sns/get_sns_token");
//        OapiSnsGetSnsTokenRequest request = new OapiSnsGetSnsTokenRequest();
//        request.setPersistentCode(persistentCode);
//        request.setOpenid(openId);
//        response = client.execute(request, accessToken);
//        return response.getSnsToken();
//    }
//
//
//    public String getUserInfo(String snsToken) throws ApiException {
//        OapiSnsGetuserinfoResponse response = null;
//        DingTalkClient client = new DefaultDingTalkClient(DINGDING_URL + "/sns/getuserinfo");
//        OapiSnsGetuserinfoRequest request = new OapiSnsGetuserinfoRequest();
//        request.setSnsToken(snsToken);
//        request.setHttpMethod(METHOD_GET);
//        response = client.execute(request);
//        return response.getBody();
//    }
//
//
//}
//
