<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>DCP Management</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/demo.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" id="wizcss"  type="text/css" href="/hpms/wizware/css/UI.WizGrid.css"/>
        <link rel="stylesheet" id="scostStyle.css"  type="text/css" href="/hpms/css/scostStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/zTreeStyle.css"/>
        <script type="text/javascript" src="/hpms/wizware/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/grid.locale-en.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/jquery-ui.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/jquery.ztree.all-3.5.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/HuskyEZCreator.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/paging.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/jquery.form.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/Wizware_WizGrid.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/WizGrid.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/wizware.js"></script>
        <script type="text/javascript" src="/hpms/js/hcost.js"></script>
        <script> 
        //isChange = false;
        $(document).ready(function(e) {
        
            Approval_applicant_combo.submit(false, "ORG_InitCombo");
        
          //  $("input:not(#S_PID):not(#S2_APPROVAL_YYYYMMDD) , select:not(#S_DCP_TYPE)").change(function() {
                //  $(input:not([id="S_PID"]) select:not([id="S_DCP_TYPE"]):not([id="S_DCP_TYPE"]) ).change(function(){
        
            //    isChange = true;
           // });
          $(document).keydown(function(e){   
                  if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
        
        });
        
        
        function ORG_InitCombo() {
        
        }
        
        function PID_POP_DATE(PID) {
        
            $("#S_PID").val(PID);
            Forecast_data_combo.submit(false);
        }
        
        function FileInfosubmit() {
            FIleInfoAdd.submit(false, "Complete");
        }
        
        function Complete() {
            $("input:file").val('');
            Return_RTN_MSG("00013");
            //alert(" 파일이 첨부 되었습니다 ");
        }
        
        function FILE_PID_FOR() {
            //파일업로드 키값 전송송
            $("#F_PID").val($("#S3_PID").val());
            $("#F_DCP_TYPE").val($("#S2_DCP_TYPE").val());
        
            var DCP_TYPE = $("#S2_DCP_TYPE").val();
            var APPRO = $("#S2_APPROVAL_YYYYMMDD").val();
            ApprovalDate_output.submit(false, "DCP_ORG_Set");
        
        
        }
        
        function DCP_ORG_Set() {
        
            // ORG_CD  다시 셋팅 하고 조회
            Approval_applicant_ORG_Combo.submit(false, "", false);
            Approval_applicant_ORG_Combo1.submit(false, "PIDinfoSearch", false);
        
        
        }
        
        function PIDinfoSearch() {
        
            DCPsearch.submit(false);
        }
        
        function FIle_AUTH_Check() {
            
            
            var File_gubun = $("#F_SEQ").val();
            
            var file_Status="";
            
            switch (File_gubun) {
                case "10":
                   file_Status ="F_FILE";
                    break;
                case "20":
                    file_Status ="F_FILE1";
                    break;
                case "30":
                    file_Status ="F_FILE2";
                    break;
                case "40":
                    file_Status ="F_FILE3";
                    break;
                case "50":
                    file_Status ="F_FILE4";
                    break;
                case "60":
                    file_Status ="F_FILE5";
                    break;
                case "70":
                   file_Status ="F_FILE6";
                    break;
                case "71":
                    file_Status = "F_FILE7";
                    break;
                case "71":
                    file_Status = "F_FILE8";
                    break;
                case "":
                    alert(" File Upload Fail");
                    return;
                    break;
            }
            
           // alert($('#'+file_Status).text() + " @@ "+ file_Status+ " @@" +$('#'+).val()+ "@@" +File_gubun );
            if($('#'+file_Status).val() ==""){
            
                    return;
            }
            
            if ($("#C_CNT").val() != 1 ) {
          
        		$('input[type=file]').val("");
                Return_RTN_MSG("00020");
              
                return ;
        
            }
            
             eval("FIleUPload_"+file_Status+"()");
          /*  if ($("#out_message").val() != "") {
                $('input[type=file]').val("");
                ErrorCheck();
                return;
            }*/
            
            
        
        
        
        }
        
        
        function FileName_length(name) {
            var filename = $('#' + name).val();
            filename = filename.replace(/^.*[\\\/]/, '');
            var temp = filename.length;
            var tcount = 0;
            var onechar;
        
            for (k = 0; k < temp; k++) {
                onechar = filename.charCodeAt(k);
                if (onechar > 128) {
                    tcount += 2;
                } else {
                    tcount++;
                }
            }
        
        
        
            // alert( filename.length + "@@  "+ " @@ " + tcount);
        
            if (tcount > 64) {
                $('#' + name).val("");
                return true;
            } else {
        
                return false;
            }
        
        }
        
        function FIleUPload_F_FILE() {
        
            // 10 관람시트파일일
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE_OnChange(myObj) {
            
            
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE').val("");
                return;
        
            }
            //	alert(FileName_length("F_FILE"));
        
            if (FileName_length("F_FILE")) {
                Return_RTN_MSG("00022");
        
                return;
            }
        
        
            $("#F_SEQ").val(10);
           /* if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE').val("");
                return;
            } else {*/
        
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
        
           // }
        
        }
        
        function FIleUPload_F_FILE1() {
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE1', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE1_OnChange(myObj) {
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE1').val("");
                return;
        
            }
        
            if (FileName_length("F_FILE1")) {
                Return_RTN_MSG("00022");
        
                return;
            }
        
        
            $("#F_SEQ").val(20);
        
           /* if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE1').val("");
                return;
            } else {*/
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE1();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
          //  }
        
        }
        
        function FIleUPload_F_FILE2() {
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE2', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE2_OnChange(myObj) {
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE2').val("");
                return;
        
            }
            if (FileName_length("F_FILE2")) {
                Return_RTN_MSG("00022");
        
                return;
            }
            $("#F_SEQ").val(30);
            /*if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE2').val("");
                return;
            } else {*/
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE2();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
           // }
        }
        
        function FIleUPload_F_FILE3() {
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE3', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE3_OnChange(myObj) {
        
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE3').val("");
                return;
        
            }
            if (FileName_length("F_FILE3")) {
                Return_RTN_MSG("00022");
        
                return;
            }
            $("#F_SEQ").val(40);
        
           /* if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE3').val("");
                return;
            } else {*/
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE3();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
        
           // }
        
        }
        
        function FIleUPload_F_FILE4() {
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE4', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE4_OnChange(myObj) {
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE4').val("");
                return;
        
            }
            if (FileName_length("F_FILE4")) {
                Return_RTN_MSG("00022");
        
                return;
            }
            $("#F_SEQ").val(50);
        
           /* if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE4').val("");
                return;
            } else {*/
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE4();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
           // }
        
        }
        
        function FIleUPload_F_FILE5() {
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE5', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE5_OnChange(myObj) {
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE5').val("");
                return;
        
            }
            if (FileName_length("F_FILE5")) {
                Return_RTN_MSG("00022");
                $('#F_FILE').val("");
                return;
            }
            if (FileName_length("F_FILE5")) {
                Return_RTN_MSG("00022");
        
                return;
            }
            $("#F_SEQ").val(60);
           /* if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE5').val("");
                return;
            } else {*/
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE5();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
           // }
        
        }
        
        function FIleUPload_F_FILE6() {
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE6', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE6_OnChange(myObj) {
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE6').val("");
                return;
        
            }
            if (FileName_length("F_FILE6")) {
                Return_RTN_MSG("00022");
        
                return;
            }
            $("#F_SEQ").val(70);
           /* if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE6').val("");
                return;
            } else {*/
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE6();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
          //  }
        
        }
        
        function FIleUPload_F_FILE7() {
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE7', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE7_OnChange(myObj) {
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE7').val("");
                return;
        
            }
            if (FileName_length("F_FILE7")) {
                Return_RTN_MSG("00022");
        
                return;
            }
            $("#F_SEQ").val(71);
          /*  if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE7').val("");
                return;
            } else {*/
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE7();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
           // }
        }
        
        function FIleUPload_F_FILE8() {
            wizutil.FileAjaxSubmit(Contextpath + '/jsp/File/fileupload_DCP.jsp', fileform, 'F_FILE8', 'FileInfosubmit', ['ppt', 'xlsx', 'xls', 'pptx'], true);
        }
        
        function F_FILE8_OnChange(myObj) {
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                $('#F_FILE8').val("");
                return;
        
            }
            if (FileName_length("F_FILE8")) {
                Return_RTN_MSG("00022");
        
                return;
            }
            $("#F_SEQ").val(72);
           /* if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                $('#F_FILE8').val("");
                return;
            } else {*/
                if ("${G.AUTH_CD}" != "50") {
                    //파일 업로드 실행 
                    FIleUPload_F_FILE8();
                } else {
                    Update_File_AUTH_check.submit(false, "FIle_AUTH_Check");
                }
           // }
        
        }
        
        function required_msg() {
            if ($("#S2_PID").val() == "") {
                Return_RTN_MSG("00003", "PID");
                return false;
            }
        
            if ($("#S_DCP_TYPE").val() == "") {
                Return_RTN_MSG("00003", "Date Type");
                return false;
            }
            return true;
        }
        
        function Update_OnClick(myObj) {
        
            sbmCtlSave.submit(false, "DCPUpdate");
        }
        
        function DCPUpdate() {
        
            // 오류 체크 
            if ($("#out_message").val() != "") {
                ErrorCheck();
                return;
            }
        
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00018");
                return;
            }
            if ($("#S2_CLOSING_COMPANY_CD").val() != "" && $("#S2_CLOSING_ORG_CD").val() == "") {
        
                Return_RTN_MSG("00004", "Approval Section", "S2_CLOSING_ORG_CD");
                return;
            }
            if ($("#S2_APPLICA_COMPANY_CD").val() != "" && $("#S2_APPLICA_ORG_CD").val() == "") {
        
                Return_RTN_MSG("00004", "Applicant Section", "S2_APPLICA_ORG_CD");
                return;
            }
            
            var A_Date =$("#S2_A_DCP_PLAN_YYYYMMDD").val();
            if ( !isValidDate(A_Date) && A_Date != "") {
                Return_RTN_MSG("00036","A.DCP Scheduled Date" ,"S2_A_DCP_PLAN_YYYYMMDD");
                	return ;	
            } 
            var P_Date=$("#S2_P_DCP_PLAN_YYYYMMDD").val();
            if ( !isValidDate(P_Date) && P_Date != "" ) {
                Return_RTN_MSG("00036","P.DCP Scheduled Date" ,"S2_P_DCP_PLAN_YYYYMMDD");
                	return ;	
            } 
            var T_Date=$("#S2_T_DCP_PLAN_YYYYMMDD").val();
            if ( !isValidDate(T_Date) && T_Date != "" ) {
                Return_RTN_MSG("00036","T.DCP Scheduled Date" ,"S2_T_DCP_PLAN_YYYYMMDD");
                	return ;	
            } 
            var M_Date=$("#S2_M_DCP_PLAN_YYYYMMDD").val();
            if ( !isValidDate(M_Date) && M_Date != "" ) {
                Return_RTN_MSG("00036","M.DCP Scheduled Date" ,"S2_M_DCP_PLAN_YYYYMMDD");
                	return ;	
            } 
            var E_Date=$("#S2_E_DCP_PLAN_YYYYMMDD").val();
            if ( !isValidDate(E_Date) && E_Date != "" ) {
                Return_RTN_MSG("00036","E.DCP Scheduled Date" ,"S2_E_DCP_PLAN_YYYYMMDD");
                	return ;	
            } 
            
            
            var A_Date2 =$("#S2_A_DCP_EXEC_YYYYMMDD").val();
            if ( !isValidDate(A_Date2) && A_Date2 != "" ) {
                   Return_RTN_MSG("00036","A.DCP Date of Execution" ,"S2_A_DCP_EXEC_YYYYMMDD");
                	return ;	
            } 
            if(!today_inputDate(A_Date2)){
                  Return_RTN_MSG("00005");
                  return;
            }
            var P_Date2 =$("#S2_P_DCP_EXEC_YYYYMMDD").val();
            if ( !isValidDate(P_Date2) && P_Date2 != "" ) {
                Return_RTN_MSG("00036","P.DCP Date of Execution" ,"S2_P_DCP_EXEC_YYYYMMDD");
                	return ;	
            }
            if(!today_inputDate(P_Date2)){
                  Return_RTN_MSG("00005");
                  return;
            } 
            var T_Date2 =$("#S2_T_DCP_EXEC_YYYYMMDD").val();
            if ( !isValidDate(T_Date2) && T_Date2 != "" ) {
                Return_RTN_MSG("00036","T.DCP Date of Execution" ,"S2_T_DCP_EXEC_YYYYMMDD");
                	return ;	
            } 
            if(!today_inputDate(T_Date2)){
                  Return_RTN_MSG("00005");
                  return;
            } 
            var M_Date2 =$("#S2_M_DCP_EXEC_YYYYMMDD").val();
            if ( !isValidDate(M_Date2) && M_Date2 != "" ) {
                Return_RTN_MSG("00036","M.DCP Date of Execution" ,"S2_M_DCP_EXEC_YYYYMMDD");
                	return ;	
            } 
            if(!today_inputDate(M_Date2)){
                  Return_RTN_MSG("00005");
                  return;
            } 
            var E_Date2 =$("#S2_E_DCP_EXEC_YYYYMMDD").val();
            if ( !isValidDate(E_Date2) && E_Date2 != "" ) {
                Return_RTN_MSG("00036","E.DCP Date of Execution" ,"S2_E_DCP_EXEC_YYYYMMDD");
                	return ;	
            } 
            if(!today_inputDate(E_Date2)){
                  Return_RTN_MSG("00005");
                  return;
            }
            
            
           
            
            
        
            //일반 권한만 PI 체크 해서 수정 가능여부 판단
            if ("${G.AUTH_CD}" != "50") {
                if (confirm(confirm_MSG("0001"))) DCPInfoUpdate.submit(false, "Result");
        
            } else {
                Update_File_AUTH_check.submit(false, "DCPinfo_Update");
            }
        
        
        
        }
        
        function DCPinfo_Update() {
        
         if ($("#C_CNT").val() != 1) {
        
                Return_RTN_MSG("00020");
                $("#C_CNT").val('');
                return;
        
            }
            // 오류 체크 
          /*  if ($("#out_message").val() != "") {
                ErrorCheck();
                return;
            }
        */
            if (confirm(confirm_MSG("0001"))) DCPInfoUpdate.submit(false, "Result");
        }
        
        function Result() {
        
            Return_RTN_MSG("00014");
           // isChange = false;
            //재조회 
            DCPsearch.submit(false, "FILE_PID_FOR");
        
            // window.location.reload();
            // alert( $("#out_message").val());
        }
        
        function Approval_Update() {
        
            Return_RTN_MSG("00021");
            //재조회 
            DCPsearch.submit(false, "DCPSearch");
        
            // window.location.reload();
            // alert( $("#out_message").val());
        }
        
        
        function button5_OnClick(myObj) {
            if ("${G.AUTH_CD}" != "50") {
                sbmCtlSave.submit(false, "DCP_APPROVAL");
            } else {
                Return_RTN_MSG("00020");
                return;
            }
        }
        
        function DCP_APPROVAL() {
        
            // 오류 체크 
            if ($("#out_message").val() != "") {
                ErrorCheck();
                return;
            }
            if ($("#S3_PID").val() == "") {
                Return_RTN_MSG("00018");
                return false;
            }
            if ($("#S2_APPROVAL_YYYYMMDD").val() == "") {
                Return_RTN_MSG("00003", "Approved Date", "S2_APPROVAL_YYYYMMDD");
                return;
            }
            
              var APPROVAL_DATE =$("#S2_APPROVAL_YYYYMMDD").val();
            if ( !isValidDate(APPROVAL_DATE) && APPROVAL_DATE != "" ) {
                Return_RTN_MSG("00036","Approved Date" ,"S2_APPROVAL_YYYYMMDD");
                	return ;	
            } 
            
        
           /* if (isChange) {
                //수정 내역이 있으면 수정 버튼 먼저 누르라고 메시지 발생  
                Return_RTN_MSG("00031");
                return;
            } else {*/
                // 승인 처리 
                if (confirm(confirm_MSG("0002"))) DCPAPPROVAL.submit(false, "Approval_Update");
           // }
        
        
        
        }
        
        function InputClear() {
        
            $('input:text:not([id=S_PID]):text:not([id=S_DCP_TYPE])').val('');
            $('select').each(function() {
                var C_ID = this.id;
                if(C_ID != "S_DCP_TYPE" && C_ID !=""){
                  $("#"+C_ID+ " option:eq(0)").prop('selected', 'true');
                }
        		$("#F1_FILE_NM").text("");
        		$("#F1_FILE_NM1").text("");
        		$("#F1_FILE_NM2").text("");
        		$("#F1_FILE_NM3").text("");
        		$("#F1_FILE_NM4").text("");
        	    $("#F1_FILE_NM5").text("");
        	    $("#F1_FILE_NM6").text("");
        	    $("#F1_FILE_NM7").text("");
        		$("#F1_FILE_NM8").text("");
        	  
                //$(this).eq(0).find('select:not([id=S_DCP_TYPE])').prop('selected', 'true');
            //  alert(  $(this).find('select:not([id=S_DCP_TYPE])').val());
            });
        }
        
        function dept2() {
            if ($("#C_CNT").val() != 1) {
        
                Return_RTN_MSG("00019", "PID", "S_PID");
                // PID  마스터에 값이 존재하지않으면 값 초기화화
                 InputClear();
               // $("#S2_PID").val("");
                return;
        
            }
            //PID가 마스터에 존재하면 PID 값을 셋팅팅
            $("#S3_PID").val($("#S_PID").val());
        
            if ($("#S_DCP_TYPE").val() != "") {
        
        
                InputClear();
                DCPsearch.submit(false, "DCPSearch");
        
            } else {
                InputClear();
                DCPsearch.submit(false, "FILE_PID_FOR");
            }
        
        }
        
        function DCPSearch() {
            Calculate_DCP.submit(false, "FILE_PID_FOR");
        }
        
        function btn1_click(myObj) {
        
            sbmCtlRead.submit(false, "DCP_Search");
        
        }
        
        function DCP_Search() {
            // 오류 체크 
            if ($("#out_message").val() != "") {
                ErrorCheck();
                return;
            }
            if ($("#S_PID").val() == "") {
                Return_RTN_MSG("00003", "PID", "S_PID");
                return false;
            }
            //정규식 체크 
            if (PatternVal($("#S_PID").val(), "PID") == -1) {
                return;
            }
            if ($("#S_DCP_TYPE").val() == "") {
                Return_RTN_MSG("00004", "Data Type", "S_DCP_TYPE");
                return false;
            }
            //PID 존재여부 확인 
            PID_Confirm.submit(false, "dept2");
        
        }
        
        function S2_CLOSING_AMT_OnKeypress(myObj) {
            isNumberKey(event);
        }
        
        function S2_REQUEST_AMT_OnKeypress(myObj) {
            isNumberKey(event);
        }
        
        function S2_BTM_AMT_SP_OnKeypress(myObj) {
            isNumberKey(event);
        }
        
        function S2_BTM_AMT_SSP_OnKeypress(myObj) {
            isNumberKey(event);
        }
        
        function S2_BTM_AMT_TP_OnKeypress(myObj) {
            isNumberKey(event);
        }
        
        function S2_OSC_MARGIN_RATIO_OnKeypress(myObj) {
            isNumberKey(event);
        }
        
        
        
        function ErrorCheck() {
            //오류가 발생할 때 
            if (ErroMsgDisplay() != 0) {
        
                // 메시지 코드드
                var ERROR_CODE = "";
                ERROR_CODE = $("#out_message").val();
        
                if (ErroMsgDisplay() == 9999) {
                    ERROR_CODE = 9999;
                }
        
                var param = ""
                param += "?ERROR_CODE=" + ERROR_CODE + "&ERROR_MSG=" + ErroMsgDisplay();
        
                var url = "/hpms/20160906131150ErrorDisplay_loadpage.do" + param;
        
                $("#Error_cd").attr("src", url);
                $("#Error_cd").attr("frameborder", 0);
                $("#divPopup_ErrorCode").dialog({
                    autoPoen: false,
                    height: 260,
                    //position:[50,300],
                    width: 530,
                    modal: true,
                    title: "Error Indication",
                    resizable: true,
                    closeOnEscape: true,
        
                    close: function() {
        
                        //	window.location.reload();
                        // iframe 초기화.
                        $("#" + this.id).find('iframe').contents().find('body').html('');
                    }
                });
                $("#divPopup_ErrorCode").dialog("open");
            }
        }
        
        function S2_CLOSING_ORG_CD_OnClick(myObj) {
            if ($("#S2_CLOSING_COMPANY_CD").val() == "") {
                Return_RTN_MSG("00004", "Approval Section", "S2_CLOSING_COMPANY_CD");
                return;
            }
        
        
        }
        
        function S2_APPLICA_ORG_CD_OnClick(myObj) {
            if ($("#S2_APPLICA_COMPANY_CD").val() == "") {
                Return_RTN_MSG("00004", "Applicant Section", "S2_APPLICA_COMPANY_CD");
                return;
            }
        }
        
        function S2_CLOSING_COMPANY_CD_OnChange(myObj) {
            if ($("#S2_CLOSING_COMPANY_CD").val() != "") {
                Approval_applicant_ORG_Combo.submit(false);
            } else {
                $("#S2_CLOSING_ORG_CD").val("");
            }
        
        }
        
        function S2_APPLICA_COMPANY_CD_OnChange(myObj) {
        
            if ($("#S2_APPLICA_COMPANY_CD").val() != "") {
                Approval_applicant_ORG_Combo1.submit(false);
            } else {
                $("#S2_APPLICA_ORG_CD").val("");
            }
        
        }
        
        
        
        function S_PID_OnBlur(myObj) {
            /*
            isloadingCheck = false;
            if($("#S_PID").val() != ""){
             DATA_TYPE_TITLE_Combo.submit(false, "",false);
             }
             isloadingCheck = true;*/
        }
        
        
        function S_DCP_TYPE_OnClick(myObj) {
        
            if ($("#S_DCP_TYPE  option:eq(0)").text() != "--Choice--") {
                Return_RTN_MSG("00003", "PID", "S_PID");
                return;
            }
        }
        
        
        
        function S_PID_OnKeyUp(myObj) {
            if ($("#S_PID").val() != "" && $("#S_PID").val().length == 6) {
        
                DATA_TYPE_TITLE_Combo.submit(false);
            }
        }
        
        //날짜유효성검사
        function isValidDate(param) {
                try
                {
                    //param = param.replace(/-/g,'');
         
                    // 자리수가 맞지않을때
                    if( isNaN(param) || param.length!=8 ) {
                        return false;
                    }
                     
                    var year = Number(param.substring(0, 4));
                    var month = Number(param.substring(4, 6));
                    var day = Number(param.substring(6, 8));
         
                    var dd = day / 0;
         
                     
                    if( month<1 || month>12 ) {
                        return false;
                    }
                     
                    var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
                    var maxDay = maxDaysInMonth[month-1];
                     
                    // 윤년 체크
                    if( month==2 && ( year%4==0 && year%100!=0 || year%400==0 ) ) {
                        maxDay = 29;
                    }
                     
                    if( day<=0 || day>maxDay ) {
                        return false;
                    }
                    return true;
         
                } catch (err) {
                    return false;
                }                       
            }
            
            function today_inputDate(param){
                
                var Eyear = param.substring(0, 4);
                var Emonth = param.substring(4, 6);
                var Eday = param.substring(6, 8);
                //날짜 조합합
                var Edate =  Eyear+"-"+Emonth+"-"+ Eday; 
                    
                var date = new Date();
           
                var year  = date.getFullYear();
                var month = date.getMonth() + 1; // 0부터 시작하므로 1더함 더함
                var day   = date.getDate();
            	
                if (("" + month).length == 1) { month = "0" + month; }
                if (("" + day).length   == 1) { day   = "0" + day;   }
               
                var Cdate =Eyear+"-"+month+"-"+ day; 
                
             
                if( new Date(Edate) == new Date(Cdate)){
                
                  return true;
                }else if( new Date(Edate) > new Date(Cdate)){
        		return false;
        		}else{
        		return true;
        		}
                   
            }
        
        function F_FILE_OnClick(myObj)
        {
        }
        
        </script>
        <style> 


