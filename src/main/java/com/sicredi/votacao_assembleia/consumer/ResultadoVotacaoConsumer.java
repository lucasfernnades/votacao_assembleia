package com.sicredi.votacao_assembleia.consumer;

import com.sicredi.votacao_assembleia.constants.RabbitMqConstants;
import com.sicredi.votacao_assembleia.dto.ResultadoVotacaoResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ResultadoVotacaoConsumer {

    Logger logger = LoggerFactory.getLogger(ResultadoVotacaoConsumer.class);

    @RabbitListener(queues = RabbitMqConstants.FILA_RESULTADO_VOTACAO)
    public void consumidor(ResultadoVotacaoResponseDTO message) {
        System.out.println();
        logger.info(message.toString());
    }
}
