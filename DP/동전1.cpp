#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, k;
vector<vector<int>> dp(2, vector<int>(10001, 0));
vector<int> coin;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> k;
	coin.push_back(0);
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		coin.push_back(a);
	}

	sort(coin.begin(), coin.end());

	dp[0][0] = 1;
	dp[1][0] = 1;

	for (int i = 1; i <= n; i++) {
		int value = coin[i];
		int index=0;
		int index2 = 1;
		for (int j = 1; j <= k; j++) {
			if (i % 2 == 0) {
				index = 0;
				index2 = 1;
			}
			else {
				index = 1;
				index2 = 0;
			}

			if (j - value < 0)
				dp[index][j] = dp[index2][j];
			else 
				dp[index][j] = dp[index2][j] + dp[index][j - value];
 		}
	}

	//for (int i = 0; i < n + 1; i++) {
	//	cout << i << " : ";
	//	for (int j = 0; j < k + 1; j++) {
	//		cout << dp[i][j] << " ";
	//	}
	//	cout << '\n';
	//}
	int max;
	max = dp[0][k] > dp[1][k] ? dp[0][k] : dp[1][k];
	cout << max;

}
