package escapeMaze;

import java.util.LinkedList;

public class Solution {

  // Input data: matrix point '1' designates a wall and a point '0' designates free space to move.
  public int[][] matrix;
  public int[][] moves = {
    {-1, 0}, // up
    {1, 0}, // down
    {0, -1}, // left
    {0, 1} // right
  };

  /*
  By the problem design on binarysearch.com, we have to work
  around the given method 'public int solve(int[][] matrix)' so that the code
  can be run on the website. Even though the name 'solve' does not make
  a lot of sense, it is left as it is, so that the code can be run directly
  on the website, without any modifications.
  */
  public int solve(int[][] matrix) {

    // Start point or exit point is a wall('1').
    if (matrix[0][0] == 1 || matrix[matrix.length - 1][matrix[0].length - 1] == 1) {
      return -1;
    }
    this.matrix = matrix;
    return bfs_findMinNumberOfMoves_toEscapeMaze();
  }

  /*
   Breadth First Search: find minumum number of moves to escape the maze,
                         starting from top left and exiting bottom right.

   @return Minimum number of moves, as described above, to escape the maze.
           If it is not possible, the method returns '-1'.
  */
  public int bfs_findMinNumberOfMoves_toEscapeMaze() {
    LinkedList<Point> queue = new LinkedList<Point>();
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    Point start = new Point(0, 0);
    start.distanceFromStart = 1;
    queue.add(start);
    visited[0][0] = true;

    while (!queue.isEmpty()) {

      Point current = queue.removeFirst();
      int row = current.row;
      int column = current.column;

      if (row == matrix.length - 1 && column == matrix[0].length - 1) {
        return current.distanceFromStart;
      }

      for (int i = 0; i < moves.length; i++) {
        int new_r = row + moves[i][0];
        int new_c = column + moves[i][1];

        if (isInMatrix(new_r, new_c) && !visited[new_r][new_c] && matrix[new_r][new_c] == 0) {
          Point p = new Point(new_r, new_c);
          p.distanceFromStart = current.distanceFromStart + 1;
          queue.add(p);
          visited[new_r][new_c] = true;
        }
      }
    }
    return -1;
  }

  public boolean isInMatrix(int row, int column) {
    if (row < 0 || column < 0 || row > matrix.length - 1 || column > matrix[0].length - 1) {
      return false;
    }
    return true;
  }
}

class Point {
  int row;
  int column;
  int distanceFromStart;

  public Point(int row, int column) {
    this.row = row;
    this.column = column;
  }
}
