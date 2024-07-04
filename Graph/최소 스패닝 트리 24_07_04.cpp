#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int V, E;
vector<bool> visited(10001, false);
vector<vector<pair<int, int>>> e(10001);
long long result=0;

void prim() {
	int cur, next, weight;
	priority_queue<pair<int, int>> pq;

	visited[1] = true;
	for (int i = 0; i < e[1].size(); i++) {
		int n, w;
		n = e[1][i].second;
		w = - e[1][i].first;
		pq.push({w, n});
	}
	
	while (!pq.empty()) {
		cur = pq.top().second;
		if (visited[cur]) {
			pq.pop();
			continue;
		}
		weight = -pq.top().first;
		visited[cur] = true;
		pq.pop();
		
		result += weight;

		for (int i = 0; i < e[cur].size(); i++) {
			int n, w;

			n = e[cur][i].second;
			if (visited[n])
				continue;

			w = - e[cur][i].first;

			pq.push({ w,n });
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> V >> E;


	for (int i = 0; i < E; i++) {
		int a, b, w;
		cin >> a >> b >> w;
		e[a].push_back({ w, b });
		e[b].push_back({ w, a });
	}

	prim();

	cout << result << endl;
}