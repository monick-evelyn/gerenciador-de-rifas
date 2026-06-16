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
		for(int i=0; i<totalVendedores;i++) {
			if(vendedores[i].nome.equalsIgnoreCase(nome)) {
				return vendedores[i];
			}
		}
		return null;
	}
	
	
	void listarVendas() {
		
		String lista = "\n========== BILHETES VENDIDOS ==========\n";
		lista += "Bilhetes vendidos: "+ rifa.calcularQtdBilhetesVendidos() + "\n";
		
		boolean temBilhetes = false;
		
		for (int i = 0; i < rifa.bilhetes.length; i++) {
			if (rifa.bilhetes[i] != null) {
				temBilhetes = true;
				lista += rifa.bilhetes[i].toString() + "\n";
			}
		}
		
		if (!temBilhetes) {
			System.out.println("Não há bilhetes vendidos.");
		}
		
		System.out.println(lista);
	}
	
	void realizarVenda(int numeroBilhete, String nomeComprador, String telefone, String formaPagamento, String nomeVendedor) {
		Vendedor vendedorAuxiliar=buscarVendedorPorNome(nomeVendedor);
					
		Comprador novoComprador=new Comprador(nomeComprador, telefone);
		
		if(vendedorAuxiliar==null) {
			System.out.println("Erro: vendedor não existe.");
			return;
		}
		
		System.out.println(rifa.venderBilhete(numeroBilhete, novoComprador, vendedorAuxiliar, formaPagamento));
	}
	
	void carregarRankingDeVendedores() {
		Vendedor vendedoresOrdenado[] = new Vendedor[totalVendedores];
		
		for (int i = 0; i < totalVendedores; i++) {
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
	
	void listarVendasPorVendedor(String nomeVendedor) {
		Vendedor vendedor = buscarVendedorPorNome(nomeVendedor);
		
		if (vendedor == null) {
			System.out.println("Erro: Vendedor " + nomeVendedor + " não encontado");
			return;
		}
		
		String lista = "\n========== BILHETES POR VENDEDOR ==========\n";
		lista += vendedor.toString() + "\n";
		
		boolean temBilhetes = false;
		boolean temVendas = false;
		
		for (int i = 0; i < rifa.bilhetes.length; i++) {
			if (rifa.bilhetes[i] != null) {
				temBilhetes = true;
				if (rifa.bilhetes[i].vendedor.equals(vendedor)) {
					temVendas = true;
					lista += "Número: " + rifa.bilhetes[i].numero +
							" Comprador: " + rifa.bilhetes[i].comprador.nome + "\n";
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
	
	
}
