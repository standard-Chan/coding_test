#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
const int INF = 1e9;
int n, m;

vector<vector<vector<int>>> e(101, vector<vector<int>>(101));
vector<vector<int>> dist(101, vector<int>(101, INF));
void print() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (dist[i][j] == INF)
				cout << "0" << " ";
			else
				cout << dist[i][j] << " ";
		}
		cout << endl;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n;
	cin >> m;

	for (int i = 0; i < m; i++) {
		int a, b, w;
		cin >> a >> b >> w;
		e[a][b].push_back(w);
	}


	for (int i = 1; i <= n; i++) {
		dist[i][i] = 0;
	}

	// ÃÊ±âÈ­
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			for (int k = 0; k < e[i][j].size(); k++) {
				if (dist[i][j] > e[i][j][k])
					dist[i][j] = e[i][j][k];
			}
		}
	}

	//floyd
	for (int m = 1; m <= n; m++) {
		for (int s = 1; s <= n; s++) {
			if (s == m)
				continue;
			for (int next = 1; next <= n; next++) {
				if (s == next || next == m)
					continue;
				if (dist[s][next] > dist[s][m] + dist[m][next]) {
					dist[s][next] = dist[s][m] + dist[m][next];
				}
			}
		}
	}
	print();
}
