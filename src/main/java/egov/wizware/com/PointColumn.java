package egov.wizware.com;

public class PointColumn{
    short x;
    int   y;
    String name = null;
    boolean kor = false;
    public  PointColumn(){
    }
    public PointColumn(short x, int y,String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public short getX(){
        return x;
    }
    public void setX(String x){
        this.x = Short.parseShort(x);
    }
    public int   getY(){
        return y;
    }
    public void setY(String y){
        this.y = Integer.parseInt(y);
    }
    public String  getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public boolean  _5162(){
        return kor;
    }
    public void _1763(String name){
        if(name.indexOf("kor")!= -1) this.kor = true;
    }

}
