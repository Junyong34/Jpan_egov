package egov.wizware.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.sql.*;

public class XFObject {
  private DOBJ     _dobj     = null;
  private String    _filename  ="";
  private String    _path      ="";
  private int       _createopt =0;  //0:create, 1:overwrite, 2:appendfirst, 3:appendlast
  private int       _errflag   =0;  //0:before status, 1:delete
  private VOBJ      _vobj      = null;
  private boolean   _containfilesize= false;

  public XFObject(DOBJ _dobjx) {
    _dobj = _dobjx;
  }
  public void setErrflag(int _err){
    _errflag = _err;
  }
  public void _setFileinfo(String _path, String _filename, String _opt, VOBJ _ivobj)
  {
    _path = _path.trim();
    if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";
    this._path = _path;
    this._filename = _filename;
    this._vobj = _ivobj;
    if(_opt == null || _opt.trim().equals("")) _createopt =0;
    else   _createopt = Integer.parseInt(_opt);
  }


  private String _getFBDLayoutSpaceClear(String _str)
  {
      String _rtn="";
      int _cnt=0;
      char[] _tochar = _str.toCharArray();
      for(int i=0;i<_tochar.length;i++){

          if(_tochar[i] =='\'')
          {
              if(_cnt == 1) {
                  _cnt =0;
              }else {
                  _cnt =1;
              }
              _rtn+= _tochar[i];
              continue;
          }

          if(_cnt == 1){
              _rtn+= _tochar[i];
          }else {
              if(_tochar[i] != ' ')
              {
                  _rtn+= _tochar[i];
              }
          }
      }
      return _rtn;
  }




  public String _makeFile(DOBJ _dobj, String  _layout, String _nline, String _charset)
  {
      String rtn="";
      _layout = _getFBDLayoutSpaceClear(_layout);
      ArrayList _clist = this._getLayout(_layout);
      if(_createopt==0)
      {
          rtn = _create(_dobj, _clist, _nline, _charset);
      }else if(_createopt==1)
      {
          rtn = _appendfirst(_dobj, _clist, _nline, _charset);
      }else if(_createopt==2)
      {
         //rtn =  _appendlast(_dobj, _clist, _nline, _charset);
         rtn =  _appendFile(_dobj, _clist, _nline, _charset);

      }else if(_createopt==3)
      {
          rtn = _appendseqname(_dobj, _clist, _nline, _charset);
      }
      return rtn;
  }



  private void _cvtFileCharset(String _charset, String newline) throws Exception
  {
	 
      FileReader in = null;
      FileOutputStream fout = null;
      OutputStreamWriter out = null;
      File _fp = null;
      File _fpx = null;
      try
      {
    	 
          _fp = new File(_path + _filename +"_bak");
          _fpx = new File(_path + _filename);
        
          in = new FileReader(_fpx);
          fout = new FileOutputStream(_fp);
          fout.write( 0xef );  
          fout.write( 0xbb );  
          fout.write( 0xbf ); 
          out = new OutputStreamWriter(fout, _charset);
        
          BufferedReader  bread = new BufferedReader(in);
          boolean first = true;
          String _linestr ="";
          while((_linestr=bread.readLine())!= null)
          {
              if(first == true)
              {
                  out.write(_linestr);
                  first = false;
              }
              else
              {
                  out.write( newline + _linestr );
              }
          }
        
      }
      finally
      {
    	 
          if(out != null) out.close();
          if(in != null) in.close();
      }

      if(_fpx.exists()) _fpx.delete();
      _fp.renameTo(_fpx);
  }
  private void _cvtFileCharsetAppend(String _charset, String newline, String file1) throws Exception
  {
      FileReader in = null;
      FileOutputStream fout = null;
      OutputStreamWriter out = null;
      File _fp = null;
      File _fpx = null;
      try
      {
          _fp = new File(_path + _filename +"_append");
          _fpx = new File(file1);

          in = new FileReader(_fpx);
          fout = new FileOutputStream(_fp);
          out = new OutputStreamWriter(fout, _charset);

          BufferedReader  bread = new BufferedReader(in);
          boolean first = true;
          String _linestr ="";
          while((_linestr=bread.readLine())!= null)
          {
              if(first == true)
              {
                  out.write(_linestr);
                  first = false;
              }
              else
              {
                  out.write( newline + _linestr );
              }
          }
      }
      finally
      {
          if(out != null) out.close();
          if(in != null) in.close();
      }

      if(_fpx.exists()) _fpx.delete();
      _fp.renameTo(_fpx);
  }

