package egov.wizware.com;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.*;
import java.util.*;
public class XocClt
{
  private String _SERVERIP="";
  private int    _SERVERPORT=0;
  private Socket _msocket= null;
  private BufferedOutputStream _msout= null;
  public XocClt() {
  }
  public XocClt(String _ipadd, int _port)
  {
    _SERVERIP = _ipadd;
    _SERVERPORT=_port;
  }
  public ArrayList _sendDuplex(ArrayList _sendlist, int _relen) throws Exception
  {
    ArrayList _rtn = new ArrayList();
    byte[] _senddata = null;
    byte[] _rcvdata = null;
    Socket 	_socket= null;
    BufferedOutputStream _sout= null;
    try
    {
      _socket = new Socket( _SERVERIP , _SERVERPORT ); //서버 ip 주소, 포트 세팅
      Thread _th = new Thread();
      for(int i=0;i<_sendlist.size();i++)
      {
        _senddata = (byte[])_sendlist.get(i);
        //System.out.println(" [Client SEND DATA:" +new String(_senddata) );
        _sout= new BufferedOutputStream(new DataOutputStream(_socket.getOutputStream()));
        _sout.write(_senddata);
        _sout.flush();
        //System.out.println(" [Client] : " + "DATA 보내기 완료" );
        _th.sleep(100);

        _rcvdata = new byte[_relen];
        _socket.getInputStream().read(_rcvdata);
        //System.out.println(" [Client] : " + new String(_rcvdata) );
        _rtn.add(_rcvdata);
        _th.sleep(100);
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw e;
    }finally{
      try{
        if( _sout != null)	_sout.close();
        if( _socket != null) _socket.close();
      }catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return _rtn;
  }

  public void   _connetMultiSocket() throws Exception
  {
    try
    {
      _msocket = new Socket( _SERVERIP , _SERVERPORT ); //서버 ip 주소, 포트 세팅
      _msout= new BufferedOutputStream(new DataOutputStream(_msocket.getOutputStream()));
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw e;
    }
  }
  public void   _closeMultiSocket() throws Exception
  {
    try
    {
      if( _msout != null)  _msout.close();
      if( _msocket != null) _msocket.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ex;
    }
  }

  public byte[] _sendMultiDuplex(byte[] _senddata, int _relen) throws Exception
  {
    byte[] ss = null;
    try
    {
      _msout.write(_senddata);
      _msout.flush();
      ss = new byte[_relen];
      _msocket.getInputStream().read(ss);
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw e;
    }
    return ss;
  }
  public byte[] _sendDuplex(byte[] _senddata, int _relen) throws Exception
  {
    byte[] ss = null;
    Socket 	_socket= null;
    BufferedOutputStream _sout= null;
    try
    {
      _socket = new Socket( _SERVERIP , _SERVERPORT ); //서버 ip 주소, 포트 세팅
      //System.out.println("XXXXXXXXXXXXX:"+ _socket.getSoTimeout() +":" +_socket.getTcpNoDelay() );
      //System.out.println(" [Client SEND DATA:" +new String(_senddata) );
      _sout= new BufferedOutputStream(new DataOutputStream(_socket.getOutputStream()));
      _sout.write(_senddata);
      _sout.flush();
      //System.out.println(" [Client] : " + "DATA 보내기 완료" );

      ss = new byte[_relen];
      _socket.getInputStream().read(ss);
      //_sout.flush();
      //System.out.println(" [Client] : " + new String(ss) );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw e;
    }finally{
      try{
        if( _sout != null)	_sout.close();
        if( _socket != null) _socket.close();
      }catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return ss;
  }
  public boolean _sendSimplex(ArrayList _sendlist) throws Exception{
    boolean _rtn = true;
    Socket 	socket= null;
    BufferedOutputStream sout= null;
    try
    {
      socket = new Socket( _SERVERIP , _SERVERPORT ); //서버 ip 주소, 포트 세팅
      byte[] _senddata=null;
      for(int i=0;i<_sendlist.size();i++){
        _senddata =(byte[])_sendlist.get(i);
        //System.out.println(" [Client SEND DATA:" +new String(_senddata) );
        sout= new BufferedOutputStream(new DataOutputStream(socket.getOutputStream()));
        sout.write(_senddata);
        sout.flush();
        //System.out.println(" [Client] : " + "DATA 보내기 완료" );
        Thread _th = new Thread();
        _th.sleep(100);
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw e;
    }finally{
      try{
        if( sout != null)	sout.close();
        if( socket != null) 	socket.close();

      }catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return true;
  }
  public boolean _sendSimplex(byte[] _senddata) throws Exception{
    boolean _rtn = true;
    Socket 	socket= null;
    BufferedOutputStream sout= null;
    try
    {
      socket = new Socket( _SERVERIP , _SERVERPORT ); //서버 ip 주소, 포트 세팅
      //System.out.println(" [Client SEND DATA:" +new String(_senddata) );
      sout= new BufferedOutputStream(new DataOutputStream(socket.getOutputStream()));
      sout.write(_senddata);
      sout.flush();
      //System.out.println(" [Client] : " + "DATA 보내기 완료" );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw e;
    }finally{
      try{
        if( sout != null)	sout.close();
        if( socket != null) 	socket.close();
      }catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return true;
  }
  public boolean _sendMultiSimplex(byte[] _senddata) throws Exception{
    boolean _rtn = true;
    try
    {
      _msout.write(_senddata);
      _msout.flush();
    }
    catch( Exception e )
    {
      _rtn= false;
      e.printStackTrace();
      throw e;
    }
    return _rtn;
  }


}
