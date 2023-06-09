package br.com.coffeeandit.transactionsvc.domain;

import br.com.coffeeandit.transactionsvc.dto.SituacaoEnum;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import br.com.coffeeandit.transactionsvc.fixture.TransactionDtoFixture;
import br.com.coffeeandit.transactionsvc.repository.TransactionRepository;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class TransactionBusinessTest {

    private TransactionBusiness transactionBusiness;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionValidation transactionValidation;

    @BeforeEach
    private void setUp() {
        transactionBusiness = new TransactionBusiness(transactionRepository, transactionValidation);
    }

    @ParameterizedTest
    @MethodSource("montarDto")
    void deveAprovarTransacao(TransactionDto dto) {
        var dtoFixture = TransactionDtoFixture.build();

        BDDMockito.when(transactionRepository.findById(dto.getUuid())).thenReturn(Optional.of(dtoFixture));

        transactionBusiness.aprovarTransacao(dto);

        Assertions.assertEquals(dtoFixture.getSituacao(), SituacaoEnum.APROVADA);

    }

    private static Stream<Arguments> montarDto() {
        TransactionDto dto = new TransactionDto();
        dto.setUuid(UUID.fromString("3c4e5485-8880-4994-a19a-86e68c9ea34d"));
        dto.setSituacao(SituacaoEnum.ANALISADA);

        return Stream.of(
                Arguments.of(dto)
        );
    }

}
