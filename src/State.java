
public class State {
	/*
	 * para cada vizinha, vê custo da vizinha até o objetivo, custo da vizinha até o
	 * inicio(anterior+1), e soma desses dois q gera o custo final. Custo = número
	 * de casas
	 */

	private int cost_goal = 0;
	private int cost_initial = 0;
	private int cost_final = 0;
	private int[] position = new int[2];

	public State(int cg, int ci, int cf, int[] p) {
		this.cost_goal = cg;
		this.cost_initial = ci;
		this.cost_final = cf;
		this.position = p;
	}

	public int getCost_goal() {
		return cost_goal;
	}

	public int getCost_initial() {
		return cost_initial;
	}

	public int getCost_final() {
		return cost_final;
	}

	public int[] getPosition() {
		return position;
	}

}
