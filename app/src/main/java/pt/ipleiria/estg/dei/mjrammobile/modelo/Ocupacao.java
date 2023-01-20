package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Ocupacao {
    private int id_aviao, ocupacao;

    private String classe;


    public Ocupacao(int id_aviao, int ocupacao, String classe) {
        this.id_aviao = id_aviao;
        this.ocupacao = ocupacao;
        this.classe = classe;
    }

    public int getId_aviao() {
        return id_aviao;
    }

    public void setId_aviao(int id_aviao) {
        this.id_aviao = id_aviao;
    }

    public int getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
