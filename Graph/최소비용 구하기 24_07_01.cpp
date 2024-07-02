#include <iostream>
#include <vector>
#include <queue>
using namespace std;

const int INF = 1e9;

priority_queue<pair<int, int>> pq; // weight, node 순으로 저장
vector<int> dist(1001, INF);
vector<vector<pair<int, int>>> e(1001); // dist, node 로 저장
vector<bool> visited(1001, false);
int N, M; // N 도시 수, M 엣지 수
int start, dest;


void dijkstra(int s) {

	dist[s] = 0;
	pq.push({ 0, s });
	int cur, distance;

	while (!pq.empty()) {
		cur = pq.top().second;
		if (visited[cur]) {
			pq.pop();
			continue;
		}
		distance = -pq.top().first; // heapQ 음수처리
		visited[cur] = true;
		pq.pop();
		
		for (int i = 0; i < e[cur].size(); i++) {
			int weight, next, d;
			
			next = e[cur][i].first;
			weight = e[cur][i].second;
			d = dist[cur] + weight;

			if (d < dist[next]) {
				dist[next] = d;
				pq.push({ -d, next });
			}
		}	
	}
}

int main() {
	cin >> N;
	cin >> M;

	for (int i = 0; i < M; i++) {
		int a, b, w;
		cin >> a >> b >> w;
		e[a].push_back({ b,w });
	}

	cin >> start >> dest;

	dijkstra(start);

	cout << dist[dest];



}
