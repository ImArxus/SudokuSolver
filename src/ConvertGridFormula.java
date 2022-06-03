import stev.booleans.And;
import stev.booleans.BooleanFormula;
import stev.booleans.Or;
import stev.booleans.PropositionalVariable;

import java.util.function.BooleanSupplier;

public class ConvertGridFormula {

    public static BooleanFormula covertGridFormula(String grid){
        BooleanFormula f = null;
        BooleanFormula tf = null;
        int gridSize = (int) Math.sqrt(grid.length());
        for(int i =0;i<grid.length();i++){
            char c = grid.charAt(i);
            final int row = i / gridSize + 1;
            final int column = (i - (gridSize * (row - 1))) + 1;
            if(c!='#'){
                 String val = row + String.valueOf(column) + c;
                 PropositionalVariable var = new PropositionalVariable(val);
                if (f == null) {
                    if (tf == null) {
                        tf = var;
                    } else {
                        f = new And(tf, var);
                    }
                } else {
                    f = new And(f, var);
                }
            }else {
                BooleanFormula sf = null;
                PropositionalVariable svar = null;
                for (int j = 1; j <= gridSize; j++) {
                    final String val = row + String.valueOf(column) + j;
                    final PropositionalVariable var = new PropositionalVariable(val);
                    if (sf == null) {
                        if (svar == null) {
                            svar = var;
                        } else {
                            sf = new Or(svar, var);
                        }
                    } else {
                        sf = new Or(sf, var);
                    }
                }
                if (f == null) {
                    if (tf == null) {
                        tf = sf;
                    } else {
                        f = new And(tf, sf);
                    }
                } else {
                    f = new And(f, sf);
                }
            }
        }
        return BooleanFormula.toCnf(f);
    }
}
