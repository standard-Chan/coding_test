#include <iostream>
#include <vector>
using namespace std;
int N;
int apple_n, direction_n;
vector<pair<int, int>> apple; // {y, x}
vector<pair<int, int>> turn; // 0 = ¿ÞÂÊ, 1 = right
vector<pair<int, int>> route;
int y=1, x=1, length=1, cnt=0, d=0; // 0 1 2 3 µ¿ ³² ¼­ ºÏ

void print() {
	cout << cnt << " : " << length << " : ";
	for (auto i : route) {
		cout << "(" << i.first << ", " << i.second << ")" << "  ";
	}
	cout << endl;
}

bool collide() {
	if (cnt == 0)
		return false;
	int e = route.size()-1;
	if (y <= 0 || x <= 0 || y > N || x > N) {
		return true;
	}
	for (int i = 1; i <= length; i++) {
		if (e - i < 0)
			break;
		int tail_y, tail_x;
		tail_y = route[e - i].first;
		tail_x = route[e - i].second;
		if (y == tail_y && x == tail_x) {
			return true;
		}
	}
	return false;
}

void move(int* y, int* x) {
	if (d == 0) {
		*x = *x + 1;
	}
	else if (d == 1) {
		*y = *y + 1;
	
	}
	else if (d == 2) {
		*x = *x - 1;
	}
	else if (d == 3) {
		*y = *y - 1;
	}
}

int start() {
	route.push_back({ y, x });
	while (true) {
		if (collide()) {
			return cnt;
		}
		//print();
		move(&y, &x);
		cnt++;
		route.push_back({ y, x });

		for (int i = 0; i < apple.size(); i++) {
			int appleY, appleX;
			appleY = apple[i].first;
			appleX = apple[i].second;

			if (y == appleY && x == appleX) {
				length++;
				apple.erase(apple.begin() + i);
				break;
			}
		}

		for (int i = 0; i < turn.size(); i++) {
			if (cnt == turn[i].first) {
				if (turn[i].second == 1) {
					if (d == 3)
						d = 0;
					else
						d++;
				}
				else if (turn[i].second == 0) {
					if (d == 0)
						d = 3;
					else
						d--;
				}
			}
		}

	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> N;

	cin >> apple_n;
	for (int i = 0; i < apple_n; i++) {
		int a, b;
		cin >> a >> b;
		apple.push_back({ a,b });
	}

	cin >> direction_n;
	for (int i = 0; i < direction_n; i++) {
		int a;
		char d;
		cin >> a >> d;
		if (d == 'L') {
			turn.push_back({ a, 0 });
		}
		else if (d == 'D') {
			turn.push_back({ a, 1 });
		}
	}

	cout << start();
}