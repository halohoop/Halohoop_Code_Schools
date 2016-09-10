# In eclipse,how to link jar and source folder,step in step

# Changelog 
 
### Version: 1.0.1

  * In eclipse,how to link jar and source folder,step by step;
  * 在eclipse中如何连接jar包和原码，使得码代码的时候直接可以ctrl点进去看到原码；

### 步骤

![eclipse:技巧001](../../pics/03aafd9c-3e55-43d9-b442-7bd48a059450.png)


  * 首先我们在libs目录下，设置一个properties文件，
  * 文件名为：

    **jar包的完整名字.properties**

  * 如：**android-support-v7-recyclerview.jar.properties**
  * android-support-v7-recyclerview.jar.properties文件的内容如下：

    src=D:\\\eclipse-java-mars-1-win32-x86_64\\\support_source\\\support 

	
  * **记住如果是单斜杠，要换成双斜杠哦！**


  * 又如v4包的**android-support-v4.jar.properties**：

    src=D:\\\eclipse-java-mars-1-win32-x86_64\\\support_source\\\support\\\v4\\\java 

