package br.com.coffeeandit.transactionsvc.fixture;

import br.com.coffeeandit.transactionsvc.dto.BeneficiarioDto;
import br.com.coffeeandit.transactionsvc.dto.SituacaoEnum;
import br.com.coffeeandit.transactionsvc.dto.TipoTransacao;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionDtoFixture {

    public static TransactionDto build() {
        TransactionDto dto = new TransactionDto();
        dto.setUuid(UUID.randomUUID());
        dto.setData(LocalDateTime.now());
        dto.setSituacao(SituacaoEnum.NAO_ANALISADA);
        dto.setTipoTransacao(TipoTransacao.PAGAMENTO_TITULOS);
        dto.setValor(BigDecimal.TEN);
        BeneficiarioDto beneficiarioDto = new BeneficiarioDto();
        beneficiarioDto.setAgencia("1213");
        beneficiarioDto.setConta("8989-5");
        beneficiarioDto.setCodigoBanco(144L);
        beneficiarioDto.setCPF(37634567812L);
        beneficiarioDto.setNomeFavorecido("Fulano da Silva");
        dto.setBeneficiario(beneficiarioDto);
        return dto;
    }
}
