import java.util.ArrayList;
import java.util.Random;

public class Maze {
	
	private String [][] maze = new String [10][10];
	private Random random = new Random();
	private String wallSide = "";
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
		generateHoles();
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
				wallSide = "ACIMA";
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
				wallSide = "ESQUERDA";
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
				wallSide = "ABAIXO";
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
				wallSide = "DIREITA";
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
		//Constrói os 4 muros internos:
		int direction;
		int initialLine, initialColumn;
		for(int i=0; i<4; i++) {
			direction = random.nextInt(2);
			
			switch(direction) {
			case(0):
				do{
					initialLine = random.nextInt(10);
					initialColumn = random.nextInt(6);
				}while( ! (maze[initialLine][initialColumn].equals(" - ")
						&& maze[initialLine][initialColumn+1].equals(" - ")
						&& maze[initialLine][initialColumn+2].equals(" - ")
						&& maze[initialLine][initialColumn+3].equals(" - ")
						&& maze[initialLine][initialColumn+4].equals(" - "))
				);
				for(int j=0; j<5; j++) {
					maze[initialLine][initialColumn+j] = " P ";
				}
				break;
			case(1): 
				do{
					initialLine = random.nextInt(6);
					initialColumn = random.nextInt(10);
				}while( ! (maze[initialLine][initialColumn].equals(" - ")
						&& maze[initialLine+1][initialColumn].equals(" - ")
						&& maze[initialLine+2][initialColumn].equals(" - ")
						&& maze[initialLine+3][initialColumn].equals(" - ")
						&& maze[initialLine+4][initialColumn].equals(" - "))
				);
				for(int j=0; j<5; j++) {
					maze[initialLine+j][initialColumn] = " P ";
				}
				break;
			}
				
			
		}
	}
	
	public void generateHoles() {
		int line;
		int column;
		boolean validation;
		for(int i=0; i<5; i++) {	
			do {
				line = random.nextInt(10);
				column = random.nextInt(10);
				if(line==0) {
					validation = validateHolePosition("UpperEnd", line, column);
				}else if(column==0){
					validation = validateHolePosition("LeftEnd", line, column);
				}else if(column==maze[0].length-1){
					validation = validateHolePosition("RightEnd", line, column);
				} else if(line==maze.length-1) {
					validation = validateHolePosition("BottomEnd", line, column);
				} else {
					validation = validateHolePosition("Central", line, column);
				}
			}while(!validation);
			maze[line][column] = " O ";
		}
	}
	
	public boolean validateHolePosition(String holeArea, int line, int column) {
		
		if(line==0 && column==0) {
			if(!maze[line][column].equals(" - ")
					|| maze[line+1][column].equals(" O ")
					|| maze[line][column+1].equals(" O "))
				{
					return false;
				} else return true;
		} else if(line==maze.length-1 && column==0) {
			if(!maze[line][column].equals(" - ")
					|| maze[line-1][column].equals(" O ")
					|| maze[line][column+1].equals(" O "))
				{
					return false;
				} else return true;
		}else if(line==0 && column==maze[0].length-1) {
			if(!maze[line][column].equals(" - ")
					|| maze[line+1][column].equals(" O ")
					|| maze[line][column-1].equals(" O "))
				{
					return false;
				} else return true;
		} else if(line==maze.length-1 && column==maze[0].length-1) {
			if(!maze[line][column].equals(" - ")
					|| maze[line-1][column].equals(" O ")
					|| maze[line][column-1].equals(" O "))
				{
					return false;
				} else return true;
		}
		
		switch(holeArea) {
			case("Central"):
				if(!maze[line][column].equals(" - ")
					|| maze[line+1][column].equals(" O ")
					|| maze[line][column+1].equals(" O ")
					|| maze[line-1][column].equals(" O ")
					|| maze[line][column-1].equals(" O ")) 
				{
					return false;
				} else return true;
			case("LeftEnd"):
				if(!maze[line][column].equals(" - ")
						|| maze[line+1][column].equals(" O ")
						|| maze[line][column+1].equals(" O ")
						|| maze[line-1][column].equals(" O ")) 
					{
						return false;
					} else return true;
			case("RightEnd"):
				if(!maze[line][column].equals(" - ")
						|| maze[line+1][column].equals(" O ")
						|| maze[line-1][column].equals(" O ")
						|| maze[line][column-1].equals(" O ")) 
					{
						return false;
					} else return true;
			case("UpperEnd"):
				if(!maze[line][column].equals(" - ")
						|| maze[line+1][column].equals(" O ")
						|| maze[line][column+1].equals(" O ")
						|| maze[line][column-1].equals(" O ")) 
					{
						return false;
					} else return true;
			case("BottomEnd"):
				if(!maze[line][column].equals(" - ")
						|| maze[line][column+1].equals(" O ")
						|| maze[line-1][column].equals(" O ")
						|| maze[line][column-1].equals(" O ")) 
					{
						return false;
					} else return true;		
		}
		
		
		return false;
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
