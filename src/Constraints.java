import stev.booleans.*;
import stev.booleans.BooleanFormula;
import stev.booleans.PropositionalVariable;

public class Constraints {

	public static BooleanFormula checkRow(int gridSize, BooleanFormula f) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int rowIndex = i / 100;
				int columnIndex = (i / 10) % 10;
				int value = i % 10;

				for (int k = 1; k <= gridSize; k++) {
					if (k != columnIndex) {
						String stri = String.valueOf(i);
						StringBuilder str = new StringBuilder().append(rowIndex).append(k).append(value);
						f = new And(f, new Implies(new PropositionalVariable(stri),
								new Not(new PropositionalVariable(str.toString()))));
						System.out.println(stri + " " + str);
					}
				}
			}
			f = BooleanFormula.toCnf(f);
		}

		return f;
	}

	public static BooleanFormula checkColumn(int gridSize, BooleanFormula f) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int a = i % 100;
				int b = i / 100;
				for (int k = 1; k <= gridSize; k++) {
					if (k != b) {
						String stri = String.valueOf(i);
						StringBuilder str = new StringBuilder().append(k).append(a);
						f = new And(f, new Implies(new PropositionalVariable(stri),
								new Not(new PropositionalVariable(str.toString()))));
						System.out.println(stri + " " + str);
					}
				}

			}
			f = BooleanFormula.toCnf(f);
		}

		return f;
	}

	// TODO
	public static BooleanFormula checkSquare(int gridSize, BooleanFormula f) {
		for (int i = 111; i <= (111 * gridSize); i++) {

			f = BooleanFormula.toCnf(f);
		}

		return f;
	}

	// TODO
	public static void checkCellsValue(int gridSize) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				for (int k = 1; k <= gridSize; k++) {
					int a = i / 10;
					int c = i % 10;
					if (k != c) {
						String stri = String.valueOf(i);
						String str = String.valueOf(a + "" + k);
						// f = new And(f, new Implies(new PropositionalVariable(stri), new Not(new
						// PropositionalVariable(str))));
						System.out.println(stri + " " + str);
					}
				}

			}
		}
	}

}
