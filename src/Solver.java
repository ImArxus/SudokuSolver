import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

import stev.booleans.BooleanFormula;

public class Solver {

	protected ISolver mSolver;

	public Solver(BooleanFormula formula) {
		this.mSolver = createSolver(formula);
	}

	private static ISolver createSolver(BooleanFormula formula) {
		int[][] clauses = formula.getClauses();
		ISolver solver = org.sat4j.minisat.SolverFactory.newDefault();
		solver.newVar(1000000);
		solver.setExpectedNumberOfClauses(clauses.length);

		for (int[] clause : clauses) {
			try {
				if (clause.length > 0) {
					solver.addClause(new VecInt(clause));
				}
			} catch (ContradictionException e) {
			}
		}

		return solver;
	}

	public void printSolution(BooleanFormula formula) {
		Map<String, Integer> variables = formula.getVariablesMap();
		List<Integer> validValues = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : variables.entrySet()) {
			for (int current : mSolver.model()) {
				if (entry.getValue() == current && current > 0) {
					validValues.add(Integer.parseInt(entry.getKey()));
				}
			}
		}

		Collections.sort(validValues);
		System.out.println("Sudoku résolu : ");
		for (int current : validValues) {
			System.out.print(current % 10);
		}
	}

}
