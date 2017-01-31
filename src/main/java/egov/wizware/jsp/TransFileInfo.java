package egov.wizware.jsp;
import java.io.File;
public class TransFileInfo  implements java.io.Serializable
{
  private String _446 = "";
  private String _2996="";
  private long    _1349 = 0;
  private String _5918="";
  private String _263="";
  private String _5963 = "";

  public TransFileInfo(){
  }

  public String getPath() {
    return this._2996;
  }

  public void setPath(String _2996) {
    this._2996 = _2996;
  }

  public String getFilename() {
    return this._5963;
  }

  public void setFilename(String _3374) {
    this._5963 = _3374;
  }

  public String getUniname() {
    return this._446;
  }

  public void setUniname(String _3374) {
    this._446 = _3374;
  }

  public void setSize(long _1349) {
    this._1349 = _1349;
  }

  public long getSize() {
    return this._1349;
  }

  public long getDownfilesize(){
    File file = new File(this.getPath() + this._446);
    return file.length();
  }

  public void setFiletype(String _1136){
    this._5918=_1136;
  }

  public String getFiletype(){
    return this._5918;
  }
  public String getVarname(){
    return this._263;
  }
  public void setVarname(String _3374){
    this._263 = _3374;
  }

}
