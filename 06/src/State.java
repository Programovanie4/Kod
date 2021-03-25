import java.util.HashSet;
import java.util.Set;

interface State<S> {
	abstract boolean isFinalState();	// test na koncovy stav hladania
	abstract Set<S> next();			    // nasledujuci/susedny stav
	abstract boolean isCorrect();		// test na korektnost stavu
}


//interface State {
//    abstract boolean isFinalState();	// test na koncovy stav hladania
//    abstract Set<State> next();			// nasledujuci/susedny stav
//    abstract boolean isCorrect();		// test na korektnost stavu
//}