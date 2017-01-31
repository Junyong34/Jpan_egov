package egov.wizware.com;

import java.util.Properties;
import egov.wizware.com.*;
import java.net.URI;
import javax.servlet.http.HttpServlet;
import java.io.InputStream;
import javax.servlet.ServletContext;

public class CONFIG
{

  public CONFIG()
  {
  }

  public void dispCurrentDir()
  {
      try
      {
          java.io.File fp = new java.io.File(".");
          System.out.println("getPath:" +fp.getPath());
          System.out.println("getAbsolutePath:" +fp.getAbsolutePath());
          System.out.println("getAbsolutePath:" +fp.getCanonicalPath());
          System.out.println("getAbsolutePath:" +fp.getParent() +":=:" + fp.pathSeparatorChar +":=:" + fp.separatorChar );
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
  }

  public String getPathfinderHttpConfig(String _3986, ServletContext context) throws Exception
  {
    String _281 = "";
    Properties _2759 = new Properties();
    try
    {
      _2759.load( context.getResourceAsStream("/WEB-INF/WIZFRAME_v20.cfg"));
      _281 = _2759.getProperty(_3986);
      if(_281 == null) _281="";
    }catch(Exception e)
    {
      e.printStackTrace();
      throw new UException("WIZWARE CONFIG FILE READING ERROR:" + _3986 +":/WEB-INF/WIZFRAME_v20.cfg"  );
    }
    return _281.trim();
  }


  public String getPathfinderConfig(String _3986) throws Exception
  {
      String _5963="";
      _5963=System.getenv("PROCESSBUILDER_CFG");

      String _osname = System.getProperty("os.name").toUpperCase();
      if(_5963==null || _5963.equals(""))
      {
          System.out.println("PROCESSBUILDER_CFG:" + _5963);
          if(_osname.indexOf("WINDOW") != -1)
          {
              System.out.println("WINDOWS SYSTEM����:PROCESSBUILDER_CFG ���� ����");
          }
          else
          {
             System.out.println("UNIX SYSTEM����:PROCESSBUILDER_CFG ���� ����");
          }
      }
      else
      {
          _5963 = _5963 +"/"+ "WIZFRAME_v20.cfg";
      }

      String _281 = "";
      Properties _2759 = new Properties();
      try
      {
          _2759.load(new java.io.FileInputStream(_5963));
          _281 = _2759.getProperty(_3986);
          if(_281 == null) _281="";
      }
      catch(Exception e)
      {
          e.printStackTrace();
          throw new UException("PATHFINDER CONFIG FILE READING ERROR:::" + _3986);
      }
      return _281.trim();
  }

  public String getPBConfig(String _key) throws Exception
  {
    String fname="";
    //fname="./config/processbuilder_v20.cfg";
    String _5963="";
    fname=System.getenv("PROCESSBUILDER_CFG");

    String _osname = System.getProperty("os.name").toUpperCase();
    if(fname==null || fname.equals(""))
    {
        System.out.println("CONFIG ���� ���� ���� NOT FOUNDED:CONFIG-GETPBCONFIG");
    }
    else
    {
        fname = fname +"/"+ "processbuilder_v3.cfg";
    }
    String rtn = "";
    Properties _pp = new Properties();
    try
    {
      _pp.load(new java.io.FileInputStream(fname));
      rtn = _pp.getProperty(_key);
      if(rtn == null) rtn="";
    }catch(Exception e)
    {
      e.printStackTrace();
      throw new UException("PATHFINDER CONFIG FILE READING ERROR::" + _key);
    }
    return rtn.trim();
  }

  public String getPathServiceEnv(String _id) throws Exception    //���� XML ������ main(root)��ġ
  {
      String _rtn ="";

      _rtn=System.getenv("PROCESSBUILDER_XML_" + _id);
      System.out.println(_rtn+":XXXXXXXXX:"+"PROCESSBUILDER_XML_" + _id);
      return _rtn;
  }
  public String getWasConfigEnv(String _id) throws Exception      //was����
  {
      String _rtn ="";
      _rtn=System.getenv("PROCESSBUILDER_WAS_" + _id);
      //if(_rtn ==null || _rtn.trim().equals("")) return "jeus";
      return _rtn;
  }



  public String getPathfinderMsg(String _3986) throws Exception{
    String _3416 = this.getPathfinderConfig("SysMessage");
    String _281 = "";
    Properties _2759 = new Properties();
    try {
      _2759.load(new java.io.FileInputStream(_3416));
      _281 = _2759.getProperty(_3986);
      _281 = new String(_281.getBytes("8859_1"), "KSC5601");
    }catch(Exception e) {
      e.printStackTrace();
      throw new UException("PATHFINDER Message File Reading Error:" + _3986);
    }
    return Ltrim(_281.trim());
  }

  public String Ltrim(String _1073 ) {
    String _2366 ="";
    char[] _7172 = _1073.toCharArray();
    int i=0;
    for(i=0;i<_7172.length;i++){
      if(_7172[i] != ' '){
        break;
      }
    }
    for(int ii=i;ii<_7172.length;ii++){
      _2366 = _2366 + _7172[ii];
    }
    return _2366;
  }

}
