package egov.wizware.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import  java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.*;
import java.util.HashMap;
import java.util.*;
import  java.net.*;
import org.apache.commons.net.ftp.*;

public class Ftp  {
  public Ftp()
  {
  }

  public Ftp(String server) throws Exception {
      //super(server);
  }

  private boolean _isfindedPathIndex(String _dir){
    _dir = _dir.trim();
    if(_dir.charAt(_dir.length()-1) == '/') return true;
    return false;
  }


  public boolean _sputJnet(String _server,String _userid,String passwd,String mode, String _dest , String _srcdir, String _inputname) throws Exception
  {
    FTPClient client = null;
    boolean _rtn = true;
    FileOutputStream fos = null;
    FileInputStream  fis = null;
    try {
      client = new FTPClient();
      client.connect(_server);
      int replyCode = client.getReplyCode();
      System.out.println("replyCode :: " + replyCode);

      if(!FTPReply.isPositiveCompletion(replyCode))
      {
        client.disconnect();
        System.err.println("FTP server refused connection.");
      }
      else
      {
        boolean connect_stat = client.login(_userid, passwd); // id, pw
        System.out.println("login connect_stat :: " + connect_stat);

        if(mode.equals("0"))
        {
          client.setFileType(FTP.BINARY_FILE_TYPE);            //default FTP.ASCII_FILE_TYPE, FTP.ASCII_FILE_TYPE, FTP.EBCDIC_FILE_TYPE, FTP.IMAGE_FILE_TYPE , FTP.LOCAL_FILE_TYPE
        }
        else
        {
          client.setFileType(FTP.ASCII_FILE_TYPE);             //default FTP.ASCII_FILE_TYPE, FTP.ASCII_FILE_TYPE, FTP.EBCDIC_FILE_TYPE, FTP.IMAGE_FILE_TYPE , FTP.LOCAL_FILE_TYPE
        }
        client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);  //default FTP.STREAM_TRANSFER_MODE, FTP.BLOCK_TRANSFER_MODE, FTP.COMPRESSED_TRANSFER_MODE

        ArrayList _dlist = _getDirlist(_dest);
        if(!_dest.trim().equals(""))
        {
          if(_dest.trim().substring(0,1).equals("/"))
          {
            client.changeWorkingDirectory("/");
          }
        }
        boolean rtnVal = false;
        ArrayList _nlist =null;
        for(int x=0;x<_dlist.size();x++)
        {
          String _dirname = _dlist.get(x).toString();
          _nlist = _getLSNamelistJnet(client);
          if(_findname(_nlist, _dirname) == false){
            client.makeDirectory(_dirname);
            rtnVal = client.changeWorkingDirectory(_dirname);
          }else {
            rtnVal = client.changeWorkingDirectory(_dirname);
          }
        }
        System.out.println("cd domain : WorkingDirectory : " + client.printWorkingDirectory());
        client.enterLocalPassiveMode();  //client.enterLocalActiveMode(); // Set the current data connection mode to PASSIVE_LOCAL_DATA_CONNECTION_MODE .
        client.setSoTimeout(10000);  // 현재 커넥션 timeout을 10000밀리세컨드 설정.

        _srcdir = _srcdir.trim();
        if(_srcdir.equals(""))
        {
            System.out.println("Ftp:파일전송하기 위한 소스파일의 위치가 존재하지않습니다.");
            return false;
        }else   if(_srcdir.toCharArray()[_srcdir.length()-1] !='/')
        {
            _srcdir +="/";
        }

        File uploadFile1 = new File(_srcdir + _inputname);
        fis = new FileInputStream(uploadFile1);
        boolean isSuccess1 = client.storeFile(uploadFile1.getName(), fis);
        if(isSuccess1)
        {
          System.out.println("파일 업로드 성공");
        }
        else
        {
          System.out.println("파일 업로드 실패");
          _rtn = false;
        }
        System.out.println("-----------------------------------------");
        try
        {
          boolean chkout = client.logout();
          System.out.println("Logout status :: " + chkout);
        }catch(Exception ex){}
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw e;
    } finally {
      if(fis != null)
      {
        try
        {
          fis.close();
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
      }
      if(fos != null)
      {
        try {
          fos.close();
        } catch(Exception e) {
          e.printStackTrace();
        }
      }
      if(client != null || client.isConnected())
      {
        try
        {
          client.disconnect();
        } catch(Exception e)
        {
          e.printStackTrace();
        }
      }
//      try
//      {
//          closeServer();
//      }catch(Exception ex)
//      {
//      }
    }
    return _rtn;
  }
  public boolean _sgetJnet(String _server,String _userid,String passwd,String mode, String _dest , String _srcdir, String _inputname) throws Exception
  {
    FTPClient client = null;
    boolean _rtn = false;
    FileOutputStream fos = null;
    FileInputStream  fis = null;
    try {
      client = new FTPClient();
      client.connect(_server);
      int replyCode = client.getReplyCode();
      System.out.println("replyCode :: " + replyCode);

      if(!FTPReply.isPositiveCompletion(replyCode))
      {
        client.disconnect();
        System.err.println("FTP server refused connection.");
      }
      else
      {
        boolean connect_stat = client.login(_userid, passwd); // id, pw
        System.out.println("login connect_stat :: " + connect_stat);

        if(mode.equals("0"))
        {
          client.setFileType(FTP.BINARY_FILE_TYPE);
        }else {
          client.setFileType(FTP.ASCII_FILE_TYPE); //default FTP.ASCII_FILE_TYPE, FTP.ASCII_FILE_TYPE, FTP.EBCDIC_FILE_TYPE, FTP.IMAGE_FILE_TYPE , FTP.LOCAL_FILE_TYPE
        }

        client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); //default FTP.STREAM_TRANSFER_MODE, FTP.BLOCK_TRANSFER_MODE, FTP.COMPRESSED_TRANSFER_MODE
        ArrayList _dlist = _getDirlist(_dest);
        if(!_dest.trim().equals(""))
        {
          if(_dest.trim().substring(0,1).equals("/"))
          {
            client.changeWorkingDirectory("/");
          }
        }
        boolean rtnVal = false;
        ArrayList _nlist =null;
        for(int x=0;x<_dlist.size();x++)
        {
          String _dirname = _dlist.get(x).toString();
          System.out.println("_dirname:"+_dirname);
          _nlist = _getLSNamelistJnet(client);
          if(_findname(_nlist, _dirname) == false){
            client.makeDirectory(_dirname);
            rtnVal = client.changeWorkingDirectory(_dirname);
          }else {
            rtnVal = client.changeWorkingDirectory(_dirname);
          }
        }
        System.out.println("cd domain : WorkingDirectory : " + client.printWorkingDirectory());
        client.enterLocalPassiveMode();  //client.enterLocalActiveMode(); // Set the current data connection mode to PASSIVE_LOCAL_DATA_CONNECTION_MODE .
        client.setSoTimeout(10000);  // 현재 커넥션 timeout을 10000밀리세컨드 설정.

        _srcdir = _srcdir.trim();
        if(_srcdir.equals(""))
        {
            _srcdir="/";

        }else   if(_srcdir.toCharArray()[_srcdir.length()-1] !='/')
        {
            _srcdir +="/";
        }
        File uploadFile1 = new File(_srcdir + _inputname);
        fos = new FileOutputStream(uploadFile1);
        boolean isSuccess = client.retrieveFile(uploadFile1.getName(), fos);
        if(isSuccess) {
          System.out.println("파일 다운로드 성공");
          _rtn = true;
        } else {
          System.out.println("파일 다운로드 실패");
          _rtn = false;
        }
        System.out.println("-----------------------------------------");
        try {
           boolean chkout = client.logout();
           System.out.println("Logout status :: " + chkout);
         }catch(Exception ex){
         }
       }
     }
     catch(Exception e)
     {
       e.printStackTrace();
       throw e;
     } finally {
       if(fis != null)
       {
         try
         {
           fis.close();
         }
         catch(Exception e)
         {
           e.printStackTrace();
         }
       }
       if(fos != null)
       {
         try {
           fos.close();
         } catch(Exception e) {
           e.printStackTrace();
         }
       }
       if(client != null || client.isConnected())
       {
         try
         {
           client.disconnect();
         } catch(Exception e)
         {
           e.printStackTrace();
         }
       }
//       try
//       {
//           closeServer();
//       }catch(Exception ex)
//       {
//       }
     }
     return _rtn;
   }

