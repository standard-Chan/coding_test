#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int dz[] = {0, 0, 0, 0, 1, -1 };
int dy[] = { 0, 0, 1, -1, 0, 0 };
int dx[] = { 1, -1, 0, 0, 0, 0 };

int M, N, H; // 가로 세로 높이
vector<vector<vector<int>>> map;
vector <vector<vector<bool>>> visited;
queue<vector<int>> q; // 높이, 세로, 가로   z,y,x 순으로


void bfs() {
	while (!q.empty()) {
		int y, x, z;
		z = q.front()[0];
		y = q.front()[1];
		x = q.front()[2];
		q.pop();

		for (int i = 0; i < 6; i++) {
			int nz, ny, nx;
			nz = z + dz[i];
			ny = y + dy[i];
			nx = x + dx[i];

			if (nz >= 0 && ny >= 0 && nx >= 0 && nz < H && ny < N && nx < M) {
				if (!visited[nz][ny][nx] && map[nz][ny][nx] == 0) {
					q.push({ nz, ny, nx });
					visited[nz][ny][nx] = true;
					map[nz][ny][nx] = map[z][y][x] + 1;
				}
			}
		}
	}
}

int check() {
	int max = 0;
	for (int z = 0; z < H; z++) {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[z][y][x] == 0)
					return -1;
				if (max < map[z][y][x])
					max = map[z][y][x];
			}
		}
	}
	return max -1;
}

void p() {
	int max = 0;
	for (int z = 0; z < H; z++) {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				cout << map[z][y][x] << " ";
			}
			cout << '\n';
		}
		cout << "\n\n";
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> M >> N >> H;
	map.resize(H, vector<vector<int>>(N, vector<int>(M, 0)));
	visited.resize(H, vector<vector<bool>>(N, vector<bool>(M, false)));

	for (int h = 0; h < H; h++) {
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				int a;
				cin >> a;
				map[h][n][m] = a;
				if (a == 1) {
					visited[h][n][m] = true;
					q.push({ h,n,m });
				}
			}
		}
	}
	bfs();
		
	cout << check();



}

