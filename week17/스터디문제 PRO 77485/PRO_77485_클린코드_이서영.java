/**
 * 멀쩡한 사각형 클래스
 *
 * @author RWB
 * @since 2021.12.28 Tue 22:51:28
 */
class Solution
{
	/**
	 * 해답 반환 메서드
	 *
	 * @param rows: [int] 열
	 * @param columns: [int] 행
	 * @param queries: [int[][]] 회전 대상
	 *
	 * @return [int] 해답
	 */
	public int[] solution(int rows, int columns, int[][] queries)
	{
		int[] answer = new int[queries.length];
		
		int[][] board = new int[rows][columns];
		
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				board[i][j] = i * columns + j + 1;
			}
		}
		
		for (int i = 0; i < queries.length; i++)
		{
			int[] minPos = { queries[i][0] - 1, queries[i][1] - 1 };
			int[] maxPos = { queries[i][2] - 1, queries[i][3] - 1 };
			
			int start = board[minPos[0]][minPos[1]];
			
			int min = Integer.MAX_VALUE;
			
			// 좌측 라인 회전
			for (int j = minPos[0]; j < maxPos[0]; j++)
			{
				min = Math.min(min, board[j][minPos[1]]);
				
				board[j][minPos[1]] = board[j + 1][minPos[1]];
			}
			
			// 하단 라인 회전
			for (int j = minPos[1]; j < maxPos[1]; j++)
			{
				min = Math.min(min, board[maxPos[0]][j]);
				
				board[maxPos[0]][j] = board[maxPos[0]][j + 1];
			}
			
			// 우측 라인 회전
			for (int j = maxPos[0]; j > minPos[0]; j--)
			{
				min = Math.min(min, board[j][maxPos[1]]);
				
				board[j][maxPos[1]] = board[j - 1][maxPos[1]];
			}
			
			// 윗 라인 회전
			for (int j = maxPos[1]; j > minPos[1]; j--)
			{
				min = Math.min(min, board[minPos[0]][j]);
				
				board[minPos[0]][j] = board[minPos[0]][j - 1];
			}
			
			board[minPos[0]][minPos[1] + 1] = start;
			
			answer[i] = min;
		}
		
		return answer;
	}
}