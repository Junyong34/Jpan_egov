package egov.wizware.ria;

import org.jdom.input.*;
import org.jdom.*;
import java.io.*;
import java.util.*;
import egov.wizware.com.*;

/*
 단일 Table에 해당 xml 처리 (다중table은 제외 한다)
 <table>   //-- name , tagname 사용에 대한 규약 필요
   <record>
      <column>  </column>   //--name , tagname의 사용에 대한 규약 필요
   </record>
 </table>
*/
public class XObject {
    //- 0: tagname ,1:name 속성처리,  2:column seq로 처리되면 column name정보를 별도로처리
    private int _3329 = 0;
    private ArrayList _7109= null;

    private int _4142 = 0;
    private StringBuffer _2279 = null;
    private File _5777 = null;
    private String _1073 = null;

    public XObject() {
    }
    public void setNametype(int _461){
        this._3329 = _461;
    }
    public void setNames(ArrayList _7109){
        this._7109 = _7109;
    }

    public void setXmlData(Object _3230){

      if(_3230 instanceof StringBuffer){
        _4142 = 0;
        this._2279 = (StringBuffer)_3230;
      }else if(_3230 instanceof String){
        _4142=1;
        this._1073 = (String)_3230;
      }else if(_3230 instanceof File){
        _4142 =2;
        this._5777 = (File)_3230;
      }
    }

    public ArrayList getXmlData(){
        ArrayList _7475 = new ArrayList();
        InputStream _4271 = null;

        try{
          if(_4142 ==0){
            _4271 = new ByteArrayInputStream(this._2279.toString().getBytes());
          }else if(_4142 == 1){
            _4271 = new ByteArrayInputStream(this._1073.getBytes());
          }else if(_4142 == 2){
            _4271 = new FileInputStream(_5777);
          }
        }catch(IOException e){
          e.printStackTrace();
        }

        Document _9127=null;
        SAXBuilder _9128 = new SAXBuilder();
        Element _9129 = null;
        Element _3251 = null;
        HashMap _4358 = null;
        try{
            _9127 = _9128.build(_4271);
            _9129 = _9127.getRootElement();
            List _9137 = _9129.getChildren();
            Iterator _4070 = _9137.iterator();
            while(_4070.hasNext()){
                _4358 = new HashMap();
                _3251 = (Element)_4070.next();
                switch(this._3329){
                case 0:
                    _4358 = this.getRecordElementByTag(_3251);
                    break;
                case 1:
                    _4358 = this.getRecordElementByName(_3251);
                    break;
                case 2:
                    _4358 = this.getRecordElementBySeq(_3251,this._7109);
                    break;
                }
                _7475.add(_4358);
            }
        }catch(Exception je){
            je.printStackTrace();
        }
        return _7475;
    }

    private HashMap getRecordElementByTag(Element _9147){
        HashMap _2594 = new HashMap();
        List list = _9147.getChildren();
        Element _cele = null;
        Iterator _4070 = list.iterator();
        while(_4070.hasNext()){
            _cele = (Element)_4070.next();
            _2594.put(_cele.getName(),_cele.getText());
        }
        return _2594;
    }

    private HashMap getRecordElementByName(Element _9147){
        HashMap _2594 = new HashMap();
        List _9137 = _9147.getChildren();
        Element _cele = null;
        Iterator _4070 = _9137.iterator();
        String _6944="";
        while(_4070.hasNext()){
            _cele = (Element)_4070.next();
            StringTokenizer _1079 = new StringTokenizer(_cele.getAttribute("name").toString(),"'\"");
            _1079.nextToken();
            _6944 = _1079.nextToken();
            _2594.put(_6944, _cele.getText());
            //_2594.put(cele.getAttribute("name").toString(), cele.getText());
            //System.out.println("ELEMENT NAME:"+cele.getAttribute("name").toString() +":"+ columnname);

        }
        return _2594;
    }

    private HashMap getRecordElementBySeq(Element _9147,ArrayList _3338){
        HashMap _2594 = new HashMap();
        List _9137 = _9147.getChildren();
        Element _cele = null;
        Iterator _4070 = _9137.iterator();
        int _3362=0;
        while(_4070.hasNext()){
            _cele = (Element)_4070.next();
            _2594.put(_3338.get(_3362).toString(),_cele.getText());
            _3362++;
        }
        return _2594;
    }

