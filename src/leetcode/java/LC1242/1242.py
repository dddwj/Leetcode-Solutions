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

# Reference: https://leetcode.com/problems/web-crawler-multithreaded/solutions/739683/Concise-and-Beautiful-Python/
# Not the best solution:  https://leetcode.com/problems/web-crawler-multithreaded/solutions/739683/Concise-and-Beautiful-Python/comments/748164
class Solution1_not_optimized:
    def crawl(self, startUrl: str, htmlParser: 'HtmlParser') -> List[str]:
        def hostname(url: str) -> str:
            url = url.split("http://")[1]
            hostname = url.split("/")[0]
            return hostname

        seen = {startUrl}

        from concurrent import futures
        with futures.ThreadPoolExecutor(max_workers=16) as executor:
            tasks = deque([executor.submit(htmlParser.getUrls, startUrl)])
            while tasks:
                for url in tasks.popleft().result():
                    if url not in seen and hostname(startUrl) == hostname(url):
                        seen.add(url)
                        tasks.append(executor.submit(htmlParser.getUrls, url))

        return list(seen)


# Reference: https://leetcode.com/problems/web-crawler-multithreaded/solutions/418126/python3-multithreaded-bfs-with-queue/comments/729405
class Solution2_optimized:
    def crawl(self, startUrl: str, htmlParser: 'HtmlParser') -> List[str]:
        def hostname(url: str) -> str:
            url = url.split("http://")[1]
            hostname = url.split("/")[0]
            return hostname

        from concurrent import futures
        from queue import Queue    # Ref: https://stackoverflow.com/questions/717148/queue-queue-vs-collections-deque
        import threading

        seen = {startUrl}
        queue = Queue()
        seen_lock = threading.Lock()

        def worker():
            try:
                while True:
                    url = queue.get(timeout=0.015)
                    for next_url in htmlParser.getUrls(url):
                        if next_url not in seen and hostname(next_url) == hostname(startUrl):
                            seen_lock.acquire()
                            # Acquire lock to ensure urls are no enqueed multiple times
                            if next_url not in seen:
                                seen.add(next_url)
                                queue.put(next_url)
                            seen_lock.release()
                    queue.task_done()
            except:
                pass


        queue.put(startUrl)
        with futures.ThreadPoolExecutor(max_workers=8) as executor:
            for i in range(8):
                executor.submit(worker)

        return list(seen)