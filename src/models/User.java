package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "getAllUsers",
                    query = "SELECT u FROM User AS u ORDER BY u.id DESC"
                    ),
        @NamedQuery(name = "getUsersCount",
                    query = "SELECT COUNT(u) FROM User AS u"
                    ),
        @NamedQuery(name = "checkRegisteredCode",
                    query = "SELECT COUNT(u) FROM User AS u WHERE u.code =:code"
                    /*指定されたユーザー番号がすでにデータベースに登録されているかチェック（指定されたユーザ番号＝:code）*/
                    ),
        @NamedQuery(name = "checkLoginCodeAndPassword",
                    query = "SELECT u FROM User AS u WHERE u.delete_flag=0 AND u.code=:code AND u.password=:pass"
                    /*                                     (現役)         かつ (指定されたユーザー番号とパスワードが両方DBに登録されているか)
                     * ユーザーがログインするときにユーザー番号とパスワードが正しいかをチェック*/
                    )
})
@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue：プライマリーキーカラムにユニーク（オンリーワン）な値を自動で生成、そして付与する方法を指定するアノテーション
     * strategy：エンティティクラスのプライマリー地を生成する方法を指定する属性
     * GenerationType.IDENTITY：データベースのidentity列を利用して、プライマリーキーを生成*/
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    /*nullable：nullはだめ。必ず何か入れなければいけない。nullだったらエラーになる。*/
    /*unique=true：一意制約（オンリーワン）。既に存在しているユーザ番号は登録できない旨をDBに教えてあげるための設定*/
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", length=64, nullable = false)
    private String password;

    @Column(name = "admin_flag", nullable = false)
    private Integer admin_flag;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Integer getAdmin_flag(){
        return admin_flag;
    }

    public void setAdmin_flag(Integer admin_flag){
        this.admin_flag = admin_flag;
    }

    public Timestamp getCreated_at(){
        return created_at;
    }

    public void setCreated_at(Timestamp created_at){
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at(){
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at){
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag(){
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag){
        this.delete_flag = delete_flag;
    }
}
