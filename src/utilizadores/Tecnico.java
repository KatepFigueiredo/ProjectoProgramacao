package utilizadores;

//Classe Tecnico que herda de Utilizador
public class Tecnico extends Utilizador {
	
	private String nif;
    private String morada;
    private String contacto;

	
	//Construtor
	
    public Tecnico(String username, String password, String nome, boolean estado, String email, String nif, String morada, String contacto) {
        super(username, password, nome, estado, email, "Tecnico");
        this.nif = nif;
        this.morada = morada;
        this.contacto = contacto;
    }
	
	
	//getters e setters
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }


}
