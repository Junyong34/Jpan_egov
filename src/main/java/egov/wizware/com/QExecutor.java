package egov.wizware.com;

import java.util.HashMap;
import java.util.*;
import java.sql.*;
import java.io.*;
import egov.wizware.log.DLog;
import java.text.DecimalFormat;


public class QExecutor  extends CommonTypes{

  String _2252 ="";
  HashMap _3062 = null;
  boolean _2513 = true;
  int  _6248mode=0;
  private DLog     _19G= null;  //log ó�� ���

  public QExecutor(){}
  public QExecutor(DOBJ _9823){
      _6248mode = _9823.getDispmode();
      _19G = _9823._getDlg();
  }
  public QExecutor(int _4391){
      _6248mode=_4391;
  }

  public void setSchema(String name) {
    this._2252 = name;
  }
  public void DispSelectSql(SQLObject _1178)  {
      if(_6248mode == 1) return;
      String _2675 = "";
      _2675 = _1178.getCvtsql();

      String _737 = "", _632 = "";
      String name = "";

      SQLParam _sparm = null;
      for (int i = 0; i < _1178.getColumninfo().size(); i++)
      {

          _737 = _2675.substring(0, _2675.indexOf("?"));
          _632 = _2675.substring(_2675.indexOf("?") + 1);

          _sparm = (SQLParam) _1178.getColumninfo().get(i);
          switch (_sparm.getColumnType())
          {
          case INTEGER:
              _2675 = _737 + _sparm.getInt() + _632;
              break;

          case LONG:
              _2675 = _737 + _sparm.getLong() + _632;
              break;

          case NUMBER:
              _2675 = _737 + _sparm.getDouble() + _632;
              break;

          case STRING:
              _2675 = _737 + "'" + _sparm.getString() + "'" + _632;
              break;

          case BLOB:
              _2675 = _737 + "'" + "BLOB" + "'" + _632;
              break;

          case CLOB:
              _2675 = _737 + "'" + "CLOB" + "'" + _632;
              break;

          default:
              _2675 = _737 + "'" + _sparm.getString() + "'" + _632;
              break;

          }
      }
      _19ginfo("SQL:" + _2675);
  }

   public String getQUERYSTRING(SQLObject _1178, String _4238)  {

    String _2675 = "";
    _2675 = _1178.getCvtsql();

    String _737 = "", _632 = "";
    String _3374 = "";

    SQLParam _sparm = null;
    for (int i = 0; i < _1178.getColumninfo().size(); i++) {

      _737 = _2675.substring(0, _2675.indexOf("?"));
      _632 = _2675.substring(_2675.indexOf("?") + 1);

      _sparm = (SQLParam) _1178.getColumninfo().get(i);
      switch (_sparm.getColumnType()) {

        case INTEGER:
          _2675 = _737 + _sparm.getInt() + _632;
          break;

        case LONG:
          _2675 = _737 + _sparm.getLong() + _632;
          break;

        case NUMBER:
          _2675 = _737 + _sparm.getDouble() + _632;
          break;

        case STRING:
          _2675 = _737 + "'" + _sparm.getString() + "'" + _632;
          break;

      case BLOB:
          _2675 = _737 + "'" + "BLOB" + "'" + _632;
          break;

      case CLOB:
          _2675 = _737 + "'" + "CLOB" + "'" + _632;
          break;

      default:
          _2675 = _737 + "'" + _sparm.getString() + "'" + _632;
          break;

      }
    }
    return _4238 + _2675;
  }
  public void _19ginfo(String xxx){
        if(this._19G != null)
        {
            _19G.info(xxx, null);
        }
        else
        {
            System.out.println(xxx);
        }
  }

