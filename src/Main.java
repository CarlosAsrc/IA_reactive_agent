import java.io.IOException;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		Maze maze = new Maze();
		Agent agent = new Agent(maze);
		System.out.println("\n");
		agent.explore();

		A_star a = new A_star(maze.getAgentPosition(), maze.getSaidaPosition(), maze);
		a.run();
	}
}
