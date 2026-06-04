package gerenciador_de_rifas;

public class SistemaRifa {
	Rifa rifa;
	Vendedor vendedores[];
	int totalVendedores;
	
	SistemaRifa (int capacidadeVendedores){
		this.rifa = null;
		vendedores = new Vendedor[capacidadeVendedores];
		totalVendedores = 0;
	}
	
	boolean criarRifa(String premio, double valorBilhete, int capacidadeBilhetes) {
		if(this.rifa == null){
			Rifa novaRifa = new Rifa(premio, valorBilhete, capacidadeBilhetes);
			this.rifa = novaRifa;
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
	
	String sortearNumero(Rifa rifa) {
		int quantidadeBilhetesVendidos=0;
		
		for(int i=0; i<rifa.bilhetes.length;i++) {
			if(rifa.bilhetes[i]!=null) {
				quantidadeBilhetesVendidos++;
			}
		}
		if (quantidadeBilhetesVendidos==0) {
			return "Nenhum bilhete foi vendido ainda, não é possível sortear!";
		}
		Bilhete[] BilhetesPreenchidos=new Bilhete[quantidadeBilhetesVendidos];
		
		int contadorAux=0;
		for(int i=0; i<rifa.bilhetes.length;i++) {
			if(rifa.bilhetes[i]!=null) {
				BilhetesPreenchidos[contadorAux] = rifa.bilhetes[i];
				contadorAux++;
			}
		}
		
		java.util.Random geradorAleatorio = new java.util.Random();
		
		int posicaoSorteada=geradorAleatorio.nextInt(quantidadeBilhetesVendidos);
		
		Bilhete bilheteGanhador= rifa.bilhetes[posicaoSorteada];
		
		String resultado="";
		resultado += "\n=================================================\n";
	    resultado += "        NÚMERO SORTEADO COM SUCESSO!           \n";
	    resultado += "=================================================\n";
	    resultado += "Número do Bilhete: " + bilheteGanhador.numero + "\n";
	    resultado += "Comprador: " + bilheteGanhador.comprador.nome + "\n";
	    resultado += "Vendedor responsável: " + bilheteGanhador.vendedor.nome+"\n";
	    resultado += "=================================================\n";
	    
	    return resultado;
		
		
	}
	
	
}
