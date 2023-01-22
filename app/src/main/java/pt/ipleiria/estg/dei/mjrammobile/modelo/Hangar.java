package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Hangar {
    private int id;
    private String designacao;
    public Hangar(int id,String designacao){
        this.id = id;
        this.designacao = designacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
