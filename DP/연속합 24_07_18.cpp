#include<iostream>
#include<vector>
using namespace std;

int n;
vector<int> arr;



int main() {
	int max=-1e9;
	
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a; cin >> a;
		arr.push_back(a);
	}

	int sum=0;
	for (int i = 0; i < n; i++) {
		int cur = arr[i];
		sum += cur;
		if (max < sum)
			max = sum;
		if (sum < 0)
			sum = 0;
	}
	cout << max;
}
