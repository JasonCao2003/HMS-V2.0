package com.xupt.hms.utils;

import com.alibaba.fastjson.JSON;


import com.xupt.hms.pojo.PrividerToken;
import com.xupt.hms.pojo.gitUser;

import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GiteeAccessPrivider {
    public String getToken(PrividerToken prividerToken) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(prividerToken));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code&code=" + prividerToken.getCode() + "&client_id=" + prividerToken.getClient_id() + "&redirect_uri=" + prividerToken.getRedirect_uri() + "&client_secret=" + prividerToken.getClient_Secret())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            String string = null;
            if (response.body() != null) {
                string = response.body().string();
            }

            String str1 = null;
            if (string != null) {
                str1 = string.split(":")[1];
            }
            String str2 = null;
            if (str1 != null) {
                str2 = str1.split("\"")[1];
            }

            System.out.println("token:    " + str2);
            System.out.println(string);
            return str2;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public gitUser getUser(String Token) {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token=" + Token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = null;
            if (response.body() != null) {
                string = response.body().string();
            }
            gitUser user = JSON.parseObject(string, gitUser.class);

            return user;
        } catch (IOException ignored) {

        }
        return null;
    }


}
