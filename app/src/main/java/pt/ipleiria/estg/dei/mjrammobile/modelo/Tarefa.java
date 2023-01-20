package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Tarefa {

    private int id, id_voo,id_hangar,id_recurso;
    private String estado, designacao;

    public Tarefa(int id, int id_voo, int id_hangar, int id_recurso, String estado, String designacao) {
        this.id = id;
        this.id_voo = id_voo;
        this.id_hangar = id_hangar;
        this.id_recurso = id_recurso;
        this.estado = estado;
        this.designacao = designacao;
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

    public int getId_hangar() {
        return id_hangar;
    }

    public void setId_hangar(int id_hangar) {
        this.id_hangar = id_hangar;
    }

    public int getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(int id_recurso) {
        this.id_recurso = id_recurso;
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
