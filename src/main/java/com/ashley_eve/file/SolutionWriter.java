package com.ashley_eve.file;

import static java.lang.String.format;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The SolutionWriter class is used to write solution data to structured output files.
 *
 * <p>This class supports organizing the output files by day and problem numbers.
 * Each day's solutions can be stored in a dedicated directory named according to the day number.
 * The files within these directories can be named either by problem number or default to "test.output".</p>
 *
 * <p>Features:
 * <ul>
 *   <li>Automatic directory creation if it does not exist.</li>
 *   <li>Customizable file naming based on problem numbers.</li>
 *   <li>Logging for key operations such as file and directory creation, and data writing.</li>
 * </ul>
 * </p>
 *
 * <p>Usage example:
 * <pre>{@code
 * SolutionWriter.writeSolutionToFile(1, 2, "Some solution data");
 * }</pre>
 * </p>
 */
public class SolutionWriter {

    /** Static class logger. */
    private static final Logger LOG = LogManager.getLogger(SolutionWriter.class);


    /**
     * Private empty constructor to prevent instantiation.
     */
    private SolutionWriter() {
    }

    /**
     * The SolutionWriter class is responsible for writing solution data to files.
     * It supports writing data associated with specific day and problem numbers.
     *
     * <p>Example usage:
     * <pre>
     * {@code
     * SolutionWriter.writeSolutionToFile(1, "Some solution data");
     * }
     * </pre>
     * </p>
     *
     * <p>Notes:
     * <ul>
     *   <li>If the problem number is not provided, the file is named "test.output".</li>
     *   <li>If the directory for the given day number does not exist, it will be created.</li>
     *   <li>All output files are stored in directories named after the day number (e.g., "day1/").</li>
     * </ul>
     * </p>
     *
     * @param dayNumber the day number to organize the solution file (e.g., 1 for day1)
     * @param data      the solution data to write to the file
     */
    public static void writeSolutionToFile(final int dayNumber, final String data) {
        writeSolutionToFile(dayNumber, 0, data);
    }


    /**
     * Writes the solution data to a file organized by day and problem number.
     * If the problem number is zero, it writes to a default file named "test.output".
     *
     * <p>Example usage:
     * <pre>{@code
     * SolutionWriter.writeSolutionToFile(1, 2, "Some solution data");
     * }</pre>
     * </p>
     *
     * <p>Notes:
     * <ul>
     *   <li>If the directory for the given day number does not exist, it will be created.</li>
     *   <li>All output files are stored in directories named after the day number (e.g., "day1/").</li>
     * </ul>
     * </p>
     *
     * @param dayNumber     the day number to organize the solution file (e.g., 1 for day1)
     * @param problemNumber the problem number to include in the file name (0 to use the default "test.output")
     * @param data          the solution data to write to the file
     */
    public static void writeSolutionToFile(final int dayNumber, final int problemNumber, final String data) {
        String solutionFileName = "test.output";
        if (problemNumber != 0) {
            solutionFileName = format("solution%1$d.output", problemNumber);
        }
        final String formattedFilename = format("solutions/day%1$d/%2$s", dayNumber, solutionFileName);

        final File directory = new File(format("solutions/day%1$d", dayNumber));
        if (directory.mkdirs()) {
            LOG.debug(() -> format("Created directory: day%1$d", dayNumber));
        } else {
            LOG.trace(() -> format("Skipped making directory: day%1$d", dayNumber));
        }

        final File file = new File(formattedFilename);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    LOG.info(() -> String.format("Created new file: %1$s", file.getAbsolutePath()));
                }
            } catch (final IOException e) {
                LOG.debug(() -> format("Failed to create new file %1$s", formattedFilename));
            }
        }
        LOG.debug(() -> format("Formatted filename: %1$s", formattedFilename));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(formattedFilename))) {
            LOG.debug(() -> format("Writing solution to file: %1$s", data));
            writer.write(data);
        } catch (final IOException e) {
            LOG.warn(() -> format("Failed to write solution to file. %1$s", e.getMessage()));
        }
    }
}
