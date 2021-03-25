import java.util.*;

public class DFA<S,A> {
    private Set<S> states;          // mnozina stavov
    private S initState;            // pociatocny stav
    private Set<A> alphabet;        // abeceda symbolov slova
    private Set<S> finalStates;     // mnozina koncovych/akceptujucich stavov
    private Map<S, Map<A, S>> delta; // delta funkcia, zobrazuje stav a pismenko na novy stav

    public DFA(Set<S> states, S initState, Set<A> alphabet, Set<S> finalStates, Map<S, Map<A, S>> delta) {
    //public NFA(Set<S> states, S initState, Set<A> alphabet, Set<S> finalStates, Map<S, Map<A, Set<S>>> delta) {

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
    ////////////////// potialto to nemente

    /**
     * tento kus kodu som vam nechal len preto, aby
     * som ilustroval, ze zistit, ci automat akceptuje
     * dane vstupne slovo je uplne priamociare, a aj
     * ako ilustraciu na pracu so strukturami automatu
     */
    public boolean accept(List<A> word) {
        S s = initState;
//        for (A ch : word) {
//            s = delta.get(s).get(ch);    // s = delta[s][ch];
//        }
        for(int i =0; i < word.size(); i++) {
            s = delta.get(s).get(word.get(i));    // s = delta[s][ch];
        }
        return finalStates.contains(s);
    }
    public boolean isCorrect() {
        for (S s : states)
            for (A a : alphabet)
                if (delta == null || delta.get(s) == null || delta.get(s).get(a) == null)
                    return false;
        return states.containsAll(finalStates) && states.contains(initState);
    }
    public boolean daSa(S s) {  // pozor na SO
        if (finalStates.contains(s)) return true;
        for (A a:alphabet) {
            if (daSa(delta.get(s).get(a)))
                return true;
        }
        return false;
    }

    public boolean daSa(S s, List<A> word) {   // pozor na SO
        if (finalStates.contains(s)) return true;
        for (A a:alphabet) {
            List<A> nword = new ArrayList<>(word);
            nword.add(a);
            if (daSa(delta.get(s).get(a),nword))
                return true;
        }
        return false;
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
        var dfa = new DFA<String, Character>(
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
        System.out.println(dfa);
        System.out.println(dfa.accept(List.of('1','1','0','0','1','0','0','1')));
        System.out.println(dfa.language(10));
        System.out.println(dfa.prazdny());
        System.out.println(dfa.nekonecny());
    }
}
