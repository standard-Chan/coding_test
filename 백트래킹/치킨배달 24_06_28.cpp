#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// 조합용 변수
vector<vector<int>> comb;
vector<bool> arr2(100, false);

int N, M;
int total=0; // 총 치킨집 개수

vector<int> value;
vector<vector<int>> map(50, vector<int>(50, 0));

vector<pair<int, int>> stores;
vector<pair<int, int>> houses;


// total  C   M
void c(int j, int depth) {
	if (depth == M) {
		vector<int> tmp;
		for (int i = 0; i < total; i++) {
			if (arr2[i]) {
				tmp.push_back(i);
			}
		}
		comb.push_back(tmp);
		return;
	}

	for (int i = j; i < total; i++) {
		if (!arr2[i]) {
			arr2[i] = true;
			c(i+1, depth + 1);
			arr2[i] = false;
		}
	}
}

// 치킨 거리
int cal_dist(int y1, int x1, int y2, int x2) {
	return abs(y1 - y2) + abs(x1 - x2);
}

// 집1 : 치킨집1,2,3,.. 사이의 거리중 가장 작은 값 구하기.
int min_dist(int h_y, int h_x, vector<int> combination) {
	int min=999;
	for (auto i : combination) {
		int d = cal_dist(h_y, h_x, stores[i].first, stores[i].second);
		if (min > d)
			min = d;
	}
	return min;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			int a;
			cin >> a;
			if (a == 1) {
				map[y][x] = 1;
				houses.push_back({ y,x });
			}
			else if (a == 2) {
				map[y][x] == 2;
				total++;
				stores.push_back({y, x});
			}
		}
	}

	c(0, 0);

	int result;
	for (int j = 0; j < comb.size(); j++) {
		result = 0;
		for (int i = 0; i < houses.size(); i++) {
			result += min_dist(houses[i].first, houses[i].second, comb[j]);
		}
		value.push_back(result);
	}

	int min_value = 1e9;
	for (auto i : value) {
		min_value = min_value < i ? min_value : i;
	}
	cout << min_value;

	

}
