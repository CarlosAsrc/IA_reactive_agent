import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		 * para cada vizinha, v� custo da vizinha at� o objetivo, custo da vizinha at� o
		 * inicio(anterior+1), e soma desses dois q gera o custo final. Custo = n�mero
		 * de casas
		 * 
		 * casa atual ent�o vai p lista fechada
		 * 
		 * ordena lista aberta em ordem crescente de custos
		 * 
		 * ent�o vai p quem t� no topo da lista aberta (aquele com menor custo). Repete
		 * passos anteriores. Obst�culos s�o ignorados na an�lise de vizinhos. O que j�
		 * foi computado e j� ta na lista aberta n�o � analisado de novo quando �
		 * vizinho novamente. Analisa apenas vizinhos livres(que n�o est�o em nenhuma
		 * das listas).
		 */

		ArrayList<State> open_list = new ArrayList<State>();
		ArrayList<State> closed_list = new ArrayList<State>();

		ArrayList<State> path = new ArrayList<State>();

		int[] current = new int[2];

		State inicial = new State(0, 0, 0, this.inicio);
		int count = 0;

		open_list.add(inicial);

		int controla = 0;

		State menor_vizinho = new State();
		double menor_custo_vizinho = 50000000;

		while /* (!open_list.isEmpty()) */ (controla < 15) {

			// pega primeiro elemento da lista aberta (aquele com menor custo)
			State current_state = open_list.get(0);
			current = open_list.get(0).getPosition();
			System.out.println("current: " + current[0] + " " + current[1]);
			open_list.remove(0);

			// checa se posi��o atual � a posi��o do objetivo(ba� ou saida)
			if (current == this.objetivo) {
				break;
			}

			// ignora qnd sao obstaculos
			ArrayList<int[]> vizinhos = getVizinhos(current);

			for (int[] v : vizinhos) {

				int cost_initial = count;
				double cost_goal = calcHeuristic(current, this.objetivo);
				double cost_final = cost_initial + cost_goal;
				State vizinho = new State(cost_goal, cost_initial, cost_final, v);

				if (cost_final < menor_custo_vizinho)
					menor_vizinho = vizinho;

				boolean contains = false;

				// testa se vizinho j� est� em open_list ou closed_list
				for (State s : open_list) {
					if (s.getPosition()[0] == v[0] && s.getPosition()[1] == v[1]) {
						contains = true;
						break;
					}
				}
				if (!contains)
					for (State s : closed_list) {
						if (s.getPosition()[0] == v[0] && s.getPosition()[1] == v[1]) {
							contains = true;
							break;
						}
					}

				if (!contains)
					open_list.add(vizinho);
			}

			closed_list.add(current_state);
			Collections.sort(open_list);
			for (State s : open_list) {
				System.out.println("Open list: " + s.getCost_final());
			}
			
			vizinhos.clear();
			open_list.remove(current_state);
			count++;
			path.add(menor_vizinho);
			controla++;
		}
		for (State s : path) {
			
			System.out.println("PATH: " +s.getPosition()[0]+","+s.getPosition()[1]+" custo: "+ s.getCost_final());
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

	/*
	 * Gera a fun��o heuristica do estado corrente
	 *
	 */
	public double calcHeuristic(int[] current, int[] goal) {
		System.out.println("Pontos: " + current[0] + " " + current[1] + " goal " + goal[0] + " " + goal[1]);
		double distance = Math.hypot(current[0] - goal[0], current[1] - goal[1]);
		System.out.println("Distancia : " + distance);
		return distance;
	}

}

