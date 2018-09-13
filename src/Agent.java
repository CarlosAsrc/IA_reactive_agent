
public class Agent {
	private Maze maze;

	public Agent(Maze maze) {
		this.maze = maze;
	}

	public void mover() {
		String[][] tst = maze.getMaze();
		int[] position = maze.getAgentPosition();
		System.out.println(position[0]);
		System.out.println(position[1]);
	}
}
