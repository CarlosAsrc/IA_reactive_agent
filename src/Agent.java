import java.io.IOException;
import java.util.Random;

import javax.swing.event.TreeWillExpandListener;

public class Agent {
	private Maze maze;
	private int chestsPositions[] = new int[8];
	int chestsPositionsCont;
	private int exitPosition[] = new int[2];
	private String currentDirection;
	private Random random = new Random();

	public Agent(Maze maze) {
		this.maze = maze;
		sortDirection();

		int[] saida = new int[2];

		//A_star a = new A_star(maze.getAgentPosition(), this.maze.getSaidaPosition(), this.maze);
		//a.run();
	}

	
	public void explore() throws InterruptedException, IOException {
		for(int i=0; i<1000; i++) {
			for (int j=0; j<50; j++){System.out.println();}
			scan();
			move();			
			System.out.print("X: "+maze.getAgentPosition()[0]);
			System.out.println("  Y: "+maze.getAgentPosition()[1]);
			//System.out.println(currentDirection);
			//if("vb".contains("vb")) {System.out.println("TRUE");}
			maze.printMaze();
			Thread.sleep(500);

	}
}



	public void sortDirection() {
		int n;
		if(currentDirection==null) {
			n = random.nextInt(4);
		} else {
			if(currentDirection.contains("up") || currentDirection.contains("down")) {
				n = random.nextInt(2)+2;
			} else {
				n = random.nextInt(2);
			}
		}
		
		switch (n) {
		case 0:
			currentDirection = "up";
			break;
		case 1:
			currentDirection = "down";
			break;
		case 2:
			currentDirection = "left";
			break;
		case 3:
			currentDirection = "right";
			break;
		}
	}

	public void move() {
		int[] position = maze.getAgentPosition();
		int[] before = new int[2];
		before[0] = position[0];
		before[1] = position[1];
		int x, y;

		switch(currentDirection) {
			case "up":
				x = position[0]-1;
				y = position[1];
				if(scanPos(x, y).contains("O") && validPos(x-1, y)) {
					
						position[0] = x-1;
						break;
					
				} else {
					if(validPos(x, y)) {
						position[0] = x;
					} else {
						sortDirection();
//						move();
						
					}
				}
				break;
				
				
			case "down":
				x = position[0]+1;
				y = position[1];
				if(scanPos(x, y).contains("O") && validPos(x+1, y)) {		
					position[0] = x+1;
					break;
					
				} else {
					if(validPos(x, y)) {
						position[0] = x;
					} else {
						sortDirection();
//						move();
					}
				}
				break;
				
			case "left":
				x = position[0];
				y = position[1]-1;
				if(scanPos(x, y).contains("O") && validPos(x, y-1)) {
					
						position[1] = y-1;
						break;
					
				} else {
					if(validPos(x, y)) {
						position[1] = y;
					} else {
						sortDirection();
//						move();
					}
				}
				break;
				
			case "right":
				x = position[0];
				y = position[1]+1;
				if(scanPos(x, y).contains("O") && validPos(x, y+1)) {
					
						position[1] = y+1;
						break;
					
				} else {
					if(validPos(x, y)) {
						position[1] = y;
					} else {
						sortDirection();
//						move();
					}

				}
			}
					
		maze.updateAgentPosition(position, before);
	}

	public boolean validPos(int x, int y) {
		return validRangePos(x, y) && !maze.getMaze()[x][y].contains("P") && !maze.getMaze()[x][y].contains("O");
	}

	// Percepcao: verifica ate 2 casas nas 4 direcoes
	public void scan() {
		int x = maze.getAgentPosition()[0] - 1;
		int y = maze.getAgentPosition()[1];

		if(validRangePos(x, y) || validRangePos(x-1, y)) {
			if(	scanPos(x, y).replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]") || 
				(scanPos(x-1, y).replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]") && validPos(x, y))) {
				currentDirection = "up";
				
				return;
			}

		}

		x = maze.getAgentPosition()[0];

		y = maze.getAgentPosition()[1]+1;
		if(validRangePos(x, y) || validRangePos(x, y+1)) {
			if(	scanPos(x, y).replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]") || 
				(scanPos(x, y+1).replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]") && validPos(x, y))) {

				currentDirection = "right";
				
				return;
			}

		}

		x = maze.getAgentPosition()[0] + 1;
		y = maze.getAgentPosition()[1];

		if(validRangePos(x, y) || validRangePos(x+1, y)) {
			if(	scanPos(x, y).replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]") || 
				(scanPos(x+1, y).replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]") && validPos(x, y))) {

				currentDirection = "down";
				return;
			}

		}

		x = maze.getAgentPosition()[0];

		y = maze.getAgentPosition()[1]-1;
		if(validRangePos(x, y) || validRangePos(x, y-1)) {
			if(	scanPos(x, y).replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]") || 
				(scanPos(x, y-1).replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]") && validPos(x, y))){

				currentDirection = "left";
				return;
			}

		}

	}

	public String scanPos(int x, int y) {
		if (!validRangePos(x, y))
			return "invalid position";
		if (maze.getMaze()[x][y].contains("B")) {
			saveChest(x, y);
		}
		if (maze.getMaze()[x][y].contains("S")) {
			exitPosition[0] = x;
			exitPosition[1] = y;
		}
		return maze.getMaze()[x][y];
	}

	public boolean validRangePos(int i, int j) {
		return (i >= 0 && i < maze.getMaze().length && j >= 0 && j < maze.getMaze()[0].length);
	}

	public void saveChest(int x, int y) {
		if (chestsPositionsCont < 8) {
			chestsPositions[chestsPositionsCont] = x;
			chestsPositionsCont++;
			chestsPositions[chestsPositionsCont] = y;
			chestsPositionsCont++;
		}
	}
}
