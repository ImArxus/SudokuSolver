import java.util.Arrays;

import stev.booleans.*;

public class Main {

	public static void main(String[] args) {
		
		PropositionalVariable p = new PropositionalVariable("p");
		PropositionalVariable q = new PropositionalVariable("q");
		PropositionalVariable r = new PropositionalVariable("r");
		PropositionalVariable s = new PropositionalVariable("s");
		
		// Subformula: r -> p
		Implies imp = new Implies(r, p);
		
		// Subformula !q
		Not not = new Not(q);
		
		// Subformula !q & (r -> p)
		And and_1 = new And(not, imp);
		
		// Subformula q & s
		And and_2 = new And(q, s);
		
		// The whole formula
		Or big_formula = new Or(p, and_1, and_2);
		
		// We can print it
		System.out.println(big_formula);
		
		// Convert this formula to CNF
		BooleanFormula cnf = BooleanFormula.toCnf(big_formula);
		
		// Let's print it again
		System.out.println(cnf);
		
		// Export this formula as an array of clauses
		int[][] clauses = cnf.getClauses();
		
		// What's in that array? First element corresponds to first clause: [1, -2, 3]
		System.out.println(Arrays.toString(clauses[0]));
		// Second element corresponds to second clause: [1, -3, 4]
		System.out.println(Arrays.toString(clauses[1]));
	}

}
