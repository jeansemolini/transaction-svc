package br.com.coffeeandit.transactionsvc.repository;

import br.com.coffeeandit.transactionsvc.dto.Conta;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends MongoRepository<TransactionDto, UUID> {

    List<TransactionDto> findByConta(final Conta conta);
}
