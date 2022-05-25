package br.com.coffeeandit.transactionsvc.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BeneficiarioDto {

    @NotNull(message = "Informar o CPF.")
    private Long CPF;
    @NotNull(message = "Informar o código do banco de destino.")
    private Long codigoBanco;
    @NotNull(message = "Informar a agência de destino.")
    private String agencia;
    @NotNull(message = "Informar a conta de destino.")
    private String conta;
    @NotNull(message = "Informar o nome do Favorecido.")
    private String nomeFavorecido;
}
