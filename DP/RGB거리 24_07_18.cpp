#include<iostream>
#include<vector>
using namespace std;
int N;
vector<vector<int>> dp(1001, vector<int>(3,0));
vector<vector<int>> cost(1001, vector<int>(3, 0));

int main() {
	cin >> N;

	for (int i = 1; i <= N; i++) {
		int a, b, c;
		cin >> a >> b >> c;

		cost[i][0] = a;
		cost[i][1] = b;
		cost[i][2] = c;
	}
	min(99, 100);

	dp[1][0] = cost[1][0];
	dp[1][1] = cost[1][1];
	dp[1][2] = cost[1][2];

	for (int i = 2; i <= N; i++) {
		int a, b, c;
		a = dp[i - 1][0];
		b = dp[i - 1][1];
		c = dp[i - 1][2];
		dp[i][0] = cost[i][0] + min(b,c);
		dp[i][1] = cost[i][1] + min(a,c);
		dp[i][2] = cost[i][2] + min(a,b);

	}
	int minv = 1e9;
	minv = min(dp[N][0], dp[N][1]);
	minv = min(minv, dp[N][2]);

	cout << minv;
}
