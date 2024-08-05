#include<iostream>
#include<vector>
using namespace std;

long long dp[101];
vector<long long> result;
//
int main() {
	int n,t;
	cin >> t;
	dp[0] = 0;
	dp[1] = 1;
	dp[2] = 1;
	dp[3] = 1;
	dp[4] = 2;
	dp[5] = 2;
	for (int i = 6; i < 101; i++) {
		dp[i] = dp[i - 1] + dp[i - 5];
	}
	for (int i = 0; i < t; i++) {
		cin >> n;
		result.push_back(dp[n]);
	}
	for (auto i : result) {
		cout << i << endl;
	}
}
