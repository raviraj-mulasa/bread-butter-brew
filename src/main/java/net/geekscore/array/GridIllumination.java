package net.geekscore.array;

/**
 *
 * Given an NxN grid with an array of lamp coordinates.
 * Each lamp provides illumination to
 * every square on their x axis,
 * every square on their y axis, and e
 * very square that lies in their diagonal (think of a Queen in chess).
 *
 * Given an array of query coordinates, determine whether that point is illuminated or not.
 *
 * // TODO
 */
public class GridIllumination {

    public static void main(String[] args) {

        final Lamps lamps = new Lamps(5, new int[][]{
                {0, 0}
                ,{4, 4}
        });
        System.out.println(lamps.query(1, 1)); // true
        System.out.println(lamps.query(2, 1)); // false
        lamps.addLamp(new int[]{2,3});
        System.out.println(lamps.query(1, 2)); // true
        System.out.println(lamps.query(3, 1)); // false

    }
    private static class Lamps {

        private static  final int[][] NEIGHBORS = new int[][] {
                {0,0}
                ,{-1,0}
                ,{1,0}
                ,{0,-1}
                ,{0,1}
                ,{-1,1}
                ,{1,-1}
                ,{-1,-1}
                ,{1,1}
        };

        private int[] columns, rows, diagonalsLeft, diagonalsRight;


        private Lamps(int size, int[][] lamps) {
            this.columns = new int[size];
            this.rows = new int[size];
            this.diagonalsLeft = new int[2*(size-1)+1];
            this.diagonalsRight = new int[2*(size-1)+1];
            this.illuminate(lamps);
        }

        private void illuminate(int[][] lamps) {
            for (int[] lamp: lamps) {
                this.addLamp(lamp);
            }
        }

        private void addLamp(int[] lamp) {
            final int row = lamp[0];
            final int col = lamp[1];
            this.rows[row]++;
            this.columns[col]++;
            this.diagonalsLeft[row + col]++;
            this.diagonalsRight[row - col]++;
        }

        private void removeLamp(int[] lamp) {
            final int row = lamp[0];
            final int col = lamp[1];
            this.rows[row]--;
            this.columns[col]--;
            this.diagonalsLeft[row + col]--;
            this.diagonalsRight[row - col]--;
        }

        private boolean query(int x, int y) {

            for(final int[] neighbor: NEIGHBORS) {
                final int neighborX = x + neighbor[0];
                final int neighborY = y + neighbor[1];
                if (neighborX < 0 || neighborX >= this.rows.length || neighborY < -1 || neighborY >= this.columns.length) {
                    continue;
                } else {
                    this.removeLamp(new int[]{neighborX, neighborY});
                }
            }

            return this.rows[x] > 0 || this.columns[y] > 0 || this.diagonalsLeft[x + y]  > 0|| this.diagonalsRight[x - y] > 0;

        }
    }
}