</style>
</head>
<body >
    <div id="pbview" >
<form id='frm' name='frm' >
    <div id="hiddenvariable" style="display:none">
        <input style="display:none" id="out_message" name="out_message"  value="" ></input>
        <input style="display:none" id="out_code" name="out_code"  value="" ></input>
    </div>
        <input type='hidden'  id="InWIzJsonParma" name="InWIzJsonParma"  value="" ></input>
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:28px;  width:435px; height:34px; " id="label2" name="label2"><label style=" line-height:36px; " >    DCP Management     </label>    </div>

        	<div   id="user1" style=" position: absolute; top:56px; left:863px;  width:49px; height:64px; display:none;" >
<!-- Error Code screen -->

<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:66px; left:26px;  width:399px; height:84px; " >  
                	<div  class="infoLabel" style="position:absolute;left:29px;top:19px; width:100px; height:19px; " id="label1" name="label1"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:131px;top:20px; width:97px; height:17px; " id="S_PID" name="S_PID" type="text" maxlength="6" tabindex="0"  onkeyup="S_PID_OnKeyUp(this);" onblur="S_PID_OnBlur(this);">        </input>
                	<div  class="infoLabel" style="position:absolute;left:29px;top:44px; width:100px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        Data Type         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:131px;top:44px; width:102px; height:21px; " id="S_DCP_TYPE" name="S_DCP_TYPE" tabindex="0" onclick="S_DCP_TYPE_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="btn3" style="position:absolute;left:314px;top:46px; width:65px; height:21px; " id="btn1" name="btn1" type="button" value="Search" tabindex="0"  onclick="btn1_click(this);" ></input>
      </div>
        	<input  class="TextBox-S" style=" position: absolute; top:95px; left:555px;  width:97px; height:17px; " id="C_CNT" name="C_CNT" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:162px; left:30px;  width:435px; height:34px; " id="label4" name="label4"><label style=" line-height:36px;  font-family:굴림체;font-weight:bold;font-size:12pt;" >    DCP Info     </label>    </div>

      <div  id="group2" name="group2"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:204px; left:26px;  width:731px; height:346px; " >  
                	<div  class="infoLabel" style="position:absolute;left:372px;top:8px; width:124px; height:19px; " id="label12" name="label12"><label style=" line-height:21px; " >        Latest DCP Type         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:8px; width:149px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:162px;top:8px; width:161px; height:17px; " id="S3_PID" name="S3_PID" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<select  class="selectbox" style="position:absolute;left:498px;top:9px; width:132px; height:21px; " id="S2_DCP_TYPE" name="S2_DCP_TYPE" tabindex="1" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:331px;top:28px; width:25px; height:17px; " id="S3_PID_STATUS_CD" name="S3_PID_STATUS_CD" type="hidden" maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:372px;top:33px; width:124px; height:19px; " id="label13" name="label13"><label style=" line-height:21px; " >        Required return         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:162px;top:33px; width:161px; height:17px; " id="S3_PID_STATUS_NM" name="S3_PID_STATUS_NM" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:33px; width:149px; height:19px; " id="label6" name="label6"><label style=" line-height:21px; " >        PID Status         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:499px;top:33px; width:127px; height:17px; " id="S2_REQUIRED_RETURN_NM" name="S2_REQUIRED_RETURN_NM" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:331px;top:54px; width:22px; height:17px; " id="S3_RD_CATEGORY_CD" name="S3_RD_CATEGORY_CD" type="hidden" maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:162px;top:57px; width:161px; height:17px; " id="S3_RD_CATEGORY_NM" name="S3_RD_CATEGORY_NM" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:57px; width:149px; height:19px; " id="label7" name="label7"><label style=" line-height:21px; " >        R&D Category         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:507px;top:59px; width:127px; height:17px; " id="S2_REQUIRED_RETURN" name="S2_REQUIRED_RETURN" type="hidden" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:162px;top:81px; width:161px; height:17px; " id="S3_RD_THEME" name="S3_RD_THEME" type="text" readonly  maxlength="120" tabindex="-1" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:81px; width:149px; height:19px; " id="label8" name="label8"><label style=" line-height:21px; " >        R&d Theme         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:162px;top:105px; width:161px; height:17px; " id="S3_ITEM_NAME" name="S3_ITEM_NAME" type="text" readonly  maxlength="30" tabindex="-1" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:105px; width:149px; height:19px; " id="label9" name="label9"><label style=" line-height:21px; " >        Product         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:372px;top:106px; width:124px; height:19px; " id="label14" name="label14"><label style=" line-height:21px; " >        Applied Price         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:498px;top:106px; width:127px; height:17px; " id="S2_CLOSING_AMT" name="S2_CLOSING_AMT" type="text" maxlength="18" tabindex="2"  onkeypress="S2_CLOSING_AMT_OnKeypress(this);">        </input>
                	<input  class="TextBox-S" style="position:absolute;left:162px;top:128px; width:161px; height:17px; " id="S3_NICKNAME" name="S3_NICKNAME" type="text" readonly  maxlength="120" tabindex="-1" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:129px; width:149px; height:19px; " id="label10" name="label10"><label style=" line-height:21px; " >        Nickname (For Display)         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:372px;top:131px; width:124px; height:19px; " id="label15" name="label15"><label style=" line-height:21px; " >        Approved Ordered Price         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:498px;top:137px; width:127px; height:17px; " id="S2_REQUEST_AMT" name="S2_REQUEST_AMT" type="text" maxlength="18" tabindex="3"  onkeypress="S2_REQUEST_AMT_OnKeypress(this);">        </input>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:153px; width:149px; height:19px; " id="label11" name="label11"><label style=" line-height:21px; " >        Nickname (For Outside)         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:162px;top:153px; width:161px; height:17px; " id="S3_NICKNAME_EXCL_HIDDEN" name="S3_NICKNAME_EXCL_HIDDEN" type="text" readonly  maxlength="120" tabindex="-1" >        </input>
                	<select  class="selectbox" style="position:absolute;left:162px;top:192px; width:166px; height:21px; " id="S2_CLOSING_COMPANY_CD" name="S2_CLOSING_COMPANY_CD" tabindex="0" onchange="S2_CLOSING_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:371px;top:196px; width:124px; height:19px; " id="label16" name="label16"><label style=" line-height:21px; " >        Bottom ECP Price         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:497px;top:196px; width:127px; height:17px; " id="S2_BTM_AMT_ECP" name="S2_BTM_AMT_ECP" type="text" maxlength="18" tabindex="8"  onkeypress="S2_BTM_AMT_SP_OnKeypress(this);">        </input>
                	<select  class="selectbox" style="position:absolute;left:630px;top:196px; width:79px; height:21px; " id="S2_BTM_AMT_ECP_UNIT" name="S2_BTM_AMT_ECP_UNIT" tabindex="9" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:205px; width:149px; height:19px; " id="label19" name="label19"><label style=" line-height:21px; " >        Approval Section         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:162px;top:215px; width:166px; height:21px; " id="S2_CLOSING_ORG_CD" name="S2_CLOSING_ORG_CD" tabindex="0" onclick="S2_CLOSING_ORG_CD_OnClick(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:630px;top:221px; width:79px; height:21px; " id="S2_BTM_AMT_SSP_UNIT" name="S2_BTM_AMT_SSP_UNIT" tabindex="11" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:371px;top:221px; width:124px; height:19px; " id="label48" name="label48"><label style=" line-height:21px; " >        Bottom SSP Price         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:497px;top:221px; width:127px; height:17px; " id="S2_BTM_AMT_SSP" name="S2_BTM_AMT_SSP" type="text" maxlength="50" tabindex="10"  onkeypress="S2_BTM_AMT_SSP_OnKeypress(this);">        </input>
                	<select  class="selectbox" style="position:absolute;left:162px;top:238px; width:166px; height:21px; " id="S2_APPLICA_COMPANY_CD" name="S2_APPLICA_COMPANY_CD" tabindex="0" onchange="S2_APPLICA_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:371px;top:245px; width:124px; height:19px; " id="label17" name="label17"><label style=" line-height:21px; " >        Bottom TP Price         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:497px;top:245px; width:127px; height:17px; " id="S2_BTM_AMT_TP" name="S2_BTM_AMT_TP" type="text" maxlength="18" tabindex="12"  onkeypress="S2_BTM_AMT_TP_OnKeypress(this);">        </input>
                	<select  class="selectbox" style="position:absolute;left:630px;top:246px; width:79px; height:21px; " id="S2_BTM_AMT_TP_UNIT" name="S2_BTM_AMT_TP_UNIT" tabindex="13" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:249px; width:149px; height:19px; " id="label20" name="label20"><label style=" line-height:21px; " >        Applicant Section         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:162px;top:261px; width:166px; height:21px; " id="S2_APPLICA_ORG_CD" name="S2_APPLICA_ORG_CD" tabindex="0" onclick="S2_APPLICA_ORG_CD_OnClick(this);" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:371px;top:269px; width:124px; height:19px; " id="label18" name="label18"><label style=" line-height:21px; " >        Dealer Margin         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:497px;top:269px; width:127px; height:17px; " id="S2_OSC_MARGIN_RATIO" name="S2_OSC_MARGIN_RATIO" type="text" maxlength="18" tabindex="14"  onkeypress="S2_OSC_MARGIN_RATIO_OnKeypress(this);">        </input>
                	<input  class="TextBox-S" style="position:absolute;left:162px;top:285px; width:161px; height:17px; " id="S2_RESPONS_ORG_CD" name="S2_RESPONS_ORG_CD" type="text" maxlength="100" tabindex="6" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:285px; width:149px; height:19px; " id="label21" name="label21"><label style=" line-height:21px; " >        Responsibility         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:163px;top:309px; width:161px; height:17px; " id="S2_PERSON_IN_CHARGE_ID" name="S2_PERSON_IN_CHARGE_ID" type="text" maxlength="100" tabindex="7" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:309px; width:149px; height:19px; " id="label22" name="label22"><label style=" line-height:21px; " >        Person in charge         </label>        </div>
      </div>
        	<input  class="TextBox-S" style=" position: absolute; top:355px; left:775px;  width:97px; height:17px; " id="S3_NICKNAME_EXCL" name="S3_NICKNAME_EXCL" type="hidden" maxlength="50" tabindex="0" >    </input>

      <div  id="group3" name="group3"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:555px; left:26px;  width:731px; height:196px; " >  
                	<div  class="infoLabel" style="position:absolute;left:276px;top:14px; width:140px; height:19px; " id="label35" name="label35"><label style=" line-height:21px; " >        Date of Execution         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:134px;top:14px; width:140px; height:19px; " id="label34" name="label34"><label style=" line-height:21px; " >        Scheduled Date         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:58px;top:14px; width:73px; height:19px; " id="label33" name="label33"><label style=" line-height:21px; " >        DCP         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:419px;top:14px; width:140px; height:19px; " id="label36" name="label36"><label style=" line-height:21px; " >        Execution Count         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:561px;top:14px; width:140px; height:19px; " id="label37" name="label37"><label style=" line-height:21px; " >        Date of Approval         </label>        </div>
                <div id=="calendar_S2_A_DCP_PLAN_YYYYMMDD" style="position:absolute;left:134px;top:38px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_A_DCP_PLAN_YYYYMMDD" name="S2_A_DCP_PLAN_YYYYMMDD" type="text" tabindex="15" >        </input>
            </div>
