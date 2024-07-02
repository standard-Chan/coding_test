#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, K; // ¹°Ç° ¼ö, ÃÖ´ë ¹«°Ô
vector<pair<int, int>> wv;
vector<vector<int>> dp(101, vector<int>(100001, 0));

void backsack() {
	for (int y = 1; y <= N; y++) {
		for (int x = 0; x <= K; x++) {
			int w, v;
			w = wv[y].first;
			v = wv[y].second;

			if (y < 1 || x - w < 0) {
				dp[y][x] = dp[y - 1][x];
				continue;
			}

			if (dp[y - 1][x - w] + v < dp[y - 1][x])
				dp[y][x] = dp[y - 1][x];
			else
				dp[y][x] = dp[y - 1][x - w] + v;
		}
	}
}

int main() {
	cin >> N >> K;
	wv.push_back({ 0,0 });

	for (int i = 0; i < N; i++) {
		int W, V;
		cin >> W >> V;

		wv.push_back({ W, V });
	}

	sort(wv.begin(), wv.end());

	backsack();


	cout << dp[N][K];


}
