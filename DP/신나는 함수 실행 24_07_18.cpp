#include <iostream>
#include <vector>
using namespace std;

vector < vector<int>> abc;
vector<vector<vector<int>>> dp(21, vector<vector<int>>(21, vector<int>(21, 0)));

void fill_dp() {
    for (int a = 1; a <= 20; a++) {
        for (int b = 1; b <= 20; b++) {
            for (int c = 1; c <= 20; c++) {
                if (a <= 0 || b <= 0 || c <= 0)
                    dp[a][b][c] = 1;
                if (a > 20 || b > 20 || c > 20)
                    dp[a][b][c] = dp[20][20][20];
                if (a < b && b < c)
                    dp[a][b][c] = dp[a][b][c - 1] + dp[a][b - 1][c - 1] - dp[a][b - 1][c];
                else
                    dp[a][b][c] = dp[a - 1][b][c] + dp[a - 1][b - 1][c] + dp[a - 1][b][c - 1] - dp[a - 1][b - 1][c - 1];
            }
        }
    }
}

int w(int a, int b, int c) {
    if (a <= 0 || b <= 0 || c <= 0)
        return 1;
    if (a > 20 || b > 20 || c > 20)
        return dp[20][20][20];
    return dp[a][b][c];
}

int main() {
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

    for (int i = 0; i <= 20; i++) {
        for (int j = 0; j <= 20; j++) {
            dp[0][i][j] = 1;
            dp[i][0][j] = 1;
            dp[i][j][0] = 1;
        }
    }
    fill_dp();
    while (true) {
        int a, b, c;
        cin >> a >> b >> c;
        if (a == -1 && b == -1 && c == -1)
            break;
        abc.push_back({ a,b,c });
    }
    for (auto i : abc) {
        int a, b, c;
        a = i[0];
        b = i[1];
        c = i[2];
        cout << "w(" << a << ", " << b << ", " << c << ") = " << w(a,b,c) << endl;
    }
}