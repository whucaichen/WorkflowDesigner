package com.chance;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class DoubleClick extends MouseAdapter {
    private boolean flag = false; // 用来判断是否已经执行双击事件 ,每执行一次事件便将其置为true
    private int clickNum = 0;     // 用来判断是否该执行双击事件, 每执行一次事件便将其置为0
    private MouseEvent me = null;
    Timer timer = new Timer();    // 创建一个定时器

    public void mouseClicked(MouseEvent e) {  //覆写MouseAdapter中mouseClicked方法
        me = e; // 接收事件源 对象
        this.flag = false; // 每次点击鼠标，初始化双击事件执行标志为false

        if (this.clickNum == 1)  //当clickNum==1时执行双击事件
        {
            this.mouseDoubleClicked(me);// 执行双击事件
            this.clickNum = 0; // 执行完双击事件后，初始化双击事件执行标志为0
            this.flag = true;  // 双击事件已执行,事件标志为true
            return;
        }

        //clickNum!=1时执行下面的代码
        // 定时器开始执行,延时0.3秒后确定是否执行单击事件
        timer.schedule(new MyTimerTask(), new Date(), 300);//从系统当前时间开始，每隔0.3s重复执行一次run()方法
    }

    class MyTimerTask extends TimerTask  //定时器任务类
    {
        private int n = 0;// 记录定时器执行次数

        public void run() {
            if (flag) {// 如果双击事件已经执行,那么直接取消单击执行
                n = 0;
                clickNum = 0;
                this.cancel(); //取消此计时器任务。
                return;
            }
            if (n == 1) {// 定时器等待0.3秒后,双击事件仍未发生,执行单击事件
                mouseSingleClicked(me);// 执行单击事件
                flag = true;
                clickNum = 0;
                n = 0;
                this.cancel();   //取消此计时器任务。
                return;
            }
            clickNum++;
            n++;
        }
    }


    public void mouseSingleClicked(MouseEvent e) {
        System.out.println("Single Clicked!");
    }


    public void mouseDoubleClicked(MouseEvent e) {
        System.out.println("Doublc Clicked!");
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(300, 300);
        jf.setVisible(true);
        jf.addMouseListener(new DoubleClick());
    }
}