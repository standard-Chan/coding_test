#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const long long divisor = 1e9;
vector<vector<int>> dp (101, vector<int>(10, 0));

int n;
int main() {
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n;
	for (int i = 0; i < 10; i++) {
		dp[1][i] = 1;
 	}

	for (int len = 2; len < 101; len++) {
		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				dp[len][i] = dp[len - 1][1];
			}
			else if (i == 9) {
				dp[len][i] = dp[len - 1][8];
			}
			else {
				dp[len][i] = dp[len - 1][i - 1] + dp[len - 1][i + 1];
			}
			dp[len][i] %= divisor;
		}
	}

	int sum=0;
	for (int i = 1; i < 10; i++) {
		sum += dp[n][i];
		sum %= divisor;
	}
	cout << sum;


}