  private String _create(DOBJ _dobj, ArrayList _clist, String _nline, String _charset )
  {
      File _fp = new File(_path + _filename +"_bak");
      File _fpx = new File(_path + _filename);

      FileOutputStream _fout = null;
      DataOutputStream _dout = null;
      try{
          _fout = new FileOutputStream(_fp);
          _fout.write( 0xef );  
          _fout.write( 0xbb );  
          _fout.write( 0xbf );  
          _dout = new DataOutputStream(_fout);
          _writeFile(_dobj, _dout, _clist, 0); //0:create, 1:overwrite, 2:appendfirst, 3:appendlast

      }catch(Exception e)
      {
          e.printStackTrace();
      }finally
      {
          try{
              _fout.close();
              _dout.close();
          }catch(Exception e){
              e.printStackTrace();
          }
      }

      if(_fpx.exists()) _fpx.delete();
      _fp.renameTo(_fpx);

      try
      {
          if(_nline.equals("")) _nline="\n";
          else if(_nline.equals("n")) _nline="\n";
          else if(_nline.equals("rn")) _nline="\r\n";
          else  _nline="\r\n";
          if(!_charset.trim().equals(""))
          {
              _cvtFileCharset(_charset, _nline);
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return _filename;
  }


  private String _appendFile(DOBJ _dobj, ArrayList _clist, String _nline, String _charset )
  {
      File _fp = new File(_path + _filename);

      FileOutputStream _fout = null;
      DataOutputStream _dout = null;
      try
      {
          _fout = new FileOutputStream(_fp,true);
          _dout = new DataOutputStream(_fout);
          _writeFile(_dobj, _dout, _clist, 0); //0:create, 1:overwrite, 2:appendfirst, 3:appendlast

      }catch(Exception e)
      {
          e.printStackTrace();
      }
      finally
      {
          try
          {
              _fout.close();
              _dout.close();
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
      }
      return _filename;
  }

  private String _appendseqname(DOBJ _dobj, ArrayList _clist, String _nline, String _charset )
  {
      String rtn ="";
      File _fp = new File(_path + _filename);
      rtn  = _filename;
      int _cnt=0;
      
      while(_fp.exists())
      {
          String _filenamex = _filename.substring(0,_filename.indexOf(".")) + _cnt + _filename.substring(_filename.indexOf("."));
          _fp = new File(_path + _filenamex );
          rtn = _filenamex;
          _cnt++;
      }
      FileOutputStream _fout = null;
      DataOutputStream _dout = null;

      try
      {
          _fout = new FileOutputStream(_fp);
          _dout = new DataOutputStream(_fout);
          _writeFile(_dobj, _dout, _clist, 0);   //0:create,  2:appendfirst, 3:appendlast, 4:_appendseqname,

          //File _fpx = new File(_path + _filename);
          //if(_fpx.exists()) _fpx.delete();
          //_fp.renameTo(_fpx);

      }catch(Exception e){
          e.printStackTrace();
      }finally{
          try{
              _fout.close();
              _dout.close();
          }catch(Exception e){
              e.printStackTrace();
          }
      }


      try
      {
          if(_nline.equals("")) _nline="\n";
          else if(_nline.equals("n")) _nline="\n";
          else if(_nline.equals("rn")) _nline="\r\n";
          else  _nline="\r\n";
         
          if( !_charset.trim().equals(""))
          {
        	  
              _cvtFileCharset(_charset, _nline);
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }

      return rtn;
  }


  private static void merge(String file1, String file2, String outfile, String _charset, String _newline)
  {

    File fp1 = new File(file1);
    File fp2 = new File(file2);
    File fp3 = new File(outfile);

    FileInputStream fin1 = null;
    FileInputStream fin2 = null;
    FileOutputStream fout = null;

    InputStreamReader iread1 = null;
    InputStreamReader iread2 = null;
    OutputStreamWriter wrt = null;

    try {
      fin1 = new FileInputStream(fp1);
      fin2 = new FileInputStream(fp2);
      fout = new FileOutputStream(fp3);

      if(_charset !=null && !_charset.trim().equals(""))
      {
          //iread1 = new InputStreamReader(fin1, _charset);
          //iread2 = new InputStreamReader(fin2, _charset);
          //wrt = new OutputStreamWriter(fout, _charset);

          iread1 = new InputStreamReader(fin1, _charset);
          iread2 = new InputStreamReader(fin2, _charset);
          wrt = new OutputStreamWriter(fout, _charset);

      }
      else
      {
          iread1 = new InputStreamReader(fin1);
          iread2 = new InputStreamReader(fin2);
          wrt = new OutputStreamWriter(fout);
      }

      BufferedReader _breader = new BufferedReader(iread1);
      String _str = "";
      boolean _first = true;
      while ( (_str = _breader.readLine()) != null) {
        if (_first == true) {
          _first = false;
        }
        else {
          _str = _newline + _str;
        }
        wrt.write(_str);
      }

      _breader = new BufferedReader(iread2);
      _str = "";
      while ( (_str = _breader.readLine()) != null) {
        if (_first == true) {
          _first = false;
        }
        else {
          _str = _newline + _str;
        }
        wrt.write(_str);
      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        fin1.close();
        fin2.close();
        wrt.close();
        System.out.println("File Class : end");

        fp1.delete();
        fp2.delete();
      }
      catch (Exception ee) {
        ee.printStackTrace();
      }

    }
  }
  private String _appendfirst(DOBJ _dobj, ArrayList _clist, String _nline, String _charset)
  {
    File _fp = new File(_path + _filename +"_temp1");
    File _fp1 = new File(_path + _filename + "_temp2");
    File _fpx = new File(_path + _filename);

    int _cnt=0;
    if(_fp.exists())
    {
      _fp.delete();
    }
    if(_fp1.exists())
    {
      _fp1.delete();
    }

    FileOutputStream _fout = null;
    DataOutputStream _dout = null;
    try
    {
      _fout = new FileOutputStream(_fp);
      _dout = new DataOutputStream(_fout);
      _writeFile(_dobj, _dout, _clist, 0);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        _dout.close();
        _fout.close();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }

    try
    {
      if(!_charset.trim().equals(""))
      {
        _cvtFileCharsetAppend(_charset, _nline, _path + _filename +"_temp1");
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }

    if(_fpx.exists())
    {
        if(_nline.equals("")) _nline="\n";
        else if(_nline.equals("n")) _nline="\n";
        else if(_nline.equals("rn")) _nline="\r\n";
        else  _nline="\r\n";

        _fpx.renameTo(_fp1);
        this.merge(_path + _filename +"_temp1", _path + _filename +"_temp2", _path + _filename, _charset, _nline);
    }else
    {
        _fp.renameTo(_fpx);
    }
    return _filename;
  }

  private String _appendlast(DOBJ _dobj, ArrayList _clist, String _nline, String _charset)
  {
    File _fp = new File(_path + _filename +"_temp1");
    File _fp1 = new File(_path + _filename + "_temp2");
    File _fpx = new File(_path + _filename);

    int _cnt=0;
    if(_fp.exists())
    {
      _fp.delete();
    }
    if(_fp1.exists())
    {
      _fp1.delete();
    }


    FileOutputStream _fout = null;
    DataOutputStream _dout = null;
    try
    {
      _fout = new FileOutputStream(_fp);
      _dout = new DataOutputStream(_fout);
      _writeFile(_dobj, _dout, _clist, 0);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        _dout.close();
        _fout.close();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }

    try
    {
      if(!_charset.trim().equals(""))
      {
        _cvtFileCharsetAppend(_charset, _nline, _path + _filename +"_temp1");
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }

    if(_fpx.exists())
    {
      _fpx.renameTo(_fp1);
      if(_nline.equals("")) _nline="\n";
      else if(_nline.equals("n")) _nline="\n";
      else if(_nline.equals("rn")) _nline="\r\n";
      else  _nline="\r\n";

      this.merge(_path + _filename +"_temp2", _path + _filename +"_temp1", _path + _filename, _charset, _nline);

    }else
    {
      _fp.renameTo(_fpx);
    }
    return _filename;
  }
  private void _appendfirst_bk1(DOBJ _dobj, ArrayList _clist, String _nline, String _charset){
    File _fp = new File(_path + _filename +"_bak");
    File _fpx = new File(_path + _filename);
    int _cnt=0;
    //System.out.println(_path + _filename +"_bak" +"======" + _path + _filename);

    if(_fp.exists()){
      _fp.delete();
    }
    FileOutputStream _fout = null;
    DataOutputStream _dout = null;

    try
    {
        _fout = new FileOutputStream(_fp);
        _dout = new DataOutputStream(_fout);
        _writeFile(_dobj, _dout, _clist, 0);
    }catch(Exception e){
        e.printStackTrace();
    }finally
    {
        try{
            _dout.close();
            _fout.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    try
    {
        if(_nline.equals("")) _nline="\n";
        else if(_nline.equals("n")) _nline="\n";
        else if(_nline.equals("rn")) _nline="\r\n";
        else  _nline="\r\n";

        if(!_charset.trim().equals(""))
        {
            _cvtFileCharsetAppend(_charset, _nline, _path + _filename+"_bak");
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }


    File _fpp = null;
    FileOutputStream _foutp = null;
    DataOutputStream _doutp = null;
    try
    {
        if(_fpx.exists())
        {
            _fpp = new File(_path + _filename +"_appendx");
            _foutp = new FileOutputStream(_fpp);
            _doutp = new DataOutputStream(_foutp);

            _cnt++;
            if(_nline.equals("")) _nline="\n";
            else if(_nline.equals("n")) _nline="\n";
            else if(_nline.equals("rn")) _nline="\r\n";
            else  _nline="\r\n";
            FileReader _freader = new FileReader( _fp);
            BufferedReader  _breader = new BufferedReader( _freader);
            String _str = "";
            boolean _first= true;
            while(( _str= _breader.readLine()) != null )
            {
                if(_first == true)
                {
                    _first =false;
                }else
                {
                    _str= _nline + _str;
                }
                _doutp.write(_str.getBytes());
            }
            _breader.close();
            _freader.close();


            _freader = new FileReader( _fpx);
            _breader = new BufferedReader( _freader);
            _str = "";
            while(( _str= _breader.readLine()) != null )
            {
                _str= _nline + _str;
                _doutp.write(_str.getBytes());
            }
            _breader.close();
            _freader.close();

        }

    }catch(Exception e){
        e.printStackTrace();
    }finally
    {
        try{
            _foutp.close();
            _foutp.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    _fpx.delete();
    _fp.renameTo(_fpx);
    //_fp.delete();

  }
  private void _appendlast_bk1(DOBJ _dobj, ArrayList _clist, String _nline, String _charset){
      File _fp = new File(_path + _filename +"_bak");
      File _fpx = new File(_path + _filename);
      int _cnt=0;
      //System.out.println(_path + _filename +"_bak" +"======" + _path + _filename);

      if(_fp.exists()){
        _fp.delete();
      }
      FileOutputStream _fout = null;
      DataOutputStream _dout = null;

      try
      {

          _fout = new FileOutputStream(_fp);
          _dout = new DataOutputStream(_fout);
          _writeFile(_dobj, _dout, _clist, 0);

      }catch(Exception e){
          e.printStackTrace();
      }finally{
          try{
              _dout.close();
              _fout.close();
          }catch(Exception e){
              e.printStackTrace();
          }
      }

      try
      {
          if(_nline.equals("")) _nline="\n";
          else if(_nline.equals("n")) _nline="\n";
          else if(_nline.equals("rn")) _nline="\r\n";
          else  _nline="\r\n";

          if(!_charset.trim().equals(""))
          {
              _cvtFileCharsetAppend(_charset, _nline, _path + _filename+"_bak");
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }

      File _fpp = null;
      FileOutputStream _foutp = null;
      DataOutputStream _doutp = null;
      try
      {

          if(_fpx.exists())
          {
              _fpp = new File(_path + _filename +"_appendx");
              _foutp = new FileOutputStream(_fpp);
              _doutp = new DataOutputStream(_foutp);

              _cnt++;
              if(_nline.equals("")) _nline="\n";
              else if(_nline.equals("n")) _nline="\n";
              else if(_nline.equals("rn")) _nline="\r\n";
              else  _nline="\r\n";
              FileReader _freader = new FileReader( _fpx);
              BufferedReader  _breader = new BufferedReader( _freader);
              String _str = "";
              boolean _first= true;
              while(( _str= _breader.readLine()) != null )
              {
                  if(_first == true)
                  {
                      _first =false;
                  }else
                  {
                      _str= _nline + _str;
                  }
                  _doutp.write(_str.getBytes());
              }
              _breader.close();
              _freader.close();


              _freader = new FileReader( _fp);
              _breader = new BufferedReader( _freader);
              _str = "";
              while(( _str= _breader.readLine()) != null )
              {
                  _str= _nline + _str;
                  _doutp.write(_str.getBytes());
              }
              _breader.close();
              _freader.close();

          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }finally
      {
          try
          {
              if( _foutp != null) _foutp.close();
              if(_doutp != null)  _doutp.close();

              _fp.delete();
              _fpp.renameTo(_fp);

          }catch(Exception ee)
          {
              ee.printStackTrace();
          }
      }

      _fpx.delete();
      _fp.renameTo(_fpx);
      //_fp.delete();

  }

  private void _appendlast_bk(DOBJ _dobj, ArrayList _clist, String _nline, String _charset){
      File _fp = new File(_path + _filename +"_bak");
      File _fpx = new File(_path + _filename);
      int _cnt=0;
      if(_fp.exists()){
          _fp.delete();
      }

      FileOutputStream _fout = null;
      DataOutputStream _dout = null;
      try{

          _fout = new FileOutputStream(_fp);
          _dout = new DataOutputStream(_fout);

          if(_fpx.exists())
          {
              _cnt++;
              if(_nline.equals("")) _nline="\n";
              else if(_nline.equals("n")) _nline="\n";
              else if(_nline.equals("rn")) _nline="\r\n";
              else  _nline="\r\n";

              FileReader _freader = new FileReader( _fpx);
              BufferedReader  _breader = new BufferedReader( _freader);
              String _str = "";
              while(( _str= _breader.readLine()) != null ){
                  _str= _str+_nline;
                  _dout.write(_str.getBytes());
              }
              _breader.close();
              _freader.close();
          }



          _writeFile(_dobj, _dout, _clist, 0);



      }catch(Exception e){
          e.printStackTrace();
      }finally
      {
          try
          {
              _dout.close();
              _fout.close();
          }catch(Exception e)
          {
              e.printStackTrace();
          }
      }

      if(_fpx.exists()) _fpx.delete();
      _fp.renameTo(_fpx);

      try
      {
          if(_nline.equals("")) _nline="\n";
          else if(_nline.equals("n")) _nline="\n";
          else if(_nline.equals("rn")) _nline="\r\n";
          else  _nline="\r\n";

          if(_cnt ==0 && !_charset.trim().equals(""))
          {
              _cvtFileCharset(_charset, _nline);
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }

  }

  private void _delete(){
    if(_errflag == 0){
      //-- ���� ���Ų ���� ����
    }else if(_errflag==1){
      //-- ���� ���ϱ��� ��� ����
    }
  }

  //----- FUP[ó�� ��� : length��� ����format, ������ó���� csv������ �������� ó�� �Ѵ� ----
  public void  _getLengthFormatColumnList()
  {
  }

  public VOBJ _getCSVVobj(String _path, String _filename, String _columns, String _delim) throws Exception{
    VOBJ _vobj = new VOBJ();
    HashMap rec = null;
    if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";

    XFRLayout _xlayout = new XFRLayout();
    ArrayList _clist = _xlayout._getCSVColumnlist(_columns,",");

    File _fp = new File(_path +_filename);
    FileReader _freader = new FileReader( _fp);
    BufferedReader  _breader = new BufferedReader( _freader);
    char _delimchar = _getDelimchar(_delim);
    String _str = "";
    while(( _str= _breader.readLine()) != null )
    {
      rec =_xlayout._getCVSRecord(_str, _clist, _delimchar);
      _vobj.addRecord(rec);
    }
    return _vobj;
  }

  public VOBJ _getCSVLVobj(String _path, String _filename, String _columns, String _delim) throws Exception{
      VOBJ _vobj = new VOBJ();
      HashMap rec = null;
      if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";

      XFRLayout _xlayout = new XFRLayout();
      ArrayList _clist = _xlayout._getCSVColumnlist(_columns,",");
      ArrayList _nlist = _xlayout._getCSVColumnlist(_delim,",");
      int[] _len = new int[_nlist.size()];
      for(int i=0;i<_nlist.size();i++)
      {
        _len[i] = Integer.parseInt(_nlist.get(i).toString().trim());
      }

      File _fp = new File(_path +_filename);
      FileReader _freader = new FileReader( _fp);
      BufferedReader  _breader = new BufferedReader( _freader);
      char _delimchar = _getDelimchar(_delim);
      String _str = "";
      while(( _str= _breader.readLine()) != null )
      {
        rec =_xlayout._getCVSLenRecord(_str, _clist, _len);
        _vobj.addRecord(rec);
      }
      return _vobj;
  }



  private char _getDelimchar(String _delim){
    boolean _excapechar=false;;
    char[] _tochar = _delim.trim().toCharArray();
    for(int i=0;i<_tochar.length;i++){
      if(_tochar[i] == '\\'){
        _excapechar= true;

      }else if(_excapechar==true){
        if(_tochar[i]=='t'){
          return '\t';
        }
      }else {
        return _tochar[i];
      }
    }
    return ' ';
  }

  private ArrayList _getLayout(String _layout)
  {
      XFRLayout _xlayout = new XFRLayout(_layout);
      ArrayList _clist = _xlayout._getColumnList();
      _containfilesize = _xlayout._getContainfilesize();
      return _clist;
  }
  private void _deleteFile(File _fp){
    _fp.delete();
  }
  private long _getFilesize(File _fp)
  {
    return _fp.length();
  }

  private void  _writeFile(DataOutputStream _dout , String _recstr) throws Exception
  {
      HashMap _rec = null;
      _dout.write(_recstr.getBytes());
  }

  private void  _writeFile_bk( DOBJ _dobj, DataOutputStream _dout , ArrayList _clist, long _fsize) throws Exception
  {
      long  _size=_fsize;
      String _recstr="";
      HashMap _rec = null;
      boolean _last = false;
      _vobj.first();
      while(_vobj.next())
      {
          if(_vobj.current == _vobj.getRecordCnt()-1)   _last=true;
          _rec = _vobj.getRecord().getRecord();
          _recstr = _getRecordData(_dobj, _clist ,_rec, _size, _last);
          _size+=_recstr.length();
          _dout.write(_recstr.getBytes());
      }
  }

  private void  _writeFile_A( DOBJ _dobj,DataOutputStream _dout , ArrayList _clist, long _fsize) throws Exception
  {
      long  _size=_fsize;
      byte[] _recstr="".getBytes();
      HashMap _rec = null;
      boolean _last = false;
      _vobj.first();
      while(_vobj.next())
      {
          if(_vobj.current == _vobj.getRecordCnt()-1)  _last=true;
          _rec = _vobj.getRecord().getRecord();
          _recstr = _getRecordByteData(_dobj, _clist ,_rec, _size, _last);
          _size+=_recstr.length;
          _dout.write(_recstr);
      }
  }

  private byte[] setNullSpace(byte[] _bin)
  {
      for(int i=0;i<_bin.length;i++)
      {
          if(_bin[i] =='\0') _bin[i] =0x20;   //null byte ��Ʈó��
      }
      return _bin;
  }
  private void  _writeFile(DOBJ _dobj, DataOutputStream _dout , ArrayList _clist, long _fsize) throws Exception
  {
      long  _size=_fsize;
      ArrayList _recstr= null;
      HashMap _rec = null;
      boolean _last = false;
    
      _vobj.first();
      while(_vobj.next())
      {
          if(_vobj.current == _vobj.getRecordCnt()-1)  _last=true;
          _rec = _vobj.getRecord().getRecord();
          _recstr = _getRecordByteArrayData(_dobj, _clist ,_rec, _size, _last);
          for(int i=0;i<_recstr.size();i++)
          {
               _dout.write(setNullSpace((byte[])_recstr.get(i)));
          }
      }
  }

  private String _getRecordData(DOBJ _dobj,ArrayList _clist,  HashMap _record, long _filesize, boolean _last){
    String _rtn ="";
    String _recdata ="";
    if(_containfilesize==true){
      _recdata = _getRecordData(_dobj,  _clist,   _record, _last);
      _rtn += _getRecordData(_dobj, _clist,   _record, _filesize, _recdata.length());
    }else {
      _rtn += _getRecordData(_dobj,  _clist,   _record, _last);
    }
    return _rtn;
  }
  private byte[] _getRecordByteData(DOBJ _dobj,ArrayList _clist,  HashMap _record, long _filesize, boolean _last){
    byte[] _rtn = new byte[0];
    String _recdata ="";
    //System.out.println(_record);
    if(_containfilesize==true){
        _recdata = _getRecordData(_dobj,  _clist,   _record, _last);
        _rtn  = addByte(_rtn , _getRecordByteData(_dobj, _clist,   _record, _filesize, _recdata.length()) );

    }else {
        _rtn  = addByte(_rtn , _getRecordByteData(_dobj,  _clist,   _record, _last) );

    }
    return _rtn;
  }


  private String _getRecordData(DOBJ _dobj, ArrayList _clist,  HashMap _record, long _filesize, int _recordsize){
      String _rtn = "";
      XFCLayout _xcol = null;
      String[] namex = null;
      for(int i=0; i<_clist.size(); i++){
        _xcol = (XFCLayout)_clist.get(i);
        if(_xcol._getColumntype()==1)
        {
          _xcol._setFsize(_filesize , _recordsize);
        }

        if(isString(_xcol._getColumnnm()))
        {
            if(_xcol._getCharset().equals(""))
            {
                _rtn += _xcol._getFormatvalue(getStringValue(_xcol._getColumnnm()));
            }else
            {
                _rtn += _xcol._getFormatvalue( this.CvsString(getStringValue(_xcol._getColumnnm()) ,_xcol._getCharset())  );
            }
        }
        else
        {
            if( (namex = getNameset(_xcol._getColumnnm()))==null)
            {
                if(_xcol._getCharset().equals(""))
                {
                    _rtn += _xcol._getFormatvalue( _record.get(_xcol._getColumnnm()).toString() );
                }else
                {
                    _rtn += _xcol._getFormatvalue(  this.CvsString( _record.get(_xcol._getColumnnm()).toString() ,_xcol._getCharset())  );
                }
            }
            else
            {
                if(_xcol._getCharset().equals(""))
                {
                    _rtn += _xcol._getFormatvalue(_dobj.getRetObject(namex[0]).getRecord().get(namex[1]));
                }else
                {
                    _rtn += _xcol._getFormatvalue(  this.CvsString(_dobj.getRetObject(namex[0]).getRecord().get(namex[1]) ,_xcol._getCharset())  );
                }
            }
        }
      }
      return _rtn;
  }
  private String _getRecordData(DOBJ _dobj, ArrayList _clist,  HashMap _record, boolean _last){
    String _rtn = "";
    XFCLayout _xcol = null;
    String[] namex = null;
    for(int i=0; i<_clist.size(); i++){
      _xcol = (XFCLayout)_clist.get(i);
      //System.out.println( _xcol._getColumnnm() +":" + _record.get(_xcol._getColumnnm()));
      if(_last==true && i==_clist.size()-1){

        if(isString(_xcol._getColumnnm()))
        {
            if(_xcol._getCharset().equals(""))
            {
                _rtn += _xcol._getLastFormatvalue(getStringValue(_xcol._getColumnnm()));
            }else
            {
                _rtn += _xcol._getLastFormatvalue( this.CvsString(getStringValue(_xcol._getColumnnm()) ,_xcol._getCharset()) );
            }
        }else
        {
            if( (namex = getNameset(_xcol._getColumnnm()))==null)
            {
                if(_xcol._getCharset().equals(""))
                {
                    _rtn += _xcol._getLastFormatvalue(_record.get(_xcol._getColumnnm()).toString());
                }else
                {
                    _rtn += _xcol._getLastFormatvalue( this.CvsString(_record.get(_xcol._getColumnnm()).toString() ,_xcol._getCharset()) );
                }
            }
            else
            {
                if(_xcol._getCharset().equals(""))
                {
                    _rtn += _xcol._getLastFormatvalue( _dobj.getRetObject(namex[0]).getRecord().get(namex[1]) );
                }else
                {
                    _rtn += _xcol._getLastFormatvalue( this.CvsString(  _dobj.getRetObject(namex[0]).getRecord().get(namex[1])  ,_xcol._getCharset())  );
                }
            }
        }

      }else {

        if(isString(_xcol._getColumnnm()))
        {
            if(_xcol._getCharset().equals(""))
            {
                _rtn += _xcol._getFormatvalue(getStringValue(_xcol._getColumnnm()));
            }else
            {
                _rtn += _xcol._getFormatvalue(this.CvsString(   getStringValue(_xcol._getColumnnm())  ,_xcol._getCharset()) );
            }
        }
        else
        {
            if( (namex = getNameset(_xcol._getColumnnm()))==null)
            {
                if(_xcol._getCharset().equals(""))
                {
                    _rtn += _xcol._getFormatvalue(_record.get(_xcol._getColumnnm()).toString());
                }
                else
                {
                    _rtn += _xcol._getFormatvalue(this.CvsString(  _record.get(_xcol._getColumnnm()).toString()  ,_xcol._getCharset()) );
                }
            }
            else
            {
                if(_xcol._getCharset().equals(""))
                {
                    _rtn += _xcol._getFormatvalue(_dobj.getRetObject(namex[0]).getRecord().get(namex[1]));
                }else
                {
                    _rtn += _xcol._getFormatvalue( this.CvsString( _dobj.getRetObject(namex[0]).getRecord().get(namex[1])   ,_xcol._getCharset()) );
                }
            }
        }
      }
    }
    return _rtn;
  }


   private ArrayList _getRecordByteArrayData(DOBJ _dobj,  ArrayList _clist,  HashMap _record, long _filesize, boolean _last){
    ArrayList _rtn =new ArrayList();
    String _recdata ="";
    //System.out.println(_record);
    if(_containfilesize==true){
        _recdata = _getRecordData( _dobj, _clist,   _record, _last);
        _rtn  = _getRecordByteArrayData(_dobj, _clist,   _record, _filesize, _recdata.length());
    }else {
        _rtn  = _getRecordByteArrayData( _dobj, _clist,   _record, _last) ;
    }
    return _rtn;
  }

  private int _getPoint(char c){
      int p=0;
      switch(c){
      case '0' : p=0; break;
      case '1' : p=1; break;
      case '2' : p=2; break;
      case '3' : p=3; break;
      case '4' : p=4; break;
      case '5' : p=5; break;
      case '6' : p=6; break;
      case '7' : p=7; break;
      case '8' : p=8; break;
      case '9' : p=9; break;
      case 'a' :
      case 'A' : p=10; break;
      case 'b' :
      case 'B' : p=11; break;
      case 'c' :
      case 'C' : p=12; break;
      case 'd' :
      case 'D' : p=13; break;
      case 'e' :
      case 'E' : p=14; break;
      case 'f' :
      case 'F' : p=15; break;
      }
      return p;
  }
  private byte _getByteValue(int x, int y)
  {
      byte[][] table= {
                      {0x00,	0x01,	0x02,	0x03,	0x04,	0x05,	0x06,	0x07,	0x08,	0x09,	0x0a,	0x0b,	0x0c,	0x0d,	0x0e,	0x0f},
                      {0x10,	0x11,	0x12,	0x13,	0x14,	0x15,	0x16,	0x17,	0x18,	0x19,	0x1a,	0x1b,	0x1c,	0x1d,	0x1e,	0x1f},
                      {0x20,	0x21,	0x22,	0x23,	0x24,	0x25,	0x26,	0x27,	0x28,	0x29,	0x2a,	0x2b,	0x2c,	0x2d,	0x2e,	0x2f},
                      {0x30,	0x31,	0x32,	0x33,	0x34,	0x35,	0x36,	0x37,	0x38,	0x39,	0x3a,	0x3b,	0x3c,	0x3d,	0x3e,	0x3f},
                      {0x40,	0x41,	0x42,	0x43,	0x44,	0x45,	0x46,	0x47,	0x48,	0x49,	0x4a,	0x4b,	0x4c,	0x4d,	0x4e,	0x4f},
                      {0x50,	0x51,	0x52,	0x53,	0x54,	0x55,	0x56,	0x57,	0x58,	0x59,	0x5a,	0x5b,	0x5c,	0x5d,	0x5e,	0x5f},
                      {0x60,	0x61,	0x62,	0x63,	0x64,	0x65,	0x66,	0x67,	0x68,	0x69,	0x6a,	0x6b,	0x6c,	0x6d,	0x6e,	0x6f},
                      {0x70,	0x71,	0x72,	0x73,	0x74,	0x75,	0x76,	0x77,	0x78,	0x79,	0x7a,	0x7b,	0x7c,	0x7d,	0x7e,	0x7f}};
      return table[x][y];
  }
  private byte[] _getByte(String colnm){
      byte[] b = new byte[1];
      int x=0, y=0;
      char[] _tochar = colnm.trim().toCharArray();
      x = _getPoint(_tochar[2]);
      y = _getPoint(_tochar[3]);
      b[0] = _getByteValue(x,y);
      return b;
  }
  private boolean _isByteValue(String colnm)
  {
      boolean _rtn = false;
      if(colnm ==null && colnm.trim().length() != 4) return _rtn;
      char[] _tochar = colnm.toCharArray();
      if(_tochar[0]=='0' && _tochar[1] =='x'){
          return true;
      }
      return _rtn;
  }

  private ArrayList _getRecordByteArrayData(DOBJ _dobj,  ArrayList _clist,  HashMap _record, boolean _last){
    ArrayList _rtn =new ArrayList();
    XFCLayout _xcol = null;
    String[] namex = null;
    for(int i=0; i<_clist.size(); i++){
      _xcol = (XFCLayout)_clist.get(i);
      if(_last==true && i==_clist.size()-1){

          if(isString(_xcol._getColumnnm()))
          {
              if(_xcol._getCharset().equals(""))
              {
                  _rtn.add(_xcol._getLastFormatBytevalue( getStringValue(_xcol._getColumnnm()).toString()) );
              }else
              {
                  _rtn.add(_xcol._getLastFormatBytevalue( this.CvsString(  getStringValue(_xcol._getColumnnm()).toString()  ,_xcol._getCharset()) ) );
              }
          }
          else
          {
              if(_isByteValue(_xcol._getColumnnm()))
              {
                  _rtn.add(_getByte( _xcol._getColumnnm() ) ) ;
              }
              else
              {

                  if( (namex = getNameset(_xcol._getColumnnm()))==null)
                  {
                      if(_xcol._getCharset().equals(""))
                      {
                          _rtn.add(_xcol._getLastFormatBytevalue(getString(_record.get(_xcol._getColumnnm()))));
                      }else
                      {
                          _rtn.add(_xcol._getLastFormatBytevalue( this.CvsString(  getString(_record.get(_xcol._getColumnnm()))  ,_xcol._getCharset()) ));
                      }
                  }
                  else
                  {
                      if(_xcol._getCharset().equals(""))
                      {
                          _rtn.add(_xcol._getLastFormatBytevalue(_dobj.getRetObject(namex[0]).getRecord().get(namex[1])));
                      }else
                      {
                          _rtn.add(_xcol._getLastFormatBytevalue( this.CvsString( _dobj.getRetObject(namex[0]).getRecord().get(namex[1]) ,_xcol._getCharset())  ));
                      }
                  }
              }
          }
      }else {

          if(isString(_xcol._getColumnnm()))
          {
              if(_xcol._getCharset().equals(""))
              {
                  _rtn.add(_xcol._getFormatBytevalue(getStringValue(_xcol._getColumnnm()).toString()) );
              }else
              {
                  _rtn.add(_xcol._getFormatBytevalue( this.CvsString( getStringValue(_xcol._getColumnnm()).toString()    ,_xcol._getCharset())  ) );
              }
          }
          else
          {
              if(_isByteValue(_xcol._getColumnnm()))
              {
                  _rtn.add(_getByte( _xcol._getColumnnm() ) ) ;
              }
              else
              {
                  if( (namex = getNameset(_xcol._getColumnnm()))==null)
                  {
                      if(_xcol._getCharset().equals(""))
                      {
                          _rtn.add(_xcol._getFormatBytevalue(getString(_record.get(_xcol._getColumnnm()))) );
                      }
                      else
                      {
                          //System.out.println(_xcol._getColumnnm()+":"+_record.get(_xcol._getColumnnm()));
                          _rtn.add(_xcol._getFormatBytevalue( this.CvsString( getString(_record.get(_xcol._getColumnnm()))   ,_xcol._getCharset())  ) );
                      }
                  }
                  else
                  {
                      if(_xcol._getCharset().equals(""))
                      {
                          _rtn.add(_xcol._getFormatBytevalue(_dobj.getRetObject(namex[0]).getRecord().get(namex[1]) ) );
                      }else
                      {
                          _rtn.add(_xcol._getFormatBytevalue( this.CvsString(  _dobj.getRetObject(namex[0]).getRecord().get(namex[1])  ,_xcol._getCharset()) ) );
                      }
                  }
              }
          }
      }
  }
  return _rtn;
}
    private String getString(Object str)
    {
        if(str==null) return "";
        return str.toString();
    }


    private String[] getNameset(String colname){
        String[] rtn = null;
      if(colname.indexOf(".")!=-1)
      {
        rtn = new String[2];
        rtn[0] = colname.substring(0,colname.indexOf("."));
        rtn[1] = colname.substring(colname.indexOf(".")+1);
      }
      return rtn;
  }
  private ArrayList _getRecordByteArrayData(DOBJ _dobj,  ArrayList _clist,  HashMap _record, long _filesize, int _recordsize){
      ArrayList _rtn =new ArrayList();
      String[] namex = null;
      XFCLayout _xcol = null;
      for(int i=0; i<_clist.size(); i++){
          _xcol = (XFCLayout)_clist.get(i);
          if(_xcol._getColumntype()==1){
              _xcol._setFsize(_filesize , _recordsize);
          }

          if(isString(_xcol._getColumnnm()))
          {
              if(_xcol._getCharset().equals(""))
              {
                  _rtn.add(_xcol._getFormatBytevalue(getStringValue(_xcol._getColumnnm())));
              }else
              {
                  _rtn.add(_xcol._getFormatBytevalue(  this.CvsString( getStringValue(_xcol._getColumnnm())   ,_xcol._getCharset())  ));
              }
          }else
          {
              if(_isByteValue(_xcol._getColumnnm()))
              {
                  _rtn.add(_getByte( _xcol._getColumnnm() ) ) ;
              }
              else
              {
                  if( (namex = getNameset(_xcol._getColumnnm()))==null)
                  {
                      if(_xcol._getCharset().equals(""))
                      {
                          _rtn.add(_xcol._getFormatBytevalue(_record.get(_xcol._getColumnnm()).toString()));
                      }else
                      {
                          _rtn.add(_xcol._getFormatBytevalue( this.CvsString(  _record.get(_xcol._getColumnnm()).toString()  ,_xcol._getCharset()) ));
                      }
                  }else
                  {
                      if(_xcol._getCharset().equals(""))
                      {
                          _rtn.add(_xcol._getFormatBytevalue(_dobj.getRetObject(namex[0]).getRecord().get(namex[1])));
                      }else
                      {
                          _rtn.add(_xcol._getFormatBytevalue( this.CvsString(  _dobj.getRetObject(namex[0]).getRecord().get(namex[1])  ,_xcol._getCharset())  ));
                      }
                  }
              }
          }
      }
      return _rtn;
  }

  private byte[] _getRecordByteData(DOBJ _dobj,  ArrayList _clist,  HashMap _record, boolean _last){
      byte[] _rtn = new byte[0];
      XFCLayout _xcol = null;
      String[] namex = null;
      for(int i=0; i<_clist.size(); i++){
          _xcol = (XFCLayout)_clist.get(i);
          if(_last==true && i==_clist.size()-1)
          {
              if(isString(_xcol._getColumnnm()))
              {
                  if(_xcol._getCharset().equals(""))
                  {
                      _rtn = addByte(_rtn , _xcol._getLastFormatBytevalue(getStringValue(_xcol._getColumnnm())));
                  }else
                  {
                      _rtn = addByte(_rtn , _xcol._getLastFormatBytevalue( this.CvsString(  getStringValue(_xcol._getColumnnm())  ,_xcol._getCharset())  ));
                  }
              }
              else
              {
                  if(_isByteValue(_xcol._getColumnnm()))
                  {
                      _rtn = _getByte( _xcol._getColumnnm() ) ;
                  }
                  else
                  {
                      if( (namex = getNameset(_xcol._getColumnnm()))==null)
                      {
                          if(_xcol._getCharset().equals(""))
                          {
                              _rtn = addByte(_rtn , _xcol._getLastFormatBytevalue(_record.get(_xcol._getColumnnm()).toString()));
                          }else
                          {
                              _rtn = addByte(_rtn , _xcol._getLastFormatBytevalue( this.CvsString(  _record.get(_xcol._getColumnnm()).toString()  ,_xcol._getCharset()) ));
                          }

                      }else
                      {

                          if(_xcol._getCharset().equals(""))
                          {
                              _rtn = addByte(_rtn , _xcol._getLastFormatBytevalue(_dobj.getRetObject(namex[0]).getRecord().get(namex[1])));
                          }else
                          {
                              _rtn = addByte(_rtn , _xcol._getLastFormatBytevalue( this.CvsString(  _dobj.getRetObject(namex[0]).getRecord().get(namex[1])  ,_xcol._getCharset())  ));
                          }
                      }
                  }
              }
          }else
          {
              if(isString(_xcol._getColumnnm()))
              {
                  if(_xcol._getCharset().equals(""))
                  {
                      _rtn = addByte(_rtn , _xcol._getFormatBytevalue(getStringValue(_xcol._getColumnnm())) );
                  }
                  else
                  {
                      _rtn = addByte(_rtn , _xcol._getFormatBytevalue( this.CvsString(  getStringValue(_xcol._getColumnnm())  ,_xcol._getCharset())  ) );
                  }
              }else
              {
                  if(_isByteValue(_xcol._getColumnnm())){
                      _rtn = _getByte( _xcol._getColumnnm() ) ;
                  }
                  else
                  {
                      if( (namex = getNameset(_xcol._getColumnnm()))==null)
                      {
                          if(_xcol._getCharset().equals(""))
                          {
                              _rtn = addByte(_rtn , _xcol._getFormatBytevalue(_record.get(_xcol._getColumnnm()).toString()) );
                          }else
                          {
                              _rtn = addByte(_rtn , _xcol._getFormatBytevalue( this.CvsString( _record.get(_xcol._getColumnnm()).toString()   ,_xcol._getCharset())  ) );
                          }
                      }else
                      {
                          if(_xcol._getCharset().equals(""))
                          {
                              _rtn = addByte(_rtn , _xcol._getFormatBytevalue(_dobj.getRetObject(namex[0]).getRecord().get(namex[1])) );
                          }else
                          {
                              _rtn = addByte(_rtn , _xcol._getFormatBytevalue( this.CvsString(  _dobj.getRetObject(namex[0]).getRecord().get(namex[1])  ,_xcol._getCharset()) ) );
                          }

                      }
                  }
              }
          }
      }
      return _rtn;
  }

  private byte[] _getRecordByteData(DOBJ _dobj,  ArrayList _clist,  HashMap _record, long _filesize, int _recordsize){
      byte[] _rtn = null;
      XFCLayout _xcol = null;
      String[] namex = null;
      for(int i=0; i<_clist.size(); i++){
          _xcol = (XFCLayout)_clist.get(i);
          if(_xcol._getColumntype()==1){
              _xcol._setFsize(_filesize , _recordsize);
          }

          if(isString(_xcol._getColumnnm()))
          {
              if(_xcol._getCharset().equals(""))
              {
                  _rtn = addByte(_rtn , _xcol._getFormatBytevalue(getStringValue(_xcol._getColumnnm())) );
              }else
              {
                  _rtn = addByte(_rtn , _xcol._getFormatBytevalue( this.CvsString( getStringValue(_xcol._getColumnnm())   ,_xcol._getCharset())  ) );
              }
          }else
          {
              if(_isByteValue(_xcol._getColumnnm())){
                  _rtn = _getByte( _xcol._getColumnnm() ) ;
              }else
              {
                  if( (namex = getNameset(_xcol._getColumnnm()))==null)
                  {
                      if(_xcol._getCharset().equals(""))
                      {
                          _rtn = addByte(_rtn , _xcol._getFormatBytevalue(_record.get(_xcol._getColumnnm()).toString()) );
                      }else
                      {
                          _rtn = addByte(_rtn , _xcol._getFormatBytevalue( this.CvsString(  _record.get(_xcol._getColumnnm()).toString()  ,_xcol._getCharset())  ) );
                      }

                  }else
                  {
                      if(_xcol._getCharset().equals(""))
                      {
                          _rtn = addByte(_rtn , _xcol._getFormatBytevalue(_dobj.getRetObject(namex[0]).getRecord().get(namex[1])) );
                      }else
                      {
                          _rtn = addByte(_rtn , _xcol._getFormatBytevalue(this.CvsString( _dobj.getRetObject(namex[0]).getRecord().get(namex[1])    ,_xcol._getCharset())  ) );
                      }
                  }
              }
          }
      }
      return _rtn;
  }

  private byte[] addByte(byte[] target, byte[] src){
      byte[] rtn = new byte[target.length + src.length];
      for(int i=0;i<src.length;i++){
          rtn[target.length+i] = src[i];
      }
      return rtn;
  }

  private boolean isString(String s){
      boolean _rtn=false;
      char[] _tochar = s.toCharArray();
      if(_tochar[0]=='"' || _tochar[_tochar.length-1]=='"'){
          _rtn = true;
      }
      return _rtn;
  }
  private String getStringValue(String s)
  {
      String  _rtn="";
      char[] _tochar = s.toCharArray();
      for(int i=0;i<_tochar.length;i++)
      {
          if(_tochar[i] == '"') continue;
          _rtn +=_tochar[i];
      }
      return _rtn;
  }

  //------------socket client


  private boolean _isAddress(String _str)
  {
      int _idxcnt=0;
      boolean rtn = false;
      char[] _tochar = _str.trim().toCharArray();
      for(int i=0;i<_tochar.length;i++){
          if(_tochar[i] == '.') _idxcnt++;
      }
      if(_idxcnt==4) return true;
      return rtn;
  }
  private boolean _isNumber(String _str){
      boolean rtn = true;
      try{
          Integer.parseInt(_str);
      }catch(Exception e){
          rtn = false;
      }
      return rtn;
  }
  public void _SocketSimPlex(DOBJ _dobj, VOBJ _vobj, String server, String  port, String  _layout , int len) throws Exception
  {
      ArrayList _clist = this._getLayout(_layout);
      if(_vobj.getRecordCnt() ==0)
      {
          System.out.println("SCLT:Simplex:���DATA�� �������� �ʽ��ϴ�.");
          return;
      }
      _writeSingleSimplexServer(_dobj, _vobj, _clist, server,  port, len);

      /*
      if(_vobj.getRecordCnt() ==1)
      {
          _writeSingleSimplexServer(_dobj, _vobj, _clist, server,  port, len);
      }
      else
      {
          _writeMultiSimplexServer(_dobj, _vobj, _clist,  server,  port, len);
      }
      */
  }
  private void  _writeSingleSimplexServer(DOBJ _dobj, VOBJ _vobj, ArrayList _clist, String server, String port, int len) throws Exception
  {
      long  _size=0;
      ArrayList _recstr= null;
      HashMap _rec = null;
      boolean _last = false;

      int _portnum=0;
      if(_isNumber(port))
      {
          _portnum = Integer.parseInt( port);
      }else {
          String[] namex = null;
          if( ( namex = getNameset(port) ) ==null)
          {
              _portnum = _vobj.getRecord().getInt(port);
          }else {
              _portnum = _dobj.getRetObject(namex[0]).getRecord().getInt(namex[1]);
          }
      }

      String _address ="";
      if(_isAddress(server))
      {
          _address = server.trim();
      }
      else
      {
          String[] namex = null;
          if( ( namex = getNameset(server ) ) ==null)
          {
              _address = _vobj.getRecord().get(server).trim();
          }else {
              _address = _dobj.getRetObject(namex[0]).getRecord().get(namex[1]).trim();
          }
      }
      XocClt _sclt = new XocClt(_address,  _portnum);

      int _start =0;
      int _idx=0;
      byte[] _sendbyte = null;  //record bytes
      byte[] _cbyte = null;     //column bytes
      _vobj.first();
      while(_vobj.next())
      {
          _rec = _vobj.getRecord().getRecord();
          _recstr = _getRecordByteArrayData(_dobj, _clist ,_rec, _size, _last);
          _start=0;
          _sendbyte = new byte[len];
          for(int i=0;i<_recstr.size();i++)
          {
              _idx=0;
              _cbyte =(byte[])_recstr.get(i);
              for(int j=_start;j<_cbyte.length+_start;j++)
              {
                  _sendbyte[j] = _cbyte[_idx];
                  _idx++;
              }
              _start = _start+_cbyte.length;
          }
          try
          {
              _rec.put("SCLTERR","");
              _sendbyte = this.setNullFilter(_sendbyte);
              _sclt._sendSimplex(_sendbyte);
              _rec.put("SCLTERR","1");
          }
          catch(Exception e)
          {
              _rec.put("SCLTERR","-1");
              _vobj.setRecord(_rec);
              _dobj.setRetObject(_vobj);
              throw e;
          }
          _vobj.setRecord(_rec);
      }
      _dobj.setRetObject(_vobj);
  }

  public VOBJ _SocketDuPlex(DOBJ dobj, VOBJ vobj, String  _layout, String _rcvlayout,  String server, String port, int slen, int rlen) throws Exception
  {
      VOBJ _rvobj = null;
      ArrayList _clist = this._getLayout(_layout);

      XcltRcvLayout _rcv = new XcltRcvLayout(dobj);
      _rcv._setColumnLayout(_rcvlayout);
      ArrayList _rcvlist = _rcv._getLayoutList();
      if(vobj.getRecordCnt() ==0)
      {
          System.out.println("��۴�� Data not Founded");
          return _rvobj;
      }
      _rvobj = _writeSingleDuplexServer(dobj, vobj, _clist, _rcvlist, server, port, slen, rlen);

      /*
      if(vobj.getRecordCnt() ==1)
      {
          _rvobj = _writeSingleDuplexServer(dobj, vobj, _clist, _rcvlist, server, port, slen, rlen);
      }
      else
      {
          _rvobj = _writeMultiDuplexServer(dobj, vobj, _clist, _rcvlist, server, port, slen, rlen);
      }
      */
      return _rvobj;
  }
  private VOBJ  _writeSingleDuplexServer(DOBJ _dobj, VOBJ _vobj, ArrayList _clist, ArrayList _rcvlist, String server, String port, int slen, int rlen) throws Exception
  {
      VOBJ _rvobj = new VOBJ();
      HashMap _rcvrec = null;
      long  _size=0;
      ArrayList _recstr= null;
      HashMap _rec = null;
      boolean _last = false;

      int _portnum=0;
      if(_isNumber(port))
      {
          _portnum = Integer.parseInt( port);
      }else {
          String[] namex = null;
          if( ( namex = getNameset(port) ) ==null)
          {
              _portnum = _vobj.getRecord().getInt(port);
          }else {
              _portnum = _dobj.getRetObject(namex[0]).getRecord().getInt(namex[1]);
          }
      }

      String _address ="";
      if(_isAddress(server))
      {
          _address = server.trim();
      }
      else
      {
          String[] namex = null;
          if( ( namex = getNameset(server ) ) ==null)
          {
              _address = _vobj.getRecord().get(server).trim();
          }else {
              _address = _dobj.getRetObject(namex[0]).getRecord().get(namex[1]).trim();
          }
      }

      XocClt _sclt = new XocClt(_address,  _portnum);

     int _start =0;
     int _idx=0;
     byte[] _sendbyte = null;  //record bytes
     byte[] _cbyte = null;     //column bytes
     _vobj.first();
     while(_vobj.next())
     {
       _rec = _vobj.getRecord().getRecord();
       _recstr = _getRecordByteArrayData(_dobj, _clist ,_rec, _size, _last );
       _start=0;
       _sendbyte = new byte[slen];
       for(int i=0;i<_recstr.size();i++)
       {
         _idx=0;
         _cbyte =(byte[])_recstr.get(i);
         for(int j=_start;j<_cbyte.length+_start;j++)
         {
           _sendbyte[j] = _cbyte[_idx];
           _idx++;
         }
         _start = _start+_cbyte.length;
       }

       try
       {
         _rec.put("SCLTERR","");
         _sendbyte = this.setNullFilter(_sendbyte);
         byte[] _rcv=_sclt._sendDuplex(_sendbyte,rlen);
         if(_rcv.length != rlen)
         {
           System.out.println( "���Data���� :"+ new String(_sendbyte));
           throw new Exception("���� Total Layout Length�� ��ġ �����ʽ��ϴ�.("+rlen+":" +_rcv.length +")");
         }
         _rcvrec = getRcvRecord(_rcv, _rcvlist);
         _rec.put("SCLTERR","1");
       }catch(Exception e){
         _rec.put("SCLTERR","-1");
         _vobj.setRecord(_rec);
         _dobj.setRetObject(_vobj);

         throw e;
       }
       _vobj.setRecord(_rec);
       _rvobj.addRecord(_rcvrec);
     }
     _dobj.setRetObject(_vobj);
     return _rvobj;
   }




   private void  _writeMultiSimplexServer(DOBJ _dobj, VOBJ _vobj, ArrayList _clist,  String server, String port, int len) throws Exception
 {
     long  _size=0;
     ArrayList _recstr= null;
     HashMap _rec = null;
     boolean _last = false;

     int _portnum=0;
     if(_isNumber(port))
     {
         _portnum = Integer.parseInt( port);
     }else {
         String[] namex = null;
         if( ( namex = getNameset(port) ) ==null)
          {
              _portnum = _vobj.getRecord().getInt(port);
          }else {
              _portnum = _dobj.getRetObject(namex[0]).getRecord().getInt(namex[1]);
          }
      }

      String _address ="";
      if(_isAddress(server))
      {
          _address = server.trim();
      }
      else
      {
          String[] namex = null;
          if( ( namex = getNameset(server ) ) ==null)
          {
              _address = _vobj.getRecord().get(server).trim();
          }else {
              _address = _dobj.getRetObject(namex[0]).getRecord().get(namex[1]).trim();
          }
      }


     XocClt _sclt = new XocClt(_address,   _portnum);
     try
     {
         _sclt._connetMultiSocket();
     }
     catch(Exception e)
     {
         System.out.println( "Socket Connection Error : MultiSimplex Connection:");
         _sclt._closeMultiSocket();
         throw e;
     }

     int _start =0;
     int _idx=0;
     byte[] _sendbyte = null;  //record bytes
     byte[] _cbyte = null;     //column bytes

     try
     {
         _vobj.first();
         while(_vobj.next())
         {
             _rec = _vobj.getRecord().getRecord();
             _recstr = _getRecordByteArrayData(_dobj, _clist ,_rec, _size, _last);
             _start=0;
             _sendbyte = new byte[len];
             for(int i=0;i<_recstr.size();i++)
             {
                 _idx=0;
                 _cbyte =(byte[])_recstr.get(i);
                 for(int j=_start;j<_cbyte.length+_start;j++)
                 {
                     _sendbyte[j] = _cbyte[_idx];
                     _idx++;
                 }
                 _start = _start+_cbyte.length;
             }
             _rec.put("SCLTERR","");
             _sendbyte = this.setNullFilter(_sendbyte);
             _sclt._sendMultiSimplex(_sendbyte);
             _rec.put("SCLTERR","1");
             _vobj.setRecord(_rec);
         }

     }catch (Exception ex)
     {
         ex.printStackTrace();
         _rec.put("SCLTERR","-1");
         _vobj.setRecord(_rec);
         _dobj.setRetObject(_vobj);
         throw ex;
     }finally
     {
         _sclt._closeMultiSocket();
     }
     _dobj.setRetObject(_vobj);
 }

   private VOBJ  _writeMultiDuplexServer(DOBJ _dobj, VOBJ _vobj, ArrayList _clist, ArrayList _rcvlist, String server, String port, int slen, int rlen) throws Exception
   {
     VOBJ _rvobj = new VOBJ();
     HashMap _rcvrec = null;
     long  _size=0;
     ArrayList _recstr= null;
     HashMap _rec = null;
     boolean _last = false;

     int _portnum=0;
      if(_isNumber(port))
      {
          _portnum = Integer.parseInt( port);
      }else {
          String[] namex = null;
          if( ( namex = getNameset(port) ) ==null)
          {
              _portnum = _vobj.getRecord().getInt(port);
          }else {
              _portnum = _dobj.getRetObject(namex[0]).getRecord().getInt(namex[1]);
          }
      }

      String _address ="";
      if(_isAddress(server))
      {
          _address = server.trim();
      }
      else
      {
          String[] namex = null;
          if( ( namex = getNameset(server ) ) ==null)
          {
              _address = _vobj.getRecord().get(server).trim();
          }else {
              _address = _dobj.getRetObject(namex[0]).getRecord().get(namex[1]).trim();
          }
      }
     XocClt _sclt = new XocClt(_address,  _portnum);
     try
     {
       _sclt._connetMultiSocket();
     }
     catch(Exception e)
     {
       _sclt._closeMultiSocket();
       throw e;
     }

     int _start =0;
     int _idx=0;
     byte[] _sendbyte = null;  //record bytes
     byte[] _cbyte = null;     //column bytes

     try
     {
       _vobj.first();
       while(_vobj.next())
       {
         _rec = _vobj.getRecord().getRecord();
         _recstr = _getRecordByteArrayData(_dobj, _clist ,_rec, _size, _last);
         _start=0;
         _sendbyte = new byte[slen];
         for(int i=0;i<_recstr.size();i++)
         {
           _idx=0;
           _cbyte =(byte[])_recstr.get(i);
           for(int j=_start;j<_cbyte.length+_start;j++)
           {
             _sendbyte[j] = _cbyte[_idx];
             _idx++;
           }
           _start = _start+_cbyte.length;
         }
         _rec.put("SCLTERR","");
         _sendbyte = this.setNullFilter(_sendbyte);
         byte[] _rcv=_sclt._sendMultiDuplex(_sendbyte,rlen);
         if(_rcv.length != rlen)
         {
           System.out.println("Multi-Duplex:���Data ���̿���:"+ new String(_sendbyte) );
           throw new Exception("���� Total Layout Length�� ��ġ �����ʽ��ϴ�.("+rlen+":" +_rcv.length +")");
         }
         _rcvrec = getRcvRecord(_rcv, _rcvlist);
         _rec.put("SCLTERR","1");

         _vobj.setRecord(_rec);
         _rvobj.addRecord(_rcvrec);
       }
     }catch(Exception ex)
     {
       ex.printStackTrace();
       _rec.put("SCLTERR","-1");
       _vobj.setRecord(_rec);
       _dobj.setRetObject( _vobj);
       throw ex;
     }
     finally
     {
       _sclt._closeMultiSocket();
     }
     _dobj.setRetObject(_vobj);
     return _rvobj;
   }

   private HashMap getRcvRecord(byte[] _data, ArrayList _rlist) throws Exception
   {
       HashMap _rtn =new HashMap();
       XcltLayoutObject _xcol = null;
       int _start=0;
       int _idx=0;
       byte[] _value = null;
       String _str ="";
       for(int i=0; i<_rlist.size(); i++)
       {
           _xcol = (XcltLayoutObject)_rlist.get(i);
           _idx =0;
           _value = new byte[_xcol._getLength()];
           for(int j=_start;j<_xcol._getLength() + _start;j++)
           {
               _value[_idx] = _data[j];
               _idx++;
           }
           _str =getNullFilter(_value);
           if(!_xcol._getCharset().trim().equals(""))  //charset ��ȯó�� �ʿ��
           {
               _str = new String(_str.getBytes(),_xcol._getCharset());
           }
           if(_xcol._getDelim().equals("1"))  //trimó�� ����
           {
               _rtn.put(_xcol._getColumnnm(), _str);
           }
           else
           {
               _rtn.put(_xcol._getColumnnm(),_str);
           }
       }
       return _rtn;
  }
  private String getNullFilter(byte[] bi)
  {
      for(int i=0;i<bi.length;i++)
      {
          if(bi[i] =='\0' ){
              bi[i]=0x20;
          }
      }
      return new String(bi);
  }
  private byte[] setNullFilter(byte[] bi)
  {
      for(int i=0;i<bi.length;i++)
      {
          if(bi[i] =='\0' )
          {
              bi[i]=0x20;
          }
      }
      return bi;
  }
  private String CvsString(String value,String _charset)
  {
    try
    {
      value = new String(value.getBytes(), _charset );
    }catch(java.io.UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return value;
  }

}



