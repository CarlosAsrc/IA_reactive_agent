import java.util.Random;

public class Maze {
	
	private String [][] maze = new String [10][10];
	private Random random = new Random();
//	Agente: A
//	Parede: P
//	Buraco: B
//	Saída:  S
//	Moedas: M
//	vazio:  -
	public Maze() {
		init();
		generateWalls();
		printMaze();
	}
	
	public void init() {
			for(int i=0; i<maze.length; i++) {
				for(int j=0; j<maze[0].length; j++) {
					maze[i][j] = " - ";
				}
		}	
	}
	
	public void generateWalls() {
		int side = random.nextInt(4);
		int exit = random.nextInt(10);
		switch(side) {
			case 0:
				for(int i=0; i<maze[0].length; i++) {
					if(exit==i) 
						maze[0][i] = " S "; 
					else
						maze[0][i] = " P ";
				}
				break;
			case 1:
				for(int i=0; i<maze.length; i++) {
					if(exit==i) 
						maze[i][0] = " S "; 
					else
						maze[i][0] = " P ";
				}
				break;
			case 2:
				for(int i=0; i<maze[0].length; i++) {
					if(exit==i) 
						maze[maze[0].length-1][i] = " S "; 
					else
						maze[maze[0].length-1][i] = " P ";
				}
				break;
			case 3:
				for(int i=0; i<maze.length; i++) {
					if(exit==i) 
						maze[i][maze[0].length-1] = " S "; 
					else
						maze[i][maze[0].length-1] = " P ";
				}
				break;
				
		}
	}
	
	public void printMaze() {
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
}
