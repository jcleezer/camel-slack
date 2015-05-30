package io.mikekennedy.camel;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.client.HttpClient;


public class SlackProducer extends DefaultProducer {

    private static final transient Logger LOG = LoggerFactory.getLogger(SlackProducer.class);

    private SlackEndpoint slackEndpoint;

    public SlackProducer(SlackEndpoint endpoint) {
        super(endpoint);
        this.slackEndpoint = endpoint;
        LOG.info("webHookUrl = " + this.slackEndpoint.getWebhookUrl());
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(slackEndpoint.getWebhookUrl());

        SlackMessage slackMessage = new SlackMessage();

        slackMessage.setText((String)exchange.getIn().getBody());
        slackMessage.setChannel(slackEndpoint.getChannel());
        slackMessage.setUsername(slackEndpoint.getUsername());
        slackMessage.setIconUrl(slackEndpoint.getIconUrl());
        slackMessage.setIconEmoji(slackEndpoint.getIconEmoji());

        StringEntity body = new StringEntity(slackMessage.toString());

        LOG.info(slackMessage.toString());

        httpPost.setEntity(body);

        client.execute(httpPost);
    }
}