<script>
        $('#S2_A_DCP_PLAN_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                	<div  class="infoLabel" style="position:absolute;left:58px;top:38px; width:73px; height:19px; " id="label49" name="label49"><label style=" line-height:21px; " >        A.DCP         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:561px;top:38px; width:137px; height:17px; " id="S4_A" name="S4_A" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:419px;top:38px; width:137px; height:17px; " id="S2_A_DCP_EXEC_CNT" name="S2_A_DCP_EXEC_CNT" type="text" maxlength="2" tabindex="17" >        </input>
                <div id=="calendar_S2_A_DCP_EXEC_YYYYMMDD" style="position:absolute;left:277px;top:38px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_A_DCP_EXEC_YYYYMMDD" name="S2_A_DCP_EXEC_YYYYMMDD" type="text" tabindex="16" >        </input>
            </div>
<script>
        $('#S2_A_DCP_EXEC_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,maxDate:"+0d"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                <div id=="calendar_S2_P_DCP_PLAN_YYYYMMDD" style="position:absolute;left:134px;top:62px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_P_DCP_PLAN_YYYYMMDD" name="S2_P_DCP_PLAN_YYYYMMDD" type="text" tabindex="18" >        </input>
            </div>
<script>
        $('#S2_P_DCP_PLAN_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                <div id=="calendar_S2_P_DCP_EXEC_YYYYMMDD" style="position:absolute;left:276px;top:62px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_P_DCP_EXEC_YYYYMMDD" name="S2_P_DCP_EXEC_YYYYMMDD" type="text" tabindex="19" >        </input>
            </div>
<script>
        $('#S2_P_DCP_EXEC_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,maxDate:"+0d"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                	<div  class="infoLabel" style="position:absolute;left:58px;top:62px; width:73px; height:19px; " id="label50" name="label50"><label style=" line-height:21px; " >        P.DCP         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:561px;top:62px; width:137px; height:17px; " id="S4_P" name="S4_P" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:419px;top:62px; width:137px; height:17px; " id="S2_P_DCP_EXEC_CNT" name="S2_P_DCP_EXEC_CNT" type="text" maxlength="2" tabindex="20" >        </input>
                <div id=="calendar_S2_T_DCP_EXEC_YYYYMMDD" style="position:absolute;left:276px;top:86px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_T_DCP_EXEC_YYYYMMDD" name="S2_T_DCP_EXEC_YYYYMMDD" type="text" tabindex="22" >        </input>
            </div>
<script>
        $('#S2_T_DCP_EXEC_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,maxDate:"+0d"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                	<div  class="infoLabel" style="position:absolute;left:58px;top:86px; width:73px; height:19px; " id="label51" name="label51"><label style=" line-height:21px; " >        T.DCP         </label>        </div>
                <div id=="calendar_S2_T_DCP_PLAN_YYYYMMDD" style="position:absolute;left:134px;top:86px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_T_DCP_PLAN_YYYYMMDD" name="S2_T_DCP_PLAN_YYYYMMDD" type="text" tabindex="21" >        </input>
            </div>
<script>
        $('#S2_T_DCP_PLAN_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                	<input  class="TextBox-S" style="position:absolute;left:561px;top:86px; width:137px; height:17px; " id="S4_T" name="S4_T" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:419px;top:86px; width:137px; height:17px; " id="S2_T_DCP_EXEC_CNT" name="S2_T_DCP_EXEC_CNT" type="text" maxlength="2" tabindex="23" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:58px;top:109px; width:73px; height:19px; " id="label52" name="label52"><label style=" line-height:21px; " >        M.DCP         </label>        </div>
                <div id=="calendar_S2_M_DCP_EXEC_YYYYMMDD" style="position:absolute;left:276px;top:110px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_M_DCP_EXEC_YYYYMMDD" name="S2_M_DCP_EXEC_YYYYMMDD" type="text" tabindex="25" >        </input>
            </div>
<script>
        $('#S2_M_DCP_EXEC_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,maxDate:"+0d"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                <div id=="calendar_S2_M_DCP_PLAN_YYYYMMDD" style="position:absolute;left:134px;top:110px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_M_DCP_PLAN_YYYYMMDD" name="S2_M_DCP_PLAN_YYYYMMDD" type="text" tabindex="24" >        </input>
            </div>
<script>
        $('#S2_M_DCP_PLAN_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                	<input  class="TextBox-S" style="position:absolute;left:561px;top:110px; width:137px; height:17px; " id="S4_M" name="S4_M" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:419px;top:110px; width:137px; height:17px; " id="S2_M_DCP_EXEC_CNT" name="S2_M_DCP_EXEC_CNT" type="text" maxlength="2" tabindex="26" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:58px;top:133px; width:73px; height:19px; " id="label53" name="label53"><label style=" line-height:21px; " >        E.DCP         </label>        </div>
                <div id=="calendar_S2_E_DCP_EXEC_YYYYMMDD" style="position:absolute;left:276px;top:134px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_E_DCP_EXEC_YYYYMMDD" name="S2_E_DCP_EXEC_YYYYMMDD" type="text" tabindex="28" >        </input>
            </div>
<script>
        $('#S2_E_DCP_EXEC_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,maxDate:"+0d"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                <div id=="calendar_S2_E_DCP_PLAN_YYYYMMDD" style="position:absolute;left:134px;top:134px;">
          	<input  class="TextBox-S" style=" width:115px; height:17px; " id="S2_E_DCP_PLAN_YYYYMMDD" name="S2_E_DCP_PLAN_YYYYMMDD" type="text" tabindex="27" >        </input>
            </div>
<script>
        $('#S2_E_DCP_PLAN_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
                	<input  class="TextBox-S" style="position:absolute;left:561px;top:134px; width:137px; height:17px; " id="S4_E" name="S4_E" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:419px;top:134px; width:137px; height:17px; " id="S2_E_DCP_EXEC_CNT" name="S2_E_DCP_EXEC_CNT" type="text" maxlength="2" tabindex="29" >        </input>
                	<input  class="btn3" style="position:absolute;left:623px;top:163px; width:98px; height:23px; " id="Update" name="Update" type="button" value="Update" tabindex="30"  onclick="Update_OnClick(this);" ></input>
      </div>
        <div id="grid2_gdiv"  style=" position: absolute; top:759px; left:26px; width:241px;height:192px;" >
		     <table id="grid2" elType='Grid' ></table>
	    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:958px; left:30px;  width:435px; height:34px; " id="label23" name="label23"><label style=" line-height:36px;  font-family:굴림체;font-weight:bold;font-size:14.25pt;" >    File Upload     </label>    </div>

      <div  id="group4" name="group4"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:999px; left:26px;  width:891px; height:312px; " >  
                	<input type='hidden'  id="F_FILE_NAME" name="F_FILE_NAME"/>
        	<input type='hidden'  id="F_FILE_PATH" name="F_FILE_PATH"/>
        	<input type='hidden'  id="F_FILE_UNNAME" name="F_FILE_UNNAME"/>
        	<input type='hidden'  id="F_FILE_SIZE" name="F_FILE_SIZE"/>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:11px; width:115px; height:19px; " id="label24" name="label24"><label style=" line-height:21px; " >        Front Page File         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:455px;top:13px; width:435px; height:19px; " id="F1_FILE_NM" name="F1_FILE_NM"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        </label>        </div>
                	<input type='hidden'  id="F_FILE1_NAME" name="F_FILE1_NAME"/>
        	<input type='hidden'  id="F_FILE1_PATH" name="F_FILE1_PATH"/>
        	<input type='hidden'  id="F_FILE1_UNNAME" name="F_FILE1_UNNAME"/>
        	<input type='hidden'  id="F_FILE1_SIZE" name="F_FILE1_SIZE"/>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:41px; width:146px; height:19px; " id="label26" name="label26"><label style=" line-height:21px; " >        PL Sheet         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:455px;top:43px; width:435px; height:19px; " id="F1_FILE_NM1" name="F1_FILE_NM1"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:455px;top:76px; width:435px; height:19px; " id="F1_FILE_NM2" name="F1_FILE_NM2"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:76px; width:115px; height:19px; " id="label27" name="label27"><label style=" line-height:21px; " >        Cost Estimate Sheet         </label>        </div>
                	<input type='hidden'  id="F_FILE2_NAME" name="F_FILE2_NAME"/>
        	<input type='hidden'  id="F_FILE2_PATH" name="F_FILE2_PATH"/>
        	<input type='hidden'  id="F_FILE2_UNNAME" name="F_FILE2_UNNAME"/>
        	<input type='hidden'  id="F_FILE2_SIZE" name="F_FILE2_SIZE"/>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:455px;top:110px; width:435px; height:19px; " id="F1_FILE_NM3" name="F1_FILE_NM3"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:111px; width:146px; height:19px; " id="label28" name="label28"><label style=" line-height:21px; " >        Quote Sheet         </label>        </div>
                	<input type='hidden'  id="F_FILE3_NAME" name="F_FILE3_NAME"/>
        	<input type='hidden'  id="F_FILE3_PATH" name="F_FILE3_PATH"/>
        	<input type='hidden'  id="F_FILE3_UNNAME" name="F_FILE3_UNNAME"/>
        	<input type='hidden'  id="F_FILE3_SIZE" name="F_FILE3_SIZE"/>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:141px; width:115px; height:19px; " id="label29" name="label29"><label style=" line-height:21px; " >        DR Slip Sheet         </label>        </div>
                	<input type='hidden'  id="F_FILE4_NAME" name="F_FILE4_NAME"/>
        	<input type='hidden'  id="F_FILE4_PATH" name="F_FILE4_PATH"/>
        	<input type='hidden'  id="F_FILE4_UNNAME" name="F_FILE4_UNNAME"/>
        	<input type='hidden'  id="F_FILE4_SIZE" name="F_FILE4_SIZE"/>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:455px;top:142px; width:435px; height:19px; " id="F1_FILE_NM4" name="F1_FILE_NM4"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:176px; width:146px; height:19px; " id="label25" name="label25"><label style=" line-height:21px; " >        CHIP Specification Sheet         </label>        </div>
                	<input type='hidden'  id="F_FILE5_NAME" name="F_FILE5_NAME"/>
        	<input type='hidden'  id="F_FILE5_PATH" name="F_FILE5_PATH"/>
        	<input type='hidden'  id="F_FILE5_UNNAME" name="F_FILE5_UNNAME"/>
        	<input type='hidden'  id="F_FILE5_SIZE" name="F_FILE5_SIZE"/>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:455px;top:209px; width:435px; height:19px; " id="F1_FILE_NM6" name="F1_FILE_NM6"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:211px; width:115px; height:19px; " id="label30" name="label30"><label style=" line-height:21px; " >        Others 1         </label>        </div>
                	<input type='hidden'  id="F_FILE6_NAME" name="F_FILE6_NAME"/>
        	<input type='hidden'  id="F_FILE6_PATH" name="F_FILE6_PATH"/>
        	<input type='hidden'  id="F_FILE6_UNNAME" name="F_FILE6_UNNAME"/>
        	<input type='hidden'  id="F_FILE6_SIZE" name="F_FILE6_SIZE"/>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:455px;top:239px; width:435px; height:19px; " id="F1_FILE_NM7" name="F1_FILE_NM7"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:11px;top:241px; width:146px; height:19px; " id="label31" name="label31"><label style=" line-height:21px; " >        Others 2         </label>        </div>
                	<input type='hidden'  id="F_FILE7_NAME" name="F_FILE7_NAME"/>
        	<input type='hidden'  id="F_FILE7_PATH" name="F_FILE7_PATH"/>
        	<input type='hidden'  id="F_FILE7_UNNAME" name="F_FILE7_UNNAME"/>
        	<input type='hidden'  id="F_FILE7_SIZE" name="F_FILE7_SIZE"/>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:455px;top:274px; width:435px; height:19px; " id="F1_FILE_NM8" name="F1_FILE_NM8"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:13px;top:276px; width:115px; height:19px; " id="label32" name="label32"><label style=" line-height:21px; " >        Others 3         </label>        </div>
                	<input type='hidden'  id="F_FILE8_NAME" name="F_FILE8_NAME"/>
        	<input type='hidden'  id="F_FILE8_PATH" name="F_FILE8_PATH"/>
        	<input type='hidden'  id="F_FILE8_UNNAME" name="F_FILE8_UNNAME"/>
        	<input type='hidden'  id="F_FILE8_SIZE" name="F_FILE8_SIZE"/>
      </div>
        	<input  class="TextBox-S" style=" position: absolute; top:1102px; left:929px;  width:97px; height:17px; " id="F_DCP_TYPE" name="F_DCP_TYPE" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:1137px; left:939px;  width:97px; height:17px; " id="F_SEQ" name="F_SEQ" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:1169px; left:942px;  width:97px; height:17px; " id="F_PID" name="F_PID" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:1175px; left:483px;  width:435px; height:19px; " id="F1_FILE_NM5" name="F1_FILE_NM5"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:1319px; left:30px;  width:435px; height:34px; " id="label38" name="label38"><label style=" line-height:36px;  font-family:굴림체;font-weight:bold;font-size:14.25pt;" >    Calculate the DCP front Page     </label>    </div>

      <div  id="group5" name="group5"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:1361px; left:26px;  width:891px; height:84px; " >  
                	<div  class="infoLabel" style="position:absolute;left:269px;top:10px; width:151px; height:19px; " id="label42" name="label42"><label style=" line-height:21px; " >        Inside NRE         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:422px;top:10px; width:151px; height:19px; " id="label43" name="label43"><label style=" line-height:21px; " >        The Total Development         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:575px;top:10px; width:151px; height:19px; " id="label44" name="label44"><label style=" line-height:21px; " >        Life Operating Income         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:728px;top:10px; width:151px; height:19px; " id="label45" name="label45"><label style=" line-height:21px; " >        Life Operating Margin         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:116px;top:10px; width:151px; height:19px; " id="label40" name="label40"><label style=" line-height:21px; " >        Life Sales         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:15px;top:33px; width:100px; height:19px; " id="label39" name="label39"><label style=" line-height:21px; " >        Present         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:576px;top:33px; width:148px; height:17px; " id="S2_INCOME_AMT" name="S2_INCOME_AMT" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:729px;top:33px; width:148px; height:17px; " id="S2_INCOME_AMT_RATIO" name="S2_INCOME_AMT_RATIO" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:423px;top:33px; width:148px; height:17px; " id="S2_RD_COST" name="S2_RD_COST" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:270px;top:33px; width:148px; height:17px; " id="S2_NRE_AMT" name="S2_NRE_AMT" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:117px;top:33px; width:148px; height:17px; " id="S2_SALES_AMT" name="S2_SALES_AMT" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:15px;top:56px; width:100px; height:19px; " id="label41" name="label41"><label style=" line-height:21px; " >        Previous         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:117px;top:56px; width:148px; height:17px; " id="C1_SALES_AMT" name="C1_SALES_AMT" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:270px;top:56px; width:148px; height:17px; " id="C1_NRE_AMT" name="C1_NRE_AMT" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:423px;top:56px; width:148px; height:17px; " id="C1_RD_COST" name="C1_RD_COST" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:576px;top:56px; width:148px; height:17px; " id="C1_INCOME_AMT" name="C1_INCOME_AMT" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:729px;top:56px; width:148px; height:17px; " id="C1_INCOME_AMT_RATIO" name="C1_INCOME_AMT_RATIO" type="text" readonly  maxlength="50" tabindex="-1" >        </input>
      </div>
        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:1454px; left:30px;  width:435px; height:34px; " id="label46" name="label46"><label style=" line-height:36px;  font-family:굴림체;font-weight:bold;font-size:14.25pt;" >    DCP Information Storage     </label>    </div>

      <div  id="group6" name="group6"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:1496px; left:26px;  width:441px; height:46px; " >  
                	<div  class="infoLabel" style="position:absolute;left:23px;top:14px; width:100px; height:19px; " id="label47" name="label47"><label style=" line-height:21px; " >        Approved Date         </label>        </div>
                	<input  class="btn3" style="position:absolute;left:323px;top:14px; width:101px; height:21px; " id="button5" name="button5" type="button" value="Next DCP" tabindex="32"  onclick="button5_OnClick(this);" ></input>
                <div id=="calendar_S2_APPROVAL_YYYYMMDD" style="position:absolute;left:126px;top:15px;">
          	<input  class="TextBox-S" style=" width:96px; height:17px; " id="S2_APPROVAL_YYYYMMDD" name="S2_APPROVAL_YYYYMMDD" type="text" tabindex="31" >        </input>
            </div>
<script>
        $('#S2_APPROVAL_YYYYMMDD').datepicker({ 
          inline:true
          ,dateFormat:"yymmdd"
          ,constrainInput:true
          ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
          ,buttonImageOnly:true
          ,showOn:"button"
          ,changeMonth:true
          ,changeYear:true
          ,showButtonPanel:true
          ,currentText:"today"
          ,closeText:"close"
          ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
          ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
          ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
          ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
        });
</script>
      </div>
</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1009px; left:204px;  width:269px;" id="F_FILE" name="F_FILE" tabindex="-1"  onchange="F_FILE_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1040px; left:204px;  width:269px;" id="F_FILE1" name="F_FILE1" tabindex="-1"  onchange="F_FILE1_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1075px; left:204px;  width:269px;" id="F_FILE2" name="F_FILE2" tabindex="-1"  onchange="F_FILE2_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1110px; left:204px;  width:269px;" id="F_FILE3" name="F_FILE3" tabindex="-1"  onchange="F_FILE3_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1140px; left:204px;  width:269px;" id="F_FILE4" name="F_FILE4" tabindex="-1"  onchange="F_FILE4_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1175px; left:204px;  width:269px;" id="F_FILE5" name="F_FILE5" tabindex="-1"  onchange="F_FILE5_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1210px; left:204px;  width:269px;" id="F_FILE6" name="F_FILE6" tabindex="-1"  onchange="F_FILE6_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1240px; left:204px;  width:269px;" id="F_FILE7" name="F_FILE7" tabindex="-1"  onchange="F_FILE7_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1275px; left:204px;  width:269px;" id="F_FILE8" name="F_FILE8" tabindex="-1"  onchange="F_FILE8_OnChange(this);" ></input>
	<div style='display:none'>
	<iframe id='FileDownload'  name='FileDownload' style='border-width:0px;' src='' frameborder='0' ></iframe>
	</div>
	
</form>
    </div>
</body>
<script type="text/javascript" >
    var GridRowHeight= {"grid2":"22",};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {"S_DCP_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S2_DCP_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S2_CLOSING_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S2_BTM_AMT_ECP_UNIT":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S2_CLOSING_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S2_BTM_AMT_SSP_UNIT":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S2_APPLICA_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S2_BTM_AMT_TP_UNIT":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S2_APPLICA_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S_DCP_TYPE":":", "S2_DCP_TYPE":":", "S2_CLOSING_COMPANY_CD":":", "S2_BTM_AMT_ECP_UNIT":":", "S2_CLOSING_ORG_CD":":", "S2_BTM_AMT_SSP_UNIT":":", "S2_APPLICA_COMPANY_CD":":", "S2_BTM_AMT_TP_UNIT":":", "S2_APPLICA_ORG_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_CommonCombo.do","","S2_DCP_TYPE:SEL12,S2_BTM_AMT_ECP_UNIT:SEL8,S2_BTM_AMT_SSP_UNIT:SEL8,S2_BTM_AMT_TP_UNIT:SEL8","");
    var DCPsearch = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_DCPSearchInfo.do","S[A]:S","S3:SEL4,grid2:SEL8,S2:SEL10,F1:SEL6,S2:SEL14","");
    var FIleInfoAdd = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_FileInfoAdd.do","F[A]:F,S3[A]:S3,S[A]:S","F1:SEL21","");
    var DCPInfoUpdate = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_DCPInfoUpdate.do","S2[A]:S2,S3[A]:S3,S[A]:S","","");
    var DCPAPPROVAL = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_DCPApproval_Flow.do","S2[A]:S2,S3[A]:S3","","");
    var PID_Confirm = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_PIDConfirm.do","S[A]:S","C:SEL2","");
    var ApprovalDate_output = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_ApprovalDataSearch.do","S3[A]:S3","S4:SEL10,S4:SEL2,S4:SEL4,S4:SEL6,S4:SEL8","");
    var Calculate_DCP = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_DCP_Calculate.do","S[A]:S,S2[A]:S2,S3[A]:S3","S2:SEL2,C1:SEL4","");
    var sbmCtlSave = new SubMission( frm, "/hpms/20161004133312Control_saveUsingCheck.do","","","");
    var sbmCtlRead = new SubMission( frm, "/hpms/20161004133312Control_SearchUsingCheck.do","","","");
    var Approval_applicant_combo = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_Company_Org_cd.do","","S2_CLOSING_COMPANY_CD:SEL4,S2_APPLICA_COMPANY_CD:SEL4,S2_APPLICA_ORG_CD:SEL7,S2_CLOSING_ORG_CD:SEL7","");
    var Approval_applicant_ORG_Combo = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGCombobox.do","S2[A]:S2","S2_CLOSING_ORG_CD:SEL1","");
    var Approval_applicant_ORG_Combo1 = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_Approval_applicant_ORG_Combo.do","S2[A]:S2","S2_APPLICA_ORG_CD:SEL1","");
    var Update_File_AUTH_check = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_DCP_PIDCheck.do","S3[A]:S3","C:SEL2","");
    var DATA_TYPE_TITLE_Combo = new SubMission( frm, "/hpms/20160901101143PLSheetOutput_initCombo.do","S[A]:S2","S_DCP_TYPE:SEL2","");
    sbmInit.InitCombo("S_DCP_TYPE,S2_DCP_TYPE,S2_CLOSING_COMPANY_CD,S2_BTM_AMT_ECP_UNIT,S2_CLOSING_ORG_CD,S2_BTM_AMT_SSP_UNIT,S2_APPLICA_COMPANY_CD,S2_BTM_AMT_TP_UNIT,S2_APPLICA_ORG_CD");
    var DefalueGrid =[], ColNm_grid2=[] , ColInfo_grid2=[] ; 
    var grid2= jQuery("#grid2");
    var grid2_Data = []; 
    function HeadLabel_grid2(){
	ColNm_grid2.unshift("ROWID","PID","SUB PID","Purpose","OWNER_ORG_CD");
	return ColNm_grid2;
	}
    function Columninfo_grid2(){
	ColInfo_grid2.unshift(
        	 {index: "id", name:"id", width:75, key:true, hidden:true }
        	 ,{index:"PID",name:"PID",width:100,hidden:true,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SUB_PID",name:"SUB_PID",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'SUB_PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"APPLICATION",name:"APPLICATION",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'APPLICATION'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"OWNER_ORG_CD",name:"OWNER_ORG_CD",width:100,hidden:true,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'OWNER_ORG_CD'  }  ] }  ,cellattr:rowspan,align:"center"}	
);
	return ColInfo_grid2;
	}

    var initGrids = function() { 
        grid2.wizGrid({   
        	data:grid2_Data,   
        	height:153,  
        	width:241,    
        	cellEdit:false,   
        	Names: HeadLabel_grid2()
,        	Columns: Columninfo_grid2() ,
        	OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  

	         var gridName ='grid2';

	         $("[colheight=grid2]").css('height',"22px");
        		 $("#paginate_grid2").hide();
        		 
        		 
        		 
        		 
        	}  
        });



    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
        grid2.setDatasetName("G2");        
    });
</script>
</html>