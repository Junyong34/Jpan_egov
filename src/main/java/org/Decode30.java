package org;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Hashtable;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

/**
*  Edonavi発行のCookie(SYS30)の デコードクラス  JDK1.4.x版<BR>
*  複合化されたCookieの文字コードはUTF8
*       @author Ooka Masashi
*       @since J2SDK 1.3
*       @history decodesys30hashメソッドはスレッドセーフではないため非推奨<br>
*       		  だったため削除(2008/6/9)
*       @version 3.0  (2008/6/9) FLT)kuriyama
*/
public class Decode30 {
                /**インスタンス変数(非推奨)<BR>
                *decodesys30メソッドにより使用される<BR>
                *デコードされたCookie(UTF8)が格納される
                */
                public String decoded_cookie = null;
                /**インスタンス変数(非推奨)<BR>
                *decodesys30メソッドにより使用される<BR>
                *デコード後分解されたCookieの項目名(UTF8)が格納される
                */
                public String name[] =  new String[32];
                /**インスタンス変数(非推奨)<BR>
                *decodesys30メソッドにより使用される<BR>
                *デコード後分解されたCookieの項目値(UTF8)が格納される
                */
                public String data[] =  new String[32];
/**
 * 復号化対象の文字列を受け取り、正常終了の場合は復号化された文字列(UTF8)<BR>
 * 異常終了の場合は、falseとエラーコードの文字列を返す(UTF8)<BR>
 *
 * @author      Ooka Masashi
 * @version 2.1  (2008/6/9) FLT)kuriyama
 * @param  s 復号化対象のString  <BR>
 * @history 日本語文字化け部分修正(2008/6/9)<br>
 * @return 正常終了：新しく復号化された String(UTF8) <BR>
 *      異常終了：エラーコード String(UTF8)<BR>
 *  1       base64デコードエラー <BR>
 *  2       Blowfishデコードエラー <BR>
 *  3       MD5チェックサムエラー <BR>
 *  4       バッファーオーバーランエラー <BR>
 *  5       解凍したデータの書式エラー <BR>
 *  255     unknown error
 */
public String decodesys30str(String s)
                        {
        int status = 0;         //戻り値
        int MD5_fg = 0;         //MD5比較フラグ
        String result = "";    //戻り値
        String url_dec = "";    //URLデコード文字列
        String ahalf = "";      //Cookie非MD5文字列（後半部分:After half）
        byte[] devalue = null;  //Base64デコード文字列
        byte[] decrypted = null;//Blowfishデコード文字列

        //URLデコード
	 try{
        URLDecoder decoder = new URLDecoder();
        url_dec = decoder.decode(s,"EUC-JP");
        } catch(UnsupportedEncodingException ex) { return result = "255"; }
         catch(Exception e) { return result = "255"; }

                //Base64デコード
                try{
                devalue = Conversion.base64StringToByteArray(url_dec);
                } catch(Exception e) { return result = "1";}
                                for(int i = 0; i < 3; i++){
                                 try{
                                        //Blowfishデコード
                                        String key1 = "EDoNavi Cookie Encrypt";
                                        String key2="for Internet sites";
                                        decrypted = null;
                                        //初期化ベクタの設定
                                        javax.crypto.spec.IvParameterSpec iv;
                                        iv = new javax.crypto.spec.IvParameterSpec("3DoN4V1?".getBytes("iso-8859-1"));
                                        //パスフレーズの生成
                                        Date date = new Date();
                                        String timestamp=key1 + String.valueOf((date.getTime()/1000-(i*12*60*60))/(12*60*60)) + key2;
                                        //指定されたバイト配列から秘密鍵を構築します。
                                        SecretKeySpec skeySpec = new SecretKeySpec(timestamp.getBytes("iso-8859-1"),"Blowfish");
                                        //指定された変換を実装する Cipher オブジェクトを生成します
                                        Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
                                        //鍵を使用してこの暗号を初期化します
                                        cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);
                                        //Blowfishデコード
                                        decrypted = cipher.doFinal(devalue);
                                        }catch(IllegalStateException ex){ result = "2"; continue; }
                                        //catch(ShortBufferException ex){ result = "2"; continue; }
                                        catch(IllegalBlockSizeException ex){result = "2"; continue;}
                                        catch(BadPaddingException ex){ result = "2"; continue; }
                                        catch(Exception e) { result = "2"; continue; }
                                        /*MD5チェック*/
                                        try{
                                        if(new String(decrypted).length() < 23 ){ return result = "4"; }
                                        String fhalf = new String(decrypted,0,22);
                                        ahalf = new String(decrypted,23,decrypted.length-23);
                                        MessageDigest md = MessageDigest.getInstance("MD5");
                                        md.update(ahalf.getBytes());
                                        String dhalf = Conversion.byteArrayToBase64String(md.digest()).substring(0,22);
                                        if(!fhalf.equals(dhalf)) continue;
                                        if(fhalf.equals(dhalf)) { MD5_fg = 1; result = "0"; break; }
                                        }catch(NoSuchAlgorithmException e) { return result = "3";}
                                }
       if(MD5_fg == 0) result = "5";
        else if(MD5_fg == 1){
                if(ahalf.indexOf("USERID=")!=-1){
                        try{
	            			URLDecoder decoder = new URLDecoder();
	            			String url_dec2 = decoder.decode(ahalf,"EUC-JP");
	            			result = new String(url_dec2);
	            			//080609 changed kuriyama
	            			//result = new String(ahalf);
                        }catch(Exception e) { return result = "255"; }
                }else result = "5";
        } else result = "255";
       		return result;
        }

