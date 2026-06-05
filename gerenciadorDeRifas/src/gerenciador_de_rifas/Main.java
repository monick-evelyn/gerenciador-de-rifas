package gerenciador_de_rifas;

import java.util.Scanner;

public class Main {
	
	static Scanner leitor=new Scanner(System.in);
	
	static int mostrarMenu() {
		System.out.println("\n|----------------MENU DE OPÇÕES----------------|");
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
		System.out.println("|----------------------------------------------|");
		return lerInteiro("| Digite a opção desejada: ");
	}
	
	static void opcaoCriarRifa(SistemaRifa sistema) {
		if (sistema.rifa != null) {
			System.out.println("Já existe uma rifa cadastrada.");
		} else {
			String premio=lerTexto("Digite o prêmio: ");
			lerTexto("");
			Double valorBilhete=lerDouble("Digite o valor do bilhete: ");
		
			int capacidade=lerInteiro("Digite a quantidade de bilhetes da rifa: ");
			sistema.criarRifa(premio, valorBilhete, capacidade);
			
			if (sistema.rifa != null) {
				System.out.println("Rifa criada com sucesso!");
			}
		}
	}
	
	static void opcaoCadastrarVendedor(SistemaRifa sistema) {
		
		String nomeVendedor=lerTexto("Digite o nome: ");
		
		sistema.cadastrarVendedor(nomeVendedor);
	}
	
	static void opcaoRealizarVenda(SistemaRifa sistema, Rifa rifa) {
		
		int numeroBilhete=lerInteiro("Digite o numero do bilhete: ");
		
		String nomeComprador=lerTexto("Digite o nome do comprador: ");
		
		String telefone=lerTexto("Digite o telefone: ");
		
		String formaPagamento=lerTexto("Digite a forma de pagamento: ");

		String nomeVendedor=lerTexto("Digite o nome do vendedor: ");
		
		sistema.realizarVenda(rifa, numeroBilhete, nomeComprador, telefone, formaPagamento, nomeVendedor);
	}
	
	static Vendedor opcaoBuscarVendedorPorNome(SistemaRifa sistema) {
		String nomeVendedor=lerTexto("Digite o nome do Vendedor: ");
		
		return sistema.buscarVendedorPorNome(nomeVendedor);
	}
	
	static Comprador opcaoBuscarCompradorPorNome(SistemaRifa sistema, Rifa rifa) {
		
		String nomeComprador=lerTexto("Digite o nome do Comprador: ");
		
		return sistema.buscarCompradorPorNome(rifa,nomeComprador);
	}
	
	static void opcaoVisualizarNumerosEmGrade(SistemaRifa sistema) {
		System.out.println(sistema.rifa.listarBilhetesEmMatriz());
	}
	
	static void opcaoMostrarRelatorio(SistemaRifa sistema) {
		sistema.listarRelatorioGeral();
		
	}
	
	//TODO: Testar o ranking
	static void opcaoRankingDeVendedores(SistemaRifa sistema) {
		sistema.carregarRankingDeVendedores();
	}
	
	static void opcaoListarVendasPorVendedor(SistemaRifa sistema) {
		lerTexto("Digite o nome do Vendedor: ");
		String nomeVendedor=leitor.nextLine();

		sistema.listarVendasPorVendedor(nomeVendedor);
	}
	
	static String opcaoSortearNumeroRifa(SistemaRifa sistema,Rifa rifa) {
		return sistema.sortearNumero(rifa);
	}
	
	
	static String lerTexto(String mensagem) {
			System.out.print(mensagem);
			
			String texto=leitor.nextLine();
			return texto;
	}
	
	static int lerInteiro(String mensagem) {
		System.out.print(mensagem);
		
		int numero=leitor.nextInt();
		
		
		return numero;
	}
	
	static double lerDouble(String mensagem) {
		System.out.print(mensagem);
		
		double valor=leitor.nextDouble();
		
		return valor;
	}
	
	static SistemaRifa criarSistema(String opcao) {
		if (opcao.equalsIgnoreCase("S")) {
			int capacidade = lerInteiro("Digite a capacidade de vendedores: ");
			SistemaRifa novoSistema = new SistemaRifa(capacidade);
			System.out.println("Sistema cadastrado com sucesso!");
			return novoSistema;
		}
		
		if (opcao.equalsIgnoreCase("N")) {
			System.out.println("Encerrando o sistema...");
			return null;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		String opcaoCadastrarSistema=lerTexto("Deseja cadastrar um sistema (S/N)? ");
		
		SistemaRifa sistema = criarSistema(opcaoCadastrarSistema);
		
		int opcaoEscolhaDoUsuario;
		if (sistema != null) {
			do {
				opcaoEscolhaDoUsuario = mostrarMenu();
				
				switch(opcaoEscolhaDoUsuario) {
					case 1:
						opcaoCriarRifa(sistema);
						break;
					
					case 2:
						opcaoCadastrarVendedor(sistema);
						break;
						
					case 3:
						opcaoRealizarVenda(sistema, sistema.rifa);
						break;
					
					case 4:
						opcaoBuscarVendedorPorNome(sistema);
						break;
						
					case 5:
						opcaoBuscarCompradorPorNome(sistema, sistema.rifa);
						break;
					
					case 6:
						opcaoVisualizarNumerosEmGrade(sistema);
						break;
					
					case 7:
						opcaoMostrarRelatorio(sistema);
						break;
						
					case 8:
						opcaoRankingDeVendedores(sistema);
						break;
						
					case 9:
						opcaoListarVendasPorVendedor(sistema);
						break;
					case 10:
						opcaoSortearNumeroRifa(sistema, sistema.rifa);
						break;
					
					case 0:
						System.out.println("Saindo do sistema...");
						break;
						
					default:
						System.out.println("Opção inválida!");
						break;
				}
			} while(opcaoEscolhaDoUsuario != 0);
		}
	}
}
