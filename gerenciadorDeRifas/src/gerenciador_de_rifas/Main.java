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
			Double valorBilhete=lerDouble("Digite o valor do bilhete: ");
		
			int capacidade=lerInteiro("Digite a quantidade de bilhetes da rifa: ");
			sistema.criarRifa(premio, valorBilhete, capacidade);
			
			if (sistema.rifa != null) {
				System.out.println("Rifa criada com sucesso!");
			}
		}
	}
	
	static void opcaoCadastrarVendedor(SistemaRifa sistema) {
		if (sistema.totalVendedores < sistema.vendedores.length) {
			String nomeVendedor=lerTexto("Digite o nome: ");
			if (sistema.cadastrarVendedor(nomeVendedor)) {
				System.out.println("Vendedor cadastrado com sucesso!");
			} else {
				System.out.println("Erro: Não foi possível cadastrar o vendedor.");
			}
		} else {
			System.out.println("Erro: Capacidade de vendedores atingida.");
		}
	}
	
	static void opcaoRealizarVenda(SistemaRifa sistema, Rifa rifa) {
		if(rifa==null ) {
			System.out.println("Erro: é necessário criar uma rifa primeiro!");
			mostrarMenu();
			return;
		}
		if(rifa.calcularQtdBilhetesDisponiveis()==0) {
			System.out.println("Erro: não existem bilhetes disponíveis!");
			mostrarMenu();
			return;
		}
		if(sistema.totalVendedores==0) {
			System.out.println("Erro: não existem vendedores disponíveis!");
			mostrarMenu();
			return;
		}
		
		int numeroBilhete=lerInteiro("Digite o numero do bilhete: ");
		boolean bilheteInvalido=true;
		
		while(bilheteInvalido) {
			if(numeroBilhete<1 || numeroBilhete> rifa.bilhetes.length) {
				numeroBilhete=lerInteiro("Erro: O bilhete já foi vendido ou não existe! Tente novamente: ");
			}else if(rifa.verificarNumeroDisponivel(numeroBilhete)==false){
				numeroBilhete=lerInteiro("Erro: O bilhete já foi vendido ou não existe! Tente novamente: ");
			}else {
				bilheteInvalido=false;
			}
		}
		
		String nomeVendedor=lerTexto("Digite o nome do vendedor: ");
		Vendedor vendedorAuxiliar=sistema.buscarVendedorPorNome(nomeVendedor);
		while(vendedorAuxiliar==null) {
			nomeVendedor=lerTexto("Erro: O vendedor não existe, digite novamente!");
			vendedorAuxiliar=sistema.buscarVendedorPorNome(nomeVendedor);
		}
		
		String nomeComprador=lerTexto("Digite o nome do comprador: ");
		
		String telefone=lerTexto("Digite o telefone: ");
		
		String formaPagamento=lerTexto("Digite a forma de pagamento: ");

		sistema.realizarVenda(rifa, numeroBilhete, nomeComprador, telefone, formaPagamento, nomeVendedor);
		
		System.out.println("Venda realizada com sucesso!");
	}
	
	static String opcaoBuscarVendedorPorNome(SistemaRifa sistema) {
		
		String nomeVendedor=lerTexto("Digite o nome do Vendedor: ");
		
		Vendedor vendedorEncontrado= sistema.buscarVendedorPorNome(nomeVendedor);
		
		if(vendedorEncontrado==null) {
			return "Erro: O vendedor não foi encontrado!";
		}
		
		String lista="";
		lista+=vendedorEncontrado.toString();
		return lista;
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
		int numero=-1;
		boolean entradaValida=false;
		
		while(entradaValida==false) {
			System.out.print(mensagem);
			
			if(leitor.hasNextInt()) {
				numero=leitor.nextInt();
				leitor.nextLine();
				entradaValida=true;
			}else {
				System.out.println("Erro: Você deve digitar apenas números inteiros!");
				leitor.nextLine();
			}
		}		
		return numero;
	}
	
	static double lerDouble(String mensagem) {
		double numero=-1;
		boolean entradaValida=false;
		
		while(entradaValida==false) {
			System.out.print(mensagem);
			
			if(leitor.hasNextDouble()) {
				numero=leitor.nextDouble();
				leitor.nextLine();
				entradaValida=true;
			}else {
				System.out.println("Erro: Você deve digitar apenas números!");
				leitor.nextLine();
			}
		}
		
		return numero;
	}
	
	static SistemaRifa criarSistema(String opcao) {
		while(!opcao.equalsIgnoreCase("S") && !opcao.equalsIgnoreCase("N")) {
			opcao=lerTexto("ERRO: Opção inválida, por favor digite (S/N): ");
		}
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
