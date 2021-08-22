package kickstart.year2021.rounde.problem1;

import kickstart.AbstractSolutionTests;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Solution}.
 *
 * @author Johnny Lim
 */
class SolutionTests extends AbstractSolutionTests {

    @Override
    protected void doTest() {
        Solution.main(new String[0]);
    }

    @Test
    void getShuffledAnagramWhenTestSet1Case4() {
        assertThat(Solution.getShuffledAnagram("hhhjehho")).isEqualTo("IMPOSSIBLE");
    }

    @Test
    void getShuffledAnagramWhenTestSet1Case24() {
        assertThat(Solution.getShuffledAnagram("x")).isEqualTo("IMPOSSIBLE");
    }

}
