package pt.ipleiria.estg.dei.mjrammobile.modelo;

public class Perfil {
    private int id;
    private String nib,email,telemovel,nomes,dataregisto;

    public Perfil( String nib, String email, String telemovel, String nomes,String dataregisto, int id){
        System.out.println(email);
        this.nib = nib;
        this.email  = email;
        this.telemovel = telemovel;
        this.nomes = nomes;
        this.dataregisto = dataregisto;
        this.id = id;
    }

    public String getDataregisto() {
        return dataregisto;
    }

    public void setDataregisto(String dataregisto) {
        this.dataregisto = dataregisto;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getNomes() {
        return nomes;
    }

    public void setNomes(String nomes) {
        this.nomes = nomes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
