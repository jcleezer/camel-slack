package io.mikekennedy.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SlackComponent extends DefaultComponent {

    private static final transient Logger LOG = LoggerFactory.getLogger(SlackComponent.class);

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new SlackEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
