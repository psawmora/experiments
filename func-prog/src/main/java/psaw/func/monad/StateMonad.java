package psaw.func.monad;

import java.util.function.Function;

/**
 * <p>
 * <p>
 * bind :: Ma -> a -> Mb -> Mb
 * Here the Ma is a function. So in the bind operation, we need to get the a from applying Ma to a given state and then again
 * apply that value to the transformation function to get the new monad.
 * <p>
 * f :: a -> s -> (b,s)
 * g :: b -> s -> (c,s)
 * <p>
 * bind :: (s->(a,s)) -> a -> (s->(b,s)) -> (s->(b,s))
 * bind Ma ::
 * <p>
 * <p>
 * <p>
 * </p>
 *
 * @param <S>
 * @param <A>
 */
public class StateMonad<S, A> {

    Function<S, StateValueTuple<S, A>> stateRunner; // a

    public StateMonad(Function<S, StateValueTuple<S, A>> stateRunner) {
        this.stateRunner = stateRunner;
    }

    public static <S, A> StateMonad<S, A> apply(Function<S, StateValueTuple<S, A>> stateRunner) {
        return new StateMonad<>(stateRunner);
    }

    public static <S, A> StateMonad<S, A> unit(A a) {
        return new StateMonad<>(s -> new StateValueTuple<>(s, a));
    }

    public StateMonad<S, A> bind(Function<A, StateMonad<S, A>> tf) {

        return new StateMonad<>(s -> {
            StateValueTuple<S, A> result = stateRunner.apply(s);
            return tf.apply(result.a).stateRunner.apply(result.s);
        });
    }

    public StateValueTuple<S, A> eval(S s) {
        return stateRunner.apply(s);
    }

}