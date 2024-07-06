#include<iostream>
#include<queue>
#include<vector>
using namespace std;

int M, N; // 세로 가로
vector<vector<int>> map;
vector<vector<int>> route;
priority_queue<pair<int, pair<int, int>>> pq;

int dy[] = { 0,-1,0,1 };
int dx[] = { 1,0,-1,0 };


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> M >> N;

	map.resize(M, vector<int>(N, 0));
	route.resize(M, vector<int>(N, 0));

	for (int y = 0; y < M; y++) {
		for (int x = 0; x < N; x++) {
			int a;
			cin >> a;
			map[y][x] = a;
			pq.push({ -a, {y, x} });
		}
	}

	int end_v = -map[M - 1][N - 1];
	while (pq.top().first > end_v) {
		pq.pop();
	}

	route[M - 1][N - 1] = 1;

	while (!pq.empty()) {
		int y, x, value;
		value = -pq.top().first;
		y = pq.top().second.first;
		x = pq.top().second.second;
		pq.pop();

		for (int i = 0; i < 4; i++) {
			int ny, nx;
			ny = y + dy[i];
			nx = x + dx[i];
			if (ny >= 0 && ny < M && nx >= 0 && nx < N) {
				if (map[ny][nx] > map[y][x]) {
					route[ny][nx] += route[y][x];
				}
			}
		}
		if (y == 0 && x == 0)
			break;
	}

	cout << route[0][0] << endl;
}