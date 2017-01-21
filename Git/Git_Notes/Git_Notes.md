# Git_Notes

## Git Skill Notes that is useful;

### 技巧：将自己本地的修改暂存到栈中，然后再pull，然后再从栈中取出合并
	命令如下：
	git stash
	//这时候就已经整个提交控件
	//可以进行pull的操作了，因为pull不能够在本地修改还没有commit的时候进行pull；
	//这时候我们输入git stash list 就能够看到刚刚放入栈的缓存
	//然后输入
	git pull
	//更新仓库最新代码到本地；
	//然后再输入
	git stash pop
	//将缓存从栈中取出合并到分支中
	//git stash pop就相当于
	//git stash apply + git stash drop
	
### 技巧：查看某次提交的某个文件的修改
	命令如下：
	git show 某次的提交哈希值 文件名

### 技巧：查看本地分支和远程分支的对应情况
	命令如下：
	git branch -vv

### 技巧：删除为暂存修改
	命令如下：
	//对于文件
	git checkout .
	//对于文件夹和文件
	git clean -df