package com.alura.ChallengeLiteratura.Service;

public interface IConvierteDatos {
    <T> T obtenerDatosLibros (String json, Class <T> clase);
}
