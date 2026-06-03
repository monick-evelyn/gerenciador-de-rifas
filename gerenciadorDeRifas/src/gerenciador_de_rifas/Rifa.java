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
		//TODO: Verificar se numero existe no sistemaRifa
		for (int i = 0; i < bilhetes.length; i++) {
			if ((i+1) == numero) {
				if (bilhetes[i] == null) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	String cadastrarFormaDePagamento(int opcao) {
		switch (opcao) {
		case 1:
			return "PIX";
		case 2:
			return "Dinheiro";
		case 3:
			return "Outro";
		default:
			return "";
		}
	}
	
	String venderBilhete(int numero, Comprador comprador, Vendedor vendedor, String formaDePagamento) {
		//TODO: Verificar se esse método pode ser dividido em mais funções
		for (int i = 0; i < bilhetes.length; i++) {
			if ((i+1) == numero) {
				if (verificarNumeroDisponivel(numero)) {
					Bilhete bilhete = new Bilhete (numero, comprador, vendedor, formaDePagamento);
					bilhetes[i] = bilhete;
					vendedor.qtdNumerosVendidos++;
					totalBilhetes++;
					
					return "Bilhete vendido com sucesso!";
				}
				return "Bilhete com o número " + numero + " não está disponível.";
			}
			return "Bilhete com o número " + numero + " não encontrado.";
		}
		return "Não foi possível vender o bilhete";
	}
	
	int calcularQtdBilhetesVendidos() {
		int contadorBilhetesVendidos = 0;
		for (int i = 0; i < bilhetes.length; i++) {
			if (bilhetes[i] != null) {
				contadorBilhetesVendidos++;
			}
		}
		return contadorBilhetesVendidos;
	}
	
	int calcularQtdBilhetesDisponiveis() {
		return calcularQtdBilhetesDisponiveis() - bilhetes.length;
	}
	
	//TODO: Formatação de retorno double na main
	double calcularValorArrecadado() {
		return calcularQtdBilhetesVendidos() * valorPorBilhete;
	}
	
	double calcularProgressoEmPorcentagem() {
		double progressoPorcentagem = (100 * calcularValorArrecadado())/metaArrecadacao;
		return progressoPorcentagem;
	}
	
	double calcularRestanteEmPorcentagem() {
		if (calcularProgressoEmPorcentagem() >= 100) {
			return 0.0;
		}
		double valorRestante = metaArrecadacao - calcularValorArrecadado() ;
		double progressoPorcentagem = (100 * valorRestante)/metaArrecadacao;
		return progressoPorcentagem;
	}
	
	String listarBilhetesVendidos() {
		String lista = "========== LISTA DE BILHETES VENDIDOS ==========\n"+
						"Total: " + calcularQtdBilhetesVendidos();
		
		for (int i = 0; i < bilhetes.length; i++) {
			if (bilhetes[i] != null) {
				lista += "\n" + bilhetes[i].toString();
			}
		}
		return lista;
	}
	
	String listarBilhetesDisponiveis() {
		String lista = "========== LISTA DE BILHETES DISPONÍVEIS ==========\n"+
				"Total: " + calcularQtdBilhetesDisponiveis();
		for (int i = 0; i < bilhetes.length; i++) {
			if (bilhetes[i] == null) {
				lista += "\nNúmero disponível: " + (i+1);
				}
			}
		return lista;
	}

	//TODO: Listar bilhetes gerais em matriz
	
	public String toString() {
		return "Rifa " +
				"\nPrêmio: " + premio + 
				"\nValor por bilhete: " + valorPorBilhete + 
				"\nTotal de bilhetes vendidos: " + totalBilhetes +
				"\nMeta de arrecadação: " + metaArrecadacao;
	}
}
