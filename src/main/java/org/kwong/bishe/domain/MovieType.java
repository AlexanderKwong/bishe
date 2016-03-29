package org.kwong.bishe.domain;

/**
 * @author ������
 * @version V1.0
 * @Description
 * @Company �㶫ȫͨ�����ɷݹ�˾
 * @date 2016/3/26
 */
public class MovieType {

    private String id ;

    private String movieId ;

    private String typeId ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "MovieType{" +
                "id='" + id + '\'' +
                ", movieId='" + movieId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }

    public MovieType(String movieId, String typeId) {
        this.movieId = movieId;
        this.typeId = typeId;
    }

    public MovieType(){

    }
}
