import stev.booleans.BooleanFormula;
import stev.booleans.And;

/**
 * 
 * @author LE BALANGER Alexandre - LEBA20129906
 * @author DOLLO Vincent - DOLV26029901
 * @author MARTINEZ Eloy - MARE12089900
 * @author KHODJA Meziane - KHOM26099900
 *
 */

public class Main {

	public static void main(String[] args) {
		try {
			String sudoku = args[0];
			// Sudoku résalisable =
			// ##3#2#6##9##3#5##1##18#64####81#29##7#######8##67#82####26#95##8##2#3##9##5#1#3##

			// Sudoku non-réalisable =
			// 1#3#2#6##9##3#5##1##18#64####81#29##7####6##8##67#82####26#95##8##2#3##94#5#1#3##

			System.out.println("On cherche si le sudoku suivant est réalisable :\n" + sudoku);

			int gridSize = (int) Math.sqrt(sudoku.length());
			BooleanFormula formula = ConvertGridFormula.convertGridToCNF(sudoku);

			long start = System.currentTimeMillis();
			BooleanFormula formulaForRows = Constraints.checkRows(gridSize, formula);
			BooleanFormula formulaForColumns = Constraints.checkColumns(gridSize, formula);
			BooleanFormula formulaForSquares = Constraints.checkSquares(gridSize, formula);
			BooleanFormula formulaForValues = Constraints.checkCellsValue(gridSize, formula);
			formula = BooleanFormula.toCnf(new And(formula, formulaForRows, formulaForColumns, formulaForSquares, formulaForValues));
			long end = System.currentTimeMillis();
			
			System.out.println(formula);
			System.out.println("Durée d'exécution : " + (end - start) / 1000 + "s");

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
