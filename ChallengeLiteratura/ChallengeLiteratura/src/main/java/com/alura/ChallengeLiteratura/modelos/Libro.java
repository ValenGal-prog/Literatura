package com.alura.ChallengeLiteratura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Libro(@JsonAlias("id") Long id,
                    @JsonAlias("title") String titulo,
                    @JsonAlias("authors") List<DatosAutor> autor,
                    @JsonAlias("languages") List<DatosIdioma> idioma,
                    @JsonAlias("download_count") Integer numeroDeDescargas) {
}
