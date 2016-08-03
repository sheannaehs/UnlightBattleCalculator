package pers.sheannaehs.unlightbattlecalculator;

import java.util.Scanner;

public class UnlightBattleCalculator {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String cmd;
        String[] vars;

        int atk, def, dmg;
        double pbb;

        while (true) {
            System.out.println("Choose which variable to calculate.");
            System.out.println("1 for atk, 2 for def, 3 for dmg, 4 for pbb : ");
            switch (scanner.nextInt()) {
                case 0:
                    System.exit(0);
                case 1:
                    System.out.println("Enter data ( def/dmg/pbb% ) : ");
                    scanner.nextLine();
                    cmd = scanner.nextLine();
                    vars = cmd.split("\\s+");
                    if(vars.length<3){
                        throw new Exception();
                    }
                    def = Integer.parseInt(vars[0]);
                    dmg = Integer.parseInt(vars[1]);
                    pbb = Double.parseDouble(vars[2]) / 100;
                    System.out.println("Need " + DamageSystem.attack(def, dmg, pbb) + " attacks");
                    break;
                case 2:
                    System.out.println("Enter data ( atk/dmg/pbb% ) : ");
                    scanner.nextLine();
                    cmd = scanner.nextLine();
                    vars = cmd.split("\\s+");
                    if(vars.length<3){
                        throw new Exception();
                    }
                    atk = Integer.parseInt(vars[0]);
                    dmg = Integer.parseInt(vars[1]);
                    pbb = Double.parseDouble(vars[2]) / 100;
                    System.out.println("Need " + DamageSystem.defence(atk, dmg, pbb) + " defences");
                    break;
                case 3:
                    System.out.println("Enter data ( atk/def/pbb% ) : ");
                    scanner.nextLine();
                    cmd = scanner.nextLine();
                    vars = cmd.split("\\s+");
                    if(vars.length<3){
                        throw new Exception();
                    }
                    atk = Integer.parseInt(vars[0]);
                    def = Integer.parseInt(vars[1]);
                    pbb = Double.parseDouble(vars[2]) / 100;
                    System.out.println("Cause " + DamageSystem.damage(atk, def, pbb) + " damages");
                    break;
                case 4:
                    System.out.println("Enter data ( atk/def/dmg ) : ");
                    scanner.nextLine();
                    cmd = scanner.nextLine();
                    vars = cmd.split("\\s+");
                    if(vars.length<3){
                        throw new Exception();
                    }
                    atk = Integer.parseInt(vars[0]);
                    def = Integer.parseInt(vars[1]);
                    dmg = Integer.parseInt(vars[2]);
                    System.out.println("Has " + DamageSystem.probability(atk, def, dmg) + " probability");
                    break;
                default:
                    System.out.println("Input wrong!");
            }
        }
    }

}
