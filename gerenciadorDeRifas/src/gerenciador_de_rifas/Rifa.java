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
		for (int i = 0; i < bilhetes.length; i++) {
			if ((i+1) == numero) {
				if (verificarNumeroDisponivel(numero)) {
					Bilhete bilhete = new Bilhete (numero, comprador, vendedor, formaDePagamento);
					bilhetes[i] = bilhete;
					vendedor.qtdNumerosVendidos++;
					totalBilhetes++;
					
					return "Bilhete vendido com sucesso!";
				}
			}
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
		return bilhetes.length - calcularQtdBilhetesVendidos();	
	}
	
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
		lista += "================================================\n";
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
		lista += "====================================================\n";
		return lista;
	}

	String listarBilhetesEmMatriz() {
		int controlador = 0;
		
		if(bilhetes.length<10) {
			String bilhetesEmMatriz = "========================= VISUALIZAÇÃO GERAL DE BILHETES =========================\n";
			for(int i=0; i< bilhetes.length;i++) {
				bilhetesEmMatriz+= " "+(controlador+1)+" ";
				controlador++;
			}
			bilhetesEmMatriz += "\n==================================================================================\n";
			return bilhetesEmMatriz;
		}
		
		int colunas = 10;
		int linhas = bilhetes.length / colunas;
		
		if(bilhetes.length/colunas!=0) {
			linhas++;
		}
		
		String bilhetesEmMatriz = "========================= VISUALIZAÇÃO GERAL DE BILHETES =========================\n";
		
		controlador = 0;
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if(controlador<bilhetes.length) {
					if (bilhetes[controlador] != null) {
						bilhetesEmMatriz += "    X   ";
					} else {
						if ((controlador+1) < 10) {
							bilhetesEmMatriz += "    " + (controlador+1) + "   ";
						} else {
							bilhetesEmMatriz += "   " + (controlador+1) + "   ";
						}
					}
				}
				
				controlador++;
			}
			bilhetesEmMatriz += "\n";
		}
		bilhetesEmMatriz += "=====================================================================================\n";
		return bilhetesEmMatriz;
	}
	
	void listarRelatorioGeral() {
		System.out.println("\n============================== RELATÓRIO GERAL ==============================");
		
		System.out.println(toString());
		System.out.println("\nPROGRESSO: ==================================================================");
		System.out.printf("Meta de arrecadação: R$ %.2f%n",metaArrecadacao);
		System.out.printf("Valor Arrecadado:    R$ %.2f%n",calcularValorArrecadado());
		
		System.out.println("\nBilhetes vendidos: " + calcularQtdBilhetesVendidos());
		System.out.println("Bilhetes disponíveis: " + calcularQtdBilhetesDisponiveis());
		
		System.out.printf("\nProgresso: %.1f%%%n", calcularProgressoEmPorcentagem());
		System.out.printf("Restante para meta: %.1f%%%n", calcularRestanteEmPorcentagem());
		
		System.out.println(listarBilhetesEmMatriz());
		
		System.out.println("===========================================================================");
	}
	
void listarVendas() {
		String lista = "\n========== BILHETES VENDIDOS ==========\n";
		lista += "Bilhetes vendidos: "+ calcularQtdBilhetesVendidos() + "\n";
		
		boolean temBilhetes = false;
		
		for (int i = 0; i < bilhetes.length; i++) {
			if (bilhetes[i] != null) {
				temBilhetes = true;
				lista += bilhetes[i].toString() + "\n";
			}
		}
		
		if (!temBilhetes) {
			System.out.println("Não há bilhetes vendidos.");
		}
		
		System.out.println(lista);
	}
	
	String sortearNumero() {
		int quantidadeBilhetesVendidos=0;
		
		for(int i=0; i<bilhetes.length;i++) {
			if(bilhetes[i]!=null) {
				quantidadeBilhetesVendidos++;
			}
		}
		if (quantidadeBilhetesVendidos==0) {
			return "Nenhum bilhete foi vendido ainda, não é possível sortear!";
		}
		Bilhete[] bilhetesPreenchidos=new Bilhete[quantidadeBilhetesVendidos];
		
		int contadorAux=0;
		for(int i=0; i<bilhetes.length;i++) {
			if(bilhetes[i]!=null) {
				bilhetesPreenchidos[contadorAux] = bilhetes[i];
				contadorAux++;
			}
		}
		
		java.util.Random geradorAleatorio = new java.util.Random();
		
		int posicaoSorteada = geradorAleatorio.nextInt(quantidadeBilhetesVendidos);
		
		Bilhete bilheteGanhador= bilhetesPreenchidos[posicaoSorteada];
		
		String resultado="";
		resultado += "\n=================================================\n";
	    resultado += "        NÚMERO SORTEADO COM SUCESSO!           \n";
	    resultado += "=================================================\n";
	    resultado += "Número do Bilhete: " + bilheteGanhador.numero;
	    resultado += bilheteGanhador.comprador.toString() + "\n";
	    resultado += "Vendedor responsável: " + bilheteGanhador.vendedor.nome+"\n";
	    resultado += "=================================================\n";
	    
	    return resultado;
	}
	
	public String toString() {
		return "Rifa " +
				"\nPrêmio: " + premio + 
				"\nValor por bilhete: " + valorPorBilhete + 
				"\nTotal de bilhetes vendidos: " + totalBilhetes +
				"\nMeta de arrecadação: " + metaArrecadacao;
	}
}
