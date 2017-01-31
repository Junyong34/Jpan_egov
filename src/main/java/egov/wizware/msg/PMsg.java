package egov.wizware.msg;

import java.sql.Connection;
import java.util.*;
import java.sql.SQLException;
import java.io.*;
import java.util.Stack;
import egov.wizware.com.CONFIG;
import egov.wizware.com.*;

public class PMsg {

  private OutputStream _3122=null;
  private String _6029="";
  private char[] _6455=null;;
  private boolean _3869=true;
  private Stack   _6683=null;
  private String  _452="";
  private String  _getTotalseq="1";

  public PMsg() {
    this._6683 = new Stack();
  }

  public void _1433(String _4001){
    this._452 = _4001;
  }
  public String  _getUNIKEY(){
    return this._452 ;
  }

  public void _1841(String _6026){
    this._6029 = _6026;
  }
  public String _5300(){
    return this._6029;
  }

  public void _1913(char[] DEBUG){
    this._6455 = DEBUG;
  }
  public char[] _5441(){
    if(this._6455 == null) {
      this._2807(20,"DEBUG TRACE INFORMATION NOT FOUNDED","err");
    }
    return this._6455;
  }


  public String _7199(){
    String _2366="";
    if(_5441()[19]=='1'){
      ArrayList _7478 = new ArrayList();
      while(this._6683.isEmpty()){
        _7478.add(this._6683.pop());
      }
      for(int i=_7478.size()-1;i>=0;i--){
        _2366 = _2366 + _7478.get(i).toString() +" ";
        this._6683.push( _7478.get(i));
      }
    }
    return _2366;
  }

  public void _7241(String code){
     if(_5441()[19]=='1'){
       this._6683.push(code);
     }
  }


  public void _6713(){
    String _6146="";
    int _3848=0;
    if(_5441()[19]=='1') {
      while(!this._6683.isEmpty()){
        _6146 = _6146 +" " + this._6683.pop();
        _3848++;
        if(_3848 == 15){
          this._2807(20,_6146,"msg");
          System.out.println(_6146);
          _6146 ="       ";
          _3848=0;
        }
      }
      this._2807(20,_6146,"msg");
      System.out.println(_6146);
    }
  }

  public void _1619(OutputStream _3089){
    this._3122 = _3089;
  }
  public OutputStream _getOUTSTM(){
    return this._3122;
  }


  public void _6503(Connection _6428, ArrayList _6791){
    try{
      if(_6428 != null && _6428.isClosed()!=true){
        _6428.close();
      }
    }catch(Exception e){
      System.out.println("DEFAULT CONNECTION PFDB CLOSE 오류");
      this._2849(e,"DEFAULT CONNECTION PFDB CLOSE 오류");
      e.printStackTrace();
    }

    for(int i=0;i<_6791.size();i++){
      Connection _6776 = (Connection)_6791.get(i);
      try {
        if(_6776 != null && _6776.isClosed() != true) {
          _6776.close();
        }
      }catch(Exception e){
        System.out.println("EACH PROCESS TYPE CONNECTION CLOSE 오류(" +i + ")" );
        this._2849(e,"EACH PROCESS TYPE CONNECTION CLOSE 오류(" +i + ")" );
        e.printStackTrace();
      }
    }
  }


  public void _2810(int NUM, VOBJ _227, String _4223 ){
    try{
      String _7734="";
      switch(NUM){
        case 1: _7734="INPUT";
          break;
        case 2: _7734="OUTPUT";
          break;
        case 3: _7734="MIDDLE";
          break;
      }


      if(_4223.equals("")){
          _4223 =this._6029;
      }

      if (this._5441()[NUM] == '1' && this._3869==true)
          this._2846_7583(_4223, _7734, _227);

    }catch(Exception e){
      e.printStackTrace();
      this._2849(e,_4223);
    }
  }

  public void _2807(int _NUM,   String _4223 , String _3407){
    try{

        if (_NUM==20 || ( this._5441()[_NUM] == '1' && this._3869==true)) {
            this._2846("&"+_3407);
            this._2846(_4223);
            this._2846("!"+_3407);
            if(_3407.equals("err")) this._2843();
        }

    }catch(Exception e){
      e.printStackTrace();
      this._2849(e,_4223);
    }
  }





