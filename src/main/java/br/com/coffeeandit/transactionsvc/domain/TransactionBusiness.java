package br.com.coffeeandit.transactionsvc.domain;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.repository.TransactionRepository;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
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
}
