package com.kadTI.techHeart_backend.dto;

public class IniciarSesionResponse {
    private Long idSesion;

    public IniciarSesionResponse(Long idSesion){
        this.idSesion = idSesion;
    }

    public Long getIdSesion(){
        return idSesion;
    }
}