  private ArrayList _getLSNamelistJnet(FTPClient client ) throws Exception
  {
      ArrayList _flist = new ArrayList();
      String [] names = client.listNames();
      for(int i=0;i<names.length;i++){
         // System.out.println(client.printWorkingDirectory()+":dirlist:"+names[i]);
          _flist.add(names[i]);
      }
      return _flist;
  }

//  public boolean _sget(String _server, String _userid, String passwd, String mode,  String _destx , String _srcdir, String _inputname ) {
//
//    boolean _rtn = false;
//
//    if(_isfindedPathIndex(_destx) == false) _destx = _destx +"/";
//    String _transFname = _srcdir + _inputname;
//
//    int i = 0;
//    TelnetInputStream tis = null;
//    FileOutputStream _outfile = null;
//    try {
//      login(_userid, passwd);
//      if(mode.equals("0")){
//          ascii();
//      }else {
//          binary();
//      }
//
//      _srcdir = _srcdir.trim();
//      cd(_srcdir);
//
//      tis = get(_transFname);
//      BufferedInputStream _dataInput = new BufferedInputStream(tis);
//      _outfile = new FileOutputStream(_destx+ _inputname);
//
//      byte[] b = new byte[1024];
//      int n = 0;
//      while((n = _dataInput.read(b)) != -1) {
//        _outfile.write(b,0,b.length);
//      }
//
//      Date date = new Date();
//      //System.out.println("서버에서 "+_inputname+"파일을 다운로드했습니다 "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");
//
//      return true;
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("during doGet Error : "+e.toString()); //writeLog("서버에서 "+_inputname+"파일을 다운로드중 에러가 발생했습니다.");
//      return false;
//    }finally{
//      try {
//        _outfile.close();
//        tis.close();
//        closeServer();
//      }catch(Exception ex){
//        ex.printStackTrace();
//      }
//    }
//  }

