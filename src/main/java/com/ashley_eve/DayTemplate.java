package com.ashley_eve;

import java.util.Collection;

import com.ashley_eve.file.InputReader;
import com.ashley_eve.file.SolutionWriter;


/**
 * The {@code TestDayTemplate} class represents a template for handling the test data
 * associated with a specific day's problems. It uses an {@link InputReader} to read input files
 * and stores the data in appropriate collections.
 *
 * <p>The class provides methods to generate file paths for input files given a day number and
 * a problem id, and it initializes collections for test data and the two problem datasets.
 *
 * <p>Note: The input files should be in the format specified by the {@link #getFile} method.
 *
 * <ul>
 * <li>{@code testData} - Collection to store the test data</li>
 * <li>{@code problem1Data} - Collection to store the data from the first problem input file</li>
 * <li>{@code problem2Data} - Collection to store the data from the second problem input file</li>
 * </ul>
 *
 * @see InputReader
 */
public class DayTemplate {
    /** Collection to store the test data. */
    private final Collection<byte[]> testData;

    /** Collection to store the data from the first problem input file. */
    private final Collection<byte[]> problem1Data;

    /** Collection to store the data from the second problem input file. */
    private final Collection<byte[]> problem2Data;

    /** The number of the day for which this template is created. */
    private final int dayNumber;

    /**
     * Constructs an instance of TestDayTemplate for a specified day number.
     *
     * @param dayNumber the number of the day for which this template is created
     */
    protected DayTemplate(final int dayNumber) {
        final InputReader inputReader = new InputReader(getFile(dayNumber, "1"),
                getFile(dayNumber, "2"),
                getFile(dayNumber, ""));
        this.dayNumber = dayNumber;
        this.testData = inputReader.getTestData();
        this.problem1Data = inputReader.getProblem1Data();
        this.problem2Data = inputReader.getProblem2Data();
    }


    /**
     * Converts the given collection of byte arrays to a collection of characters.
     * Assumes each byte array contains a single character.
     *
     * @param data the collection of byte arrays to convert
     * @return a collection of characters
     */
    public static Collection<Character> convertToCharCollection(final Collection<byte[]> data) {
        return data.stream().map(val -> (char) val[0]).toList();
    }

    /**
     * This method generates the file path for the input files
     * based on the given day number and problem id.
     *
     * @param dayNumber the number of the day for which the file path needs to be generated
     * @param problemId the id of the problem for which the file path needs to be generated
     * @return the file path as a String
     */
    private String getFile(final int dayNumber, final String problemId) {
        if (problemId.isEmpty()) {
            return String.format("day%d/test.input", dayNumber);
        } else {
            return String.format("day%d/problem%s.input", dayNumber, problemId);
        }
    }

    /**
     * Gets the collection of test data.
     *
     * @return the test data collection
     */
    protected Collection<byte[]> getTestData() {
        return this.testData;
    }

    /**
     * Gets the collection of data from the first problem input file.
     *
     * @return the data from the first problem input file collection
     */
    protected Collection<byte[]> getProblem1Data() {
        return this.problem1Data;
    }

    /**
     * Gets the collection of data from the second problem input file.
     *
     * @return the data from the second problem input file collection
     */
    protected Collection<byte[]> getProblem2Data() {
        return this.problem2Data;
    }

    /**
     * Writes the provided test solution data to a file.
     *
     * @param data the test solution data to write
     */
    protected void writeTestSolution(final String data) {
        SolutionWriter.writeSolutionToFile(this.dayNumber, 0, data);
    }

    /**
     * Writes the provided solution data for a specified problem number to a file.
     *
     * @param problemNumber the number of the problem to which the solution data pertains
     * @param data          the solution data to write
     */
    protected void writeSolution(final int problemNumber, final String data) {
        SolutionWriter.writeSolutionToFile(this.dayNumber, problemNumber, data);
    }
}
