package hpms.UserObject.Excel;
import java.util.*;

public class MenuInfo {

    private ArrayList mlist = new ArrayList();
    public MenuInfo()
    {
        setMenuInfo();
    }
    public String getPBMenuid(String menuid)
    {
        String rtn="";
        IDInfo info = null;
        for(int i=0;i<mlist.size();i++)
        {
            info = (IDInfo) mlist.get(i);
            if(info.getMenuID().equals(menuid))
            {
                return info.getPBMenuID();
            }
        }
        return rtn;
    }
    public String getMenuid(String pbmenuid)
    {
        String rtn="";
        IDInfo info = null;
        for(int i=0;i<mlist.size();i++)
        {
            info = (IDInfo) mlist.get(i);
            if(info.getPBMenuID().equals(pbmenuid))
            {
                return info.getMenuID();
            }
        }
        return rtn;
    }
    private void setMenuInfo()
    {
        IDInfo info1 = new IDInfo("2016082417883","10. PID관리","M000100","PID Numbering");
        IDInfo info2 = new IDInfo("2016082417883","10. PID관리","M000110","SubPID Numbering");
        IDInfo info3 = new IDInfo("20160902161145","11. PID 목록","M000120","PID List");

        mlist.add(info1);
        mlist.add(info2);
        mlist.add(info3);
    }
    private class IDInfo
    {
        private String pbmenuId="";
        private String pbmenuName="";
        private String menuId ="";
        private String menuName ="";

        public IDInfo(String pbmid, String pbmnm, String mid, String mnm)
        {
            pbmenuId = pbmid;
            pbmenuName = pbmnm;
            menuId = mid;
            menuName = mnm;
        }
        public String getPBMenuID()
        {
            return pbmenuId;
        }
        public String getMenuID()
        {
            return menuId;
        }
        public String getPBMenuName()
        {
            return pbmenuName;
        }
        public String getMenuName()
        {
            return menuName;
        }
    }

}
