import stev.booleans.And;
import stev.booleans.BooleanFormula;
import stev.booleans.Or;
import stev.booleans.PropositionalVariable;

public class ConvertGridFormula {

	public static BooleanFormula convertGridToCNF(String grid) {
		BooleanFormula formula = null;
		BooleanFormula tFormula = null;
		int gridSize = (int) Math.sqrt(grid.length());

		for (int i = 0; i < grid.length(); i++) {
			char c = grid.charAt(i);
			final int row = i / gridSize + 1;
			final int column = (i - (gridSize * (row - 1))) + 1;

			if (c != '#') {
				String val = row + String.valueOf(column) + c;
				PropositionalVariable var = new PropositionalVariable(val);
				if (formula == null) {
					if (tFormula == null) {
						tFormula = var;
					} else {
						formula = new And(tFormula, var);
					}
				} else {
					formula = new And(formula, var);
				}
			} else {
				BooleanFormula sFormula = null;
				PropositionalVariable svar = null;
				
				for (int j = 1; j <= gridSize; j++) {
					final String val = row + String.valueOf(column) + j;
					final PropositionalVariable var = new PropositionalVariable(val);
					if (sFormula == null) {
						if (svar == null) {
							svar = var;
						} else {
							sFormula = new Or(svar, var);
						}
					} else {
						sFormula = new Or(sFormula, var);
					}
				}
				
				if (formula == null) {
					if (tFormula == null) {
						tFormula = sFormula;
					} else {
						formula = new And(tFormula, sFormula);
					}
				} else {
					formula = new And(formula, sFormula);
				}
			}
		}

		return BooleanFormula.toCnf(formula);
	}

}
