package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Aviao {
    private int id,combustivelatual;
    private Ocupacao[] ocupacoes;
    public Aviao(int id, int combustivelatual, Ocupacao[] ocupacoes){
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

    public Ocupacao[] getOcupacoes() {
        return ocupacoes;
    }

    public void setOcupacoes(Ocupacao[] ocupacoes) {
        this.ocupacoes = ocupacoes;
    }

    public int getCombustivelatual() {
        return combustivelatual;
    }

    public void setCombustivelatual(int combustivelatual) {
        this.combustivelatual = combustivelatual;
    }
}
