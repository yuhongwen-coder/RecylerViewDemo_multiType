package com.example.recylerviewdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Dialog需要的WindowToken在Activity onCreate时，WindowManager和Window已初始化好，因为直接用的该WindowManager.mParentWindow（即activity window）的token，所以不为空。
 *
 * PopWindow则是直接用的View.getAttachInfo()里面的token，由于是在ActivityThread的handleResumeActivity函数中才调用windowmanager.addView方法，即显示界面，然后根据decorview创建对应的ViewRootImpl，然后才有ViewRootImpl的performTraversals遍历整个viewtree并将attachinfo传递给所有子view（ViewRootImpl.scheduleTraversals()是个异步操作），因此在onCreate时popwindow创建时报token null异常。
 * ActivityThread.handleResumeActivity中会调用wm.updateViewLayout(decor, l);这个最终会导致viewtree遍历。
 *
 * 作者：大屁股小白
 * 链接：https://www.jianshu.com/p/11430e86853a
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 *
 * 程序打开后，点击弹框，生命周期显示是：onCreate() -> onStart() -> onResume 。这表明弹出的东西对后面Activity的生命周期并没有影响。
 *
 * 官方文档我们可以看到： onPause()：Called when the system is about to start resuming another activity. （启动另外一个Activity的时候才会进入onPause状态）
 *
 * Dialog实现的原理是windowmanager.addView();其实添加的是一个view。所以，我们弹出的Dialog对话框实际上是Activity的一个组件，Activity并不是不可见而是被一个布满屏幕的组件覆盖到最顶层，我们无法对其他内容进行操作，因此，Dialog不会影响Activity的生命周期的变化。
 *
 *
 *
 * 如果在弹出对话框的Activity点击home键返回桌面，Activity生命周期会不会变化呢？当然会，会执行onPause() -> onStop() 。
 *
 * 再次启动： onRestart() -> onStart() -> onResume()。（有没有Dialog生命周期都不会发生变化）
 *
 *  1 ：Dialog 生命周期 onCreta（） 以及构造函数
 *  2： Dialog 显示在页面上的位置 setContentView() 函数真的重要性
 *  3： RecylerView 显示 Dialog 内容
 */

public class CustomDialog extends Dialog {
    private CustomDialog customDialog;

    public CustomDialog(@NonNull Context context) {
        super(context);
        initView(context);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout,null,false);
        setContentView(view);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public class DiallogBuilder {
        private CustomDialog build() {
            customDialog = new CustomDialog();
        }
    }
}
