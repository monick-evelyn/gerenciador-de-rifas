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
				vendedor.toString() +
				comprador.toString() +
				"\nForma de pagamento: " + formaDePagamento;
	}
}
