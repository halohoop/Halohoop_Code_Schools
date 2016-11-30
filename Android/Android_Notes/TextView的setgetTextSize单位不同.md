# TextView的setgetTextSize单位不同

http://blog.csdn.net/a357664853/article/details/52316932

版权声明：本文为博主原创文章，未经博主允许不得转载。

今天要弄一个根据textview长度自动调节文字大小，防止文字超出范围导致UI混乱的问题，你懂得国际化的过程中很多语言的相同意义的文案长度不一样，导致UI很难看。

  tv是需要自动调节文字大小的Textview  

    Paint testPaint = tv.getPaint();  
    String text = tv.getText().toString();  
    int textWidth = tv.getMeasureWidth();  
    if (textWidth > 0) {  
      int availableWidth = textWidth - tv.getPaddingLeft() -  
              tv.getPaddingRight();  
      float trySize = tv.getTextSize();  
      testPaint.setTextSize(trySize);  
    while ((testPaint.measureText(text) > availableWidth)) {  
        trySize -= 2;  
        testPaint.setTextSize(trySize);  
    }  
    tv.setTextSize(trySize);  
    }  

发现字体反而更大了。。。。
看了一下源码发现
TextView中的getTextSize直接返回内部成员paint.getTextSize单位是PX，setTextSize默认是以SP为单位，所以在这种情况下使用的时候应该
[java] view plain copy
 在CODE上查看代码片派生到我的代码片

	tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, trySize);  

————————————————————————————————————————————————————————————————————————————————————————————————
后来发现一个问题，用px设置的话，文字的padding很大导致只显示了一个单词（当然是两个单词raw raw，结果显示成了一个居中的raw左右明显还有很多空白但是就是没把第二个一起显示出来），但是如果转换成sp直角设置，文字就能完全显示，空白页消失了。。。。。
 