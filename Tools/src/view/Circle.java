package view;

import java.awt.*;

public class Circle {
    private int x;
    private int y;
    private int diameter;
    private final Color BLANK_COLOR;
    private final Color SET_COLOR;
    private boolean flag;

    //////////////////////// Constructors //////////////////////////
    public Circle(int x,int y, int diameter, Color setColor){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.SET_COLOR = setColor;
        this.BLANK_COLOR = Color.white;
        this.flag = false;
    }
    public Circle(int x,int y, int diameter,Color blankColor, Color setColor){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.SET_COLOR = setColor;
        this.BLANK_COLOR = blankColor;
        this.flag = false;
    }

    //////////////////////// Methods //////////////////////////
    public void draw(Graphics g){
        if(flag)
            g.setColor(SET_COLOR);
        else g.setColor(BLANK_COLOR);
        g.fillOval(x,y,diameter,diameter);
        g.setColor(new Color(141, 87, 0));
        g.drawOval(x,y,diameter,diameter);

    }

    ////////////////////////  Setters & Getters //////////////////////////
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    public int getY(){
        return this.y;
    }
    public int getX(){
        return this.x;
    }
    public int getDiameter(){
        return this.diameter;
    }
}
