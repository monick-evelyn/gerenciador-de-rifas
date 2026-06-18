package gerenciador_de_rifas;

public class Bilhete {
	int numero;
	Comprador comprador;
	Vendedor vendedor;
	String formaDePagamento;
	
	Bilhete(int numero, Comprador comprador, Vendedor vendedor, String formaDePagamento) {
		this.numero = numero;
		this.comprador = comprador;
		this.vendedor = vendedor;
		this.formaDePagamento = formaDePagamento;
	}
	
	public String toString() {
		return "\nNúmero: " + numero + "\n"+
				"\nVendedor: " + vendedor.nome +
				"\nComprador: " + comprador.nome +
				"\nTelefone de Comprador: " + comprador.telefone +
				"\nForma de pagamento: " + formaDePagamento;
	}
}
