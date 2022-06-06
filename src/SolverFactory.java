import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

import stev.booleans.BooleanFormula;

public class SolverFactory {

	public static ISolver createSolver(BooleanFormula formula) {
		int[][] clauses = formula.getClauses();
		ISolver solver = org.sat4j.minisat.SolverFactory.newDefault();
		solver.newVar(1000000);
		solver.setExpectedNumberOfClauses(clauses.length);

		for (int[] clause : clauses) {
			try {
				solver.addClause(new VecInt(clause));
			} catch (ContradictionException e) {
			}
		}
		
		return solver;
	}

}
