import java.sql.*;
import java.util.*;

public abstract class DAOPadrao<T, ID> implements ICrud<T, Integer> {
    protected Connection connection;
    protected String comandoSQL;
    private String tabela;

    public DAOPadrao(String tabela) throws SQLException {
        this.connection = new Banco().conectar();
        this.tabela = tabela;
    }

    public void setComandoSQL(String comandoSQL) {
        this.comandoSQL = comandoSQL;
    }

    public String getComandoSQL() {
        return comandoSQL;
    }

    @Override
    public Set<T> buscarTodos() {
        try (Statement statement = connection.createStatement()) {
            setComandoSQL("select * from " + tabela + ";");
            ResultSet resultSet = statement.executeQuery(getComandoSQL());
            Set<T> lista = new HashSet<>();
            while (resultSet.next()) {
                lista.add(converter(resultSet));
            }
            return lista;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deletar(Integer id) {
        setComandoSQL("DELETE FROM " + tabela + " WHERE id = ?");
        try (PreparedStatement statement = connection.prepareStatement(getComandoSQL())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T buscarUm(Integer id) {
        setComandoSQL("SELECT * FROM " + tabela + " WHERE id = ?");
        try (PreparedStatement statement = connection.prepareStatement(getComandoSQL())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return converter(resultSet);
            }
            throw new NoSuchElementException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    public abstract T converter(ResultSet resultSet) throws SQLException;
}
