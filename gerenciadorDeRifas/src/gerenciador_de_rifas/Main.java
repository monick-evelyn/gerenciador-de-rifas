package gerenciador_de_rifas;

import java.util.Scanner;

public class Main {
	
	static Scanner leitor=new Scanner(System.in);
	
	static int mostrarMenu() {
		System.out.println("|----------------MENU DE OPÇÕES----------------|");
		System.out.println("| 1 - Criar Rifa                               |");
		System.out.println("| 2 - Cadastrar vendedor                       |");
		System.out.println("| 3 - Realizar venda                           |");
		System.out.println("| 4 - Mostrar relatório                        |");
		System.out.println("| 5 - Mostrar rifa                             |");
		System.out.println("| 0 - Para sair                                |");		
		return lerInteiro("| Digite a opção desejada:                     |");
	}
	
	static void opcaoCriarRifa() {
		
	}
	
	static void opcaoCadastrarVendedor() {
		
	}
	
	static void opcaoRealizarVenda() {
		
	}
	
	static void opcaoMostrarRelatorio() {
		
	}
	
	static String opcaoMostrarRifa() {
		return "Em processo...";
		
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
					opcaoMostrarRelatorio();
					break;
					
				case 5:
					opcaoMostrarRifa();
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
