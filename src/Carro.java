import java.sql.*;

public class Carro {

    private Integer id;
    private String marca;
    private String cor;
    private String modelo;
    private Double preco;
    private int ano;

    public Carro(Integer id, String marca, String cor, String modelo, Double preco, int ano) {
        this.id = id;
        this.marca = marca;
        this.cor = cor;
        this.modelo = modelo;
        this.preco = preco;
        this.ano = ano;
    }

    public Carro(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.marca = resultSet.getString("marca");
        this.cor = resultSet.getString("cor");
        this.modelo = resultSet.getString("modelo");
        this.preco = resultSet.getDouble("preco");
        this.ano = resultSet.getInt("ano");
    }
    public Carro(Integer id_carro) {
        this.id = id_carro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", cor='" + cor + '\'' +
                ", modelo='" + modelo + '\'' +
                ", preco=" + preco +
                ", ano=" + ano +
                '}';
    }
}