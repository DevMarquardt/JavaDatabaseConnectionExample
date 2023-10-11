import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Cadastrando um Usuário com um carro.
        Usuario usuario = new Usuario(31, "Toretto", "careca123", 50, new Carro(1, "Dodge", "Preto", "Charger", 100000.0, 1979));

        try (ICrud<Carro, Integer> crudCarro = new CarroDAO();
             ICrud<Usuario, Integer> crudUsuario = new UsuarioDAO();) {
            try {
                crudCarro.buscarUm(usuario.getCarro().getId());
            } catch (NoSuchElementException e) {
                crudCarro.inserir(usuario.getCarro());
            }
            crudUsuario.inserir(usuario);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}