package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Aviao {
    private int id, ocupacao,combustivelatual;
    private String classe;

    public Aviao(int id, int combustivelatual, String classe, int ocupacao){
        this.id = id;
        this.combustivelatual  = combustivelatual;
        this.classe = classe;
        this.ocupacao = ocupacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    public int getCombustivelatual() {
        return combustivelatual;
    }

    public void setCombustivelatual(int combustivelatual) {
        this.combustivelatual = combustivelatual;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