  private ArrayList _getDirlist(String _path) throws Exception{
      ArrayList _clist = new ArrayList();
      StringTokenizer _stoken = new StringTokenizer(_path, "/");
      String _str="";
      while(_stoken.hasMoreTokens()){
        _str =  _stoken.nextToken();
        if(_str.trim().equals("")) continue;
        _clist.add(_str);
      }
      return _clist;
    }
    private boolean _findname(ArrayList _flist, String _name) throws Exception{
      _name = _name.trim();
      for(int i=0;i<_flist.size();i++){
        if(_name.equals(_flist.get(i).toString())) return true;
      }
      return false;
    }
//    private ArrayList _getLSNamelist() throws Exception {
//      String _str="";
//      ArrayList _flist = new ArrayList();
//      TelnetInputStream _ls = list();
//      BufferedReader _br = new BufferedReader(new InputStreamReader(_ls));
//      while( (_str = _br.readLine()) != null){
//   //     System.out.println(_str.trim().substring(_str.trim().lastIndexOf(" ")).trim());
//        _flist.add(_str.trim().substring(_str.trim().lastIndexOf(" ")).trim());
//      }
//      return _flist;
//  }


//
//  public boolean _put(String server,String _userid,String passwd,String mode,
//                       String dest , String srcdir, String _inputname){
//    boolean _rtn = false;
//    String _transFname = srcdir + _inputname;
//    FtpClient fc=null;
//    String _outputname=_inputname;
//    int i = 0;
//
//    try {
//      fc = new FtpClient(server);
//      fc.login(_userid, passwd);
//      if(mode.equals("asc")){
//        fc.ascii();
//      }else {
//        fc.binary();
//      }
////      fc.cd("/TOTWEB/WEBTOP/fmtot/_upfile");
//      //------ _upload시  권한 허용여 부 확인 필요
//      FileInputStream _theFile = new FileInputStream(_transFname);
//      TelnetOutputStream fos = fc.put(_outputname);
//
//      while((i = _theFile.read()) != -1) {
//        //if(i != '\r') {
//          fos.write((byte)i);
//        //}
//      }
//      fos.close();
//      fc.closeServer();
//      _rtn=true;
//    } catch (IOException ee) {
//      ee.printStackTrace();
//      _rtn=false;
//    }
//    return _rtn;
//  }
//
//
//  public boolean _put(String server,String _userid,String passwd,String mode,
//                       String dest ,  String _inputname){
//    boolean _rtn = false;
//    String _transFname =  _inputname;
//    FtpClient fc=null;
//    String _outputname=_inputname.substring(_inputname.lastIndexOf("\\")+1,_inputname.length());
//    int i = 0;
//
//    try {
//      fc = new FtpClient(server);
//      fc.login(_userid, passwd);
//
//      if(mode.equals("asc")){
//        fc.ascii();
//      }else {
//        fc.binary();
//      }
//      fc.cd(dest);
//
//      FileInputStream _theFile = new FileInputStream(_transFname);
//      TelnetOutputStream fos = fc.put(_outputname);
//
//      while((i = _theFile.read()) != -1) {
//        if(i != '\r') {
//          fos.write((byte)i);
//        }
//      }
//      fos.close();
//      fc.closeServer();
//
//      _rtn=true;
//    } catch (IOException ee) {
//      System.err.println(ee);
//      _rtn=false;
//    }
//    return _rtn;
//  }



//
//  public void get(String _server, String _userid, String _passwd, ArrayList _flist, String _remoteloc, String _localloc){
//    FtpClient fc =null;
//    try {
//      fc = new FtpClient(_server);
//      fc.login(_userid, _passwd);
//      String _filename="";
//      for(int i=0;i<_flist.size();i++){
//        _filename = _flist.get(i).toString();
//        get(fc, _filename, _remoteloc,  _localloc);
//      }
//    }catch (Exception e){
//      e.printStackTrace();
//    }finally{
//      try{
//        fc.closeServer();
//      }catch(Exception e){
//      }
//    }
//  }




