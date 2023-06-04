

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exe.ex3.game.Game;
import exe.ex3.game.GhostCL;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

/**
 * This is the major algorithmic class for Ex3 - the PacMan game:
 *
 * This code is a very simple example (random-walk algorithm).
 * Your task is to implement (here) your PacMan algorithm.
 */
public class Ex3Algo implements PacManAlgo{
	private int _count;
	public Ex3Algo() {_count=0;}
	@Override
	/**
	 *  Add a short description for the algorithm as a String.
	 */
	public String getInfo() {
		return "the algorithm moves the pacman to the nearest pink point using all distance function we write in the Map class" +
				"we use the all distance to find the distances from the pacman position to the pink points and we use the shortest path" +
				"we write in the same class to find the shortest path to go to that point and we use the finddir function to go there";
	}
	@Override
	/**
	 * This ia the main method - that you should design, implement and test.
	 */
	public int move(PacmanGame game) {
		if(_count==0 || _count==300) {
			int code = 0;
			int[][] board = game.getGame(0);
			printBoard(board);
			int blue = Game.getIntColor(Color.BLUE, code);
			int pink = Game.getIntColor(Color.PINK, code);
			int black = Game.getIntColor(Color.BLACK, code);
			int green = Game.getIntColor(Color.GREEN, code);
			System.out.println("Blue=" + blue + ", Pink=" + pink + ", Black=" + black + ", Green=" + green);
			String pos = game.getPos(code).toString();
			System.out.println("Pacman coordinate: "+pos);
			GhostCL[] ghosts = game.getGhosts(code);
			printGhosts(ghosts);
			int up = Game.UP, left = Game.LEFT, down = Game.DOWN, right = Game.RIGHT;
		}
		_count++;
		int dir = dir(game);
		return dir;
	}


	private static void printBoard(int[][] b) {
		for(int y =0;y<b[0].length;y++){
			for(int x =0;x<b.length;x++){
				int v = b[x][y];
				System.out.print(v+"\t");
			}
			System.out.println();
		}
	}
	private static void printGhosts(GhostCL[] gs) {
		for(int i=0;i<gs.length;i++){
			GhostCL g = gs[i];
			System.out.println(i+") status: "+g.getStatus()+",  type: "+g.getType()+",  pos: "+g.getPos(0)+",  time: "+g.remainTimeAsEatable(0));
		}
	}
	private static int randomDir() {
		int[] dirs = {Game.UP, Game.LEFT, Game.DOWN, Game.RIGHT};
		int ind = (int)(Math.random()*dirs.length);
		return dirs[ind];
	}

	private static int dir(PacmanGame game){
		int[][] gameBoard = game.getGame(0);
		Map gameMap = new Map(gameBoard);

		String playerPosition = game.getPos(0);
		String[] positionParts = playerPosition.split(",");
		int playerX = Integer.parseInt(positionParts[0]);
		int playerY = Integer.parseInt(positionParts[1]);
		Pixel2D playerPos = new Index2D(playerX, playerY);
		Map2D allDistances = gameMap.allDistance(playerPos, 1);
		Pixel2D closestPinkPixel = closestPink(gameMap, playerPos, allDistances);
		Pixel2D[] path = gameMap.shortestPath(playerPos, closestPinkPixel, 1);

		int direction = findDir(playerPos, path[1]);
		return direction;
	}

	private static Pixel2D closestPink(Map map, Pixel2D pos , Map2D allDistanceMap){
		Pixel2D closestPinkPixel = new Index2D(0, 0);
		int maxDistance = Integer.MAX_VALUE;
		int width = map.getWidth();
		int height = map.getHeight();

		for (int i = 0; i < width; i++){
			for (int j = 1; j < height - 1; j++){
				if (map.getPixel(i, j) == 3){
					if(allDistanceMap.getPixel(i, j) != -1){
						int currentDistance = allDistanceMap.getPixel(i, j);
						if(maxDistance > currentDistance){
							maxDistance = currentDistance;
							closestPinkPixel = new Index2D(i, j);
						}
					}
				}
			}
		}
		return closestPinkPixel;
	}

	private static int findDir(Pixel2D currentPosition, Pixel2D destination){
		// Go up and down
		if (currentPosition.getX() == destination.getX()){
			if (currentPosition.getY() + 1 == destination.getY()){return Game.UP;}
			if (currentPosition.getY() - 1 == destination.getY()){return Game.DOWN;}
			if (currentPosition.getY() < destination.getY()){return Game.DOWN;}
			if (currentPosition.getY() > destination.getY()){return Game.UP;}
		}
		// Go left and right
		else if (currentPosition.getY() == destination.getY()){
			if (currentPosition.getX() + 1 == destination.getX()){return Game.RIGHT;}
			if (currentPosition.getX() - 1 == destination.getX()){return Game.LEFT;}
			if (currentPosition.getX() > destination.getX()){return Game.RIGHT;}
			if (currentPosition.getX() < destination.getX()){return Game.LEFT;}
		}
		return -1;
	}

	private static Pixel2D stringToPixel(String str) {
		String[] arr = str.split(",");
		int x = Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		return new Index2D(x, y);
	}

	/*private static int toMove(Map map, Pixel2D from, Pixel2D to) {

		System.out.println("from:" + from);
		System.out.println("to:" + to);

		if (map.isCyclic()&&to!=null) {
			if (from.getX() == to.getX()) {
				if (from.getY() == map.getHeight() - 1 && to.getY() == 0) {
					return Game.UP;
				}
				if (from.getY() == 0 && to.getY() == map.getHeight() - 1) {
					return Game.DOWN;
				}
			}
			if (from.getY() == to.getY()) {
				if (from.getX() == map.getWidth() - 1 && to.getX() == 0) {
					return Game.RIGHT;
				}
				if (from.getX() == 0 && to.getX() == map.getWidth() - 1) {
					return Game.LEFT;
				}
			}
		}
		if(to!=null) {
			if (to.getX() == from.getX() && to.getY() < from.getY()) {
				return Game.DOWN;
				//	dirs[a] = down;
			}
			if (to.getX() == from.getX() && to.getY() > from.getY()) {
				return Game.UP;
				//	dirs[a] = up;
			}
			if (to.getX() < from.getX() && to.getY() == from.getY()) {
				return Game.LEFT;
				//dirs[a] = right;
			}
			if (to.getX() > from.getX() && to.getY() == from.getY()) {
				return Game.RIGHT;
				//dirs[a] = left;
			}
		}
		return Game.PAUSE;
	}*/
}