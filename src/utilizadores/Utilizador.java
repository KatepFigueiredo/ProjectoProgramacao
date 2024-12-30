package utilizadores;

import java.util.Objects;

public class Utilizador {
	private String username;
	private String password;
	private String nome;
	private boolean estado_ativo;
	private String email;
	private String tipo;
	
//construtor
	
	public Utilizador(String username,String password, String nome, String email, String tipo) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.estado_ativo = true; //verificar
		this.email = email;
		this.tipo = tipo;
		
	}
	
//getters
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getNome() {
		return nome;
	}
	public boolean isEstado_ativo() {
		return estado_ativo;
	}
	public String getEmail() {
		return email;
	}
	public String getTipo() {
		return tipo;
	}
	
	
	//setters
	public String setEmail() {
		return email;
	}
	
	
	
	//Método para só atualizar a própria informação
	public boolean atualizarInformacao(String password, String nome, String email) {
		
		this.password = password;
		this.nome = nome;
		this.email = email;
		return true;
		
	}
	
}
