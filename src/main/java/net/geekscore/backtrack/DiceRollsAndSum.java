package net.geekscore.backtrack;

import java.util.*;
import java.util.stream.Collectors;

public class DiceRollsAndSum {

    public static void main(String[] args) {
        diceRolls(3, Arrays.stream(new int[]{1,2,3,4,5,6}).boxed().collect(Collectors.toSet()));
        diceRollsSum(2, Arrays.stream(new int[]{1,2,3,4,5,6}).boxed().collect(Collectors.toSet()), 6 );
        diceRollsSum(3, Arrays.stream(new int[]{1,2,3,4,5,6}).boxed().collect(Collectors.toSet()), 9 );
    }

    private static void diceRolls(int dices, Set<Integer> valuesOnDice) {
        diceRollsHelper(dices, valuesOnDice, new LinkedList<>());
    }

    private static void diceRollsSum(int dices, Set<Integer> valuesOnDice, int sum) {
        diceRollSumHelper(dices, valuesOnDice, new LinkedList<>(), sum);
    }


    private static void diceRollsHelper(int dices, Set<Integer> valuesOnDice, List<Integer> chosenSoFar) {
        if(dices == 0) {
            System.out.println(chosenSoFar);
        } else {
            for (Integer valueOnDice: valuesOnDice) {
                chosenSoFar.add(valueOnDice);
                diceRollsHelper(dices  - 1, valuesOnDice, chosenSoFar);
                chosenSoFar.remove(chosenSoFar.size() - 1);
            }
        }
    }

    private static void diceRollSumHelper(int dices, Set<Integer> valuesOnDice, List<Integer> chosenSoFar, int sumSoFar) {
        System.out.println("diceRollSumHelper("+dices+","+valuesOnDice+","+chosenSoFar+","+sumSoFar+")");
        if(sumSoFar == 0) {
            System.out.println(chosenSoFar);
        } else {
            for (Integer valueOnDice: valuesOnDice) {
                if(valueOnDice <= sumSoFar && dices >= 1) {
                    /**
                     * The value on current dice <= sum to achieve and
                     *  We have more dices to rolls
                     */
                    chosenSoFar.add(valueOnDice);
                    diceRollSumHelper(dices  - 1, valuesOnDice, chosenSoFar, sumSoFar - valueOnDice);
                    chosenSoFar.remove(chosenSoFar.size() - 1);
                }

            }
        }
    }
}
