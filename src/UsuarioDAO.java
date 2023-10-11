import java.sql.*;

public class UsuarioDAO extends DAOPadrao<Usuario, Integer> {

    public UsuarioDAO() throws SQLException {
        super("usuarios");
    }

    public void inserir(Usuario usuario) {
        setComandoSQL("INSERT INTO usuarios VALUES(?,?,?,?,?);");
        try (PreparedStatement statement = connection.prepareStatement(getComandoSQL())) {
            statement.setInt(1, usuario.getId());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getSenha());
            statement.setInt(4, usuario.getIdade());
            try {
                statement.setInt(5, usuario.getCarro().getId());
            } catch (NullPointerException e) {
                statement.setNull(5, 0);
            }
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void editar(Usuario usuario) {
        setComandoSQL("UPDATE usuarios SET nome = ?, senha = ?, idade = ?, id_carro = ? WHERE id_usuario = ?");
        try (PreparedStatement statement = connection.prepareStatement(getComandoSQL())) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setInt(3, usuario.getIdade());
            try {
                statement.setInt(4, usuario.getCarro().getId());
            } catch (NullPointerException e) {
                statement.setNull(4, 0);
            }
            statement.setInt(5, usuario.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Usuario converter(ResultSet resultSet) throws SQLException {
        return new Usuario(resultSet);
    }
}