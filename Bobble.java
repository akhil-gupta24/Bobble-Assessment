import java.util.ArrayList;
import java.util.Iterator;

//Generation of ConcurrentModificationException --> 

//ConcurrentModificationException occurs when a object is being modified concurrently by multiple thread 
//in other words if one thread is iterating on collection object and at same time another thread
//try to modifiy the same underlying collection object then it will cause ConcurrentModificationExceptionto be thrown
public class Bobble {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("English");
		list.add("Hindi");
		list.add("Marathi");
		list.add("Telugu");

		// Updating or modifying element of arraylist during iteration. This will cause
		// to throw ConcurrentModificationException (Unchecked Exception)
		for (String s : list) {
			if (s.equals("Hindi")) {
				list.remove("Hindi"); // Here we are using list.remove() method i.e. modifying the Collection object
										// concurrently by multiple threads, which is not acceptable. So this method is
										// not thread-safe
			}
		}

		// for each loop behind the scene is using the iterator. The same exception can
		// be occur through the use of iterator instead of for each loop

		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			if (iterator.next().equals("Hindi"))
				list.remove("Hindi"); // cause to throw ConcurrentModificationException
		}

	}
}
