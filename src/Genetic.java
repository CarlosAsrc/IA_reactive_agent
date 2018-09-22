import java.util.Random;

public class Genetic {
	// nova população gerada
	// selecao dos mais aptos
	// cruzamento
	// mutacao aleatoria
	// nova populacao ...

	// Elementos importantes:
	// operador de selecao: da preferencia p/ melhores individuos
	// operador de cruzamento: local escolhido aleatoriamente
	// operador de mutacao: realiza mutacoes, evita convergência prematura

	// quando execuçoes param de dar soluçoes novas, então convergiu = fim da
	// execucao do AG
	
	static int[] carga = {5,10,15,3,10,5,2,16,9,7,2,1,3,7,5,1};
	
	public Genetic() {
	}

	public void run() {
		int[][] populacao = new int[5][17];
		int[][] intermediaria = new int[5][17];

		System.out.println("Populaçao: ");
		popular(populacao);
		printPopulacao(populacao, 17);

		for (int i=0;i<5;i++) {
			System.out.println("\nGeracao " + i);
			aptidar(populacao);
			printPopulacao(populacao,17);
	
			//elitizar(populacao, intermediaria);

			//gerar(populacao, intermediaria);
		}

	}

	static void popular(int[][] populacao) {
		Random r = new Random();
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 16; j++) {
				populacao[i][j] = r.nextInt(4);
			}

	}

	static void printPopulacao(int[][] populacao, int limite) {
		for (int i = 0; i < 5; i++) {
			System.out.print("P: ");
			for (int j = 0; j < limite-1; j++) {
				System.out.print(populacao[i][j] + " ");
			}
			System.out.print("   S: ");
			System.out.print(populacao[i][limite-1] + " ");
			System.out.println("");
		}
	}

	static void aptidar(int[][] populacao) {
		//combinacoes: ab, ac, ad, bc, bd, cd
		//somar diferenca entre elas
		for (int i = 0; i < 5; i++) {
			populacao[i][16] = 0;
			for (int j = 0; j < 16; j++) {
				populacao[i][16] += (populacao[i][j] == 1) ? carga[j] : -carga[j];
			}
			populacao[i][16] = Math.abs(populacao[i][16]);
		}
	}
}
