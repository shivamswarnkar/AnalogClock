/*Shivam Swarnkar HW5
 */
package hw5;

import java.awt.*;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author RAMNARAYAN
 */
public class HW5 {

    /**
     * @param args the command line arguments
 /**
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("Analog Clock");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        GraphicsPanel gp= new GraphicsPanel();
        new UpdatePanel(gp).start();
        jf.add(gp);
        jf.setVisible(true);
        
        
    }
    
}

class UpdatePanel extends Thread{
    GraphicsPanel jp;
    UpdatePanel(GraphicsPanel newjp){jp=newjp;}
    @Override
    public void run(){
        try{
            while(true){
                jp.repaint();
                sleep(50);
            }
        }
        catch(Exception e){}
    }
}

class Point{
    double x;
    double y;
}
class GraphicsPanel extends JPanel{
    
    Point center;
    Point sec;
    Point min;
    Point hour;
    double r;
    GraphicsPanel(){
            center = new Point();
            sec = new Point();
            min = new Point();
            hour = new Point();
            r=0;
    }
  
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        center.x = getSize().getHeight()/2;
        center.y = getSize().getWidth()/2;
        
        r = center.x/2;


        
        Calendar c = Calendar.getInstance();
        
        int second = c.get(Calendar.SECOND);
        sec.x = center.x-r*Math.cos(Math.toRadians(90+((second%60)*6)));
        sec.y = center.y-r*Math.sin(Math.toRadians(90+((second%60)*6)));
        
        
        int minute = c.get(Calendar.MINUTE);
        min.x = center.x-r*Math.cos(Math.toRadians(90+((minute%60)*6)));
        min.y = center.y-r*Math.sin(Math.toRadians(90+((minute%60)*6)));
        
        int curr_hour =c.get(Calendar.HOUR_OF_DAY)%12;
        hour.x = (center.x)-((r/2)*Math.cos(Math.toRadians(90+((curr_hour%12)*30))));
        hour.y = (center.y)-((r/2)*Math.sin(Math.toRadians(90+((curr_hour%12)*30))));
       
               
                
        
        int x = (int)getSize().getHeight()/2;
        int y = (int)getSize().getWidth()/2;
        g.setColor(Color.WHITE);
        g.fillOval(x-((x+10)/2), y-((x+10)/2), x+10,x+10);
        
        g.setColor(Color.GREEN);
        g.drawLine(x, y, (int)sec.x, (int)sec.y);
        g.setColor(Color.orange);
        g.drawLine(x, y, (int)min.x, (int)min.y);
        g.setColor(Color.RED);
        
        g.drawString("12",x-2,(int)(y-r+7));
        g.drawString("3", (int)(x+r),y+2);
        g.drawString("6",x-2 ,(int)(y+r+7));
        g.drawString("9",(int)(x-r),y+2);
        
        g.setColor(Color.MAGENTA);
        g.drawLine(x, y, (int)hour.x, (int)hour.y);
        g.setColor(Color.BLUE);
        g.drawString(""+curr_hour+":"+minute+":"+second, x, y); 
        
    }    
}
