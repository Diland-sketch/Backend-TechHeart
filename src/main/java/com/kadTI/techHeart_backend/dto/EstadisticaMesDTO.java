package com.kadTI.techHeart_backend.dto;

public class EstadisticaMesDTO {
    private int mes;
    private long total;

    public EstadisticaMesDTO(int mes, long total){
        this.mes = mes;
        this.total = total;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
