package com.curady.userservice.domain.tendency.vo;

import java.util.HashMap;

public class TendencyVo {
    private HashMap<String, String> tendencyMap = new HashMap<>();

    public TendencyVo() {
        this.tendencyMap.put("backend", "서버/백엔드");
        this.tendencyMap.put("frontend", "프론트엔드");
        this.tendencyMap.put("android", "안드로이드");
        this.tendencyMap.put("ios", "아이폰 앱");
        this.tendencyMap.put("data", "데이터 엔지니어");
        this.tendencyMap.put("mobile_game", "모바일 게임");
        this.tendencyMap.put("game_client", "게임 클라이언트");
        this.tendencyMap.put("embedded", "임베디드");
        this.tendencyMap.put("iot", "사물인터넷/IoT");
        this.tendencyMap.put("block_chain", "블록체인");
        this.tendencyMap.put("system", "시스템/네트워크");
        this.tendencyMap.put("internet", "인터넷 보안");
        this.tendencyMap.put("ai", "인공지능/AI");
        this.tendencyMap.put("c", "C");
        this.tendencyMap.put("cpp", "C++");
        this.tendencyMap.put("c#", "C#");
        this.tendencyMap.put("go", "Go");
        this.tendencyMap.put("java", "Java");
        this.tendencyMap.put("javascript", "JavaScript");
        this.tendencyMap.put("kotlin", "Kotlin");
        this.tendencyMap.put("python", "Python");
        this.tendencyMap.put("ruby", "Ruby");
        this.tendencyMap.put("scala", "Scala");
        this.tendencyMap.put("swift", "Swift");
        this.tendencyMap.put("mysql", "MySQL");
        this.tendencyMap.put("project", "프로젝트 중심 실습");
        this.tendencyMap.put("example", "예제와 풀이 실습");
        this.tendencyMap.put("communication", "소통을 잘하는 강사님");
        this.tendencyMap.put("theory", "이론 위주");
        this.tendencyMap.put("cost", "가성비");
        this.tendencyMap.put("detail", "자세한 내용");
        this.tendencyMap.put("simple", "간결한 내용");
        this.tendencyMap.put("easy", "쉬운 이해");
        this.tendencyMap.put("recent", "최신 강의");
    }

    public String getTendencyName(String key) {
        return this.tendencyMap.get(key);
    }
}
