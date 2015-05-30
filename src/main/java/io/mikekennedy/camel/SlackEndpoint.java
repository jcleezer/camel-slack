package io.mikekennedy.camel;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.client.HttpClient;


public class SlackEndpoint extends DefaultEndpoint {

    private static final transient Logger LOG = LoggerFactory.getLogger(SlackEndpoint.class);

    public SlackEndpoint(String uri, SlackComponent component) {
        super(uri, component);
    }

    @Override
    public Producer createProducer() throws Exception {
        SlackProducer producer = new SlackProducer(this);
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException("You cannot consume slack messages from this endpoint: " + getEndpointUri());
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