  public VOBJ executeSql(Object _6773,  SQLObject _1178 ) throws Exception{
      Connection _conn = (Connection) _6773;
      VOBJ _rtn = executeSql(_conn,   _1178 );
      return _rtn;
  }
  public VOBJ executeSql(Connection _6773,  SQLObject _1178 ) throws   Exception
  {
      VOBJ _2435 = null;
      PreparedStatement _2699 = null;
      ResultSet _8980 = null;
      ResultSetMetaData _8978 = null;
      String _2675 = "";

      try
      {
          _2675 = getQuery(_1178);
          _2699 = _6773.prepareStatement(_2675);
          _8980 = _2699.executeQuery();
          _8978 = _2699.getMetaData();

          int _7986=0;
          _2435 = new VOBJ();
          String[][] _7109 = new String[_8978.getColumnCount()][2];
          for (int x = 0; x < _8978.getColumnCount(); x++)
          {
              _7986++;
              _7109[x][0] = _8978.getColumnName(x + 1);
              _7109[x][1] = _8978.getColumnTypeName (x+1);

             ColumnHeadInfo cinfo = new ColumnHeadInfo();
             cinfo.setName(_8978.getColumnName(x + 1));
             cinfo.setType(_8978.getColumnType(x+1));
             cinfo.setTypename (_8978.getColumnTypeName(x+1));
             cinfo.setNumbersize (_8978.getPrecision(x+1));
             cinfo.setRoundsize (_8978.getScale(x+1));
             cinfo.setLength (_8978.getColumnDisplaySize (x+1));
             _2435.setHeadColumn(_7109[x][0], cinfo);
          }

          while (_8980.next())
          {
              _2435.makeRecord();
              for (int x = 0; x < _7986; x++)
              {

                  if(_7109[x][1] != null){

                      if(_7109[x][1].equals("LONG"))
                      {
                          Reader _8979 = _8980.getCharacterStream (_7109[x][0]);
                          _2435.setColumn(_7109[x][0], _8973(_8979));
                      }
                      else if(_7109[x][1].equals("BLOB"))
                      {
//                          System.out.println(_7109[x][1] +":::::" +  _8980.getBytes(_7109[x][0]));
                          _2435.setColumn  (_7109[x][0], _8980.getBytes(_7109[x][0]));

                      }
                      else if(_7109[x][1].equals("CLOB"))
                      {
                          Reader _8979 = _8980.getCharacterStream (_7109[x][0]);
                          _2435.setColumn(_7109[x][0], _8973(_8979));
                      }
                      else
                      {
                          _2435.setColumn  (_7109[x][0], _8980.getString(_7109[x][0]));

                      }

                  }else
                  {
                      _2435.setColumn(_7109[x][0], "");
                  }
            }
            _2435.addRecord();
        }

      }finally{
         if(_8980 != null)_8980.close();
         if(_2699 != null)_2699.close();
      }
      _2435._setDispmode(_6248mode);
      _2435._19lg(this._19G);
      return _2435;
  }

  public int executeSqlUpdate(Object _6773,  SQLObject _1178 ) throws Exception{
      Connection _conn = (Connection) _6773;
      return executeSqlUpdate(_conn,   _1178 );
  }

  public int executeSqlUpdate(Connection _6773,  SQLObject _1178 ) throws Exception{

      PreparedStatement _2699 = null;
      String _2675 = "";
      int _7100=0 ;
      try{
          _2675 = getQuery(_1178);
          _2699 = _6773.prepareStatement(_2675);
          _7100 = _2699.executeUpdate();
      }finally{
          if(_2699 != null)_2699.close();
      }
      HashMap _377 = new HashMap();
      _377.put("UPDCNT",_7100+"");
      return _7100;
  }

  private String getQuery(SQLObject _1178)
  {
      String _2675 = "";
      _2675 = _1178.getCvtsql();

      String _737 = "", _632 = "";

      SQLParam _sparm = null;
      for (int i = 0; i < _1178.getColumninfo().size(); i++)
      {

          _737 = _2675.substring(0, _2675.indexOf("?"));
          _632 = _2675.substring(_2675.indexOf("?") + 1);

          _sparm = (SQLParam) _1178.getColumninfo().get(i);
          switch (_sparm.getColumnType()) {

          case INTEGER:
              _2675 = _737 + _sparm.getInt() + _632;
              break;

          case LONG:
              _2675 = _737 + _sparm.getLong() + _632;
              break;

          case NUMBER:
              _2675 = _737 + _sparm.getDouble() + _632;
              break;

          case STRING:
              _2675 = _737 + "'" + _sparm.getString() + "'" + _632;
               break;
           }
       }
       return _2675;
   }

   public VOBJ executeQuery(Object _6773,  SQLObject _1178 ) throws Exception
   {
       Connection _conn = (Connection) _6773;
       return executeQuery(_conn,   _1178 );
   }

