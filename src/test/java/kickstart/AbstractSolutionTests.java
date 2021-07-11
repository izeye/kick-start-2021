package kickstart;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base class for solution tests.
 *
 * @author Johnny Lim
 */
public abstract class AbstractSolutionTests {

    private static final InputStream standardInput = System.in;
    private static final PrintStream standardOutput = System.out;

    private ByteArrayOutputStream outputStream;

    @Test
    void runAgainstSample() throws IOException {
        run(TestTarget.SAMPLE);
    }

    private void run(TestTarget target) throws IOException {
        prepare(target);
        doTest();
        verifyAndCleanUp(target);
    }

    @Test
    void runAgainstTestSet1() throws IOException {
        try {
            run(TestTarget.TEST_SET_1);
        }
        catch (FileNotFoundException ex) {
            disable();
        }
    }

    private void disable() {
        // Better way to disable a test?
        Assumptions.assumeTrue(false);
    }

    @Test
    void runAgainstTestSet2() throws IOException {
        try {
            run(TestTarget.TEST_SET_2);
        }
        catch (FileNotFoundException ex) {
            disable();
        }
    }

    private void prepare(TestTarget target) throws FileNotFoundException {
        FileInputStream testInput = getTestInputStream(target);
        this.outputStream = new ByteArrayOutputStream();
        PrintStream testOutput = new PrintStream(this.outputStream);

        System.setIn(testInput);
        System.setOut(testOutput);
    }

    protected abstract void doTest();

    private void verifyAndCleanUp(TestTarget target) throws IOException {
        System.setIn(standardInput);
        System.setOut(standardOutput);

        String output = this.outputStream.toString(StandardCharsets.UTF_8);
        String expectedOutput = getExpectedOutput(target);
        assertThat(output).isEqualTo(expectedOutput);
    }

    private FileInputStream getTestInputStream(TestTarget target) throws FileNotFoundException {
        String inputFilePath = getFilePath(target, "input.txt");
        return new FileInputStream(inputFilePath);
    }

    private String getFilePath(TestTarget target, String filename) {
        return "src/test/resources/" + getClass().getPackageName().replace('.', '/') + "/" + target.name().toLowerCase() + "/" + filename;
    }

    private String getExpectedOutput(TestTarget target) throws IOException {
        String outputFilePath = getFilePath(target, "output.txt");
        return Files.readString(Path.of(outputFilePath));
    }

}

enum TestTarget {

    SAMPLE,
    TEST_SET_1,
    TEST_SET_2

}
