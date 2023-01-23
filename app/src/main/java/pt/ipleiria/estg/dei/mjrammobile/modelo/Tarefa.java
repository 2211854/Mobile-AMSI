package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Tarefa {

    private int id, id_voo;
    private String estado, designacao,id_hangar,id_recurso, quantidade;

    public Tarefa(int id, int id_voo, String id_hangar, String id_recurso, String estado, String designacao, String quantidade) {
        this.id = id;
        this.id_voo = id_voo;
        this.id_hangar = id_hangar;
        this.id_recurso = id_recurso;
        this.estado = estado;
        this.designacao = designacao;
        this.quantidade = quantidade;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_voo() {
        return id_voo;
    }

    public void setId_voo(int id_voo) {
        this.id_voo = id_voo;
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

    public String getId_hangar() {
        return id_hangar;
    }

    public void setId_hangar(String id_hangar) {
        this.id_hangar = id_hangar;
    }

    public String getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(String id_recurso) {
        this.id_recurso = id_recurso;
    }
}