   //----------------------------  null column start----------------------
   private SQLObject getNullColumnSQLObject(SQLObject sqlx)
   {
       ArrayList plist = new  ArrayList();
       SQLParam sparm = null;
        for (int i = 0; i < sqlx.getColumninfo().size(); i++)
        {

          sparm = (SQLParam) sqlx.getColumninfo().get(i);
          switch (sparm.getColumnType())
          {
            case NULL:
              SQLNullString _nl = new SQLNullString(i, sparm.getString());
              plist.add(0,_nl);
              break;
          }
        }

        if(plist.size() > 0)
        {
          String qry = getNullFilterSql(sqlx,  plist);
          System.out.println("getNullColumnSQLObject:"+qry);
          sqlx.setSql(qry);
        }
        return sqlx;
     }
     private String getNullFilterSql(SQLObject sqlx, ArrayList plist)
     {
       String _qry = sqlx.getCvtsql();
       SQLNullString _nl = null;
       for(int i=0;i<plist.size();i++)
       {
         _nl = (SQLNullString) plist.get(i);
         _qry = getFilterSql(_nl.getNo(), _qry, _nl.getValue());
       }
       return _qry;
     }
     private String getFilterSql(int no, String qry, String str)
     {
       int  ridx =0;
       StringBuffer sbuf = new StringBuffer();
       int sidx =0;
       char[] _tochar = qry.toCharArray();
       for(int i=0;i<_tochar.length;i++)
       {
         if(sidx == 1 && _tochar[i] != '\'')
         {
           sbuf.append(_tochar[i]);
            continue;
         }
         if(_tochar[i] =='\'')
         {
           sbuf.append(_tochar[i]);
           if(sidx == 0)
           {
             sidx++;
             continue;
           }

           if(sidx == 1)
           {
             sidx =0;
             continue;
           }
         }

         if(_tochar[i] == '?')
         {
           if(no==ridx)
           {
             sbuf.append(str);
           }
           else
           {
             sbuf.append(_tochar[i]);
           }
           ridx++;
         }
         else
         {
           sbuf.append(_tochar[i]);
         }
       }

       System.out.println(sbuf.toString());
       return sbuf.toString();
     }


