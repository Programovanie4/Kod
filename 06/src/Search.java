import java.util.ArrayList;
import java.util.List;

public class Search<S extends State<S>> {
	List<List<State<S>>> riesenia;

	public Search() {
		riesenia = new ArrayList<>();
	}

	public void add(List<State<S>> s) {
        List<State<S>> ss = new ArrayList<>(s);
		riesenia.add(ss);
	}

	public String toString() {
		return "" + riesenia.toString() + "\n" + riesenia.size();
	}

	/**
	 * tento sa zacykli pri cyklickom grafe
	 */
	public void searchWhichLoops(S s) {
		if (s.isFinalState()) 
			add(List.of(s));
		else
			for (State<S> ns : s.next())
				search(ns);
	}

	public List<List<State<S>>> search(State<S> s) {
		ArrayList<State<S>> visited = new ArrayList<>();
		visited.add(s);
		search(s, visited);
		return riesenia;
	}

	public void search(State<S> s, ArrayList<State<S>> visited) {
		if (s.isFinalState()) {
			add(visited);
		} else
			for (State<S> ns : s.next()) {
				if (!visited.contains(ns) && ns.isCorrect()) {
					visited.add(ns);
					search(ns, visited);
					visited.remove(ns);
				}
			}
	}

	public void search(S s, boolean DFS) {
		ArrayList<State<S>> queue = new ArrayList<>();
		queue.add(s);
		ArrayList<State<S>> visited = new ArrayList<>();
		search(queue, visited, DFS);
	}

	private void search(ArrayList<State<S>> queue, ArrayList<State<S>> visited, boolean DFS) {
		while (queue.size() > 0) {
			State<S> s = queue.remove(0);
			if (s.isFinalState()) {
				add(visited);
			} else {
				for (State<S> ns : s.next()) {
					if (!visited.contains(ns) && ns.isCorrect()) {
						visited.add(ns);
						if (DFS)
							queue.add(0, ns);
						else
							queue.add(queue.size(),ns);
					}
				}
			}
		}
	}
	
	private void search(ArrayList<State<S>> queue, ArrayList<State<S>> visited, ArrayList<State<S>> comeFrom, boolean DFS) {
		while (queue.size() > 0) {
            State<S> s = queue.remove(0);
			if (s.isFinalState()) {
				add(visited);
			} else {
				for (State<S> ns : s.next()) {
					if (!visited.contains(ns) && ns.isCorrect()) {
						visited.add(ns);
						comeFrom.add(s);
						if (DFS) {
							queue.add(0,  ns);
						} else
							queue.add(queue.size(), ns);
					}
				}
			}
		}
	}
}
