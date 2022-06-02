import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;

import stev.booleans.*;

public class Main {

	public static void main(String[] args) {
		try {
			System.out.println("On cherche si le sudoku suivant est réalisable :\n" + args[0] + "\n");
			
			BooleanFormula formula = null;
			ISolver solver = SolverFactory.createSolver(BooleanFormula.toCnf(formula));

			IProblem problem = solver;
			if (problem.isSatisfiable()) {
				System.out.println("Le sudoku est réalisable");
			} else {
				System.out.println("Le sudoku n'est pas réalisable");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
