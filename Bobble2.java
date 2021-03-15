import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//Resolving and preventing ConcurrentModificationException (3 Methods)
//First Method : Concurrent Collection (Java 1.5 +)
//Second Method : use of Iterator methods
//Third Method : use of removeIf method (Java 8)
public class Bobble2 {
	public static void main(String[] args) {
		// Using concurrent Collection object --> CopyOnWriteArrayList()
		List<String> concurrentlist = new CopyOnWriteArrayList<>();
		// Simple arraylist
		List<String> list = new ArrayList<>();
		list.add("English");
		concurrentlist.add("English");
		list.add("Hindi");
		concurrentlist.add("Hindi");
		list.add("Marathi");
		concurrentlist.add("Marathi");
		list.add("Telugu");
		concurrentlist.add("Telugu");

		// First Method
		Concurrent(concurrentlist);

		// Second Method
		IteratorMethod(list);

		// Third Method
		RemoveIfMethod(list);

	}

	public static void Concurrent(List<String> list) {
		// First way to resolve this exception is to use Concurrent Collections object
		// (Jdk 1.5 +)
		// like CopyOnWriteArrayList, CopyOnWriteArraySet, ConcurrentHashMap (Best way
		// if we are working with collections object which are modified by multiple
		// thread concurrently
		for (String s : list) {
			if (s.equals("Hindi")) {
				// as we are using concurrent collection --> CopyOnWriteArrayList, this will now
				// not throw the ConcurrentModificationException as this class method is
				// synchronized performing operations in synchronized block
				list.remove("Hindi");
			}
		}
		System.out.println(list);
	}

	public static void IteratorMethod(List<String> list) {
		// Second way is to use Iterator methods instead of Collection method (like
		// remove etc) when we are dealing with single thread
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String s = iterator.next();
			if (s.equals("Hindi")) {
				iterator.remove(); // using Iterator method for modification
			}
		}
		System.out.println(list);
	}

	public static void RemoveIfMethod(List<String> list) {
		// Third method to prevent exception is by using removeIf() method. (Java 8)
		list.removeIf(lang -> lang.equals("Hindi"));
		System.out.println(list);
	}
}
