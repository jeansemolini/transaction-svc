package br.com.coffeeandit.transactionsvc.validator;

import br.com.coffeeandit.transactionsvc.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.validator.impl.BancoTransactionValidator;
import br.com.coffeeandit.transactionsvc.validator.impl.HorarioTransactionValidator;
import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnBean(value = {BancoTransactionValidator.class, HorarioTransactionValidator.class})
@ConditionalOnExpression("${transaction.validation.enabled:true}")
@Slf4j
public class TransactionValidationBean implements TransactionValidation{

    private List<TransactionValidator> transactionValidatorList = new ArrayList<>();

    @PostConstruct
    public void addBeans() {
        transactionValidatorList.add(new BancoTransactionValidator());
        transactionValidatorList.add(new HorarioTransactionValidator());
    }

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        transactionValidatorList.stream().forEach(transactionValidator -> {
            try {
                transactionValidator.validate(requestTransactionDto);
            } catch (DomainBusinessException e) {
                e.printStackTrace();
            }
        });
    }
}
