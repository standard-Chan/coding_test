#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
int r1, r2;
vector<int> s;
int sum = 2147283647;

void search() {
	int p1, p2, tmp;
	p1 = 0;
	p2 = s.size()-1;
	while (true) {
		
		if (sum == 0)
			break;
		if (p1 >= p2)
			break;

		tmp = s[p1] + s[p2];
		if (abs(sum) > abs(tmp)) {
			sum = tmp;
			r1 = p1;
			r2 = p2;
		}

		if (tmp > 0) {
			p2--;
		}

		else if (tmp < 0) {
			p1++;
		}
	}
}


int main() {
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++) {
		int a;
		cin >> a;
		s.push_back(a);
	}
	sort(s.begin(), s.end());

	r1 = 0;
	r2 = s.size() - 1;

	search();

	cout << s[r1] << " " << s[r2];


}