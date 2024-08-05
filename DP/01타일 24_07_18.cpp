#include<iostream>
#include<vector>
using namespace std;

const int MAX = 1000001;
int dp[MAX];

int main() {
	int n = 0;
	cin >> n;
	dp[0] = 1;
	dp[1] = 1;
	dp[2] = 2;
	for (int i = 3; i < MAX; i++) {
		dp[i] = (dp[i - 1] + dp[i - 2])%15746;
	}

	cout << dp[n];
}
