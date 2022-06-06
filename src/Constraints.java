import stev.booleans.*;
import stev.booleans.BooleanFormula;
import stev.booleans.PropositionalVariable;

public class Constraints {

	public static BooleanFormula checkRows(int gridSize, BooleanFormula formula) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int rowIndex = i / 100;
				int columnIndex = (i / 10) % 10;
				int value = i % 10;

				for (int j = 1; j <= gridSize; j++) {
					if (j != columnIndex) {
						String stri = String.valueOf(i);
						StringBuilder str = new StringBuilder().append(rowIndex).append(j).append(value);
						formula = new And(formula, new Implies(new PropositionalVariable(stri),
								new Not(new PropositionalVariable(str.toString()))));
					}
				}
			}
			System.out.println("> checkRows : " + i);
			formula = BooleanFormula.toCnf(formula);
		}

		return formula;
	}

	public static BooleanFormula checkColumns(int gridSize, BooleanFormula formula) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int rowIndex = i / 100;
				int columnIndex = (i / 10) % 10;
				int value = i % 10;

				for (int j = 1; j <= gridSize; j++) {
					if (j != rowIndex) {
						String stri = String.valueOf(i);
						StringBuilder str = new StringBuilder().append(j).append(columnIndex).append(value);
						formula = new And(formula, new Implies(new PropositionalVariable(stri),
								new Not(new PropositionalVariable(str.toString()))));
					}
				}
			}
			System.out.println("> checkColumns : " + i);
			formula = BooleanFormula.toCnf(formula);
		}

		return formula;
	}

	public static BooleanFormula checkSquares(int gridSize, BooleanFormula formula) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int rowIndex = i / 100;
				int columnIndex = (i / 10) % 10;
				int value = i % 10;

				for (int j = 1; j <= gridSize; j++) {
					for (int k = 1; k <= gridSize; k++) {
						if ((j != rowIndex || k != columnIndex) && getSquareNumber(rowIndex, columnIndex,
								gridSize) == getSquareNumber(j, k, gridSize)) {
							String stri = String.valueOf(i);
							StringBuilder str = new StringBuilder().append(j).append(k).append(value);
							formula = new And(formula, new Implies(new PropositionalVariable(stri),
									new Not(new PropositionalVariable(str.toString()))));
						}
					}
				}
			}
			System.out.println("> checkSquares : " + i);
			formula = BooleanFormula.toCnf(formula);
		}

		return formula;
	}

	public static int getSquareNumber(int rowIndex, int columnIndex, int gridSize) {
		int squareSize = (int) Math.sqrt(gridSize);
		int squareRow = (rowIndex - 1) / squareSize;
		int squareColumn = (columnIndex - 1) / squareSize;
		return (squareRow * squareSize) + squareColumn + 1;
	}

	// TODO
	public static void checkCellsValue(int gridSize, BooleanFormula formula) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				for (int j = 1; j <= gridSize; j++) {
					int a = i / 10;
					int c = i % 10;

					if (j != c) {
						String stri = String.valueOf(i);
						StringBuilder str = new StringBuilder().append(a).append(j);
						formula = new And(formula, new Implies(new PropositionalVariable(stri),
								new Not(new PropositionalVariable(str.toString()))));
						System.out.println(stri + " " + str);
					}
				}

			}
		}
	}

}
