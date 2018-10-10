/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li;

/**
 *
 * @author likang
 */
import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Container;  
import java.awt.Font;  
import java.awt.GridLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.Stack;  
  
import javax.swing.JApplet;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JTextField;  
  
    public class jisuanqi extends JApplet implements ActionListener  
    {  
        private final JTextField lk = new JTextField("       ");  
        String operator = "";//操作  
        String input = "";//输入的 式子  
        boolean flag =  true;  
    @Override
    public void init()//覆写Applet里边的init方法  
    {  
        Container li = getContentPane();  
        JButton ff[] = new JButton[16];  
        JPanel panel = new JPanel(); 
        lk.setFont(new Font("宋体",Font.PLAIN,23));
        li.add(lk, BorderLayout.NORTH);  
        li.add(panel,BorderLayout.CENTER);  
        panel.setLayout(new GridLayout(4, 4,5,5));  
        String anniu[]={"7","8","9","+","4","5","6","-","1","2","3","*","0","AC","=","/"};//设置 按钮  
        for(int i=0;i<16;i++)//添加按钮  
        {  
            ff[i] = new JButton(anniu[i]);  
            ff[i].setBackground(new Color(192,192,192));  
            ff[i].setForeground(Color.BLUE);//数字键 设置为 蓝颜色  
            if(i%4==3)  
                ff[i].setForeground(Color.RED);  
            ff[i].setFont(new Font("宋体",Font.PLAIN,23));//设置字体格式  
            panel.add(ff[i]);  
            ff[i].addActionListener(this);  
        }  
        ff[13].setForeground(Color.RED);//非数字键，即运算键设置为红颜色  
        ff[13].setForeground(Color.RED);  
    }  
    @Override
    public void actionPerformed(ActionEvent e)   
    {  
        int t = 0;  
        String zhiling = e.getActionCommand();  
        if(zhiling.equals("+")||zhiling.equals("-")||zhiling.equals("*") ||zhiling.equals("/"))  
            input +=" "+zhiling+" ";//设置输入，把输入的样式改成 需要的样子  
        else if(zhiling.equals("AC"))  
            input = "";  
        else if(zhiling.equals("="))//当监听到等号时，则处理 input  
        {  
            input+= "="+compute(input);  
            lk.setText(input);  
            input="";  
            t = 1;  
        }  
        else  
            input += zhiling;//数字为了避免多位数的输入 不需要加空格  
        if(t==0)  
        lk.setText(input);  
    }  
    private String compute(String input)//即1237 的 样例  
    {  
        String lf[];  
        lf = input.split(" ");  
        Stack<Double> feng = new Stack<Double>();  
        Stack<Character> li=new Stack<Character>();
        double m = Double.parseDouble(lf[0]);  
        feng.push(m);  
        for(int i=1;i<lf.length;i++)  
        {  
            if(i%2==1)    
            {    
                if(lf[i].compareTo("+")==0)    
                {    
                    double zhi = Double.parseDouble(lf[i+1]);    
                    feng.push(zhi);    
                }    
                    
                if(lf[i].compareTo("-")==0)    
                {    
                    double zhi = Double.parseDouble(lf[i+1]);    
                    feng.push(-zhi);    
                }    
                    
                if(lf[i].compareTo("*")==0)    
                {    
                    double zhi = Double.parseDouble(lf[i+1]);    
                    double k = feng.peek();//取出栈顶元素    
                    feng.pop();//消栈    
                    k*=zhi;    
                    feng.push(k);    
                }    
                    
                if(lf[i].compareTo("/")==0)    
                {    
                    double zhi = Double.parseDouble(lf[i+1]);    
                    double k = feng.peek();    
                    feng.pop();    
                    k/=zhi;    
                    feng.push(k);    
                }    
            }    
        }    
        double k = 0d;    
        while(!feng.isEmpty())    
        {    
            k+=feng.peek();    
            feng.pop();    
        }    
        String kf = String.valueOf(k);  
        return kf;  
    }  
    public static void main(String args[])  
    {  
        JFrame chuangti = new JFrame("likang计算器");  
        jisuanqi kang = new jisuanqi();  
        chuangti.getContentPane().add(kang, BorderLayout.CENTER);  
        kang.init();//kang的init方法  
        kang.start();//线程开始  
        chuangti.setSize(350, 400);//设置窗口大小  
        chuangti.setVisible(true);//设置窗口可见  
        chuangti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
} 
