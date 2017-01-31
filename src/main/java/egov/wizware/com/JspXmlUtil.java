package egov.wizware.com;
import java.util.*;
public class JspXmlUtil {
    public JspXmlUtil()
    {
    }

    //--------------------------------Return Void : 결과 코드와 Message만 전송처리 한다 ------------------------
    //<root>
    public StringBuffer getOutXmlData(DOBJ dobj)
    {
        StringBuffer sbuf = new StringBuffer(100000);
        sbuf.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?> \n");
        sbuf.append("<root> \n");
        sbuf.append("<code value=\""+dobj.getRtncode()+"\" /> \n");
        sbuf.append("<retmsg value=\""+dobj.getRetmsg()+"\" /> \n");
        ArrayList alist = dobj.getRetObjectKeys();
        VOBJ vobj = null;
        for(int i=0;i<alist.size();i++)
        {
           vobj = dobj.getRetObject(alist.get(i).toString());
           if(vobj.getName().trim().equals(""))
           {
               continue;
           }
           sbuf.append(getXMLVobj(vobj));
        }
        sbuf.append("</root>");
        return sbuf;
    }
    private String getXMLVobj(VOBJ vobj)
    {
        ArrayList _clist = vobj.getColumnNames();
        StringBuffer sbuf = new StringBuffer(100000);
        String colnames="";
        for(int i=0;i<vobj.getColumnNames().size();i++)
        {
            if(i==0) {
                colnames += vobj.getColumnNames().get(i).toString();
                continue;
            }
            colnames += "," + vobj.getColumnNames().get(i).toString();
        }

        sbuf.append("<"+vobj.getName()+" totalcnt='"+vobj.getRecordCnt()+"' colnames='"+colnames+"' " + "> \n");
        vobj.first();
        while(vobj.next()){
            sbuf.append("  <row seq=\'"+vobj.current+"\'> ");
            for(int i=0;i<_clist.size();i++)
            {
                sbuf.append("\n     <"+_clist.get(i).toString()+">");
                sbuf.append(_cvtChar(vobj.getRecord().get(_clist.get(i).toString())));
                sbuf.append("</"+_clist.get(i).toString()+">");
            }
            sbuf.append("\n  </row> \n");
        }
        sbuf.append("</"+vobj.getName()+"> \n");
        return sbuf.toString();
    }

    //--------------------------------Return Void : 결과 코드와 Message만 전송처리 한다 ------------------------
    public StringBuffer getMsgCode(DOBJ dobj)
    {
        StringBuffer sbuf= new StringBuffer();
        int code = dobj.getRtncode();
        String msg = dobj.getRetmsg();
        sbuf.append(code +":");
        sbuf.append(msg);
        return sbuf;
    }

    //--------------------------------SELECT BOX START-------------------------------------------------------
    //"<option value='1'>AAAAAAAAAAAA</option><option value='2'>BBBBBB</option><option value='3'>CCCCCCCCCCCCC</option>"
    public StringBuffer getSelectBox(DOBJ dobj, String codename, String dispname)
    {
        StringBuffer sbuf= new StringBuffer();
        ArrayList alist = dobj.getRetObjectKeys();
        for(int i=0;i<alist.size();i++)
        {
            if(alist.get(i).toString().length() < 3)
            {
                continue;
            }
            sbuf = getSelectBoxText(dobj.getRetObject(alist.get(i).toString()),  codename,  dispname);
            break;
        }
        return sbuf;
    }
    private StringBuffer getSelectBoxText(VOBJ vobj, String codename, String dispname)
    {
        StringBuffer sbuf = new StringBuffer(500);
        vobj.first();
        while(vobj.next()){
            sbuf.append("<option value='"+_cvtChar(vobj.getRecord().get(codename))+"'>");
            sbuf.append(""+_cvtChar(vobj.getRecord().get(dispname))+"");
            sbuf.append("</option>");
        }
        return sbuf;
    }
    //--------------------------------SELECT BOX END-------------------------------------------------------



    private String _cvtChar(String data)
    {
         //& : &amp;   ,  < : &lt; ,  > : &gt;  , ' : &apos; , " : &quot;
         data = data.replaceAll("&","&amp;");
         data = data.replaceAll("<","&lt;");
         data = data.replaceAll(">","&gt;");
         data = data.replaceAll("'","&apos;");
         data = data.replaceAll("\"","&quot;");
         return data;
     }

}
