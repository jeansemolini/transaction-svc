package br.com.coffeeandit.transactionsvc.events;

import br.com.coffeeandit.transactionsvc.domain.TransactionBusiness;
import br.com.coffeeandit.transactionsvc.dto.SituacaoEnum;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class TransactionConsumer {

    private ObjectMapper objectMapper;
    private TransactionBusiness transactionBusiness;

    public TransactionConsumer(ObjectMapper objectMapper, TransactionBusiness transactionBusiness) {
        this.objectMapper = objectMapper;
        this.transactionBusiness = transactionBusiness;
    }

    @KafkaListener(topics = "${events.consumeTopic}")
    public void consumeTransaction(String message) {
        try {
            var transaction = getTransaction(message);
            transactionBusiness.atualizarTransacao(transaction);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "${events.returnTopic}")
    public void consumeTransactionEstorno(String message) {
        try {
            var transaction = getTransaction(message);
            log.info("Transação recebida da análise: {}", transaction);
            if (!transaction.isAnalisada()) {
                return;
            }
            log.info("Transação analisada: {}", transaction);
            transactionBusiness.aprovarTransacao(transaction);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private TransactionDto getTransaction(String message) throws JsonProcessingException {
        TransactionDto transactionDto = objectMapper.readValue(message, TransactionDto.class);
        if (Objects.isNull(transactionDto.getSituacao())) {
            transactionDto.setSituacao(SituacaoEnum.NAO_ANALISADA);
        }
        transactionDto.setData(LocalDateTime.now());
        return transactionDto;
    }
}
