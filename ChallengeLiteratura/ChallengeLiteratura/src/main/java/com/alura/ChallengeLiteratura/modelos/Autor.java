package com.alura.ChallengeLiteratura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Autor(@JsonAlias("name") String nombreAutor,
                    @JsonAlias ("birth_year") Integer anioDeNacimiento,
                    @JsonAlias ("death_year") Integer anioDeFallecimiento) {
}
