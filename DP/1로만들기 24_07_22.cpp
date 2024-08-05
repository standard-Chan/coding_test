#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> dp(1e6+1, 0);
int n;

int min(int a, int b, int c) {
	int minv = a;
	minv = min(minv, b);
	minv = min(minv, c);
	return minv;
}

int main() {
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n;
	dp[0] = 0;
	dp[1] = 1;
	dp[2] = 1;
	dp[3] = 1;

	for (int i = 4; i <= 1e6; i++) {
		if (i%6 == 0) {
			dp[i] = min(dp[i - 1], dp[i / 2], dp[i / 3]) + 1;
		}
		else if (i % 2 == 0) {
			dp[i] = min(dp[i - 1], dp[i / 2]) + 1;
		}
		else if (i % 3 == 0) {
			dp[i] = min(dp[i - 1], dp[i / 3]) + 1;
		}
		else {
			dp[i] = dp[i - 1] + 1;
		}
	}
	cout << dp[n];
}
