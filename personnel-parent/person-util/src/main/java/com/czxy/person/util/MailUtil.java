package com.czxy.person.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {


    public static void sendMail(String to, String subject, String text) throws Exception {
        //1 创建propreties对象
        Properties props = new Properties();
        //2 设置邮箱服务器地址 smtp.163.com    smtp.126.com     smtp.qq.com
        props.put("mail.smtp.host", "smtp.126.com");
        //3 设置是否打开验证 true
        props.put("mail.smtp.auth", true);
        //4 获取与邮件服务器的连接
        Session session = Session.getDefaultInstance(props);
        //5 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        //6 设置发送者
//        InternetAddress fromAddress = new InternetAddress("jihongshuai547@163.com");
        InternetAddress fromAddress = new InternetAddress("txchangkun@126.com");
        message.setFrom(fromAddress);
        //7 设置接收者
        InternetAddress toAddress = new InternetAddress(to);
        // to 接收者   cc  抄送   bcc   暗送
        message.setRecipient(Message.RecipientType.TO, toAddress);
        //8 设置主题
        message.setSubject(subject);
        //9 设置内容
        //message.setText(text);
        message.setContent(text, "text/html;charset=utf-8");

        //10 坐火箭
        Transport transport = session.getTransport("smtp");//括号中的参数不能少
        // 连接邮箱
        // 第一个参数：邮箱
        // 第二个参数: 授权码
//        transport.connect("jihongshuai547@163.com", "jhslhh547");
//        transport.connect("2926429475@qq.com", "ck19970212");
        transport.connect("txchangkun@126.com", "19970212");
        // 发送
        // 第一个参数：邮件
        // 第二个参数：所有的接收者包括抄送者
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();

        System.out.println("ok");
    }

    public static void main(String[] args) {
        try {
            MailUtil.sendMail("1359926424@qq.com", "注册信息", "您已成功绑定邮箱!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
