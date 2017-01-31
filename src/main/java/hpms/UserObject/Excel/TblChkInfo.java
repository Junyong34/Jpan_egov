package hpms.UserObject.Excel;

public class TblChkInfo {
    private String name ="";
    private int    length =0;

    public TblChkInfo(String nm, int len)
    {
        name = nm;
        length = len;
    }

    public String getName()
    {
        return name;
    }
    public int getLength()
    {
        return length;
    }

}
