# 优雅的让Fragment监听返回键 

### from [http://blog.csdn.net/guxiao1201/article/details/40507387](http://blog.csdn.net/guxiao1201/article/details/40507387)

版权声明：本文为博主原创文章，未经博主允许不得转载。

转载请注明出处：[http://write.blog.csdn.NET/postedit/40507387](http://write.blog.csdn.NET/postedit/40507387)


Activity可以很容易的得到物理返回键的监听事件，而Fragment却不能。假设FragmentActivity有三个Fragment，一般安卓用户期望点击返回键会一层层返回到FragmentActivity。当然，我们可以将每个Fragment对应的Transaction放到BackStack中，但是如果每个Fragment有对返回事件的特殊消费，那么在FragmentActivity的onBackPressed()中的代码就会比较混乱，例如：

[java] view plain copy

    @Override  
    public void onBackPressed() {  
        if(selectedFragment.equals(fragmentA) && fragmentA.hasExpandedRow()) {  
            fragmentA.collapseRow();  
        } else if(selectedFragment.equals(fragmentA) && fragmentA.isShowingLoginView()) {  
            fragmentA.hideLoginView();  
        } else if(selectedFragment.equals(fragmentA)) {  
            popBackStack();  
        } else if(selectedFragment.equals(fragmentB) && fragmentB.hasCondition1()) {  
            fragmentB.reverseCondition1();  
        } else if(selectedFragment.equals(fragmentB) && fragmentB.hasCondition2()) {  
            fragmentB.reverseCondition2();  
        } else if(selectedFragment.equals(fragmentB)) {  
            popBackStack();  
        } else {  
            // handle by activity  
            super.onBackPressed();  
        }  
    }  

这对于有代码洁癖的程序猿显然是不能容忍的，后来发现了一种优雅的解决方案。

首先创建一个抽象类BackHandledFragment，该类有一个抽象方法onBackPressed()，所有BackHandledFragment的子类在onBackPressed方法中处理各自对Back事件的消费逻辑。onBackPressed返回布尔值，宿主FragmentActivity将会根据该方法的返回值判断子Fragment是否有消费Back事件。此外，宿主FragmentActivity还会保持一份当前Fragment的引用，当用户按下Back键时，宿主Activity会判断当前Fragment是否需要消费该事件，如果没有Fragment消费才会自己消费。

[java] view plain copy

    public abstract class BackHandledFragment extends Fragment {  
      
        protected BackHandledInterface mBackHandledInterface;  
          
        /** 
         * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑 
         * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件 
         * 如果没有Fragment消息时FragmentActivity自己才会消费该事件 
         */  
        protected abstract boolean onBackPressed();  
          
        @Override  
        public void onCreate(Bundle savedInstanceState) {  
            super.onCreate(savedInstanceState);  
            if(!(getActivity() instanceof BackHandledInterface)){  
                throw new ClassCastException("Hosting Activity must implement BackHandledInterface");  
            }else{  
                this.mBackHandledInterface = (BackHandledInterface)getActivity();  
            }  
        }  
          
        @Override  
        public void onStart() {  
            super.onStart();  
            //告诉FragmentActivity，当前Fragment在栈顶  
            mBackHandledInterface.setSelectedFragment(this);  
        }  
          
    }  

宿主FragmentActivity需要继承BackHandledIntegerface，子Fragment会通过该接口告诉宿主FragmentActivity自己是当前屏幕可见的Fragment。

[java] view plain copy

    public interface BackHandledInterface {  
      
        public abstract void setSelectedFragment(BackHandledFragment selectedFragment);  
    }  

所以在Fragment的onCreate中会判断宿主FragmentActivity是否已继承了该接口。在Fragment的onStart()方法中就会调用该接口告诉宿主FragmentActivity自己是当前屏幕可见的Fragment。
宿主FragmentActivity就可以在onBackPressed()方法中对Back事件进行判断处理了。

[java] view plain copy

    public class MainActivity extends FragmentActivity implements BackHandledInterface{  
      
        private BackHandledFragment mBackHandedFragment;  
        private boolean hadIntercept;  
      
        @Override  
        public void setSelectedFragment(BackHandledFragment selectedFragment) {  
            this.mBackHandedFragment = selectedFragment;  
        }  
          
        @Override  
        public void onBackPressed() {  
            if(mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()){  
                if(getSupportFragmentManager().getBackStackEntryCount() == 0){  
                    super.onBackPressed();  
                }else{  
                    getSupportFragmentManager().popBackStack();  
                }  
            }  
        }  
    }  

示例程序[Github链接](https://github.com/mxy1228/FragmentHandleBackDemo/)。
参考资料：
[Handling back button press Inside Fragments](http://vinsol.com/blog/2014/10/01/handling-back-button-press-inside-fragments/)