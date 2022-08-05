package br.com.coffeeandit.transactionsvc.validator;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.exception.DomainBusinessException;

public class EmptyTransactionValidationBean implements TransactionValidation {

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {

    }
}
