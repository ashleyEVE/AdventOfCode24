package com.ashley_eve.day1;

import java.util.Collection;

import com.ashley_eve.DayTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


/**
 * The {@code Solution} class for this day which extends {@link DayTemplate}.
 * It optionally runs the test solution and/or problem solutions based on the provided configuration properties.
 * <p>
 * The class is marked as a Spring {@link Component} and its instantiation is conditional
 * on the "day1.run" property being set to true.
 * <p>
 * The primary responsibilities of this class include:
 * <ul>
 *   <li>Initializing and running the test solution if required</li>
 *   <li>Solving the first problem if required</li>
 *   <li>Solving the second problem if required</li>
 * </ul>
 * <p>
 * Configuration properties:
 * <ul>
 *   <li>{@code day1.test.run}: Indicates whether the test solution needs to be run</li>
 *   <li>{@code day1.problem.one.run}: Indicates whether the solution for the first problem needs to be run</li>
 *   <li>{@code day1.problem.two.run}: Indicates whether the solution for the second problem needs to be run</li>
 * </ul>
 * </p>
 * <p>
 * Example of property configuration:
 * <pre>
 * {@code
 * day1.run=true
 * day1.test.run=true
 * day1.problem.one.run=false
 * day1.problem.two.run=true
 * }
 * </pre>
 * </p>
 */
@Component
@ConditionalOnProperty(value = "day1.run", havingValue = "true")
public class Solution extends DayTemplate {
    /**
     * Creates an instance of the Solution class for Day 1, and optionally runs the test and/or problem solutions
     * based on the provided configuration properties.
     *
     * @param runTest       {@code true} if the test solution needs to be run, {@code false} otherwise
     * @param runProblemOne {@code true} if the first problem solution needs to be run, {@code false} otherwise
     * @param runProblemTwo {@code true} if the second problem solution needs to be run, {@code false} otherwise
     */
    public Solution(@Value("${day1.test.run:false}") final boolean runTest,
                    @Value("${day1.problem.one.run:false}") final boolean runProblemOne,
                    @Value("${day1.problem.two.run:false}") final boolean runProblemTwo) {
        super(1);

        if (runTest) {
            testSolution();
        }
        if (runProblemOne) {
            solveProblemOne();
        }
        if (runProblemTwo) {
            solveProblemTwo();
        }
    }

    /**
     * Tests the solution by performing necessary checks to validate
     * if the solution can proceed to solve problems.
     */
    private void testSolution() {
        final Collection<byte[]> problemData = getTestData();

        final StringBuilder dataStringBuilder = new StringBuilder();
        for (final byte[] dataArray : problemData) {
            dataStringBuilder.append(new String(dataArray));
        }
        writeTestSolution(dataStringBuilder.toString());
    }

    /**
     * Attempts to solve the first problem based on the data available
     * and defined logic.
     */
    private void solveProblemOne() {
        final Collection<byte[]> problemData = getProblem1Data();
        final StringBuilder dataStringBuilder = new StringBuilder();
        for (final byte[] dataArray : problemData) {
            dataStringBuilder.append(new String(dataArray));
        }
        writeSolution(1, dataStringBuilder.toString());
    }

    /**
     * Attempts to solve the first problem based on the data available
     * and defined logic.
     */
    private void solveProblemTwo() {
        final Collection<byte[]> problemData = getProblem2Data();
        final StringBuilder dataStringBuilder = new StringBuilder();
        for (final byte[] dataArray : problemData) {
            dataStringBuilder.append(new String(dataArray));
        }
        writeSolution(2, dataStringBuilder.toString());
    }
}
