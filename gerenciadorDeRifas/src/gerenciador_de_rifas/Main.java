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
		System.out.println("| 6 - Mostrar relatório                        |");
		System.out.println("| 7 - Mostrar Ranking de vendedores            |");
		System.out.println("| 8 - Listar vendas por vendedor               |");
		System.out.println("| 9 - Sortear um número da rifa                |");
		System.out.println("| 0 - Para sair                                |");		
		return lerInteiro("| Digite a opção desejada:                     |");
	}
	
	static void opcaoCriarRifa() {
		
	}
	
	static void opcaoCadastrarVendedor() {
		
	}
	
	static void opcaoRealizarVenda() {
		
	}
	
	static void opcaoBuscarVendedorPorNome() {
		
	}
	
	static void opcaoBuscarCompradorPorNome() {
		
	}
	
	static void opcaoMostrarRelatorio() {
		
	}
	
	
	//static String opcaoMostrarRifa() {
		//return "Em processo...";
		
	//}
	
	static void opcaoRankingDeVendedores() {
		
	}
	
	static void opcaoListarVendasPorVendedor() {
		
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
					opcaoMostrarRelatorio();
					break;
				
				case 7:
					opcaoRankingDeVendedores();
					break;
					
				case 8:
					opcaoListarVendasPorVendedor();
					break;
					
				case 9:
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
