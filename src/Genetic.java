import java.util.Random;

public class Genetic {
	// nova popula��o gerada
	// selecao dos mais aptos
	// cruzamento
	// mutacao aleatoria
	// nova populacao ...

	// Elementos importantes:
	// operador de selecao: da preferencia p/ melhores individuos
	// operador de cruzamento: local escolhido aleatoriamente
	// operador de mutacao: realiza mutacoes, evita converg�ncia prematura

	// quando execu�oes param de dar solu�oes novas, ent�o convergiu = fim da
	// execucao do AG
	
	static int[] carga = {5,10,15,3,10,5,2,16,9,7,2,1,3,7,5,1};
	
	public Genetic() {
	}

	public void run() {
		int[][] populacao = new int[5][17];
		int[][] intermediaria = new int[5][17];

		System.out.println("Popula�ao: ");
		popular(populacao);
		printPopulacao(populacao, 17);

		for (int i=0;i<5;i++) {
			System.out.println("\nGeracao " + i);
			aptidar(populacao);
			printPopulacao(populacao,17);
	
			elitizar(populacao, intermediaria);

			gerar(populacao, intermediaria);
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

	static void elitizar(int [][]populacao, int [][]intermediaria) {
		int indexMenor = 0;
		for (int i=0; i<5; i++) {
			if (populacao[i][16] < populacao[indexMenor][16]) {
				indexMenor = i;
			}
		}
		clonar(intermediaria[0], populacao[indexMenor]);
	}
	
	static void gerar(int [][]populacao, int [][]intermediaria) {
		int linha = 0;
		for (int i=0; i<2; i++) {
			int pai = torner(populacao);
			int mae = torner(populacao);
			linha++;
			for (int j=0; j<5; j++) {
				intermediaria[linha][j] = populacao[pai][j];
				intermediaria[linha+1][j] = populacao[mae][j];
			}
			for (int j=5; j<16; j++) {
				intermediaria[linha][j] = populacao[mae][j];
				intermediaria[linha+1][j] = populacao[pai][j];
			}
			linha++;
		}
		clonar(populacao,intermediaria);
	}
	
	static int torner(int [][]populacao) {
		Random r = new Random();
		int primeiro = r.nextInt(5);
		int segundo = r.nextInt(5);
		return (populacao[primeiro][16] < populacao[segundo][16]) ? primeiro : segundo;
	}
	
	static void clonar(int []destino, int []origem) {
		for (int j=0; j<17; j++) {
			destino[j] = origem[j];
		}
	}
	
	static void clonar(int [][]destino, int [][]origem) {
		for (int i=0; i<5; i++) {
			for (int j=0; j<17; j++) {
				destino[i][j] = origem[i][j];
			}
		}
	}
	
	
	static void aptidar(int[][] populacao) {
		//combinacoes: ab, ac, ad, bc, bd, cd
		//somar diferenca entre elas
		int a=0, b=0, c=0, d=0;
		for (int i = 0; i < 5; i++) {
			populacao[i][16] = 0;
			for (int j = 0; j < 16; j++) {
				//populacao[i][16] += (populacao[i][j] == 1) ? carga[j] : -carga[j];
				switch (carga[j]) {
					case 0:
						a=a+carga[j];
						break;
					case 1:
						b=b+carga[j];
						break;
					case 2:
						c=c+carga[j];
						break;
					case 3:
						d=d+carga[j];
					
				}
			}
			int ab, ac, ad, bc, bd, cd;
			ab = Math.abs(a-b);
			ac = Math.abs(a-c);
			ad = Math.abs(a-d);
			bc = Math.abs(b-c);
			bd = Math.abs(b-d);
			cd = Math.abs(c-d);
			populacao[i][16] = ab+ac+ad+bc+bd+cd;
		}
	}
}
