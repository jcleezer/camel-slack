package io.mikekennedy.camel;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SlackMessage {

    private String text;
    private String channel;
    private String username;
    private String iconUrl;
    private String iconEmoji;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconEmoji() {
        return iconEmoji;
    }

    public void setIconEmoji(String iconEmoji) {
        this.iconEmoji = iconEmoji;
    }

    public String toString() {

        Map<String, String> jsonMap = new HashMap<String, String>();

        jsonMap.put("text", this.text);
        jsonMap.put("channel", this.channel);
        jsonMap.put("username", this.username);
        jsonMap.put("icon_url", this.iconUrl);
        jsonMap.put("icon_emoji", this.iconEmoji);

        JSONObject jsonObject = new JSONObject(jsonMap);

        return JSONObject.toJSONString(jsonMap);

    }
}
