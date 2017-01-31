package egov.wizware.com;

public class ColumnHeadInfo implements java.io.Serializable {
    private String typename="";
    private String name="";
    private int    type=0;
    private int    length=0;     // ����
    private int    numbersize=0; //��ġũ��
    private int    roundsize=0;  //�Ҽ��� ���� ũ��
    public ColumnHeadInfo() {
    }
    public void setTypename(String name){
        typename = name;
    }
    public String getTypename(){
        return typename;
    }
    public void setName(String s){
        name = s;
    }
    public String getName(){
        return name;
    }
    public void setType(int s){
        type = s;
    }
    public int getType(){
        return type;
    }
    public void setLength(int s){
        //System.out.println("Length:" + s);
        length = s;
    }
    public int getLength(){
        return length;
    }
    public void setNumbersize( int s){
        //System.out.println("setNumbersize:" + s);
        numbersize = s;
    }
    public int getNumbersize(){
        return numbersize;
    }
    public void setRoundsize( int s){
        //System.out.println("setRoundsize:" + s);
        roundsize = s;
    }
    public int getRoundsize(){
        return roundsize;
    }
}