   //----------------------------  null column end ----------------------
   public VOBJ executeQuery(Connection _6773,  SQLObject _1178 ) throws   Exception
   {

       getNullColumnSQLObject( _1178);

       VOBJ _vobj = null;
       PreparedStatement _pstat = null;
       ResultSet _rset = null;
       ResultSetMetaData _rmeta = null;
       String _2675 = "";

       try
       {
           int idx =0;
           _2675 = _1178.getCvtsql();
           _pstat = _6773.prepareStatement(_2675);
           SQLParam _sparm = null;
           for (int i = 0; i < _1178.getColumninfo().size(); i++)
           {
               _sparm = (SQLParam) _1178.getColumninfo().get(i);
               switch (_sparm.getColumnType())
               {
               case INTEGER:
                   _pstat.setInt(idx + 1, _sparm.getInt());
                   idx++;
                   break;

               case LONG:
                   _pstat.setLong(idx + 1, _sparm.getLong());
                   idx++;
                   break;

               case NUMBER:
                   _pstat.setDouble(idx + 1, _sparm.getDouble());
                   idx++;
                   break;

               case STRING:
                   _pstat.setString(idx + 1, _sparm.getString());
                   idx++;
                   break;

               case NULL:
                   break;

               default:
                  _pstat.setString(idx + 1, _sparm.getString());
                  idx++;
                  break;
               }
           }

           _rset = _pstat.executeQuery();
           _rmeta = _pstat.getMetaData();

           _vobj = new VOBJ();
           int _7986=0;
           String[][] _7109 = new String[_rmeta.getColumnCount()][2];
           for (int x = 0; x < _rmeta.getColumnCount(); x++)
           {
               _7986++;
               _7109[x][0] = _rmeta.getColumnName(x + 1);
               _7109[x][1] = _rmeta.getColumnTypeName(x+1);

               ColumnHeadInfo cinfo = new ColumnHeadInfo();
               cinfo.setName(_rmeta.getColumnName(x + 1));
               cinfo.setType(_rmeta.getColumnType(x+1));
               cinfo.setTypename (_rmeta.getColumnTypeName(x+1));

               try
               {
                   cinfo.setNumbersize (_rmeta.getPrecision(x+1));
               }
               catch(Exception e)
               {
                   cinfo.setNumbersize (0);
               }

               try
               {
                   cinfo.setRoundsize (_rmeta.getScale(x+1));
               }
               catch(Exception e)
               {
                   cinfo.setRoundsize (0);
               }

               try
               {
                   cinfo.setLength (_rmeta.getColumnDisplaySize (x+1));
               }
               catch(Exception e)
               {
                   cinfo.setLength (0);
               }

               _vobj.setHeadColumn(_7109[x][0], cinfo);

              // _vobj.setHeadColumnType(_7109[x][0] , _7109[x][1] );
           }

           while (_rset.next())
           {
               _vobj.makeRecord();
               for (int x = 0; x < _7986; x++)
               {

                   if(_7109[x][1] != null)
                   {
                      // System.out.println(_7109[x][1] +":::::" +  _7109[x][0] );
                       if(_7109[x][1].equals("LONG"))
                       {
                           Reader _8979 = _rset.getCharacterStream (_7109[x][0]);
                           _vobj.setColumn(_7109[x][0], _8973(_8979));
                       }
                       else if(_7109[x][1].equals("BLOB"))
                       {
                           //byte[] _cbl= _rset.getBytes(_7109[x][0]);
                           byte[] _cbl=_getInputStreamColumn(_rset.getBinaryStream(_7109[x][0]));
                           //System.out.println("BLOB:" + _7109[x][0] +":" + _cbl +":" + _cbl.length );
                           _vobj.setColumn(_7109[x][0], _cbl );
                       }
                       else if(_7109[x][1].equals("CLOB"))
                       {
                    	 
                           Reader _8979 = _rset.getCharacterStream (_7109[x][0]);
                           System.out.println();
                           _vobj.setColumn(_7109[x][0], _8973(_8979));
                       }
                       else if(_7109[x][1].equals("NUMBER"))
                       {
                           _vobj.setColumn  (_7109[x][0], FormatString(_rset.getString(_7109[x][0])));
                       }
                       else
                       {
                           _vobj.setColumn  (_7109[x][0], _rset.getString(_7109[x][0]));
                       }

                   }
                   else
                   {
                       _vobj.setColumn(_7109[x][0], "");
                   }
               }
               _vobj.addRecord();
           }

       }finally{
           if(_rset != null)_rset.close();
           if(_pstat != null)_pstat.close();
       }

       _vobj._setDispmode(_6248mode);
       _vobj._19lg(this._19G);
       return _vobj;
   }

   public String FormatString(String _71, String _68)
   {
       if(_68 == null || _68.equals("")) return "";
       String _65;
       double _137 = Double.parseDouble(_68.trim());
       DecimalFormat _6272 = new DecimalFormat(_71);
       _65 = _6272.format(_137);
       return _65;
   }
   public String FormatString(String _68)
   {
       if(_68 == null || _68.equals("")) return "";
       String _65;
       double _137 = Double.parseDouble(_68.trim());
       DecimalFormat _6272 = new DecimalFormat("###0.######");
       _65 = _6272.format(_137);
       return _65;
   }
   public String FormatString(String format, double _137)
   {
       DecimalFormat _6272 = new DecimalFormat(format);
       return _6272.format(_137);
   }
   public String FormatString(double _137)
   {
       DecimalFormat _6272 = new DecimalFormat("###0.######");
       return _6272.format(_137);
   }


   private byte[] _getInputStreamColumn(InputStream _in) throws Exception{
       byte[] bin = new byte[1024];
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       int cnt=0;
       int sum=0;
       while((cnt=_in.read(bin)) != -1 ){
           bout.write(bin, 0, cnt);
           sum += cnt;
       }
       bout.close();
       System.out.println("READ SIZE:" +sum  +":" + bout.toByteArray().length);
       return bout.toByteArray();
  }


   private String _8973(Reader _4205) throws Exception{
       char[] _8977 = new char[100];
       StringBuffer _2279 = new StringBuffer();
       if(_4205 != null){
           while((_4205.read(_8977)) != -1){
               _2279.append(_8977);
               _8977 = new char[100];
           }
           _4205.close();
       }else{
           _2279.append("");
       }
      return _2279.toString();
  }


