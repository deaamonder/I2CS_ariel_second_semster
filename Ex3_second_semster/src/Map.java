import java.util.*;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D {
	private int[][] _map;
	private boolean _cyclicFlag = true;

	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w,h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size, 0);}

	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
		/////// add your code below ///////
		_map = new int[h][w];
		for(int i = 0; i < h ; i++) {
			for(int j = 0; j < w; j++) {
				_map[i][j] = v;
			}
		}
		///////////////////////////////////
	}

	@Override
	public void init(int[][] arr) {
		/////// add your code below ///////
		if(arr == null || arr.length == 0 || arr[0].length == 0) {
			throw new RuntimeException("invalide 2D array");
		}

		int rows = arr.length;
		int cols = arr[0].length;
		_map = new int[rows][cols];

		for(int i = 0; i < rows;i++) {
			for(int j = 0; j < cols;j++) {
				//if arr is ragged
				if (arr[i].length != cols) {throw new RuntimeException("Invalid 2D array");}
				_map[i][j] = arr[i][j];
			}
		}

		///////////////////////////////////
	}
	@Override
	public int[][] getMap() {
		int[][] ans = null;
		/////// add your code below ///////
		int rows = _map.length;
		int cols = _map[0].length;


		ans = new int[rows][cols];
		//deep copy
		for(int i = 0; i < rows;i++) {
			for(int j = 0; j < cols;j++) {
				ans[i][j] = _map[i][j];
			}
		}

		///////////////////////////////////
		return ans;
	}
	@Override
	/////// add your code below ///////
	public int getWidth() {return _map[0].length;}
	@Override
	/////// add your code below ///////
	public int getHeight() {return _map.length;}
	@Override
	/////// add your code below ///////
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	/////// add your code below ///////
	public int getPixel(Pixel2D p) {
		return this.getPixel(p.getX(),p.getY());
	}
	@Override
	/////// add your code below ///////
	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	@Override
	/////// add your code below ///////
	public void setPixel(Pixel2D p, int v) {this.setPixel(p.getX(), p.getY(),v);}
	@Override
	/**
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v) {
		int ans=0;
		int[][] temp = new int[this.getWidth()][this.getHeight()];
		//fill the temp matrix with numbers
		for(int i = 0; i<this.getWidth(); i++) {
			for(int j =0; j<this.getHeight(); j++) {
				if(getPixel(i,j)==getPixel(xy.getX(),xy.getY())) {
					temp[i][j] = 1;
				}
				else {
					temp[i][j] = -1;
				}
			}
		}
		//set the new color where there is an 1 in the temp matrix
		for(int i=0; i<temp.length; i++) {
			for(int j =0; j<temp[i].length;j++) {
				if(temp[i][j]==1) {
					setPixel(i,j,new_v);//set the new color
					ans ++;
				}
			}
		}
		return ans;
	}
	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
		Pixel2D[] path = null;  // the result.
		Map2D distanceMap = this.allDistance(p1, obsColor);
		int targetDistance = distanceMap.getPixel(p2);

		if (targetDistance == -1 || targetDistance == -2) {
			return null;
		}

		if (targetDistance == 0) {
			path = new Pixel2D[1];
			path[0] = p2;
			return path;
		}

		path = new Pixel2D[targetDistance + 1];

		ArrayList<Pixel2D> track = new ArrayList<>();
		track.add(p2);
		path[targetDistance] = p2;

		int index = 0;
		while (index < track.size()) {
			Pixel2D current = track.get(index);
			index++;

			if (current.equals(p1)) {
				break;
			}

			Pixel2D[] neighbors = getNeighbors(current);
			for (Pixel2D neighbor : neighbors) {
				if ((this.isCyclic() || isInside(neighbor)) && distanceMap.getPixel(neighbor) == distanceMap.getPixel(current) - 1) {
					track.add(neighbor);
					path[distanceMap.getPixel(current) - 1] = neighbor;
					break;
				}
			}
		}

		return path;
	}

	@Override
	public boolean isInside(Pixel2D p) {
		//a pixel(point) is inside the matrix(map) if its x and y coordinates is with in the map border.
		return (p.getX()>=0&&p.getX()<this.getWidth())&&(p.getY()>=0&&p.getY()<this.getHeight());
	}

	@Override
	public boolean isCyclic() {
		return _cyclicFlag;
	}
	@Override
	public void setCyclic(boolean cy) {this._cyclicFlag=cy;}
	@Override
	public Map2D allDistance(Pixel2D start, int obsColor) {
		Map2D ans = null;  // the result.
		int w = getWidth();
		int h = getHeight();

		ans = new Map(w, h, -1);
		ArrayList<Pixel2D> pixeList = new ArrayList<>();
		ans.setPixel(start, 0);
		pixeList.add(start);
		while (!pixeList.isEmpty()) {
			Pixel2D currPixel = pixeList.remove(0);
			int currDistance = ans.getPixel(currPixel);
			Pixel2D[] neighbors = getNeighbors(currPixel);// retrieve the nearby pixels of the current pixel.
			for (Pixel2D neighbor : neighbors) {//loop through the neighbors
				if (isInside(neighbor) && ans.getPixel(neighbor) == -1) { //verify whether the neighboring pixel is within the boundaries of the map and remains unvisited
					if (getPixel(neighbor) != obsColor) {//ensure that the neighboring pixel is not an obstacle.
						ans.setPixel(neighbor, currDistance + 1);//set the distance value to the neighboring pixel
						pixeList.add(neighbor);
					}
				}
			}
		}
		return ans;
	}

	/////private function///////
	private Pixel2D[] getNeighbors(Pixel2D p) {
		int pX = p.getX();
		int pY = p.getY();
		Pixel2D[] neighbors = new Pixel2D[4];

		//The % ensures that the indices wrap around to the opposite side of the map
		//when they reach the borders of the map
		neighbors[0] = new Index2D((pX - 1 + getWidth()) % getWidth(), pY);  // up
		neighbors[1] = new Index2D((pX + 1) % getWidth(), pY);  // down
		neighbors[2] = new Index2D(pX, (pY + 1) % getHeight());  // right
		neighbors[3] = new Index2D(pX, (pY - 1 + getHeight()) % getHeight());  // left

		return neighbors;
	}
}
