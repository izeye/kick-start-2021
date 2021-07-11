package kickstart;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

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

    // @BeforeAll is better here, but it doesn't fit well with the inheritance.
    @BeforeEach
    void beforeEach() throws FileNotFoundException {
        FileInputStream testInput = getTestInputStream();
        this.outputStream = new ByteArrayOutputStream();
        PrintStream testOutput = new PrintStream(this.outputStream);

        System.setIn(testInput);
        System.setOut(testOutput);
    }

    private FileInputStream getTestInputStream() throws FileNotFoundException {
        String inputFilePath = "src/test/resources/" + getClass().getPackageName().replace('.', '/') + "/input.txt";
        return new FileInputStream(inputFilePath);
    }

    // @AfterAll is better here, but it doesn't fit well with the inheritance.
    @AfterEach
    void afterEach() throws IOException {
        System.setIn(standardInput);
        System.setOut(standardOutput);

        String output = this.outputStream.toString(StandardCharsets.UTF_8);
        String expectedOutput = getExpectedOutput();
        assertThat(output).isEqualTo(expectedOutput);
    }

    private String getExpectedOutput() throws IOException {
        String outputFilePath = "src/test/resources/" + getClass().getPackageName().replace('.', '/') + "/output.txt";
        return Files.readString(Path.of(outputFilePath));
    }

}
