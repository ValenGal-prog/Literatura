package com.alura.ChallengeLiteratura.modelos;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(@JsonAlias("results") List<DatosLibros> resultados,
                    @JsonAlias("count") Double total){
}
