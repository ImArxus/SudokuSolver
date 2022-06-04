import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;

import stev.booleans.*;

public class Main {

	public static void checkRow(int gridSize) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int a = i / 100;
				int b = i % 10; // le reste
				int c = (i / 10) % 10;
				for (int k = 1; k <= gridSize; k++) {
					if (k != c) {
						String stri = String.valueOf(i);
						String str = String.valueOf(a + "" + k + "" + b);
						// f = new And(f, new Implies(new PropositionalVariable(stri), new Not(new
						// PropositionalVariable(str))));
						System.out.println(stri + " " + str);
					}
				}

			}
		}
	}

	public static void checkColumn(int gridSize) {
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int a = i % 100;
				int b = i / 100;
				for (int k = 1; k <= gridSize; k++) {
					if (k != b) {
						String stri = String.valueOf(i);
						String str = String.valueOf(k + "" + a);
						// f = new And(f, new Implies(new PropositionalVariable(stri), new Not(new
						// PropositionalVariable(str))));
						System.out.println(stri + " " + str);
					}
				}

			}
		}
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

	public static void main(String[] args) {

		// checkColumn(3);
		checkRow(3);
		// checkCellsValue(3);

		String a = "##3#2#6##9##3#5##1##18#64####81#29##7#######8##67#82####26#95##8##2#3##9##5#1#3##";
		// test(a);
		/*
		 * Exemple de prédicats
		 * 
		 * 1) Si la la valeur de la colonne est i et de la ligne j est k alors k ne doit
		 * plus apparaitre dans la même ligne ((Xi11 => -(Xi21 || Xi31 || ...)) &&
		 * ((Xi12 => -(Xi22 || Xi32 || ...)) && ... & ((Xi21 => -(Xi11 || Xi31 || ...))
		 * ... 2) Si la la valeur de la colonne est i et de la ligne j est k alors k ne
		 * doit plus apparaitre dans la même colonne 3) Si la la valeur de la colonne
		 * est i et de la ligne j est k alors k ne doit plus apparaitre dans la même
		 * carré 4) Les cellules de colonne i et de ligne j doivent forcement contenir
		 * une valeur qui est comprise en 1 et 9 inclus.
		 */

		String s = "12#4";
		System.out.println(ConvertGridFormula.covertGridFormula(s));
//		try {
//			System.out.println("On cherche si le sudoku suivant est réalisable :\n" + args[0] + "\n");
//
//			BooleanFormula formula = null;
//			ISolver solver = SolverFactory.createSolver(BooleanFormula.toCnf(formula));
//
//			IProblem problem = solver;
//			if (problem.isSatisfiable()) {
//				System.out.println("Le sudoku est réalisable");
//			} else {
//				System.out.println("Le sudoku n'est pas réalisable");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
