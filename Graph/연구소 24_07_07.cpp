#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

const int r = 3;

int N, M;
int empty_cnt = 0;
int dy[] = { 0, -1, 0, 1 };
int dx[] = { 1, 0, -1, 0 };

queue<pair<int, int>> q;
vector<vector<int>> origin_map;
vector<vector<int>> map;
vector<pair<int,int>> virus;
vector<pair<int, int>> space; // 0인 지점

vector<bool> c; // 조합용
vector<vector<int>> c_list; // 조합 저장

void combination(int i, int depth) {
	if (depth == r) {
		vector<int> tmp(0);
		for (int a = 0; a < c.size(); a++) {
			if (c[a])
				tmp.push_back(a);
		}
		c_list.push_back(tmp);
		return;
	}

	for (int j = i; j < space.size(); j++) {
		if (!c[j]) {
			c[j] = true;
			combination(j + 1, depth+1);
			c[j] = false;
		}
	}
}

void bfs(int y, int x) {
	int y1, x1;
	while (!q.empty()) {
		y1 = q.front().first;
		x1 = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int ny, nx;
			ny = y1 + dy[i];
			nx = x1 + dx[i];

			if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
				if (map[ny][nx] == 0) {
					map[ny][nx] = 2;
					q.push({ ny, nx });
				}
			}
		}
	}



}

int count_space() {
	int cnt = 0;
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < M; x++) {
			if (map[y][x] == 0)
				cnt++;
		}
	}
	return cnt;
}

void cpy_map() {
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < M; x++) {
			map[y][x] = origin_map[y][x];
		}
	}
}

void print() {
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < M; x++) {
			cout << map[y][x] << "";
		}
		cout << '\n';
	}
}

int main() {
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> N >> M;
	origin_map.resize(N, vector<int>(M, 0));
	map.resize(N, vector<int>(M, 0));

	for (int y = 0; y < N; y++) {
		for (int x = 0; x < M; x++) {
			int a;
			cin >> a;
			if (a == 2) {
				virus.push_back({ y, x });
			}
			if (a == 0) {
				space.push_back({ y,x });
				empty_cnt++;
			}
			origin_map[y][x] = a;
		}
	}

	c.resize(space.size(), false);
	combination(0, 0);


	int result = 0;
	for (vector<int> select : c_list) {
		cpy_map();
		for (int i : select) {
			int y, x;
			y = space[i].first;
			x = space[i].second;

			map[y][x] = 1;
		}

		for (auto v : virus) {
			int vy, vx;
			vy = v.first;
			vx = v.second;
			q.push({ vy, vx });
			bfs(vy, vx);
		}
		int cnt = count_space();
		if (cnt > result) {
			result = cnt;
		}
	}

	cout << result;

}