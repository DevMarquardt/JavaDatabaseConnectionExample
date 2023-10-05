import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/aulaJavaBancoDados";
        String nome = "root";
        String senha = "root";
        try {
            Connection connection = DriverManager.getConnection(url, nome, senha);
            menuInicial(connection);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void menuInicial(Connection connection) {
        int opcao;
        do {
            System.out.println("""
                    [1] - Cadastrar usuario
                    [2] - Buscar todos os usuarios
                    [3] - Buscar usuario especifico
                    [0] - Sair""");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    alocarNoBancoDeDados(connection, cadastrar());
                    break;
                case 2:
                    buscarTodosUsuarios(connection);
                    break;
                case 3:
                    buscarUsuarioEspeciico(connection);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while(opcao!= 0);
    }

    public static void alocarNoBancoDeDados(Connection connection, Usuario usuario) {
        try {
            Statement statement = connection.createStatement();
            String insert = "insert into usuarios values(" +
                    usuario.getId() + ", '" +
                    usuario.getUsuario() + "', '" +
                    usuario.getSenha() + "', '" +
                    usuario.getIdade() + "')";
            statement.execute(insert);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Usuario cadastrar() {
        System.out.println("Digite o id do usuário: ");
        Integer id = sc.nextInt();
        System.out.println("Nome: ");
        String nome = sc.next();
        System.out.println("Senha: ");
        String senha = sc.next();
        System.out.println("Idade: ");
        int idade = sc.nextInt();
        return new Usuario(id, nome, senha, idade);
    }

    public static Set<Usuario> buscarTodosUsuarios(Connection connection) {
        try {
            String select = "select * from usuarios";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            Set<Usuario> usuarios = new HashSet<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                int idade = resultSet.getInt("idade");
                Usuario usuario = new Usuario(id, nome, senha, idade);
                usuarios.add(usuario);
            }
            System.out.println(usuarios);
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Usuario buscarUsuarioEspeciico(Connection connection) {
        try {
            System.out.println("Qual o id do usuario?: ");
            int id = sc.nextInt();
            String selectSingle = "select * from usuarios where id_usuario = " + id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSingle);
            Usuario usuario = null;
            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("id_usuario");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                int idade = resultSet.getInt("idade");
                usuario = new Usuario(idUsuario, nome, senha, idade);
            }
            System.out.println(usuario);
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
