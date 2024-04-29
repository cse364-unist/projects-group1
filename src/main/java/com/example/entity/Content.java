package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "content")
public class Content {

    @Id
    private String id;  // MongoDB가 자동으로 할당할 ID
    private String name; // 컨텐츠 이름
    private String url;  // 관련된 URL 정보

    // 기본 생성자
    public Content() {
    }

    // 모든 필드를 포함한 생성자
    public Content(String name, String url) {
        this.name = name;
        this.url = url;
    }

    // Getter와 Setter 메서드
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
