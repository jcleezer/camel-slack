package io.mikekennedy.camel;

import org.apache.camel.CamelException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.client.HttpClient;

import javax.activation.DataHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlackProducer extends DefaultProducer {

    private static final transient Logger LOG = LoggerFactory.getLogger(SlackProducer.class);

    private SlackEndpoint slackEndpoint;

    /**
     * Constuctor
     *
     * @param endpoint a SlackEndpoint
     */
    public SlackProducer(SlackEndpoint endpoint) {
        super(endpoint);
        this.slackEndpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        // Create an HttpClient and Post object
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(slackEndpoint.getWebhookUrl());

        // Build Helper object
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setText(exchange.getIn().getBody(String.class));
        slackMessage.setChannel(slackEndpoint.getChannel());
        slackMessage.setUsername(slackEndpoint.getUsername());
        slackMessage.setIconUrl(slackEndpoint.getIconUrl());
        slackMessage.setIconEmoji(slackEndpoint.getIconEmoji());
        SlackAttachment[] attachments = getSlackAttachments(exchange.getIn());
        slackMessage.setSlackAttachments(attachments);
        // Set the post body
        StringEntity body = new StringEntity(slackMessage.toString());

        // Do the post
        httpPost.setEntity(body);

        HttpResponse response = client.execute(httpPost);

        if (response.getStatusLine().getStatusCode() != 200) {
            LOG.error("Error POSTing to Slack API: " + response.toString());
            throw new CamelException("Error POSTing to Slack API: " + response.toString());
        }
    }

    private SlackAttachment[] getSlackAttachments(Message message) {
        List<SlackAttachment> slackAttachments = new ArrayList<SlackAttachment>();
        if (message.getHeaders().containsKey("attachments") && message.getHeaders().get("attachments") instanceof List){
            List attachments = (List) message.getHeader("attachments");
            for (Object attachment : attachments){
                if (attachment instanceof Map){
                    Map<String,String> attachmentMap = (Map<String,String>) attachment;
                    SlackAttachment slackAttachment = convertMapToSlackAttachment(attachmentMap);
                    slackAttachments.add(slackAttachment);
                }
            }
        }
        return slackAttachments.toArray(new SlackAttachment[slackAttachments.size()]);
    }

    private SlackAttachment convertMapToSlackAttachment(Map<String, String> attachmentMap) {
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback(attachmentMap.get("fallback"));
        slackAttachment.setAuthor_icon(attachmentMap.get("author_icon"));
        slackAttachment.setAuthor_link(attachmentMap.get("author_link"));
        slackAttachment.setColor(attachmentMap.get("color"));
        slackAttachment.setImage_url(attachmentMap.get("image_url"));
        slackAttachment.setPretext(attachmentMap.get("pretext"));
        slackAttachment.setText(attachmentMap.get("text"));
        slackAttachment.setThumb_url(attachmentMap.get("thumb_url"));
        slackAttachment.setTitle(attachmentMap.get("title"));
        slackAttachment.setTitle_link(attachmentMap.get("title_link"));
        return slackAttachment;
    }
}
