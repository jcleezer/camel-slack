package io.mikekennedy.camel;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Helper class for building JSON message to Slack API
 */
public class SlackMessage {

    private static final Gson gson = new Gson();

    private String text;
    private String channel;
    private String username;
    private String iconUrl;
    private String iconEmoji;

    @SerializedName("attachments")
    private SlackAttachment[] slackAttachments;

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

    public SlackAttachment[] getSlackAttachments() {
        return slackAttachments;
    }

    public void setSlackAttachments(SlackAttachment[] slackAttachments) {
        this.slackAttachments = slackAttachments;
    }

    /**
     * Returns a JSON string to be posted to the Slack API
     *
     * @return JSON string
     */
    public String toString() {
        return gson.toJson(this);
    }
}
