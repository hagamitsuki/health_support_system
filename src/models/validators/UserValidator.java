package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
/*①errorsリストを作成*/
    public static List<String> validate(User u, Boolean code_duplicate_check_flag, Boolean password_check_flag){
        /*戻り値がList型の（validateという名前の）メソッド
         * ユーザー番号とパスワードについては、第2引数にBoolean型の引数を用意し、そこが true であればパスワードの入力値チェック、
         * および社員番号の重複チェックを行う*/
        List<String> errors = new ArrayList<String>();

        String code_error = _validateCode(u.getCode(),code_duplicate_check_flag);
        if(!code_error.equals("")){
            errors.add(code_error);
        }

        String name_error =_validateName(u.getName());
        if(!name_error.equals("")){
            errors.add(name_error);
        }

        String password_error = validatePassword(u.getPassword(), password_check_flag);
        if(!password_error.equals("")){
            errors.add(password_error);
        }

        return errors;

    }


/*②errorsに値を入れる値を作成。入れない場合は""を返す。*/
    private static String _validateCode(String code, Boolean code_duplicate_check_flag) {
        if(code == null || code.equals("")){
            return "ユーザー番号を入力してください。";
        }

        if(code_duplicate_check_flag){
            EntityManager em = DBUtil.createEntityManager();
            long users_count =(long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                       .setParameter("code", code)
                                        .getSingleResult();
            em.close();
            if(users_count > 0){
                return "入力されたユーザー番号の情報は既に存在しています。";
            }
        }

        return "";
    }

    private static String _validateName(String name) {
        if(name == null || name.equals("")){
            return "氏名を入力してください。";
        }

        return "";
    }


    private static String validatePassword(String password, Boolean password_check_flag) {
        if(password_check_flag && (password == null || password.equals(""))){
            /*、新規登録（create）の場合はパスワードの入力値チェックをしたい、
             * (UserCreateServletからは"password_check_flag = true"が引数として渡される)
             * でも変更（update）の場合は入力されていなければ入力値チェックは実行したくはない
             * (UserUpdateServletからは"password_check_flag = true"だが、password==nullではないのでこのif文の条件はtrueとならない。)*/
            return "パスワードを入力してください。";
        }

        return "";
    }


}
