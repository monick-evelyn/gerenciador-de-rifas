package gerenciador_de_rifas;

import java.util.Scanner;

public class Main {
	
	static Scanner leitor=new Scanner(System.in);
	
	static int mostrarMenu() {
		System.out.println("|----------------MENU DE OPÇÕES----------------|");
		System.out.println("| 1 - Criar Rifa                               |");
		System.out.println("| 2 - Cadastrar vendedor                       |");
		System.out.println("| 3 - Realizar venda                           |");
		System.out.println("| 4 - Buscar Vendedor por nome                 |");
		System.out.println("| 5 - Buscar Comprador por nome                |");
		System.out.println("| 6 - Visualizar números em grade              |");
		System.out.println("| 7 - Mostrar relatório geral                  |");
		System.out.println("| 8 - Mostrar Ranking de vendedores            |");
		System.out.println("| 9 - Listar vendas por vendedor               |");
		System.out.println("| 10 - Sortear um número da rifa               |");
		System.out.println("| 0 - Para sair                                |");		
		return lerInteiro("| Digite a opção desejada:                      |");
	}
	
	static void opcaoCriarRifa() {
		
	}
	
	static void opcaoCadastrarVendedor() {
		
	}
	
	static void opcaoRealizarVenda() {
		
	}
	
	static Vendedor opcaoBuscarVendedorPorNome(SistemaRifa sistema, String nomeVendedor) {
		
	}
	
	static Comprador opcaoBuscarCompradorPorNome(SistemaRifa sistema, String nomeComprador) {
		
	}
	
	static void opcaoVisualizarNumerosEmGrade(SistemaRifa sistema) {
		System.out.println(sistema.rifa.listarBilhetesEmMatriz());
	}
	
	static void opcaoMostrarRelatorio(SistemaRifa sistema) {
		System.out.println("==================== RELATÓRIO GERAL ====================");
		
		System.out.println("\nPROGRESSO: ==============================================");
		System.out.println("Meta de arrecadação: R$ %.2f%n" + sistema.rifa.metaArrecadacao);
		System.out.println("Valor Arrecadado:    R$ %.2f%n" + sistema.rifa.calcularValorArrecadado());
		
		System.out.println("\nBilhetes vendidos: " + sistema.rifa.calcularQtdBilhetesVendidos());
		System.out.println("Bilhetes disponíveis: " + sistema.rifa.calcularQtdBilhetesDisponiveis());
		
		System.out.println("\nProgresso: %.1f%%%n" + sistema.rifa.calcularProgressoEmPorcentagem());
		System.out.println("Restante para meta: %.1f%%%n" + sistema.rifa.calcularRestanteEmPorcentagem());
		
		System.out.println("\nEQUIPE: =================================================");
		System.out.println("Total de Vendedores: " + sistema.totalVendedores);
		
		System.out.println("\nVISUALIZAÇÃO GERAL: ======================================");
		System.out.println(sistema.rifa.listarBilhetesEmMatriz());
		
		System.out.println("=========================================================");
		
	}
	
	//TODO: Testar o ranking
	static void opcaoRankingDeVendedores(SistemaRifa sistema) {
		Vendedor vendedores[] = sistema.vendedores;
		Vendedor vendedoresOrdenado[] = new Vendedor[sistema.totalVendedores];
		
		for (int i = 0; i < sistema.totalVendedores; i++) {
			vendedoresOrdenado[i] = vendedores[i];
		}
		
		for (int i = 0; i < vendedoresOrdenado.length - 1; i++) {
			
			for (int j = 0; j < vendedoresOrdenado.length - 1 - i; j++) {
				
				if (vendedoresOrdenado[j].qtdNumerosVendidos < vendedoresOrdenado[j+1].qtdNumerosVendidos) {
					Vendedor vendedorAux = vendedoresOrdenado[j];
					vendedoresOrdenado[j] = vendedoresOrdenado[j+1];
					vendedoresOrdenado[j+1] = vendedorAux;
				}
			}
		}
		
		System.out.println("==================== RANKING DE VENDEDORES ====================");
		for (int i = 0; i < vendedoresOrdenado.length; i++) {
			System.out.println((i+1) + "º Lugar: " + vendedoresOrdenado[i].nome + " | Bilhetes vendidos: " + vendedoresOrdenado[i].qtdNumerosVendidos);
		}
		System.out.println("===============================================================");
	}
	
	static void opcaoListarVendasPorVendedor(SistemaRifa sistema, String nomeVendedor) {
		Vendedor vendedor = opcaoBuscarVendedorPorNome(sistema, nomeVendedor);
		
		if (vendedor == null) {
			System.out.println("Erro: Vendedor " + nomeVendedor + " não encontado");
		}
		
		String lista = "\n========== BILHETES POR VENDEDOR ==========\n";
		lista += vendedor.toString() + "\n";
		
		boolean temBilhetes = false;
		boolean temVendas = false;
		
		for (int i = 0; i < sistema.rifa.bilhetes.length; i++) {
			if (sistema.rifa.bilhetes[i] != null) {
				temBilhetes = true;
				if (sistema.rifa.bilhetes[i].vendedor.equals(vendedor)) {
					temVendas = true;
					lista += "Número: " + sistema.rifa.bilhetes[i].numero +
							"Comprador: " + sistema.rifa.bilhetes[i].comprador.nome + "\n";
				}
			}
		}
		
		if (!temBilhetes) {
			System.out.println("Não há bilhetes vendidos.");
		}
		if (!temVendas) {
			System.out.println("O vendedor " + nomeVendedor + " ainda não realizou nenhuma venda.");
		}
		
		System.out.println(lista);
	}
	
	static void opcaoSortearNumeroRifa() {
		
	}
	
	
	static String lerTexto(String mensagem) {
			System.out.print(mensagem);
			
			String texto=leitor.nextLine();
			return texto;
	}
	
	static int lerInteiro(String mensagem) {
		System.out.println(mensagem);
		
		int numero=leitor.nextInt();
		
		return numero;
	}
	
	static double lerDouble(String mensagem) {
		System.out.println(mensagem);
		
		double valor=leitor.nextDouble();
		
		return valor;
	}
	
	
	public static void main(String[] args) {
		int opcao;
		do {
			opcao = mostrarMenu();
			
			switch(opcao) {
				case 1:
					opcaoCriarRifa();
					break;
				
				case 2:
					opcaoCadastrarVendedor();
					break;
					
				case 3:
					opcaoRealizarVenda();
					break;
				
				case 4:
					opcaoBuscarVendedorPorNome();
					break;
					
				case 5:
					opcaoBuscarCompradorPorNome();
					break;
				
				case 6:
					opcaoVisualizarNumerosEmGrade();
					break;
				
				case 7:
					opcaoMostrarRelatorio();
					break;
					
				case 8:
					opcaoRankingDeVendedores();
					break;
					
				case 9:
					opcaoListarVendasPorVendedor();
					break;
				case 10:
					opcaoSortearNumeroRifa();
					break;
				
				case 0:
					System.out.println("Saindo do sistema...");
					break;
					
				default:
					System.out.println("Opção inválida!");
					break;
			}
		} while(opcao != 0);
	}
}
