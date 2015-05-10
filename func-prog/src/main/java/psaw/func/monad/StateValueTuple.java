package psaw.func.monad;

/**
 * <p></p>
 *
 * @author prabath.
 */
public class StateValueTuple<S, A> {

    public StateValueTuple() {
    }

    public StateValueTuple(S s, A a) {
        this.s = s;
        this.a = a;
    }

    public S s;

    public A a;


}
