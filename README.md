#### LavCahce
====

> **Introduction**:  LavCache是一款可以支持现有场景的缓存框架，支持自定义存储媒介、项目初始化加载、过期重试、访问统计等多种特性，
> 并且支持yml文件配置。技术水平有限，欢迎各位大佬批评指正([lonelyangel.jcw@gmail.com](mailto:lonelyangel.jcw@gmail.com))

一. 相关概念
  1. 特定名词
  
     - **Piece:** 对于执行缓存的Method的称呼，缓存执行的单位
     - **Sector:** 管理一组Piece，同一个Sector下面的Piece使用同一个Cache实例
     - **Container:** 管理Sector 
     - **CachePool:** 管理Cache，内部CacheName与Sector Name一致，通过两个Name进行匹配
  2. LavCacache中缓存的管理和Sectors的管理时独立的，通过CacheName与SectorName匹配来进行组合，
    在Preload的场景下可能会出现先加载缓存然后BuildCache，然后缓存使用默认的ConcurrentCache的情况，开发需要保证调用顺序
  
二. 配置文件
    
  * resouces/lavcache.yml
   ```yaml
    cache   :
      - name   : "DefaultCache" # same as sector name
        expire : 100   # ms
        record : false # cache visited log
        autoreload : false # switch which used to auto reload cache
        impl   : com.langel.customize.RestRedisCache.class # class name with implement Cache interface.
    
    preload :
      - com.langel.lavcache.test.MySector.class # sector class name with need preload
    
    redis   :
      ip : 127.0.0.1
      host : 6379
  ```
  
三. 缓存过期

  每1min触发一次Job进行对缓存的expire(ms)进行处理,此处的清理时类似于懒加载，当sector中expire字段的值<=0,
  needReload将被置为true，当下次访问到这个sector中的缓存时将被重新加载  

四. 缓存预加载

   > **Notice**:由于预加载并不知晓函数执行时的参数，因此对于有预加载需求的Piece不要指定参数 
   
   ```java
      PieceLoader loader = SectorInjector.getInstance(PieceLoader.class);
      boolean val = loader.preload();
   ```
五. 使用
    
   符合JSR107规范，Demo
   ```java
   
   
    @Test
    public void injectTest() {
        MySector sector = SectorInjector.getInstance(MySector.class);
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        sector.erase("UserId 111");
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.pieceMultiParam("UserId 111","key2"));

        System.out.println(sector.pieceMultiParam("UserId 111","key2"));
        System.out.println(sector.eraseMultiParam("UserId 111","key2"));
        System.out.println(sector.pieceMultiParam("UserId 111","key2"));
        System.out.println(sector.pieceMultiParam("UserId 111","key3"));
        System.out.println(sector.pieceMultiParam("UserId 111","key3"));

        System.out.println(sector.piece("UserId 111"));

        Assert.assertNotNull(sector.piece("test2"));

    }
    
    /**
     * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
     * @date 2018/9/20
     **/
    @Sector("MyTestSector")
    public class MySector {
    
        @Piece(prefix = "piece", preload = true, after = {PreloadPieceAction.class})
        public String preloadPiece() {
            System.out.println("With no hit cache");
            return "PreLoadTest";
        }
    
        @Piece(prefix = "testkey#userId")
        public String piece(@PieceKey(field = "userId") String userId) {
            System.out.println("With no hit cache");
            return "MyTestPiece";
        }
    
        @Erase(prefix = "testkey#userId")
        public String erase(@PieceKey(field = "userId") String userId) {
            return "";
        }
    
        @Piece(prefix = "testkey2")
        public String pieceMultiParam(@PieceKey(field = "userId") String userId,
                                      @PieceKey(field = "key2") String keys) {
            System.out.println("With no hit cache");
            return "MyTestPiece";
        }
    
        @Erase(prefix = "testkey2")
        public String eraseMultiParam(@PieceKey(field = "userId") String userId,
                                      @PieceKey(field = "key2") String keys) {
            System.out.println("With no hit cache");
            return "MyTestPiece";
        }
    }
   ```  
   如果要使用autoreload，过期控制，preload，自定义Cache等功能，需要
   首先使用Luancher进行初始化
   
   ```java
        LavCacheLauncher launcher=new LavCacheLauncher();
        launcher.load();
   ```

 