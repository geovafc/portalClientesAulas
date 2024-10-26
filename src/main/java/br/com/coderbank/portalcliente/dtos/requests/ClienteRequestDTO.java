package br.com.coderbank.portalcliente.dtos.requests;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDTO(
        @NotBlank(message = "O campo nome é obrigatório.")
        @Size(message = "O campo nome deve ter pelo menos 3 caracteres.", min = 3)
        String nome,
        @CPF(message = "O campo cpf precisa ser válido.")
        String cpf,
        @NotBlank(message = "O campo email é obrigatório.")
        @Email(message = "O campo email precisa ser válido.")
        String email,

        @NotBlank(message = "O campo email é obrigatório.")
        String endereco,
        @Min(message = "A idade mínima precisa ser igual a 18 anos", value = 18)
        @Max(message = "A idade máxima precisa ser igual a 120 anos", value = 120)
        Integer idade
) {
}