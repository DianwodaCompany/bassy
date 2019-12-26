package com.dianwoda.test.bassy.service.common;

/**
 * Created by gaoh on 2019/10/14.
 */
public interface DingdingService {

    String getToken();

    String createGroupChat(String token, String groupName, String owner, String[] useridList);

    String sendLinkMsg(String token, String chatId, String content);

}