    public StringBuffer getTreeXmlData(VOBJ _227 ) {
      String _9145="";
      try{
      CONFIG _9131 = new CONFIG();
      _9145 = _9131.getPathfinderConfig("sysImage") ;
    }catch(Exception ee){
      System.out.println("시스템 CONFIG에서 syImage가 존재 하지 않습니다.");
      ee.printStackTrace();
    }


      java.util.Stack _1130 = new  java.util.Stack();
      ArrayList _7022 = _227.getColumnNames();
      String _3251=_227._3248;
      String _3041=_227._3038;
      String _554="";
      String _7013="";
      StringBuffer _2366 = new StringBuffer();
      _2366.append("<"+_227.getName()+">\n");
      _227.first();
      while(_227.next()){
        if(_1130.isEmpty()){
          _1130.push(_227.getRecord().get(_3251));
          //-node 작성
          _2366.append("<REC id=\""+_227.getRecord().get(_3251)+"\">\n");
          for(int i=0;i<_7022.size();i++){
            _7013 = _7022.get(i).toString();
            _2366.append("<" + _7013 +">" + _227.getRecord().get(_7013)+ "</"+_7013+">\n" );
          }
          _2366.append("<image>"+_9145+"book.gif</image>\n");
          _2366.append("<imageOpen>"+_9145+"bookOpen.gif</imageOpen>\n");
          _2366.append("<cnode>");
        }else{
          while(true){
            _554 = _1130.pop().toString();
            if(_554.equals(_227.getRecord().get(_3041))){
              _1130.push(_554);
              _1130.push(_227.getRecord().get(_3251));

              _2366.append("<REC id=\""+_227.getRecord().get(_3251)+"\">\n");
              for(int i=0;i<_7022.size();i++){
                _7013 = _7022.get(i).toString();
                _2366.append("<" + _7013 +">" + _227.getRecord().get(_7013)+ "</"+_7013+">\n" );
              }
              _2366.append("<image>"+_9145+"book.gif</image>\n");
              _2366.append("<imageOpen>"+_9145+"bookOpen.gif</imageOpen>\n");
              _2366.append("<cnode>");
              break;
            }else{     //-- contents 종료처리
              _2366.append("</cnode>\n");
              _2366.append("</REC>\n");
            }
          }
        }
      }

      while(!_1130.isEmpty()){
        _1130.pop();
        _2366.append(" </cnode> \n");
        _2366.append("</REC> \n");
      }

      _2366.append("</"+_227.getName()+"> \n");
      //System.out.println("GET TREE XMLDATA: \n" + rtn.toString());
      return _2366;
    }


    /*

    */
    public StringBuffer getXmlStringBuffer(VOBJ _170, String _53){
      StringBuffer _2279 = new StringBuffer();
      ArrayList _7475 =null;
      if(!_170.isEmpty())
        _7475 = _170.getColumnNames();

      if(_7475 != null){
        if(_53.equals("XNAME"))     {  //<XDATA> , <REC> , <COL (name=)> value  </COL>
          _2279.append("<XDATA> \n");
          _170.first();
          while(_170.next()){
            _2279.append("<REC> \n");
            for(int i=0;i<_7475.size();i++){
              _2279.append("     <COL name=\""+_7475.get(i)+"\">");
              _2279.append(_170.getRecord().get(_7475.get(i).toString()));
              _2279.append("</COL> \n");
            }
            _2279.append("</REC> \n");
          }
          _2279.append("</XDATA> \n");

        }else if(_53.equals("XTAG")){    //<XDATA> ,<REC> , <NAME> value </NAME>
          _2279.append("<XDATA> \n");
          _170.first();
          while(_170.next()){
            _2279.append("<REC> \n");
            for(int i=0;i<_7475.size();i++){
              _2279.append("     <"+_7475.get(i)+">");
              _2279.append(_170.getRecord().get(_7475.get(i).toString()));
              _2279.append("</"+_7475.get(i)+">  \n");
            }
            _2279.append("</REC> \n");
          }
          _2279.append("</XDATA> \n");

        }

      }else {
        _2279.append("<XDATA> </XDATA> ");
      }

      return _2279;
    }

    public StringBuffer getErrLogXmlStringBuffer(VOBJ _170){
        StringBuffer _2279 = new StringBuffer(4000);
        ArrayList _7475 =null;
        if(!_170.isEmpty())
            _7475 = _170.getColumnNames();

        if(_7475 != null){  //<DATASET name=> , <REC> , <COL (name=)> value  </COL>
            _2279.append("<DATASET name=\""+_170.getName()+"\" count=\""+_170.getRecordCnt()+"\">\n");
            _170.first();
            while(_170.next())
            {
                _2279.append("<REC>\n");
                for(int i=0;i<_7475.size();i++)
                {
                    //_2279.append("<"+_7475.get(i)+"><![CDATA[");
                    _2279.append("<"+_7475.get(i)+">");
                    _2279.append(_170.getRecord().get(_7475.get(i).toString()));
                    _2279.append("</"+_7475.get(i)+">\n");
                    //_2279.append("]]</"+_7475.get(i)+">\n");
                }
                _2279.append("</REC>\n");
            }
            _2279.append("</DATASET>\n");
        }
        return _2279;
    }



}



