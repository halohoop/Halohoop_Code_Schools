# Android Studio Shortcut Keymap Smmary

### 快捷键总结

## 001.集合或者数组快速生成出for循环结构

  * 例子如下：

	List<Bean> datas = new ArrayList<>();
	这时候键入datas.for，然后回车；
	就能够得到关于datas的for循环结构；
	当然，输入datas.for之后会弹窗让你选择是高级for还是普通for等；

  * 数组也是一样的，例子如下：

	Bean[] beans = new Bean[10];
	beans.for回车；

## 002.将光标所在代码行向上向下移动 move line up or down

  * Alt+Shift+↑   move line up
  * Alt+Shift+↓   move line down

## 003.【Super Useful】将光标所在代码行所在的代码组向上向下移动 move statement up or down

  * Ctrl+Shift+↑   move line up
  * Ctrl+Shift+↓   move line down
  * 示例：

        public vo'cusor is here'id a(){
		//blah blah blah
	}

	public void b(){
		//blah blah blah
	}

    with cursor in method a line,then press Ctrl+Shift+↓ ,
    u will get the result below:

	public void b(){
		//blah blah blah
	}

	public vo'cusor is here'id a(){
		//blah blah blah
	}
