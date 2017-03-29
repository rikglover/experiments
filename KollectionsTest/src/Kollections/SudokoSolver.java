package Kollections;

public class SudokoSolver {



	public boolean isValidSudoku(char[][] board) {

		int[][] rowValues = new int[9][9];
		int[][] columnValues = new int[9][9];
		int[][] subBoardValues = new int[9][9];

        for(Integer i = 0; i < 9; i++) {
        	for(Integer j = 0; j < 9; j++) {
				char value = board[i][j];

				if(value == '.') {
					continue;
				} else if(value < '1' || value > '9') {
					return false;
				} else {
					int index = value - '0' - 1;
					int subBoardIndex = 3 * (i / 3) + j / 3;

					if(rowValues[i][index] != 0 || columnValues[index][j] != 0 || subBoardValues[subBoardIndex][index] != 0) {
					    return false;
					}

					rowValues[i][index] = 1;
					columnValues[index][j] = 1;
					subBoardValues[subBoardIndex][index] = 1;
				}
			}
		}

		return true;
	}
}
