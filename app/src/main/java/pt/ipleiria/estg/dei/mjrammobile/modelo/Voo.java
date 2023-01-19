package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Voo {

    private int id, id_aviao, totalbilhetes;
    private String estado, designacao, aviao, pista;

    public Voo(int id, String designacao, String estado, int id_aviao, int totalbilhetes, String pista, String aviao){
        this.id = id;
        this.designacao  = designacao;
        this.estado = estado;
        this.id_aviao = id_aviao;
        this.aviao = aviao;
        this.pista = pista;
        this.totalbilhetes = totalbilhetes;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_aviao() {
        return id_aviao;
    }

    public void setId_aviao(int id_aviao) {
        this.id_aviao = id_aviao;
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

    public String getAviao() {
        return aviao;
    }

    public void setAviao(String aviao) {
        this.aviao = aviao;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    public int getTotalbilhetes() {return totalbilhetes;}

    public void setTotalbilhetes(int totalbilhetes) {this.totalbilhetes = totalbilhetes;}
}
