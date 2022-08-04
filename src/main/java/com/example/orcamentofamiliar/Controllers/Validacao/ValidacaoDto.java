package com.example.orcamentofamiliar.Controllers.Validacao;

import lombok.Getter;

@Getter
public class ValidacaoDto {

    private String campo;
    private String erro;

    public ValidacaoDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }
}
