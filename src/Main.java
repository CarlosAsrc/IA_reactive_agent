
public class Main {
	public static void main(String[] args) {
		Maze maze = new Maze();
		Agent agent = new Agent(maze);
		System.out.println("\n");
		agent.mover();
	}
}
