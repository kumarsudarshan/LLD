package cacheservice;

public class Main {
	
	public static void main(String[] args) {
		
		LRUCache<Integer, Integer> lruCache = new LRUCache<Integer, Integer>(5);
		CacheService<Integer, Integer> cacheService = new LRUCacheServiceImpl<Integer, Integer>(lruCache);
		
		cacheService.put(1, 100);
		cacheService.put(2, 200);
		System.out.println(cacheService.get(1));
		cacheService.put(3, 300);
		cacheService.put(4, 400);
		cacheService.put(5, 500);
		cacheService.put(6, 600);
		System.out.println(lruCache.toString());
		System.out.println(cacheService.get(1));
		System.out.println(cacheService.get(2));
		System.out.println(cacheService.get(1));
		cacheService.put(6, 600);
		cacheService.put(7, 700);
		
		System.out.println(lruCache.toString());
		
		
	}

}
