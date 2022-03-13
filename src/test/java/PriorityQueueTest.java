import java.io.ObjectOutputStream;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}),
                Arguments.of(new int[]{5, 7, 2, 3, 6}, new int[]{2, 3, 5, 6, 7}),
                Arguments.of(new int[]{5}, new int[]{5}),
                Arguments.of(new int[]{}, new int[]{}),
                Arguments.of(new int[]{9, 8, 7, 6, 5, 4, 3}, new int[]{3, 4, 5, 6, 7, 8, 9})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void priorityQueueRunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<>();
        int index  = 0;
        Integer s;
        int[] result = new int[random_array.length];

        for (int i : random_array) {
            test.add(i);
        }
        for (int i = 0; !test.isEmpty(); i++) {
            result[i] = test.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void constructorExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<>(-1);
        });
    }

    @Test
    public void addExceptionTest() {
        assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<>();
            test.add(null);
        });
    }

    @Test
    public void toArrayExceptionTest() {
        assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<>();
            Integer[] array = null;
            test.add(1);
            test.add(2);
            test.add(3);
            test.toArray(array);
        });
    }
}
