package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = "CalculatorActivity";
    private TextView tv_result; // 声明一个文本视图对象
    private String operator = ""; // 运算符
    private String firstNum = ""; // 第一个操作数
    private String secondNum = ""; // 第二个操作数
    private String result = ""; // 当前的计算结果
    private String showText = ""; // 显示的文本内容
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result=findViewById(R.id.tv_result);
        findViewById(R.id.btn_cancel).setOnClickListener(this); // “取消”按钮
        findViewById(R.id.btn_div).setOnClickListener(this); // “除法”按钮
        findViewById(R.id.btn_mul).setOnClickListener(this); // “乘法”按钮
        findViewById(R.id.btn_clear).setOnClickListener(this); // “清除”按钮
        findViewById(R.id.btn_seven).setOnClickListener(this); // 数字7
        findViewById(R.id.btn_eight).setOnClickListener(this); // 数字8
        findViewById(R.id.btn_nine).setOnClickListener(this); // 数字9
        findViewById(R.id.btn_add).setOnClickListener(this); // “加法”按钮
        findViewById(R.id.btn_four).setOnClickListener(this); // 数字4
        findViewById(R.id.btn_five).setOnClickListener(this); // 数字5
        findViewById(R.id.btn_six).setOnClickListener(this); // 数字6
        findViewById(R.id.btn_sub).setOnClickListener(this); // “减法”按钮
        findViewById(R.id.btn_one).setOnClickListener(this); // 数字1
        findViewById(R.id.btn_two).setOnClickListener(this); // 数字2
        findViewById(R.id.btn_three).setOnClickListener(this); // 数字3
        findViewById(R.id.btn_reciprocal).setOnClickListener(this); // 求倒数按钮
        findViewById(R.id.btn_zero).setOnClickListener(this); // 数字0
        findViewById(R.id.btn_dot).setOnClickListener(this); // “小数点”按钮
        findViewById(R.id.btn_equal).setOnClickListener(this); // “等号”按钮
        findViewById(R.id.btn_sqrt).setOnClickListener(this); // “开平方”按钮
    }

    @Override
    public void onClick(View view) {
        String input = null;
        if(view.getId()==R.id.btn_sqrt)
        {
            input="√";
        }else{
            input= ((TextView)view).getText().toString();
        }
        switch (view.getId())
        {
            case R.id.btn_cancel:
                break;
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
                operator=input;
                refreshText(showText+operator);
                break;
            case R.id.btn_equal:
                double calculate_reuslt=0;
                if(operator.equals(""))
                {
                    Toast.makeText(MainActivity.this,"请输入运算符",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(firstNum.equals("") || secondNum.equals(""))
                {
                    Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(operator.equals("÷") && Double.parseDouble(secondNum)==0)
                {
                    Toast.makeText(MainActivity.this,"除数不能为0",Toast.LENGTH_SHORT).show();
                    break;
                }
                 calculate_reuslt = calculateFour();
                refreshOperate(String.valueOf(calculate_reuslt));
                refreshText(showText+"="+String.valueOf(calculate_reuslt));
                break;
            case R.id.btn_reciprocal:
                break;
            default:
                //上次结果已经出来
                if(result.length()>0 && operator.equals(""))
                {
                    clear();
                }

                if(operator.equals(""))
                {
                    firstNum=firstNum+input;
                }else{
                    secondNum=secondNum+input;
                }

                if(showText.equals("0") && !input.equals("."))//showText是0但是又不是小数
                {
                    //整数不需要前面的0
                    refreshText(input);
                }else{
                    refreshText(showText+input);
                }

                break;

        }
    }
    private double calculateFour()
    {
        switch (operator)
        {
            case "+":
                return Double.parseDouble(firstNum)+Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum)-Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum)*Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum)/Double.parseDouble(secondNum);
        }
    }
    private void refreshText(String text)
    {
        showText=text;
        tv_result.setText(showText);
    }
    private  void refreshOperate(String new_result)//刷新运算结果
    {
        result=new_result;
        firstNum=new_result;
        secondNum="";
        operator="";
    }

    //清空并初始化
    private void clear()
    {
        refreshOperate("");
        refreshText("0");
    }

}
