
public class State {
	/*
	 * para cada vizinha, v� custo da vizinha at� o objetivo, custo da vizinha at� o
	 * inicio(anterior+1), e soma desses dois q gera o custo final. Custo = n�mero
	 * de casas
	 */

	private double cost_goal = 0;
	private int cost_initial = 0;
	private double cost_final = 0;
	private int[] position = new int[2];

	public State(double cg, int ci, double cf, int[] p) {
		this.cost_goal = cg;
		this.cost_initial = ci;
		this.cost_final = cf;
		this.position = p;
	}

	public double getCost_goal() {
		return cost_goal;
	}

	public int getCost_initial() {
		return cost_initial;
	}

	public double getCost_final() {
		return cost_final;
	}

	public int[] getPosition() {
		return position;
	}

}
