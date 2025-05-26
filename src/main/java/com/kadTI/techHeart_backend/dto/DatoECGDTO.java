package com.kadTI.techHeart_backend.dto;

public class DatoECGDTO {
    private int valor;
    private Integer bpm;

    public int getValor(){
        return valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }
}
