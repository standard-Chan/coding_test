#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
bool check(int, int, int);

// 동 남 서 북 : 1 2 3 0
int N, M; // N 세로 , M 가로
int startX, startY, startD;
int cnt=0;
vector<vector<int>> map;
int dy[] = { 0, -1, 0, 1 };
int dx[] = { 1, 0, -1, 0 };

bool check(int y, int x, int dir) {
	if (dir == 1)
		x = x + 1;
	else if (dir == 2)
		y = y + 1;
	else if (dir == 3)
		x = x - 1;
	else if (dir == 0)
		y = y - 1;

	if (map[y][x] == 0)
		return true;
	return false;
}

void move(int* y, int* x, int* d) {
	int dir = *d;
	if (dir == 1)
		*x = *x + 1;
	else if (dir == 2)
		*y = *y + 1;
	else if (dir == 3)
		*x = *x - 1;
	else if (dir == 0)
		*y = *y - 1;
	return;
}

void move_back(int *y, int* x, int* d) {
	int dir = *d;
	if (dir == 1)
		*x = *x - 1;
	else if (dir == 2)
		*y = *y - 1;
	else if (dir == 3)
		*x = *x + 1;
	else if (dir == 0)
		*y = *y + 1;
	return;
}

void work() {
	int x, y, d;
	x = startX;
	y = startY;
	d = startD;
	while (true) {
		if (y < 0 && y >= N && x < 0 && x >= M) {
			break;
		}
		if (map[y][x] == 1)
			break;

		bool flag = false;
		//clean
		if (map[y][x] == 0) {
			cnt++;
			map[y][x] = -1;
		}

		// 빈칸 확인
		for (int i = 0; i < 4; i++) {
			int ny, nx;
			ny = y + dy[i];
			nx = x + dx[i];
			if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
				if (map[ny][nx] == 0) {
					flag = true;
					break;
				}
			}
		}

		// 빈칸 존재
		if (flag) {
			while (true) {
				if (d == 0)
					d = 3;
				else
					d -= 1;

				if (check(y, x, d)) {
					move(&y, &x, &d);
					break;
				}
			}
		}
		// 빈칸 x
		else {
			move_back(&y, &x, &d);
		}
	}
}

void print() {
	for (auto i : map) {
		for (auto j : i)
			cout << j << " ";
		cout << endl;
	}
}
void input() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			int a;
			cin >> a;
			if (a == 1)
				map[i][j]=1;
		}
	}
}

int main() {
	cin >> N >> M;
	cin >> startY >> startX >> startD;

	map.resize(N, vector<int>(M, 0));
	input();

	work();
	cout << cnt;
}
