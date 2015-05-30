package io.mikekennedy.camel;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlackProducer extends DefaultProducer {

    private static final transient Logger LOG = LoggerFactory.getLogger(SlackProducer.class);

    private SlackEndpoint slackEndpoint;

    public SlackProducer(SlackEndpoint endpoint) {
        super(endpoint);
        this.slackEndpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

    }
}
