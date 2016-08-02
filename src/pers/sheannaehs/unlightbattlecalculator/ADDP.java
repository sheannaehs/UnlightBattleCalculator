package pers.sheannaehs.unlightbattlecalculator;

public class ADDP {

    private static final double HEAD_PROB = (1.0 / 3);

    private static double[] atkProbArray, defProbArray;

    static {
        atkProbArray = generateProbArray(0);
        defProbArray = generateProbArray(0);
    }

    private static double[] generateProbArray(int diceAmount) {
        double[] probArray = new double[diceAmount + 1];
        probArray[diceAmount] = Math.pow(HEAD_PROB, diceAmount);

        for (int ct = diceAmount - 1; ct >= 0; ct--) {
            probArray[ct] = probArray[ct + 1] * 2 * (ct + 1) / (diceAmount - ct);
        }

        return probArray;
    }

    public static double probability(int atk, int def, int dmg) {
        if (atkProbArray.length != atk + 1) {
            atkProbArray = generateProbArray(atk);
        }
        if (defProbArray.length != def + 1) {
            defProbArray = generateProbArray(def);
        }
        if (dmg < 0) {
            return 1 - probability(def, atk, 1 - dmg);
        }

        double pbb = 0.0;
        for (int ct_a = dmg; ct_a <= atk; ct_a++) {
            for (int ct_d = 0; ct_d <= ct_a - dmg && ct_d <= def; ct_d++) {
                pbb += atkProbArray[ct_a] * defProbArray[ct_d];
            }
        }
        return pbb;
    }

    public static int damage(int atk, int def, Double pbb) {
        for (int ct = (int) (pbb > 0.5 ? Math.round((atk - def) * HEAD_PROB + 1) : atk); ct > (-1 * def); ct--) {
            if (pbb < probability(atk, def, ct)) {
                pbb = probability(atk, def, ct);
                return ct;
            }
        }
        pbb = probability(atk, def, 1 - def);
        return -1 * def + 1;
    }
}
