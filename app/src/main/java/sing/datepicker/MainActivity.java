package sing.datepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sing.datepicker.wheelview.DatePicker;
import sing.datepicker.wheelview.MyBottomDialog;
import sing.datepicker_wheelview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(final View v){
        new MyBottomDialog(this, new DatePicker.OnSelectDate() {
            @Override
            public void result(String year, String month, String day) {
                ((Button)v).setText(year + "-" + month + "-" + day);
            }
        }).show();
    }
}