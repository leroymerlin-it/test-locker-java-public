package it.leroymerlin.locker.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SizeTest {

    @Test
    void checkPossibleInclusion() {
        Size targetSize = Size.of(10, 20, 30);

        // Smaller
        assertThat(targetSize.couldContain(Size.of(5, 15, 25)), is(true));
        assertThat(targetSize.couldContain(Size.of(15, 5, 25)), is(true));
        assertThat(targetSize.couldContain(Size.of(25, 15, 5)), is(true));

        // Larger
        assertThat(targetSize.couldContain(Size.of(35, 5, 5)), is(false));
        assertThat(targetSize.couldContain(Size.of(5, 35, 5)), is(false));
        assertThat(targetSize.couldContain(Size.of(5, 5, 35)), is(false));

        // The same
        assertThat(targetSize.couldContain(Size.of(30, 10, 20)), is(true));

    }

}