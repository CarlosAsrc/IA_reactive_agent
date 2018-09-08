import java.util.ArrayList;
import java.util.Random;

public class Maze {
	
	private String [][] maze = new String [10][10];
	private Random random = new Random();
	private String wall = "";
//	Agente: A
//	Parede: P
//	Buraco: O
//	Baús:   B
//	Saída:  S
//	Moedas: M
//	vazio:  -
	public Maze() {
		init();
		generateWallsAndChests();
		printMaze();
	}
	
	public void init() {
			for(int i=0; i<maze.length; i++) {
				for(int j=0; j<maze[0].length; j++) {
					maze[i][j] = " - ";
				}
		}	
	}
	
	public void generateWallsAndChests() {
		//Decide o lado do paredão:
		int side = random.nextInt(4);
		//Decide a posição da porta de saída no paredão:
		int exit = random.nextInt(10);
		//Decide as posições dos baús vizinhos ao paredão:
		ArrayList chests = new ArrayList<>();
		int n;
		for(int i=0; i<4; i++) {
			do{
				n = random.nextInt(10);
			}
			while(chests.contains(n));
			chests.add(n);
		}
		
		switch(side) {
			case 0:
				wall = "ACIMA";
				for(int i=0; i<maze[0].length; i++) {
					if(exit==i) 
						maze[0][i] = " S "; 
					else
						maze[0][i] = " P ";
					if(chests.contains(i)) {
						maze[1][i] = " B ";
					}
				}
				break;
			case 1:
				wall = "ESQUERDA";
				for(int i=0; i<maze.length; i++) {
					if(exit==i) 
						maze[i][0] = " S "; 
					else
						maze[i][0] = " P ";
					if(chests.contains(i)) {
						maze[i][1] = " B ";
					}
				}
				break;
			case 2:
				wall = "ABAIXO";
				for(int i=0; i<maze[0].length; i++) {
					if(exit==i) 
						maze[maze[0].length-1][i] = " S "; 
					else
						maze[maze[0].length-1][i] = " P ";
					if(chests.contains(i)) {
						maze[maze[0].length-2][i] = " B ";
					}
				}
				
				break;
			case 3:
				wall = "DIREITA";
				for(int i=0; i<maze.length; i++) {
					if(exit==i) 
						maze[i][maze[0].length-1] = " S "; 
					else
						maze[i][maze[0].length-1] = " P ";
					if(chests.contains(i)) {
						maze[i][maze[0].length-2] = " B ";
					}
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
