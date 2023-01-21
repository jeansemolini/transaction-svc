package br.com.coffeeandit.transactionsvc.domain;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import br.com.coffeeandit.transactionsvc.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.repository.TransactionRepository;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TransactionBusiness {

    private TransactionRepository transactionRepository;
    private TransactionValidation transactionValidaton;

    public TransactionBusiness(TransactionRepository transactionRepository, TransactionValidation transactionValidaton) {
        this.transactionRepository = transactionRepository;
        this.transactionValidaton = transactionValidaton;
    }

    public void save(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if (Objects.isNull(requestTransactionDto.getData()))
            requestTransactionDto.setData(LocalDateTime.now());

        transactionValidaton.validate(requestTransactionDto);
        transactionRepository.save(requestTransactionDto);
    }

    public void atualizarTransacao(TransactionDto transactionDto) {
        log.info("Atualizando transação: {}", transactionDto);
        transactionRepository.save(transactionDto);
    }

    public void aprovarTransacao(TransactionDto transactionEvent) {
        var transacao = buscarTransacao(transactionEvent);
        if (transacao.isPresent()) {
            var transactionDB = transacao.get();
            if (!transactionDB.isAnalisada() && transactionEvent.isAnalisada()) {
                transactionDB.aprovar();
                atualizarTransacao(transactionDB);
                log.info("Transação aprovada: {}", transactionDB);
            }
        }
    }

    private Optional<TransactionDto> buscarTransacao(TransactionDto transactionDto) {
        return transactionRepository.findById(transactionDto.getUuid());
    }
}