/**
 * 復号化対象の文字列を受け取り、復号化ステータスおよび、復号化したCookie(UTF8)を項目名、項目値に分けHashtableに格納して返す<BR>
 *
 * @author      Ooka Masashi
 * @version 2.0  (2005/4/20)
 * @param  s 復号化対象のString  <BR>
 * @return Hashtable -復号化ステータス及び、項目名、項目値が格納されている<P>
 * Hashtable キー<BR>
 *      status:復号化ステータス<BR>
 *      USERID:ユーザID<BR>
 *      LV:ユーザレベル<BR>
 *      MAIL:メールアドレス<BR>
 *      NAME:氏名<BR>
 *      UNIT:部コード<BR>
 *      FJKANACN:氏名(カタカナ)<BR>
 *      DN:DN<P>
 * ステータスコード：<BR>
 *  0       エラーなし、正常終了 <BR>
 *  1       base64デコードエラー <BR>
 *  2       Blowfishデコードエラー <BR>
 *  3       MD5チェックサムエラー <BR>
 *  4       バッファーオーバーランエラー <BR>
 *  5       解凍したデータの書式エラー <BR>
 *  255     unknown error
 */
public Hashtable decodesys30hash(String s)
                        {
        int status = 0;         //戻り値
        int MD5_fg = 0;         //MD5比較フラグ
        String url_dec = "";    //URLデコード文字列
        String url_dec2 = "";    //URLデコード文字列
        String ahalf = "";      //Cookie非MD5文字列（後半部分:After half）
        String decoded_cookie = "";
        byte[] devalue = null;  //Base64デコード文字列
        byte[] decrypted = null;//Blowfishデコード文字列

        Hashtable ht = new Hashtable();

        //URLデコード
	 try{
        URLDecoder decoder = new URLDecoder();
        url_dec = decoder.decode(s,"EUC-JP");
        } catch(UnsupportedEncodingException ex) { ht.put("status","255"); return ht; }
         catch(Exception e) { ht.put("status","255"); return ht;}

                //Base64デコード
                try{
                	devalue = Conversion.base64StringToByteArray(url_dec);
                } catch(Exception e) {  ht.put("status","1"); return ht;}
                                for(int i = 0; i < 3; i++){
                                 try{
                                        //Blowfishデコード
                                        String key1 = "EDoNavi Cookie Encrypt";
                                        String key2="for Internet sites";
                                        decrypted = null;
                                        //初期化ベクタの設定
                                        javax.crypto.spec.IvParameterSpec iv;
                                        iv = new javax.crypto.spec.IvParameterSpec("3DoN4V1?".getBytes("iso-8859-1"));
                                        //パスフレーズの生成
                                        Date date = new Date();
                                        String timestamp=key1 + String.valueOf((date.getTime()/1000-(i*12*60*60))/(12*60*60)) + key2;
                                        //指定されたバイト配列から秘密鍵を構築します。
                                        SecretKeySpec skeySpec = new SecretKeySpec(timestamp.getBytes("iso-8859-1"),"Blowfish");
                                        //指定された変換を実装する Cipher オブジェクトを生成します
                                        Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
                                        //鍵を使用してこの暗号を初期化します
                                        cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);
                                        //Blowfishデコード
                                        decrypted = cipher.doFinal(devalue);
                                        }catch(IllegalStateException ex){  ht.put("status","2"); continue; }
                                        //catch(ShortBufferException ex){  ht.put("status","2"); continue; }
                                        catch(IllegalBlockSizeException ex){  ht.put("status","2"); continue;}
                                        catch(BadPaddingException ex){  ht.put("status","2"); continue;}
                                        catch(Exception e) {  ht.put("status","2"); continue;}
                                        /*MD5チェック*/
                                        try{
                                        if(new String(decrypted).length() < 23 ){ ht.put("status","4"); return ht; }
                                        String fhalf = new String(decrypted,0,22);
                                        ahalf = new String(decrypted,23,decrypted.length-23);
                                        MessageDigest md = MessageDigest.getInstance("MD5");
                                        md.update(ahalf.getBytes());
                                        String dhalf = Conversion.byteArrayToBase64String(md.digest()).substring(0,22);
                                        if(!fhalf.equals(dhalf)) continue;
                                        if(fhalf.equals(dhalf)) { MD5_fg = 1; ht.put("status","0"); break; }
                                        }catch(NoSuchAlgorithmException e) { ht.put("status","3"); return ht;}
                                }
       if(MD5_fg == 0) ht.put("status","5");
        else if(MD5_fg == 1){
                if(ahalf.indexOf("USERID=")!=-1){
                        try{
			URLDecoder decoder = new URLDecoder();
			url_dec2 = decoder.decode(ahalf,"EUC-JP");
                        decoded_cookie = new String(url_dec2);
                        int j = 0;
                        int k = 0;
                        String s1[] =  new String[32];
                                for(int i = 0; i < decoded_cookie.length(); i++){
                                        if('&' == decoded_cookie.charAt(i)){
                                                s1[k] = decoded_cookie.substring(j,i);
                                                int eq = s1[k].indexOf('=');
                                                name[k] = s1[k].substring(0,eq);
                                                data[k] = s1[k].substring(eq+1,s1[k].length());
                                                ht.put(s1[k].substring(0,eq),s1[k].substring(eq+1,s1[k].length()));
                                                j=i+1;k++;
                                        }
                                }
                        }catch(Exception e) { ht.put("status","255"); return ht; }
                }else ht.put("status","5");
        } else ht.put("status","255");
        return ht;
        }

}
