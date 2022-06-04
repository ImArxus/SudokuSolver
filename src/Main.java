import stev.booleans.*;

public class Main {

	public static void main(String[] args) {
		String sudoku = args[0];
		// Sudoku = ##3#2#6##9##3#5##1##18#64####81#29##7#######8##67#82####26#95##8##2#3##9##5#1#3##

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

		int gridSize = (int) Math.sqrt(sudoku.length());
		BooleanFormula formula = ConvertGridFormula.convertGridToCNF(sudoku);
		
		formula = Constraints.checkRow(gridSize, formula);
		formula = Constraints.checkColumn(gridSize, formula);
		//formula = Constraints.checkSquare(gridSize, formula);
		System.out.println(formula);

		try {
			System.out.println("On cherche si le sudoku suivant est réalisable :\n" + sudoku + "\n");

			if (SolverFactory.createSolver(formula).isSatisfiable()) {
				System.out.println("Le sudoku est réalisable");
			} else {
				System.out.println("Le sudoku n'est pas réalisable");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
