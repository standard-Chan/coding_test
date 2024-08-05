#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int>> tri(500, vector<int>(500, 0));
vector<vector<int>> dp(500, vector<int>(500, 0));
int n;

int main() {
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n;

	for (int i = 1; i <= n; i++) {
		for (int j = 0; j < i; j++) {
			int a;
			cin >> a;
			tri[i - 1][j] = a;
		}
	}
	dp[0][0] = tri[0][0];

	for (int i = 1; i < n; i++) {
		for (int j = 0; j <= i; j++) {
			int cur = tri[i][j];
			if (j == 0) {
				dp[i][j] = dp[i - 1][j] + cur;
			}
			else if (j < i) {
				dp[i][j] = max(dp[i - 1][j-1], dp[i - 1][j])+cur;
			}
			else if (j == i) {
				dp[i][j] = dp[i - 1][j-1] + cur;
			}
		}
	}

	int result = 0;
	for (int i = 0; i < n; i++) {
		result = max(result, dp[n-1][i]);
	}
	cout << result;
}