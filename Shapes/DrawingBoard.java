import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The DrawingBoard class keeps a record of all of the shapes that have been added, and assists in managing their states.
 * @author Nikita Chrystephan
 * @version 0.1
 * @assignment Shapes part 1
 *
 */
public class DrawingBoard {

	//you are truly lost without a list!
	private List<AbstractShape> objectList;
	private Iterator<AbstractShape> e;
	private AbstractShape selectedShape;

	/**
	 * This zero parameter constructor instantiates the fields.
	 */
	public DrawingBoard() {
		objectList = new ArrayList<AbstractShape>();
		e = objectList.iterator();
		selectedShape = null;
	}

	/**
	 * this method returns a reference to the selected shape.
	 * @return AbstractShape
	 */
	public AbstractShape getSelectedShape() {
		return selectedShape;
	}

	//this method deselects the previous shape and selects the shape passed in.
	private void selector(AbstractShape s) {
		if(selectedShape != null) {
			selectedShape.setSelected(false);
		}
		s.setSelected(true);
		selectedShape = s;
		objectList.remove(s);
		objectList.add(0, s);
	}

	/**
	 * this method selects the top-most shape at the specified coordinates and ensures all the other shapes are de-selected.
	 * @param int x
	 * @param int y
	 */
	public void selectShape(int x, int y) {
		for(int i = 0; i < objectList.size(); i++) {
			AbstractShape s = objectList.get(i);
			if(s.isOn(x, y)) {
				selector(s);
				break;
			}
		}
	}

	/**
	 * Given a reference to a specific shape, it selects that shape and deselects the other shapes.
	 * @param AbstractShape s
	 */
	public void selectShape(AbstractShape s) {
		if(objectList.contains(s)) {
			selector(s);
		} else throw new IllegalStateException("The provided object reference is not on the list!");
	}

	/**
	 * Allows selecting a shape by the index of the shape.
	 * @param int i
	 */
	public void selectShape(int i) {
		if(objectList.size() >= i && i >= 0) {
			AbstractShape s = objectList.get(i);
			selector(s);
		} else {
			throw new IndexOutOfBoundsException("The provided index does not correspond to an object in the collection.");
		}
	}

	/**
	 * Adds a new shape to the list.
	 * @param AbstractShape a
	 */
	public void addShape(AbstractShape a) {
		if(objectList.contains(a)) {
			//selector(a);
			throw new IllegalStateException("Adding the same object to the list twice is forbidden.");
		} else {
			objectList.add(0,a);
			selector(a);
		}
	}

	/**
	 * This method adds a shape below the top layer, and does not set it as the selected shape.
	 * @param int i
	 * @param AbstractShape a
	 */
	public void addBackgroundShape(int i, AbstractShape a) {
		objectList.add(i, a);
	}

	/**
	 * this method removes the shape at the given index.
	 * @param int i
	 */
	public void removeShape(int i) {
		if(objectList.size() > i && i >= 0) {
			objectList.remove(i);
		} else {
			throw new IndexOutOfBoundsException("You must provide a number that corresponds to the index of an item in the collection.");
		}
	}

	/**
	 * Removes a shape in the collection given a reference to that shape.
	 * @param AbstractShape s
	 */
	public void removeShape(AbstractShape s) {
		if(objectList.contains(s)) {
			objectList.remove(s);
		} else {
			throw new IllegalStateException("The specified shape is not in the collection!");
		}
	}

	/**
	 * this method removes all the shapes of a given name.
	 * @param String s
	 */
	public void removeNamedShapes(String s) {
		for(int i = 0; i < objectList.size(); i++) {
			if(objectList.get(i).name.equals(s)) {
				objectList.remove(i);
				i--;
			} else {
				throw new TypeNotPresentException("No shapes with that name were found: "+ s, null);
			}
		}
	}

	/**
	 * this object removes the selected shape from the list if it exists and selects the next shape
	 * if it exists. if no shapes exist, it throws an exception. if one shape exists, no shape will be selected.
	 */
	public void removeSelectedShape() {
		if(! e.hasNext() || getSelectedShape() == null) {
			throw new IllegalStateException("No shape is selected!");
		} else {
			if(objectList.get(0).isSelected()) {
				objectList.remove(0);
				if( ! objectList.isEmpty()) {
					objectList.get(0).setSelected(true);
				}
			} else {
				throw new IllegalStateException("There are no shapes on the board!");
			}
		}
	}

	/**
	 * This is a method that returns a copy of the ArrayList that contains all of the objects handled by DrawingBoard.
	 * @return ArrayList<AbstractShape>
	 */
	public ArrayList<AbstractShape> getShapes() {
		ArrayList<AbstractShape> ar = new ArrayList<AbstractShape>();
		ar.addAll(objectList);
		return ar;
	}
}
