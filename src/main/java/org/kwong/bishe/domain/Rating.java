package org.kwong.bishe.domain;

/**
 * @author ������
 * @version V1.0
 * @Description
 * @Company �㶫ȫͨ�����ɷݹ�˾
 * @date 2016/3/26
 */
public class Rating {

    private String id ;

    private String userId ;

    private String movieId ;

    private int score ;

    private java.util.Date time ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public java.util.Date getTime() {
        return time;
    }

    public void setTime(java.util.Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", movieId='" + movieId + '\'' +
                ", score=" + score +
                ", time=" + time +
                '}';
    }
    //id��uuid
    public Rating(String userId, String movieId, int score, java.util.Date time) {
        this.userId = userId;
        this.movieId = movieId;
        this.score = score;
        this.time = time;
    }

    public Rating(){

    }
}
