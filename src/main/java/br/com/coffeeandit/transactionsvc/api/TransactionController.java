package br.com.coffeeandit.transactionsvc.api;

import br.com.coffeeandit.transactionsvc.domain.TransactionBusiness;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionBusiness transactionBusiness;

    public TransactionController(TransactionBusiness transactionBusiness) {
        this.transactionBusiness = transactionBusiness;
    }

    @GetMapping("/{agencia}/{conta}")
    public List<TransactionDto> buscarLimiteDiario(@PathVariable("agencia") Long agencia, @PathVariable("conta") Long conta) {
        return transactionBusiness.findByConta(agencia, conta);
    }
}
