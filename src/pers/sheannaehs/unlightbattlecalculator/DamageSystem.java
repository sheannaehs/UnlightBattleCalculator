package pers.sheannaehs.unlightbattlecalculator;

public class DamageSystem {

    private static final double HEAD_PROB = (1.0 / 3);
    private static final int MAX_DICE_AMOUNT = 99;

    private static double[] generateProbArray(int diceAmount) {
        double[] probArray = new double[diceAmount + 1];
        probArray[diceAmount] = Math.pow(HEAD_PROB, diceAmount);

        for (int ct = diceAmount - 1; ct >= 0; ct--) {
            probArray[ct] = probArray[ct + 1] * 2 * (ct + 1) / (diceAmount - ct);
        }

        return probArray;
    }

    private static double probability(int atk, int def, int dmg, double[] atkProbArray, double[] defProbArray) {
        if (dmg < 0) {
            return 1 - probability(def, atk, 1 - dmg, defProbArray, atkProbArray);
        }

        if (atkProbArray.length != atk + 1) {
            return -1.0;
        }
        if (defProbArray.length != def + 1) {
            return -1.0;
        }

        double pbb = 0.0;
        for (int ct_a = dmg; ct_a <= atk; ct_a++) {
            for (int ct_d = 0; ct_d <= ct_a - dmg && ct_d <= def; ct_d++) {
                pbb += atkProbArray[ct_a] * defProbArray[ct_d];
            }
        }
        return pbb;
    }

    public static double probability(int atk, int def, int dmg) {
        double[] atkProbArray = generateProbArray(atk);
        double[] defProbArray = generateProbArray(def);

        return probability(atk, def, dmg, atkProbArray, defProbArray);
    }

    public static int damage(int atk, int def, double pbb) {
        double[] atkProbArray = generateProbArray(atk);
        double[] defProbArray = generateProbArray(def);

        for (int ct = (int) (pbb > 0.5 ? Math.round((atk - def) * HEAD_PROB + 1) : atk); ct > (-1 * def); ct--) {
            if (pbb <= probability(atk, def, ct, atkProbArray, defProbArray)) {
                return ct;
            }
        }
        return 1 - def;
    }

    public static int attack(int def, int dmg, double pbb) {
        double[] atkProbArray;
        double[] defProbArray = generateProbArray(def);

        for (int ct = 1; ct <= MAX_DICE_AMOUNT; ct++) {
            atkProbArray = generateProbArray(ct);
            if (pbb <= probability(ct, def, dmg, atkProbArray, defProbArray)) {
                return ct;
            }
        }

        return MAX_DICE_AMOUNT;
    }

    public static int defence(int atk, int dmg, double pbb) {
        double[] atkProbArray = generateProbArray(atk);
        double[] defProbArray;

        for (int ct = MAX_DICE_AMOUNT; ct >= 0; ct--) {
            defProbArray = generateProbArray(ct);
            if (pbb <= probability(atk, ct, dmg, atkProbArray, defProbArray)) {
                return ct;
            }
        }

        return 0;
    }
}
