package com.czxy.fore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author likk
 * @create 2018/9/12
 */
@RestController
public class CheckCodeController {

    @RequestMapping("checkCode")
    public ResponseEntity<Void> checkCode(HttpServletResponse response, HttpSession session){
        try {
            int width = 80;
            int height = 32;
            //create the image
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            // set the background color
            g.setColor(new Color(0xDCDCDC));
            g.fillRect(0, 0, width, height);
            // draw the border
            g.setColor(Color.black);
            g.drawRect(0, 0, width - 1, height - 1);
            // create a random instance to generate the codes
            Random rdm = new Random();
            String hash1 = Integer.toHexString(rdm.nextInt());
            // make some confusion
            for (int i = 0; i < 50; i++) {
                int x = rdm.nextInt(width);
                int y = rdm.nextInt(height);
                g.drawOval(x, y, 0, 0);
            }
            // generate a random code
            String capstr = hash1.substring(0, 4);
            session.setAttribute("key", capstr);
            g.setColor(new Color(0, 100, 0));
            g.setFont(new Font("Candara", Font.BOLD, 24));
            g.drawString(capstr, 8, 24);
            g.dispose();
            response.setContentType("image/jpeg");
            OutputStream strm = response.getOutputStream();
            ImageIO.write(image, "jpeg", strm);
            strm.close();

            return ResponseEntity.ok().build();
        } catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
