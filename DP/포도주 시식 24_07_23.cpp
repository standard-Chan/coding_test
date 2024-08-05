#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> ml(10001, 0);
vector<vector<int>> dp(10001, vector<int>(3, 0));
int n;


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);


	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		ml[i] = a;
	}

	dp[0][1] = ml[0];
	dp[0][2] = ml[0];
	dp[1][0] = ml[0] + ml[1];
	dp[1][1] = ml[1];
	dp[1][2] = ml[0] + ml[1];

	for (int i = 2; i < n; i++) {
		// 연속 2번 먹음
		dp[i][0] = dp[i - 1][1] + ml[i];
		// 연속 1번 먹음
		dp[i][1] = dp[i - 2][2] + ml[i];
		// max값
		dp[i][2] = max(dp[i - 1][2], max(dp[i][0], dp[i][1]));
	}

	int max = 0;
	for (int i = 0; i < n + 1; i++) {
		if (max < dp[i][2])
			max = dp[i][2];
	}
	cout << max;
}
