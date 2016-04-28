package io.mikekennedy.camel;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SlackMessageTest extends TestCase {

    @Test
    public void testSlackMessageConvertedToCorrectJson(){
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setChannel("#test-channel");
        slackMessage.setIconEmoji(":ack:");
        slackMessage.setText("Test text");
        slackMessage.setUsername("Tester");
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setText("Attachment text");
        slackAttachment.setAuthor_name("test author");
        slackAttachment.setAuthor_link("www.author-link.com");
        slackAttachment.setTitle("Test Title");
        slackMessage.setSlackAttachments(new SlackAttachment[]{slackAttachment});


        String json = slackMessage.toString();
        assertEquals("{\"text\":\"Test text\",\"channel\":\"#test-channel\",\"username\":\"Tester\",\"iconEmoji\":\":ack:\",\"attachments\":[{\"author_name\":\"test author\",\"author_link\":\"www.author-link.com\",\"title\":\"Test Title\",\"text\":\"Attachment text\"}]}",json);
    }

}