  public int executeUpdate(Object _6773,  SQLObject _1178 ) throws Exception
  {
      Connection _conn = (Connection) _6773;
      return executeUpdate(_conn,   _1178 );
  }
  public int executeUpdate(Connection _6773,  SQLObject _1178 ) throws Exception
  {

      getNullColumnSQLObject( _1178);

      PreparedStatement _2699 = null;
      String _2675 = "";
      int _7100=0 ;
      _2675 = _1178.getCvtsql();

      try
      {
          int idx =0;
          _2699 = _6773.prepareStatement(_2675);
          SQLParam _sparm = null;
          for (int i = 0; i < _1178.getColumninfo().size(); i++)
          {

              _sparm = (SQLParam) _1178.getColumninfo().get(i);
              switch (_sparm.getColumnType())
              {

              case INTEGER:
                  _2699.setInt(idx + 1, _sparm.getInt());
                  idx++;
                  break;

              case LONG:
                  _2699.setLong(idx + 1, _sparm.getLong());
                  idx++;
                  break;

              case NUMBER:
                  _2699.setDouble(idx + 1, _sparm.getDouble());
                  idx++;
                  break;

              case STRING:
                  _2699.setString(idx + 1, _sparm.getString());
                  idx++;
                  break;

              case BLOB:
                  _2699.setBinaryStream(idx + 1,
                                        new ByteArrayInputStream(_sparm.getBlob()),
                                        _sparm.getBlob().length);
                  idx++;
                  break;

              case CLOB:
                  _2699.setCharacterStream(idx + 1,
                                           new StringReader(_sparm.getString()),
                                           _sparm.getString().length());
                  idx++;
                  break;

              case NULL:
                  break;

              default:
                  _2699.setString(idx + 1, _sparm.getString());
                  idx++;
                  break;
              }
          }

          _7100 = _2699.executeUpdate();
      }
      finally
      {
          if(_2699 != null)_2699.close();
      }

      HashMap _377 = new HashMap();
      _377.put("UPDCNT",_7100+"");
      return _7100;
  }


  public HashMap CopyHash(HashMap _4220, Object _3338)
  {
    HashMap _2468 = new HashMap();

    if (_3338 instanceof String)
    {
      _2468.put(_3338, _4220.get(_3338));
    }
    else if (_3338 instanceof String[]) {
      String[] _665 = (String[]) _3338;
      for (int i = 0; i < _665.length; i++) {
        _2468.put(_665[i], _4220.get(_665[i]));
      }
    }
    else if (_3338 instanceof ArrayList) {
      ArrayList _665 = (ArrayList) _3338;
      for (int i = 0; i < _665.size(); i++) {
        _2468.put(_665.get(i), _4220.get(_665.get(i)));
      }
    }
    return _2468;
  }


  public String LengthCheck(HashMap _281) {
    return "";
  }

  public void prt(String _7556) {
    //System.out.println(_7556);
    this._19ginfo(_7556);
  }

  public String NullCheckString(Object _305){
    String _2468 ="";
    if(_305 != null){
      String _1073 = (String)_305;
      if(_1073.trim().length() > 0){
        _2468 = _1073.trim();
      }
    }
    return _2468;
  }


  private String getKSC5601(String _281, String _7190) throws Exception{

    if(_281 == null){
      _281="";
    }else {
      try {
        _281 = new String(_281.getBytes(_7190), "KSC5601" );
      }catch(java.io.UnsupportedEncodingException e) {
        e.printStackTrace();
        //System.out.println("FM:CodeCvt:�ѱۺ�ȯ ����(getKSC5601-->"+_7190+")");
        _19ginfo("FM:CodeCvt:�ѱۺ�ȯ ����(getKSC5601-->"+_7190+")");
      }
    }
    return _281;
  }

  private String get8859(String _281, String _7190) throws Exception{

    if(_281 == null){
      _281="";
    }else {
      try {
        _281 = new String(_281.getBytes(_7190), "8859_1" );
      }catch(java.io.UnsupportedEncodingException e) {
        e.printStackTrace();
        //System.out.println("FM:CodeCvt:�ѱۺ�ȯ ����(get 8859_1-->"+_7190+")");
        _19ginfo("FM:CodeCvt:�ѱۺ�ȯ ����(get 8859_1-->"+_7190+")");
      }
    }
    return _281;
  }



