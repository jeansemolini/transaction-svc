package br.com.coffeeandit.transactionsvc.dto;

import java.time.LocalDateTime;

public class RequestTransactionDto extends TransactionDto {
    private SituacaoEnum situacao;
    private LocalDateTime data;
}