  private void writeLog(String msg) {
    String logFileName="";
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(logFileName, true));
      bw.write(msg, 0, msg.length());
      bw.close();
    } catch(Exception e) {
      System.out.println(e.toString());
    } //try

  } //writeLog


  public boolean _FileDelete(String _fname){
    boolean _rtn=false;
    java.io.File file = new java.io.File(_fname);
    _rtn=file.delete();

    return _rtn;
  }

  public boolean _FileRename(String _SrcName ,  String _reName ){
    boolean _rtn=false;
    java.io.File _srcname = new java.io.File(_SrcName);
    java.io.File rename = new java.io.File(_reName);
    _rtn=_srcname.renameTo(rename);

    return _rtn;
  }

  public HashMap _FIleMove(String targetdir,String _targetfname,String srcdir,String _srcfname){
    return null;
  }

  public String[] DirList(HashMap _param ,  int _gubun ){
    java.io.File _fname = new java.io.File("");
    String[] _flist=null;
    if(_fname.isDirectory()){
      _flist =_fname.list();
    }
    return _flist;
  }

  public ArrayList _FindFile(String targetdir,String target_fname ){
    java.io.File _fname = new java.io.File("");
    String[] _flist =_fname.list();
    ArrayList _foundfile=new ArrayList();
    boolean _rtn=false;

    for(int i=0;i<_flist.length;i++){
      if(_flist[i].equals("")){
         _foundfile.add(_flist[i]);
      }
    }

    return _foundfile;
  }


  public String _GetCurrentDate(String _wsFormat) {
    java.util.Date TodayDate = new java.util.Date();
    java.text.SimpleDateFormat yyyyMMddE = new java.text.SimpleDateFormat(_wsFormat);
    String _wsCurrentDate;
    _wsCurrentDate = yyyyMMddE.format(TodayDate);

    return _wsCurrentDate;

  }


  public DataOutputStream _dataAppendLineFile(DataOutputStream outStream,String s ){
    try {
    outStream.writeBytes(s+"\n");
    }catch(java.io.IOException e) {
      e.printStackTrace();
    }
    return outStream;
  }

  }



