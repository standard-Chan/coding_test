#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 1e9;
int n, k;
vector<int> coin(0);
vector<int> dp(10001, INF);


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> k;

	for (int i = 0; i < n; i++) {
		bool isDuplicate = false;
		int a;
		cin >> a;
		for (auto j : coin) {
			if (a == j)
				isDuplicate = true;
		}
		if (isDuplicate)
			continue;
		coin.push_back(a);
	}

	sort(coin.begin(), coin.end());
	dp[0] = 0;
	for (int i = 0; i < coin.size(); i++) {
		int y = 0;
		int value = coin[i];
		
		for (int x = 0; x+value <= k; x++) {
			if (dp[x + value] > dp[x] + 1)
				dp[x + value] = dp[x] + 1;
		}
	}
	if (dp[k] == INF)
		cout << -1;
	else 
		cout << dp[k];


}