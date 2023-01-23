package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class TarefaSingle {

    private int id, id_voo;
    private String estado, designacao,hangar,recurso, quantidade,data_registo,data_inicio,data_final,username_funcionario_registo;



    public TarefaSingle(int id, int id_voo, String username_funcionario_registo, String designacao, String estado, String recurso, String hangar, String quantidade, String data_registo, String data_inicio, String data_final) {
        this.id = id;
        this.id_voo = id_voo;
        this.username_funcionario_registo = username_funcionario_registo;
        this.designacao = designacao;
        this.estado = estado;
        this.recurso = recurso;
        this.hangar = hangar;
        this.quantidade = quantidade;
        this.data_registo = data_registo;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
    }

    public String getUsername_funcionario_registo() {
        return username_funcionario_registo;
    }

    public void setUsername_funcionario_registo(String username_funcionario_registo) {
        this.username_funcionario_registo = username_funcionario_registo;
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

    public String getHangar() {
        return hangar;
    }

    public void setHangar(String hangar) {
        this.hangar = hangar;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getData_registo() {
        return data_registo;
    }

    public void setData_registo(String data_registo) {
        this.data_registo = data_registo;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_final() {
        return data_final;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }
}
