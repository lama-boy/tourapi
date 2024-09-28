package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {

    String servicekey = "0C4i%2Fipzgt0CEVu4Zdu3n4Yfnypyt4vuRXPaG4Ya879UAIbninBThK1tcHUdynOQVdjcHkmP6sezsV34hQlijA%3D%3D";

    // todo
    // return getData("LocgoHubTarService/areaBasedList",params,1,10); 로 바꾸기

    // 기초 기반 데이터
    @GetMapping("/getGichoBaseData")
    public String getData1(@RequestParam("areaCd")String areaCd,@RequestParam("sigunguCd")String sigunguCd,@RequestParam("baseYm") String baseYm) throws Exception{
        String params = "baseYm=" + baseYm + "&areaCd=" + areaCd + "&sigunguCd" + sigunguCd;
        System.out.println(params);
        return getData("LocgoHubTarService/areaBasedList","baseYm=202408&areaCd=11&signguCd=11530",1,10);
    }

    // 방문자 집중률 추이 데이터
    @GetMapping("/tourConcRateData")
    public String getData2(@RequestParam("areaCd")String areaCd,@RequestParam("sigunguCd")String sigunguCd,@RequestParam("baseYm") String tAtsNm) throws Exception{
        //&tAtsNm=%EA%B0%84%ED%98%84%EA%B4%80%EA%B4%91%EC%A7%80&_type=json
        String params = "areaCd=" + areaCd + "&sigunguCd" + sigunguCd + "&tAtsNm=" + tAtsNm;
        System.out.println(params);
        //return getData("TatsCnctrRateService/tatsCnctrRatedList","&areaCd=51&signguCd=51130&tAtsNm=",1,10);
        return getData("TatsCnctrRateService/tatsCnctrRatedList","areaCd=51&signguCd=51130&tAtsNm=",1,10);
    }

    // 두루누비는 코스, 길 목록 으로 두개로 나누어짐
    // 두루누비는 테스트 데이터가 나오지 않는 관계로 한 번 더 확인해봐야됨.
    @GetMapping("/getDurunubiData")
    public String getData3() throws Exception{
        //&themeNm=%EC%B2%9C%EC%A7%80%EC%9D%B8&brdDiv=DNWW
        System.out.println("asdf");
        return getData("Durunubi/routeList","themeNm=%EC%B2%9C%EC%A7%80%EC%9D%B8&brdDiv=DNWW",1,100);
        //&crsKorNm=%EB%B0%80%EC%96%91%EA%B0%95&routeIdx=%20&crsLevel=2&brdDiv=DNBW
        //return getData("Durunubi/courseList","&routeIdx=T_ROUTE_MNG0000000006&crsKorNm=%EB%B0%80%EC%96%91%EA%B0%95&routeIdx=%20&crsLevel=2&brdDiv=DNBW",1,10);
    }

    // 투어 빅데이터인데 두개로 나누어짐. 광역 지자체 지역 방문자수 / 기초 지자체 지역방문자수
    @GetMapping("/getTourBigData")
    public String getData4(@RequestParam("startYmd")String startYmd,@RequestParam("endYmd")String endYmd) throws Exception{
        //지역 지자체 지역방문자수 집계 데이터 정보 조회
        //startYmd=20210513&endYmd=20210513
        //return getData("DataLabService/locgoRegnVisitrDDList","&startYmd=20210513&endYmd=20210513",1,100);
        //광역 지자체 지역방문자수 집계 데이터 정보 조회
        String params = "startYmd=" + startYmd + "&endYmd=" + endYmd;
        System.out.println(params);
        //return getData("DataLabService/metcoRegnVisitrDDList","&startYmd=20210513&endYmd=20210513",1,10);
        return getData("DataLabService/metcoRegnVisitrDDList","startYmd=20210513&endYmd=20210513",1,10);
    }

    // 한국관광공사_고캠핑 정보 조회서비스
    // no params
    @GetMapping("/getGoCampingData")
    public String getData5() throws Exception{
        // 총 5개의 항목을 가질 수 있다.
        // 위치기반 목록 조회
        // return getData("GoCamping/locationBasedList","mapX=128.6142847&mapY=36.0345423&radius=2000",1,100);
        // 키워드 검색 목록 조회
        // return getData("GoCamping/searchList","keyword=%EC%95%BC%EC%98%81%EC%9E%A5",1,100);
        // 요청변수
        // return getData("/GoCamping/imageList","&contentId=3429",1,100);

        // 동기화 목록 조회
        // return getData("/GoCamping/basedSyncList","&syncStatus=A",1,100);
        // 기본 정보 목록 조회
        return getData("GoCamping/basedList","",1,10);
    }

    // 관광지별 연관 관광지 정보
    @GetMapping("/getRelatedTourLocData")
    public String getData6(@RequestParam("baseYm")String baseYm,@RequestParam("areaCd")String areaCd,@RequestParam("sigunguCd")String sigunguCd,@RequestParam("keyword")String keyword) throws Exception{
        // 지역기반 관광지별 연관 과노강지 정보 목록 조회
        // return getData("/TarRlteTarService/areaBasedList","&baseYm=202408&areaCd=11&signguCd=11530",1,100);
        String params = "baseYm=" + baseYm + "&areaCd=" + areaCd + "&sigunguCd=" + sigunguCd + "&keyword" + keyword;
        System.out.println(params);
        // 키워드 검색 관광지별 연관 관광지 정보 목록 조회
        return getData("TarRlteTarService/searchKeyword","baseYm=202408&areaCd=51&signguCd=51130&keyword=%EB%AE%A4%EC%A7%80%EC%97%84%EC%82%B0",1,10);
    }


    public String getData(String link,String params, int pageNo,int numOfRows) throws Exception{

        //https://apis.data.go.kr/B551011/LocgoHubTarService/areaBasedList?&&_type=json

        String apiUrl = "https://apis.data.go.kr/B551011/"+link+"?serviceKey="+servicekey+"&pageNo="+pageNo+"&numOfRows="+numOfRows+"&MobileOS=ETC&MobileApp=AppTest&"+params+"&_type=json";
        // HTTP 요청 생성
        System.out.println(apiUrl);
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 5. 통신을 위한 메소드 SET.
        conn.setRequestMethod("GET");
        // 6. 통신을 위한 Content-type SET.
        conn.setRequestProperty("Content-type", "application/json");
        // 7. 통신 응답 코드 확인.
        System.out.println("Response code: " + conn.getResponseCode());
        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
            System.out.println(line);
        }
        // 10. 객체 해제.
        rd.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        System.out.println(sb.toString());
        return sb.toString();
    }
}
