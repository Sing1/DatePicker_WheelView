package sing.datepicker.wheelview;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import sing.datepicker_wheelview.R;

public class MyBottomDialog {

    private Dialog dialog = null;
    private View view = null;
    private DatePicker picker;

    public MyBottomDialog(Activity activity, final DatePicker.OnSelectDate listener) {
        dialog = new Dialog(activity,R.style.transparentFrameWindowStyle);
        view = View.inflate(activity,R.layout.bottom_dialog,null);
        dialog.setContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setWindowAnimations(R.style.bottom_dialog);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getScreenSize(activity).y;
        wl.width = getScreenSize(activity).x;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);// 点击窗口以外区域，关闭窗口

        picker = (DatePicker) view.findViewById(R.id.picker);

        view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.result(picker.getYear(),picker.getMonth(),picker.getDay());
                }
                dismiss();
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    // 获取屏幕尺寸
    public static Point getScreenSize(Activity act) {
        Point point = new Point();
        DisplayMetrics dm = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(dm);
        point.x = dm.widthPixels;
        point.y = dm.heightPixels;
        return point;
    }
}