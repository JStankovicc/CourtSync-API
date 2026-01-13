package com.example.MQService;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class ActiveMqConfig {

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("MQServiceBroker");

        broker.setPersistent(false);

        broker.addConnector("vm://MQServiceBroker");

        TransportConnector tcpConnector = new TransportConnector();
        tcpConnector.setUri(new URI("tcp://0.0.0.0:61616"));
        broker.addConnector(tcpConnector);

        System.out.println("Broker MQServiceBroker started on TCP port 61616");

        return broker;
    }
}

