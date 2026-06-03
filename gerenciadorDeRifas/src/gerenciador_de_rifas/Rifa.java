package gerenciador_de_rifas;

public class Rifa {
	String premio;
	double valorPorBilhete;
	int totalBilhetes;
	Bilhete[] bilhetes;
	double metaArrecadacao;
	
	Rifa(String premio, double valorPorBilhete, int capacidadeBilhetes){
		this.premio = premio;
		this.valorPorBilhete = valorPorBilhete;
		this.bilhetes = new Bilhete[capacidadeBilhetes];
		this.totalBilhetes = 0;
		this.metaArrecadacao = valorPorBilhete * capacidadeBilhetes;
	}
	
	boolean verificarNumeroDisponivel(int numero) {
		
	}
	
	String cadastrarFormaDePagamento(int opcao) {
		
	}
	
	String venderBilhete(int numero, Comprador comprador, Vendedor vendedor, String formaDePagamento) {
		
	}
	
	int calcularQtdBilhetesVendidos() {
		
	}
	
	double calcularValorArrecadado() {
	
	}
	
	double calcularProgressoDeMeta() {
	
	}
	
	
}
