#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
vector<int> line(501, 0);
vector<int> endline(101,0);
vector<int> len(101,0);

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n;

	for (int i = 0; i < n; i++) {
		int a, b;
		cin >> a >> b;
		line[a] = b;
	}
	int k = 0;
	for (int i = 0; i < 501; i++) {
		if (line[i] != 0) {
			endline[k++] = line[i];
		}
	}

	for (int i = 0; i < n; i++) {
		int cur;
		cur = endline[i];
		len[i] = 1;
		for (int j = 0; j < i; j++) {
			if (cur > endline[j])
				len[i] = max(len[i], len[j] + 1);
		}
	}

	int maxlen = 0;
	for (int i = 0; i < 101; i++) {
		maxlen = max(maxlen, len[i]);
	}

	cout << n - maxlen;

}