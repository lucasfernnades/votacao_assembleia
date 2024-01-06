package com.sicredi.votacao_assembleia.config;

import com.sicredi.votacao_assembleia.constants.RabbitMqConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMqConfig {

    private AmqpAdmin amqpAdmin;

    @Autowired
    public RabbitMqConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange exchange() {
        return new DirectExchange(RabbitMqConstants.NOME_EXCHANGE);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange().getName(), queue.getName(), null);
    }

    @PostConstruct
    private void configureRabbit() {
        Queue filaResultado = this.queue(RabbitMqConstants.FILA_RESULTADO_VOTACAO);

        DirectExchange exchange = this.exchange();

        Binding bindingResultado = this.binding(filaResultado, exchange);

        amqpAdmin.declareQueue(filaResultado);

        amqpAdmin.declareExchange(exchange);

        amqpAdmin.declareBinding(bindingResultado);
    }
}
