import stev.booleans.*;
import stev.booleans.BooleanFormula;
import stev.booleans.PropositionalVariable;

public class Constraints {

	
	/**
	 * Cette fonction va permettre d'ajouter a la booleanFormula en entrée toutes les conditions sur les lignes
	 * 
	 * Si la valeur de la ligne i et colomn j est k alors, k ne doit pas apparaitre sur la même ligne
	 * 
	 * Exemple :
	 * i,j,k sous la forme 111
	 * 111 -> !121
	 * 111 -> !131
	 * 111 -> !141
	 * .
	 * .
	 * .
	 * 111 -> !191
	 * 112 -> !122
	 * 112 -> !132
	 * .
	 * .
	 * .
	 * 999 -> !989
	 * 
	 * @param gridSize : un entier qui represente la taille de la grille
	 * @param formula : une booleanFormula à laquel nous allons ajouter des conditions
	 * @return la booleanFormula complété avec toutes les conditions
	 */
	public static BooleanFormula checkRows(int gridSize, BooleanFormula formula) {
		//ProgressBar
		int counter = 0;
		ProgressBar progressBar = new ProgressBar("Contraintes sur les lignes");

		//Pour toutes les valeurs de i,j,k 
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int rowIndex = i / 100;
				int columnIndex = (i / 10) % 10;
				int value = i % 10;

				//test de toutes les valeurs de ligne
				for (int j = 1; j <= gridSize; j++) {
					//Enlever le cas de la cellule que l'on teste
					if (j != columnIndex) {
						String stri = String.valueOf(i);
						StringBuilder str = new StringBuilder().append(rowIndex).append(j).append(value);
						formula = new And(formula, new Implies(new PropositionalVariable(stri),
								new Not(new PropositionalVariable(str.toString()))));
					}
				}
			}
			if (i % (111 * gridSize / 23) == 0) {
				progressBar.showProgress(counter++);
			}
			formula = BooleanFormula.toCnf(formula);
		}

		System.out.println("\r1/4 - [ OK ] Contraintes sur les lignes");
		return formula;
	}

	
	/**
	 * Cette fonction va permettre d'ajouter a la booleanFormula en entrée toutes les conditions sur les colonnes
	 * 
	 * Si la valeur de la ligne i et colomn j est k alors, k ne doit pas apparaitre sur la même colonne
	 * 
	 * Exemple :
	 * i,j,k sous la forme 111
	 * 111 -> !211
	 * 111 -> !311
	 * 111 -> !411
	 * .
	 * .
	 * .
	 * 111 -> !911
	 * 112 -> !212
	 * 112 -> !312
	 * .
	 * .
	 * .
	 * 999 -> !899
	 * 
	 * @param gridSize : un entier qui represente la taille de la grille
	 * @param formula : une booleanFormula à laquel nous allons ajouter des conditions
	 * @return la booleanFormula complété avec toutes les conditions
	 */
	public static BooleanFormula checkColumns(int gridSize, BooleanFormula formula) {
		//ProgressBar
		int counter = 0;
		ProgressBar progressBar = new ProgressBar("Contraintes sur les colonnes");
		
		//Pour toutes les valeurs de i,j,k 
		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				int rowIndex = i / 100;
				int columnIndex = (i / 10) % 10;
				int value = i % 10;

				//test de toutes les valeurs de colonne
				for (int j = 1; j <= gridSize; j++) {
					//Enlever le cas de la cellule que l'on teste
					if (j != rowIndex) {
						String stri = String.valueOf(i);
						StringBuilder str = new StringBuilder().append(j).append(columnIndex).append(value);
						formula = new And(formula, new Implies(new PropositionalVariable(stri),
								new Not(new PropositionalVariable(str.toString()))));
					}
				}
			}
			if (i % (111 * gridSize / 23) == 0) {
				progressBar.showProgress(counter++);
			}
			formula = BooleanFormula.toCnf(formula);
		}
		
		System.out.println("\r2/4 - [ OK ] Contraintes sur les colonnes");
		return formula;
	}

	
	/**
	 * Cette fonction va permettre d'ajouter a la booleanFormula en entrée toutes les conditions sur les carrés
	 * 
	 * Si la valeur de la ligne i et colomn j est k alors, k ne doit pas apparaitre sur le même carré
	 * 
	 * Exemple :
	 * i,j,k sous la forme 111
	 * 111 -> !121
	 * 111 -> !131
	 * 111 -> !211	 
	 * 111 -> !221
	 * 111 -> !231
	 * 111 -> !311
	 * 111 -> !321
	 * 111 -> !331
	 * 112 -> !122
	 * 112 -> !132
	 * .
	 * .
	 * .
	 * 999 -> !989
	 * 
	 * @param gridSize : un entier qui represente la taille de la grille
	 * @param formula : une booleanFormula à laquel nous allons ajouter des conditions
	 * @return la booleanFormula complété avec toutes les conditions
	 */
	public static BooleanFormula checkSquares(int gridSize, BooleanFormula formula) {
		int counter = 0;
		ProgressBar progressBar = new ProgressBar("Contraintes sur les carrés");

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
			if (i % (111 * gridSize / 23) == 0) {
				progressBar.showProgress(counter++);
			}
			formula = BooleanFormula.toCnf(formula);
		}

		System.out.println("\r3/4 - [ OK ] Contraintes sur les carrées");
		return formula;
	}

	public static int getSquareNumber(int rowIndex, int columnIndex, int gridSize) {
		int squareSize = (int) Math.sqrt(gridSize);
		int squareRow = (rowIndex - 1) / squareSize;
		int squareColumn = (columnIndex - 1) / squareSize;
		return (squareRow * squareSize) + squareColumn + 1;
	}

	
	/**
	 * Cette fonction va permettre d'ajouter a la booleanFormula en entrée toutes les conditions sur les cellules
	 * 
	 * Si la valeur de la ligne i et colomn j est k alors, toutes les autres valeurs ne sont pas autorisés pour cette cellule
	 * 
	 * Exemple :
	 * i,j,k sous la forme 111
	 * 111 -> !112
	 * 111 -> !113
	 * 111 -> !114
	 * 111 -> !115
	 * 111 -> !116
	 * 111 -> !117
	 * 111 -> !118
	 * 111 -> !119
	 * 112 -> !111
	 * 112 -> !113
	 * .
	 * .
	 * .
	 * 999 -> !998
	 * 
	 * @param gridSize : un entier qui represente la taille de la grille
	 * @param formula : une booleanFormula à laquel nous allons ajouter des conditions
	 * @return la booleanFormula complété avec toutes les conditions
	 */
	public static BooleanFormula checkCellsValue(int gridSize, BooleanFormula formula) {
		int counter = 0;
		ProgressBar progressBar = new ProgressBar("Contraintes sur les valeurs");

		for (int i = 111; i <= (111 * gridSize); i++) {
			// Si on a 201 pas possible et si on a 210 pas possible
			if (((i / 10) % 10) != 0 && ((i % 10) != 0) && (i % 10) <= gridSize && ((i / 10) % 10 <= gridSize)) {
				for (int j = 1; j <= gridSize; j++) {
					int indexes = i / 10;
					int value = i % 10;

					if (j != value) {
						String stri = String.valueOf(i);
						StringBuilder str = new StringBuilder().append(indexes).append(j);
						formula = new And(formula, new Implies(new PropositionalVariable(stri),
								new Not(new PropositionalVariable(str.toString()))));
					}
				}
			}
			if (i % (111 * gridSize / 23) == 0) {
				progressBar.showProgress(counter++);
			}
			formula = BooleanFormula.toCnf(formula);
		}

		System.out.println("\r4/4 - [ OK ] Contraintes sur les valeurs");
		return formula;
	}

}
