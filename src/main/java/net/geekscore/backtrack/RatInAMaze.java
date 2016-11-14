package net.geekscore.backtrack;

import java.util.Arrays;

/**
 *
 * Given a maze, NxN matrix. A rat has to find a path from source to des­ti­na­tion.
 * maze[0][0] (left top corner)is the source and maze[N-1][N-1](right bot­tom cor­ner) is destination.
 * There are few cells which are blocked, means rat can­not enter into those cells.
 *
 * Rat can move in any direction ( left, right, up and down).
 *
 * Created by ravirajmulasa on 9/19/16.
 */
public final class RatInAMaze {

    private static final class Position {

        private final int row;

        private final int col;

        Position(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        public final int getRow() {
            return this.row;
        }

        public final int getCol() {
            return this.col;
        }
    }


    private static enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    private static final int[][] MAZE ={
        { 1, 0, 1, 1, 1 },
        { 1, 0, 1, 0, 1 },
        { 1, 0, 0, 1, 1 },
        { 1, 1, 1, 1, 0 },
        { 0, 1, 0, 1, 1 }
    };
    private static final Position SOURCE  = new Position(4,1);
    private static final Position TARGET  = new Position(4,4);


//    private static final int[][] MAZE ={
//            { 1, 0, 0, 0 },
//            { 1, 1, 0, 1 },
//            { 0, 1, 0, 0 },
//            { 1, 1, 1, 1 }
//    };
//    private static final Position SOURCE    = new Position(0,0);
//    private static final Position TARGET    = new Position(3,3);


    private static final int[][] PATH       = new int[MAZE.length][MAZE[0].length];


    public static void main(String[] args) {
        findPath(SOURCE, Direction.UP);
        for (int i = 0; i < PATH.length; i++) {
            System.out.println(Arrays.toString(PATH[i]));
        }

    }

    private static boolean findPath(final Position position, final Direction direction) {

        final int row = position.getRow();
        final int col = position.getCol();

        if(isTarget(position)) {
            PATH[row][col] = 1;
            return true;
        }

        if(isSafeToMove(position)) {

            PATH[row][col] = 1;


            if(Direction.RIGHT != direction && findPath(left(position),  Direction.LEFT)) {
                return true;
            }
            if(Direction.DOWN != direction && findPath(up(position), Direction.UP)) {
                return true;
            }
            if(Direction.LEFT != direction && findPath(right(position), Direction.RIGHT)) {
                return true;
            }
            if(Direction.UP != direction && findPath(down(position), Direction.DOWN)) {
                return true;
            }

            PATH[row][col] = 0;
            return false;
        }
        return false;
    }

    private static Position left(final Position position) {
        return new Position(position.getRow() - 1, position.getCol());
    }

    private static Position right(final Position position) {
        return new Position(position.getRow() + 1, position.getCol());
    }

    private static Position up(final Position position) {
        return new Position(position.getRow(), position.getCol() + 1);
    }

    private static Position down(final Position position) {
        return new Position(position.getRow(), position.getCol() - 1);
    }

    private static boolean isSafeToMove(final Position position) {

        final int row = position.getRow();
        final int col = position.getCol();
        return col < MAZE[0].length && col >= 0 && row < MAZE.length && row >= 0 && MAZE[row][col] == 1;
    }

    private static boolean isTarget(final Position position) {
        return equalPositions(position, TARGET);
    }

    private static boolean equalPositions(final Position position1, final Position position2) {
        return position1.getRow() == position2.getRow() && position1.getCol() == position2.getCol();
    }
}
