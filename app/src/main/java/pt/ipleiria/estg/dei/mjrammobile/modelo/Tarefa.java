package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Tarefa {

    private int id;
    private String estado, designacao;

    public Tarefa(int id, String designacao, String estado){
        this.id = id;
        this.designacao  = designacao;
        this.estado = estado;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
