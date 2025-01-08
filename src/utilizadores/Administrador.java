package utilizadores;

public class Administrador extends Utilizador {

    public Administrador(String username, String password, String nome, boolean estado, String email) {
        super(username, password, nome, estado, email, "Administrador");
    }

}
