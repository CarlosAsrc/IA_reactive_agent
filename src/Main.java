import java.io.IOException;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		Maze maze = null;
		Agent agent = null;
//		do {
			maze = new Maze();
			agent = new Agent(maze);
//		} while(!validateMaze(agent, maze));
		System.out.println("\n");
		maze.printMaze();
		agent.explore();

		Genetic alg_gen = new Genetic();
		alg_gen.run();
		System.out.println("Posicao do agente: " + maze.getAgentPosition()[0] + "," + maze.getAgentPosition()[1]);
		System.out.println("Posicao do saida: " + maze.getSaidaPosition()[0] + "," + maze.getSaidaPosition()[1]);
		A_star a = new A_star(agent,maze.getAgentPosition(), maze.getSaidaPosition(), maze);
		a.run();

		
		
		
	}
	
	public static boolean validateMaze(Agent a, Maze m) {
		A_star alg = null;
		boolean valid = true;
		int [] chestsPositions = m.getChestsPositions();
		for(int i=0; i<chestsPositions.length; i++) {
			alg = new A_star(a, m.getAgentPosition(), chestsPositions, m);
			if(alg.run().size()-1 != chestsPositions.length) {
				valid = false;
			}
		}
		return valid;
	}
	
}