  private String getStringcvt(int _2162no, String _899, String _305, String _4205){
    String _6590 ="";
    int _5897=1;

    while(_4205.indexOf(_899) != -1){
      if(_5897 == _2162no){
        _6590 = _6590 + _4205.substring(0,_4205.indexOf(_899));
        _4205 = _305 +  _4205.substring(_4205.indexOf(_899)+_899.length());
        break;
      }else{
        _6590 = _6590 + _4205.substring(0,_4205.indexOf(_899)+_899.length());
        _4205 = _4205.substring(_4205.indexOf(_899)+_899.length());
      }
      _5897++;
   //   System.out.println(inputstr +":" + cvtstr);
    }
    _6590 = _6590 + _4205;
//    System.out.println(cvtstr);
    return _6590;
  }


  public String Ltrim(String _2300) throws Exception {  //VU0090
    String _2366 ="";
    char[] _7244 = _2300.toCharArray();
    int i=0;
    for(i=0;i<_7244.length;i++){
      if(_7244[i] != ' '){
        break;
      }
    }
    for(int ii=i;ii<_7244.length;ii++){
      _2366 = _2366 + _7244[ii];
    }

    return _2366;
  }

  // List _1220(Connection _6773, String procedu_2492, List _4265, int[] _4142,  int[] ou_ttype)
  //'K4[A,B]','SPNAME(VC,VC,C:INT,D:DOUBLE,E:VC)'
  public  ArrayList SPCall(Connection _6773, String _2798, List _4265, int[] _4139, int[] _3086) throws Exception {

      if (_2798 == null || _2798.length() < 1)
        return new ArrayList();

      ArrayList _2471List = new ArrayList();
      CallableStatement _cs = null;

      if (_4265 == null) _4265 = new ArrayList();
      int _9384=0;
      if(_3086 != null) _9384 =  _3086.length;

      int _7451 = _4265.size() + _9384;

      _2798 = "{call " +   _2798;
      if (_7451 == 0)
        _2798 = _2798 + "()}";
      else if (_7451 > 0) {
        _2798 = _2798 + "(?";
        for (int _3845 = 1; _3845 < _7451; _3845++) {
          _2798 = _2798 + ",?";
        }
        _2798 = _2798 + ")}";
      }

      try{
        _cs = _6773.prepareCall(_2798);
        int _7448 = 1;
        int _566;
        if (_4265.size() > 0) {
          for (int i = 0; i < _4265.size(); i++) {
            Object _7445 = _4265.get(i);
            _566 = _4139[i];

            if (_566 == java.sql.Types.INTEGER)
            {
                if(_7445==null || _7445.toString().equals(""))
                {
                    _cs.setInt(_7448,  0 );
                }
                else
                {
                    _cs.setInt(_7448,  Integer.parseInt(_7445.toString()) );
                }
              _7448++;
            }else if (_566 == java.sql.Types.DOUBLE) {
                if(_7445==null || _7445.toString().equals("")) {
                    _cs.setDouble(_7448, 0 );
                }else{
                    _cs.setDouble(_7448, Double.parseDouble(_7445.toString()) );
                }
              _7448++;
            }else if (_566 == java.sql.Types.DATE) {
              _cs.setDate(_7448, (java.sql.Date) _7445);
              _7448++;
            }else if ( _566 == java.sql.Types.VARCHAR) {
              _cs.setString(_7448, (String) _7445);
              _7448++;
            }else {
              _cs.setObject(_7448, (String) _7445);
              _7448++;
            }
           }
         }

         if (_3086!=null && _3086.length > 0) {
           for (int i = 0; i < _3086.length; i++) {
             _566 =_3086[i];
             _cs.registerOutParameter(_7448, _566);
             _7448++;
           }
         }

         _cs.execute();
         _7448 = _4265.size() + 1;
         if (_3086!=null && _3086.length > 0) {
           for (int i = 0; i < _3086.length; i++) {
             _2471List.add(_cs.getObject(_7448));
             _7448++;
           }
         }
       }finally{
         if(_cs != null) _cs.close();
       }
       return _2471List;
   }

}
