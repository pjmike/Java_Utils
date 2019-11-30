package com.pjmike.reactive.callback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>
 * Java Swing 事件/监听是一种典型的既符合异步非阻塞，又属于 Callback 实现的场景，其并发模型可为同步或异步
 * </p>
 *
 * <p>
 * Java GUI 以及事件/监听模式基本采用匿名内置类实现，即回调实现。
 * 从本例可以得出，鼠标的点击确实没有被其他线程给阻塞。不过当监听的维度增多时，Callback 实现也随之增多
 * </p>
 * @author: pjmike
 * @create: 2019/11/29
 */
public class JavaGUI {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("GUI 示例");
        jFrame.setBounds(500, 300, 400, 300);
        LayoutManager layoutManager = new BorderLayout(400, 300);
        jFrame.setLayout(layoutManager);
        //添加鼠标点击事件发生时的监听器
        //可以看做一种异步callback
        //实际上主线程和执行线程不是同一个线程
        jFrame.addMouseListener(new MouseAdapter() { //callback1
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("[线程 : %s] 鼠标点击，坐标(X : %d, Y : %d)\n",
                        currentThreadName(), e.getX(), e.getY());
            }
        });
        //添加窗口事件发生时的监听器
        jFrame.addWindowListener(new WindowAdapter() { //callback2
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.printf("[线程 : %s] 清除 jFrame... \n", currentThreadName());
                jFrame.dispose(); // 清除 jFrame
            }
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.printf("[线程 : %s] 退出程序... \n", currentThreadName());
                System.exit(0); // 退出程序
            }

        });
        System.out.println("当前线程：" + currentThreadName());
        jFrame.setVisible(true);
    }

    private static String currentThreadName() { // 当前线程名称
        return Thread.currentThread().getName();
    }
}
