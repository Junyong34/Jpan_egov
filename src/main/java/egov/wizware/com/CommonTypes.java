package egov.wizware.com;

public class CommonTypes {

  private static final String INSUSER_COLUMNNAME="";
  private static final String INSDATE_COLUMNNAME="";
  private static final String UPDUSER_COLUMNNAME="";
  private static final String UPDDATE_COLUMNNAME="";
  private static final int CHGFLAG=0;

  public static final int STRING = 1;
  public static final int NUMBER = 2;
  public static final int LONG = 3;
  public static final int INTEGER = 4;
  public static final int DATE = 5;

  public static final int BLOB = 6;
  public static final int CLOB = 7;
  public static final int BYTES = 8;
  public static final int NULL = 99;

  public String CHGUSER="";
  public String CHGDATE="";

  protected String getInsuser_Columnname(){
    return this.INSUSER_COLUMNNAME;
  }

  protected String getInsdate_Columnname(){
    return this.INSDATE_COLUMNNAME;
  }

  protected String getUpduser_Columnname(){
    return this.UPDUSER_COLUMNNAME;
  }

  protected String getUpddate_Columnname(){
    return this.UPDDATE_COLUMNNAME;
  }

  protected int getChgflag_Columnname(){
    return this.CHGFLAG;
  }

}
