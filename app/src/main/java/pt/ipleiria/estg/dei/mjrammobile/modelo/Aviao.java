package pt.ipleiria.estg.dei.mjrammobile.modelo;

import java.util.ArrayList;

public class Aviao {
    private int  id,combustivelatual,combustivelmaximo,ocupacaoeconomica,ocupacaoprimeira,ocupacaobusiness;
    public Aviao(int id, int combustivelatual,int combustivelmaximo,int ocupacaoeconomica,int ocupacaoprimeira,int ocupacaobusiness){
        this.id = id;
        this.combustivelatual  = combustivelatual;
        this.combustivelmaximo  = combustivelmaximo;
        this.ocupacaoeconomica  = ocupacaoeconomica;
        this.ocupacaoprimeira  = ocupacaoprimeira;
        this.ocupacaobusiness  = ocupacaobusiness;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOcupacaoeconomica() {
        return ocupacaoeconomica;
    }

    public void setOcupacaoeconomica(int ocupacaoeconomica) {
        this.ocupacaoeconomica = ocupacaoeconomica;
    }

    public int getOcupacaoprimeira() {
        return ocupacaoprimeira;
    }

    public void setOcupacaoprimeira(int ocupacaoprimeira) {
        this.ocupacaoprimeira = ocupacaoprimeira;
    }

    public int getOcupacaobusiness() {
        return ocupacaobusiness;
    }

    public void setOcupacaobusiness(int ocupacaobusiness) {
        this.ocupacaobusiness = ocupacaobusiness;
    }
}
