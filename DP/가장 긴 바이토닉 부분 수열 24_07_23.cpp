#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> num(1001, 0);
vector<int> inc(1001, 0);
vector<int> de(1001, 0);
vector<int> sum(1001, 0);
int n;

int main() {
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n;

	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		num[i] = a;
	}

	inc[0] = 1;
	de[n - 1] = 1;

	for (int i = 1; i < n; i++) {
		int maxlen, cur;
		maxlen = 0;
		cur = num[i];
		for (int j = 0; j < i; j++) {
			if (cur > num[j] && maxlen < inc[j]) {
				maxlen = inc[j];
			}
		}
		inc[i] = maxlen + 1;
	}
	for (int i = n-2; i >= 0; i--) {
		int maxlen, cur;
		maxlen = 0;
		cur = num[i];
		for (int j = n-1; j > i; j--) {
			if (cur > num[j] && maxlen < de[j]) {
				maxlen = de[j];
			}
		}
		de[i] = maxlen + 1;
	}

	int result = 0;

	for (int i = 0; i < n; i++) {
		sum[i] = de[i] + inc[i] - 1;
		if (result < sum[i])
			result = sum[i];
	}

	cout << result;
}