   public void _2846_7583(String _4223, String _42231 , VOBJ vOBJ){
     try{
       if(this._3122 != null){

       if(vOBJ != null &&  vOBJ.getRecordCnt()>0) {
         HashMap _4358 = null;
         ArrayList _7475 = vOBJ.getColumnNames();
         this._2846("&data");
         this._2846(_4223+"] ["+_42231+":"+vOBJ.getName()+"] ["+vOBJ.getRecordCnt()+"]");
         String _7028="";
         String _6899="";
         for(int i=0;i<_7475.size();i++)
           _7028 = _7028 +":)"+_7475.get(i);
         this._2846(_7028);

         boolean _1340 = false;
         if(vOBJ.getRecordCnt() > 200) {
           _1340 = true;
         }

         vOBJ.first();
         while(vOBJ.next()){
             _4358 = vOBJ.getRecord().getRecord();
             if(_1340 == true){
                 if(vOBJ.current > 200  ){
                     continue;
                 }
             }

             for(int ii=0;ii<_7475.size();ii++){
                 _6899 = _6899 +":)"+ _4358.get(_7475.get(ii));
             }
             this._2846("["+(vOBJ.current+1)+"] "+_6899);
             _6899="";
         }

         if(_1340 == true) {
           this._2846("|"+_4223+"] Record가 200이상은 Display하지 않습니다. Display Count는 " + vOBJ.getRecordCnt() + " 입니다");
         }

       }else{
         this._2846("|"+_4223+"] IS NOT FOUNDED RECORDS ");
       }
       this._2846("!data");
     }

     }catch(Exception e){
       e.printStackTrace();
     }
   }

  public void _PRINTLX(int _lccode ,String ip){
    if(_lccode == -1){
      System.out.println("등록되지 않은 IP입니다.(" + ip +")");
    }else if(_lccode == -2){
      System.out.println("해당 라이센스 VERSION 오류가 발생 했습니다.");
    }else if( _lccode == -9) {
      System.out.println("서버 라이센스 오류 입니다.");
    }
  }

  public void _2846(String _3170, OutputStream _3122){
    BufferedWriter _2264 = new BufferedWriter(new OutputStreamWriter(_3122));
    try{
      Thread th = new Thread();
      th.sleep(1);
      //_2264.write(_3170);
      _2264.write(0 +"|" +"&"+1+"|"+ _3170);
      _2264.newLine();
      _2264.flush();
    }catch(java.net.SocketException se){
      se.printStackTrace();
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void _2846(Object _3170){
    if(this._3122 != null){
      BufferedWriter _2264 = new BufferedWriter(new OutputStreamWriter(_3122));
      if(_3170 instanceof String){
        try{
          Thread th = new Thread();
          th.sleep(1);
          _2264.write(this._452 +"|" +"&"+_getTotalseq +"|"+ _3170.toString());
          _2264.newLine();
          _2264.flush();
        }catch(java.net.SocketException se){
        }catch(Exception e){
          e.printStackTrace();
        }
      }else if(_3170 instanceof Exception){
        Exception _6014=  (Exception)_3170;
        StackTraceElement[] _7571=  _6014.getStackTrace();
        try{
          Thread th = new Thread();
          this._2846(_6014.toString());
          for(int i=0;i<_7571.length;i++){
            th.sleep(1);
            _2264.write(this._452 + "|" + "&" + _getTotalseq + "|" + _7571[i].toString());
            _2264.newLine();
            _2264.flush();
          }
          this._2843X();
        }catch(java.net.SocketException se){
        }catch(Exception e){
          this._2843X();
          e.printStackTrace();
        }
      }else if(_3170 instanceof StackTraceElement[]){
        StackTraceElement[] _7571=  (StackTraceElement[])_3170;
       try{
         Thread th = new Thread();
         for(int i=0;i<_7571.length;i++){
           th.sleep(1);
           _2264.write(this._452 + "|"  + "&" + _getTotalseq + "|" + _7571[i].toString());
           _2264.newLine();
           _2264.flush();
         }
       }catch(java.net.SocketException se){
       }catch(Exception e){
         this._2843X();
         e.printStackTrace();
       }
      }
    }
  }

  public void _2843(){
    if(this._3122 != null){
      BufferedWriter _2270 = new BufferedWriter(new OutputStreamWriter(_3122));
      try{
        Thread th = new Thread();
        th.sleep(1);
        _2270.write(this._452 +"|" +"!" +_getTotalseq );
        _2270.newLine();
        _2270.flush();
      }catch(java.net.SocketException e){
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  public void _2843X(){
    if(this._3122 != null){
      BufferedWriter _2270 = new BufferedWriter(new OutputStreamWriter(_3122));
      try{
        Thread th = new Thread();
        th.sleep(1);
        _2270.write(this._452 +"|" +"!END");
        _2270.newLine();
        _2270.flush();
      }catch(java.net.SocketException e){
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  private void _2843Y(){
     if(this._3122 != null){
       BufferedWriter _2270 = new BufferedWriter(new OutputStreamWriter(_3122));
       try{
         Thread th = new Thread();
         th.sleep(1);
         _2270.write(this._452 +"|" +"!1");
         _2270.newLine();
         _2270.flush();
       }catch(java.net.SocketException e){
       }catch(Exception e){
         e.printStackTrace();
       }
     }
  }

  public void _2849(Exception _3170, String _3437){
      if(_3122 != null){
        this._2846("&err");
        this._2846(_3437);
        this._2846(_3170);
        this._2846("!err");
        this._2843();
      }
  }
}

