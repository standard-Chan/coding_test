#include <iostream>
#include <vector>
#include <algorithm>
#include <sstream>
#include <string>
using namespace std;

vector<vector<int>>map(9, vector<int>(9, 0));
vector<pair<int, int>> pos(0);



void print() {
	for (auto i : map) {
		for (auto j : i) {
			cout << j << " ";
		}
		cout << endl;
	}
}

bool promising(int y, int x, int num) {
	// °¡·Î
	for (int i = 0; i < 9; i++) {
		if (map[y][i] == num) {
			return false;
		}
	}
	// ¼¼·Î
	for (int i = 0; i < 9; i++) {
		if (map[i][x] == num) {
			return false;
		}
	}
	// 9Ä­
	int cy, cx;
	cy = y / 3;
	cx = x / 3;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			if (map[cy * 3 + i][cx * 3 + j] == num) {
				return false;
			}
		}
	}

	return true;
}

void backtracking(int depth) {
	if (depth == pos.size()) {
		print();
		exit(0);
	}

	int y, x;
	y = pos[depth].first;
	x = pos[depth].second;

	for (int j = 1; j <= 9; j++) {
		if (promising(y, x, j)) {
			map[y][x] = j;
			backtracking(depth + 1);
			map[y][x] = 0;
		}
	}

	return;
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	for (int i = 0; i < 9; i++) {
		stringstream stream;
		string str = "";
		getline(cin, str);
		stream.str(str);
		string s;
		int j = 0;
		while(stream >> s) {
			map[i][j] = stoi(s);
			j++;
		}
		stream.str("");
	}

	for (int y = 0; y < 9; y++) {
		for (int x = 0; x < 9; x++) {
			if (map[y][x] == 0)
				pos.push_back({ y, x });
		}
	}

	backtracking(0);
}