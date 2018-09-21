import java.io.IOException;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		Maze maze = new Maze();
		Agent agent = new Agent(maze);
		System.out.println("\n");
		agent.explore();

		System.out.println("Posi��o do agente: " + maze.getAgentPosition()[0] + "," + maze.getAgentPosition()[1]);
		System.out.println("Posi��o do saida: " + maze.getSaidaPosition()[0] + "," + maze.getSaidaPosition()[1]);
		A_star a = new A_star(agent,maze.getAgentPosition(), maze.getSaidaPosition(), maze);
		a.run();
		int v1[] = {2,1};
		int v2[] = {6,3};
		//System.out.println(a.calcHeuristic(v1, v2));
		
		
	}
}
