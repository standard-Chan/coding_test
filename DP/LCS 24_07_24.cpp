#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	string str1, str2;
	cin >> str1;
	cin >> str2;
	
	vector<vector<int>> dp (str1.length() + 1, vector<int>(str2.length() + 1, 0));

	int l1, l2;
	l1 = str1.length();
	l2 = str2.length();

	for (int y = 1; y <= l1; y++) {
		for (int x = 1; x <= l2; x++) {
			dp[y][x] = max(dp[y - 1][x], dp[y][x - 1]);
			
			if (str1[y-1] == str2[x-1]) {
				dp[y][x] = dp[y - 1][x - 1] + 1;
			}
		}
	}


	int result = 0;
	for (int i = 0; i <= l1; i++) {
		for (int j = 0; j <= l2; j++) {
			
			result = max(result, dp[i][j]);
		}
	}

	cout << result;
}