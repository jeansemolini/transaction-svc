package br.com.coffeeandit.transactionsvc.repository;

import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TransactionRepository extends MongoRepository<TransactionDto, UUID> {
}
