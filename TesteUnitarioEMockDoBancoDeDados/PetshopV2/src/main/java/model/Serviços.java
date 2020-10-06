package model;

public class Serviços {
    private int id;
    private static int proxid = 1;
    private String nomeServiço;
    private double preço;


    public Serviços(String nomeServiço, double preço) {
        this.nomeServiço = nomeServiço;
        this.preço = preço;
        this.IncrementarId();
    }

    public String getNomeServiço() {
        return nomeServiço;
    }


    public void setNomeServiço(String nomeServiço) {
        this.nomeServiço = nomeServiço;
    }

    public double getPreço() {
        return preço;
    }

    public int getId() {
        return id;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getProxid() {
        return proxid;
    }

    public void IncrementarId() {
        this.id = this.proxid;
        this.proxid++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(this.id + " ");
        sb.append("nome: ");
        sb.append(this.nomeServiço + " ");
        sb.append("preço: ");
        sb.append(this.preço + " ");
        return sb.toString();
    }
}
