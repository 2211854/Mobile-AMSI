package pt.ipleiria.estg.dei.mjrammobile.modelo;

import java.util.ArrayList;

public class Aviao {
    private int id,combustivelatual;
    private ArrayList<Ocupacao> ocupacoes;
    public Aviao(int id, int combustivelatual, ArrayList<Ocupacao> ocupacoes){
        this.id = id;
        this.combustivelatual  = combustivelatual;
        this.ocupacoes = ocupacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Ocupacao> getOcupacoes() {
        return ocupacoes;
    }

    public void setOcupacoes(ArrayList<Ocupacao> ocupacoes) {
        this.ocupacoes = ocupacoes;
    }

    public int getCombustivelatual() {
        return combustivelatual;
    }

    public void setCombustivelatual(int combustivelatual) {
        this.combustivelatual = combustivelatual;
    }
}
