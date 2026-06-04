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
		return lerInteiro("| Digite a opção desejada:                     |");
	}
	
	static void opcaoCriarRifa(SistemaRifa sistema) {
		
		String premio=lerTexto("Digite o prêmio: ");
		
		Double valorBilhete=lerDouble("Digite o valor do bilhete: ");
	
		int capacidade=lerInteiro("Digite a capacidade: ");
		
		sistema.criarRifa(premio, valorBilhete, capacidade);
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
	
	static void opcaoListarVendasPorVendedor(SistemaRifa sistema) {
		lerTexto("Digite o nome do Vendedor: ");
		String nomeVendedor=leitor.nextLine();

		Vendedor vendedor = opcaoBuscarVendedorPorNome(sistema);
		
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
	
	static String opcaoSortearNumeroRifa(SistemaRifa sistema,Rifa rifa) {
		return sistema.sortearNumero(rifa);
	}
	
	
	static String lerTexto(String mensagem) {
			System.out.println(mensagem);
			
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
		String opcaoCadastrarSistema=lerTexto("Deseja cadastrar um sistema S/N? ");

		if(opcaoCadastrarSistema.equalsIgnoreCase("S")) {
			
			int capacidade=lerInteiro("Digite a capacidade de vendedores: ");
			
			SistemaRifa novoSistema=new SistemaRifa(capacidade);

			lerTexto("Sistema cadastrado com sucesso!");
			
			int opcaoEscolhaDoUsuario;
			do {
				opcaoEscolhaDoUsuario = mostrarMenu();
				
				switch(opcaoEscolhaDoUsuario) {
					case 1:
						opcaoCriarRifa(novoSistema);
						break;
					
					case 2:
						opcaoCadastrarVendedor(novoSistema);
						break;
						
					case 3:
						opcaoRealizarVenda(novoSistema, novoSistema.rifa);
						break;
					
					case 4:
						opcaoBuscarVendedorPorNome(novoSistema);
						break;
						
					case 5:
						opcaoBuscarCompradorPorNome(novoSistema, novoSistema.rifa);
						break;
					
					case 6:
						opcaoVisualizarNumerosEmGrade(novoSistema);
						break;
					
					case 7:
						opcaoMostrarRelatorio(novoSistema);
						break;
						
					case 8:
						opcaoRankingDeVendedores(novoSistema);
						break;
						
					case 9:
						opcaoListarVendasPorVendedor(novoSistema);
						break;
					case 10:
						opcaoSortearNumeroRifa(novoSistema, novoSistema.rifa);
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
		else {
			lerTexto("O programa foi encerrao devido a não ter um sistema cadastrado.");
		}
		
		
	}
}
