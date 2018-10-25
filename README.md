#### LavCahce


<div style="align: center">
    <img src="./logo.png"/>
</div

> **Introduction**:  LavCache是一款可以支持现有大多数业务场景的缓存框架，支持自定义存储媒介、项目初始化加载、过期重试、访问统计等多种特性，
> 并且支持yml文件配置。技术水平有限，欢迎各位大佬批评指正([lonelyangel.jcw@gmail.com](mailto:lonelyangel.jcw@gmail.com))
> 
> **Poject Address**：https://github.com/L-Angel/lavcache


一. 相关概念
  1. 特定名词
 
     - **Piece:** 对于执行缓存的Method的称呼，缓存执行的单位
     - **Sector:** 管理一组Piece，同一个Sector下面的Piece使用同一个Cache实例，突破了类文件的隔离
     - **Container:** 管理Sector 
     - **CachePool:** 管理Cache，内部CacheName与Sector Name一致，通过两个Name进行匹配
  2. LavCache中缓存的管理和Sectors的管理是独立的，通过CacheName与SectorName匹配来进行组合，
    在Preload的场景下可能会出现先加载缓存然后BuildCache，然后缓存使用默认的ConcurrentCache的情况，开发需要保证调用顺序
  3. 特性
     
     - 缓存行为事件扩展
     - 缓存自动过期
     - 缓存预加载
     - 自定义缓存媒介，支持是否设置过期时间（ms）
     - 动态生成缓存KEY
     - 支持yml文件配置
     - 缓存启动Launcher扩展
     
二. 配置文件
    
  * resouces/lavcache.yml
   ```yaml
    cache   :
      - name   : "DemoCustomizeCache" # same as sector name
        expire : 60000   # ms
        record : false # cache visited log
        autoreload : true # switch which used to auto reload cache
        impl   : com.langel.lavcache.mock.DemoCustomizeCache # class name with implement Cache interface.
    
    preload :
      - com.langel.lavcache.mock.MySector # sector class name with need preload
      - com.langel.lavcache.mock.DemoSector
    
    sectors :
      #- com.langel.lavcache.DemoSector
    
    redis   : # Beta，is not a release feature
      ip : 127.0.0.1
      host : 6379
   ```
  
三. 缓存过期

  ~~每1min触发一次Job进行对缓存的expire(ms)进行处理,此处的清理时类似于懒加载，当sector中expire字段的值<=0,
  needReload将被置为true，当下次访问到这个sector中的缓存时将被重新加载~~ 
  
  缓存在执行getRaw方法的时候会事先进行过期时间的校验，如果当前时间大于等于expireAt时间，就会将Sector下面
  所有的Piece中needreload字段置为True，执行懒刷新。所谓的懒刷新指的是在在获取缓存里面的数据的时候，会事先
  对Method对应下的Piece中needReload字段进行判断，如果为True则进行异步的加载。

四. 缓存预加载

   > **Notice**:由于预加载并不知晓函数执行时的参数，因此对于有预加载需求的Piece不要指定参数 
   
   ```java
      PieceLoader loader = SectorInjector.getInstance(PieceLoader.class);
      boolean val = loader.preload();
   ```
 
五. Action,缓存事件
    
   LavCache一大亮点就是在Piece初始化缓存之后，会出发一系列自定的Action事件，来进行缓存返回数据的重构，
   ```java
   /**
    *PreloadAction 即为自己定义的Action行为，该Action必须实现，com.langel.lavcache.action.Action接口 
    */
   @Piece(prefix = "piece", preload = true, after = {PreloadPieceAction.class})
   public String preloadPiece() {
      System.out.println("With no hit cache");
      return "PreLoadTest";
   }
   
   /**
    * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
    * @date 2018/9/29 3:42 PM
    **/
   public class PreloadPieceAction implements Action {
   
       @Override
       public void call(MethodHolder holder, String key, Object val) {
           ActionDataCache.DATA = val;
           System.out.println("PreloadPiece action");
           System.out.println(key);
           System.out.println(val);
       }
   }
   ```
六. 相关说明
   1. 缓存key生成规则（大写），无参Piece=PREFIX，有参Piece=PREFIX://FIELD1=VAL1;FIELD2=VAL2;
      其中FIELD1，FIELD2 为 com.langel.lavcache.annotation.PieceKey 中field字段
   
   2. 线程
   
      Cache运行过程中会启动两组线程(已删除该线程)
      - lavcache-single-schedule-thread （threadSize:1）
      
      调度线程，1分钟运行1次，用于对expire的处理
      
      - lavcache-pool-x-thread-x（coreSize:0,maxSize:30,keepAlive:33mins,queueSize:1000）
        
        用于异步重载缓存等
        
七. 使用
    
  Demo
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
八. In the future

    In the future,lavcache will support invoke by SpringFramework style.Bring the document to completion.
    And I wish more and more developer who interested in this tools and like open source activities join us.
    You can contract me with open a issue in github or send a mail to me,Rick,L-Angel(lonelyangel.jcw@gmail.com)
     
 