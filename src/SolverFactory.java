import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

import stev.booleans.BooleanFormula;

public class SolverFactory {

	private static final int MAX_VAR = 1000000;

	public static ISolver createSolver(BooleanFormula formula) {
		int[][] clauses = formula.getClauses();
		ISolver solver = org.sat4j.minisat.SolverFactory.newDefault();
		solver.newVar(MAX_VAR);
		solver.setExpectedNumberOfClauses(clauses.length);

		for (int[] clause : clauses) {
			try {
				solver.addClause(new VecInt(clause));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
		}
		
		return solver;
	}

}
