package com.dianwoda.test.bassy.service.common.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianwoda.test.bassy.service.common.DingdingService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.dianwoda.test.bassy.common.constants.DingdingConstant.*;
import static io.restassured.RestAssured.given;

/**
 * Created by gaoh on 2019/10/14.
 */
@Service
public class DingdingServiceImpl implements DingdingService {

    @Value("dingding.corpid")
    private String corpid;

    @Override
    public String getToken() {
        Response response = given().contentType(ContentType.JSON).when().get(GET_DINGDING_TOKEN_URL + corpid);
        if (response.getStatusCode() != 200) return "";
        JSONObject body = JSON.parseObject(response.getBody().asString());
        return body.getString("access_token");
    }

    @Override
    public String createGroupChat(String token, String groupName, String owner, String[] useridList) {
        Map<String,Object> content = new HashMap<>();
        content.put("name", groupName);
        content.put("owner", owner);
        content.put("useridlist", useridList);
        Response response = given().contentType(ContentType.JSON).body(content).when().post(CREAT_GROUP_CHAT_URL + token);
        JSONObject body = JSON.parseObject(response.getBody().asString());
        return body.getString("chatid");
    }

    @Override
    public String sendLinkMsg(String token, String chatId, String msgContent) {
        Map<String,Object> msgTxt = new HashMap<>();
        msgTxt.put("title", "白石提醒");
        msgTxt.put("text", msgContent);
        msgTxt.put("picUrl", "@lALOACZwe2Rk");
        msgTxt.put("messageUrl", "http://dianwoda.cn");

        Map<String,Object> msg = new HashMap<>();
        msg.put("msgtype", "link");
        msg.put("link", msgTxt);

        Map<String,Object> content = new HashMap<>();
        content.put("chatid", chatId);
        content.put("msg", msg);
        Response response = given().contentType(ContentType.JSON).body(content).when().post(SEND_LINK_MSG_URL + token);
        JSONObject body = JSON.parseObject(response.getBody().asString());
        return body.getString("messageId");
    }

}
