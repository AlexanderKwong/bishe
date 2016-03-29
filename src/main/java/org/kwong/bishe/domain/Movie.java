package org.kwong.bishe.domain;

/**
 * @author ������
 * @version V1.0
 * @Description
 * @Company �㶫ȫͨ�����ɷݹ�˾
 * @date 2016/3/26
 */
public class Movie {

    private String id ;

    private String name ;

    private int uploadyear;

    public int getUploadyear() {
        return uploadyear;
    }

    public void setUploadyear(int uploadyear) {
        this.uploadyear = uploadyear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", uploadyear=" + uploadyear +
                '}';
    }

    public Movie(String id, String name, int uploadyear) {
        this.id = id;
        this.name = name;
        this.uploadyear = uploadyear;
    }

    public Movie(){

    }
}
