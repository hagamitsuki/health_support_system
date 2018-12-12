package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "moods")
@NamedQueries({
    @NamedQuery(
            name = "getAllMoods",
            query = "SELECT m FROM Mood AS m ORDER BY m.id DESC"
            ),
    @NamedQuery(
            name = "getMoodsCount",
            query = "SELECT COUNT(m) FROM Mood AS m"
            ),
    @NamedQuery(//自分が作成した気分を取得
            name = "getMyAllMoods",
            query = "SELECT m FROM Mood AS m WHERE m.user = :user ORDER BY m.id DESC"
            ),
    @NamedQuery(//自分が作成した気分の数を取得
            name = "getMyMoodsCount",
            query = "SELECT COUNT(m) FROM Mood AS m WHERE m.user = :user"
            )


})
@Entity
public class Mood {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /*user フィールドが、その作成者の情報。
     * ログインしている従業員の情報をオブジェクトのまま user フィールドに格納する。*/

    @Column(name = "mood_date", nullable = false)
    private Date mood_date;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob/*これを指定することで、改行もデータベースに保存される。*/
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getMood_date() {
        return mood_date;
    }

    public void setMood_date(Date mood_date) {
        this.mood_date = mood_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
