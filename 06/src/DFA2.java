import java.util.*;

public class DFA2<S,A> {
    private Set<S> states;          // mnozina stavov
    private S initState;            // pociatocny stav
    private Set<A> alphabet;        // abeceda symbolov slova
    private Set<S> finalStates;     // mnozina koncovych/akceptujucich stavov
    private Map<S, Map<A, S>> delta; // delta funkcia, zobrazuje stav a pismenko na novy stav

    public DFA2(Set<S> states, S initState, Set<A> alphabet, Set<S> finalStates, Map<S, Map<A, S>> delta) {
        this.states = states;
        this.initState = initState;
        this.alphabet = alphabet;
        this.finalStates = finalStates;
        this.delta = delta;
    }

    @Override
    public String toString() {
        return "DFA{" +
                "states=" + states +
                ", initState=" + initState +
                ", alphabet=" + alphabet +
                ", finalStates=" + finalStates +
                ", delta=" + delta +
                '}';
    }

    public Set<S> getStates() {
        return states;
    }

    public S getInitState() {
        return initState;
    }

    public Set<A> getAlphabet() {
        return alphabet;
    }

    public Set<S> getFinalStates() {
        return finalStates;
    }

    public Map<S, Map<A, S>> getDelta() {
        return delta;
    }
    //---------------------------------------------
    class DFAState<S> implements State<DFAState<S>> {
        S state;
        public DFAState(S state) {
            this.state = state;
        }

        @Override
        public boolean isFinalState() {
            return finalStates.contains(state);
        }

        @Override
        public Set<DFAState<S>> next() {
            var res = new HashSet<DFAState<S>>();
            var col = delta.get(state).values();
//            for(S s : delta.get(state).values())
//                res.add(new DFAState(s));

            for(var it = col.iterator(); it.hasNext(); )
                res.add(new DFAState<S>((S)it.next()));
            return res;
        }

        @Override
        public boolean isCorrect() {
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DFAState<?> dfaState = (DFAState<?>) o;
            return Objects.equals(state, dfaState.state);
        }

        @Override
        public int hashCode() {
            return Objects.hash(state);
        }
    }

    public boolean daSa(S s) {
        return new Search<DFAState<S>>().search(new DFAState<S>(s)).size() > 0;
    }


    public boolean prazdny() {
        return false; // DU 6
    }

    public boolean nekonecny() {
        return false; // DU 6
    }
    public Set<List<A>> language(int len) {
        return null; // DU 6
    }

    public static void main(String[] args) {
        var dfa = new DFA2<String, Character>(
                Set.of("a", "b", "c", "d"),
                "a",
                Set.of('0', '1'),
                Set.of("d"),
                Map.of("a", Map.of(
                                '0', "b",
                                '1', "a"),
                       "b", Map.of(
                                '0', "b",
                                '1', "c"),
                       "c", Map.of(
                                '0', "d",
                                '1', "a"),
                        "d", Map.of(
                                '0', "d",
                                '1', "d")

                )
        );
        //System.out.println(dfa.accept(List.of('1','1','0','0','1','0','0','1')));
        System.out.println(dfa.language(10));
        System.out.println(dfa.prazdny());
        System.out.println(dfa.nekonecny());
        System.out.println(dfa.daSa("a"));
    }
}
