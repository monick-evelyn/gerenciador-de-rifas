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
	
	static void opcaoMostrarRelatorio() {
		 
	}
	
	static void opcaoRankingDeVendedores() {
		
	}
	
	static String opcaoListarVendasPorVendedor(SistemaRifa sistema, String nomeVendedor) {
		Vendedor vendedor = opcaoBuscarVendedorPorNome(sistema, nomeVendedor);
		
		if (vendedor == null) {
			return "Erro: Vendedor " + nomeVendedor + " não encontado";
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
			return "Não há bilhetes vendidos.";
		}
		if (!temVendas) {
			return "O vendedor " + nomeVendedor + " ainda não realizou nenhuma venda";
		}
		
		return lista;
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
			opcao=mostrarMenu();
			
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
		} while(opcao!=0);
	}
}
