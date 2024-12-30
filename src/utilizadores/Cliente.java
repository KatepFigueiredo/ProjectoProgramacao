package utilizadores;

//Classe Cliente que herda de Utilizador
public class Cliente extends Utilizador {
	
	private String nif;
	private String morada;
	private String telefone;

	
	//Construtor
	
	public Cliente(String login, String password, String nome, boolean estado, String email, String nif, String morada, String telefone, String tipo) {
		super(username, password, nome, estado, email, tipo);
		this.nif = nif;
		this.morada = morada;
		this.telefone = telefone;
		
	}
	
	//getters e setters para o cliente
	
	public String getNif() {
        return nif;
    }
	
	public void setNif(String nif) {
        this.nif = nif;
    }
	
	public String getMorada() {
        return morada;
    }
	
	public void setMorada(String morada) {
        this.morada = morada;
    }
	
	public String getTelefone() {
        return telefone;
    }
	
	public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
	
	


}
