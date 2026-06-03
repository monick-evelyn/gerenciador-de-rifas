package gerenciador_de_rifas;

public class SistemaRifa {
	//Rifa rifa;
	Vendedor vendedores[];
	int totalVendedores;
	
	boolean criarRifa(String premio, double valorBilhete, int capacidadeBilhetes) {
		
		//if(Rifa novaRifa= new Rifa(premio, valorBilhete, capacidadeBilhetes));{ return true;}
		return true;
		
	}
	
	boolean cadastrarVendedor(String nome) {
		
		for(int i=0; i<vendedores.length;i++) {
			
			if(vendedores[totalVendedores]==null) {
				Vendedor novoVendedor=new Vendedor(nome);
				vendedores[totalVendedores]=novoVendedor;
				return true;
			}
			
			
		}
		return false;	
	}
	
	//boolean realizarVenda(int numeroBilhete, String nomeVendedor, String telefone) {
		//for(int i=0; i<vendedores.length,i++) {
			//if(vendedores[i].nome.equalsIgnoreCase(nomeComprador)) {
				
			//}
		//}
		//Bilhete novoBilhete= new Bilhete(numero, vendedor)
	//}
}
