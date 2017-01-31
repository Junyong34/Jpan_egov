package egov.wizware.ctl;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import egov.wizware.com.*;
import egov.wizware.dao.*;
import egov.wizware.ria.*;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.Element;

public class ClassRunningServlet  extends HttpServlet
{
    private static final String CONTENT_TYPE = "text/html; charset=euc-kr";
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }
    public StringBuffer dispRequest(HttpServletRequest req)
    {
        System.out.println("======DISPLAY REQUEST INPUT xxxxxx=====");
        StringBuffer inbuf = new StringBuffer(10000);
        try
        {
            DataInputStream in = new DataInputStream(req.getInputStream());
            String str="";
            int i = 0;
            while ( (str = in.readLine()) != null)
            {
                System.out.println(str);
                inbuf.append(str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return inbuf;
    }

    public void doGet(HttpServletRequest requesta, HttpServletResponse responsea) throws  ServletException, IOException
    {
        doPost(requesta, responsea);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException
    {
      //req.setCharacterEncoding("UTF-8");
      HashMap target = null;
      DOBJ dobj=null;

      try
      {
          dobj=_getData(req.getInputStream()); // 입력 데이터셋
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }

      target= dobj.getParam();
      //dobj.getParam().put("SERVICE",callname.substring(0, callname.indexOf(",")));
      //dobj.getParam().put("METHOD",callname.substring(callname.indexOf(",")+1));


      VOBJ info  = null;
      try
      {
          TimeInterval tinteval = new TimeInterval();

          dobj = this.NonejbExecute(dobj, target);

          //------------------------------
          dobj.dispRetObjectKeyNames();
          //-------------------------------


          StringBuffer data = new StringBuffer();
          forwardx( resp, data);
          tinteval.setEndTime();

      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
      finally
      {
      }
  }

  public DOBJ NonejbExecute(DOBJ dobj, HashMap ctl)
  {
      String objectname = ctl.get("SERVICE").toString() ;
      String methodname = ctl.get("METHOD").toString();
      Class commandClass = null;
      try
      {
          commandClass = Class.forName( objectname );
      }
      catch (ClassNotFoundException ex)
      {
          ex.printStackTrace();
      }

      Object arg[] = new Object[1];
      Class[] cls = new Class[1];
      cls[0] = dobj.getClass();
      arg[0] = dobj;
      DOBJ ret = null;
      try
      {
          Method method = commandClass.newInstance().getClass().getMethod(methodname, cls);
          ret = (DOBJ) method.invoke(commandClass.newInstance(),arg);
          return ret;
      }
      catch (Exception e)
      {
          e.printStackTrace();
          ret = new DOBJ();
          ret.setRtncode(-500);
          ret.setRetmsg(e.getMessage());
          return ret;
      }
  }


  private VOBJ _getDataVobj(String _data)
  {
      VOBJ vobj = null;
      String cdelim="";
      String dsname ="";
      int    columncnt =0;
      StringTokenizer _stoken = new StringTokenizer(_data, cdelim);
      dsname = _stoken.nextToken();   // S;4
      columncnt  = Integer.parseInt(dsname.substring(dsname.indexOf(";")+1));
      dsname  = dsname.substring(0, dsname.indexOf(";"));
      vobj = new VOBJ();
      vobj.setName(dsname);

      int idx=0;
      int cpint=0;
      String _str ="";
      String _cname="";
      HashMap _rec = null;
      while(_stoken.hasMoreTokens())
      {
          if(idx ==0)
          {
              _rec = new HashMap();
          }
          _str = _stoken.nextToken();
          cpint = _str.indexOf("=");
          _rec.put(_str.substring(0,cpint), _str.substring(cpint+1));
          idx++;
          if( idx == columncnt)
          {
              idx =0;
              vobj.addRecord(_rec);
          }
      }

      vobj.Println("----- input ------");
      return vobj;
  }
  private  DOBJ _getData(InputStream in)
  {
      //전송Layout :
      //[호출대상서비스,MEOTHD][DATASET;컬럼갯수][컬럼=값][컬럼=값][컬럼=값][DATASET;컬럼갯수][컬럼=값][컬럼=값][컬럼=값]
      //호출대상서비스,METHODS;4NAME=AANAME=AANAME=AANAME=AAS;4NAME=AANAME=AANAME=AANAME=AA
      DOBJ dobj = new DOBJ();
      VOBJ vobj = null;
      StringBuffer _sbuf = new StringBuffer(50000);
      String ddelim  ="";
      String cdelim  ="";
      String callname="";

      try
      {
          String _linestr="";
          InputStreamReader inreader = new InputStreamReader(in, "UTF-8");
          BufferedReader _bread = new BufferedReader(inreader);
          while( (_linestr = _bread.readLine()) != null)
          {
              _sbuf.append(_linestr);
          }

          System.out.println("INPUT-STR:" + _sbuf.toString());

          StringTokenizer _stoken = new StringTokenizer(_sbuf.toString(), ddelim);
          callname = _stoken.nextToken();
          while(_stoken.hasMoreTokens())
          {
              vobj = _getDataVobj(_stoken.nextToken());
              dobj.setRetObject(vobj);
          }

          HashMap map = new HashMap();
          map.put("SERVICE",callname.substring(0, callname.indexOf(",")));
          map.put("METHOD",callname.substring(callname.indexOf(",")+1));

          dobj.setParam(map);
      }
      catch(Exception je)
      {
          je.printStackTrace();
      }
      return dobj;
  }


  public void forwardx(HttpServletResponse res, StringBuffer data)
  {
      try
      {
          res.setContentType("text/plain; charset=UTF-8");
          PrintWriter out = new PrintWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8"), false);
          out.write(data.toString());
          out.flush();
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }


}
