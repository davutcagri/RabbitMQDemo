package com.davutcagri.Producer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${app.mq.queue}")
    private String queueName;
    @Value("${app.mq.exchange}")
    private String exchangeName;
    @Value("${app.mq.routing}")
    private String routingKey;

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory cf) {
        RabbitAdmin admin = new RabbitAdmin(cf);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public ApplicationRunner forceDeclare(AmqpAdmin admin) {
        return args -> admin.initialize(); // context açılır açılmaz declare
    }

    @Bean
    public Queue createQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange createExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding createBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

}
