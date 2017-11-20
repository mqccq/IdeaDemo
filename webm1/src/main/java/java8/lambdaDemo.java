package java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2017/10/10.
 */
public class lambdaDemo {
    private enum Status {
        OPEN, CLOSED
    };

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }

    public static void main(String[] args) {
        /*String separator = ",";
        Arrays.asList("a","b","c").forEach((String e) ->
            System.out.println(e + separator)
        );*/
        /*Arrays.asList("a","d","b").sort((e1,e2)->e1.compareTo(e2));
        final Collection< Task > tasks = Arrays.asList(
                new Task( Status.OPEN, 5 ),
                new Task( Status.OPEN, 13 ),
                new Task( Status.CLOSED, 8 )
        );
        final long totalPointsOfOpenTasks = tasks.stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println( "Total points: " + totalPointsOfOpenTasks );*/
        Optional<String> firstName = Optional.of(null);
        System.out.println(firstName.isPresent());
    }
}
