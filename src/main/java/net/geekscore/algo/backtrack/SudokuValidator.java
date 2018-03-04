package net.geekscore.algo.backtrack;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the
 * character '.'.
 *
 * A partially filled sudoku which is valid.
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 *
 */
public class SudokuValidator {

    public static void main(String[] args) {

        final char[][] sudoku =  {
                {'.','8','7','6','5','4','3','2','1'}
                ,{'2','.','.','.','.','.','.','.','.'}
                ,{'3','.','.','.','.','.','.','.','.'}
                ,{'4','.','.','.','.','.','.','.','.'}
                ,{'5','.','.','.','.','.','.','.','.'}
                ,{'6','.','.','.','.','.','.','.','.'}
                ,{'7','.','.','.','.','.','.','.','.'}
                ,{'8','.','.','.','.','.','.','.','.'}
                ,{'9','.','.','.','.','.','.','.','.'}
        }; // true
        System.out.println(isValid(sudoku));


        final char[][] sudoku1 =  {
                 {'7','.','.','.','4','.','.','.','.'}
                ,{'.','.','.','8','6','5','.','.','.'}
                ,{'.','1','.','2','.','.','.','.','.'}
                ,{'.','.','.','.','.','9','.','.','.'}
                ,{'.','.','.','.','5','.','5','.','.'}
                ,{'.','.','.','.','.','.','.','.','.'}
                ,{'.','.','.','.','.','.','2','.','.'}
                ,{'.','.','.','.','.','.','.','.','.'}
                ,{'.','.','.','.','.','.','.','.','.'}
        }; // false
        System.out.println(isValid(sudoku1));


    }

    private static final boolean isValid(final char[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            Set<Character> rows = new HashSet<>();
            Set<Character> cols = new HashSet<>();
            Set<Character> cube = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j] != '.' && !rows.add(sudoku[i][j] )) return false;
                if(sudoku[j][i] != '.' && !cols.add(sudoku[j][i] )) return false;
                final int rowIdx = 3*(i/3);
                final int colIdx = 3*(i%3);
                if(sudoku[rowIdx+j/3][colIdx+j%3] != '.' && !cube.add(sudoku[rowIdx+j/3][colIdx+j%3])) return false;
            }
        }
        return true;
    }
}
