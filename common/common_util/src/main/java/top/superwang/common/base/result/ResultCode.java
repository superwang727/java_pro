package top.superwang.common.base.result;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ResultCode {


    SUCCESS(true,20000,"成功"),

    ERROR(false,20001,"失败"),

    UPLOAD_ERROR(false,21004,"文件上传失败"),

    UPLOAD_EXCEL_ERROR(false,21006,"上传excel文件失败");


    private Boolean success;

    private Integer code;

    private String message;



    ResultCode(Boolean success,Integer code, String message){

        this.success=success;
        this.code=code;
        this.message=message;
    }
}
