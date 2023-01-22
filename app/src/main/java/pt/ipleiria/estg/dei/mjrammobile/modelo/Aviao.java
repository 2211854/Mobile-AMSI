package pt.ipleiria.estg.dei.mjrammobile.modelo;

import java.util.ArrayList;

public class Aviao {
    private int id,combustivelatual,combustivelmaximo;
    private ArrayList<Ocupacao> ocupacoes;
    public Aviao(int id, int combustivelatual,int combustivelmaximo, ArrayList<Ocupacao> ocupacoes){
        this.id = id;
        this.combustivelatual  = combustivelatual;
        this.combustivelmaximo  = combustivelmaximo;
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

    public int getCombustivelmaximo() {
        return combustivelmaximo;
    }

    public void setCombustivelmaximo(int combustivelmaximo) {
        this.combustivelmaximo = combustivelmaximo;
    }
}
