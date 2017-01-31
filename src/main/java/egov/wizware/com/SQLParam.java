package egov.wizware.com;

import java.math.BigDecimal;
import java.sql.*;
public class SQLParam {

  private int _6911;
  private String _6944;
  private int _2873;

  private long _3863;
  private int _4145;
  private String _1034;
  private double _6167;
  private BigDecimal _6452;
  private byte[] _blob;
  private byte[] _clob;

  public void setColumnType(int _281) {
    this._6911 = _281;
  }

  public int getColumnType() {
    return this._6911;
  }

  public void setColumnname(String _281) {
    this._6944 = _281;
  }

  public String getColumnname() {
    return this._6944;
  }

  public void setPosition(int _281) {
    this._2873 = _281;
  }

  public int getPosition() {
    return this._2873;
  }


  public void setBlob(byte[] _281) {
    this._blob = _281;
  }
  public byte[] getBlob() {
    return this._blob;
  }

  public void setClob(byte[] _281) {
      this._clob = _281;
  }
  public byte[] getClob() {
      return this._clob;
  }



  public void setString(String _281) {
    this._1034 = _281;
  }

  public String getString() {
      if(this._1034 == null) this._1034="";
    return this._1034;
  }

  public void setInt(int _281) {
    this._4145= _281;
  }

  public int getInt() {
    return this._4145;
  }

  public void setBigDecimal(BigDecimal _281) {
    this._6452 = _281;
  }

  public BigDecimal getBigDecimal() {
    return this._6452;
  }

  public void setDouble(double _281) {
    this._6167 = _281;
  }

  public double getDouble() {
    return this._6167;
  }

  public void setLong(long _281) {
    this._3863 = _281;
  }

  public long getLong() {
    return this._3863;
  }

}
