package gerenciador_de_rifas;

public class SistemaRifa {
	Rifa rifa;
	Vendedor vendedores[];
	int totalVendedores;
	
	boolean criarRifa(String premio, double valorBilhete, int capacidadeBilhetes) {
		
		if(rifa==null){
			
			Rifa novaRifa= new Rifa(premio, valorBilhete, capacidadeBilhetes);
			
			return true;
		}
		return false;	
	}
	
	boolean cadastrarVendedor(String nome) {
		
		if(totalVendedores < vendedores.length) {
			Vendedor novoVendedor=new Vendedor(nome);
			vendedores[totalVendedores]=novoVendedor;
			totalVendedores++;
			return true;
		}
		return false;	
	}
	
	Vendedor buscarVendedorPorNome(String nome) {
		for(Vendedor vendedorAtual:vendedores) {
			if(vendedorAtual.nome.equalsIgnoreCase(nome)) {
				return vendedorAtual;
			}
		}
		return null;
	}
	
	Comprador buscarCompradorPorNome(Rifa rifa, String nome) {
		
		for(int i=0; i<rifa.totalBilhetes;i++){
			if(rifa.bilhetes[i].comprador.nome.equalsIgnoreCase(nome)){
				return rifa.bilhetes[i].comprador;
			}
		}
		return null;
	}
	
	boolean realizarVenda(Rifa rifa,int numeroBilhete, String nomeComprador, String telefone, String formaPagamento, String nomeVendedor) {
		if(rifa.verificarNumeroDisponivel(numeroBilhete)==true) {
						
			Vendedor vendedorAuxiliar=buscarVendedorPorNome(nomeVendedor);
			if(vendedorAuxiliar==null) {
				System.out.println("O vendedor não foi encontrado!");
				return false;
			}
			
			Comprador novoComprador=new Comprador(nomeComprador, telefone);

			Bilhete novoBilhete=new Bilhete(numeroBilhete, novoComprador,vendedorAuxiliar,formaPagamento);
			
			rifa.bilhetes[rifa.totalBilhetes]=novoBilhete;
			
			rifa.totalBilhetes++;
			return true;
		}
		return false;
	}
	
	//Não vi que era função da pessoa A no dia 3
	//String listarRelatorioGeral(Rifa rifa) {
		//String relatorio= "========== RELATÓRIO DA RIFA ==========\n"+
	    //"PRÊMIO: "+rifa.premio+"\n" +
		//"PORCENTAGEM DE ARRECADAÇÃO: "+rifa.calcularProgressoEmPorcentagem()+ "%\n"+
		//"LISTA DE VENDAS:\n"+
		//rifa.listarBilhetesVendidos()+
		//"================================================\n";
		//return relatorio;

	//}
}
