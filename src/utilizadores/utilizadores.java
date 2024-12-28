package utilizadores;

import java.util.Objects;

public class utilizadores {
	private String username;
	private String password;
	private String nome;
	private boolean estado_ativo;
	private String email;
	private String tipo;
	private String nif;
	private String morada;
	private String telefone;
	
//construtor
	
	public utilizadores(String username,String password, String nome, String email, String tipo, String nif, String morada, String telefone) {
		this.username = username;
		this.password =password;
		this.nome = nome;
		this.estado_ativo = true; //verificar
		this.email = email;
		this.tipo = tipo;
		this.nif = nif;
		this.morada = morada;
		this.telefone = telefone;
		
	}
	
//gets
	
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
	public String getNif() {
		return nif;
	}
	public String getmorada() {
		return morada;
	}
	public String getTelefone() {
		return telefone;
	}
	//sets
	public String setEmail() {
		return email;
	}
	public String setNif() {
		return nif;
	}
	public String setmorada() {
		return morada;
	}
	public String setTelefone() {
		return telefone;
	}
}
