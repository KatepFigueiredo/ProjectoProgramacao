package utilizadores;

import java.util.Objects;

public class Utilizador {
	private String username;
	private String password;
	private String nome;
	private boolean estado; //true = ativo, false = inativo
	private String email;
	private String tipo; //Administrador, Tecnico, Cliente
	
//construtor
	
	public Utilizador(String username, String password, String nome, boolean estado, String email, String tipo) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.estado = estado;
        this.email = email;
        this.tipo = tipo;
    }
	
//getters e setters
	
	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

}
	
	
	
