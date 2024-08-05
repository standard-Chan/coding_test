#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> arr(1001, 0);
vector<int> dp(1001, 0);
int n;

int main() {
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n;

	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		arr[i] = a;
	}

	dp[0] = 1;

	for (int i = 1; i < n; i++) {
		int cur, maxSeq;
		cur = arr[i];
		maxSeq = 0;
		for (int j = 0; j < i; j++) {
			if (cur > arr[j] && maxSeq < dp[j]) {
				maxSeq = dp[j];
			}
		}
		dp[i] = maxSeq+1;
	}

	int result = 0;
	for (int i = 0; i < n; i++) {
		if (result < dp[i])
			result = dp[i];
	}

	cout << result;
}