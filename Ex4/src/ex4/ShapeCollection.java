package ex4;

import geo.GeoShape;
import gui.GUIShape;
import gui.GUI_Shape;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection{
	private ArrayList<GUI_Shape> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {
		GUI_Shape ans = this._shapes.get(i);
		this._shapes.remove(i);
		return ans;
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		if (s!=null&&s.getShape()!=null) {
			this._shapes.add(i, s);
		}
	}
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public GUI_Shape_Collection copy() {
		ShapeCollection copy_shapes= new ShapeCollection();
		for (int i = 0; i<_shapes.size()-1; i++) {
			GeoShape g = _shapes.get(i).getShape().copy(); // Copying the shapes qualities
			GUIShape guiSh = new GUIShape(g,_shapes.get(i).isFilled(),_shapes.get(i).getColor(), _shapes.get(i).getTag());
			copy_shapes.add(guiSh);
		}
		return copy_shapes;
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		int i=0;
		while(i<_shapes.size()){
			_shapes.remove(0);
		}
	}

	@Override
	public void save(String file) {
		try {
			FileWriter file_Write = new FileWriter(file);
			for(int i = 0; i<_shapes.size(); i++) {
				file_Write.write((_shapes.get(i).toString()+ "\n"));
			}
			file_Write.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String file) {
		_shapes.clear(); // maybe to do a new one empty in the same size
		try {
			// Open the file
			FileReader reader = new FileReader(file); // getting the file path
			BufferedReader bufferedReader = new BufferedReader(reader);

			// Read the file
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				_shapes.add(new GUIShape(line));
			}

			// Close the file
			bufferedReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}
