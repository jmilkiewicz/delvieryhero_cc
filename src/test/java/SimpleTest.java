import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleTest {
    private final List<Integer> input = List.of(2, 1, 3);

    @Test
    void shallReturnTrueOnSumLastAndFirstElem() {
        boolean result = sut(input, 5);
        assertThat(result, is(true));
    }

    @Test
    void shallReturnTrueOnSumLastAndSecondElem() {
        boolean result = sut(input, 4);
        assertThat(result, is(true));
    }

    @Test
    void shallReturnTrueOnSumFirstAndSecondElem() {
        boolean result = sut(input, 3);
        assertThat(result, is(true));
    }

    @Test
    void shallReturnFalseForFirstElem() {
        boolean result = sut(input, 2);
        assertThat(result, is(false));
    }

    @Test
    void shallReturnFalseForSumOfAlElem() {
        boolean result = sut(input, 6);
        assertThat(result, is(false));
    }

    @Test
    void shallReturnFalseForSecondElem() {
        boolean result = sut(input, 1);
        assertThat(result, is(false));
    }

    private boolean sut(List<Integer> input, int i) {
        return new Sut(input).check(i);
    }

    private static class Sut {
        private final List<Integer> sorted;

        Sut(List<Integer> input) {
            this.sorted = input.stream()
                    .sorted()
                    .collect(Collectors.toList());
        }

        public boolean check(Integer expected) {
            return go(0, sorted.size() - 1, expected);
        }

        private boolean go(int left, int right, Integer expected) {
            if (left == right) return false;
            int sum = sorted.get(left) + sorted.get(right);
            if (expected < sum) {
                return go(left, right - 1, expected);
            }
            if (expected > sum) {
                return go(left + 1, right, expected);
            }
            return true;
        }


    }
}
