import java.util.ArrayList;

public class A_star {
	private int[] inicio = new int[2];
	private int[] objetivo = new int[2];

	private String[][] maze = null;

	public A_star(int[] i, int[] o, Maze m) {
		this.inicio = i;
		this.objetivo = o;

		this.maze = m.getMaze();
	}

	public void run() {
		/*
		 * lista_aberta lista_fechada
		 * 
		 * lista_aberta[0] = inicio //coloca vizinhos dela na lista aberta
		 * lista_aberta.add inicio+1 inicio-1
		 * 
		 * para cada vizinha, vê custo da vizinha até o objetivo, custo da vizinha até o
		 * inicio(anterior+1), e soma desses dois q gera o custo final. Custo = número
		 * de casas
		 * 
		 * casa atual então vai p lista fechada
		 * 
		 * ordena lista aberta em ordem crescente de custos
		 * 
		 * então vai p quem tá no topo da lista aberta (aquele com menor custo). Repete
		 * passos anteriores. Obstáculos são ignorados na análise de vizinhos. O que já
		 * foi computado e já ta na lista aberta não é analisado de novo quando é
		 * vizinho novamente. Analisa apenas vizinhos livres(que não estão em nenhuma
		 * das listas).
		 */

		ArrayList<int[]> open_list = new ArrayList<int[]>();
		ArrayList<int[]> closed_list = new ArrayList<int[]>();

		int[] current = new int[2];
		ArrayList<int[]> current_sons = new ArrayList<int[]>();

		open_list.add(this.inicio);

		while (!open_list.isEmpty()) {
			// pega primeiro elemento da lista aberta (aquele com menor custo)
			current = open_list.get(0);
			System.out.println("current: " + current[0] + " " + current[1]);
			open_list.remove(0);

			// checa se posição atual é a posição do objetivo(baú ou saida)
			if (current == this.objetivo) {
				break;
			}

			// pega vizinhos
			// vizinhos.add(e)

			// ignora qnd sao obstaculos
			ArrayList<int[]> vizinhos = getVizinhos(current);
			for (int[] v : vizinhos) {
				// descobrir como checar custos

				// State vizinho = new State(,,,v)

				// testa se vizinho já está em open_list ou closed_list
				if (!open_list.contains(v) && !closed_list.contains(v)) {

					open_list.add(v);

				}
			}

			closed_list.add(current);
			sort(open_list);
			vizinhos.clear();
		}

	}

	public ArrayList<int[]> getVizinhos(int[] current) {
		ArrayList<int[]> vizinhos = new ArrayList<int[]>();

		// ignora qnd sao obstaculos
		int[] position = new int[2];

		position[0] = current[0] + 1;
		position[1] = current[1];
		if (this.maze[position[0]][position[1]] != null || (!this.maze[position[0]][position[1]].contains("P"))
				|| (!this.maze[position[0]][position[1]].contains("O"))) {
			vizinhos.add(position);
		}

		position = new int[2];
		position[0] = current[0] - 1;
		position[1] = current[1];
		if (position[0] >= 0) {
			if (this.maze[position[0]][position[1]] != null || (!this.maze[position[0]][position[1]].contains("P"))
					|| (!this.maze[position[0]][position[1]].contains("O"))) {
				vizinhos.add(position);
			}
		}
		position = new int[2];
		position[0] = current[0];
		position[1] = current[1] + 1;
		if (this.maze[position[0]][position[1]] != null || (!this.maze[position[0]][position[1]].contains("P"))
				|| (!this.maze[position[0]][position[1]].contains("O"))) {
			vizinhos.add(position);
		}
		position = new int[2];
		position[0] = current[0];
		position[1] = current[1] - 1;
		if (position[1] >= 0) {
			if (this.maze[position[0]][position[1]] != null || (!this.maze[position[0]][position[1]].contains("P"))
					|| (!this.maze[position[0]][position[1]].contains("O"))) {
				vizinhos.add(position);
			}
		}

		for (int[] num : vizinhos) {
			System.out.println("Vizinhos: ");
			System.out.println(num[0]);
			System.out.println(num[1]);
		}

		return vizinhos;
	}

	public void sort(ArrayList<int[]> a) {

	}
}
