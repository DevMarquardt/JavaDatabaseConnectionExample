import java.sql.*;

public class CarroDAO extends DAOPadrao<Carro, Integer> {

    public CarroDAO() throws SQLException {
        super("carro");
    }

    @Override
    public void inserir(Carro obj) {
        setComandoSQL("INSERT INTO carro values(?,?,?,?,?,?)");

        try (PreparedStatement statement = connection.prepareStatement(getComandoSQL())) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getMarca());
            statement.setString(3, obj.getModelo());
            statement.setInt(4, obj.getAno());
            statement.setString(5, obj.getCor());
            statement.setDouble(6, obj.getPreco());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editar(Carro obj) {
        setComandoSQL("UPDATE carro SET marca = ?, cor = ?, modelo = ?, preco = ?, ano = ? WHERE id = ?");
        try (PreparedStatement statement = connection.prepareStatement(getComandoSQL())) {
            statement.setString(1, obj.getMarca());
            statement.setString(2, obj.getCor());
            statement.setString(3, obj.getModelo());
            statement.setDouble(4, obj.getPreco());
            statement.setInt(5, obj.getAno());
            statement.setInt(6, obj.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Carro converter(ResultSet resultSet) throws SQLException {
        return new Carro(resultSet);
    }
}