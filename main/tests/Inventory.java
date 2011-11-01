package tests;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory implements Iterable<SingleTest> {

	private ArrayList<SingleTest> testsArray;

	public Inventory() {
		this.testsArray = new ArrayList<SingleTest>();
	}

	@Override
	public Iterator<SingleTest> iterator() {
		Iterator<SingleTest> iTest = testsArray.iterator();
		return iTest;
	}

	public void add(SingleTest aTest) {
		testsArray.add(aTest);
	}

}
