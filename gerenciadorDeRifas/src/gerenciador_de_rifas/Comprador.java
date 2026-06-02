package gerenciador_de_rifas;

public class Comprador {
	String nome;
	String telefone;
	
	Comprador(String nome, String telefone){
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public String toString(){
		return "\nComprador: " + nome +
				"\nTelefone: " + telefone;
	}
}
