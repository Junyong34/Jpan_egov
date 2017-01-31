package hpms.UserObject.Excel;
import java.util.*;
public class SheetInfo
{
    private String sheetname="";
    private int    sheetno =0;
    private String indataset ="";
    private int[] writerowno;
    private int   startcolumnindex =32;

    private String[] headtitle = null;
    private String filename ="";
    private ArrayList colname = null;
    public SheetInfo(String sheet, int no, String dsname, String gubun, int cindex)
    {
        sheetname = sheet;
        sheetno = no;
        indataset = dsname;
        getManageRowOrder(gubun);
        startcolumnindex = cindex;
    }

    public void setHeadTitle(String[] head)
    {
        headtitle = head;
    }
    public String[] getHeadTitle()
    {
        return headtitle;
    }

    public void setFilename(String fname, String[] colnm)
    {
        filename = fname;
        colname = new ArrayList();
        for(int i=0;i<colnm.length;i++)
        {
            colname.add(colnm[i]);
        }
    }
    public String getFilename()
    {
        return filename;
    }
    public ArrayList getColname()
    {
        return colname;
    }


    public String getSheetName()
    {
        return sheetname;
    }
    public int getSheetNo()
    {
        return sheetno;
    }
    public String getDatasetName()
    {
        return indataset;
    }
    public int[] getWriteRowNo()
    {
        return writerowno;
    }
    public int getStartColumnIndex()
    {
        return startcolumnindex;
    }
    private void getManageRowOrder(String gubun)
    {
        if(gubun.equals("IoT"))
        {
            writerowno = new int[28];
            writerowno[0] = 9;writerowno[1] = 10;writerowno[2] = 11;      //Demand
            writerowno[3] = 14;writerowno[4] = 15;writerowno[5] = 16;writerowno[6] = 17;  //Sales
            writerowno[7] = 18;writerowno[8] = 19;writerowno[9] = 21;     //Cost of Goods Sales
            writerowno[10] = 29;writerowno[11] = 30;writerowno[12] = 31;writerowno[13] = 32;writerowno[14] = 33;writerowno[15] = 34;  //R&D-Division Direct Expense
            writerowno[16] = 37;writerowno[17] = 38;writerowno[18] = 39;  //R&D-Segment Transfer Cost
            writerowno[19] = 42;writerowno[20] = 43;writerowno[21] = 44;writerowno[22] = 45;writerowno[23] = 46;writerowno[24] = 47;  // SG&A
            writerowno[25] = 49;   //Overseas Companies Profit
            writerowno[26] = 50;   //BU Transfer of Profit
            writerowno[27] = 54;   //R&D Support from Panasonic
        }
        else if(gubun.equals("OrgPlan"))
        {
            writerowno = new int[38];
            writerowno[0] = 168;  //R&D Summary
            writerowno[1] = 169;
            writerowno[2] = 170;
            writerowno[3] = 171;
            writerowno[4] = 172;
            writerowno[5] = 173;
            writerowno[6] = 174;
            writerowno[7] = 177;
            writerowno[8] = 178;
            writerowno[9] = 179;
            writerowno[10] = 180;
            writerowno[11] = 181;
            writerowno[12] = 184;
            writerowno[13] = 185;
            writerowno[14] = 194; //Mass Production and Sales
            writerowno[15] = 195;
            writerowno[16] = 196;
            writerowno[17] = 198;
            writerowno[18] = 200;
            writerowno[19] = 201;
            writerowno[20] = 203;
            writerowno[21] = 204;
            writerowno[22] = 206;
            writerowno[23] = 207;
            writerowno[24] = 208;
            writerowno[25] = 209;
            writerowno[26] = 210;
            writerowno[27] = 211;
            writerowno[28] = 212;
            writerowno[29] = 213;
            writerowno[30] = 218;
            writerowno[31] = 220;
            writerowno[32] = 221;
            writerowno[33] = 222;
            writerowno[34] = 225;
            writerowno[35] = 227;
            writerowno[36] = 232;
            writerowno[37] = 233;
        }
        else if(gubun.equals("mast"))
        {
        }
    }


}
