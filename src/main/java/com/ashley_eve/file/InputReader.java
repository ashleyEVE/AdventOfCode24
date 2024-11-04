package com.ashley_eve.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The {@code InputReader} class is responsible for reading input files for different problems.
 * It reads and logs the contents of three specific input files using their paths.
 *
 * <p>Usage example:
 * <pre>
 *     InputReader inputReader = new InputReader("path/to/problem1.input", "path/to/problem2.input",
 *     "path/to/test.input");
 * </pre>
 *
 * <p>Note: The input files should be placed in the resources folder of the project.
 */
public class InputReader {

    /** Static class logger. */
    private static final Logger LOG = LogManager.getLogger(InputReader.class);

    /** List to store test data. */
    private final List<byte[]> testData;

    /** List to store data from the first problem input file. */
    private final List<byte[]> problem1Data;

    /** List to store data from the second problem input file. */
    private final List<byte[]> problem2Data;

    /**
     * Constructs an InputReader to read from three input files specified by their
     * paths.
     *
     * @param problem1InputFile the path to the first input file
     * @param problem2InputFile the path to the second input file
     * @param testInputFile     the path to the test input file
     */
    public InputReader(final String problem1InputFile, final String problem2InputFile, final String testInputFile) {
        this.problem1Data = readFileData(problem1InputFile);
        this.problem2Data = readFileData(problem2InputFile);
        this.testData = readFileData(testInputFile);
    }


    /**
     * Reads the data from the specified input file and returns it as a list of byte arrays.
     * Each element in the returned list corresponds to a line in the input file.
     *
     * @param inputFile the input file stream to read data from
     * @return a list of byte arrays, each representing a line of the input file
     */
    private List<byte[]> readFileData(final String fileName) {
        final List<byte[]> data = new ArrayList<>();
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            final InputStreamReader inputReader = new InputStreamReader(new BufferedInputStream(inputStream));

            try (BufferedReader bufferedReader = new BufferedReader(inputReader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    data.add(line.getBytes());
                }
            } catch (final IOException e) {
                LOG.error(() -> String.format("Failed to read input file %1$s", fileName));
            }
        } else {
            LOG.error(() -> String.format("Input file not found: %1$s", fileName));
        }
        return data;
    }

    /**
     * Gets the test data.
     *
     * @return the collection of test data
     */
    public Collection<byte[]> getTestData() {
        return this.testData;
    }

    /**
     * Gets the data from the first problem input file.
     *
     * @return the collection of data from the first problem input file
     */
    public Collection<byte[]> getProblem1Data() {
        return this.problem1Data;
    }

    /**
     * Gets the data from the second problem input file.
     *
     * @return the collection of data from the second problem input file
     */
    public Collection<byte[]> getProblem2Data() {
        return this.problem2Data;
    }
}