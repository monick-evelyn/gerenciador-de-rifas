package gerenciador_de_rifas;

public class Vendedor {
	String nome;
	int qtdNumerosVendidos;
	
	Vendedor(String nome){
		this.nome = nome;
		this.qtdNumerosVendidos = 0;
	}
	
	void adicionarVenda(int quantidade) {
		qtdNumerosVendidos += quantidade;
	}
	
	public String toString() {
		return "Vendedor: " + nome +
				"\nNúmeros vendidos: " + qtdNumerosVendidos;
	}
}
