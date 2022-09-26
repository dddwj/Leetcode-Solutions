# """
# This is HtmlParser's API interface.
# You should not implement it, or speculate about its implementation
# """
#class HtmlParser(object):
#    def getUrls(self, url):
#        """
#        :type url: str
#        :rtype List[str]
#        """

# Reference: https://leetcode.com/problems/web-crawler/discuss/450325/Python-BFS-straightforward
class Solution:
    def crawl(self, startUrl: str, htmlParser: 'HtmlParser') -> List[str]:
        def genHostName(url: str) -> str:
            hostname = url.split("http://")[1].split('/')[0]
            return hostname

        hostname = genHostName(startUrl)
        visited = set([startUrl])
        q = deque([startUrl])
        while q:
            url = q.popleft()
            conn = htmlParser.getUrls(url)
            for new_url in conn:
                if new_url in visited:
                    continue
                if genHostName(new_url) == hostname:
                    q.append(new_url)
                    visited.add(new_url)

        return list(visited)