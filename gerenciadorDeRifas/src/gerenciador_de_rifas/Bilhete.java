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
		return "Número: " + numero +
				vendedor.toString() +
				comprador.toString() +
				"Forma de pagamento: " + formaDePagamento;
	}
}