/*


   package egov.wizware.util;

  import java.io.DataOutputStream;
  import java.io.IOException;
  import java.util.ArrayList;
  import java.io.FileInputStream;
  import  java.io.FileOutputStream;
  import java.io.BufferedInputStream;
  import java.io.*;
  import sun.net.ftp.FtpClient;
  import java.util.HashMap;
  import sun.net.TelnetOutputStream;
  import sun.net.TelnetInputStream;
  import java.util.*;
  import  java.net.*;
  import org.apache.commons.net.ftp.*;

  public class Ftp extends FtpClient {
    public Ftp()
    {
    }

    public Ftp(String server) throws Exception {
        super(server);
    }

    private boolean _isfindedPathIndex(String _dir){
      _dir = _dir.trim();
      if(_dir.charAt(_dir.length()-1) == '/') return true;
      return false;
    }


    public boolean _sputJnet(String _server,String _userid,String passwd,String mode, String _dest , String _srcdir, String _inputname) throws Exception
    {
      FTPClient client = null;
      boolean _rtn = true;
      FileOutputStream fos = null;
      FileInputStream  fis = null;
      try {
        client = new FTPClient();
        client.connect(_server);
        int replyCode = client.getReplyCode();
        System.out.println("replyCode :: " + replyCode);

        if(!FTPReply.isPositiveCompletion(replyCode))
        {
          client.disconnect();
          System.err.println("FTP server refused connection.");
        }
        else
        {
          boolean connect_stat = client.login(_userid, passwd); // id, pw
          System.out.println("login connect_stat :: " + connect_stat);

          if(mode.equals("0"))
          {
            client.setFileType(FTP.BINARY_FILE_TYPE);            //default FTP.ASCII_FILE_TYPE, FTP.ASCII_FILE_TYPE, FTP.EBCDIC_FILE_TYPE, FTP.IMAGE_FILE_TYPE , FTP.LOCAL_FILE_TYPE
          }
          else
          {
            client.setFileType(FTP.ASCII_FILE_TYPE);             //default FTP.ASCII_FILE_TYPE, FTP.ASCII_FILE_TYPE, FTP.EBCDIC_FILE_TYPE, FTP.IMAGE_FILE_TYPE , FTP.LOCAL_FILE_TYPE
          }
          client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);  //default FTP.STREAM_TRANSFER_MODE, FTP.BLOCK_TRANSFER_MODE, FTP.COMPRESSED_TRANSFER_MODE

          ArrayList _dlist = _getDirlist(_dest);
          if(!_dest.trim().equals(""))
          {
            if(_dest.trim().substring(0,1).equals("/"))
            {
              client.changeWorkingDirectory("/");
            }
          }
          boolean rtnVal = false;
          ArrayList _nlist =null;
          for(int x=0;x<_dlist.size();x++)
          {
            String _dirname = _dlist.get(x).toString();
            _nlist = _getLSNamelistJnet(client);
            if(_findname(_nlist, _dirname) == false){
              client.makeDirectory(_dirname);
              rtnVal = client.changeWorkingDirectory(_dirname);
            }else {
              rtnVal = client.changeWorkingDirectory(_dirname);
            }
          }
          System.out.println("cd domain : WorkingDirectory : " + client.printWorkingDirectory());
          client.enterLocalPassiveMode();  //client.enterLocalActiveMode(); // Set the current data connection mode to PASSIVE_LOCAL_DATA_CONNECTION_MODE .
          client.setSoTimeout(10000);  // 현재 커넥션 timeout을 10000밀리세컨드 설정.

          _srcdir = _srcdir.trim();
          if(_srcdir.equals(""))
          {
              System.out.println("Ftp:파일전송하기 위한 소스파일의 위치가 존재하지않습니다.");
              return false;
          }else   if(_srcdir.toCharArray()[_srcdir.length()-1] !='/')
          {
              _srcdir +="/";
          }

          File uploadFile1 = new File(_srcdir + _inputname);
          fis = new FileInputStream(uploadFile1);
          boolean isSuccess1 = client.storeFile(uploadFile1.getName(), fis);
          if(isSuccess1)
          {
            System.out.println("파일 업로드 성공");
          }
          else
          {
            System.out.println("파일 업로드 실패");
            _rtn = false;
          }
          System.out.println("-----------------------------------------");
          try
          {
            boolean chkout = client.logout();
            System.out.println("Logout status :: " + chkout);
          }catch(Exception ex){}
        }
      }
      catch(Exception e)
      {
        e.printStackTrace();
        throw e;
      } finally {
        if(fis != null)
        {
          try
          {
            fis.close();
          }
          catch(Exception e)
          {
            e.printStackTrace();
          }
        }
        if(fos != null)
        {
          try {
            fos.close();
          } catch(Exception e) {
            e.printStackTrace();
          }
        }
        if(client != null || client.isConnected())
        {
          try
          {
            client.disconnect();
          } catch(Exception e)
          {
            e.printStackTrace();
          }
        }
        try
        {
            closeServer();
        }catch(Exception ex)
        {
        }
      }
      return _rtn;
    }
    public boolean _sgetJnet(String _server,String _userid,String passwd,String mode, String _dest , String _srcdir, String _inputname) throws Exception
    {
      FTPClient client = null;
      boolean _rtn = false;
      FileOutputStream fos = null;
      FileInputStream  fis = null;
      try {
        client = new FTPClient();
        client.connect(_server);
        int replyCode = client.getReplyCode();
        System.out.println("replyCode :: " + replyCode);

        if(!FTPReply.isPositiveCompletion(replyCode))
        {
          client.disconnect();
          System.err.println("FTP server refused connection.");
        }
        else
        {
          boolean connect_stat = client.login(_userid, passwd); // id, pw
          System.out.println("login connect_stat :: " + connect_stat);

          if(mode.equals("0"))
          {
            client.setFileType(FTP.BINARY_FILE_TYPE);
          }else {
            client.setFileType(FTP.ASCII_FILE_TYPE); //default FTP.ASCII_FILE_TYPE, FTP.ASCII_FILE_TYPE, FTP.EBCDIC_FILE_TYPE, FTP.IMAGE_FILE_TYPE , FTP.LOCAL_FILE_TYPE
          }

          client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); //default FTP.STREAM_TRANSFER_MODE, FTP.BLOCK_TRANSFER_MODE, FTP.COMPRESSED_TRANSFER_MODE
          ArrayList _dlist = _getDirlist(_dest);
          if(!_dest.trim().equals(""))
          {
            if(_dest.trim().substring(0,1).equals("/"))
            {
              client.changeWorkingDirectory("/");
            }
          }
          boolean rtnVal = false;
          ArrayList _nlist =null;
          for(int x=0;x<_dlist.size();x++)
          {
            String _dirname = _dlist.get(x).toString();
            System.out.println("_dirname:"+_dirname);
            _nlist = _getLSNamelistJnet(client);
            if(_findname(_nlist, _dirname) == false){
              client.makeDirectory(_dirname);
              rtnVal = client.changeWorkingDirectory(_dirname);
            }else {
              rtnVal = client.changeWorkingDirectory(_dirname);
            }
          }
          System.out.println("cd domain : WorkingDirectory : " + client.printWorkingDirectory());
          client.enterLocalPassiveMode();  //client.enterLocalActiveMode(); // Set the current data connection mode to PASSIVE_LOCAL_DATA_CONNECTION_MODE .
          client.setSoTimeout(10000);  // 현재 커넥션 timeout을 10000밀리세컨드 설정.

          _srcdir = _srcdir.trim();
          if(_srcdir.equals(""))
          {
              _srcdir="/";

          }else   if(_srcdir.toCharArray()[_srcdir.length()-1] !='/')
          {
              _srcdir +="/";
          }
          File uploadFile1 = new File(_srcdir + _inputname);
          fos = new FileOutputStream(uploadFile1);
          boolean isSuccess = client.retrieveFile(uploadFile1.getName(), fos);
          if(isSuccess) {
            System.out.println("파일 다운로드 성공");
            _rtn = true;
          } else {
            System.out.println("파일 다운로드 실패");
            _rtn = false;
          }
          System.out.println("-----------------------------------------");
          try {
             boolean chkout = client.logout();
             System.out.println("Logout status :: " + chkout);
           }catch(Exception ex){
           }
         }
       }
       catch(Exception e)
       {
         e.printStackTrace();
         throw e;
       } finally {
         if(fis != null)
         {
           try
           {
             fis.close();
           }
           catch(Exception e)
           {
             e.printStackTrace();
           }
         }
         if(fos != null)
         {
           try {
             fos.close();
           } catch(Exception e) {
             e.printStackTrace();
           }
         }
         if(client != null || client.isConnected())
         {
           try
           {
             client.disconnect();
           } catch(Exception e)
           {
             e.printStackTrace();
           }
         }
         try
         {
             closeServer();
         }catch(Exception ex)
         {
         }
       }
       return _rtn;
     }

    private ArrayList _getLSNamelistJnet(FTPClient client ) throws Exception
    {
        ArrayList _flist = new ArrayList();
        String [] names = client.listNames();
        for(int i=0;i<names.length;i++){
           // System.out.println(client.printWorkingDirectory()+":dirlist:"+names[i]);
            _flist.add(names[i]);
        }
        return _flist;
    }

    public boolean _sput(String _server,String _userid, String passwd,String mode,  String _dest , String _srcdir, String _inputname) {

        boolean _rtn = false;
        if(_isfindedPathIndex(_srcdir) == false) _srcdir = _srcdir +"/";
        String _transFname = _srcdir + _inputname;
        String _outputname=_inputname;
        int i = 0;
        FileInputStream _theFile =null;
        TelnetOutputStream _fos = null;

        try {
          login(_userid, passwd);
          if(mode.equals("0")){
            ascii();
          }else {
            binary();
          }

          _dest = _dest.trim();
          if(_dest != null && !_dest.equals("") && !_dest.equals("/")){
            String _dirname="";
            ArrayList _nlist = null;
            ArrayList _dlist = _getDirlist(_dest);
            for(int x=0;x<_dlist.size();x++){
              _dirname = _dlist.get(x).toString();
              _nlist = _getLSNamelist();

              if(_findname(_nlist, _dirname) == false){
                issueCommand("MKD " +_dirname);
                cd(_dirname);
              }else {
                cd(_dirname);
              }
            }
          }

          _theFile = new FileInputStream(_transFname);
          _fos = put(_outputname);
          while((i = _theFile.read()) != -1)
          {
            //if(i != '\r')
            //{
              _fos.write((byte)i);
            //}
          }
          _rtn=true;
        }
        catch (Exception ee)
        {
          ee.printStackTrace();
          _rtn=false;
        }finally{
          try {
            _fos.close();
            closeServer();
          }catch(Exception ex){
            ex.printStackTrace();
          }
        }

        return _rtn;
    }
    public boolean _sget(String _server, String _userid, String passwd, String mode,  String _destx , String _srcdir, String _inputname ) {

      boolean _rtn = false;

      if(_isfindedPathIndex(_destx) == false) _destx = _destx +"/";
      String _transFname = _srcdir + _inputname;

      int i = 0;
      TelnetInputStream tis = null;
      FileOutputStream _outfile = null;
      try {
        login(_userid, passwd);
        if(mode.equals("0")){
            ascii();
        }else {
            binary();
        }

        _srcdir = _srcdir.trim();
        cd(_srcdir);

        tis = get(_transFname);
        BufferedInputStream _dataInput = new BufferedInputStream(tis);
        _outfile = new FileOutputStream(_destx+ _inputname);

        byte[] b = new byte[1024];
        int n = 0;
        while((n = _dataInput.read(b)) != -1) {
          _outfile.write(b,0,b.length);
        }

        Date date = new Date();
        //System.out.println("서버에서 "+_inputname+"파일을 다운로드했습니다 "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");

        return true;
      } catch(Exception e) {
        e.printStackTrace();
        System.out.println("during doGet Error : "+e.toString()); //writeLog("서버에서 "+_inputname+"파일을 다운로드중 에러가 발생했습니다.");
        return false;
      }finally{
        try {
          _outfile.close();
          tis.close();
          closeServer();
        }catch(Exception ex){
          ex.printStackTrace();
        }
      }

    }
    private ArrayList _getDirlist(String _path) throws Exception{
        ArrayList _clist = new ArrayList();
        StringTokenizer _stoken = new StringTokenizer(_path, "/");
        String _str="";
        while(_stoken.hasMoreTokens()){
          _str =  _stoken.nextToken();
          if(_str.trim().equals("")) continue;
          _clist.add(_str);
        }
        return _clist;
      }
      private boolean _findname(ArrayList _flist, String _name) throws Exception{
        _name = _name.trim();
        for(int i=0;i<_flist.size();i++){
          if(_name.equals(_flist.get(i).toString())) return true;
        }
        return false;
      }
      private ArrayList _getLSNamelist() throws Exception {
        String _str="";
        ArrayList _flist = new ArrayList();
        TelnetInputStream _ls = list();
        BufferedReader _br = new BufferedReader(new InputStreamReader(_ls));
        while( (_str = _br.readLine()) != null){
     //     System.out.println(_str.trim().substring(_str.trim().lastIndexOf(" ")).trim());
          _flist.add(_str.trim().substring(_str.trim().lastIndexOf(" ")).trim());
        }
        return _flist;
    }



    public boolean _put(String server,String _userid,String passwd,String mode,
                         String dest , String srcdir, String _inputname){
      boolean _rtn = false;
      String _transFname = srcdir + _inputname;
      FtpClient fc=null;
      String _outputname=_inputname;
      int i = 0;

      try {
        fc = new FtpClient(server);
        fc.login(_userid, passwd);
        if(mode.equals("asc")){
          fc.ascii();
        }else {
          fc.binary();
        }
//      fc.cd("/TOTWEB/WEBTOP/fmtot/_upfile");
        //------ _upload시  권한 허용여 부 확인 필요
        FileInputStream _theFile = new FileInputStream(_transFname);
        TelnetOutputStream fos = fc.put(_outputname);

        while((i = _theFile.read()) != -1) {
          //if(i != '\r') {
            fos.write((byte)i);
          //}
        }
        fos.close();
        fc.closeServer();
        _rtn=true;
      } catch (IOException ee) {
        ee.printStackTrace();
        _rtn=false;
      }
      return _rtn;
    }


    public boolean _put(String server,String _userid,String passwd,String mode,
                         String dest ,  String _inputname){
      boolean _rtn = false;
      String _transFname =  _inputname;
      FtpClient fc=null;
      String _outputname=_inputname.substring(_inputname.lastIndexOf("\\")+1,_inputname.length());
      int i = 0;

      try {
        fc = new FtpClient(server);
        fc.login(_userid, passwd);

        if(mode.equals("asc")){
          fc.ascii();
        }else {
          fc.binary();
        }
        fc.cd(dest);

        FileInputStream _theFile = new FileInputStream(_transFname);
        TelnetOutputStream fos = fc.put(_outputname);

        while((i = _theFile.read()) != -1) {
          if(i != '\r') {
            fos.write((byte)i);
          }
        }
        fos.close();
        fc.closeServer();

        _rtn=true;
      } catch (IOException ee) {
        System.err.println(ee);
        _rtn=false;
      }
      return _rtn;
    }




    public void get(String _server, String _userid, String _passwd, ArrayList _flist, String _remoteloc, String _localloc){
      FtpClient fc =null;
      try {
        fc = new FtpClient(_server);
        fc.login(_userid, _passwd);
        String _filename="";
        for(int i=0;i<_flist.size();i++){
          _filename = _flist.get(i).toString();
          get(fc, _filename, _remoteloc,  _localloc);
        }
      }catch (Exception e){
        e.printStackTrace();
      }finally{
        try{
          fc.closeServer();
        }catch(Exception e){
        }
      }
    }

    public FtpClient getConnection(String _server, String _userid, String _passwd){
        FtpClient fc =null;
      try {
        fc = new FtpClient(_server);
        fc.login(_userid, _passwd);

      }catch (Exception e){
        e.printStackTrace();
      }
      return fc;
    }

    public boolean get(FtpClient fc, String fileName, String _remoteloc,  String _localloc ,String _type) {   // default가 binary로 전송 처리 한다.
        try {
            fc.cd(_remoteloc);
            if(_type.equals("asc")){
                fc.ascii();
            }else{
                fc.binary();
            }
            TelnetInputStream tis = fc.get(fileName);
            BufferedInputStream dataInput = new BufferedInputStream(tis);
            FileOutputStream outfile = new FileOutputStream(_localloc+"/"+fileName);

            byte[] b = new byte[1024];
            int n = 0;
            while((n = dataInput.read(b)) != -1) {
                outfile.write(b,0,b.length);
            }
            outfile.close();
            Date date = new Date();
            writeLog("서버에서 "+fileName+"파일을 다운로드했습니다 "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");
            return true;
        } catch(Exception e) {
            System.out.println("during doGet Error : "+e.toString());
            writeLog("서버에서 "+fileName+"파일을 다운로드중 에러가 발생했습니다.");
            return false;
        }
    }

    public boolean get(FtpClient fc, String fileName, String _localloc, String type) {
      try {
        if(type.equals("asc")){
          fc.ascii();
        }else{
          fc.binary();
        }
        TelnetInputStream tis = fc.get(fileName);
        BufferedInputStream dataInput = new BufferedInputStream(tis);
        FileOutputStream outfile = new FileOutputStream(_localloc +"/"+ fileName);

        byte[] b = new byte[1024];
        int n = 0;
        while((n = dataInput.read(b)) != -1) {
          outfile.write(b,0,b.length);
        }
        outfile.close();
        Date date = new Date();
        writeLog("서버에서 "+fileName+"파일을 다운로드했습니다 "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");
        return true;
      } catch(Exception e) {
        System.out.println("during doGet Error : "+e.toString());
        writeLog("서버에서 "+fileName+"파일을 다운로드중 에러가 발생했습니다.");
        return false;
      }
    }

    public boolean getX(String fileName) {
      String server="";
      String _userid="";
      String passwd="";
      try {
        FtpClient fc = new FtpClient(server);
        fc.login(_userid, passwd);

        TelnetInputStream tis = fc.get(fileName);
        BufferedInputStream dataInput = new BufferedInputStream(tis);
        FileOutputStream outfile = new FileOutputStream("location" +"/"+fileName);

        byte[] b = new byte[1024];
        int c = 0;
        while((c =dataInput.read(b)) != -1) {
          outfile.write(b,0,c);
        } //end while

        outfile.close();
        Date date = new Date();
        writeLog("서버에서 "+fileName+"파일을 다운로드했습니다 "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");
        return true;
      } catch(Exception e) {
        System.out.println("during doGet Error : "+e.toString());
        writeLog("서버에서 "+fileName+"파일을 다운로드중 에러가 발생했습니다.");
        return false;
      }
    }

    public boolean get(String fileName,String type) {
      String server="";
      String _userid="";
      String passwd="";

      try {
        FtpClient fc = new FtpClient(server);
        fc.login(_userid, passwd);
        if(type.equals("asc")){
          fc.ascii();
        }else{
          fc.binary();
        }

        TelnetInputStream tis = fc.get(fileName);
        BufferedInputStream dataInput = new BufferedInputStream(tis);
        FileOutputStream outfile = new FileOutputStream("localPath"+"/"+fileName);

        byte[] b = new byte[1024];
        int n = 0;
        while((n = dataInput.read(b)) != -1) {
          outfile.write(b,0,b.length);
        } //end while

        outfile.close();
        Date date = new Date();
        writeLog("서버에서 "+fileName+"파일을 다운로드했습니다 "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");
        return true;
      } catch(Exception e) {
        System.out.println("during doGet Error : "+e.toString());
        writeLog("서버에서 "+fileName+"파일을 다운로드중 에러가 발생했습니다.");
        return false;
      } //try
    }



    private void writeLog(String msg) {
      String logFileName="";
      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(logFileName, true));
        bw.write(msg, 0, msg.length());
        bw.close();
      } catch(Exception e) {
        System.out.println(e.toString());
      } //try

    } //writeLog


    public boolean _FileDelete(String _fname){
      boolean _rtn=false;
      java.io.File file = new java.io.File(_fname);
      _rtn=file.delete();

      return _rtn;
    }

    public boolean _FileRename(String _SrcName ,  String _reName ){
      boolean _rtn=false;
      java.io.File _srcname = new java.io.File(_SrcName);
      java.io.File rename = new java.io.File(_reName);
      _rtn=_srcname.renameTo(rename);

      return _rtn;
    }

    public HashMap _FIleMove(String targetdir,String _targetfname,String srcdir,String _srcfname){
      return null;
    }

    public String[] DirList(HashMap _param ,  int _gubun ){
      java.io.File _fname = new java.io.File("");
      String[] _flist=null;
      if(_fname.isDirectory()){
        _flist =_fname.list();
      }
      return _flist;
    }

    public ArrayList _FindFile(String targetdir,String target_fname ){
      java.io.File _fname = new java.io.File("");
      String[] _flist =_fname.list();
      ArrayList _foundfile=new ArrayList();
      boolean _rtn=false;

      for(int i=0;i<_flist.length;i++){
        if(_flist[i].equals("")){
           _foundfile.add(_flist[i]);
        }
      }

      return _foundfile;
    }


    public String _GetCurrentDate(String _wsFormat) {
      java.util.Date TodayDate = new java.util.Date();
      java.text.SimpleDateFormat yyyyMMddE = new java.text.SimpleDateFormat(_wsFormat);
      String _wsCurrentDate;
      _wsCurrentDate = yyyyMMddE.format(TodayDate);

      return _wsCurrentDate;

    }


    public DataOutputStream _dataAppendLineFile(DataOutputStream outStream,String s ){
      try {
      outStream.writeBytes(s+"\n");
      }catch(java.io.IOException e) {
        e.printStackTrace();
      }
      return outStream;
    }




    }



*/






