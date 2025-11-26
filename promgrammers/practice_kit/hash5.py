from collections import defaultdict;

def safe_in(arr, idx):
    return true if len(arr) > idx else false;

def solution(genres, plays):
    answer = [];
    length = len(genres);
    
    genresTotalPlay = defaultdict(int);
    genresTotalPlayList = [];
    musicByGenre = defaultdict(list);
    
    for idx in range(length):
        genre = genres[idx];
        times = plays[idx];
        # genres와 plays 읽으면서 hash<genre, <id, play>[]> 값 추가하기 O(N)        
        musicByGenre[genre].append((idx, times));
        # 위 작업 함과 동시에 genresTotal Hash<genre, int> 에도 추가하기
        genresTotalPlay[genre] += times;
        
    # hash play 전체 읽으면서 각 요소 정렬하기
    for _, lst in musicByGenre.items():
        lst.sort(key=lambda x : x[1], reverse=True);
    
    # genres Total 전체 읽으면서 각 요소 정렬하기
    for key, play in genresTotalPlay.items():
        genresTotalPlayList.append((key,play));
        
    genresTotalPlayList.sort(key=lambda x: x[1], reverse=True);
    
    # genres Total의 각 genres 순서대로 hash play에서 최대 2개씩 빼서 answer[]에 추가하기
    for a in genresTotalPlayList:
        genre = a[0];
        answer.append(musicByGenre[genre][0][0]);
        if (len(musicByGenre[genre]) > 1):
            answer.append(musicByGenre[genre][1][0]);
            
    return answer
