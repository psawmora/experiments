package psaw.func.monad;

import java.util.function.Function;

/**
 * <p>
 * <code>StateMonadTest</code> -
 * Tests state monad usage
 * </p>
 *
 * @author: prabath
 */
public class StateMonadTest {

    public static void main(String[] args) {
        testMonad();
        testMonad2();
    }

    public static void testMonad() {
        Function<Integer, StateMonad<String, Integer>> monadFunc = i -> {
            int i2 = i * 5;
            String log = " Calculated Value is " + i2;
            return new StateMonad<>(s -> new StateValueTuple<>(s.concat(log), i2));
        };

        StateMonad<String, Integer> stateMonad = StateMonad.<String, Integer>unit(-1).
                bind((i) -> monadFunc.apply(1).
                        bind((i2) -> monadFunc.apply(2)));

        StateValueTuple<String, Integer> result = stateMonad.eval("");
        System.out.println("State is : " + result.s);
    }

    private static void testMonad2() {

        Function<Integer, Function<Integer, StateMonad<String, Integer>>> monadFuncApplier = i ->
                j -> {
                    int tmp = i * 5;
                    return new StateMonad<>(
                            s -> new StateValueTuple<>(s.concat(" Calculated Value is ").concat(String.valueOf(tmp)), tmp));
                };


        StateMonad<String, Integer> stateMonad = StateMonad.<String, Integer>unit(1).
                bind(monadFuncApplier.apply(2)).bind(monadFuncApplier.apply(3));

        StateValueTuple<String, Integer> result = stateMonad.eval("");
        System.out.println("State is : " + result.s);
    }
}
