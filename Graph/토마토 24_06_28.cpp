/*
BFS로 확장시키기.
1. 동일한 지역일 경우, 더 작은 숫자로 갱신 or 숫자가 있을 경우 그냥 무시
2. 
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int M, N; // M = 가로
vector<vector<int>> map;
queue<pair<int, int>> q;
int dy[4] = { 0, -1, 0, 1 };
int dx[4] = { 1, 0, -1, 0 };

void bfs() {
	while (!q.empty()) {
		int y, x, ny, nx;
		y = q.front().first;
		x = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];

			if (ny < N && ny >= 0 && nx < M && nx >= 0) {
				if (map[ny][nx] == 0) {
					map[ny][nx] = map[y][x] + 1;
					q.push({ ny,nx });
				}
			}
		}
		
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> M >> N;
	map.resize(N, vector<int>(M, 0));

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			int a;
			cin >> a;
			map[i][j] = a;
			if (a == 1) {
				q.push({ i,j });
			}
		}
	}

	bfs();

	int max = 0;
	bool isZero = false;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] >= 0 && max < map[i][j])
				max = map[i][j];
			if (map[i][j] == 0) {
				isZero = true;
				break;
			}
		}
		if (isZero)
			break;
	}

	if (isZero) {
		cout << -1 << endl;
	}

	else {
		cout << max - 1;
	}

	//for (int i = 0; i < N; i++) {
	//	for (int j = 0; j < M; j++) {
	//		cout << map[i][j] << " ";
	//	}
	//	cout << '\n';
	//}
}