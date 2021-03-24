import java.util.HashSet;

interface State {
	abstract boolean isFinalState();	// test na koncovy stav hladania
	abstract HashSet<State> next();			// nasledujuci/susedny stav
	abstract boolean isCorrect();		// test na korektnost stavu
}